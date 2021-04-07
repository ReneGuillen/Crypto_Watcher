package com.project.crytowatcher;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(path="/API")
public class MainController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String username;
    
    @GetMapping(path = "/showCoins")
    public @ResponseBody List<String> showCoins(Principal principal){
        /* 
        Method retrieves coins from the user's favorite list on database
        at the same time that initialize username for other methods for
        quick access. 
        returns:
             coins as a list to later display on front end.
        */
 
        //Get username and initialize global variable.
        String username = getUser(principal);
        this.username = username;
        
        //Retrieve coins from user's database based on username.
        List<String> coins = new ArrayList<>();
        String sql = "SELECT "+username+" FROM heroku_4c689183642aecd.coins;";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for(Map<String, Object> map : list){
            Object object = map.get(username);
            String coin = object.toString();
            coins.add(coin);
        }
        return coins;
    }

    //
    @PutMapping(path = "/addCoin")
    public @ResponseBody void addCoin(@RequestParam String coin){
        /* 
        Method adds a new coin to the user's favorite list on database.
        Updates current coin count + 1.
        */
        
        //add 1 to current count where id = 1 (First row under username).
        int count = Integer.parseInt(getCurrentCount(username)) + 2;
        String sql = "UPDATE heroku_4c689183642aecd.coins SET " + username + " = '" + coin + "' WHERE id = " + String.valueOf(count) +";";
        jdbcTemplate.update(sql);
        updateCount(count - 2, 1, username);
    }

    @DeleteMapping(path = "/deleteCoin")
    public @ResponseBody void deleteCoin(@RequestParam String coin){
        /*
        Method deletes the given coin from the user's favorite list on dabase.
        First finds the coin's location to delete it and re-arranges the column.
        */
        
        //Get current coin's location from database.
        String sql = "SELECT "+ username +" FROM heroku_4c689183642aecd.coins;";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int count = 0;
        for(Map<String, Object> map : list){
            count++;
            Object object = map.get(username);
            if(coin.equals(object.toString()))
                break;
        }
        
        //Delete the given coin based on its location on database.
        String sql2 = "UPDATE heroku_4c689183642aecd.coins SET "+ username +" = '' WHERE id = " + String.valueOf(count) + ";";
        jdbcTemplate.update(sql2);
        count = Integer.parseInt(getCurrentCount(username));
        updateCount(count, -1, username);
    }
    
    // Method arranges the user's column to avoid white cells.
    private void arrangeColumn(String username){
        //Clear empty cells and re-arrange column.
        String sql1 = "SELECT "+ username +" heroku_4c689183642aecd.coins;";
        List<Map<String, Object>> list2 = jdbcTemplate.queryForList(sql1);
        List<String> coins = new ArrayList<>();
        
        //fill array to save coins and clear all cells.
        count = 1;
        for(Map<String, Object> map : list2){
            Object object = map.get(username);
            coin.add(object.toString());
            clearCell(String.valueOf(count++), username);
        }
        
        //fill column with previous values in order with no empty cells.
        count = 1;
        for(String s : coins){
            if(!s.equals("")){
                arrangeColumn(String.valueOf(count++), s, username);
            }
        }
    }
    
    //Clears current user's cell when deleting a coin.
    private void clearCell(String id, String username){
        String sql = "UPDATE heroku_4c689183642aecd.coins SET "+ username +" = '' WHERE id = " + id + ";";
        jdbcTemplate.update(sql);
    }
    
    //moves the coin up the list to remove white cells.
    private void arrangeColumn(String id, String stock, String username){
        String sql = "UPDATE heroku_4c689183642aecd.coins SET "+ username +" = '" + stock + "' WHERE id = " + id +";";
        jdbcTemplate.update(sql);
    }

    //Method to get current coin count for the user given.
    private String getCurrentCount(String username){
        String sql = "SELECT " + username + " FROM heroku_4c689183642aecd.coins WHERE id = 1;";
        return (String) jdbcTemplate.queryForObject(sql, new Object[]{}, String.class);
    }

    //Method to update current coin count for the user given.
    private void updateCount(int count, int number, String username){
        String sql =
                "UPDATE heroku_4c689183642aecd.coins SET " + username + " = '" + String.valueOf(count + number) + "' WHERE id = 1;";
        jdbcTemplate.update(sql);
    }
}

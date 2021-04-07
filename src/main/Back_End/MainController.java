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
@RequestMapping(path="/")
@CrossOrigin(origins = "*")
public class MainController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String username;

    //Show stocks from the user's favorite list on db.
    @RequestMapping(path = "/showStocks")
    public @ResponseBody List<String> showStocks(Principal principal){
        String username = getUser(principal);
        this.username = username;
        List<String> stocks = new ArrayList<>();
        String sql = "SELECT "+username+" FROM heroku_4c689183642aecd.coins;";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for(Map<String, Object> map : list){
            Object object = map.get(username);
            String stock = object.toString();
            stocks.add(stock);
        }
        return stocks;
    }

    //Add a new stock to the user's favorite list on db.
    @RequestMapping(path = "/addStock")
    public @ResponseBody void addStock(@RequestParam String stock){
        //add 1 to current count where id = 1; //First row under username;
        int count = Integer.parseInt(getCurrentCount(username)) + 2;
        String sql = "UPDATE heroku_4c689183642aecd.coins SET " + username + " = '" + stock + "' WHERE id = " + String.valueOf(count) +";";
        jdbcTemplate.update(sql);
        updateCount(count - 2, 1, username);
    }

    //Delete the given stock from the user's favorite list on db.
    @RequestMapping(path = "/deleteStock")
    public @ResponseBody void deleteStock(@RequestParam String stock){
        String sql = "SELECT "+ username +" FROM heroku_4c689183642aecd.coins;";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int count = 0;
        for(Map<String, Object> map : list){
            count++;
            Object object = map.get(username);
            if(stock.equals(object.toString()))
                break;
        }
        String sql2 = "UPDATE heroku_4c689183642aecd.coins SET "+ username +" = '' WHERE id = " + String.valueOf(count) + ";";
        jdbcTemplate.update(sql2);
        count = Integer.parseInt(getCurrentCount(username));
        updateCount(count, -1, username);

        //Clear empty cells and re-arrange column.
        String sql1 = "SELECT "+ username +" heroku_4c689183642aecd.coins;";
        List<Map<String, Object>> list2 = jdbcTemplate.queryForList(sql1);
        List<String> stocks = new ArrayList<>();
        //fill array to save stocks and clear all cells.
        count = 1;
        for(Map<String, Object> map : list2){
            Object object = map.get(username);
            stocks.add(object.toString());
            clearCell(String.valueOf(count++), username);
        }
        //fill column with previous values in order with no empty cells.
        count = 1;
        for(String s : stocks){
            if(!s.equals("")){
                arrangeColumn(String.valueOf(count++), s, username);
            }
        }
    }

    public void clearCell(String id, String username){
        String sql = "UPDATE heroku_4c689183642aecd.coins SET "+ username +" = '' WHERE id = " + id + ";";
        jdbcTemplate.update(sql);
    }

    public void arrangeColumn(String id, String stock, String username){
        //move stock up the list.
        String sql = "UPDATE heroku_4c689183642aecd.coins SET "+ username +" = '" + stock + "' WHERE id = " + id +";";
        jdbcTemplate.update(sql);
    }

    //Method to get current stock count for the user given.
    public String getCurrentCount(String username){
        String sql = "SELECT " + username + " FROM heroku_4c689183642aecd.coins WHERE id = 1;";
        return (String) jdbcTemplate.queryForObject(sql, new Object[]{}, String.class);
    }

    //Method to update current stock count for the user given.
    public void updateCount(int count, int number, String username){
        String sql =
                "UPDATE heroku_4c689183642aecd.coins SET " + username + " = '" + String.valueOf(count + number) + "' WHERE id = 1;";
        jdbcTemplate.update(sql);
    }
}

package com.project.crytowatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path="/")
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //Search method to get username from github response after login.
    public String getUser(Principal principal) {
        int count = 2;
        int index = 0;
        String details = principal.toString();
        StringBuilder username = new StringBuilder();
        while(count > 0){
            if(details.charAt(index++) == '=')
                count--;
        }
        for(int i = 0; i < details.length(); i++){
            if(details.charAt(index) == ',')
                break;
            username.append(details.charAt(index++));
        }
        return username.toString();
    }

    //Add user to the db table after getting user's info from github.
    @GetMapping("/addUser")
    public @ResponseBody String addUser(Principal principal){
        String username = getUser(principal);
        if(!checkIfExists(username)){
            //Create user based on github username on table.
            String sql = "ALTER TABLE heroku_4c689183642aecd.coins ADD " + username + " varchar(40) not null;";
            jdbcTemplate.update(sql);
            //set current stock count to 0.
            String sql2 = "UPDATE heroku_4c689183642aecd.coins SET " + username + " = '0' WHERE (`id` = '1');";
            jdbcTemplate.update(sql2);
            return "new user has been added '" + username + "' Welcome";
        }
        return "User " + username + " already exists, WELCOME BACK!!";
    }

    //Check if user already exists on db table based on response/error.
    public boolean checkIfExists(String username){
        try {
            String sql = "SELECT " + username +" FROM heroku_4c689183642aecd.coins;";
            List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        }catch (Exception error){
            return false;
        }
        return true;
    }
}

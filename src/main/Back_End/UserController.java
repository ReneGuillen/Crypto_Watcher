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
    
    @GetMapping("/getUser")
    public String getUser(Principal principal) {
        /*
        Search Method to get username from github response after login to
        later add the user to database table utilizing O(N)Time / O(N)Space.
        Returns:
            user's username as a string.
        */
        
        //Search for username on principal's response
        int count = 2;
        int index = 0;
        String details = principal.toString();
        StringBuilder username = new StringBuilder();
        
        //get username's index in string.
        while(count > 0){
            if(details.charAt(index++) == '=')
                count--;
        }
        //append chars to the stringbuilder to get full username.
        for(int i = 0; i < details.length(); i++){
            if(details.charAt(index) == ',')
                break;
            username.append(details.charAt(index++));
        }
        return username.toString();
    }

    @PostMapping("/addUser")
    public @ResponseBody String addUser(Principal principal){
        /*
        Method adds user to the db table after getting user's info from github.
        Username is look up on the table if it exists returns coins if not, new user
        gets created with username.
        */
        
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

    //Check if user already exists on databseb table based on response/error.
    private boolean checkIfExists(String username){
        try {
            String sql = "SELECT " + username +" FROM heroku_4c689183642aecd.coins;";
            List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        }catch (Exception error){
            return false;
        }
        return true;
    }
}

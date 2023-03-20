package com.cooperps.rps.webservice;

import com.cooperps.rps.DatabaseConnectionManager;
import com.cooperps.rps.data.User;
import com.cooperps.rps.data.UserDAO;
import com.cooperps.rps.business.UserService;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final UserService userService;

    public WebserviceController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @GetMapping("/getUsers")
    public List<User> getUsers(){
        System.out.println("getUsers");
        return this.userService.getUsers();
    }

    @PostMapping("/getUserById")
    public User create(@RequestBody String message) {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("db",
                "rps", "postgres", "password");
        User user = new User();
        try {
            Connection connection = dcm.getConnection();
            UserDAO userDAO = new UserDAO(connection);

            user = userDAO.findById(Integer.parseInt(message));
            System.out.println(user);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @PostMapping("/setGamesAndWins")
    public void setGamesAndWins(@RequestBody String message) {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("db",
                "rps", "postgres", "password");
        try {
            Connection connection = dcm.getConnection();
            UserDAO userDAO = new UserDAO(connection);

            userDAO.setGamesAndWins(2, 10000000);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

}

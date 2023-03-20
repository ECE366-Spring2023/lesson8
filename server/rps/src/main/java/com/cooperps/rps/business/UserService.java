package com.cooperps.rps.business;

import com.cooperps.rps.DatabaseConnectionManager;
import com.cooperps.rps.data.User;
import com.cooperps.rps.data.UserDAO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public List<User> getUsers() {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("db",
                "rps", "postgres", "password");
        List<User> userList = new ArrayList<>();
        try {
            Connection connection = dcm.getConnection();
            UserDAO userDAO = new UserDAO(connection);

            userList = userDAO.getUsers();
            System.out.println(userList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}

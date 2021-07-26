package com.example.assginment3.controller;
import com.example.assginment3.dao.DatabaseDetailsDao;
import com.example.assginment3.entity.DatabaseDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@RestController
public class Controller {

    @Autowired
    private DatabaseDetailsDao databaseDetailsDao;

    @PostMapping("/switchDatabase/{username}")
    public void switchDatabase(@PathVariable String username)
    {
        DatabaseDetails databaseDetails=databaseDetailsDao.findByUsername(username);
        String jdbcUrl = databaseDetails.getUrl();
        String password = databaseDetails.getPassword();
        try{
            System.out.println(jdbcUrl+" "+username+" "+password);
            Connection connection = DriverManager.getConnection(jdbcUrl,username,password);
            Statement statement=connection.createStatement();
            statement.executeUpdate("CREATE TABLE test (id INT)");
            System.out.println("Table created");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
    @PostMapping("/setDatabaseDetails")
    public DatabaseDetails setDatabaseDetails(@RequestBody DatabaseDetails databaseDetails)
    {
        return databaseDetailsDao.save(databaseDetails);
    }
}

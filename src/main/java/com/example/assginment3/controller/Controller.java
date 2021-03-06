package com.example.assginment3.controller;
import com.example.assginment3.dao.DatabaseDetailsDao;
import com.example.assginment3.dao.TestDao;
import com.example.assginment3.entity.DatabaseDetails;
import com.example.assginment3.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private DatabaseDetailsDao databaseDetailsDao;

    @Autowired
    private TestDao testDao;

    @PostMapping("/switchDatabase/{id}")
    public Connection switchDatabase(@PathVariable int id)
    {
        DatabaseDetails databaseDetails=databaseDetailsDao.findById(id);
        if(databaseDetails==null)
        {
            return null;
        }
        String jdbcUrl = databaseDetails.getUrl();
        String username=databaseDetails.getUsername();
        String password = databaseDetails.getPassword();
        try{
            Connection connection = DriverManager.getConnection(jdbcUrl,username,password);
            Statement statement=connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT  EXISTS test (id INT,name VARCHAR(100),PRIMARY KEY (id))");
            return connection;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }
    @PostMapping("/setDatabaseDetails")
    public DatabaseDetails setDatabaseDetails(@RequestBody DatabaseDetails databaseDetails)
    {
        return databaseDetailsDao.save(databaseDetails);
    }

    @GetMapping("{db_id}/getData")
    public List<Test> getData(@PathVariable int db_id)
    {
        List<Test> list=new ArrayList<>();
        Connection connection=switchDatabase(db_id);
        if(connection==null)
            return list;
        try{
            Statement statement=connection.createStatement();
            ResultSet set=statement.executeQuery("select * from test");
            while (set.next()) {
                Test test=new Test();
                int id=(set.getInt(1));
                String name=(set.getString("name"));
                test.setId(id);
                test.setName(name);
                list.add(test);
            }

        }
        catch (Exception exception)
        {

        }
        return list;
    }
}

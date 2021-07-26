package com.example.assginment3.dao;

import com.example.assginment3.entity.DatabaseDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DatabaseDetailsDao extends JpaRepository<DatabaseDetails,Integer> {
    public DatabaseDetails findByUsername(String url);

}

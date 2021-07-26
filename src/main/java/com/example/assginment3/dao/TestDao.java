package com.example.assginment3.dao;

import com.example.assginment3.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestDao extends JpaRepository<Test,Integer> {
}

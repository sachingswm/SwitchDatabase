package com.example.assginment3.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class DatabaseDetails {
    @Id
    @GeneratedValue
    private int id;
    private String url;
    private String username;
    private String password;

}

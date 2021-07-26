package com.example.assginment3.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="test")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Test {
    @Id
    @GeneratedValue
    private int id;
    private String name;
}

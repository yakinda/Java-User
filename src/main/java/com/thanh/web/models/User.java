package com.thanh.web.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String address;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dob;
    private String imgUrl;
}

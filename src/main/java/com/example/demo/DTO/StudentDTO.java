package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor 
public class StudentDTO {
    private Integer id;
    private String name;
    private String branch;
    private String phone_no;
}

    
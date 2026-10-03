package com.example.demo.DTO;

import lombok.*;

@Data
@AllArgsConstructor
public class StudentRequestDTO {
    private String name;
    private String branch;
    private String phone_no;
    private String email;
}
    
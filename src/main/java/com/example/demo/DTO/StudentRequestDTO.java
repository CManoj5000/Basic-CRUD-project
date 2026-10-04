package com.example.demo.DTO;

import jakarta.validation.constraints.*;

import lombok.*;

@Data
@AllArgsConstructor
public class StudentRequestDTO {
    @NotBlank (message = "Name cannot be blank")
    private String name;
    private String branch;
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phone_no;
    @Email (message = "Email should be valid")
    private String email;
}
    
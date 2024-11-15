package org.example.projectspringojt.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.projectspringojt.entity.Role;

import java.time.LocalDate;

@Data
public class RegisterUserDTO {
    @NotBlank(message = "Name is required")
    private String  userName;

//    @NotNull(message = "Date of Birth is required")
//    private LocalDate Dob;
//
//    @NotNull(message = "National ID is required")
//    private Integer nationalId;

    @NotBlank(message = "Phone number is required")
    @Size(max = 11, message = "Phone number must be 11 digits or less")
    private String phone;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    private String address;

    @NotBlank(message = "Password is required")
    private String password;


    private Role role = Role.CUSTOMER;


}

package org.example.projectspringojt.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public class CreateUser {
    private Integer id;

    @NotBlank(message = "Username is required!")
    private String customerName;

    private LocalDate Dob;
    private Integer nationalId;

    @Length(min = 8, max = 12, message = "Password have length from {min} to {max}")
    private String password;

    @Pattern(regexp = "\\d{10}", message = "Phone invalid!")
    private String phone;

    @Pattern(regexp = "[a-zA-Z0-9@.]{5,30}", message = "Email invalid format!")
    private String email;

    private String address;
    private Integer wallet;
    private String drivingLicense;
    private String role;
}

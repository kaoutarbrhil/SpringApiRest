package com.api.springapirest.entity;

import com.api.springapirest.enums.EmployeeRoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("EMPLOYEE")
public class Employee extends User {

    @NotNull(message = "Firstname must be not null")
    @NotBlank(message = "Firstname must be not blank")
    @Size(min = 2, max = 50, message = "Firstname size must be between 2 and 50 characters")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotNull(message = "Lastname must be not null")
    @NotBlank(message = "Lastname must be not blank")
    @Size(min = 2, max = 50, message = "Lastname size must be between 2 and 50 characters")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Min(value = 3000, message = "Salary must be gt 3000")
    @Column(name = "salary")
    private Double salary;

    @NotNull(message = "Creation date must be not null")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_creation", nullable = false)
    private Date date;

    @NotNull(message = "Active must be not null")
    @Column(name = "active", nullable = false)
    private Boolean active;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role must be not null")
    @Column(name = "role", nullable = false)
    private EmployeeRoleEnum role;

    @Size(max = 255, message = "Image URL must not exceed 255 characters")
    @Column(name = "image_url", length = 255)
    private String imageUrl;
}

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
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @NotNull(message = "Email must be not null")
    @NotBlank(message = "Email must be not blank")
    @Size(min = 10, max = 50, message = "Email size must be between 10 and 50 characters")
    @Email(message = "Email format invalid")
    @Column(name = "mail", unique = true, nullable = false)
    private String mail;

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

    @NotBlank(message = "Password must be not blank")
    @NotNull(message = "Password must be not null")
    @Size(min = 8, max = 16, message = "Password character must be between 8 and 16")
    @Column(name = "password", nullable = false)
    private String password;
}

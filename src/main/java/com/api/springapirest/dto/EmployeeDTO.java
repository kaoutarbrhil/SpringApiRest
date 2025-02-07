package com.api.springapirest.dto;

import com.api.springapirest.enums.EmployeeRoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    @Schema(description = "Prénom de l'employé", required = true)
    protected String firstName;

    @Schema(description = "Nom de famille de l'employé", required = true)
    protected String lastName;

    @Schema(description = "Adresse email de l'employé", required = true)
    protected String mail;

    @Schema(description = "Salaire de l'employé", required = true)
    protected Double salary;

    @Schema(description = "L'employé est-il actif ?", required = true)
    protected Boolean active;

    @Schema(description = "Rôle de l'employé", example = "MANAGER", required = true)
    protected EmployeeRoleEnum role;

}

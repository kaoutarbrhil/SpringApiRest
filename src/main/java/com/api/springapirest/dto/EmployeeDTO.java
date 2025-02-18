package com.api.springapirest.dto;

import com.api.springapirest.enums.EmployeeRoleEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO extends UserDTO {

    @Schema(description = "Prénom de l'employé", required = true)
    @JsonProperty("first_name")
    protected String firstName;

    @Schema(description = "Nom de famille de l'employé", required = true)
    @JsonProperty("last_name")
    protected String lastName;

    @Schema(description = "Salaire de l'employé", required = true)
    @JsonProperty("salary")
    protected Double salary;

    @Schema(description = "L'employé est-il actif ?", required = true)
    @JsonProperty("active")
    protected Boolean active;

    @Schema(description = "Rôle de l'employé", example = "MANAGER", required = true)
    @JsonProperty("role")
    protected EmployeeRoleEnum role;

}
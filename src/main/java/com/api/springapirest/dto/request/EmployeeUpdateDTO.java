package com.api.springapirest.dto.request;

import com.api.springapirest.dto.EmployeeDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Demande de mise à jour d'un employé")
public class EmployeeUpdateDTO extends EmployeeDTO {

    @Schema(description = "Mot de passe de l'employé", example = "SecureP@ss123", required = true)
    private String password;
}

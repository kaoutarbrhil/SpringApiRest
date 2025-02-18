package com.api.springapirest.dto.request;

import com.api.springapirest.dto.EmployeeDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Demande de création d'un employé")
public class EmployeeCreateDTO extends EmployeeDTO {

    @Schema(description = "Mot de passe de l'employé", example = "SecureP@ss123", required = true)
    private String password;

    @Schema(description = "Image de l'employé", required = false)
    private MultipartFile image;

}

package com.api.springapirest.dto.response;

import com.api.springapirest.dto.EmployeeDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Réponse d'un employé")
public class EmployeeResponseDTO extends EmployeeDTO {

    @Schema(description = "Id de l'employé", required = true)
    private Long id;

    @Schema(description = "Date de creation", example = "MANAGER", defaultValue = "Date courante")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date date;

    @Schema(description = "URL de l'image de l'employé")
    @JsonProperty("image_url")
    protected String imageUrl;
}

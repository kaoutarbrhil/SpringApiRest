package com.api.springapirest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Nom d'utilisateur", required = true)
    @JsonProperty("user_name")
    protected String userName;

    @Schema(description = "Adresse email de l'utilisateur", required = true)
    @JsonProperty("email")
    protected String email;
}

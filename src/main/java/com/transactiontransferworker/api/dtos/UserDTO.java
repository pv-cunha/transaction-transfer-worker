package com.transactiontransferworker.api.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.transactiontransferworker.repository.enuns.UserType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String document;

    @Email
    private String email;

    @NotBlank
    private String password;

    private BigDecimal balance;

    private UserType userType;

}

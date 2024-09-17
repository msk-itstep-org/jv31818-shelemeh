package org.itstep.msk.app.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.itstep.msk.app.validation.Password;

import javax.validation.constraints.NotNull;

/**
 * DTO model for auth request customer dto model
 */
@Data
@AllArgsConstructor
@Builder
public class AuthRequestCustomerDto {

    @NotNull
    private String customerName;
    @NotNull
    @Password
    private String customerPassword;


}


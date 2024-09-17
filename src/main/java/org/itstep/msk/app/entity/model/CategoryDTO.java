package org.itstep.msk.app.entity.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class CategoryDTO {

    private Integer id;
    @NotBlank
    private String name;

    private String image;

}

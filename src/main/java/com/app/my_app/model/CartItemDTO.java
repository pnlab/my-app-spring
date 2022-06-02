package com.app.my_app.model;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CartItemDTO {

    private Long id;

    @NotNull
    private Integer quantity;

    private Long user;

    private Long product;

}

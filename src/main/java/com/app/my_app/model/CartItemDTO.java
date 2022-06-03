package com.app.my_app.model;

import javax.validation.constraints.NotNull;

import com.app.my_app.domain.Product;
import com.app.my_app.domain.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CartItemDTO {

    private Long id;

    @NotNull
    private Integer quantity;


    private User user;

    private Product product;

}

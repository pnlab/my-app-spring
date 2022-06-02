package com.app.my_app.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String description;

    @Size(max = 255)
    private String image;

    @NotNull
    private Long price;

    @NotNull
    private Long quantity;

    @Size(max = 255)
    private String unit;

    private Long orderItem;

    private Long category;

}

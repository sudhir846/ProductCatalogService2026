package org.example.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private CategoryDto category;
}

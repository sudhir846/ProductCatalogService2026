package org.example.productcatalogservice.models;

import lombok.Getter;
import lombok.Setter;
import org.example.productcatalogservice.dtos.CategoryDto;
import org.example.productcatalogservice.dtos.ProductDto;

@Getter
@Setter
public class Product extends BaseModel {
    private String name;

    private String description;

    private Double price;

    private String imageUrl;

    private Category category;

    public ProductDto toProductDto() {
        ProductDto productDto = new ProductDto();

        productDto.setName(this.name);
        productDto.setDescription(this.description);
        productDto.setPrice(this.price);
        productDto.setImageUrl(this.imageUrl);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(this.category.getName());
        productDto.setCategory(categoryDto);

        return productDto;
    }
}


package org.example.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.productcatalogservice.models.Category;
import org.example.productcatalogservice.models.Product;

@Getter
@Setter
public class ProductDto {
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private CategoryDto category;

    public Product toProduct() {
        Product product = new Product();

        product.setName(this.name);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setImageUrl(this.imageUrl);

        if(this.category != null) {
            Category category = new Category();
            category.setName(this.category.getName());
            category.setDescription(this.category.getDescription());
            product.setCategory(category);
        }

        return product;
    }
}

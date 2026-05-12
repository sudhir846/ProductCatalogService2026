package org.example.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.productcatalogservice.models.Category;
import org.example.productcatalogservice.models.Product;

@Getter
@Setter
public class FakestoreProductDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;
    
    
    public Product toProduct() {

        /*
         This method is used to convert the FakestoreProductDto to Product.
        */

        Product product = new Product();

        product.setName(this.title);
        product.setPrice(this.price);
        product.setDescription(this.description);
        product.setImageUrl(this.image);

        Category category = new Category();
        category.setName(this.category);
        product.setCategory(category);
        
        return product;
    }
}

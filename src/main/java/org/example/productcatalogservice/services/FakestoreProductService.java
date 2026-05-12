package org.example.productcatalogservice.services;

import org.example.productcatalogservice.dtos.FakestoreProductDto;
import org.example.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakestoreProductService implements IProductService{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        ResponseEntity<FakestoreProductDto[]> repsonse = restTemplate
                .getForEntity(
                        "https://fakestoreapi.com/products",
                        FakestoreProductDto[].class
                );

        if(repsonse.getBody() == null) {
            return null;
        }

        FakestoreProductDto[] fakestoreProductDtos = repsonse.getBody();

        // To return the List of product, need to convert the list of FakestoreDto to list of Product.
        for(FakestoreProductDto productDto : fakestoreProductDtos) {
            products.add(productDto.toProduct());
        }

        return products;
    }

    @Override
    public Product getProductById(Long productId) {

         ResponseEntity<FakestoreProductDto> response = restTemplate.
                 getForEntity (
                        "https://fakestoreapi.com/products/" + productId,
                        FakestoreProductDto.class
                );

         if(response.getBody() == null) {
             return null;
         }
         else {
             // To return the product, need to convert the FakestoreProductDto to Product.
             return response.getBody().toProduct();
         }
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }
}

package org.example.productcatalogservice.controllers;

import org.example.productcatalogservice.dtos.ProductDto;
import org.example.productcatalogservice.models.Product;
import org.example.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping()
    public List<ProductDto> getAllProducts() {

        List<Product> products = productService.getAllProducts();

        List<ProductDto> productDtos = new ArrayList<>();

        for(Product product : products) {
            productDtos.add(product.toProductDto());
        }

        return productDtos;
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable("id") Long productId) {
        Product product = productService.getProductById(productId);

        return product.toProductDto();
    }


    public ProductDto createProduct(ProductDto product) {
        return null;
    }

    @PutMapping("{id}")
    public ProductDto replaceProduct(@PathVariable("id") Long productId, @RequestBody ProductDto product) {
        return null;
    }
}

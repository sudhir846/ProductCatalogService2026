package org.example.productcatalogservice.controllers;

import org.example.productcatalogservice.dtos.ProductDto;
import org.example.productcatalogservice.exceptions.ProductNotExistException;
import org.example.productcatalogservice.models.Product;
import org.example.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) throws ProductNotExistException {
        Product product = productService.getProductById(productId);

        return ResponseEntity.ok(product.toProductDto());
    }


    public ProductDto createProduct(ProductDto product) {
        return null;
    }

    @PutMapping("{id}")
    public ProductDto replaceProduct(@PathVariable("id") Long productId,
                                     @RequestBody ProductDto newProductDto) {

        // 1. Convert the ProductDto to Product.
        Product product = newProductDto.toProduct();
        product.setId(productId);

        // 2. Call the service to replace the product.
        Product response = productService.replaceProduct(productId, product);

        // 3. Convert the Product to ProductDto and return it.
        return response.toProductDto();
    }


    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<String> handleProductNotExistException(ProductNotExistException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}

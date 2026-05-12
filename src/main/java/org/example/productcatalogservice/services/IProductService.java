package org.example.productcatalogservice.services;

import org.example.productcatalogservice.exceptions.ProductNotExistException;
import org.example.productcatalogservice.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    Product getProductById(Long productId) throws ProductNotExistException;
    Product createProduct(Product product);
    Product replaceProduct(Long id, Product product);
}

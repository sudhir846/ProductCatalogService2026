package org.example.productcatalogservice.services;

import org.example.productcatalogservice.clients.FakestoreAPIClient;
import org.example.productcatalogservice.dtos.FakestoreProductDto;
import org.example.productcatalogservice.exceptions.ProductNotExistException;
import org.example.productcatalogservice.models.Product;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakestoreProductService implements IProductService {

    /*
    * These are not meant to be in this service as they are violating the SRP.
    * So I am just keeping them here for reference in comments and moving it to the FakestoreApiClient.

    public <T> ResponseEntity<T> putForEntity(String url, @Nullable Object request,
                                               Class<T> responseType, @Nullable Object... uriVariables) throws RestClientException {

        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }

    @Autowired
    private RestTemplate restTemplate;

     */

    @Autowired
    private FakestoreAPIClient fakestoreAPIClient;

    @Override
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        ResponseEntity<FakestoreProductDto[]> repsonse = fakestoreAPIClient.requestForEntity(
                        HttpMethod.GET,
                        null,
                        "https://fakestoreapi.com/products",
                        FakestoreProductDto[].class
                );

        if(repsonse.getBody() != null && repsonse.getStatusCode().equals(HttpStatusCode.valueOf(200))) {

            for(FakestoreProductDto fakestoreProductDto : repsonse.getBody()) {
                products.add(fakestoreProductDto.toProduct());
            }
            return products;
        }
        return null;
    }

    @Override
    public Product getProductById(Long productId) throws ProductNotExistException {

         ResponseEntity<FakestoreProductDto> response = fakestoreAPIClient.requestForEntity(
                        HttpMethod.GET,
                        "https://fakestoreapi.com/products/" + productId,
                        null,
                        FakestoreProductDto.class
                );

         if(response.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
             if(response.getBody() != null) {
                 return response.getBody().toProduct();
             }
             else {
                 throw new ProductNotExistException("Product with id " + productId + " does not exist." );
             }
         }
         else {
             throw new ProductNotExistException("Product with id " + productId + " does not exist.");
         }

    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product newProduct) {
        ResponseEntity<FakestoreProductDto> response = fakestoreAPIClient.requestForEntity(
                        HttpMethod.PUT,
                        "https://fakestoreapi.com/products/{id}",
                        newProduct.toFaketoreProductDto(),
                        FakestoreProductDto.class,
                        id
                );

        if(response.getBody() != null && response.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            return response.getBody().toProduct();
        }

        return null;
    }
}

package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.ReviewRepository.ProductRepository;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.service.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Spring REST controller for working with product entity.
 */
@RestController
@RequestMapping("/products")
public class ProductsController {

    // TODO: Wire JPA repositories here
    @Autowired
    private ProductRepository productRepository;

    /**
     * Creates a product.
     *
     * 1. Accept product as argument. Use {@link RequestBody} annotation.
     * 2. Save product.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@Valid @RequestBody Product product) {

//        if (product.getId() != null) {
//            return productRepository.findById(product.getId())
//                    .map(productToUpdate -> {
//                        productToUpdate.setName(product.getName());
//                        return productRepository.save(productToUpdate);
//                    }).orElseThrow(ProductNotFoundException::new);
//        }
        Product newProduct = productRepository.save(product);
    }

    /**
     * Finds a product by id.
     *
     * @param id The id of the product.
     * @return The product if found, or a 404 not found.
     */
    @RequestMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {

        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    /**
     * Lists all products.
     *
     * @return The list of products.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<?> listProducts() {
        //List<Product> list = (List<Product>) productRepository.findAll();
        List<Product> list = productRepository.getAllProducts();
        return list;
    }
}
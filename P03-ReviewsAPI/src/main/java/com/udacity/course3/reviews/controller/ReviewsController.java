package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.ReviewRepository.ProductRepository;
import com.udacity.course3.reviews.ReviewRepository.ReviewsRepository;
import com.udacity.course3.reviews.entity.Comments;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Reviews;
import com.udacity.course3.reviews.service.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    // TODO: Wire JPA repositories here

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewsRepository reviewsRepository;

        /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */

    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> createReviewForProduct(@RequestBody Comments comments,
                                            @PathVariable("productId") Integer productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        List<Reviews> curReviews = product.getReviews();
        Reviews reviews = new Reviews();
        reviews.setProdid(productId);

        curReviews.add(reviews);
        product.setReviews(curReviews);

        reviews = reviewsRepository.save(reviews);

        comments.setReviewid(reviews.getReviewid());
        comments.setComment(comments.getComment());
        reviews.setComments(comments);

        reviewsRepository.save(reviews);

        Product updatedProduct = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<?>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        List<Reviews> reviews = product.getReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
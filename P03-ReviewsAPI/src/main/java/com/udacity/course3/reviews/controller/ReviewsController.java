package com.udacity.course3.reviews.controller;

//import com.udacity.course3.reviews.ReviewRepository.CommentsRepository;
import com.udacity.course3.reviews.ReviewRepository.ProductRepository;
import com.udacity.course3.reviews.ReviewRepository.ReviewsRepository;
//import com.udacity.course3.reviews.entity.Comments;
import com.udacity.course3.reviews.entity.Comments;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Reviews;
import com.udacity.course3.reviews.service.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.UUID;
import java.util.Random;

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
//    @Autowired
//    private CommentsRepository commentsRepository;

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
    public ResponseEntity<?> createReviewForProduct(@PathVariable("productId") Integer productId) {

        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();

        System.err.println("Random UUID String = " + randomUUIDString);
        System.err.println("UUID version       = " + uuid.version());
        System.err.println("UUID variant       = " + uuid.variant());

        Random rand = new Random();
        int rand_int1 = rand.nextInt(Integer.MAX_VALUE);
        System.err.println("rand_int1= " + rand_int1);
        System.err.println("integer max= " + Integer.MAX_VALUE);

        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        System.err.println(product.getProdid());

        List<Reviews> curReviews = product.getReviews();

        System.err.println(curReviews.size());

        Reviews reviews = new Reviews();
        reviews.setProdid(productId);

        curReviews.add(reviews);
        product.setReviews(curReviews);

        reviews = reviewsRepository.save(reviews);

        Comments comments = new Comments();
        comments.setReviewid(reviews.getReviewid());
        comments.setComment("This is a test");
        reviews.setComments(comments);

        reviewsRepository.save(reviews);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<?>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
        throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
    }
}
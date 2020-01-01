package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.ReviewRepository.CommentsRepository;
import com.udacity.course3.reviews.ReviewRepository.ProductRepository;
import com.udacity.course3.reviews.ReviewRepository.ReviewsRepository;
import com.udacity.course3.reviews.entity.Comments;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Reviews;
import com.udacity.course3.reviews.service.ProductNotFoundException;
import com.udacity.course3.reviews.service.ReviewsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    // TODO: Wire needed JPA repositories here
    @Autowired
    private ReviewsRepository reviewsRepository;
    @Autowired
    private CommentsRepository commentsRepository;

    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<?> createCommentForReview(@RequestBody Comments comments, @PathVariable("reviewId") Integer reviewId) {

        Reviews reviews = reviewsRepository.findById(reviewId)
                .orElseThrow(ReviewsNotFoundException::new);

        //TODO change ReviewsNotFoundException to CommentsNotFoundException
        Comments c = commentsRepository.findById(reviewId)
                .orElseThrow(ReviewsNotFoundException::new);

        c.setComment(comments.getComment());
        reviews.setComments(c);

        reviewsRepository.save(reviews);
        return new ResponseEntity<Comments>(c, HttpStatus.OK);
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public List<?> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
        List<Comments> list = commentsRepository.getCommentsbyReviewId(reviewId);
        return list;
    }
}
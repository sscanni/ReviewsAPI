package com.udacity.course3.reviews.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Review not found")
public class ReviewsNotFoundException extends RuntimeException {

    public ReviewsNotFoundException() {
    }

    public ReviewsNotFoundException(String message) {
        super(message);
    }
}
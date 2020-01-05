package com.udacity.course3.reviews.ReviewRepository;

import com.udacity.course3.reviews.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<Reviews, Integer> {

}
package com.udacity.course3.reviews.ReviewRepository;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Integer> {

    @Query("select r from Reviews r where prodid=:prodid")
    public Reviews getReviewsbyProdId(int prodid);
}
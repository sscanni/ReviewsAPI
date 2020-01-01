package com.udacity.course3.reviews.ReviewRepository;

import java.util.List;

import com.udacity.course3.reviews.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select p from Product p order by p.name asc")
    List<Product> getAllProducts();

    @Query("select p from Product p where id=:id")
    public Product getProductNamebyId(int id);

}
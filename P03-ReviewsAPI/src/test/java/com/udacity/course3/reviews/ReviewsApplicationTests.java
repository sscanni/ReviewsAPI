package com.udacity.course3.reviews;

import com.udacity.course3.reviews.ReviewRepository.CommentsRepository;
import com.udacity.course3.reviews.ReviewRepository.ProductRepository;
import com.udacity.course3.reviews.ReviewRepository.ReviewsRepository;
import com.udacity.course3.reviews.entity.Comments;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Reviews;
import com.udacity.course3.reviews.service.ProductNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ReviewsApplicationTests {

	String reviewComment1 = "This is a new review inserted from sql.";
	String reviewComment2 = "This is a test review and comment";

	@Autowired private ProductRepository productRepository;
	@Autowired private ReviewsRepository reviewsRepository;
	@Autowired private CommentsRepository commentsRepository;

	@Test
	public void injectedComponentsAreNotNull(){
		assertThat(productRepository).isNotNull();
		assertThat(reviewsRepository).isNotNull();
		assertThat(commentsRepository).isNotNull();
	}
	@Test
	public void testProduct(){
		//Expect 5 products
		List<Product> prodList = productRepository.findAll();
		assertThat(prodList.size()).isEqualTo(5);

		//add a new product
		Product addProd = new Product();
		addProd.setName("New Product");
		productRepository.save(addProd);

		//we should now have 6 products
		assertThat(productRepository.findAll().size()).isEqualTo(6);

		//verify that we have expected products. Product 1 is the only one with a review.
		Product testProd = new Product();
		testProd = productRepository.findById(1).orElseThrow(ProductNotFoundException::new);
		assertThat(testProd.getName()).isEqualTo("Dell XPS Desktop Computer");
		assertThat(testProd.getReviews()).isNotEmpty();
		testProd = productRepository.findById(2).orElseThrow(ProductNotFoundException::new);
		assertThat(testProd.getName()).isEqualTo("Dell XPS 13 Laptop Computer");
		assertThat(testProd.getReviews()).isEmpty();
		testProd = productRepository.findById(3).orElseThrow(ProductNotFoundException::new);
		assertThat(testProd.getName()).isEqualTo("Lenovo Laptop Computer");
		assertThat(testProd.getReviews()).isEmpty();
		testProd = productRepository.findById(4).orElseThrow(ProductNotFoundException::new);
		assertThat(testProd.getName()).isEqualTo("Dell 27 inch Monitor");
		assertThat(testProd.getReviews()).isEmpty();
		testProd = productRepository.findById(5).orElseThrow(ProductNotFoundException::new);
		assertThat(testProd.getName()).isEqualTo("HP Laptop");
		assertThat(testProd.getReviews()).isEmpty();
	}
	@Test
	public void testReviews() {

		Product product = productRepository.findById(1)
				.orElseThrow(ProductNotFoundException::new);

		List<Reviews> curReviews = product.getReviews();
		Reviews reviews = new Reviews();
		reviews.setProdid(1);
		curReviews.add(reviews);
		product.setReviews(curReviews);

		reviews = reviewsRepository.save(reviews);

		Comments comments = new Comments();
		comments.setReviewid(reviews.getReviewid());
		comments.setComment(reviewComment2);
		reviews.setComments(comments);

		reviewsRepository.save(reviews);

		Product updatedProduct = productRepository.findById(1)
		.orElseThrow(ProductNotFoundException::new);

		//Expect 2 reviews for Product #1
		assertThat(updatedProduct.getReviews().size()).isEqualTo(2);
		assertThat(updatedProduct.getReviews().get(0).getComments().getComment()).isEqualTo(reviewComment1);
		assertThat(updatedProduct.getReviews().get(1).getComments().getComment()).isEqualTo(reviewComment2);

	}
	@Test
	public void testComments() {
		List<Comments> comments = commentsRepository.getCommentsbyReviewId(1);
		assertThat(comments.size()).isEqualTo(1);
		assertThat(comments.get(0).getComment()).isEqualTo(reviewComment1);
	}

}
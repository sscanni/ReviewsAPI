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
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ReviewsApplicationTests {

//	@Autowired
//	private TestRestTemplate restTemplate;
//
//	@LocalServerPort
//	private int port;
//
//	private String getRootUrl() {
//		return "http://localhost:" + port;
//	}

	@Autowired private DataSource dataSource;
	@Autowired private JdbcTemplate jdbcTemplate;
	@Autowired private EntityManager entityManager;
	@Autowired private TestEntityManager testEntityManager;
	@Autowired private ProductRepository productRepository;
	@Autowired private ReviewsRepository reviewsRepository;
	@Autowired private CommentsRepository commentsRepository;

	@Test
	public void contextLoads() {
	}
	@Test
	public void injectedComponentsAreNotNull(){
		assertThat(dataSource).isNotNull();
		assertThat(jdbcTemplate).isNotNull();
		assertThat(entityManager).isNotNull();
		assertThat(testEntityManager).isNotNull();
		assertThat(productRepository).isNotNull();
		assertThat(reviewsRepository).isNotNull();
	}
	@Test
	public void testProduct(){
		List<Product> prodList = productRepository.findAll();
		assertThat(prodList.size()).isEqualTo(5);

		Product addProd = new Product();
		addProd.setName("New Product");
		productRepository.save(addProd);

		assertThat(productRepository.findAll().size()).isEqualTo(6);

		Product testProd = new Product();
		testProd = productRepository.findById(1).orElseThrow(ProductNotFoundException::new);
		assertThat(testProd.getName()).isEqualTo("Dell XPS Desktop Computer");
		assertThat(testProd.getReviews()).isEmpty();
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
	public void getComments_byReviewId() {
		List<Comments> list = commentsRepository.getCommentsbyReviewId(1);
		assertThat(list.size()).isEqualTo(1);
	}
//	@Test
//	public void saveReviews() {
//		Product product = productRepository.findById(productId)
//				.orElseThrow(ProductNotFoundException::new);
//
//		List<Reviews> curReviews = product.getReviews();
//		Reviews reviews = new Reviews();
//		reviews.setProdid(productId);
//
//		curReviews.add(reviews);
//		product.setReviews(curReviews);
//
//		reviews = reviewsRepository.save(reviews);
//
//		comments.setReviewid(reviews.getReviewid());
//		comments.setComment(comments.getComment());
//		reviews.setComments(comments);
//
//		reviewsRepository.save(reviews);
//
//		Product updatedProduct = productRepository.findById(productId)
//				.orElseThrow(ProductNotFoundException::new);
//	}


}
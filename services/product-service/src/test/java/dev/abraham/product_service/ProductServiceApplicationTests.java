	package dev.abraham.product_service;

	import com.fasterxml.jackson.databind.ObjectMapper;
	import dev.abraham.product_service.request.ProductRequest;
	import org.junit.jupiter.api.Test;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
	import org.springframework.boot.test.context.SpringBootTest;
	import org.springframework.http.MediaType;
	import org.springframework.http.ResponseEntity;
	import org.springframework.test.context.DynamicPropertyRegistry;
	import org.springframework.test.context.DynamicPropertySource;
	import org.springframework.test.web.servlet.MockMvc;
	import org.springframework.test.web.servlet.ResultMatcher;
	import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
	import org.testcontainers.containers.MongoDBContainer;
	import org.testcontainers.junit.jupiter.Container;
	import org.testcontainers.junit.jupiter.Testcontainers;

	import java.math.BigDecimal;

	@SpringBootTest
	@Testcontainers
	@AutoConfigureMockMvc
	class ProductServiceApplicationTests {
		@Container
		static MongoDBContainer mongoDBContainer=new MongoDBContainer("mongo:4.4.2");
		@Autowired
		private MockMvc mockMvc;

		@Autowired
		private ObjectMapper objectMapper;

		@DynamicPropertySource
		static void setProperties(DynamicPropertyRegistry registry) {
			registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
		}

		@Test
		void contextLoads() {
		}

		@Test
		void createProduct() throws Exception {
			ProductRequest request	= new ProductRequest();
			String s=objectMapper.writeValueAsString(request);
			mockMvc.perform(MockMvcRequestBuilders.post("api/v1/product/create")
					.contentType(MediaType.APPLICATION_JSON)
					.content(s))
					.andExpect((ResultMatcher) ResponseEntity.status(201));
		}

		private ProductRequest getProductRequest() {
			return ProductRequest.builder()
					.name("Samsung S23")
					.description("Samsung S23")
					.price(new BigDecimal(1200))
					.build();
		}
	}
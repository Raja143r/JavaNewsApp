package com.example.newsapp.article;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ArticleApiIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void createsArticleAndFindsItBySearch() throws Exception {
		mockMvc.perform(post("/api/articles")
					.contentType(MediaType.APPLICATION_JSON)
					.content("""
							{"title":"Test headline","summary":"A test summary","content":"Full story text",
							"author":"Taylor News","category":"Technology","imageUrl":null}
							"""))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNumber());

		mockMvc.perform(get("/api/articles").param("search", "test headline"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content[0].title").value("Test headline"));
	}

	@Test
	void rejectsArticleWithoutRequiredFields() throws Exception {
		mockMvc.perform(post("/api/articles")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"title\":\"\"}"))
				.andExpect(status().isBadRequest());
	}
}
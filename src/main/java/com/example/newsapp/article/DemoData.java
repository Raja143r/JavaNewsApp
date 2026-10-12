package com.example.newsapp.article;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoData {

	@Bean
	CommandLineRunner seedArticles(ArticleRepository repository) {
		return args -> {
			if (repository.count() == 0) {
				repository.save(new Article(new ArticleRequest(
						"City opens new riverside park", "A former industrial site is now a public green space.",
						"The new riverside park opened to the public on Saturday, adding walking paths, native gardens, and public seating along the waterfront.",
						"Morgan Lee", "Local", null)));
				repository.save(new Article(new ArticleRequest(
						"Researchers announce battery breakthrough", "A new design could make grid storage more affordable.",
						"Researchers have demonstrated a durable battery design using widely available materials. The team says more testing is needed before commercial use.",
						"Sam Rivera", "Science", null)));
				repository.save(new Article(new ArticleRequest(
						"Community library extends evening hours", "The schedule change follows a successful pilot program.",
						"The central library will stay open later on weekdays after residents used the extended hours during a three-month pilot.",
						"Avery Chen", "Community", null)));
			}
		};
	}
}
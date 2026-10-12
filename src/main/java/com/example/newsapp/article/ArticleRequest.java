package com.example.newsapp.article;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ArticleRequest(
		@NotBlank @Size(max = 180) String title,
		@NotBlank @Size(max = 500) String summary,
		@NotBlank String content,
		@NotBlank @Size(max = 120) String author,
		@NotBlank @Size(max = 80) String category,
		@Size(max = 2048) String imageUrl) {
}
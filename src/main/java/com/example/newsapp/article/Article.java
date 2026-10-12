package com.example.newsapp.article;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "articles")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 180)
	private String title;

	@Column(nullable = false, length = 500)
	private String summary;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;

	@Column(nullable = false, length = 120)
	private String author;

	@Column(nullable = false, length = 80)
	private String category;

	@Column(length = 2048)
	private String imageUrl;

	@Column(nullable = false, updatable = false)
	private Instant publishedAt;

	protected Article() {
	}

	public Article(ArticleRequest request) {
		updateFrom(request);
	}

	public void updateFrom(ArticleRequest request) {
		this.title = request.title();
		this.summary = request.summary();
		this.content = request.content();
		this.author = request.author();
		this.category = request.category();
		this.imageUrl = request.imageUrl();
	}

	@PrePersist
	void setPublishedAt() {
		if (publishedAt == null) {
			publishedAt = Instant.now();
		}
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getSummary() {
		return summary;
	}

	public String getContent() {
		return content;
	}

	public String getAuthor() {
		return author;
	}

	public String getCategory() {
		return category;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Instant getPublishedAt() {
		return publishedAt;
	}
}
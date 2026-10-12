package com.example.newsapp.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class ArticleService {

	private final ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@Transactional(readOnly = true)
	public Page<Article> findAll(String search, Pageable pageable) {
		if (search == null || search.isBlank()) {
			return articleRepository.findAll(pageable);
		}
		return articleRepository.search(search.trim(), pageable);
	}

	@Transactional(readOnly = true)
	public Article findById(Long id) {
		return articleRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found"));
	}

	public Article create(ArticleRequest request) {
		return articleRepository.save(new Article(request));
	}

	public Article update(Long id, ArticleRequest request) {
		Article article = findById(id);
		article.updateFrom(request);
		return articleRepository.save(article);
	}

	public void delete(Long id) {
		Article article = findById(id);
		articleRepository.delete(article);
	}
}
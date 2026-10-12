package com.example.newsapp.article;

import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

	private final ArticleService articleService;

	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@GetMapping
	public Page<Article> findAll(
			@RequestParam(required = false) String search,
			@PageableDefault(size = 10, sort = "publishedAt", direction = org.springframework.data.domain.Sort.Direction.DESC)
					Pageable pageable) {
		return articleService.findAll(search, pageable);
	}

	@GetMapping("/{id}")
	public Article findById(@PathVariable Long id) {
		return articleService.findById(id);
	}

	@PostMapping
	public ResponseEntity<Article> create(@Valid @RequestBody ArticleRequest request) {
		Article article = articleService.create(request);
		return ResponseEntity.created(URI.create("/api/articles/" + article.getId())).body(article);
	}

	@PutMapping("/{id}")
	public Article update(@PathVariable Long id, @Valid @RequestBody ArticleRequest request) {
		return articleService.update(id, request);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		articleService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
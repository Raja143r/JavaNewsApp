package com.example.newsapp.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long> {

	@Query("""
			select article from Article article
			where lower(article.title) like lower(concat('%', :query, '%'))
			   or lower(article.summary) like lower(concat('%', :query, '%'))
			   or lower(article.category) like lower(concat('%', :query, '%'))
			""")
	Page<Article> search(@Param("query") String query, Pageable pageable);
}
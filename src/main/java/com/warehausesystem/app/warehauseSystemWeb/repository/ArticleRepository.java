package com.warehausesystem.app.warehauseSystemWeb.repository;

import com.warehausesystem.app.warehauseSystemWeb.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}

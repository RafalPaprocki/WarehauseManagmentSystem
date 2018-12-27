package com.warehausesystem.app.warehauseSystemWeb.repository;

import com.warehausesystem.app.warehauseSystemWeb.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("Select a from Article a " +
            "where a.name like CONCAT('%',:name,'%') " +
            "and a.color like CONCAT('%',:color, '%')" +
            "and a.kind like CONCAT('%', :kind, '%')" +
            "and a.id like CONCAT('%', :id, '%')")
    List<Article> findArticleFiltered(@Param("name") String name,
                                      @Param("color") String color,
                                      @Param("kind") String kind,
                                      @Param("id") String id
                                      );
    @Query("select  a from Article a where a.id like CONCAT('%',:id, '%')")
    List<Article> findByIdLike(@Param("id") String id);

    List<Article> findByNameEndingWithAndColorEndingWith(String name, String color);
}

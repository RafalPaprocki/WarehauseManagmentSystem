package com.warehausesystem.app.warehauseSystemWeb.repository;

import com.warehausesystem.app.warehauseSystemWeb.model.Article;
import com.warehausesystem.app.warehauseSystemWeb.model.Compartment;
import com.warehausesystem.app.warehauseSystemWeb.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompartmentRepository extends JpaRepository<Compartment,Long> {
    List<Compartment> findByArticleId(Long articleId);
    List<Compartment> findBySectorId(Long sectorId);
    List<Compartment> findBySectorIdAndNumber(Long sectorId, Long number);
    List<Compartment> findByArticleQuantity(Long articleQuantity);
   // List<Compartment> findBySectorIdAndArticleIdAnd();
    @Query("Select c from Compartment c " +
            "where c.sector = :sector " +
            "and (c.article = :article or c.article = null ) and c.articleQuantity < :number ")
    List<Compartment> findBySectorIdAndArticleIdAnd(@Param("sector")Sector sectorId,
                                                    @Param("article")Article articleId,
                                                    @Param("number")Long number);

}

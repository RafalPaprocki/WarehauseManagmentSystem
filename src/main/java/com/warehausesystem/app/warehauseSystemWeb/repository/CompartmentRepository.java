package com.warehausesystem.app.warehauseSystemWeb.repository;

import com.warehausesystem.app.warehauseSystemWeb.model.Article;
import com.warehausesystem.app.warehauseSystemWeb.model.Compartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompartmentRepository extends JpaRepository<Compartment,Long> {
    List<Compartment> findByArticleId(Long articleId);
    List<Compartment> findBySectorId(Long sectorId);
    List<Compartment> findBySectorIdAndNumber(Long sectorId, Long number);
}

package com.plataforma.prueba.infrastructure.persistence.repository;

import java.time.LocalDateTime;

import com.plataforma.prueba.infrastructure.persistence.entity.PriceJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceH2Repository extends CrudRepository<PriceJpaEntity, Long> {

    @Query(value = """
    SELECT p.*
    FROM prices p
    JOIN brands b ON p.brand_id = b.id
    WHERE b.id = ?1 
      AND p.product_id = ?2 
      AND ?3 BETWEEN p.start_date AND p.end_date
    ORDER BY p.priority DESC
    LIMIT 1
    """, nativeQuery = true)
    Optional<PriceJpaEntity> findPriceByBrandProductDateAndPriorityDESC(Long brandId, Long productId, LocalDateTime appDate);

}
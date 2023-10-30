package com.betweentech.domain;


import com.betweentech.application.projections.PricesProjection;
import com.betweentech.domain.model.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PricesRepository extends JpaRepository<Prices, Integer> {
    @Query(value = "SELECT brand_id, start_date, end_date, price_list, product_id, priority, price, curr " +
            "FROM Prices " +
            "WHERE brand_id = :brandId and start_date= :applicationDate and product_id= :productId", nativeQuery = true)
    List<PricesProjection> findPricesByBrandIdStartDateProductId(@Param("brandId") Long brandId, @Param("applicationDate") Date applicationDate, @Param("productId") Long productId);

}

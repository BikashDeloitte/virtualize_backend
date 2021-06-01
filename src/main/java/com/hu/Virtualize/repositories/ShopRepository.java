package com.hu.Virtualize.repositories;

import com.hu.Virtualize.entities.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<ShopEntity, Long> {

    ShopEntity deleteByShopId(Long shopId);

    List<ShopEntity> findByShopName(String productType);
}

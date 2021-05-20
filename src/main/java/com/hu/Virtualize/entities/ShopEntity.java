package com.hu.Virtualize.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ShopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopId;

    private String shopName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn( name = "shopId", referencedColumnName = "shopId")
    List<ProductEntity> shopProducts = new ArrayList<>();
}

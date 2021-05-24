package com.hu.Virtualize.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;
    private Integer productPrice;
    private String productType;
    private String productSize;
    private String brandName;

    @Lob
    private Byte[] productImage;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    Set<DiscountEntity> productDiscounts = new HashSet<>();

    @ManyToMany(targetEntity = UserEntity.class, cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<UserEntity> users = new HashSet<>();

    public ProductEntity(String productName, Integer productPrice, String productType, String productSize) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productType = productType;
        this.productSize = productSize;
    }
}

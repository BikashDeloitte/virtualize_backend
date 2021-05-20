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
    private Byte[] productImage;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn( name = "productId", referencedColumnName = "productId")
    List<DiscountEntity> productDiscounts = new ArrayList<>();
}

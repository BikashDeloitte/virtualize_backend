package com.hu.Virtualize.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@RequiredArgsConstructor
@Entity
public class ShopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopId;

    @NonNull
    private String shopName;
    private String shopLocation;
    private String shopDescription;

    @Lob
    private Byte[] shopImage;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "shopId", referencedColumnName = "shopId")
    Set<ProductEntity> shopProducts = new HashSet<>();

    public ShopEntity(@NonNull String shopName, String shopLocation, String shopDescription) {
        this.shopName = shopName;
        this.shopLocation = shopLocation;
        this.shopDescription = shopDescription;
    }

    public ShopEntity(@NonNull String shopName, Byte[] shopImage) {
        this.shopName = shopName;
        this.shopImage = shopImage;
    }
}

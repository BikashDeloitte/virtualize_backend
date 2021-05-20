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
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    private String adminName;
    private String adminEmail;
    private String adminPassword;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn( name = "adminId", referencedColumnName = "adminId")
    List<ShopEntity> adminShops = new ArrayList<>();
}

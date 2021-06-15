package com.hu.Virtualize.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DiscountEntity")
public class DiscountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discountId;

    @NonNull
    private String discountName;

    @NonNull
    private Integer discountPercentage;

    @NonNull
    private Date endDate;

    private String discountDescription;

    public DiscountEntity(@NonNull String discountName, @NonNull Integer discountPercentage, String date) {
        this.discountName = discountName;
        this.discountPercentage = discountPercentage;
        this.endDate = Date.valueOf(date);
    }
}

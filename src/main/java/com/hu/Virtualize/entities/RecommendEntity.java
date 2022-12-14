package com.hu.Virtualize.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "RecommendEntity")
public class RecommendEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendId;

    @NonNull
    @Lob
    private Byte[] recommendImage;

    @NonNull
    private String categoryType;
    private String description;

    private Date endDate;

    public RecommendEntity(@NonNull String categoryType, Date endDate) {
        this.categoryType = categoryType;
        this.endDate = endDate;
    }

    public RecommendEntity(@NonNull String categoryType, String endDate, String description) {
        this.categoryType = categoryType;
        this.endDate = Date.valueOf(endDate);
        this.description = description;
    }
}

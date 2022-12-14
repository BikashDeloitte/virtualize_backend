package com.hu.Virtualize.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "UserInterestEntity")
public class UserInterestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userInterestId;

    private String interest;

    public UserInterestEntity(String interest) {
        this.interest = interest;
    }
}

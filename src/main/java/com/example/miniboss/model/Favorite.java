package com.example.miniboss.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {
    private Integer id;
    private Integer userId;
    private String factId;
    private String text;
    private String createdBy;
    private Timestamp createdDatetime;
    private String updatedBy;
    private Timestamp updatedDatetime;
}

package com.example.miniboss.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class Status {
    private Integer sentCount;
    private Boolean verified;

    public String toString() {
        return "Status{" +
                "sentCount=" + sentCount +
                ", verified=" + verified +
                "}";
    }
}

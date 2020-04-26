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
public class Fact {
    private String _id;
    private Integer __v;
    private String user;
    private String text;
    private Timestamp updatedAt;
    private Timestamp createdAt;
    private Boolean deleted;
    private String source;
    private Boolean used;
    private String type;
    private Status status;

    public String toString() {
        return "Fact{" +
                "_id=" + _id +
                ", __v=" + __v +
                ", user=" + user +
                ", text='" + text + "\'" +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deleted=" + deleted +
                ", source=" + source +
                ", used=" + used +
                ", type=" + type +
                ", status=" + status +
                "}";
    }
}

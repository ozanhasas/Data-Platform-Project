package com.example.tezspringbe.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author meteh
 * @create 28.05.2022 22:35
 *//*
 * @created 05
 * @author meteh
 */
@Document
@Data
public class DatasetAnalysis {
    @Id
    private String id;
    private String related_data_set_id;
    private String sender;
    private String pathOfAnalysis;
    private String explanation;

    public DatasetAnalysis(String related_data_set_id, String sender, String pathOfAnalysis, String explanation) {
        this.related_data_set_id = related_data_set_id;
        this.sender = sender;
        this.pathOfAnalysis = pathOfAnalysis;
        this.explanation = explanation;
    }

    public DatasetAnalysis() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRelated_data_set_id() {
        return related_data_set_id;
    }

    public void setRelated_data_set_id(String related_data_set_id) {
        this.related_data_set_id = related_data_set_id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getPathOfAnalysis() {
        return pathOfAnalysis;
    }

    public void setPathOfAnalysis(String pathOfAnalysis) {
        this.pathOfAnalysis = pathOfAnalysis;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    @Override
    public String toString() {
        return "DatasetAnalysis{" +
                "related_data_set_id='" + related_data_set_id + '\'' +
                ", sender='" + sender + '\'' +
                ", pathOfAnalysis='" + pathOfAnalysis + '\'' +
                ", explanation='" + explanation + '\'' +
                '}';
    }

}


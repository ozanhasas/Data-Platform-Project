package com.example.tezspringbe.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author meteh
 * @create 28.05.2022 22:39
 *//*
 * @created 05
 * @author meteh
 */
@Data
@Document
public class AdminAnalysisRequest {
    @Id
    private String id;
    private String related_data_set_id;
    private String content;
    private String senderName;
    private String dataPathOfAnalysis;

    public void setRelated_data_set_id(String related_data_set_id) {
        this.related_data_set_id = related_data_set_id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setDataPathOfAnalysis(String dataPathOfAnalysis) {
        this.dataPathOfAnalysis = dataPathOfAnalysis;
    }

    public String getRelated_data_set_id() {
        return related_data_set_id;
    }

    public String getContent() {
        return content;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getDataPathOfAnalysis() {
        return dataPathOfAnalysis;
    }
}


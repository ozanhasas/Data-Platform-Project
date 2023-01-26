package com.example.tezspringbe.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author meteh
 * @create 6.05.2022 20:09
 *//*
 * @created 05
 * @author meteh
 */
@Data
@Document
public class Dataset {
    @Id
    private String id;
    private String senderName;
    private String email;
    private String description;
    private String dataPathOfDataSet;
    private String onaylandiMi;
    private String type;


    public Dataset(String senderName, String email, String description, String dataPathOfDataSet, String onaylandiMi, String type) {
        this.senderName = senderName;
        this.email = email;
        this.description = description;
        this.dataPathOfDataSet = dataPathOfDataSet;
        this.onaylandiMi = onaylandiMi;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataPathOfDataSet() {
        return dataPathOfDataSet;
    }

    public void setDataPathOfDataSet(String dataPathOfDataSet) {
        this.dataPathOfDataSet = dataPathOfDataSet;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOnaylandiMi() {
        return onaylandiMi;
    }

    public String getType() {
        return type;
    }

    public void setOnaylandiMi(String onaylandiMi) {
        this.onaylandiMi = onaylandiMi;
    }
}
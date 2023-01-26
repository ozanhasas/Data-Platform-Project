package com.example.tezspringbe.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class AnalysisRequest {
    @Id
    private String id;
    private String dataset_id;
    private String nameSurname;
    private String email;
    private String request;
    private boolean showInFE;

    public boolean isShowInFE() {
        return showInFE;
    }

    public void setShowInFE(boolean showInFE) {
        this.showInFE = showInFE;
    }

    public String getId() {
        return dataset_id;
    }

    public void setId(String dataset_id) {
        this.dataset_id = dataset_id;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public AnalysisRequest(String dataset_id, String nameSurname, String email, String request) {
        this.dataset_id = dataset_id;
        this.nameSurname = nameSurname;
        this.email = email;
        this.request = request;
    }
}


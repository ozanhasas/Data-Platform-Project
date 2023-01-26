package com.example.tezspringbe.models;

/**
 * @Author meteh
 * @create 28.05.2022 22:37
 *//*
 * @created 05
 * @author meteh
 */
public class ApproveAnalysisRequestResponse {
    private String related_data_set_id;
    private String data_set_name;
    private String sender;
    private String senderEmail;
    private String explanation;

    public ApproveAnalysisRequestResponse(String related_data_set_id, String data_set_name, String sender, String senderEmail, String explanation) {
        this.related_data_set_id = related_data_set_id;
        this.data_set_name = data_set_name;
        this.sender = sender;
        this.senderEmail = senderEmail;
        this.explanation = explanation;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getData_set_name() {
        return data_set_name;
    }

    public void setData_set_name(String data_set_name) {
        this.data_set_name = data_set_name;
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


    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}




package com.example.tezspringbe.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Document
public class Notice {
    private LocalDate dateOfPublish;
    private String noticeHeader;
    private String noticeBody;

    public LocalDate getDateOfPublish() {
        return dateOfPublish;
    }

    public void setDateOfPublish(LocalDate dateOfPublish) {
        this.dateOfPublish = dateOfPublish;
    }

    public String getNoticeHeader() {
        return noticeHeader;
    }

    public void setNoticeHeader(String noticeHeader) {
        this.noticeHeader = noticeHeader;
    }

    public String getNoticeBody() {
        return noticeBody;
    }

    public void setNoticeBody(String noticeBody) {
        this.noticeBody = noticeBody;
    }

    public Notice(LocalDate dateOfPublish, String noticeHeader, String noticeBody) {
        this.dateOfPublish = dateOfPublish;
        this.noticeHeader = noticeHeader;
        this.noticeBody = noticeBody;
    }
}


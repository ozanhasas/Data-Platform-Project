package com.example.tezspringbe.models;

/**
 * @Author meteh
 * @create 2.05.2022 18:41
 *//*
 * @created 05
 * @author meteh
 */
public class Contact {
    private String nameSurname;
    private String email;
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Contact(String nameSurname, String email, String content) {
        this.nameSurname = nameSurname;
        this.email = email;
        this.content = content;
    }
}


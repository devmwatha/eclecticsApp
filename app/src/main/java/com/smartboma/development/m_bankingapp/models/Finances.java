package com.smartboma.development.m_bankingapp.models;

/**
 * Created by Mwatha on 02/11/2016.
 */

public class Finances {
    private String header,content;

    public Finances(String header, String content) {
        this.header = header;
        this.content = content;
    }

    public Finances() {
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

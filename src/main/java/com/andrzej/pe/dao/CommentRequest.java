package com.andrzej.pe.dao;


public class CommentRequest {
    private String author;
    private String body;

    public CommentRequest() {
    }

    public CommentRequest(String author, String body) {
        this.author = author;
        this.body = body;
    }

    public String getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }
}

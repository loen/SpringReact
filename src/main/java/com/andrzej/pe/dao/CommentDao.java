package com.andrzej.pe.dao;

public class CommentDao {
    private  int id;
    private  String author;
    private  String body;

    public CommentDao(){}

    public CommentDao(int id, String author, String body) {
        this.id = id;
        this.author = author;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }
}

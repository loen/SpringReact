package com.andrzej.pe.api;

import com.andrzej.pe.dao.CommentDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Comment {

    @RequestMapping("/comments")
    public List<CommentDao> getComments() {
        List comments = new ArrayList();
        for (int i = 1; i <= 3 ; i++) {
            CommentDao c = new CommentDao(i, "Autor " + i, "Komentarz numer " + i);
            comments.add(c);
        }
        return comments;
    }
}

package com.andrzej.pe.api;

import com.andrzej.pe.dao.CommentDao;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class Comment {

    private List<CommentDao> comments;

    public Comment() {
        this.comments = new ArrayList();
        for (int i = 1; i <= 3 ; i++) {
            CommentDao c = new CommentDao(i, "Autor " + i, "Komentarz numer " + i);
            comments.add(c);
        }
    }

    @RequestMapping("/comments")
    public List<CommentDao> getComments() {
        return comments;
    }

    @RequestMapping(method= RequestMethod.DELETE, path = "/comments/{id}")
    public void removeComment(@PathVariable Integer id){
        comments = comments.stream()
                .filter(c -> c.getId() != id )
                .collect(Collectors.toList());
    }
}

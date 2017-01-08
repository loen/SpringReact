package com.andrzej.pe.api;

import com.andrzej.pe.dao.CommentDao;
import com.andrzej.pe.dao.CommentRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class Comment {

    private List<CommentDao> comments;
    private int nextId = 1;

    public Comment() {
        this.comments = new ArrayList();
        for (int i = 1; i <= 3 ; i++) {
            CommentDao c = new CommentDao(nextId, "Autor " + i, "Komentarz numer " + i);
            nextId++;
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

    @RequestMapping(method = RequestMethod.POST, path = "/comments")
    public CommentDao createComment(@RequestBody CommentRequest commentRequest) {
        CommentDao commentDao = new CommentDao(nextId, commentRequest.getAuthor(), commentRequest.getBody());
        nextId++;
        comments.add(commentDao);
        return commentDao;
    }
}

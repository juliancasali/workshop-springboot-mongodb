package com.example.springbootmongodb.resources;

import com.example.springbootmongodb.domain.Post;
import com.example.springbootmongodb.resources.util.URL;
import com.example.springbootmongodb.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

;

@RestController
@RequestMapping(value = "/users")
public class PostResource {

    @Autowired
    private PostService service;

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = {"/tittlesearch"}, method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<Post> list = service.findByTittle(text);
        return ResponseEntity.ok().body(list);
    }
}
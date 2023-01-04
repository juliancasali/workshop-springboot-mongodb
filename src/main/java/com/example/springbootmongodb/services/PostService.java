package com.example.springbootmongodb.services;

import com.example.springbootmongodb.domain.Post;
import com.example.springbootmongodb.repository.PostRepository;
import com.example.springbootmongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post findById(String id){
        Optional<Post> obj = repository.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTittle(String text){
        return repository.searchTitle(text);
    }
}

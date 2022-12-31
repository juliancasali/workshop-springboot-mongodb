package com.example.springbootmongodb.resources;

import com.example.springbootmongodb.domain.User;
import com.example.springbootmongodb.dto.UserDTO;
import com.example.springbootmongodb.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = service.findAll();
        List<UserDTO> userDTO = list.stream().map(x -> new UserDTO(x)).toList();
        return ResponseEntity.ok(userDTO);
    }

    @RequestMapping(value = {"/id"}, method = RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(String id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO) {
        User obj = service.froDTO(objDTO);
        obj = service.insert(obj);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUriString());
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = {"{/id}"}, method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = {"{/id}"}, method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody UserDTO objDTO, @PathVariable String id) {
        User obj = service.froDTO(objDTO);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }
}
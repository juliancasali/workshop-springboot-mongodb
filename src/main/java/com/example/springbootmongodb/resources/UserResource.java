package com.example.springbootmongodb.resources;

import com.example.springbootmongodb.domain.User;
import com.example.springbootmongodb.dto.UserDTO;
import com.example.springbootmongodb.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>>findAll(){
        List<User> list = service.findAll();
        List<UserDTO> userDTO = list.stream().map(x -> new UserDTO(x)).toList();
        return ResponseEntity.ok(userDTO);
    }

    @RequestMapping(value = {"/id"}, method = RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(String id){
        User obj = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }
}

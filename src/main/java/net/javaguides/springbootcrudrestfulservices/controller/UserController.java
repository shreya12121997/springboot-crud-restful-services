package net.javaguides.springbootcrudrestfulservices.controller;

import net.javaguides.springbootcrudrestfulservices.entity.User;
import net.javaguides.springbootcrudrestfulservices.exception.ResourceNotFoundException;
import net.javaguides.springbootcrudrestfulservices.repository.UserRepository;
import org.apache.catalina.util.ErrorPageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers(){
       return userRepository.findAll();
    }
    @PostMapping
    //Build create student api
    public User createStudent(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable (value="id") long userId){
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not exist:" + userId));
    }

    @PutMapping("/{id}")
    public User updateStudent(@RequestBody User user , @PathVariable ("id") long userId) {
        User updateUser = userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User Not Found:" + userId));
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setEmail(user.getEmail());
        return userRepository.save(updateUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable ("id") long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found:"));
        userRepository.delete(user);
        return  ResponseEntity.ok().build();

    }
}
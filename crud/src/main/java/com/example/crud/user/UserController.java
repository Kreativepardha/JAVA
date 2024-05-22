package com.example.crud.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
      return userRepository.findAll();
    } 

    @GetMapping("/{id}")
        public User getUserById(@PathVariable Long id){
            return userRepository.findById(id).get();
            }
    
    @PostMapping
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
            User existingUSer = userRepository.findById(id).get();
            existingUSer.setName(user.getName());
            existingUSer.setEmail(user.getEmail());
            return userRepository.save(existingUSer);
        }

        @DeleteMapping("/{id}")
        public String deletUser(@PathVariable Long id){
            try {
                userRepository.deleteById(id);
                return "User deleted successfully";
            } catch (Exception e) {
                return "User not found";
            }
        }

    }

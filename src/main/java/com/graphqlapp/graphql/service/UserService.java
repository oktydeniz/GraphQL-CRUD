package com.graphqlapp.graphql.service;

import com.graphqlapp.graphql.exception.UserNotFoundException;
import com.graphqlapp.graphql.model.User;
import com.graphqlapp.graphql.model.UserRequest;
import com.graphqlapp.graphql.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public List<User> getAllUser() {
        return repository.findAll();
    }

    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow( () -> new UserNotFoundException("User not found"));
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        repository.delete(user);
    }

    public User createUser(UserRequest userRequest) {
        User user = User.builder()
                .userName(userRequest.getUserName())
                .mail(userRequest.getMail())
                .role(userRequest.getRole()).build();
        return repository.save(user);

    }

    public User updateUser(UserRequest userRequest) {
        User user = getUserById(userRequest.getId());
        user.setUserName(userRequest.getUserName());
        user.setMail(userRequest.getMail());
        user.setRole(userRequest.getRole());
        return repository.save(user);
    }

}

package com.aquinoa.ziptechtask.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aquinoa.ziptechtask.controllers.models.User;
import com.aquinoa.ziptechtask.exceptions.InvalidDbRecordException;
import com.aquinoa.ziptechtask.exceptions.InvalidFieldException;
import com.aquinoa.ziptechtask.repositories.UserRepository;
import com.aquinoa.ziptechtask.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

  static final Logger l = LoggerFactory.getLogger(UserController.class);

  @Autowired
  UserService userService;

  @Autowired
  UserRepository userRepository;

  @PostMapping
  public User postUser(@RequestBody User user) throws InvalidFieldException {
    return user.toModel(userService.createUser(user.toDb()));
  }

  @GetMapping
  public List<User> getUserList() {
    return User.builder().build().toModel(StreamSupport
        .stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList()));
  }

  @GetMapping("/{id}")
  public User getUser(@PathVariable Long id) throws InvalidDbRecordException {
    return User.builder().build().toModel(userRepository.findById(id)
        .orElseThrow(() -> new InvalidDbRecordException("Invalid User.")));
  }
}

package com.stackroute.controller;

import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("user")
    //@RequestMapping (path = "/something", method = RequestMethod.PUT)
    public ResponseEntity<?> savedUser(@RequestBody User user){
         ResponseEntity responseEntity;
         try {
             userService.saveUser(user);
             responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);

         }catch (UserAlreadyExistsException e){
             responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);

         }
         return responseEntity;
    }
    @GetMapping("user")
    public ResponseEntity<?> getAllUser(){

        return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
    }

//    @GetMapping("DeleteUser")
//   public void deleteUser(@RequestParam int id){
//        userService.delete()
//    }

}

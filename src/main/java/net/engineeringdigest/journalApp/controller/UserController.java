package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.entity.User;
import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping
//    public ResponseEntity<List<User>> getAll(){
//        try{
//            return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
//        }catch(Exception e){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/username/{nusername}")
    public ResponseEntity<User> findById(@PathVariable String nusername){
        Optional<User> check = userService.findByUserName(nusername);
        if(check.isPresent()){
            return new ResponseEntity<>(check.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String nusername = authentication.getName();
            User newuser = userService.findByUserName(nusername).orElse(null);
            if(newuser!=null){
                newuser.setUserName((user.getUserName()!=null && !user.getUserName().equals(""))?user.getUserName():newuser.getUserName());
                newuser.setPassword((user.getPassword()!=null && !user.getPassword().equals(""))?user.getPassword():newuser.getPassword());
                userService.newSaveEntry(newuser);
                return new ResponseEntity<>(newuser,HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<User> deleteById(){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String nusername = authentication.getName();
            userService.deleteByUserName(nusername);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}

package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.entity.User;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AppCache appCache;


    @Autowired
    private UserService userService;

    @GetMapping("/all-user")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> list = userService.getAll();
        if(list!=null && !list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/create-admin-user")
    public void createAdmin(@RequestBody User user){
        user.setRoles(new ArrayList<>());
        user.setJournalEntries(new ArrayList<>()) ;
        userService.newAdminSaveEntry(user);
    }

    @GetMapping("/clear-app-cache")
    public void clearAppCache(){
        appCache.init();
    }

}

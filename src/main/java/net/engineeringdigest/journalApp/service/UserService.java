package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveEntry(User user){
        userRepository.save(user);
    }

    public boolean newSaveEntry(User user){
            try{
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.getRoles().add("USER");
                userRepository.save(user);
                return true;
            }catch(Exception e){
                return false;
            }
    }

    public boolean newAdminSaveEntry(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.getRoles().add("USER");
            user.getRoles().add("ADMIN");
            userRepository.save(user);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }
    public Optional<User> findByUserName(String username){
        return userRepository.findByUserName(username);
    }
    public void deleteByUserName(String username){
        userRepository.deleteByUserName(username);
    }
}

package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import java.util.regex.Pattern;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/mail")
@Service
public class UserSentimentRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/sentiment-analysis")
    public List<User> getUserForSA(){
        Query query = new Query();
        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,10}$");
        query.addCriteria(Criteria.where("email").regex(emailPattern));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        List<User> list = mongoTemplate.find(query, User.class);
        return list;
    }

}

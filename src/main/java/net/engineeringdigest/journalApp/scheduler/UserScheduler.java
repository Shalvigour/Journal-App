package net.engineeringdigest.journalApp.scheduler;


import net.engineeringdigest.journalApp.Sentiment;
import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserSentimentRepository;
import net.engineeringdigest.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private UserSentimentRepository userSentimentRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 0 9 * * SUN")//for every sunday
    //  "0 * * * * *" will be used to send mail every minute.
    public void fetchUsersAndSendSaMail(){
        List<User> users=userSentimentRepository.getUserForSA();
        for(User user : users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x-> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment,Integer> sentimentCounts = new HashMap<>();
            for(Sentiment sentiment:sentiments){
                if(sentiment!=null)
                sentimentCounts.put(sentiment,sentimentCounts.getOrDefault(sentiment,0)+1);
            }
            Sentiment mostFrequent = null;
            int max=0;
            for(Map.Entry<Sentiment,Integer> entry:sentimentCounts.entrySet()){
                if(entry.getValue()>max){
                    max=entry.getValue();
                    mostFrequent=entry.getKey();
                }
            }
            if(mostFrequent !=null){
                emailService.sendEmail(user.getEmail(),"Sentiment analysis for last 7 days",mostFrequent.toString());

            }

        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache(){
        appCache.init();
    }
}






//
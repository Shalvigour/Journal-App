package net.engineeringdigest.journalApp.cache;


import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import net.engineeringdigest.journalApp.repository.ConfigJournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//this will be used to store the config values(like API key) in the database, then database se
// nikal kr hum usko cache mein daalenge kyuki voh frequently use hogi
@Component
public class AppCache {

    @Autowired
    private ConfigJournalRepository configJournalRepository;

    public Map<String,String> app_Cache;

    @PostConstruct
    public void init(){
        app_Cache=new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalRepository.findAll();
        for(ConfigJournalAppEntity configJournalAppEntity:all){
            app_Cache.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
        }
    }

}

package net.engineeringdigest.journalApp.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


//this will be used when we need to store the config values in the database like API key.
@NoArgsConstructor
@Data
@Document(collection="config_journal_app")
public class ConfigJournalAppEntity {
    private String key;
    private String value;
}

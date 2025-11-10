package net.engineeringdigest.journalApp.entity;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import lombok.NonNull;
import java.util.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@Builder
public class User {
    @Id
    private ObjectId id;

    @NonNull
    @Indexed(unique=true)
    private String userName;

    @NonNull
    private String password;

    @DBRef
    @Builder.Default
    private List<JournalEntry> journalEntries = new ArrayList<>();

    @Builder.Default
    private List<String> roles=new ArrayList<>();
}

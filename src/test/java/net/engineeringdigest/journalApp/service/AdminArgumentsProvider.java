package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import net.engineeringdigest.journalApp.entity.User;


import java.util.stream.Stream;

public class AdminArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().userName("anil").password("anil1").build()),
                Arguments.of(User.builder().userName("arvind").password("arvind1").build())
        );
    }
}

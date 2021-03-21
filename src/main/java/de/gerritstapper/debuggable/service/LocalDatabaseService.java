package de.gerritstapper.debuggable.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("local")
public class LocalDatabaseService implements IDatabaseService {

    @Override
    public String getById(Long id) {
        String result = "";

        // overwrite the result by reading from file

        return result;
    }
}

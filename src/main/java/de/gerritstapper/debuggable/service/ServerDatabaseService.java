package de.gerritstapper.debuggable.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
@Profile("!local")
public class ServerDatabaseService implements IDatabaseService {

    private final Boolean persistToFile;
    private final String persistDirectory;

    public ServerDatabaseService(
            @Value("${debuggable.persist}") Boolean persistToFile,
            @Value("${debuggable.persist.directory}") String persistDirectory) {
        this.persistToFile = persistToFile;
        this.persistDirectory = persistDirectory;
    }

    @Override
    public String getById(Long id) {
        String result = "Keep your code debuggable";

        // overwrite the result with data from the actual database
        if ( persistToFile ) {
            writeResultsToFile(result, id);
        }

        return result;
    }

    private void writeResultsToFile(String result, Long id) {
        String filename = String.format("person_%s_results", id);

        try {
            String log = String.format(
                    "Trying to write results to file %s in directory %s",
                    filename,
                    persistDirectory
            );
            System.out.println(log);

            FileWriter writer = new FileWriter(persistDirectory + filename);
            writer.write(result);
            writer.close();
        } catch (IOException exception) {
            String errorMessage = String.format(
                    "Problem creating file %s in directory %s",
                    filename,
                    persistDirectory
            );
            System.out.println(errorMessage);
        }

    }
}

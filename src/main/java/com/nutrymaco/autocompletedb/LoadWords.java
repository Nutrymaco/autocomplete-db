package com.nutrymaco.autocompletedb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@Slf4j
public class LoadWords {
    @Bean
    CommandLineRunner initWords(WordsService wordsService) {
        return args -> {
            List<String> words = loadLinesFromFile("/words.txt");
            for (String word : words) {
                wordsService.addWord(word);
            }
        };
    }

    protected List<String> loadLinesFromFile(String fileName) throws IllegalStateException {
        List<String> lines = new ArrayList<>();

        try {
            InputStream is = getClass().getResourceAsStream(fileName);
            LineNumberReader reader = new LineNumberReader(new InputStreamReader(is));
            while (reader.ready()) {
                String line = reader.readLine();
                if (!"".equals(line)) {
                    lines.add(line);
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("couldn't load file " + fileName, e);
        }
        return lines;
    }
}

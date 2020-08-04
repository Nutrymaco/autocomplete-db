package com.nutrymaco.autocompletedb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ApiController {
    @Autowired
    WordsService wordsService;

    @GetMapping("/words")
    List<String> getWordsByQuery(@RequestParam(defaultValue = "") String query) {
        return wordsService.getWordsByQuery(query);
    }

}

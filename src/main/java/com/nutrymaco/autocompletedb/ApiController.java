package com.nutrymaco.autocompletedb;


import lombok.Getter;
import lombok.Setter;
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

    @GetMapping("/test")
    String test(@RequestParam Params params) {
        System.out.println(params);
        return "hihihih";
    }

    @Getter
    @Setter
    class Params {
        private int p1;
        private String p2;

        @Override
        public String toString() {
            return "Params{" +
                    "p1=" + p1 +
                    ", p2='" + p2 + '\'' +
                    '}';
        }
    }
}

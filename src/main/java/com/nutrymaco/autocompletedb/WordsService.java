package com.nutrymaco.autocompletedb;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class WordsService {
    private final List<String> words = new ArrayList<>();

    private final static Map<Character, Character> engRus = new TreeMap<>(){
        {
            put('q', 'й');
            put('w', 'ц');
            put('e', 'у');
            put('r', 'к');
            put('t', 'е');
            put('y', 'н');
            put('u', 'г');
            put('i', 'ш');
            put('o', 'щ');
            put('p', 'з');
            put('[', 'х');
            put(']', 'ъ');
            put('a', 'ф');
            put('s', 'ы');
            put('d', 'в');
            put('f', 'а');
            put('g', 'п');
            put('h', 'р');
            put('j', 'о');
            put('k', 'л');
            put('l', 'д');
            put(';', 'ж');
            put('\'', 'э');
            put('\\', 'ё');
            put('z', 'я');
            put('x', 'ч');
            put('c', 'с');
            put('v', 'м');
            put('b', 'и');
            put('n', 'т');
            put('m', 'ь');
            put(',', 'б');
            put('.', 'ю');
        }
    };
    private final static Map<Character, Character> rusEng = new TreeMap<>();
    static {
        for (Map.Entry<Character, Character> e : engRus.entrySet()) {
            rusEng.put(e.getValue(), e.getKey());
        }
    }

    List<String> getAllWords() {
        return words;
    }

    void addWords(List<String> words) {
        this.words.addAll(words);
    }

    void addWord(String word) {
        words.add(word);
    }

    List<String> getWordsByQuery(String query) {
        if ("".equals(query)) {
            return words;
        }
        query = query.toLowerCase();
        String[] queryWords = query.split(" ");
        String prefix = queryWords[queryWords.length - 1];

        List<String> withPrefix = findWordsByPrefix(prefix, words);

        return withPrefix;
    }

    private static List<String> findWordsByPrefix(String prefix, List<String> words) {
        List<String> withPrefix = new LinkedList<>();
        for (String word : words) {
            if (word.startsWith(prefix) || word.startsWith(invertString(prefix))) {
                withPrefix.add(word);
            }
        }
        return withPrefix;
    }

    private static boolean wordMatchWord(String word, String[] otherWords) {
        for (String other : otherWords) {
            if (word.equals(other)) {
                return true;
            }
        }

        return false;
    }


    private static String invertString(String string) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char curChar = string.charAt(i);
            Character repl;
            if ((repl = engRus.get(curChar)) != null) {
                sb.append(repl);
            } else if ((repl = rusEng.get(curChar)) != null) {
                sb.append(repl);
            } else {
                sb.append(curChar);
            }
        }
        return sb.toString();
    }
}

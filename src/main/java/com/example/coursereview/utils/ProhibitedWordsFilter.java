package com.example.coursereview.utils;

import java.util.List;
import java.util.Arrays;

public class ProhibitedWordsFilter {
    private static final List<String> PROHIBITED_WORDS = Arrays.asList(
            "stupid",
            "dumb",
            "idiot",
            "lazy",
            "incompetent",
            "boring",
            "disrespectful",
            "useless",
            "awful",
            "horrible",
            "terrible",
            "rude",
            "pathetic",
            "arrogant",
            "biased"
    ); // Add words as needed

    public static boolean containsProhibitedWords(String comment) {
        for (String word : PROHIBITED_WORDS) {
            if (comment.toLowerCase().contains(word)) {
                return true;
            }
        }
        return false;
    }
}

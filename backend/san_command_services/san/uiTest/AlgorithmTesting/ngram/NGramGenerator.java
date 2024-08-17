package com.ponsun.san.uiTest.AlgorithmTesting.ngram;

import org.apache.commons.text.similarity.FuzzyScore;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NGramGenerator {

    public static List<String> generateNGrams(String input, int n) {
        List<String> ngrams = new ArrayList<>();

        // Iterate over the input string
        for (int i = 0; i <= input.length() - n; i++) {
            // Extract substring of length 'n' starting from index 'i'
            String ngram = input.substring(i, i + n);
            ngrams.add(ngram);
        }

        return ngrams;
    }

    public static void main(String[] args) {
        // Example strings
        String s1 = "ramkumar raja";
        String s2 = "ramkumar rahul";

        // Length of the n-gram
        int n = 2;

        // Generate n-grams
        List<String> ngrams1 = generateNGrams(s1, n);
        List<String> ngrams2 = generateNGrams(s2, n);

        // Create copies of ngrams1 and ngrams2
        List<String> ng1 = new ArrayList<>(ngrams1);
        List<String> ng2 = new ArrayList<>(ngrams2);

        // Print the generated n-grams
        System.out.println("Generated " + n + "-grams:");
        System.out.println(ngrams1);
        System.out.println(ngrams2);
        String str2="";

        // Modify ng2
        for (int i = 0; i < ngrams2.size(); i++) {
            if (ngrams1.contains(ngrams2.get(i))) {
                ng2.set(i, "x");
            }else {
                str2+=ng2.get(i);
            }
        }
        String str1="";


        // Modify ng1
        for (int i = 0; i < ngrams1.size(); i++) {
            if (ngrams2.contains(ngrams1.get(i))) {
                ng1.set(i, "x");
            } else {
                str1+=ng1.get(i);
            }


        }
        Locale englishLocale = Locale.ENGLISH;

        FuzzyScore fuzzyScore = new FuzzyScore(englishLocale);


        int score = fuzzyScore.fuzzyScore(str1, str2);


        System.out.println("New ng1: " + ng1);
        System.out.println("New ng2: " + ng2);
        System.out.println(score);
    }
}


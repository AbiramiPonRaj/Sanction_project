package com.ponsun.san.uiTest.AlgorithmTesting.ngram;


import java.util.ArrayList;
import java.util.List;

public class NGramGeneratorReves {

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

    public static String concatenateReversedNGrams(List<String> ngrams) {
        StringBuilder concatenatedString = new StringBuilder();

        // Iterate through the list of n-grams
        for (String ngram : ngrams) {
            // Reverse the n-gram
            String reversedNgram = new StringBuilder(ngram).reverse().toString();
            // Concatenate the reversed n-gram to the result string
            concatenatedString.append(reversedNgram);
        }

        return concatenatedString.toString();
    }

    public static void main(String[] args) {
        // Example strings
        String s1 = "ramkumar raja";
        String s2 = "ramkumar rahul";

        // Length of the n-gram
        int n = 3;

        // Generate n-grams
        List<String> ngrams1 = generateNGrams(s1, n);
        List<String> ngrams2 = generateNGrams(s2, n);

        // Concatenate reversed n-grams
        String concatenatedReversedNGrams1 = concatenateReversedNGrams(ngrams1);
        String concatenatedReversedNGrams2 = concatenateReversedNGrams(ngrams2);

        // Print the concatenated reversed n-grams
        System.out.println("Concatenated Reversed n-grams for s1: " + concatenatedReversedNGrams1);
        System.out.println("Concatenated Reversed n-grams for s2: " + concatenatedReversedNGrams2);
    }
}
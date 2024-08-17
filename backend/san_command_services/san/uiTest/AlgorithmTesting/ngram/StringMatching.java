package com.ponsun.san.uiTest.AlgorithmTesting.ngram;

import java.util.HashSet;
import java.util.StringTokenizer;

public class StringMatching {

    // Function to calculate matching percentage
    public static double calculateMatchingPercentage(String str1, String str2) {
        // Tokenize the input strings
        StringTokenizer tokenizer1 = new StringTokenizer(str1);
        StringTokenizer tokenizer2 = new StringTokenizer(str2);

        // Initialize variables to store common characters and total characters
        HashSet<String> set1 = new HashSet<>();
        HashSet<String> set2 = new HashSet<>();

        // Populate sets with tokens from the input strings
        while (tokenizer1.hasMoreTokens()) {
            set1.add(tokenizer1.nextToken());
        }

        while (tokenizer2.hasMoreTokens()) {
            set2.add(tokenizer2.nextToken());
        }

        // Calculate common characters
        HashSet<String> commonSet = new HashSet<>(set1);
        commonSet.retainAll(set2);

        // Count the length of common characters
        int commonChars = commonSet.stream().mapToInt(String::length).sum();

        // Total characters
        int totalChars = Math.max(str1.length(), str2.length());

        // Calculate unmatched characters
        int unmatchedChars = totalChars - commonChars;

        // Calculate matching percentage for common characters
        double matchingPercentage = (double) commonChars / totalChars;

        // Calculate matching percentage for unmatched characters
        double unmatchedPercentage = (double) unmatchedChars / totalChars;

        // Combine the matching percentages
        double combinedPercentage = matchingPercentage + unmatchedPercentage;

        // Print common, total, and unmatched characters
        System.out.println("Common Characters: " + commonChars);
        System.out.println("Total Characters: " + totalChars);
        System.out.println("Unmatched Characters: " + unmatchedChars);

        return combinedPercentage;
    }

//    public static void main(String[] args) {
//        // Example strings
//        String str1 = "ramkumar raja";
//        String str2 = "ramkumar rahu";
//
//        // Calculate matching percentage
//        double percentage = calculateMatchingPercentage(str1, str2);
//
//        // Print the result
//        System.out.println("Matching Percentage: " + percentage);
//    }
}

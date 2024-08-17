package com.ponsun.san.jaroWinkler;

import org.apache.commons.codec.language.DoubleMetaphone;
import org.apache.commons.text.similarity.CosineSimilarity;

import java.util.HashMap;
import java.util.Map;

public class DoubleMetaphoneCosineSimilarity {

    // Function to calculate the Double Metaphone code for a string
    public static String calculateDoubleMetaphone(String str) {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        return doubleMetaphone.doubleMetaphone(str);
    }

    public static double CalculateDoubleMetaphone(String str1,String str2) {

        String[] token1 = str1.split(" ");
        String[] token2 = str2.split(" ");

        // Calculate Double Metaphone codes for input strings
        double maxSimilarity = Double.MIN_VALUE;

        for (String tokenA : token1) {
            for (String tokenB : token2) {
                String metaphoneA = calculateDoubleMetaphone(tokenA);
                String metaphoneB = calculateDoubleMetaphone(tokenB);
                // Calculate cosine similarity between metaphoneA and metaphoneB
                double similarity = calculateCosineSimilarity(metaphoneA, metaphoneB);
                if (similarity > maxSimilarity) {
                    maxSimilarity = similarity;
                }
            }
        }

    return maxSimilarity*1e2;
    }

    // Function to calculate cosine similarity between two strings
    public static double calculateCosineSimilarity(String str1, String str2) {
        Map<CharSequence, Integer> vector1 = createFrequencyVector(str1);
        Map<CharSequence, Integer> vector2 = createFrequencyVector(str2);

        CosineSimilarity cosineSimilarity = new CosineSimilarity();
        return cosineSimilarity.cosineSimilarity(vector1, vector2);
    }

    // Function to create frequency vector from a string
    public static Map<CharSequence, Integer> createFrequencyVector(String str) {
        Map<CharSequence, Integer> vector = new HashMap<>();
        for (char c : str.toCharArray()) {
            CharSequence charSeq = String.valueOf(c);
            vector.put(charSeq, vector.getOrDefault(charSeq, 0) + 1);
        }
        return vector;
    }
}

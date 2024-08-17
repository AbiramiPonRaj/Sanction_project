package com.ponsun.san.uiTest.AlgorithmTesting.Fuzzy;

import me.xdrop.fuzzywuzzy.FuzzySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Fuzzy_WeightedRatio {
    public static double Fuzzy_WeightedRatio(String nametest1, String nametest2) {

        String  name1=removeDuplicateWords(nametest1);
        String name2=removeDuplicateWords(nametest2);

        // Tokenize the names by spaces
        ArrayList<String> token1 = tokenizeString(name1);
        ArrayList<String> token2 = tokenizeString(name2);
        ArrayList<String> Temper = new ArrayList<String>();

        if(token2.size()<token1.size()) {
            Temper=new ArrayList<String>(token2);
            token2=new ArrayList<String>(token1);
            token1=new ArrayList<String>(Temper);
        }

        ArrayList<Double> similarity = new ArrayList<Double>();

        for (int i=0;i<token2.size();i++) {
            ArrayList<Double> sim=new ArrayList<Double>();
            for (int j=0;j<token1.size();j++) {

                sim.add((double)Math.round(FuzzySearch.weightedRatio(token2.get(i),token1.get(j))));
//            	System.out.println("Similarity score between  " +token2.get(i)+" and "+token1.get(j)+" is :"+sim.get(sim.size()-1));

            }
//            System.out.println("-----------------------------------------------");
            int fl=0,f2=0;
            if(sim.contains(100.0)) {
                f2=+1;
                similarity.add(100.0);
            }
            else
            {
                for (double v:sim) {
                    if (v>=80) {
                        fl=+1;
                        similarity.add(v);
                    }
                }
            }

            if(fl==0 && f2==0) {
                similarity.addAll(sim);
            }
        }

        double matchingper=Average(similarity);

        return matchingper;

    }


    public static double Average(ArrayList<Double> numbers) {
        // Calculate the sum of all elements in the ArrayList
        double sum=0;
        int count=numbers.size();
        for (double number : numbers) {

            sum=sum+number;
        }
        double   matchingPer=sum/(count);
        return matchingPer;
    }

    public static String removeDuplicateWords(String str) {
        str=str.toLowerCase();
        String le = str.replaceAll("^\\s+", "");
        str = le.replaceAll("\\s+$", "");        String[] words = str.split("\\s+"); // Split the string into words
        Set<String> uniqueWords = new LinkedHashSet<>(); // Use LinkedHashSet to maintain insertion order

        for (String word : words) {
            uniqueWords.add(word); // Add each word to the set
        }

        return String.join(" ", uniqueWords); // Join the words back into a single string
    }
    // Method to tokenize a string by spaces
    public static ArrayList<String> tokenizeString(String str) {
        String[] tokens;
        if (str.contains(" ")) { // If the string contains spaces, split by space
            tokens = str.split(" ");
        } else { // Otherwise, return the single string as an array
            tokens = new String[] { str };
        }
        return new ArrayList<>(Arrays.asList(tokens));
    }
}

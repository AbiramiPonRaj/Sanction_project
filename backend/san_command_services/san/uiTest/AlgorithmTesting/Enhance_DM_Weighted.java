package com.ponsun.san.uiTest.AlgorithmTesting;

import info.debatty.java.stringsimilarity.CharacterSubstitutionInterface;
import info.debatty.java.stringsimilarity.WeightedLevenshtein;
import org.apache.commons.codec.language.DoubleMetaphone;
import org.apache.commons.text.similarity.CosineSimilarity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Enhance_DM_Weighted {
    public static double Main_Weighted(String name1,String name2) {
//        String [][] Input=new String[][] {{"ramkumar raja","ramkumar rahu"},
//                {"roja sampath","rahul sampath"},
//                {"kiran ram","iran"},
//                {"rajesh chandra","rajesh chandrasekar"},
//                {"surendran sundarakani","surendran sundaram"},
//                {"ramani rajesh","rahul rajesh"},
//                {"kolika pattan","koki pattan"},
//                {"sundara guhan","sundara gu"},
//                {"mugesh perumal","sumathi perumal"},
//                {"gayathri rajaraman","gayathri rajaram"},
//                {"shweta gupta","guptashweta"},
//                {"shweta gupta ram","shwetaramgupta"},
//                {"shweta gupta","shweta gupta gupta"},
//                {"shweta gupta ram","shwetaguptaram"},
//                {"shweta gupta ram","gupta shweta ram"}
//        };

            String s1   = name1;//Input[k][0];
            String s2   = name2;//Input[k][1];

            String[] token1 = s1.split(" ");
            String[] token2 = s2.split(" ");

            WeightedLevenshtein wl = new WeightedLevenshtein(new CharacterSubstitutionInterface() {
                        public double cost(char c1, char c2) {

                            // The cost for substituting 't' and 'r' is considered
                            // smaller as these 2 are located next to each other
                            // on a keyboard
                            if (c1 == 't' && c2 == 'r') {
                                return 0.5;
                            }

                            return 1.0;
                        }
                    });

            ArrayList<Double> scoreM=new ArrayList<Double>();
            ArrayList<Double> scorem=new ArrayList<Double>();

            for (String tokenA : token1) {
                for (String tokenB : token2) {
                    String metaphoneA = calculateDoubleMetaphone(tokenA);
                    String metaphoneB = calculateDoubleMetaphone(tokenB);

                    double Dist =  wl.distance(metaphoneA, metaphoneB);

                    double NDist = (double) Dist / Math.max(metaphoneA.length(), metaphoneB.length());

                    double similarity = cosineSimilarityWithWeight(metaphoneA, metaphoneB, NDist);

                    if(similarity>60)
                        scoreM.add(similarity);
                    else
                        scorem.add(similarity);

                }
            }

            double score =0;

            if(scoreM.size()<scorem.size()){
                score=Avg(scorem);}
            else {
                score=Avg(scoreM);
            }
return score;
    }


    private static double Avg(ArrayList<Double> d) {
        double m=0;
        for (double d1 : d ) {
            m+=d1;
        }
        return m/d.size();
    }

    //Custom cosine similarity calculation method with Levenshtein weight
    private static double cosineSimilarityWithWeight(String left, String right, double weight) {
        CosineSimilarity cosineSimilarity = new CosineSimilarity();

        Map<CharSequence, Integer> leftMap = stringToMap(left);
        Map<CharSequence, Integer> rightMap = stringToMap(right);

        double similarityWithoutWeight = cosineSimilarity.cosineSimilarity(leftMap, rightMap);
        double similarityWithWeight = (similarityWithoutWeight* (1 - weight))*1e2;

        return similarityWithWeight;
    }

    // Helper method to convert string to map of characters and their frequencies
    private static Map<CharSequence, Integer> stringToMap(String str) {
        Map<CharSequence, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            CharSequence ch = String.valueOf(c);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        return map;
    }

    // Function to calculate the Double Metaphone code for a string
    public static String calculateDoubleMetaphone(String str) {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        return doubleMetaphone.doubleMetaphone(str);
    }
}

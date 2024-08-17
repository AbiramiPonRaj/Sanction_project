package com.ponsun.san.uiTest.AlgorithmTesting;

import me.xdrop.fuzzywuzzy.FuzzySearch;

import java.util.ArrayList;

public class Enhance_fuzzy_weighted {
    public static String disAlgm="Weighted";

    public static double Main_fuzzy_weighted(String name1,String name2) {

            String s1   = name1;
            String s2   = name2;
            String[] parts1 = s1.split(" ");
            String[] parts2 = s2.split(" ");
            ArrayList<Double> scoreM=new ArrayList<>();
            ArrayList<Double> scorem=new ArrayList<>();
            for (int i=0;i<parts1.length;i++) {
                for (int j=0;j<parts2.length;j++) {
                    double d=(double) FuzzySearch.weightedRatio(parts1[i], parts2[j]);
                    if(d>60 ) {
                        scoreM.add(d);}
                    else {
                        scorem.add(d);
                    }

                }
            }
            double score =0;

            if(scoreM.size()<scorem.size()){
                score=Avg(scorem);}
            else {
                score=Avg(scoreM);}
        return score;
    }

    private static double Avg(ArrayList<Double> d) {
        double m=0;
        for (double d1 : d ) {
            m+=d1;
        }
        return m/d.size();
    }
}

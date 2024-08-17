package com.ponsun.san.uiTest.AlgorithmTesting.Oneside;
import me.xdrop.fuzzywuzzy.FuzzySearch;

import java.util.*;
import java.util.stream.Collectors;
public class Oneside {
    public static ArrayList<String> token;
    public static double onesideMatching(String nametest1,String nametest2) {
        String  name1=removeDuplicateWords(nametest1);
        String name2=removeDuplicateWords(nametest2);

        // Tokenize the names by spaces
        ArrayList<String> token1 = tokenizeString(name1);
        ArrayList<String> token2 = tokenizeString(name2);

        double matchcount = 0;


        //find the similar tokens
        ArrayList<String>similar=new ArrayList<String>();
        ArrayList<String>similar2=new ArrayList<String>();
        ArrayList<String>similar1=new ArrayList<String>();


        double sim=0;        boolean issingle=false;

        for (int l1=0;l1<token1.size();l1++) {
            for (int l2=0;l2<token2.size();l2++) {
                sim= FuzzySearch.weightedRatio(token1.get(l1),token2.get(l2))/1e2;
                if ( sim==1) {
                    similar.add(token1.get(l1));
                    if(token1.get(l1).length()==2 ||token1.get(l1).length()==1 )
                        issingle=true;
                }
            } }

        for (int k1=0;k1<similar.size();k1++) {
            token1.remove(token1.indexOf(similar.get(k1)));
            token2.remove(token2.indexOf(similar.get(k1)));
            matchcount+=similar.get(k1).length();
        }


        int l1=0 ,l2=0;   int incr=0;  int c=0;
        while(l1<token1.size() && l2<token2.size()) {
            l2=0;
            while(l2<token2.size() && l1<token1.size()) {
                incr=0;
//      		  System.out.println(l1+"   "+l2+token1.get(l1)+"   "+token2.get(l2));
                if((token1.get(l1).length()==1 || token2.get(l2).length()==1) && similar.size()!=0) {

                    if(token1.get(l1).charAt(0)==token2.get(l2).charAt(0) ) {
                        issingle=true;

                        matchcount+=1;
                        if(token2.get(l2).length()==1)
                            c++;
                        token1.remove(l1);
                        token2.remove(l2);
                        incr++;
                    } else {
                        incr++;}}
                else if(token1.get(l1).contains(token2.get(l2)) ) {
                    if(token2.get(l2).length()==2 && token2.get(l2).equalsIgnoreCase(token1.get(l1).substring(0, 2))==true) {
                        issingle=true;
                    }
                    similar2.add(token2.get(l2));
                    int stindex=token2.get(l2).indexOf(similar2.get(similar2.size()-1));
                    int enindex= stindex + similar2.get(similar2.size()-1).length() - 1;
                    token2.set(l2, token2.get(l2).substring(0, stindex) + token2.get(l2).substring(enindex + 1));
                    stindex=token1.get(l1).indexOf(similar2.get(similar2.size()-1));
                    enindex= stindex + similar2.get(similar2.size()-1).length() - 1;
                    token1.set(l1, token1.get(l1).substring(0, stindex) + token1.get(l1).substring(enindex + 1));
                    incr++;
                } else if(token2.get(l2).contains(token1.get(l1))) {

                    if(token1.get(l1).length()==2  && token1.get(l1).equalsIgnoreCase(token2.get(l2).substring(0, 2))==true) {
                        issingle=true;
                    }
                    similar1.add(token1.get(l1));
                    int stindex=token1.get(l1).indexOf(similar1.get(similar1.size()-1));
                    int enindex= stindex + similar1.get(similar1.size()-1).length() - 1;
                    token1.set(l1, token1.get(l1).substring(0, stindex) + token1.get(l1).substring(enindex + 1));
                    stindex=token2.get(l2).indexOf(similar1.get(similar1.size()-1));
                    enindex= stindex + similar1.get(similar1.size()-1).length() - 1;
                    token2.set(l2, token2.get(l2).substring(0, stindex) + token2.get(l2).substring(enindex + 1));
                    incr++;
                }else {
                    incr++;
                }
                if(incr>0) { l2++;}
            }
            l2=0;
            if(incr>0) { l1++;}
        }


        double totalcount1=0;
        double totalcount2=0;

//if(c>0)
        totalcount1 =findlength(name1);
//else
        totalcount2 =findlength(name2);


        for (int k1=0;k1<similar1.size();k1++) {
            if( similar1.get(k1).isEmpty()==false)
                matchcount+=similar1.get(k1).length();
        }

        for (int k1=0;k1<similar2.size();k1++) {
            if( similar2.get(k1).isEmpty()==false)
                matchcount+=similar2.get(k1).length();
        }


        int i1=0;
        while( i1<token1.size()) {
            if(token1.get(i1).isEmpty()|| similar.contains(token1.get(i1))   )
                token1.remove(i1);
            i1++;
        }


        i1=0;
        while( i1<token2.size()) {
            if(token2.get(i1).isEmpty() || similar.contains(token2.get(i1)))
                token2.remove(i1);
            i1++;
        }


        // Initialize counters for match and total lengths
        token= new ArrayList<String>(token2);int i=0;
//        // Calculate matching characters from name1 in name2
        while( token.size()>0 && token1.size()>0){
            for ( i=0;i<token1.size();i++) {
                if(token1.get(i).isEmpty() == false) {
                    matchcount += GetMatching(token1.get(i));
                } }
            if(token.isEmpty()==true||token1.size()==1||i==token1.size())
                break;
        }

//    System.out.println("Total length and Matching Count \n=====================================");
//    System.out.println("Total length 1 :"+totalcount1);
//    System.out.println("Total length 2 :"+totalcount2);
//    System.out.println("Matching count :"+matchcount);

        // Calculate the matching percentage for both names
        double matchingper1 = (matchcount / totalcount1) * 100;

        double matchingper2 = (matchcount / totalcount2) * 100;
        double matchingper=Math.max(matchingper1, matchingper2);

        if(Double.isNaN(matchingper)|| !issingle) {
            matchingper=0;
        }

//        System.out.println("matchcount:"+issingle);
//        System.out.println("Final matching score between  "+nametest1+" and  "+nametest2+" :" + matchingper);
    return matchingper;
    }





    // Method to find matching characters between two strings
    public static int GetMatching(String str1) {

        int	matchingCount=0;
        if(token.isEmpty()) {
            matchingCount=0;
        } else {
            double sim=0;int m=0;double v=0;
            for (int l=0;l<token.size();l++) {
                v=	FuzzySearch.weightedRatio(str1,token.get(l))/1e2;
                if(sim<v) {
                    sim=v;m=l;
                }	}
            String str2=token.get(m);
            token.remove(m);
            List<Character> charList = str1.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
            int index1 = 0;
            int index2 = 2;
            String st = "";
            // Loop through str1 and compare substrings of length 2 with str2
            while (index2 <= str1.length()) {

                st = str1.substring(index1, index2);
                // Check if str2 contains the substring from str1
                if (str2.contains(st)) {
                    charList.set(index1, '1');
                    charList.set(index1+1,'1');

                    index1 += 1; // Move index1 by 2 positions
                    index2 = index1 + 2; // Adjust index2 to be 2 positions ahead of index1

                } else {

                    index1++; // Move index1 by 1 position
                    index2++; // Move index2 by 1 position
                }
            }

            for (int k=0;k<charList.size();k++) {
                if(charList.get(k)=='1')
                    matchingCount++;
            }

        }
        return matchingCount;
    }
    private static double findlength(String str) {
        return str.replaceAll("\\s+", "").length();
    }
    public static String removeDuplicateWords(String str) {
        str=str.toLowerCase();
        String[] words = str.split("\\s+"); // Split the string into words
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
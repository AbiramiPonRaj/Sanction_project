package com.ponsun.san.jaroWinkler;

public class jaroWinkler {
    // Function to calculate the
// Jaro Similarity of two Strings
    public static double jaro_distance(String s1, String s2)
    {
        // If the Strings are equal
        if (s1 == s2)
            return 1.0;

        // Length of two Strings
        int len1 = s1.length(),
                len2 = s2.length();

        // Maximum distance upto which matching
        // is allowed
        int max_dist = (int) (Math.floor(Math.max(len1, len2) / 2) - 1);

        // Count of matches
        int match = 0;

        // Hash for matches
        int hash_s1[] = new int[s1.length()];
        int hash_s2[] = new int[s2.length()];

        // Traverse through the first String
        for (int i = 0; i < len1; i++)
        {

            // Check if there is any matches
            for (int j = Math.max(0, i - max_dist);
                 j < Math.min(len2, i + max_dist + 1); j++)

                // If there is a match
                if (s1.charAt(i) == s2.charAt(j) && hash_s2[j] == 0)
                {
                    hash_s1[i] = 1;
                    hash_s2[j] = 1;
                    match++;
                    break;
                }
        }

        // If there is no match
        if (match == 0)
            return 0.0;

        // Number of transpositions
        double t = 0;

        int point = 0;

        // Count number of occurrences
        // where two characters match but
        // there is a third matched character
        // in between the indices
        for (int i = 0; i < len1; i++)
            if (hash_s1[i] == 1)
            {
                // Find the next matched character
                // in second String
                while (hash_s2[point] == 0)
                    point++;

                if (s1.charAt(i) != s2.charAt(point++) )
                    t++;
            }

        t /= 2;

        // Return the Jaro Similarity
        return (((double)match) / ((double)len1)
                + ((double)match) / ((double)len2)
                + ((double)match - t) / ((double)match))
                / 3.0;
    }

    // Jaro Winkler Similarity
    public static double jaro_Winkler(String s1, String s2)
    {
        double jaro_dist = jaro_distance(s1, s2);
        System.out.println("JArrow Distance :"+jaro_dist);
        // If the jaro Similarity is above a threshold
        if (jaro_dist > 0.7)
        {

            // Find the length of common prefix
            int prefix = 0;

            for (int i = 0;
                 i < Math.min(s1.length(), s2.length()); i++)
            {

                // If the characters match
                if (s1.charAt(i) == s2.charAt(i))
                    prefix++;

                    // Else break
                else
                    break;
            }
            System.out.println("prefix :"+prefix);

            // Maximum of 4 characters are allowed in prefix
            prefix = Math.min(4, prefix);
            System.out.println("Minum prefix :"+prefix);

            // Calculate jaro winkler Similarity
            jaro_dist += 0.1 * prefix * (1 - jaro_dist);

        }
        System.out.println("jaro_dist :"+jaro_dist);

        return jaro_dist;
    }

}

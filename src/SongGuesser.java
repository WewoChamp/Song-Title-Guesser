/**
 * @author Folawewo Osibo
 * @version 2024-03-07
 * <p>
 * Guesses the name of a song from its lyrics.
 * <p>
 * Uses a HashMap to map the unique strings from the user's input to an
 * ArrayList whose first element is the length of the said string, and whose
 * second/final element is the number of appearances of the unique string in
 * the list of strings inputted by the user. It then works out which unique
 * string appeared most times, and in the case that more than one unique string
 * appears the most times, it works out which string is shortest.
 */

import java.util.ArrayList;
import java.util.HashMap;

public class SongGuesser {
    /**
     * Calculates the number of times a string appears in an array of strings.
     * @param lyric The String whose number of occurrences is unknown.
     * @param lyrics The String array to look the lyric up in.
     * @return Of type int the number of times lyric appears in lyrics.
     */
    public static int numberOfAppearancesOfLyric(String lyric, String[] lyrics){
        int appearancesOfLyric = 0;

        for(String lineOfLyrics : lyrics){
            if(lyric.equals(lineOfLyrics)) appearancesOfLyric++;
        }
        return appearancesOfLyric;
    }

    /**
     * Creates a HashMap that associates each unique String (in an array of
     * Strings) with an ArrayList whose first element is the length of the
     * String and whose second/final element is the number of occurrences of
     * the String in the array.
     * @param lyrics The array of Strings for details to be provided.
     * @return Of type HashMap<String, ArrayList<Integer>> for the Strings and
     * their details.
     */
    public static HashMap<String, ArrayList<Integer>> defineLyricDetails(String[] lyrics) {
        HashMap<String, ArrayList<Integer>> lyricDetails = new HashMap<>();

        for (String lyric : lyrics) {
            lyricDetails.put(lyric, new ArrayList<>());
            lyricDetails.get(lyric).add(lyric.length());
            lyricDetails.get(lyric).add(numberOfAppearancesOfLyric(lyric,
                    lyrics));
        }
        return lyricDetails;
    }

    /**
     * Gets the number of appearances of a String as defined in the HashMap
     * mapping the String to its length and number of occurrences.
     * @param lyric The String whose number of appearances is to be known.
     * @param lyricDetails The HashMap containing the lyric's details.
     * @return Of type int the number of appearances of lyric as defined
     * in the lyricDetails.
     */
    public static int getAppearances(String lyric, HashMap<String,
            ArrayList<Integer>> lyricDetails) {
        if(!(lyricDetails.keySet().contains(lyric)))/*in case the key doesn't
         exist in the HashMap*/ {
            System.err.printf("%s does not exist as a key in %s", lyric, lyricDetails);
        }

        return lyricDetails.get(lyric).getLast();
    }
    /**
     * Gets the length of a String as defined in the HashMap
     * mapping the String to its length and number of occurrences.
     * @param lyric The String whose length is to be known.
     * @param lyricDetails The HashMap containing the lyric's details.
     * @return Of type int the length of lyric as defined
     * in the lyricDetails.
     */
    public static int getLength(String lyric, HashMap<String,
            ArrayList<Integer>> lyricDetails) {
        if(!(lyricDetails.keySet().contains(lyric)))/*in case the key doesn't
         exist in the HashMap*/ {
            System.err.printf("%s does not exist as a key in %s", lyric, lyricDetails);
        }

        return lyricDetails.get(lyric).getFirst();
    }

    /**
     * Gets the String that appears the most times in an array of Strings,
     * and if two or more unique Strings appear the most times, the gets the
     * shortest of the two or more Strings.
     * @param lyrics The array of Strings to search for shortest out of the
     *               most occurring Strings
     * @return Of type String for the shortest out of the most occurring Strings
     */
    public static String guessTitle(String[] lyrics){
        HashMap<String, ArrayList<Integer>> lyricDetails =
                defineLyricDetails(lyrics);
        int maxAppearancesOfUniqueLyric = 0;
        int shortestLengthBetweenMostRecurringLyrics = Integer.MAX_VALUE;
        /*set to the largest possible int value, so that the initial value
        of the shortest length can be defined*/
        String songTitle = "";/*in case an empty array is passed display empty
        title*/

        for(String lyric : lyricDetails.keySet()){
            if(getAppearances(lyric, lyricDetails) > maxAppearancesOfUniqueLyric)
                maxAppearancesOfUniqueLyric = getAppearances(lyric, lyricDetails);
        }

        for(String lyric : lyricDetails.keySet()){
            if (getAppearances(lyric, lyricDetails) == maxAppearancesOfUniqueLyric
                    && getLength(lyric, lyricDetails) < shortestLengthBetweenMostRecurringLyrics) {
                shortestLengthBetweenMostRecurringLyrics = getLength(lyric, lyricDetails);
                songTitle = lyric;
            }
        }

        return songTitle;
    }

    public static void main(String[] args) {
        String songTitle = guessTitle(args);

        System.out.printf("The song title is, \"%s\".", songTitle);
    }
}

package com.example.project;
import java.util.ArrayList;


public class Game{
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        // intalizes and get hand rankings
        int p1HandRank = Utility.getHandRanking(p1Hand);
        int p2HandRank = Utility.getHandRanking(p2Hand);
        // intalizes their compared value 
        // positive for > 
        // negative for < 
        // zero for ==
        int compareValue = Utility.compareHandRanking(p1HandRank,p2HandRank);
        // checks not tie
        if (compareValue != 0) {
            // win to string uses value returned by compareHandRanking 
            // returns return string for who won
            return Utility.winToString(compareValue);
        }
        else {
            // card comparator object
            CardComparator c = new CardComparator();
            // compares the ranks of the highest card in each persons hand
            // uses win to string to convert to return string
            return Utility.winToString(c.compareRank(p1.getHandHighest(),p2.getHandHighest()));
        }
    }

    public static void play(){ //simulate card playing
        
    }
        
        

}
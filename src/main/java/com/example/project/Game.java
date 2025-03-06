package com.example.project;
import java.util.ArrayList;


public class Game{
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        int p1HandRank = Utility.getHandRanking(p1Hand);
        int p2HandRank = Utility.getHandRanking(p2Hand);
        int compareValue = Utility.compareHandRanking(p1HandRank,p2HandRank);
        if (compareValue != 0) {
            return Utility.winToString(compareValue);
        }
        else {
            CardComparator c = new CardComparator();
            return Utility.winToString(c.compareRank(p1.getHandHighest(),p2.getHandHighest()));
        }
    }

    public static void play(){ //simulate card playing
        
    }
        
        

}
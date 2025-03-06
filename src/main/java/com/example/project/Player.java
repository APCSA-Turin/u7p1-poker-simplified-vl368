package com.example.project;
import java.util.ArrayList;


public class Player{
    // instance variables
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    // public constructor
    public Player(){
        hand = new ArrayList<>();
    }

    // getter methods
    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    // public methods
    // adds cards to the hand
    public void addCard(Card c){
        hand.add(c);
    }

    // plays best hand
    public String playHand(ArrayList<Card> communityCards){      
        // initalizes variable(s) + object(s)
        String bestHand = "Nothing";
        CardComparator compare = new CardComparator();
        ArrayListHelper arrList = new ArrayListHelper();
        // initalize + sort allCards
        allCards = new ArrayList<>();
        allCards.addAll(hand);
        allCards.addAll(communityCards);
        sortAllCards();
        
        // uses ArrayListHelper class; code at the bottom of this class
        // determines if high card is present
        if (!arrList.containsCard(allCards.get(allCards.size()-1), communityCards)) {
            bestHand = "High Card";
        }
        // determines 1 pair
        if (arrList.containsInt(2,findRankingFrequency())) {
            bestHand = "A Pair";
        }
        // determines 2 pair
        if (arrList.frequency(2,findRankingFrequency()) >= 2) {
            bestHand = "Two Pair";
        }
        // determines 3 of a kind
        if (arrList.containsInt(3,findRankingFrequency())) {
            bestHand = "Three of a Kind";
        }
        // determines straight
        boolean straight = arrList.mostInOrderCards(allCards);
        if (straight) {
            bestHand = "Straight";
        }
        // determines flush
        boolean flush = false;
        int flushSuit = 0;
        if (arrList.maxValue(findSuitFrequency()) >= 5) {
            bestHand = "Flush";
            for (int i = 0; i < 4; i++) {
                if(findSuitFrequency().get(i) == arrList.maxValue(findSuitFrequency())) {
                    flushSuit = i;
                }
            }
            flush = true;
        }
        if (arrList.containsInt(3,findRankingFrequency()) && arrList.containsInt(2,findRankingFrequency())) {
            bestHand = "Full House";
        }
        if (arrList.containsInt(4,findRankingFrequency())) {
            bestHand = "Four of a Kind";
        }
        // find straight flush
        if (straight) {
            int sameFlush = 1;
            for (int i = 1; i < allCards.size(); i++) {
                if (compare.compareSuit(allCards.get(i-1), allCards.get(i))) {
                    sameFlush++;
                }
                else {
                    break;
                }
            }
            if (sameFlush == 5) {
                bestHand = "Straight Flush";
            }
        }
        int[] royalFlush = {10,11,12,13,14};
        if (straight && flush) {
            for (int i = 0; i < royalFlush.length; i++) {
                for (Card card : allCards) {
                    if (card.getRankValue() == royalFlush[i] && card.getSuitValue() == flushSuit) {
                        royalFlush[i] = 0;
                    }
                }
            }
            boolean isRoyalFlush = true;
            for (int i = 0; i < royalFlush.length; i++) {
                if (!(royalFlush[i] == 0)) {
                    isRoyalFlush = false;
                }
            }
            if (isRoyalFlush) {
                bestHand = "Royal Flush";
            }
        }
        return bestHand;
    }

    // sorts allCards
    public void sortAllCards(){
        // cardcomparator class in this folder
        CardComparator c = new CardComparator();
        for (int i = 1; i < allCards.size(); i++) {
            Card card = allCards.get(i);
            int k = i;
            while (k > 0 && c.compare(allCards.get(k-1),card) > 0) {
                allCards.set(k, allCards.get(k-1));
                k--;
            }
            allCards.set(k, card);
        }
    } 
    
    public Card getHandHighest() {
        Card highest = hand.get(0);
        CardComparator c = new CardComparator();
        if (c.compare(hand.get(1), highest) > 0) {
            highest = hand.get(1);
        }
        return highest;
    }

    // returns list with rank frequency in order of ranks in ranks arraylist
    public ArrayList<Integer> findRankingFrequency(){
        // initalizes rankFreq with size of 13 with 0s
        ArrayList<Integer> rankFreq = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            rankFreq.add(0);
        }
        for (Card card : allCards) {
            // uses rankValue for index and adds one with getter method
            int rankValue = card.getRankValue() - 2;
            rankFreq.set(rankValue, rankFreq.get(rankValue)+1);
        }
        return rankFreq; 
    }

    // returns list with suit frequency in order of suits in suits arraylist
    public ArrayList<Integer> findSuitFrequency(){
        // initalizes suitFreq with size of 4 with 0s
        ArrayList<Integer> suitFreq = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            suitFreq.add(0);
        }
        for (Card card : allCards) {
            // uses suitValue for index and adds one with getter method
            int suitValue = card.getSuitValue();
            suitFreq.set(suitValue,suitFreq.get(suitValue)+1);
        }
        return suitFreq; 
    }

    // string version
    @Override
    public String toString(){
        return hand.toString();
    }


    class ArrayListHelper {
        public boolean containsInt(int value, ArrayList<Integer> arrList) {
            for (Integer element : arrList) {
                if (element == value) {
                    return true;
                }
            }
            return false;
        }
    
        public boolean containsCard(Card card, ArrayList<Card> arrList) {
            for (Card element : arrList) {
                if (card.equals(element)) {
                    return true;
                }
            }
            return false;
        }
    
        public int maxValue(ArrayList<Integer> arrList) {
            int max = arrList.get(0);
            for (int element : arrList) {
                if (element > max) {
                    max = element;
                }
            }
            return max;
        }
    
        public int frequency(int value, ArrayList<Integer> arrList) {
            int count = 0;
            for (Integer element : arrList) {
                if (element == value) {
                    count++;
                }
            }
            return count;
        }
    
        public boolean mostInOrderCards(ArrayList<Card> arrList) {
            boolean consecutive = true;
            int previousRank = arrList.get(0).getRankValue();
            for (int i = 1; i < arrList.size(); i++) {
                if (arrList.get(i).getRankValue() == previousRank + 1) {
                    previousRank = arrList.get(i).getRankValue();
                }
                else {
                    consecutive = false;
                }
            }
            return consecutive;
        }
    }
    

}

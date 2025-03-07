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
        // arraylisthelper class at the bottom of this class
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
        boolean straight = arrList.isStraight(allCards);
        if (straight) {
            bestHand = "Straight";
        }
        // determines flush
        // if there is a suit frequency element == 5 there is a flush
        boolean flush = arrList.maxValue(findSuitFrequency()) == 5;
        if (flush) {
            bestHand = "Flush";
        }
        // determines full house 
        // if there is three of a kind + a pair in ranking freq there is a full house
        if (arrList.containsInt(3,findRankingFrequency()) && arrList.containsInt(2,findRankingFrequency())) {
            bestHand = "Full House";
        }
        // determine four of a kind
        if (arrList.containsInt(4,findRankingFrequency())) {
            bestHand = "Four of a Kind";
        }
        // find straight or royal flush
        if (straight && flush) {
            // condition already guarentees straight flush
            bestHand = "Straight Flush";
            // determines straight flush,
            // last (highest) card should be the ace
            // first (lowest) card should be the 10
            if (allCards.get(0).getRankValue() == 10 && allCards.get(allCards.size()-1).getRankValue() == 14) {
                bestHand = "Royal Flush";
            }
        }
        // returns string
        return bestHand;
    }

    // sorts allCards
    public void sortAllCards(){
        // cardcomparator class in this folder
        CardComparator c = new CardComparator();
        for (int i = 1; i < allCards.size(); i++) {
            // initalizes for insertion sort algorithm
            Card card = allCards.get(i);
            // k represents the index the card should go
            int k = i;
            // stops when k > 0 or next card isn't bigger than our current card
            while (k > 0 && c.compare(allCards.get(k-1),card) > 0) {
                allCards.set(k, allCards.get(k-1));
                k--;
            }
            allCards.set(k, card);
        }
    } 
    
    // method for highest card in hand
    public Card getHandHighest() {
        Card highest = hand.get(0);
        CardComparator c = new CardComparator();
        // only one other card, if larger by rank (maybe and suit) 
        // highest gets replaced
        if (c.compare(hand.get(1), highest) > 0) {
            highest = hand.get(1);
        }
        // returns the card
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

    // arraylist helper class
    class ArrayListHelper {
        // checks if a value is found in the array list
        public boolean containsInt(int value, ArrayList<Integer> arrList) {
            // enhanced for loop to check for target value
            for (Integer element : arrList) {
                if (element == value) {
                    // early return
                    return true;
                }
            }
            return false;
        }
    
        // checks if a card object exists in an array list
        public boolean containsCard(Card card, ArrayList<Card> arrList) {
            // enhanced for loop
            for (Card element : arrList) {
                if (card.equals(element)) {
                    // early return
                    return true;
                }
            }
            return false;
        }
    
        // finds the max integer in an array list
        public int maxValue(ArrayList<Integer> arrList) {
            int max = arrList.get(0);
            // enhanced for loop
            for (int element : arrList) {
                // replaces max variable if element is bigger
                if (element > max) {
                    max = element;
                }
            }
            return max;
        }
    
        // returns the frequency of an integer in an arraylist
        public int frequency(int value, ArrayList<Integer> arrList) {
            int count = 0;
            // enhanced for loop
            for (Integer element : arrList) {
                // counts every mached value
                if (element == value) {
                    count++;
                }
            }
            return count;
        }
        
        // returns if there is a straight
        public boolean isStraight(ArrayList<Card> arrList) {
            boolean straight = true;
            int previousRank = arrList.get(0).getRankValue();
            for (int i = 1; i < arrList.size(); i++) {
                if (arrList.get(i).getRankValue() == previousRank + 1) {
                    previousRank = arrList.get(i).getRankValue();
                }
                else {
                    // if any rank is not in the next order
                    // boolean turns false and cannot be true again
                    straight = false;
                }
            }
            return straight;
        }
    }
    

}

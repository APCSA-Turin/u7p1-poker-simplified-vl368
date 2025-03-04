package com.example.project;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


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
        // initalize + sort allCards
        allCards = new ArrayList<Card>();
        for (Card c : hand) {
            allCards.add(c);
        }
        for (Card c : communityCards) {
            allCards.add(c);
        }
        sortAllCards();
        
        // determines if high card is present
        if (!communityCards.contains(allCards.getLast())) {
            bestHand = "High Card";
        }
        if (findRankingFrequency().contains(2)) {
            bestHand = "A Pair";
        }
        ArrayList<Integer> rankFreq = findRankingFrequency();
        rankFreq.remove(2);
        if (rankFreq.contains(2)) {
            bestHand = "Two Pair";
        }
        if (findRankingFrequency().contains(3)) {
            bestHand = "Three of a Kind";
        }
        // find straight
        int maxStreak = 0;
        int consecutive = 0;
        int startOfStreak = 0;
        int previousRank = allCards.get(0).getRankValue();
        for (int i = 0; i < allCards.size(); i++) {
            if (allCards.get(i).getRankValue() == previousRank) {
                consecutive++;
            }
            else {
                if (consecutive > maxStreak) {
                    maxStreak = consecutive;
                }
                consecutive = 1;
                startOfStreak = i;
            }
            previousRank = allCards.get(i).getRankValue();
        }
        boolean straight = false;
        if (maxStreak >= 5) {
            bestHand = "Straight";
            straight = true;
        }
        if (Collections.max(findSuitFrequency()) >= 5) {
            bestHand = "Flush";
        }
        ArrayList<Integer> fullHouse = new ArrayList<>();
        fullHouse.add(2);
        fullHouse.add(3);
        if (allCards.containsAll(fullHouse)) {
            bestHand = "Full House";
        }
        if (findRankingFrequency().contains(4)) {
            bestHand = "Four of a Kind";
        }
        // find straight flush
        
        return bestHand;
    }

    // sorts allCards
    public void sortAllCards(){
        CardComparator c = new CardComparator();
        for (int i = 0; i < allCards.size(); i++) {
            Card card = allCards.get(i);
            int k = i;
            while (k > 0 && c.compare(allCards.get(k-1),card) > 0) {
                allCards.set(k, allCards.get(k-1));
                k--;
            }
            allCards.set(k, card);
        }
    } 

    // returns list with rank frequency in order of ranks in ranks arraylist
    public ArrayList<Integer> findRankingFrequency(){
        // initalizes rankFreq with size of 13 with 0s
        ArrayList<Integer> rankFreq = new ArrayList<>(Collections.nCopies(13,0));
        for (Card card : allCards) {
            // uses rankValue for index and adds one with getter method
            int rankValue = card.getRankValue() - 2;
            rankFreq.set(rankValue,rankFreq.get(rankValue)+1);
        }
        return rankFreq; 
    }

    // returns list with suit frequency in order of suits in suits arraylist
    public ArrayList<Integer> findSuitFrequency(){
        // initalizes suitFreq with size of 4 with 0s
        ArrayList<Integer> suitFreq = new ArrayList<>(Collections.nCopies(4,0));
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

    // comparator class
    class CardComparator implements Comparator<Card> {
        @Override
        public int compare(Card o1, Card o2) {
            // compares using rank value
            if (o1.getRankValue() > o2.getRankValue()) {
                return 1;
            }
            // if same rank value
            else if (o1.getRankValue() == o2.getRankValue()) {
                // compares using suit value
                if (o1.getSuitValue() > o2.getSuitValue()) {
                    return 1;
                }
                else {
                    return -1;
                }
            }
            else {
                return -1;
            }
        }
    }
}

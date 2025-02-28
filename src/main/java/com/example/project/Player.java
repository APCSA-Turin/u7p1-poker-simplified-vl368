package com.example.project;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){
        allCards = new ArrayList<Card>();      
        String bestHand = "Nothing";
        for (Card c : hand) {
            allCards.add(c);
        }
        for (Card c : communityCards) {
            allCards.add(c);
        }
        SortCards(allCards);
        return bestHand;
    }

    public void SortCards(ArrayList<Card> cards){
        Collections.sort(cards, new CardComparator());
        // for (int i = 0; i < cards.size(); i++) {
        //     Card card = cards.get(i);
        //     int idx = i;
        //     boolean better = cards.get(idx-1).getRank() > card.getRank();
        //     while (idx > 0 && (better)) {
                
        //         cards.set(idx, cards.get(idx-1));
        //         idx--;
        //     }
        //     cards.set(idx, card);
        // }
    } 

    public ArrayList<Integer> findRankingFrequency(){
        return new ArrayList<>(); 
    }

    public ArrayList<Integer> findSuitFrequency(){
        return new ArrayList<>(); 
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }

    class CardComparator implements Comparator<Card> {
        @Override
        public int compare(Card o1, Card o2) {
            if (o1.getRankValue() > o2.getRankValue()) {
                return 1;
            }
            else if (o1.getRankValue() == o2.getRankValue()) {
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

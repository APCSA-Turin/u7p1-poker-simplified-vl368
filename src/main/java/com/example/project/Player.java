package com.example.project;
import java.util.ArrayList;


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
        for (Card c : hand) {
            allCards.add(c);
        }
        for (Card c : communityCards) {
            allCards.add(c);
        }
        return "Nothing";
    }

<<<<<<< HEAD
    public void sortAllCards(){} 
=======
    public void SortCards(ArrayList<Card> cards){
        for (int i = 0; i < cards.size(); i++) {

        }
    } 
>>>>>>> 28d8570 (commit 1)

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




}

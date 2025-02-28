package com.example.project;
import java.util.ArrayList;
import java.util.Collections;

public class Deck{
    private ArrayList<Card> cards;

    public Deck(){
        cards = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public  void initializeDeck(){ //hint.. use the utility class
        String[] suits = Utility.getSuits();
        String[] ranks = Utility.getRanks();
        for (int i = 0; i < ranks.length; i++) {
            for (int k = 0; k < suits.length; k++) {
                cards.add(new Card(ranks[i], suits[k]));
            }
        }
    }

    public  void shuffleDeck(){ //You can use the Collections library or another method. You do not have to create your own shuffle algorithm
        Collections.shuffle(cards);
    }

    public  Card drawCard(){
        if (isEmpty()) {
            return cards.remove(0);
        }
        return null;
    }

    public  boolean isEmpty(){
        return cards.size() > 0;
    }

   


}
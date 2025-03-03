package com.example.project;
import java.util.ArrayList;
import java.util.Collections;

public class Deck{
    // instance variables
    private ArrayList<Card> cards;

    // constructor
    public Deck(){
        cards = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    // getter method(s)
    public ArrayList<Card> getCards(){
        return cards;
    }

    // initalizes deck with all 52 cards
    public  void initializeDeck(){ //hint.. use the utility class
        // initalizes arrays
        String[] suits = Utility.getSuits();
        String[] ranks = Utility.getRanks();
        // uses nested loop to add all possible cards
        for (int i = 0; i < ranks.length; i++) {
            for (int k = 0; k < suits.length; k++) {
                cards.add(new Card(ranks[i], suits[k]));
            }
        }
    }

    // randomly shuffles cards
    public  void shuffleDeck(){ //You can use the Collections library or another method. You do not have to create your own shuffle algorithm
        Collections.shuffle(cards);
    }

    // takes a card from deck if deck is not empty return 
    // and remove drawn card, returns null if unsuccessful
    public Card drawCard(){
        if (!isEmpty()) {
            return cards.remove(0);
        }
        return null;
    }

    // checks if cards arraylist is empty
    // returns boolean
    public boolean isEmpty(){
        return cards.isEmpty();
    }

}
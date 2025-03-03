package com.example.project;
import java.util.ArrayList;

public class Card{
    // instance variables
    private String rank;
    private String suit;

    // constuctor
    public Card(String rank, String suit){
        this.rank = rank;
        this.suit = suit;
    }

    // getter methods
    public String getRank(){return rank;}
    public String getSuit(){return suit;}
    // uses utility to get values of its rank/suit
    public int getRankValue(){return Utility.getRankValue(rank);}
    public int getSuitValue(){return Utility.getSuitValue(suit);}
    
    // to String method
    @Override
    public String toString(){
        return rank + " of " + suit;
    }

}
package com.example.project;
import java.util.ArrayList;

public class Card{
    private String rank;
    private String suit;

    public Card(String rank, String suit){
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank(){return rank;}
    public String getSuit(){return suit;}

    public int getRankValue(){return Utility.getRankValue(rank);}
    public int getSuitValue(){return Utility.getSuitValue(suit);}
    
    @Override
    public String toString(){
        return rank + " of " + suit;
    }

}
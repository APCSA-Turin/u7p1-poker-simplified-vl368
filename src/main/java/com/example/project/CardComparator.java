package com.example.project;

    // comparator class
    class CardComparator{

        public int compare(Card o1, Card o2) {
            // compares using rank value
            // positive return if o1 > o2
            // negative return if o1 < o2
            // o1 == 02 is not possible unique deck
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
        
        // compares the suits of two cards
        public boolean compareSuit(Card o1, Card o2) {
            return o1.getSuitValue() == o2.getSuitValue();
        }

        // compares the ranks of the two cards
        // o1 == o2 ranks is possible
        public int compareRank(Card o1, Card o2) {
            // compares rank values
            if (o1.getRankValue() > o2.getRankValue()) {
                return 1;
            }
            // if same rank value
            else if (o1.getRankValue() == o2.getRankValue()) {
                return 0;
            }
            else {
                return -1;
            }
        }
    }
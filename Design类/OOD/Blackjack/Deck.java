package Blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

    List<Card> cardList;

    public Deck() {
        this.cardList = new ArrayList<>();
        shuffleDeck(cardList);
    }

    public void shuffleDeck(List<Card> cardList) {
        cardList.clear();
        for (int i = 1; i <= 13; i++) {
            for (Card.TYPE type: Card.TYPE.values()) {
                cardList.add(new Card(type, i));
            }
        }
    }

    // put in DAO
    public Card getNextCard() {
        int count = this.cardList.size();
        Random rand = new Random();
        int rx = rand.nextInt(count);
        Card res = this.cardList.get(rx);
        if (rx != count - 1) {
            // swap the last one
            Card last = this.cardList.get(count - 1);
            this.cardList.set(rx, last);
            this.cardList.remove(count - 1);
        }
        return res;
    }

    public boolean isEmpty() {
        return cardList.isEmpty();
    }
}

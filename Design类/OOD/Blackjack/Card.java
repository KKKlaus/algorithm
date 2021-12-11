package Blackjack;

public class Card {

    public enum TYPE {
        HEART,
        SPADE,
        DIAMOND,
        CLUB
    }

    TYPE type;
    int value;

    public Card(TYPE type, int value) {
        this.type = type;
        this.value = value;
        if (this.value > 10) {
            this.value = 10;
        }
    }
}

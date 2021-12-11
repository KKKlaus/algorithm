package Blackjack;

public class Dealer extends Player{

    public Dealer () {
        super();
    }

    @Override
    public ACTION getNextAction(Deck deck) {
        if (this.getBestScore() > 17) {
            return ACTION.STAND;
        } else {
            this.addCard(deck);
            return ACTION.HIT;
        }
    }

}

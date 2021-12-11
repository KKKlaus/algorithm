package Blackjack;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    public enum ACTION {
        HIT,
        STAND
    }

    String name;
    int amount;
    int bet;

    private List<Card> cards;

    public Player() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Deck deck) {
        Card card = deck.getNextCard();
        this.cards.add(card);
    }

    // since A can be regarded as 1 or 11
    // 这里是最难的
    public List<Integer> getScore() {
        List<Integer> totals = new ArrayList<>();
        totals.add(0);

        // 这里用两个list来实现A为1或11的可能
        for (Card card : this.cards) {
            List<Integer> newTotals = new ArrayList<>();
            for (int score : totals) {
                newTotals.add(card.value + score);
                if (card.value == 1) {
                    newTotals.add(11 + score);
                }
            }
            totals = newTotals;
        }
        return totals;
    }

    public int getBestScore() {
        List<Integer> scoreList = getScore();
        int best = 0;
        for (int n : scoreList) {
            if (n > 21) continue;
            best = Math.max(best, n);
        }
        return best;
    }

    public ACTION getNextAction(Deck deck) {
        return ACTION.STAND;
    }
}

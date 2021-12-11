package Blackjack;

public class Game {

    private Player player;
    private Dealer dealer;

    public Game() {
        this.player = new Dealer();
        // 懒得改了。这里应该把player作为abstract user class，设置nextaction 为abstract function，然后player 也extends它
        this.dealer = new Dealer();
    }

    public String start() {
        //player.placeBet(getBetFromUI());
        Deck deck = new Deck();
        // init 2 cards for each player
        dealer.addCard(deck);
        dealer.addCard(deck);
        player.addCard(deck);
        player.addCard(deck);

        while(true) {
            dealer.getNextAction(deck); // automatically
            Player.ACTION action = player.getNextAction(deck); // actually get from UI button
            if (action == Player.ACTION.STAND) {
                break;
            }
        }
        return getGameResult(player, dealer);
    }

    public String getGameResult(Player player, Dealer dealer) {
        if (player.getBestScore() >= dealer.getBestScore()) {
            return "Player Win!";
        } else {
            return "Dealer Win!";
        }
    }

    public static void main(String[] args) {

    }
}

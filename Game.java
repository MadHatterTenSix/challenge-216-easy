/**
 *
 * @author __MadHatter (alias used on https://www.reddit.com/r/dailyprogrammer)
 */

/* Game.java */

import java.util.ArrayList;
import java.util.Scanner;

public final class Game {

  private final int MIN_PLAYERS = 2;
  private final int MAX_PLAYERS = 8;

  private Deck deck;
  private ArrayList<Player> players;  /* this list includes CPU players */
  private ArrayList<Card> boardCards; /* community cards */
  private Scanner in;

  public Game() {
    deck       = new Deck();
    players    = new ArrayList<>();
    boardCards = new ArrayList<>();
    in         = new Scanner(System.in);
  }

  public void start() {

    int numberOfPlayers;

    /* Read number of players. */
    numberOfPlayers = 0;
    while (numberOfPlayers < MIN_PLAYERS || numberOfPlayers > MAX_PLAYERS) {
      System.out.print("How many players ("
        + MIN_PLAYERS + "-"
        + MAX_PLAYERS + ") ? ");
      numberOfPlayers = in.nextInt();
    }
    System.out.println("");

    /* Add human player. */
    addPlayer(Player.Type.HUMAN, "");

    /* Add CPU players. */
    for (int i = 1; i < numberOfPlayers-1; i++) {
      addPlayer(Player.Type.CPU, "");
    }

    /* Initialize deck. */
    deck.initializeDeck();
    deck.shuffle();

    /* Deal cards to players and display. */
    for (int i = 0; i < players.size(); i++) {
      dealCard(i);
      dealCard(i);
    }
    showPlayersCards();
    System.out.println();

    /* Flop */
    System.out.print("Flop:  ");
    deck.burnTopCard();
    dealCard();
    dealCard();
    dealCard();
    showBoardCards();

    /* Turn */
    System.out.print("Turn:  ");
    deck.burnTopCard();
    dealCard();
    showBoardCards();

    /* River */
    System.out.print("River: ");
    deck.burnTopCard();
    dealCard();
    showBoardCards();

  }

  public void addPlayer(Player.Type type, String name) {
    String newName = name;
    if (newName.equalsIgnoreCase("") && type == Player.Type.CPU) {
      newName = "[CPU] Player " + (players.size() + 1);
    }
    else if (newName.equalsIgnoreCase("") && type == Player.Type.HUMAN) {
      newName = "Player " + (players.size() + 1);
    }
    players.add(new Player(type, newName));
  }

  public void showBoardCards() {
    for (int i = 0; i < boardCards.size(); i++) {
      System.out.print("" + boardCards.get(i).toString() + "  ");
    }
    System.out.println();
  }

  public void showPlayersCards() {
    for (int i = 0; i < players.size(); i++) {
      players.get(i).showHand();
    }
  }

  private void dealCard() {
    Card newCard = deck.drawCard();
    if (newCard != null) {
      boardCards.add(newCard);
    }
  }

  private void dealCard(int player) {
    Card newCard = deck.drawCard();
    if (newCard != null) {
      players.get(player).receiveCard(newCard);
    }
  }

}

/**
 *
 * @author __MadHatter (alias used on https://www.reddit.com/r/dailyprogrammer)
 */

/* Game.java */

import java.util.ArrayList;
import java.util.Scanner;

public final class Game {

  private static final int MIN_PLAYERS = 2;
  private static final int MAX_PLAYERS = 8;

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
    players.clear();
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
    for (int i = 1; i < numberOfPlayers; i++) {
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
    printPlayersCards();
    System.out.println();

    /* Flop */
    System.out.print("Flop:  ");
    deck.burnTopCard();
    for (int i = 0; i < 3; i++) {
      dealCard();
    }
    printBoardCards();

    /* Turn */
    System.out.print("Turn:  ");
    deck.burnTopCard();
    dealCard();
    printBoardCards();

    /* River */
    System.out.print("River: ");
    deck.burnTopCard();
    dealCard();
    printBoardCards();

  }

  public void addPlayer(Player.Type type, String name) {
    String newName = name;
    if (!newName.equalsIgnoreCase("") && type == Player.Type.CPU) {
      newName = "[CPU] " + newName;
    }
    else if (newName.equalsIgnoreCase("") && type == Player.Type.CPU) {
      newName = "[CPU] Player " + (players.size() + 1);
    }
    else if (newName.equalsIgnoreCase("") && type == Player.Type.HUMAN) {
      newName = "Player " + (players.size() + 1);
    }
    players.add(new Player(type, newName));
  }

  public void removePlayer(int id) {
    if (id >= 0 && id < players.size()) {
      players.remove(id);
    }
  }

  public void removePlayer(String name) {
    for (int i = 0; i < players.size(); i++) {
      if (players.get(i).getName().equalsIgnoreCase(name)) {
        players.remove(i);
        break;
      }
    }
  }

  public void printBoardCards() {
    String msg = "";
    for (int i = 0; i < boardCards.size(); i++) {
      msg += boardCards.get(i).toString() + "  ";
    }
    System.out.println(msg);
  }

  public void printPlayersCards() {
    for (int i = 0; i < players.size(); i++) {
      players.get(i).printHand();
    }
  }

  /* Deal community card. */
  private void dealCard() {
    Card newCard = deck.drawCard();
    if (newCard != null) {
      boardCards.add(newCard);
    }
  }

  /* Deal card to specific player. */
  private void dealCard(int player) {
    Card newCard = deck.drawCard();
    if (newCard != null) {
      players.get(player).receiveCard(newCard);
    }
  }

}

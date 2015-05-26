/**
 *
 * @author __MadHatter (alias used on https://www.reddit.com/r/dailyprogrammer)
 */

/* Player.java */

import java.util.ArrayList;

public class Player {

  public static enum Type {
    CPU,
    HUMAN
  }

  private String name;
  private Type type;
  private ArrayList<Card> hand;

  public Player(Type type, String name) {
    hand      = new ArrayList<>();
    this.type = type;
    this.name = name;
  }

  public String getName()
    { return name; }

  public Type getType()
    { return type; }

  public ArrayList<Card> getHand()
    { return hand; }

  public void receiveCard(Card card) {
    if (card != null) {
      hand.add(card);
    }
  }

  public void showHand() {
    String msg = name + "'s cards: ";
    for (int i = 0; i < hand.size(); i++) {
      msg += hand.get(i).toString() + "  ";
    }
    System.out.println(msg);
  }

}

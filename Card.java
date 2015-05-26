/**
 *
 * @author __MadHatter (alias used on https://www.reddit.com/r/dailyprogrammer)
 */

/* Card.java */

public final class Card {

  public static final String SUITS[] = {
    "UNKNOWN_SUIT",
    "♥", "♣", "♦", "♠"
  };

  public static final String WORD_VALUES[] = {
    "UNKNOWN_WORD_VALUE", /* 0 value is meaningless */
    "UNKNOWN_WORD_VALUE", /* 1 (ace) value moved to highest card */
    "2", "3", "4", "5",
    "6", "7", "8", "9", "10",
    "J", "Q", "K", "A"
  };

  private int val;
  private int suit;

  public Card(int val, int suit) {

    if (val <= 0 || val >= WORD_VALUES.length)
      { this.val = 0; }
    else
      { this.val  = val; }

    if (suit <= 0 || suit >= SUITS.length)
      { this.suit = 0; }
    else
      { this.suit = suit; }

  }

  public int getSuit()
    { return suit; }

  public int getValue()
    { return val; }

  public String valueToString()
    { return WORD_VALUES[val]; }

  public String suitToString()
    { return SUITS[suit]; }

  @Override
  public String toString()
    { return ("" + suitToString() + valueToString()); }

}

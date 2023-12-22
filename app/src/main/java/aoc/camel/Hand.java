package aoc.camel;

import java.util.Arrays;
import java.util.HashMap;

public class Hand implements Comparable<Hand> {
    private final int bid;
    private final int[] cards;
    private final HandType handType;
    private long compoundValue;

    public Hand(String hand, int bid){
        this.bid = bid;
        this.cards = parseHand(hand);
        this.handType = findHandType(this.cards);
    }

    private int[] parseHand(String hand){
        var cards = new int[5];
        for (int i = 0; i < hand.length(); i++) {
            var cardValue = cardToValue(hand.charAt(i));
            cards[i] = cardValue;
            cardValue = cardValue == 11 ? 1 : cardValue + 1; //make J the weakest card
            compoundValue += (long) (cardValue * 1_00_00_00_00_00_00L / Math.pow(100,i));
        }
        return cards;
    }

    private int cardToValue(char card) {
        if(Character.isDigit(card)) return Character.getNumericValue(card);
        switch (card) {
            case 'A' -> { return 14; }
            case 'K' -> { return 13; }
            case 'Q' -> { return 12; }
            case 'J' -> { return 11; }
            case 'T' -> { return 10; }
            default -> { return 0; }
        }
    }

    private HandType findHandType(int[] cards) {
        HandType hand = null;
        var cardDict = new HashMap<Integer, Long>();
        for(int card : cards){
            if(cardDict.containsKey(card)) continue;
            var count = Arrays.stream(cards).filter(c -> c == card).count();
            if (count == 4) hand = HandType.FOUR_OF_A_KIND;
            if(count == 5) return HandType.FIVE_OF_A_KIND;
            cardDict.put(card, count);
        }
        var foundCards = cardDict.keySet().size();
        var jokerCount = cardDict.getOrDefault(11, 0L);

        if(hand == null) {
            if(foundCards == 5) {
                hand = HandType.HIGH_CARD;
            } else if (foundCards == 4) {
                hand = HandType.PAIR;
            } else if (foundCards == 3) {
                hand = cardDict.containsValue(3L) ? HandType.THREE_OF_A_KIND : HandType.TWO_PAIR;
            } else if (foundCards == 2) {
                hand = HandType.FULL_HOUSE;
            } else {
                hand = HandType.HIGH_CARD;
            }
        }

        return jokerCount > 0 ? applyJoker(hand, jokerCount) : hand;
    }

    private HandType applyJoker(HandType hand, long jokerCount) {
        switch (hand) {
            case FOUR_OF_A_KIND, FULL_HOUSE -> { return HandType.FIVE_OF_A_KIND; }
            case THREE_OF_A_KIND -> { return HandType.FOUR_OF_A_KIND; }
            case TWO_PAIR -> { return jokerCount == 2 ? HandType.FOUR_OF_A_KIND : HandType.FULL_HOUSE; }
            case PAIR -> { return HandType.THREE_OF_A_KIND; }
            case HIGH_CARD -> {return HandType.PAIR; }
            default -> { return hand; }
        }
    }

    public int getBid() { return bid; }
    public int[] getCards() { return cards; }
    public HandType getHandType() { return this.handType; }

    public long getCompoundValue() { return compoundValue; }


    @Override
    public int compareTo(Hand other) {
        var handCompare = this.getHandType().compareTo(other.handType);
        return handCompare != 0 ? handCompare : Long.compare(this.getCompoundValue(), other.getCompoundValue());
    }

    @Override
    public String toString() {
        return Arrays.toString(getCards()) + " (" + getHandType().name() + ")" + " [" + getCompoundValue() + "]";
    }
}

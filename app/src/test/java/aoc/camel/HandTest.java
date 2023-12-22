package aoc.camel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class HandTest {
    Hand highCard;
    Hand pair;
    Hand threeWithJ;
    Hand twoPairWithJ;
    Hand four;
    Hand five;
    Hand fullHouse;

    @BeforeEach
    void buildCards() {
        highCard = new Hand("12345", 684);
        pair = new Hand("32T3K", 765);
        threeWithJ = new Hand("T55J5", 684);
        twoPairWithJ = new Hand("KTJJT", 220);
        four = new Hand("44441", 684);
        five = new Hand("55555", 684);
        fullHouse = new Hand("11222", 684);
    }
    @Test
    void testConstruction() {
        assertThat(highCard.getHandType()).isEqualTo(HandType.HIGH_CARD);
        assertThat(highCard.getCards()).containsExactly(1, 2, 3, 4, 5);
        assertThat(pair.getHandType()).isEqualTo(HandType.PAIR);
        assertThat(pair.getCards()).containsExactly(3, 2, 10, 3, 13);

        assertThat(threeWithJ.getHandType()).isEqualTo(HandType.FOUR_OF_A_KIND);
        assertThat(threeWithJ.getCards()).containsExactly(10, 5, 5, 11, 5);

        assertThat(twoPairWithJ.getHandType()).isEqualTo(HandType.FULL_HOUSE);
        assertThat(twoPairWithJ.getCards()).containsExactly(13, 10, 11, 11, 10);

        assertThat(four.getHandType()).isEqualTo(HandType.FOUR_OF_A_KIND);
        assertThat(four.getCards()).containsExactly(4, 4, 4, 4, 1);
        assertThat(five.getHandType()).isEqualTo(HandType.FIVE_OF_A_KIND);
        assertThat(five.getCards()).containsExactly(5, 5, 5, 5, 5);
        assertThat(fullHouse.getHandType()).isEqualTo(HandType.FULL_HOUSE);
        assertThat(fullHouse.getCards()).containsExactly(1, 1, 2, 2, 2);
    }

    @Test
    void testCompareTo() {
        var list = new ArrayList<>(List.of(fullHouse, pair, highCard, four, threeWithJ, five, twoPairWithJ));
        list.sort(Hand::compareTo);
        assertThat(list).containsExactly(highCard, pair, fullHouse, twoPairWithJ, four, threeWithJ, five);

        var fiveKings = new Hand("KKKKK", 1);
        var bigHouse = new Hand("AKKKA", 1);
        var highPair = new Hand("A1123", 1);
        var mediumPair = new Hand("T1123", 1);
        list.addAll(List.of(fiveKings, mediumPair, highPair, bigHouse));
        list.sort(Hand::compareTo);
        assertThat(list).containsExactly(
                highCard, pair, mediumPair, highPair,
                fullHouse, twoPairWithJ, bigHouse,
                four, threeWithJ, five, fiveKings
        );
    }
}
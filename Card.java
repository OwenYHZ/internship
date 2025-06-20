package pm;

import java.util.*;

public class Card {
    public static void main(String[] args) {
        // 斗地主发牌
        // 花色集合
        List<String> colors = new ArrayList<>();
        colors.add("♥");
        colors.add("♠");
        colors.add("♦");
        colors.add("♣");

        // 牌面数字集合（修正顺序）
        List<String> ranks = new ArrayList<>();
        for (int i = 3; i <= 10; i++) {
            ranks.add("" + i);
        }
        ranks.add("J");
        ranks.add("Q");
        ranks.add("K");
        ranks.add("A");
        ranks.add("2");  // 2应该是最大的数字牌

        // 创建牌库（编号->牌面）
        Map<Integer, String> poker = new HashMap<>();
        int index = 0;

        // 先添加数字牌（3->2）
        for (String rank : ranks) {
            for (String color : colors) {
                poker.put(index, color + rank);
                index++;
            }
        }

        // 添加大小王
        poker.put(52, "小王");
        poker.put(53, "大王");

        // 创建牌编号集合
        List<Integer> cardNumbers = new ArrayList<>();
        for (int i = 0; i < 54; i++) {
            cardNumbers.add(i);
        }

        // 洗牌（打乱编号）
        Collections.shuffle(cardNumbers);

        // 准备玩家和底牌
        List<Integer> player1 = new ArrayList<>();
        List<Integer> player2 = new ArrayList<>();
        List<Integer> player3 = new ArrayList<>();
        List<Integer> bottomCards = new ArrayList<>();

        // 修正发牌逻辑（轮流发牌）
        for (int i = 0; i < cardNumbers.size(); i++) {
            int cardNum = cardNumbers.get(i);

            if (i >= 51) {  // 最后3张是底牌
                bottomCards.add(cardNum);
            } else {
                // 轮流发牌：0->玩家1, 1->玩家2, 2->玩家3
                switch (i % 3) {
                    case 0: player1.add(cardNum); break;
                    case 1: player2.add(cardNum); break;
                    case 2: player3.add(cardNum); break;
                }
            }
        }

        // 对牌排序（按编号升序）
        Collections.sort(player1);
        Collections.sort(player2);
        Collections.sort(player3);
        Collections.sort(bottomCards);

        // 转换编号为实际牌面
        List<String> p1Cards = convertCards(player1, poker);
        List<String> p2Cards = convertCards(player2, poker);
        List<String> p3Cards = convertCards(player3, poker);
        List<String> bottomCardNames = convertCards(bottomCards, poker);

        // 输出结果
        System.out.println("玩家1：" + p1Cards);
        System.out.println("玩家2：" + p2Cards);
        System.out.println("玩家3：" + p3Cards);
        System.out.println("底牌：" + bottomCardNames);
    }

    // 辅助方法：将编号转换为牌面
    private static List<String> convertCards(List<Integer> cardNumbers, Map<Integer, String> poker) {
        List<String> result = new ArrayList<>();
        for (int num : cardNumbers) {
            result.add(poker.get(num));
        }
        return result;
    }
}
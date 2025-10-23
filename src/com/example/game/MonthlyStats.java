package com.example.game;

import java.util.HashMap;
import java.util.Map;

/**
 * 月ごとの成績を管理するクラスです。
 */
public class MonthlyStats {
    private String month;  // "2025-10" のような年月フォーマット
    private Map<String, Integer> counts = new HashMap<>();

    public MonthlyStats(String month) {
        this.month = month;
    }

    /**
     * 指定した結果の件数を加算する
     * @param outcome 勝ち、はずれなど
     * @param count 件数
     */
    public void addCount(String outcome, int count) {
        counts.put(outcome, counts.getOrDefault(outcome, 0) + count);
    }

    @Override
    public String toString() {
        return month + " の成績: " + counts.toString();
    }
}

/**
 * Direction.java
 *
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package com.example.game;

/**
 * あっちむいてホイで使う方向（上・下・左・右）を表す列挙型です。
 * <p>
 * 各方向はラベル（日本語）と対応しています。
 * また、数字から方向を取得するメソッドも持っています。
 * </p>
 *
 * @author FLM
 * @version 1.0.0
 */
public enum Direction {
    UP("上"),
    DOWN("下"),
    LEFT("左"),
    RIGHT("右");

    /** 方向を示す日本語のラベル */
    private final String label;

    /**
     * ラベルを指定して方向を初期化します。
     *
     * @param label 方向の日本語ラベル
     */
    Direction(String label) {
        this.label = label;
    }

    /**
     * 方向の日本語ラベルを取得します。
     *
     * @return ラベル文字列
     */
    public String getLabel() {
        return label;
    }

    /**
     * 数字（0〜3）に対応する方向を返します。
     *
     * @param num 方向を示す整数（0:上, 1:下, 2:左, 3:右）
     * @return 対応するDirectionの列挙値
     * @throws IllegalArgumentException numが0〜3以外の場合にスローされます
     */
    public static Direction fromInt(int num) {
        switch (num) {
            case 0: return UP;
            case 1: return DOWN;
            case 2: return LEFT;
            case 3: return RIGHT;
            default: throw new IllegalArgumentException("0〜3の値を入力してください。");
        }
    }

    /**
     * 方向の選択肢を画面に表示します。
     */
    public static void showOptions() {
        System.out.println("方向を選んでください：");
        System.out.println("0: 上, 1: 下, 2: 左, 3: 右");
    }
}

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
 * また、数字や文字列から {@link Direction} オブジェクトを取得するメソッドも提供します。
 * </p>
 *
 * <p>使用例:</p>
 * <pre>
 *     Direction d = Direction.fromInt(0);   // UP（上）を取得
 *     System.out.println(d.getLabel());     // "上" と表示される
 * </pre>
 *
 * @author FLM
 * @version 1.0.0
 */
public enum Direction {

    /** 上方向 */
    UP("上"),

    /** 下方向 */
    DOWN("下"),

    /** 左方向 */
    LEFT("左"),

    /** 右方向 */
    RIGHT("右");

    /** 方向の日本語ラベル */
    private final String label;

    /**
     * Direction のコンストラクタ。
     *
     * @param label 日本語の方向ラベル（例："上"）
     */
    Direction(String label) {
        this.label = label;
    }

    /**
     * 方向の日本語ラベルを取得します。
     *
     * @return 日本語の方向ラベル
     */
    public String getLabel() {
        return label;
    }

    /**
     * 数字（0〜3）から対応する方向を取得します。
     * <ul>
     *     <li>0 → 上</li>
     *     <li>1 → 下</li>
     *     <li>2 → 左</li>
     *     <li>3 → 右</li>
     * </ul>
     *
     * @param num 方向を表す数字
     * @return 対応する {@link Direction}
     * @throws IllegalArgumentException 0〜3 以外の数値が入力された場合
     */
    public static Direction fromInt(int num) {
        switch (num) {
            case 0:
                return UP;
            case 1:
                return DOWN;
            case 2:
                return LEFT;
            case 3:
                return RIGHT;
            default:
                throw new IllegalArgumentException("0〜3の値を入力してください。");
        }
    }

    /**
     * コンソールに方向の選択肢を表示します。
     * <p>例: 「0: 上, 1: 下, 2: 左, 3: 右」</p>
     */
    public static void showOptions() {
        System.out.println("方向を選んでください：");
        System.out.println("0: 上, 1: 下, 2: 左, 3: 右");
    }

    /**
     * 日本語のラベルから対応する方向を取得します。
     *
     * @param label 方向の日本語ラベル（例："上"）
     * @return 対応する {@link Direction}
     * @throws IllegalArgumentException ラベルが一致しない場合
     */
    public static Direction fromLabel(String label) {
        for (Direction d : values()) {
            if (d.label.equals(label)) {
                return d;
            }
        }
        throw new IllegalArgumentException("Unknown direction label: " + label);
    }
}

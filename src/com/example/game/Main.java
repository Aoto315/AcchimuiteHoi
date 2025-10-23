/**
 * Main.java
 *
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package com.example.game;

/**
 * プログラムの実行を開始するメインクラスです。
 * <p>
 * Gameクラスのインスタンスを作成し、ゲームを開始します。
 * </p>
 *
 * @author FLM
 * @version 1.0.0
 */
public class Main {

    /**
     * アプリケーションのエントリーポイント（mainメソッド）です。
     *
     * @param args コマンドライン引数（このプログラムでは使用しません）
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.start();  // ゲームを開始する
    }
}

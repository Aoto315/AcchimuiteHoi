/**
 * Game.java
 *
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package com.example.game;

import java.util.Random;
import java.util.Scanner;

/**
 * あっちむいてホイのゲームを処理するクラスです。
 * <p>
 * ユーザーからの入力とCPUの動きを比較し、勝敗を判定します。
 * </p>
 *
 * @author FLM
 * @version 1.0.0
 */
public class Game {

	/** ユーザーからの入力を受け取るためのスキャナーです。 */
	private final Scanner scanner = new Scanner(System.in);

	/** CPUの動きをランダムに決めるための乱数生成器です。 */
	private final Random random = new Random();

	/**
	 * ゲームを開始するメソッドです。
	 * ユーザーとCPUの方向を取得し、一致すれば勝ち、しなければ外れとなります。
	 */
	public void start() {
		// あっちむいてホイの指す方向の選択肢（0:上、1:下、2:左、3:右）を表示
		Direction.showOptions();

		Direction playerHand = getPlayerDirection();
		Direction cpuFace = getCpuDirection();

		System.out.println("あなたの指：「" + playerHand.getLabel() + "」");
		System.out.println("コンピュータの顔：「" + cpuFace.getLabel() + "」");

		boolean isMatch = playerHand == cpuFace;

		// 条件が一致する場合勝ち、一致しない場合は外れとなる
		if (isMatch) {
			System.out.println("一致しました！あなたの勝ちです！");
			ResultLogger.logResult(playerHand, cpuFace, "勝ち");
		} else {
			System.out.println("一致しませんでした。残念！");
			ResultLogger.logResult(playerHand, cpuFace, "はずれ");
		}
	}

	/**
	 * ユーザーに指す方向を選ばせて、その入力を取得します。
	 *
	 * @return ユーザーが選んだ方向（Direction列挙型）
	 */
	private Direction getPlayerDirection() {
		while (true) {
			try {
				System.out.print("どこに指をさしますか？（番号を入力）：");
				int input = Integer.parseInt(scanner.nextLine());
				return Direction.fromInt(input);
			} catch (Exception e) {
				System.out.println("無効な入力です。0〜3の数字で入力してください。");
			}
		}
	}

	/**
	 * CPUの指す方向をランダムに決定します。
	 *
	 * @return CPUが選んだ方向（Direction列挙型）
	 */
	private Direction getCpuDirection() {
		return Direction.fromInt(random.nextInt(4));
	}
}

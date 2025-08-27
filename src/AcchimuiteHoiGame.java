
/**
 * AcchimuiteHoiGame.java
 *
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

import java.util.Scanner;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author FLM
 * @version 1.0.0
 */
public class AcchimuiteHoiGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		String[] directions = { "上", "下", "右", "左" };

		boolean gameContinues = true;

		System.out.println("---あっちむてほいゲーム開始！---");

		try (BufferedWriter writer
				= new BufferedWriter(new FileWriter("game_result.txt", true))) {

			writer.write("\n--- 新しいゲームを開始 ---\n");

			// gameContinuesがtrueの場合はゲームを繰り返す。
			while (gameContinues) {
				System.out.println("指を指す方向を選んでください。");
				for (int i = 0; i < directions.length; i++) {
					System.out.println(i + "：" + directions[i]);
				}
				System.out.println("番号を入力してください：");

				// Playerから入力された値をplayerChoiceに格納する
				int playerChoice = scanner.nextInt();
				// Playerが入力した値(playerChoice)が、0〜3の範囲に入っていない場合は
				// エラーメッセージを出力し、ループ処理をやり直す
				if (playerChoice < 0 || playerChoice > 3) {
					System.out.println("無効な入力です。0~3の数字を入力してください");
					continue;
				}
				int computerChoice = random.nextInt(4);

				System.out.println("あなたのの選んだ方向：" + directions[playerChoice]);
				System.out.println("コンピューターの選んだ方向：" + directions[computerChoice]);

				// 履歴をファイルに追記
				writer.write("あなたの選んだ方向：" + directions[playerChoice] + "\n");
				writer.write("コンピューターの選んだ方向：" + directions[computerChoice] + "\n");

				if (playerChoice == computerChoice) {
					System.out.println("あなたの勝ちです！");
					writer.write("結果：あなたの勝ち\n");
					writer.write("--- ゲーム終了 ---\n");
					gameContinues = false;
				} else {
					System.out.println("一致しませんでした。もう一度勝負！");
					writer.write("結果：一致せず、再挑戦\n\n");
				}
			}

		} catch (IOException e) {
			System.out.println("ファイル書き込み中にエラーが発生しました: " + e.getMessage());
		}
		scanner.close();
	}

}

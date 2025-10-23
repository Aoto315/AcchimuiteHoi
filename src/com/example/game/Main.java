package com.example.game;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * あっちむいてホイゲームの起動とメニュー選択を担当するクラスです。
 * <p>
 * ユーザーにゲーム開始や成績確認などの選択肢を提示し、選択に応じて処理を呼び出します。
 * </p>
 */
public class Main {

    /**
     * プログラムのエントリーポイント。
     * <p>
     * メニューを表示してユーザーの入力を待ち、ゲームの開始や成績表示などを実行します。
     * </p>
     *
     * @param args コマンドライン引数（未使用）
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        DB db = new DB();

        while (true) {
            System.out.println("選択してください：");
            System.out.println("1: ゲームを開始する");
            System.out.println("2: 過去10回の対戦成績を表示する");
            System.out.println("3: 月単位でまとめた成績を表示する");
            System.out.println("4: 最多連勝記録を表示する");
            System.out.println("0: 終了");

            System.out.print("番号を入力: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    game.start();
                    break;
                case "2":
                    List<MatchResult> last10 = db.getLast10Matches();
                    for (MatchResult result : last10) {
                        System.out.println(result);
                    }
                    break;
                case "3":
                    Map<String, MonthlyStats> monthlyStats = db.getMonthlyStats();
                    for (String month : monthlyStats.keySet()) {
                        System.out.println(monthlyStats.get(month));
                    }
                    break;
                case "4":
                    int longestStreak = db.getLongestWinningStreak();
                    System.out.println("最多連勝記録は " + longestStreak + " です。");
                    break;
                case "0":
                    System.out.println("終了します。");
                    scanner.close();
                    return;
                default:
                    System.out.println("無効な入力です。");
            }
        }
    }
}

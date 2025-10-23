package com.example.game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * データベースに接続し、あっちむいてほいの試合結果を取得・表示するクラスです。
 * <p>
 * MySQLのattimuitehoiデータベースに接続し、match_resultテーブルの全レコードを
 * コンソールに出力します。
 * </p>
 */
public class DB {

    /** MySQLデータベースの接続URL */
    private static final String URL = "jdbc:mysql://localhost:3306/attimuitehoi";

    /** データベース接続用のユーザー名 */
    private static final String USERNAME = "root";

    /** データベース接続用のパスワード */
    private static final String PASSWORD = "admin123";

    /**
     * メインメソッド。
     * MySQLデータベースに接続し、match_resultテーブルの内容を取得してコンソールに表示します。
     *
     * @param args コマンドライン引数（このプログラムでは使用しません）
     */
    public static void main(String[] args) {
        // MySQLへの接続
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            System.out.println("attimuitehoi データベースに接続しました。");

            // SQLクエリの実行
            try (Statement statement = connection.createStatement()) {
                String sql = "SELECT * FROM match_result";
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    // 結果の処理
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String date = resultSet.getString("match_date");
                        String player = resultSet.getString("player_direction");
                        String cpu = resultSet.getString("cpu_direction");
                        String outcome = resultSet.getString("outcome");

                        System.out.println("ID: " + id +
                                ", DATE: " + date +
                                ", PLAYER: " + player +
                                ", CPU: " + cpu +
                                ", OUTCOME: " + outcome);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("MySQLへの接続に失敗しました。");
            e.printStackTrace();
        }
    }
}

package com.example.game;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * あっちむいてホイの結果をファイルとデータベースに保存するクラスです。
 */
public class ResultLogger {

	// ファイル名を表す定数
	private static final String FILE_NAME = "results.txt";

	// DB接続情報のURL
	private static final String DB_URL
			= "jdbc:mysql://localhost:3306/attimuitehoi";
	// DBユーザー名
	private static final String DB_USER = "root";
	// DBパスワード
	private static final String DB_PASSWORD = "admin123";

	/**
	 * プレイヤーとCPUの結果をログとしてファイルとデータベースに保存します。
	 *
	 * @param player プレイヤーが指した方向
	 * @param cpu CPUが指した方向
	 * @param result 勝敗結果の文字列（例："勝ち"や"はずれ"）
	 */
	public static void logResult(Direction player, Direction cpu,
			String result) {
		String time = LocalDateTime.now().format(
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String log = String.format("[%s] 指: %s, 顔: %s → %s",
				time, player.getLabel(), cpu.getLabel(), result);

		// ファイルに保存
		try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
			writer.write(log + "\n");
		} catch (IOException e) {
			System.out.println("結果の記録に失敗しました（ファイル）: " + e.getMessage());
		}

		// データベースに保存
		try (Connection conn
				= DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			String insertSQL
					= "INSERT INTO match_result (match_date, player_direction, cpu_direction, outcome) VALUES (?, ?, ?, ?)";
			try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
				pstmt.setString(1, time); // 時刻（"yyyy-MM-dd HH:mm:ss"）
				pstmt.setString(2, player.getLabel());
				pstmt.setString(3, cpu.getLabel());
				pstmt.setString(4, result);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("結果の記録に失敗しました（DB）: " + e.getMessage());
		}
	}
}

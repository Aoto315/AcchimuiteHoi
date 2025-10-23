package com.example.game;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * データベースへの接続とあっちむいてホイの成績情報の取得を担当するクラスです。
 */
public class DB {

    private static final String DB_URL
            = "jdbc:mysql://localhost:3306/attimuitehoi";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "admin123";

    /**
     * 過去10回の対戦成績を取得します。
     *
     * @return MatchResultのリスト（最新10件）
     */
    public List<MatchResult> getLast10Matches() {
        List<MatchResult> results = new ArrayList<>();
        String sql = "SELECT * FROM match_result ORDER BY match_date DESC LIMIT 10";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                MatchResult result = new MatchResult(
                        rs.getInt("id"),
                        rs.getString("match_date"),
                        rs.getString("player_direction"),
                        rs.getString("cpu_direction"),
                        rs.getString("outcome"));
                results.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * 月単位で勝敗などの成績を集計して取得します。
     *
     * @return 月ごとの成績を管理するMonthlyStatsオブジェクトのマップ（キーは"YYYY-MM"形式の年月）
     */
    public Map<String, MonthlyStats> getMonthlyStats() {
        Map<String, MonthlyStats> monthlyStats = new LinkedHashMap<>();
        String sql = "SELECT DATE_FORMAT(match_date, '%Y-%m') AS month, outcome, COUNT(*) AS count "
                   + "FROM match_result GROUP BY month, outcome ORDER BY month";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String month = rs.getString("month");
                String outcome = rs.getString("outcome");
                int count = rs.getInt("count");

                monthlyStats.putIfAbsent(month, new MonthlyStats(month));
                monthlyStats.get(month).addCount(outcome, count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monthlyStats;
    }

    /**
     * 最も長い連勝記録を取得します。
     *
     * @return 連勝の最大回数
     */
    public int getLongestWinningStreak() {
        int maxStreak = 0;
        int currentStreak = 0;

        String sql = "SELECT outcome FROM match_result ORDER BY match_date";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String outcome = rs.getString("outcome");
                if ("勝ち".equals(outcome)) {
                    currentStreak++;
                    if (currentStreak > maxStreak) {
                        maxStreak = currentStreak;
                    }
                } else {
                    currentStreak = 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maxStreak;
    }
}

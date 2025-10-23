package com.example.game;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 1回分の「あっちむいてホイ」対戦結果を表すクラスです。
 * <p>
 * 対戦の日時、プレイヤーとCPUの向き、そして勝敗結果を管理します。
 * データベースから取得した1件の試合データを、Javaオブジェクトとして扱えるようにします。
 * </p>
 */
public class MatchResult {

    /** 試合の通し番号（データベース上のID） */
    private int id;

    /** 試合が行われた日時 */
    private LocalDateTime matchDate;

    /** プレイヤーが指した方向 */
    private Direction playerDirection;

    /** CPUが指した方向 */
    private Direction cpuDirection;

    /** 試合の結果（例：「勝ち」「はずれ」など） */
    private String outcome;

    /**
     * 試合結果の情報をもとに、MatchResultオブジェクトを生成します。
     *
     * @param id 試合ID
     * @param matchDateStr 試合日時（文字列形式, 例: "2025-10-23 10:31:38"）
     * @param playerDirStr プレイヤーの向き（文字列形式, 例: "上"）
     * @param cpuDirStr CPUの向き（文字列形式, 例: "左"）
     * @param outcome 試合結果（例: "勝ち", "はずれ"）
     */
    public MatchResult(int id, String matchDateStr, String playerDirStr, String cpuDirStr, String outcome) {
        this.id = id;

        // 文字列からLocalDateTime型に変換（"yyyy-MM-dd HH:mm:ss" 形式）
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.matchDate = LocalDateTime.parse(matchDateStr, formatter);

        // 文字列（"上"、"下"など）を Direction 型に変換
        this.playerDirection = Direction.fromLabel(playerDirStr);
        this.cpuDirection = Direction.fromLabel(cpuDirStr);

        this.outcome = outcome;
    }

    /** @return 試合のID */
    public int getId() {
        return id;
    }

    /** @return 試合の日時 */
    public LocalDateTime getMatchDate() {
        return matchDate;
    }

    /** @return プレイヤーが選んだ方向 */
    public Direction getPlayerDirection() {
        return playerDirection;
    }

    /** @return CPUが選んだ方向 */
    public Direction getCpuDirection() {
        return cpuDirection;
    }

    /** @return 試合結果（「勝ち」や「はずれ」など） */
    public String getOutcome() {
        return outcome;
    }

    /**
     * 試合結果を文字列として返します。
     * <p>
     * {@link System#out} などで出力すると、
     * 「ID: 1, 日時: 2025-10-23T10:31:38, プレイヤー: 上, CPU: 左, 結果: 勝ち」
     * のように表示されます。
     * </p>
     *
     * @return 試合結果を表す整形済み文字列
     */
    @Override
    public String toString() {
        return String.format("ID: %d, 日時: %s, プレイヤー: %s, CPU: %s, 結果: %s",
                id, matchDate, playerDirection.getLabel(), cpuDirection.getLabel(), outcome);
    }
}

package com.melot.kk.nationalPK.api.domain.DO;

import java.io.Serializable;

public class ResActorLadderMatchDO implements Serializable {

    /**
     * 主播id
     */
    private Integer actorId;

    /**
     * 赛季id
     */
    private Integer seasonId;

    /**
     * 天梯赛积分
     */
    private Integer ladderMatchIntegral;

    /**
     * 天梯赛场次
     */
    private Integer ladderMatchTime;

    /**
     * 天梯赛胜率
     */
    private Integer winningRate;

    /**
     * 天梯赛排名
     */
    private Integer ranking;

    /**
     * 游戏段位 1-倔强青铜 2-英勇白银 3-荣耀黄金 4-华贵铂金 5-璀璨钻石 6-最强王者
     */
    private Integer gameDan;

    /**
     * 游戏段位名称
     */
    private String gameDanName;

    /**
     * 秀币是否发放
     */
    private Integer showMoneyGiveReward;

    /**
     * 发放秀币数量
     */
    private Long showMoneyCount;

    /**
     * 当前赛季是否正在进行中 true-正在进行中 false-已结束
     */
    private boolean currentSeasonOngoing;

    private static final long serialVersionUID = 1L;

    public Integer getShowMoneyGiveReward() {
        return showMoneyGiveReward;
    }

    public void setShowMoneyGiveReward(Integer showMoneyGiveReward) {
        this.showMoneyGiveReward = showMoneyGiveReward;
    }

    public Long getShowMoneyCount() {
        return showMoneyCount;
    }

    public void setShowMoneyCount(Long showMoneyCount) {
        this.showMoneyCount = showMoneyCount;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public Integer getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    public Integer getLadderMatchIntegral() {
        return ladderMatchIntegral;
    }

    public void setLadderMatchIntegral(Integer ladderMatchIntegral) {
        this.ladderMatchIntegral = ladderMatchIntegral;
    }

    public Integer getLadderMatchTime() {
        return ladderMatchTime;
    }

    public void setLadderMatchTime(Integer ladderMatchTime) {
        this.ladderMatchTime = ladderMatchTime;
    }

    public Integer getWinningRate() {
        return winningRate;
    }

    public void setWinningRate(Integer winningRate) {
        this.winningRate = winningRate;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getGameDan() {
        return gameDan;
    }

    public void setGameDan(Integer gameDan) {
        this.gameDan = gameDan;
    }

    public String getGameDanName() {
        return gameDanName;
    }

    public void setGameDanName(String gameDanName) {
        this.gameDanName = gameDanName;
    }

    public boolean isCurrentSeasonOngoing() {
        return currentSeasonOngoing;
    }

    public void setCurrentSeasonOngoing(boolean currentSeasonOngoing) {
        this.currentSeasonOngoing = currentSeasonOngoing;
    }
}
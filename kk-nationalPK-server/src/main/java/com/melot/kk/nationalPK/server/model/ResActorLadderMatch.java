package com.melot.kk.nationalPK.server.model;

import java.io.Serializable;
import java.util.Date;

public class ResActorLadderMatch implements Serializable {
    private Integer actorId;

    private Integer seasonId;

    private Integer ladderMatchIntegral;

    private Integer gameDan;

    private Integer ladderMatchTime;

    private Integer winningTime;

    private Integer winningRate;

    private Integer showMoneyGiveReward;

    private Integer medalGiveReward;

    private Long showMoneyCount;

    private Integer medalId;

    private Long receiveShowMoney;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

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

    public Integer getGameDan() {
        return gameDan;
    }

    public void setGameDan(Integer gameDan) {
        this.gameDan = gameDan;
    }

    public Integer getLadderMatchTime() {
        return ladderMatchTime;
    }

    public void setLadderMatchTime(Integer ladderMatchTime) {
        this.ladderMatchTime = ladderMatchTime;
    }

    public Integer getWinningTime() {
        return winningTime;
    }

    public void setWinningTime(Integer winningTime) {
        this.winningTime = winningTime;
    }

    public Integer getWinningRate() {
        return winningRate;
    }

    public void setWinningRate(Integer winningRate) {
        this.winningRate = winningRate;
    }

    public Integer getShowMoneyGiveReward() {
        return showMoneyGiveReward;
    }

    public void setShowMoneyGiveReward(Integer showMoneyGiveReward) {
        this.showMoneyGiveReward = showMoneyGiveReward;
    }

    public Integer getMedalGiveReward() {
        return medalGiveReward;
    }

    public void setMedalGiveReward(Integer medalGiveReward) {
        this.medalGiveReward = medalGiveReward;
    }

    public Long getShowMoneyCount() {
        return showMoneyCount;
    }

    public void setShowMoneyCount(Long showMoneyCount) {
        this.showMoneyCount = showMoneyCount;
    }

    public Integer getMedalId() {
        return medalId;
    }

    public void setMedalId(Integer medalId) {
        this.medalId = medalId;
    }

    public Long getReceiveShowMoney() {
        return receiveShowMoney;
    }

    public void setReceiveShowMoney(Long receiveShowMoney) {
        this.receiveShowMoney = receiveShowMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ResActorLadderMatch other = (ResActorLadderMatch) that;
        return (this.getActorId() == null ? other.getActorId() == null : this.getActorId().equals(other.getActorId()))
            && (this.getSeasonId() == null ? other.getSeasonId() == null : this.getSeasonId().equals(other.getSeasonId()))
            && (this.getLadderMatchIntegral() == null ? other.getLadderMatchIntegral() == null : this.getLadderMatchIntegral().equals(other.getLadderMatchIntegral()))
            && (this.getGameDan() == null ? other.getGameDan() == null : this.getGameDan().equals(other.getGameDan()))
            && (this.getLadderMatchTime() == null ? other.getLadderMatchTime() == null : this.getLadderMatchTime().equals(other.getLadderMatchTime()))
            && (this.getWinningTime() == null ? other.getWinningTime() == null : this.getWinningTime().equals(other.getWinningTime()))
            && (this.getWinningRate() == null ? other.getWinningRate() == null : this.getWinningRate().equals(other.getWinningRate()))
            && (this.getShowMoneyGiveReward() == null ? other.getShowMoneyGiveReward() == null : this.getShowMoneyGiveReward().equals(other.getShowMoneyGiveReward()))
            && (this.getMedalGiveReward() == null ? other.getMedalGiveReward() == null : this.getMedalGiveReward().equals(other.getMedalGiveReward()))
            && (this.getShowMoneyCount() == null ? other.getShowMoneyCount() == null : this.getShowMoneyCount().equals(other.getShowMoneyCount()))
            && (this.getMedalId() == null ? other.getMedalId() == null : this.getMedalId().equals(other.getMedalId()))
            && (this.getReceiveShowMoney() == null ? other.getReceiveShowMoney() == null : this.getReceiveShowMoney().equals(other.getReceiveShowMoney()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getActorId() == null) ? 0 : getActorId().hashCode());
        result = prime * result + ((getSeasonId() == null) ? 0 : getSeasonId().hashCode());
        result = prime * result + ((getLadderMatchIntegral() == null) ? 0 : getLadderMatchIntegral().hashCode());
        result = prime * result + ((getGameDan() == null) ? 0 : getGameDan().hashCode());
        result = prime * result + ((getLadderMatchTime() == null) ? 0 : getLadderMatchTime().hashCode());
        result = prime * result + ((getWinningTime() == null) ? 0 : getWinningTime().hashCode());
        result = prime * result + ((getWinningRate() == null) ? 0 : getWinningRate().hashCode());
        result = prime * result + ((getShowMoneyGiveReward() == null) ? 0 : getShowMoneyGiveReward().hashCode());
        result = prime * result + ((getMedalGiveReward() == null) ? 0 : getMedalGiveReward().hashCode());
        result = prime * result + ((getShowMoneyCount() == null) ? 0 : getShowMoneyCount().hashCode());
        result = prime * result + ((getMedalId() == null) ? 0 : getMedalId().hashCode());
        result = prime * result + ((getReceiveShowMoney() == null) ? 0 : getReceiveShowMoney().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", actorId=").append(actorId);
        sb.append(", seasonId=").append(seasonId);
        sb.append(", ladderMatchIntegral=").append(ladderMatchIntegral);
        sb.append(", gameDan=").append(gameDan);
        sb.append(", ladderMatchTime=").append(ladderMatchTime);
        sb.append(", winningTime=").append(winningTime);
        sb.append(", winningRate=").append(winningRate);
        sb.append(", showMoneyGiveReward=").append(showMoneyGiveReward);
        sb.append(", medalGiveReward=").append(medalGiveReward);
        sb.append(", showMoneyCount=").append(showMoneyCount);
        sb.append(", medalId=").append(medalId);
        sb.append(", receiveShowMoney=").append(receiveShowMoney);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}
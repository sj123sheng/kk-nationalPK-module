package com.melot.kk.nationalPK.server.model;

import java.io.Serializable;
import java.util.Date;

public class ResActorLadderMatch implements Serializable {
    private Integer actorId;

    private Integer seasonId;

    private Integer ladderMatchIntegral;

    private Integer ladderMatchTime;

    private Integer winningRate;

    private Date createTime;

    private Date updateTime;

    private Integer showMoneyGiveReward;

    private Integer medalGiveReward;

    private Long showMoneyCount;

    private Integer medalId;

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
            && (this.getLadderMatchTime() == null ? other.getLadderMatchTime() == null : this.getLadderMatchTime().equals(other.getLadderMatchTime()))
            && (this.getWinningRate() == null ? other.getWinningRate() == null : this.getWinningRate().equals(other.getWinningRate()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getShowMoneyGiveReward() == null ? other.getShowMoneyGiveReward() == null : this.getShowMoneyGiveReward().equals(other.getShowMoneyGiveReward()))
            && (this.getMedalGiveReward() == null ? other.getMedalGiveReward() == null : this.getMedalGiveReward().equals(other.getMedalGiveReward()))
            && (this.getShowMoneyCount() == null ? other.getShowMoneyCount() == null : this.getShowMoneyCount().equals(other.getShowMoneyCount()))
            && (this.getMedalId() == null ? other.getMedalId() == null : this.getMedalId().equals(other.getMedalId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getActorId() == null) ? 0 : getActorId().hashCode());
        result = prime * result + ((getSeasonId() == null) ? 0 : getSeasonId().hashCode());
        result = prime * result + ((getLadderMatchIntegral() == null) ? 0 : getLadderMatchIntegral().hashCode());
        result = prime * result + ((getLadderMatchTime() == null) ? 0 : getLadderMatchTime().hashCode());
        result = prime * result + ((getWinningRate() == null) ? 0 : getWinningRate().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getShowMoneyGiveReward() == null) ? 0 : getShowMoneyGiveReward().hashCode());
        result = prime * result + ((getMedalGiveReward() == null) ? 0 : getMedalGiveReward().hashCode());
        result = prime * result + ((getShowMoneyCount() == null) ? 0 : getShowMoneyCount().hashCode());
        result = prime * result + ((getMedalId() == null) ? 0 : getMedalId().hashCode());
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
        sb.append(", ladderMatchTime=").append(ladderMatchTime);
        sb.append(", winningRate=").append(winningRate);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", showMoneyGiveReward=").append(showMoneyGiveReward);
        sb.append(", medalGiveReward=").append(medalGiveReward);
        sb.append(", showMoneyCount=").append(showMoneyCount);
        sb.append(", medalId=").append(medalId);
        sb.append("]");
        return sb.toString();
    }
}
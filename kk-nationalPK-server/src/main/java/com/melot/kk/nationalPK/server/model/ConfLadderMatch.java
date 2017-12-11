package com.melot.kk.nationalPK.server.model;

import java.io.Serializable;
import java.util.Date;

public class ConfLadderMatch implements Serializable {
    private Integer seasonId;

    private String seasonName;

    private Integer bonusPoolMultiple;

    private Integer giveReward;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName == null ? null : seasonName.trim();
    }

    public Integer getBonusPoolMultiple() {
        return bonusPoolMultiple;
    }

    public void setBonusPoolMultiple(Integer bonusPoolMultiple) {
        this.bonusPoolMultiple = bonusPoolMultiple;
    }

    public Integer getGiveReward() {
        return giveReward;
    }

    public void setGiveReward(Integer giveReward) {
        this.giveReward = giveReward;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
        ConfLadderMatch other = (ConfLadderMatch) that;
        return (this.getSeasonId() == null ? other.getSeasonId() == null : this.getSeasonId().equals(other.getSeasonId()))
            && (this.getSeasonName() == null ? other.getSeasonName() == null : this.getSeasonName().equals(other.getSeasonName()))
            && (this.getBonusPoolMultiple() == null ? other.getBonusPoolMultiple() == null : this.getBonusPoolMultiple().equals(other.getBonusPoolMultiple()))
            && (this.getGiveReward() == null ? other.getGiveReward() == null : this.getGiveReward().equals(other.getGiveReward()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSeasonId() == null) ? 0 : getSeasonId().hashCode());
        result = prime * result + ((getSeasonName() == null) ? 0 : getSeasonName().hashCode());
        result = prime * result + ((getBonusPoolMultiple() == null) ? 0 : getBonusPoolMultiple().hashCode());
        result = prime * result + ((getGiveReward() == null) ? 0 : getGiveReward().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
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
        sb.append(", seasonId=").append(seasonId);
        sb.append(", seasonName=").append(seasonName);
        sb.append(", bonusPoolMultiple=").append(bonusPoolMultiple);
        sb.append(", giveReward=").append(giveReward);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}
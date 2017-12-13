package com.melot.kk.nationalPK.server.model;

import java.io.Serializable;
import java.util.Date;

public class HistActorLadderMatch implements Serializable {
    private Integer ladderMatchRecordId;

    private Integer actorId;

    private Integer seasonId;

    private Integer opponentActorId;

    private Integer ladderMatchResult;

    private Integer receiveScore;

    private Integer opponentReceiveScore;

    private Long receiveShowMoney;

    private Long opponentReceiveShowMoney;

    private Date createTime;

    private Integer pkId;

    private static final long serialVersionUID = 1L;

    public Integer getLadderMatchRecordId() {
        return ladderMatchRecordId;
    }

    public void setLadderMatchRecordId(Integer ladderMatchRecordId) {
        this.ladderMatchRecordId = ladderMatchRecordId;
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

    public Integer getOpponentActorId() {
        return opponentActorId;
    }

    public void setOpponentActorId(Integer opponentActorId) {
        this.opponentActorId = opponentActorId;
    }

    public Integer getLadderMatchResult() {
        return ladderMatchResult;
    }

    public void setLadderMatchResult(Integer ladderMatchResult) {
        this.ladderMatchResult = ladderMatchResult;
    }

    public Integer getReceiveScore() {
        return receiveScore;
    }

    public void setReceiveScore(Integer receiveScore) {
        this.receiveScore = receiveScore;
    }

    public Integer getOpponentReceiveScore() {
        return opponentReceiveScore;
    }

    public void setOpponentReceiveScore(Integer opponentReceiveScore) {
        this.opponentReceiveScore = opponentReceiveScore;
    }

    public Long getReceiveShowMoney() {
        return receiveShowMoney;
    }

    public void setReceiveShowMoney(Long receiveShowMoney) {
        this.receiveShowMoney = receiveShowMoney;
    }

    public Long getOpponentReceiveShowMoney() {
        return opponentReceiveShowMoney;
    }

    public void setOpponentReceiveShowMoney(Long opponentReceiveShowMoney) {
        this.opponentReceiveShowMoney = opponentReceiveShowMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
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
        HistActorLadderMatch other = (HistActorLadderMatch) that;
        return (this.getLadderMatchRecordId() == null ? other.getLadderMatchRecordId() == null : this.getLadderMatchRecordId().equals(other.getLadderMatchRecordId()))
            && (this.getActorId() == null ? other.getActorId() == null : this.getActorId().equals(other.getActorId()))
            && (this.getSeasonId() == null ? other.getSeasonId() == null : this.getSeasonId().equals(other.getSeasonId()))
            && (this.getOpponentActorId() == null ? other.getOpponentActorId() == null : this.getOpponentActorId().equals(other.getOpponentActorId()))
            && (this.getLadderMatchResult() == null ? other.getLadderMatchResult() == null : this.getLadderMatchResult().equals(other.getLadderMatchResult()))
            && (this.getReceiveScore() == null ? other.getReceiveScore() == null : this.getReceiveScore().equals(other.getReceiveScore()))
            && (this.getOpponentReceiveScore() == null ? other.getOpponentReceiveScore() == null : this.getOpponentReceiveScore().equals(other.getOpponentReceiveScore()))
            && (this.getReceiveShowMoney() == null ? other.getReceiveShowMoney() == null : this.getReceiveShowMoney().equals(other.getReceiveShowMoney()))
            && (this.getOpponentReceiveShowMoney() == null ? other.getOpponentReceiveShowMoney() == null : this.getOpponentReceiveShowMoney().equals(other.getOpponentReceiveShowMoney()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getPkId() == null ? other.getPkId() == null : this.getPkId().equals(other.getPkId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLadderMatchRecordId() == null) ? 0 : getLadderMatchRecordId().hashCode());
        result = prime * result + ((getActorId() == null) ? 0 : getActorId().hashCode());
        result = prime * result + ((getSeasonId() == null) ? 0 : getSeasonId().hashCode());
        result = prime * result + ((getOpponentActorId() == null) ? 0 : getOpponentActorId().hashCode());
        result = prime * result + ((getLadderMatchResult() == null) ? 0 : getLadderMatchResult().hashCode());
        result = prime * result + ((getReceiveScore() == null) ? 0 : getReceiveScore().hashCode());
        result = prime * result + ((getOpponentReceiveScore() == null) ? 0 : getOpponentReceiveScore().hashCode());
        result = prime * result + ((getReceiveShowMoney() == null) ? 0 : getReceiveShowMoney().hashCode());
        result = prime * result + ((getOpponentReceiveShowMoney() == null) ? 0 : getOpponentReceiveShowMoney().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getPkId() == null) ? 0 : getPkId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ladderMatchRecordId=").append(ladderMatchRecordId);
        sb.append(", actorId=").append(actorId);
        sb.append(", seasonId=").append(seasonId);
        sb.append(", opponentActorId=").append(opponentActorId);
        sb.append(", ladderMatchResult=").append(ladderMatchResult);
        sb.append(", receiveScore=").append(receiveScore);
        sb.append(", opponentReceiveScore=").append(opponentReceiveScore);
        sb.append(", receiveShowMoney=").append(receiveShowMoney);
        sb.append(", opponentReceiveShowMoney=").append(opponentReceiveShowMoney);
        sb.append(", createTime=").append(createTime);
        sb.append(", pkId=").append(pkId);
        sb.append("]");
        return sb.toString();
    }
}
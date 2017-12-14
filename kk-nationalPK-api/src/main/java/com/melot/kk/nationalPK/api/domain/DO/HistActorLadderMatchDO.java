package com.melot.kk.nationalPK.api.domain.DO;

import java.io.Serializable;
import java.util.Date;

public class HistActorLadderMatchDO implements Serializable {

    /**
     * 主播天梯赛历史记录id
     */
    private Integer ladderMatchRecordId;

    /**
     * 主播id
     */
    private Integer actorId;

    /**
     * 赛季id
     */
    private Integer seasonId;

    /**
     * 对手主播id
     */
    private Integer opponentActorId;

    /**
     * 天梯赛比赛结果 1-胜利 2-失败 3-平局
     */
    private Integer ladderMatchResult;

    /**
     * 获得分值
     */
    private Integer receiveScore;

    /**
     * 对手获得分值
     */
    private Integer opponentReceiveScore;

    /**
     * 获得秀币总数
     */
    private Long receiveShowMoney;

    /**
     * 对手获得秀币总数
     */
    private Long opponentReceiveShowMoney;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 存储pk详情的hist_pkroom_stat表的主键id
     */
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

}
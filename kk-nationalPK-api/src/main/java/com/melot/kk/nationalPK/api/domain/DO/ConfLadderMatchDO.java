package com.melot.kk.nationalPK.api.domain.DO;

import java.io.Serializable;
import java.util.Date;

public class ConfLadderMatchDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 赛季id
     */
    private Integer seasonId;

    /**
     * 赛季名称
     */
    private String seasonName;

    /**
     * 奖金池秀币总数量
     */
    private Long bonusPool;

    /**
     * 奖金池倍率 (原始倍率*100)
     */
    private Integer bonusPoolMultiple;

    /**
     * 是否已发放奖励 0-未发放 1-已发放
     */
    private Integer giveReward;

    /**
     * 赛季开始时间
     */
    private Date startTime;

    /**
     * 赛季结束时间
     */
    private Date endTime;

    /**
     * 赛季状态 0-未开始 1-正在进行中 2-已结束
     */
    private Integer status;

    /**
     * 赛季结束剩余时间 (单位：秒) 当前赛季结束,下一赛季还未开始,返回-1
     */
    private Long remainingTime;

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

    public Long getBonusPool() {
        return bonusPool;
    }

    public void setBonusPool(Long bonusPool) {
        this.bonusPool = bonusPool;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(Long remainingTime) {
        this.remainingTime = remainingTime;
    }
}
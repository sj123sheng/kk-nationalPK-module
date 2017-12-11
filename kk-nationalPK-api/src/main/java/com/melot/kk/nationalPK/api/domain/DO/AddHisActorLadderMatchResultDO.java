package com.melot.kk.nationalPK.api.domain.DO;

import java.io.Serializable;

public class AddHisActorLadderMatchResultDO implements Serializable {

    /**
     * 主播id
     */
    private Integer actorId;

    /**
     * 对手主播id
     */
    private Integer opponentActorId;

    /**
     * 天梯赛获得分值
     */
    private Integer receiveScore;

    /**
     * 对手天梯赛获得分值
     */
    private Integer opponentReceiveScore;

    /**
     * 当前游戏段位 1-倔强青铜 2-英勇白银 3-荣耀黄金 4-华贵铂金 5-璀璨钻石 6-最强王者
     */
    private Integer gameDan;

    /**
     * 对手当前游戏段位 1-倔强青铜 2-英勇白银 3-荣耀黄金 4-华贵铂金 5-璀璨钻石 6-最强王者
     */
    private Integer opponentGameDan;

    private static final long serialVersionUID = 1L;

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public Integer getReceiveScore() {
        return receiveScore;
    }

    public void setReceiveScore(Integer receiveScore) {
        this.receiveScore = receiveScore;
    }

    public Integer getGameDan() {
        return gameDan;
    }

    public void setGameDan(Integer gameDan) {
        this.gameDan = gameDan;
    }

    public Integer getOpponentActorId() {
        return opponentActorId;
    }

    public void setOpponentActorId(Integer opponentActorId) {
        this.opponentActorId = opponentActorId;
    }

    public Integer getOpponentReceiveScore() {
        return opponentReceiveScore;
    }

    public void setOpponentReceiveScore(Integer opponentReceiveScore) {
        this.opponentReceiveScore = opponentReceiveScore;
    }

    public Integer getOpponentGameDan() {
        return opponentGameDan;
    }

    public void setOpponentGameDan(Integer opponentGameDan) {
        this.opponentGameDan = opponentGameDan;
    }
}
package com.melot.kk.nationalPK.api.domain.DO;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 秀币发放对象
 * @author: shengjian
 * @date: 2018/1/8
 * @copyright: Copyright (c)2018
 * @company: melot
 * <p>
 * Modification History:
 * Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2018/1/8           shengjian     1.0
 */
public class ShowMoneyGiveRewardDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 奖金池秀币金额
     */
    private Long bonusPoolShowMoney;

    /**
     * 应发放秀币金额
     */
    private Long shouldGiveRewardShowMoney;

    private List<ResActorLadderMatchDO> resActorLadderMatchDOS;

    public Long getBonusPoolShowMoney() {
        return bonusPoolShowMoney;
    }

    public void setBonusPoolShowMoney(Long bonusPoolShowMoney) {
        this.bonusPoolShowMoney = bonusPoolShowMoney;
    }

    public Long getShouldGiveRewardShowMoney() {
        return shouldGiveRewardShowMoney;
    }

    public void setShouldGiveRewardShowMoney(Long shouldGiveRewardShowMoney) {
        this.shouldGiveRewardShowMoney = shouldGiveRewardShowMoney;
    }

    public List<ResActorLadderMatchDO> getResActorLadderMatchDOS() {
        return resActorLadderMatchDOS;
    }

    public void setResActorLadderMatchDOS(List<ResActorLadderMatchDO> resActorLadderMatchDOS) {
        this.resActorLadderMatchDOS = resActorLadderMatchDOS;
    }
}

package com.melot.kk.nationalPK.api.service;

import com.melot.kk.nationalPK.api.domain.DO.ResActorLadderMatchDO;
import com.melot.kk.nationalPK.api.domain.DO.ShowMoneyGiveRewardDO;
import com.melot.kktv.base.Result;

import java.util.List;

/**
 * Title: 全民PK-主播天梯赛资源
 * <p>
 * Description: 全民PK主播天梯赛资源接口服务
 * </p>
 *
 * @author shengjian
 * @version V1.0
 * @since 2017/12/07.
 */
public interface ResActorLadderMatchService {

    /**
     * 获取主播天梯赛当前赛季战绩资源
     * @param actorId 主播id
     */
    Result<ResActorLadderMatchDO> getResActorLadderMatch(int actorId);

    /**
     * 获取天梯榜
     * @param pageIndex 起始页码（默认为1）
     * @param countPerPage 每页条目数
     */
    Result<List<ResActorLadderMatchDO>> getLadderChart(int pageIndex, int countPerPage);

    /**
     * 赛季结束给本赛季所有主播发放奖励(结算秀币奖励和发放勋章奖励)
     * @param seasonId 赛季id
     */
    Result<Boolean> giveReward(int seasonId);

    /**
     * 获取本赛季主播秀币发放列表
     * @param seasonId 赛季id
     */
    Result<ShowMoneyGiveRewardDO> getShowMoneyGiveRewardList(int seasonId);

    /**
     * 秀币奖励结算完毕给本赛季所有主播发放秀币奖励
     * @param seasonId 赛季id
     */
    Result<Boolean> giveRewardShowMoney(int seasonId);

    /**
     * 获取上赛季最强王者的前五名
     */
    Result<List<ResActorLadderMatchDO>> getLastSeasonStrongestKingTop5();

}

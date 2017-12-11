package com.melot.kk.nationalPK.api.service;

import com.melot.kk.nationalPK.api.domain.DO.AddHisActorLadderMatchResultDO;
import com.melot.kk.nationalPK.api.domain.DO.HistActorLadderMatchDO;
import com.melot.kktv.base.Result;

import java.util.List;

/**
 * Title: 全民PK-主播天梯赛历史记录
 * <p>
 * Description: 全民PK主播天梯赛历史记录接口服务
 * </p>
 *
 * @author shengjian
 * @version V1.0
 * @since 2017/12/07.
 */
public interface HistActorLadderMatchService {

    /**
     * 新增主播天梯赛历史记录
     * @param actorId 主播id
     * @param opponentActorId 对手主播id
     * @param receiveShowMoney 获得秀币总数
     * @param opponentReceiveShowMoney 对手获得秀币总数
     * @param pkId 存储pk详情的hist_pkroom_stat表的主键id
     * @return 新增新增主播天梯赛历史记录结果 (包含主播id、获得分值、当前游戏段位)
     */
    Result<AddHisActorLadderMatchResultDO> addHistActorLadderMatch(int actorId, int opponentActorId, long receiveShowMoney, long opponentReceiveShowMoney, int pkId);

    /**
     * 获取主播天梯赛最近10场战绩
     * @param actorId 主播id
     */
    Result<List<HistActorLadderMatchDO>> getRecentHistActorLadderMatchList(int actorId);

}

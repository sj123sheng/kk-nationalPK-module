package com.melot.kk.nationalPK.api.service;

import com.melot.kk.nationalPK.api.domain.DO.ConfLadderMatchDO;
import com.melot.kk.nationalPK.api.domain.DO.ConfLadderMatchPageDO;
import com.melot.kktv.base.Result;

import java.util.Date;

/**
 * Title: 全民PK-天梯赛赛季配置
 * <p>
 * Description: 全民PK天梯赛赛季配置接口服务
 * </p>
 *
 * @author shengjian
 * @version V1.0
 * @since 2017/12/07.
 */
public interface ConfLadderMatchService {

    /**
     * 新增赛季
     * @param seasonName 赛季名称
     * @param startTime 赛季开始时间
     * @param endTime 赛季结束时间
     * @param bonusPoolMultiple 奖金池倍率 (原始倍率*100)
     */
    Result<Boolean> addConfLadderMatch(String seasonName, Date startTime, Date endTime, int bonusPoolMultiple);

    /**
     * 编辑赛季配置信息
     * @param seasonId 赛季id
     */
    Result<Boolean> editConfLadderMatch(int seasonId, String seasonName, Date startTime, Date endTime, int bonusPoolMultiple);

    /**
     * 获取赛季配置列表-分页
     * @param pageIndex 起始页码（默认为1）
     * @param countPerPage 每页条目数
     */
    Result<ConfLadderMatchPageDO> getConfLadderMatchList(int pageIndex, int countPerPage);

    /**
     * 根据赛季id获取赛季配置信息
     * @param seasonId 赛季id
     */
    Result<ConfLadderMatchDO> getConfLadderMatchBySeasonId(int seasonId);

    /**
     * 获取当前赛季配置信息(指正在进行的赛季或者已经结束但是下个赛季还没开始的赛季)
     */
    Result<ConfLadderMatchDO> getCurrentSeasonConf();

    /**
     * 设置当前赛季配置信息(指正在进行的赛季或者已经结束但是下个赛季还没开始的赛季)
     */
    Result<Boolean> setCurrentSeasonConf();

}

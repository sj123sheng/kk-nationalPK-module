package com.melot.kk.nationalPK.server.impl;

import com.google.common.collect.Lists;
import com.melot.kk.nationalPK.api.constant.LadderMatchStatusEnum;
import com.melot.kk.nationalPK.api.domain.DO.ConfLadderMatchDO;
import com.melot.kk.nationalPK.api.domain.DO.ConfLadderMatchPageDO;
import com.melot.kk.nationalPK.api.service.ConfLadderMatchService;
import com.melot.kk.nationalPK.server.constant.NationalPKResultCode;
import com.melot.kk.nationalPK.server.dao.ConfLadderMatchMapper;
import com.melot.kk.nationalPK.server.dao.ResActorLadderMatchMapper;
import com.melot.kk.nationalPK.server.model.ConfLadderMatch;
import com.melot.kk.nationalPK.server.redis.NationalPKRelationSource;
import com.melot.kktv.base.CommonStateCode;
import com.melot.kktv.base.Result;
import com.melot.kktv.util.BeanMapper;
import com.melot.kktv.util.DateUtils;
import com.melot.kktv.util.StringUtil;
import com.melot.module.kkrpc.annotation.RpcService;
import com.site.lookup.util.StringUtils;
import io.shardingjdbc.aop.anotation.RdbMasterSlave;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * @description: 全民PK天梯赛赛季配置接口服务
 * @author: shengjian
 * @date: 2017/10/20
 * @copyright: Copyright (c)2017
 * @company: melot
 * <p>
 * Modification History:
 * Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2017/10/20       shengjian     1.0
 */

@Service
@RpcService(interfaceName = "com.melot.kk.nationalPK.api.service.ConfLadderMatchService", version = "1.0.0")
public class ConfLadderMatchServiceImpl implements ConfLadderMatchService {

    private static Logger logger = Logger.getLogger(ConfLadderMatchServiceImpl.class);

    @Autowired
    private ConfLadderMatchMapper confLadderMatchMapper;

    @Autowired
    private ResActorLadderMatchMapper resActorLadderMatchMapper;

    @Autowired
    private NationalPKRelationSource nationalPKRelationSource;

    @RdbMasterSlave(toMaster = true)
    @Override
    public Result<Boolean> addConfLadderMatch(String seasonName, Date startTime, Date endTime, int bonusPoolMultiple) {

        Boolean result = false;

        if(StringUtils.isEmpty(seasonName) || startTime == null || endTime == null) {
            return new Result(CommonStateCode.FAIL,"入参不能为空");
        }

        List<ConfLadderMatch>  confLadderMatches = confLadderMatchMapper.getList(null, null);
        if(confLadderMatches != null) {
            for(ConfLadderMatch confLadderMatch : confLadderMatches) {
                long seasonStartTime = confLadderMatch.getStartTime().getTime();
                long seasonEndTime = confLadderMatch.getEndTime().getTime();
                if((startTime.getTime() <= seasonStartTime && endTime.getTime() >= seasonStartTime)
                        || (startTime.getTime() >= seasonStartTime && startTime.getTime() <= seasonEndTime)) {
                    return new Result(CommonStateCode.FAIL,"该时间段内已经配置了比赛，请不要重复配置");
                }
                if(DateUtils.addHour(startTime, -1).getTime() <= seasonEndTime) {
                    return new Result(CommonStateCode.FAIL,"新赛季与上赛季间隔时间不能少于1个小时");
                }
            }
        }

        Date nowTime = DateUtils.getCurrentDate();

        ConfLadderMatch record = new ConfLadderMatch();

        record.setSeasonName(seasonName);
        record.setBonusPoolMultiple(bonusPoolMultiple);
        record.setStartTime(startTime);
        record.setEndTime(endTime);

        record.setGiveReward(0);
        record.setCreateTime(nowTime);
        record.setUpdateTime(nowTime);

        Integer seasonId = confLadderMatchMapper.insert(record);
        if(seasonId > 0) {
            result = true;
        }

        return new Result(CommonStateCode.SUCCESS,"新增赛季成功", result);
    }

    @Override
    public Result<Boolean> editConfLadderMatch(int seasonId, String seasonName, Date startTime, Date endTime, int bonusPoolMultiple) {


        if(StringUtils.isEmpty(seasonName) || startTime == null || endTime == null) {
            return new Result(CommonStateCode.FAIL,"入参不能为空");
        }

        ConfLadderMatch record = confLadderMatchMapper.selectByPrimaryKey(seasonId);

        if(record == null) {
            logger.error("查询赛季配置信息为空 seasonId=" + seasonId);
            return new Result(CommonStateCode.FAIL, "查询赛季配置信息为空，编辑赛季配置失败", false);
        }

        long nowTime = System.currentTimeMillis();
        long oldStartTime = record.getStartTime().getTime();
        long oldEndTime = record.getEndTime().getTime();

        if(nowTime > oldEndTime) {
            logger.error("赛季已经结束 seasonId=" + seasonId);
            return new Result(CommonStateCode.FAIL, "赛季已经结束，不能再编辑", false);
        }

        // 赛季还没开始 可以更新开始和结束时间
        if(nowTime < oldStartTime) {

            List<ConfLadderMatch>  confLadderMatches = confLadderMatchMapper.getList(null, null);
            if(confLadderMatches != null) {
                for(ConfLadderMatch confLadderMatch : confLadderMatches) {

                    long seasonStartTime = confLadderMatch.getStartTime().getTime();
                    long seasonEndTime = confLadderMatch.getEndTime().getTime();
                    if(!confLadderMatch.getSeasonId().equals(seasonId) && ((startTime.getTime() <= seasonStartTime && endTime.getTime() >= seasonStartTime)
                                || (startTime.getTime() >= seasonStartTime && startTime.getTime() <= seasonEndTime))) {
                            return new Result(CommonStateCode.FAIL, "该时间段内已经配置了比赛，请不要重复配置");
                    }
                }
            }

            record.setStartTime(startTime);
            record.setEndTime(endTime);
        }

        record.setSeasonName(seasonName);
        record.setBonusPoolMultiple(bonusPoolMultiple);

        record.setUpdateTime(DateUtils.getCurrentDate());

        String tagCode = confLadderMatchMapper.updateByPrimaryKey(record);

        return new Result(CommonStateCode.SUCCESS, "编辑赛季配置信息成功", NationalPKResultCode.successFlag(tagCode));
    }

    @Override
    public Result<ConfLadderMatchPageDO> getConfLadderMatchList(int pageIndex, int countPerPage) {

        if(pageIndex <= 0) {
            pageIndex = 1;
        }
        int limit = countPerPage;
        int offset = (pageIndex - 1) * limit;

        int totalCount = confLadderMatchMapper.getListCount();
        List<ConfLadderMatch> confLadderMatches = confLadderMatchMapper.getList(limit, offset);
        List<ConfLadderMatchDO> confLadderMatchDOS = Lists.newArrayList();

        if(confLadderMatches != null) {
            for (ConfLadderMatch confLadderMatch : confLadderMatches) {
                ConfLadderMatchDO confLadderMatchDO = switchToConfLadderMatchDO(confLadderMatch);
                confLadderMatchDOS.add(confLadderMatchDO);
            }
        }

        ConfLadderMatchPageDO confLadderMatchPageDO = new ConfLadderMatchPageDO();
        confLadderMatchPageDO.setTotalCount(totalCount);
        confLadderMatchPageDO.setConfLadderMatchDOS(confLadderMatchDOS);

        return new Result(CommonStateCode.SUCCESS,"获取赛季配置列表成功", confLadderMatchPageDO);
    }

    @Override
    public Result<ConfLadderMatchDO> getConfLadderMatchBySeasonId(int seasonId) {

        ConfLadderMatch confLadderMatch = confLadderMatchMapper.selectByPrimaryKey(seasonId);

        ConfLadderMatchDO confLadderMatchDO = null;
        if(confLadderMatch != null) {
            confLadderMatchDO = switchToConfLadderMatchDO(confLadderMatch);
        }
        return new Result(CommonStateCode.SUCCESS, "根据赛季id获取赛季配置信息成功", confLadderMatchDO);
    }

    @Override
    public Result<ConfLadderMatchDO> getCurrentSeasonConf() {

        ConfLadderMatchDO confLadderMatchDO;
        Integer currentSeasonId = nationalPKRelationSource.getCurrentSeasonId();

        // 如果缓存中当前赛季id为空 从数据库获取当前赛季id
        if(currentSeasonId == null) {

            ConfLadderMatch confLadderMatch = confLadderMatchMapper.getOngoingSeason(DateUtils.getCurrentDate());
            if(confLadderMatch != null) {
                currentSeasonId = confLadderMatch.getSeasonId();
                long bonusPool = resActorLadderMatchMapper.getBonusPoolShowMoneyCount(currentSeasonId);
                int bonusPoolMultiple= confLadderMatch.getBonusPoolMultiple();
                bonusPool = (long) (bonusPool * 0.015 * bonusPoolMultiple / 100);

                nationalPKRelationSource.setCurrentSeason(currentSeasonId, bonusPool);
            }
        }

        // 如果当前赛季id不为空
        if(currentSeasonId != null) {

            ConfLadderMatch confLadderMatch = confLadderMatchMapper.selectByPrimaryKey(currentSeasonId);
            if(confLadderMatch != null) {
                confLadderMatchDO = switchToConfLadderMatchDO(confLadderMatch);

                Long currentBonusPool = nationalPKRelationSource.getCurrentBonusPool();
                confLadderMatchDO.setBonusPool(currentBonusPool);
            }else {
                return new Result(CommonStateCode.FAIL, "获取当前赛季配置信息失败");
            }
        }else {
            return new Result(CommonStateCode.FAIL, "获取当前赛季配置信息失败");
        }
        return new Result(CommonStateCode.SUCCESS, "获取当前赛季配置信息成功", confLadderMatchDO);
    }

    @Override
    public Result<Boolean> setCurrentSeasonConf() {

        ConfLadderMatch confLadderMatch = confLadderMatchMapper.getOngoingSeason(DateUtils.getCurrentDate());

        if(confLadderMatch != null) {

            int currentSeasonId = confLadderMatch.getSeasonId();
            long bonusPool = resActorLadderMatchMapper.getBonusPoolShowMoneyCount(currentSeasonId);
            int bonusPoolMultiple= confLadderMatch.getBonusPoolMultiple();
            bonusPool = (long) (bonusPool * 0.015 * bonusPoolMultiple / 100);

            nationalPKRelationSource.setCurrentSeason(currentSeasonId, bonusPool);
        }
        return new Result(CommonStateCode.SUCCESS, "设置当前赛季配置信息成功", true);
    }

    private ConfLadderMatchDO switchToConfLadderMatchDO(ConfLadderMatch confLadderMatch) {

        ConfLadderMatchDO confLadderMatchDO = null;
        if(confLadderMatch != null) {
            confLadderMatchDO = BeanMapper.map(confLadderMatch, ConfLadderMatchDO.class);
            setStatusAndRemainingTime(confLadderMatchDO);
        }
        return confLadderMatchDO;
    }

    // 设置赛季的状态和赛季结束剩余时间(单位：秒)
    private void setStatusAndRemainingTime(ConfLadderMatchDO confLadderMatchDO) {

        long nowTime = System.currentTimeMillis();
        long startTime = confLadderMatchDO.getStartTime().getTime();
        long endTime = confLadderMatchDO.getEndTime().getTime();
        int status = LadderMatchStatusEnum.NOT_BEGINNING;
        long remainingTime = (endTime - nowTime) / 1000;

        if(nowTime >= startTime && nowTime <= endTime) {
            status = LadderMatchStatusEnum.ONGOING;
        }

        if(nowTime > endTime) {
            status = LadderMatchStatusEnum.OVER;
            remainingTime = -1;
        }

        confLadderMatchDO.setStatus(status);
        confLadderMatchDO.setRemainingTime(remainingTime);
    }

}

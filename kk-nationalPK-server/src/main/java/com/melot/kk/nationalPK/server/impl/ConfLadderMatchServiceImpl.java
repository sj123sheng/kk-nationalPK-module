package com.melot.kk.nationalPK.server.impl;

import com.google.common.collect.Lists;
import com.melot.kk.nationalPK.api.constant.LadderMatchStatusEnum;
import com.melot.kk.nationalPK.api.domain.DO.ConfLadderMatchDO;
import com.melot.kk.nationalPK.api.domain.DO.ConfLadderMatchPageDO;
import com.melot.kk.nationalPK.api.service.ConfLadderMatchService;
import com.melot.kk.nationalPK.server.constant.NationalPKResultCode;
import com.melot.kk.nationalPK.server.dao.ConfLadderMatchMapper;
import com.melot.kk.nationalPK.server.model.ConfLadderMatch;
import com.melot.kktv.base.CommonStateCode;
import com.melot.kktv.base.Result;
import com.melot.kktv.util.BeanMapper;
import com.melot.kktv.util.DateUtils;
import com.melot.module.kkrpc.annotation.RpcService;
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

    @Override
    public Result<Boolean> addConfLadderMatch(String seasonName, Date startTime, Date endTime, int bonusPoolMultiple) {

        Boolean result = false;
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
                ConfLadderMatchDO confLadderMatchDO = BeanMapper.map(confLadderMatch, ConfLadderMatchDO.class);
                setStatus(confLadderMatchDO);
                confLadderMatchDOS.add(confLadderMatchDO);
            }
        }

        ConfLadderMatchPageDO confLadderMatchPageDO = new ConfLadderMatchPageDO();
        confLadderMatchPageDO.setTotalCount(totalCount);
        confLadderMatchPageDO.setConfLadderMatchDOS(confLadderMatchDOS);

        return new Result(CommonStateCode.SUCCESS,"调用成功", confLadderMatchPageDO);
    }

    @Override
    public Result<ConfLadderMatchDO> getConfLadderMatchBySeasonId(int seasonId) {

        ConfLadderMatch confLadderMatch = confLadderMatchMapper.selectByPrimaryKey(seasonId);

        ConfLadderMatchDO confLadderMatchDO = null;
        if(confLadderMatch != null) {
            confLadderMatchDO = BeanMapper.map(confLadderMatch, ConfLadderMatchDO.class);
            setStatus(confLadderMatchDO);
        }
        return new Result(CommonStateCode.SUCCESS, "调用成功", confLadderMatchDO);
    }

    @Override
    public Result<ConfLadderMatchDO> getCurrentConfLadderMatch() {
        return null;
    }

    private void setStatus(ConfLadderMatchDO confLadderMatchDO) {

        long nowTime = System.currentTimeMillis();
        long startTime = confLadderMatchDO.getStartTime().getTime();
        long endTime = confLadderMatchDO.getEndTime().getTime();
        int status = LadderMatchStatusEnum.NOT_BEGINNING;

        if(nowTime >= startTime && nowTime <= endTime) {
            status = LadderMatchStatusEnum.ONGOING;
        }

        if(nowTime > endTime) {
            status = LadderMatchStatusEnum.OVER;
        }

        confLadderMatchDO.setStatus(status);
    }

}

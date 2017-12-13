package com.melot.kk.nationalPK.server.impl;

import com.google.common.collect.Lists;
import com.melot.kk.nationalPK.api.constant.GameDanEnum;
import com.melot.kk.nationalPK.api.constant.LadderMatchStatusEnum;
import com.melot.kk.nationalPK.api.domain.DO.ConfLadderMatchDO;
import com.melot.kk.nationalPK.api.domain.DO.ResActorLadderMatchDO;
import com.melot.kk.nationalPK.api.service.ConfLadderMatchService;
import com.melot.kk.nationalPK.api.service.ResActorLadderMatchService;
import com.melot.kk.nationalPK.server.config.ConfigService;
import com.melot.kk.nationalPK.server.dao.ResActorLadderMatchMapper;
import com.melot.kk.nationalPK.server.model.ResActorLadderMatch;
import com.melot.kk.nationalPK.server.redis.NationalPKRelationSource;
import com.melot.kkcore.user.service.KkUserService;
import com.melot.kktv.base.CommonStateCode;
import com.melot.kktv.base.Result;
import com.melot.kktv.util.BeanMapper;
import com.melot.module.kkrpc.annotation.RpcConsumer;
import com.melot.module.kkrpc.annotation.RpcService;
import com.melot.module.medal.driver.service.ActivityMedalService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @description: 全民PK-主播天梯赛资源接口服务
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
@RpcService(interfaceName = "com.melot.kk.nationalPK.api.service.ResActorLadderMatchService", version = "1.0.0")
public class ResActorLadderMatchServiceImpl implements ResActorLadderMatchService {

    private static Logger logger = Logger.getLogger(ResActorLadderMatchServiceImpl.class);

    @Autowired
    private ResActorLadderMatchMapper resActorLadderMatchMapper;

    @Autowired
    private ConfLadderMatchService confLadderMatchService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private NationalPKRelationSource nationalPKRelationSource;

    @RpcConsumer(version="1.1.7")
    private KkUserService kkUserService;

    @RpcConsumer(version="1.0.1")
    private ActivityMedalService activityMedalService;

    private static String SEPARATOR = ",";

    @Override
    public Result<ResActorLadderMatchDO> getResActorLadderMatch(int actorId) {

        ResActorLadderMatchDO resActorLadderMatchDO = null;
        Integer currentSeasonId = nationalPKRelationSource.getCurrentSeasonId();
        if(currentSeasonId != null) {

            ResActorLadderMatch resActorLadderMatch = resActorLadderMatchMapper.selectByPrimaryKey(actorId, currentSeasonId);
            resActorLadderMatchDO = BeanMapper.map(resActorLadderMatch, ResActorLadderMatchDO.class);

            Boolean currentSeasonOngoing = false;
            Result<ConfLadderMatchDO> result = confLadderMatchService.getConfLadderMatchBySeasonId(currentSeasonId);
            if(result.getCode().equals(CommonStateCode.SUCCESS) && result.getData() != null) {

                int currentSeasonStatus = result.getData().getStatus();
                if(currentSeasonStatus == LadderMatchStatusEnum.ONGOING) {
                    currentSeasonOngoing = true;
                }
            }
            // 设置当前赛季是否正在进行中
            resActorLadderMatchDO.setCurrentSeasonOngoing(currentSeasonOngoing);

            // 设置游戏段位
            setGameDan(resActorLadderMatchDO);

            // 设置主播排名
            Integer ranking = resActorLadderMatchMapper.getActorRanking(actorId, currentSeasonId);
            if(ranking == null) {
                ranking = 100;
            }
            resActorLadderMatchDO.setRanking(ranking);
        }
        return new Result(CommonStateCode.SUCCESS, "获取主播天梯赛当前赛季战绩资源成功", resActorLadderMatchDO);
    }

    @Override
    public Result<List<ResActorLadderMatchDO>> getLadderChart(int pageIndex, int countPerPage) {

        List<ResActorLadderMatchDO> resActorLadderMatchDOS = Lists.newArrayList();
        if(pageIndex <= 0) {
            pageIndex = 1;
        }
        int limit = countPerPage;
        int offset = (pageIndex - 1) * limit;
        Integer currentSeasonId = nationalPKRelationSource.getCurrentSeasonId();
        if(currentSeasonId != null) {
            List<ResActorLadderMatch> resActorLadderMatches = resActorLadderMatchMapper.getLadderChart(currentSeasonId, limit, offset);
            if(resActorLadderMatches != null) {

                for (int i = 0; i < resActorLadderMatches.size(); i++) {

                    ResActorLadderMatchDO resActorLadderMatchDO = BeanMapper.map(resActorLadderMatches.get(i), ResActorLadderMatchDO.class);

                    // 设置游戏段位
                    setGameDan(resActorLadderMatchDO);

                    // 设置主播排名
                    int ranking = offset + (i + 1);
                    resActorLadderMatchDO.setRanking(ranking);

                    resActorLadderMatchDOS.add(resActorLadderMatchDO);
                }
            }
        }
        return new Result(CommonStateCode.SUCCESS, "获取天梯榜成功", resActorLadderMatchDOS);
    }

    private void setGameDan(ResActorLadderMatchDO resActorLadderMatchDO) {

        int integral = resActorLadderMatchDO.getLadderMatchIntegral();
        int gameDan;
        if(integral < 100) {
            gameDan = GameDanEnum.STUBBORN_BRONZE.getId();
        }else if(integral < 180) {
            gameDan = GameDanEnum.HEROIC_SILVER.getId();
        }else if(integral < 240) {
            gameDan = GameDanEnum.GLORY_OF_GOLD.getId();
        }else if(integral < 280) {
            gameDan = GameDanEnum.PRECIOUS_PLATINUM_GOLD.getId();
        }else if(integral < 350) {
            gameDan = GameDanEnum.RESPLENDENT_DIAMOND.getId();
        }else {
            gameDan = GameDanEnum.STRONGEST_KING.getId();
        }

        resActorLadderMatchDO.setGameDan(gameDan);
        resActorLadderMatchDO.setGameDanName(GameDanEnum.parseId(gameDan).getValue());
    }
}

package com.melot.kk.nationalPK.server.impl;

import com.melot.kk.nationalPK.api.constant.LadderMatchStatusEnum;
import com.melot.kk.nationalPK.api.domain.DO.ConfLadderMatchDO;
import com.melot.kk.nationalPK.api.domain.DO.ResActorLadderMatchDO;
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

            int currentSeasonStatus = nationalPKRelationSource.getCurrentSeasonStatus();
            if(currentSeasonStatus == LadderMatchStatusEnum.ONGOING) {
                resActorLadderMatchDO.setCurrentSeasonOngoing(true);
            }else {
                resActorLadderMatchDO.setCurrentSeasonOngoing(false);
            }
        }
        return new Result(CommonStateCode.SUCCESS, "获取主播天梯赛当前赛季战绩资源成功", resActorLadderMatchDO);
    }

    @Override
    public Result<List<ResActorLadderMatchDO>> getLadderChart(int pageIndex, int countPerPage) {
        return null;
    }
}

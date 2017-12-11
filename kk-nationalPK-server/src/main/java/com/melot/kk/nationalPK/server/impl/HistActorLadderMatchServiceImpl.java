package com.melot.kk.nationalPK.server.impl;


import com.melot.kk.nationalPK.api.domain.DO.AddHisActorLadderMatchResultDO;
import com.melot.kk.nationalPK.api.domain.DO.HistActorLadderMatchDO;
import com.melot.kk.nationalPK.api.service.ConfLadderMatchService;
import com.melot.kk.nationalPK.api.service.HistActorLadderMatchService;
import com.melot.kk.nationalPK.server.config.ConfigService;
import com.melot.kk.nationalPK.server.util.SendMsgUtil;
import com.melot.kkcore.user.service.KkUserService;
import com.melot.kktv.base.Result;
import com.melot.module.kkrpc.annotation.RpcConsumer;
import com.melot.module.kkrpc.annotation.RpcService;
import com.melot.module.medal.driver.service.ActivityMedalService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @description: 全民PK主播天梯赛历史记录接口服务
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
@RpcService(interfaceName = "com.melot.kk.nationalPK.api.service.HistActorLadderMatchService", version = "1.0.0")
public class HistActorLadderMatchServiceImpl implements HistActorLadderMatchService {

    private static Logger logger = Logger.getLogger(HistActorLadderMatchServiceImpl.class);

    @Autowired
    private ConfLadderMatchService confLadderMatchService;

    @Autowired
    private ConfigService configService;

    @RpcConsumer(version="1.1.7")
    private KkUserService kkUserService;

    @RpcConsumer(version="1.0.1")
    private ActivityMedalService activityMedalService;

    @Autowired
    private SendMsgUtil sendMsgUtil;

    private static String SEPARATOR = ",";

    @Override
    public Result<AddHisActorLadderMatchResultDO> addHistActorLadderMatch(int actorId, int opponentActorId, long receiveShowMoney, long opponentReceiveShowMoney, int pkId) {
        return null;
    }

    @Override
    public Result<List<HistActorLadderMatchDO>> getRecentHistActorLadderMatchList(int actorId) {
        return null;
    }
}

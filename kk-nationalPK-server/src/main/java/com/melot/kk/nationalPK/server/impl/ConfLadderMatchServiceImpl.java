package com.melot.kk.nationalPK.server.impl;

import com.melot.kk.nationalPK.api.domain.DO.ConfLadderMatchDO;
import com.melot.kk.nationalPK.api.domain.DO.ConfLadderMatchPageDO;
import com.melot.kk.nationalPK.api.service.ConfLadderMatchService;
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

import java.util.Date;


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
    public Result<Boolean> addConfLadderMatch(String seasonName, Date startTime, Date endTime, int bonusPoolMultiple) {
        return null;
    }

    @Override
    public Result<Boolean> editConfLadderMatch(int seasonId, String seasonName, Date startTime, Date endTime, int bonusPoolMultiple) {
        return null;
    }

    @Override
    public Result<ConfLadderMatchPageDO> getConfLadderMatchList(int pageIndex, int countPerPage) {
        return null;
    }

    @Override
    public Result<ConfLadderMatchDO> getConfLadderMatchBySeasonId(int seasonId) {
        return null;
    }

    @Override
    public Result<ConfLadderMatchDO> getCurrentConfLadderMatch() {
        return null;
    }
}

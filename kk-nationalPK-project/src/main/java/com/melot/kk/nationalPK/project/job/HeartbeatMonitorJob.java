package com.melot.kk.nationalPK.project.job;

import com.google.gson.Gson;
import com.melot.common.melot_utils.TenantContext;
import com.melot.job.api.SimpleJob;
import com.melot.kk.nationalPK.api.domain.DO.ConfLadderMatchDO;
import com.melot.kk.nationalPK.api.service.ConfLadderMatchService;
import com.melot.kkcore.actor.service.ActorService;
import com.melot.kktv.base.CommonStateCode;
import com.melot.kktv.base.Result;
import com.melot.module.kkrpc.annotation.RpcConsumer;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Title:
 * <p>
 * Description: 直播精灵心跳监控
 * </p>
 *
 * @author shengjain
 * @version V1.0
 * @since 2017/10/18.
 */
@Component
public class HeartbeatMonitorJob implements SimpleJob {

    private static Logger logger = Logger.getLogger(HeartbeatMonitorJob.class);

    @RpcConsumer
    private ConfLadderMatchService confLadderMatchService;

    @RpcConsumer(version = "1.0.8")
    private ActorService actorService;

    @Override
    public void execute() {

        int i = 0;
        Result<List<ConfLadderMatchDO>> result = null;//ladderMatchService.getRecentRecordsByRoomId(16864055);

        if(result.getCode().equals(CommonStateCode.SUCCESS) && result.getData() != null) {
            i++;
            logger.info(i + "tenantId：" + TenantContext.getContext().getTenantId() + "  catchDollRecordDOResult: " + new Gson().toJson(result));
        }
    }
}

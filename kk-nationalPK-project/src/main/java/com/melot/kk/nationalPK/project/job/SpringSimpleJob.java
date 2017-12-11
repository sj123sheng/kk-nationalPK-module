package com.melot.kk.nationalPK.project.job;

import com.google.gson.Gson;
import com.melot.common.melot_utils.TenantContext;
import com.melot.job.api.SimpleJob;
import com.melot.kk.nationalPK.api.service.ConfLadderMatchService;
import com.melot.kkcore.actor.api.RoomInfo;
import com.melot.kkcore.actor.service.ActorService;
import com.melot.module.kkrpc.annotation.RpcConsumer;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

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
public class SpringSimpleJob implements SimpleJob {

    private static Logger logger = Logger.getLogger(SpringSimpleJob.class);

    @RpcConsumer
    private ConfLadderMatchService confLadderMatchService;

    @RpcConsumer(version = "1.0.8")
    private ActorService actorService;

    int i = 0;

    @Override
    public void execute() {

        RoomInfo roomInfo = actorService.getRoomInfoById(54925668);

        i++;
        logger.info(i + "tenantId：" + TenantContext.getContext().getTenantId() + " roomInfo: " + new Gson().toJson(roomInfo));

    }
}

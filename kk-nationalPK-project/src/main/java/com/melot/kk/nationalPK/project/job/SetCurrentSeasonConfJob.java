package com.melot.kk.nationalPK.project.job;

import com.melot.job.api.SimpleJob;
import com.melot.kk.nationalPK.api.service.ConfLadderMatchService;
import com.melot.module.kkrpc.annotation.RpcConsumer;
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
public class SetCurrentSeasonConfJob implements SimpleJob {

    @RpcConsumer
    private ConfLadderMatchService confLadderMatchService;

    @Override
    public void execute() {

        confLadderMatchService.setCurrentSeasonConf();
    }
}

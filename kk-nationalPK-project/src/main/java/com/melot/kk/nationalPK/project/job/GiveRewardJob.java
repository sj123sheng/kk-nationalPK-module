package com.melot.kk.nationalPK.project.job;

import com.melot.job.api.SimpleJob;
import com.melot.kk.nationalPK.api.constant.GiveRewardStatusEnum;
import com.melot.kk.nationalPK.api.constant.LadderMatchStatusEnum;
import com.melot.kk.nationalPK.api.domain.DO.ConfLadderMatchDO;
import com.melot.kk.nationalPK.api.service.ConfLadderMatchService;
import com.melot.kk.nationalPK.api.service.ResActorLadderMatchService;
import com.melot.kktv.base.CommonStateCode;
import com.melot.kktv.base.Result;
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
public class GiveRewardJob implements SimpleJob {

    private static Logger logger = Logger.getLogger(GiveRewardJob.class);

    @RpcConsumer
    private ConfLadderMatchService confLadderMatchService;

    @RpcConsumer
    private ResActorLadderMatchService resActorLadderMatchService;

    @Override
    public void execute() {

        Result<ConfLadderMatchDO> result = confLadderMatchService.getCurrentSeasonConf();

        if(result.getCode().equals(CommonStateCode.SUCCESS) && result.getData() != null) {

            ConfLadderMatchDO confLadderMatchDO = result.getData();
            int seasonId = confLadderMatchDO.getSeasonId();
            int status = confLadderMatchDO.getStatus();
            int giveReward = confLadderMatchDO.getGiveReward();

            if(status == LadderMatchStatusEnum.OVER && giveReward == GiveRewardStatusEnum.NOT_GIVE_REWARD) {

                logger.info("开始发放天梯赛奖励 赛季id: " + seasonId);

                resActorLadderMatchService.giveReward(seasonId);

                logger.info("发放天梯赛奖励完成 赛季id: " + seasonId);
            }
        }
    }
}

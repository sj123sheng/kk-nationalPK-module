package com.melot.kk.nationalPK.project.job;

import com.alibaba.rocketmq.common.message.MessageExt;
import com.melot.common.melot_utils.TenantContext;
import com.melot.common.transaction.history.ActionHistory;
import com.melot.common.transaction.processor.ActionHistoryProcessor;
import com.melot.common.transaction.processor.KKHistoryProcessor;
import com.melot.job.api.DigInfo;
import com.melot.job.api.OneTimeJob;
import com.melot.job.api.StatusEnum;
import com.melot.kk.nationalPK.api.service.ConfLadderMatchService;
import com.melot.kkcore.actor.api.RoomInfo;
import com.melot.kkcore.actor.service.ActorService;
import com.melot.module.kkrpc.annotation.RpcConsumer;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 读文件启动consumer形式
 * @author admin
 *
 */
@Component
public class SpringOneTimeJob implements OneTimeJob {

	private static Logger logger = Logger.getLogger(SpringOneTimeJob.class);

    @RpcConsumer
    private ConfLadderMatchService confLadderMatchService;

    @RpcConsumer(version = "1.0.8")
    private ActorService actorService;
	
	@Override
	public void process() {

        DigInfo.addHit();
        DigInfo.setStatus(StatusEnum.SUCCESS);
        RoomInfo roomInfo = actorService.getRoomInfoById(54925668);

        logger.info("tenantId：" + TenantContext.getContext().getTenantId() + " --------------->start elastic job...");

        ActionHistoryProcessor processor = new ActionHistoryProcessor() {
			@Override
			public boolean process(MessageExt msg, ActionHistory actionHistory) {
				logger.info(msg.toString());
                DigInfo.addHit();
				return true;
			}

		};
        @SuppressWarnings("unused")
		KKHistoryProcessor kkhistoryConsumer = new KKHistoryProcessor(
				"actionconsumer.properties", processor);
	}
}

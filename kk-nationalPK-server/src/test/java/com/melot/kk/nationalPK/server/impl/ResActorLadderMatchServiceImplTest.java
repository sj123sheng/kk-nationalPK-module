package com.melot.kk.nationalPK.server.impl;

import com.google.gson.Gson;
import com.melot.kk.nationalPK.api.domain.DO.ShowMoneyGiveRewardDO;
import com.melot.kk.nationalPK.api.service.ResActorLadderMatchService;
import com.melot.kktv.base.Result;
import com.melot.module.kkrpc.annotation.RpcConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description: ResActorLadderMatchServiceImplTest
 * @author: shengjian
 * @date: 2018/1/8
 * @copyright: Copyright (c)2018
 * @company: melot
 * <p>
 * Modification History:
 * Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2018/1/8           shengjian     1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ResActorLadderMatchServiceImplTest {

    @RpcConsumer
    ResActorLadderMatchService resActorLadderMatchService;

    int seasonId = 45;

    @Test
    public void getResActorLadderMatch() {
    }

    @Test
    public void getLadderChart() {
    }

    @Test
    public void giveReward() {
    }

    @Test
    public void getShowMoneyGiveRewardList() {
        Result<ShowMoneyGiveRewardDO> result = resActorLadderMatchService.getShowMoneyGiveRewardList(seasonId);
        System.out.println(new Gson().toJson(result));
    }

    @Test
    public void giveRewardShowMoney() {
        Result<Boolean> result = resActorLadderMatchService.giveRewardShowMoney(seasonId);
        System.out.println(new Gson().toJson(result));
    }
}
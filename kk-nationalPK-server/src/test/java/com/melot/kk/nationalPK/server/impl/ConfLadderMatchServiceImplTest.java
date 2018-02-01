package com.melot.kk.nationalPK.server.impl;

import com.google.gson.Gson;
import com.melot.kk.nationalPK.api.domain.DO.ConfLadderMatchDO;
import com.melot.kk.nationalPK.api.domain.DO.ConfLadderMatchPageDO;
import com.melot.kk.nationalPK.api.service.ConfLadderMatchService;
import com.melot.kktv.base.Result;
import com.melot.module.kkrpc.annotation.RpcConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description: ConfLadderMatchServiceImplTest
 * @author: shengjian
 * @date: 2018/1/3
 * @copyright: Copyright (c)2018
 * @company: melot
 * <p>
 * Modification History:
 * Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2018/1/3           shengjian     1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfLadderMatchServiceImplTest {

    @RpcConsumer(version="1.0.0")
    ConfLadderMatchService confLadderMatchService;


    @Test
    public void getConfLadderMatchList() {

        Result<ConfLadderMatchPageDO> result = confLadderMatchService.getConfLadderMatchList(1,10);
        System.out.println(new Gson().toJson(result));
    }

    @Test
    public void getConfLadderMatchBySeasonId() {

        Result<ConfLadderMatchDO> result = confLadderMatchService.getConfLadderMatchBySeasonId(1);
        System.out.println(new Gson().toJson(result));
    }
}
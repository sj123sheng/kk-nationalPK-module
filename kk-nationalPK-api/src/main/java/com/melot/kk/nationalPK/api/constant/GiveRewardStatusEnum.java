package com.melot.kk.nationalPK.api.constant;

/**
 * Description: GiveRewardStatusEnum 天梯赛赛季奖励是否发放状态
 *
 * @author:     shengjian
 * @version:    1.0
 * Filename:    GiveRewardStatusEnum.java
 * Create at:   2017-12-07
 *
 * Copyright:   Copyright (c)2015
 * Company:     melot
 *
 * Modification History:
 * Date            Author      Version     Description
 * ------------------------------------------------------------------
 * 2017-12-07      shengjian      1.0         1.0 Version
 */
public class GiveRewardStatusEnum {

    private GiveRewardStatusEnum() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 未发放
     */
    public static final int NOT_GIVE_REWARD = 0;

    /**
     * 已发放
     */
    public static final int ALREADY_GIVE_REWARD = 1;

    /**
     * 已结算待发放
     */
    public static final int WAIT_GIVE_REWARD = 2;

}
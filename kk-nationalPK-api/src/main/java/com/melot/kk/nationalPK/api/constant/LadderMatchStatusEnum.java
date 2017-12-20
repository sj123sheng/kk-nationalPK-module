package com.melot.kk.nationalPK.api.constant;

/**
 * Description: LadderMatchStatusEnum 天梯赛赛季状态
 *
 * @author:     shengjian
 * @version:    1.0
 * Filename:    LadderMatchStatusEnum.java
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
public class LadderMatchStatusEnum {

    private LadderMatchStatusEnum() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 未开始
     */
    public static final int NOT_BEGINNING = 0;

    /**
     * 进行中
     */
    public static final int ONGOING = 1;

    /**
     * 已结束
     */
    public static final int OVER = 2;

}
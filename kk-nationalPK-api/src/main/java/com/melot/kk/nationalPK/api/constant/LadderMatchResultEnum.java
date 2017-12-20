package com.melot.kk.nationalPK.api.constant;

/**
 * Description: LadderMatchResultEnum 天梯赛比赛结果枚举
 *
 * @author:     shengjian
 * @version:    1.0
 * Filename:    LadderMatchResultEnum.java
 * Create at:   2017-12-14
 *
 * Copyright:   Copyright (c)2015
 * Company:     melot
 *
 * Modification History:
 * Date            Author      Version     Description
 * ------------------------------------------------------------------
 * 2017-12-14     shengjian      1.0         1.0 Version
 */
public class LadderMatchResultEnum {

    private LadderMatchResultEnum() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 胜利
     */
    public static final int WIN = 1;

    /**
     * 失败
     */
    public static final int FAIL = 2;

    /**
     * 平局
     */
    public static final int TIE = 3;

}
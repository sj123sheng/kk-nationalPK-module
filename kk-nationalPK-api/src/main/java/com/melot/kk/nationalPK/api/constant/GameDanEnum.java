package com.melot.kk.nationalPK.api.constant;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description: GameDanEnum 全民PK 天梯赛游戏段位
 *
 * @author:     shengjian
 * @version:    1.0
 * Filename:    GameDanEnum.java
 * Create at:   2017-12-07
 *
 * Copyright:   Copyright (c)2015
 * Company:     melot
 *
 * Modification History:
 * Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2017-12-07      shengjian      1.0         1.0 Version
 */
public enum GameDanEnum {

    STUBBORN_BRONZE(1, "倔强青铜"),
    HEROIC_SILVER(2, "英勇白银"),
    GLORY_OF_GOLD(3, "荣耀黄金"),
    PRECIOUS_PLATINUM_GOLD(4, "华贵铂金"),
    RESPLENDENT_DIAMOND(5, "璀璨钻石"),
    STRONGEST_KING(6, "最强王者");

    private int id;
    private String value;
    private static List<Integer> allFlags = Lists.newArrayList();

    GameDanEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }

    private static Map<Integer, GameDanEnum> gameDanEnumIdMap = Maps.newHashMap();
    private static Map<String, GameDanEnum> gameDanEnumNameMap = Maps.newHashMap();

    static {
        for (GameDanEnum gameDanEnumEnum : GameDanEnum.values()) {
            gameDanEnumNameMap.put(gameDanEnumEnum.name().toLowerCase(), gameDanEnumEnum);
            gameDanEnumIdMap.put(gameDanEnumEnum.id, gameDanEnumEnum);
            allFlags.add(gameDanEnumEnum.getId());
        }
    }

    public static GameDanEnum parseId(int id) {
        return gameDanEnumIdMap.get(id);
    }

    public static GameDanEnum parseName(String name) {
        return (null != name) ? gameDanEnumNameMap.get(name.toLowerCase()) : null;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return this.name();
    }

    public int getId() {
        return id;
    }

    public static List<Integer> getAllFlags() {
        return allFlags;
    }

    public static List<String> getNames() {
        List<String> names = new ArrayList<String>();
        for (GameDanEnum gameDanEnumEnum : GameDanEnum.values()) {
            names.add(gameDanEnumEnum.name());
        }
        return names;
    }
}

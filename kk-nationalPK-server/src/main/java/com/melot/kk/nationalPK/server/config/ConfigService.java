package com.melot.kk.nationalPK.server.config;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import org.springframework.stereotype.Service;

/**
 * @description: ConfigService
 * @author: shengjian
 * @date: 2017/8/25
 * @copyright: Copyright (c)2017
 * @company: melot
 * <p>
 * Modification History:
 * Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2017/12/11       shengjian     1.0
 */
@Service
@DisconfFile(filename = "config.properties", copy2TargetDirPath = "conf")
public class ConfigService {

    // 欢乐PK-天梯赛勋章过期时间(单位:天)
    private int medalDeadline;

    // 倔强青铜勋章id
    private int stubbornBronzeMedalId;

    // 英勇白银勋章id
    private int heroicSilverMedalId;

    // 荣耀黄金勋章id
    private int gloryOfGoldMedalId;

    // 华贵铂金勋章id
    private int preciousPlatinumGoldMedalId;

    // 璀璨钻石勋章id
    private int resplendentDiamondMedalId;

    // 最强王者勋章id
    private int strongestKingMedalId;

    // 通知相关人员发放奖励情况的邮件列表
    private String toMails;

    @DisconfFileItem(name = "to.mails", associateField = "toMails")
    public String getToMails() {
        return toMails;
    }

    @DisconfFileItem(name = "medal.deadline", associateField = "medalDeadline")
    public int getMedalDeadline() {
        return medalDeadline;
    }

    @DisconfFileItem(name = "stubbornBronze.medalId", associateField = "stubbornBronzeMedalId")
    public int getStubbornBronzeMedalId() {
        return stubbornBronzeMedalId;
    }

    @DisconfFileItem(name = "heroicSilver.medalId", associateField = "heroicSilverMedalId")
    public int getHeroicSilverMedalId() {
        return heroicSilverMedalId;
    }

    @DisconfFileItem(name = "gloryOfGold.medalId", associateField = "gloryOfGoldMedalId")
    public int getGloryOfGoldMedalId() {
        return gloryOfGoldMedalId;
    }

    @DisconfFileItem(name = "preciousPlatinumGold.medalId", associateField = "preciousPlatinumGoldMedalId")
    public int getPreciousPlatinumGoldMedalId() {
        return preciousPlatinumGoldMedalId;
    }

    @DisconfFileItem(name = "resplendentDiamond.medalId", associateField = "resplendentDiamondMedalId")
    public int getResplendentDiamondMedalId() {
        return resplendentDiamondMedalId;
    }

    @DisconfFileItem(name = "strongestKing.medalId", associateField = "strongestKingMedalId")
    public int getStrongestKingMedalId() {
        return strongestKingMedalId;
    }

}


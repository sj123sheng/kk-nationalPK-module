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
    private String medalDeadline;

    @DisconfFileItem(name = "medal.deadline", associateField = "medalDeadline")
    public String getMedalDeadline() {
        return medalDeadline;
    }

}


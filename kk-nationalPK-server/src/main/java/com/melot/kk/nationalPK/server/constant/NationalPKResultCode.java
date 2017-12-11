package com.melot.kk.nationalPK.server.constant;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 欢乐PK模块的result的错误码
 */
public class NationalPKResultCode {

    private NationalPKResultCode() {}
    
    public static final String SUCCESS = "00000000";
    
    public static final String ERROR_PARMETER = "5106020201";               //参数错误
    public static final String ERROR_SQL = "510602020102";                  //SQL错误
    public static final String ERROR_MODULE = "510602020103";               //模块错误
    public static final String ERROR_CONFIG = "510602020104";               //配置信息错误
    public static final String ERROR_REDIS = "510602020105";                //Redis异常

    public static final String ERROR_OFF_SHOW_MONEY = "5106021106";       //秀币扣除失败
    public static final String ERROR_PALYING = "5106021107";              //该房间正在进行游戏
    public static final String ERROR_FIND_MACHINE_INFO = "5106021108";    //找不到该房间对应的娃娃机信息
    public static final String ERROR_CREATE_CL_ACCOUNT = "5106021109";    //创建第三方的CL账户失败
    public static final String ERROR_CREATE_GAME_RECORD = "5106021110";   //创建游戏记录失败
    public static final String ERROR_ROOM_CAMERA = "5106021111";          //房间摄像头出错(已经下播)
    public static final String ERROR_DOLL_MACHINE_STATUS = "5106021112";  //该房间娃娃机状态异常

    private static Map<String, String> resultMap = new HashMap<>();

    static {
        resultMap.put(SUCCESS, "ok");
        resultMap.put(ERROR_PARMETER, "参数校验失败");
        resultMap.put(ERROR_SQL, "存储过程调用失败");
        resultMap.put(ERROR_MODULE, "调用模块产生错误");
        resultMap.put(ERROR_CONFIG, "配置信息异常");
        resultMap.put(ERROR_REDIS, "Redis异常");
    }

    public static String getMsg(String code) {
        String ret = resultMap.get(code);
        if (ret == null) {
            return "未定义的错误码:" + code;
        } else {
            return ret;
        }

    }

    public static boolean successFlag(String code) {

        if (StringUtils.isNotEmpty(code) && code.equals(SUCCESS)) {
            return true;
        }

        return false;
    }
}

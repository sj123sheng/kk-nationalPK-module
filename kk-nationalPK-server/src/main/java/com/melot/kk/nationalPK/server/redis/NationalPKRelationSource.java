package com.melot.kk.nationalPK.server.redis;

import com.melot.common.melot_jedis.JedisWrapper;
import com.site.lookup.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class NationalPKRelationSource {

	private static final int EXPIRE_TIME = 7 * 24 * 3600;

	private static final String DOLL_MACHINE_SUFFIX = "_doll_machine";

    private static final String DOLL_MACHINE_STATUS = "doll_machine_status";

    private static final String DOLL_MACHINE_USERID = "doll_machine_userId";

    private static final String DOLL_MACHINE_START_GAME_TIME = "doll_machine_start_game_time";

    private static final String CATCH_DOLL_RECORD_ID = "catch_doll_record_id";

    private static final String RECENT_HEARTBEAT_STATUS_SUFFIX = "_recent_heartbeat_status";

    private static final String START_GAME_SUFFIX = "_start_game";

	@Autowired
	@Qualifier("nationalPKdata")
	private JedisWrapper nationalPKRelationJedis;
	
	private static JedisWrapper jedisProxy;

    private static JedisWrapper.HashMap jedisHashMap;

	/**
	 * 初始化操作
	 * */
	@PostConstruct
	private void init() {
		try {
			jedisProxy = nationalPKRelationJedis;
            jedisHashMap = jedisProxy.HASH;
		} catch (Exception e) {
		}
	}

	private static String getDollMachineKey(int roomId) {
	    return roomId + DOLL_MACHINE_SUFFIX;
    }

	public Integer getDollMachineStatus(int roomId) {
	    String key = getDollMachineKey(roomId);
        String statusStr = jedisHashMap.hget(key, DOLL_MACHINE_STATUS);
        Integer status = null;
        if(StringUtils.isNotEmpty(statusStr)) {
            status = Integer.parseInt(statusStr);
        }
        return status;
	}

    public Integer getDollMachineUserId(int roomId) {
        String key = getDollMachineKey(roomId);
        String userIdStr = jedisHashMap.hget(key, DOLL_MACHINE_USERID);
        Integer userId = null;
        if(StringUtils.isNotEmpty(userIdStr)) {
            userId = Integer.parseInt(userIdStr);
        }
        return userId;
    }

    public Long getDollMachineStartGameTime(int roomId) {
        String key = getDollMachineKey(roomId);
        String startGameTimeStr = jedisHashMap.hget(key, DOLL_MACHINE_START_GAME_TIME);
        Long startGameTime = null;
        if(StringUtils.isNotEmpty(startGameTimeStr)) {
            startGameTime = Long.parseLong(startGameTimeStr);
        }
        return startGameTime;
    }

    public Integer getCatchDollRecordId(int roomId) {
        String key = getDollMachineKey(roomId);
        String catchDollRecordIdStr = jedisHashMap.hget(key, CATCH_DOLL_RECORD_ID);
        Integer catchDollRecordId = null;
        if(StringUtils.isNotEmpty(catchDollRecordIdStr)) {
            catchDollRecordId = Integer.parseInt(catchDollRecordIdStr);
        }
        return catchDollRecordId;
    }

    public void setRedisDollMachine(int roomId, int dollMachineStatus, int userId, int catchDollRecordId) {
        String key = getDollMachineKey(roomId);
        jedisHashMap.hset(key, DOLL_MACHINE_STATUS , dollMachineStatus + "");
        jedisHashMap.hset(key, DOLL_MACHINE_USERID , userId + "");
        jedisHashMap.hset(key, DOLL_MACHINE_START_GAME_TIME , System.currentTimeMillis() + "");
        jedisHashMap.hset(key, CATCH_DOLL_RECORD_ID , catchDollRecordId + "");
        jedisProxy.expire(key, EXPIRE_TIME);
    }

    public long setDollMachineStatus(int roomId, int dollMachineStatus) {
        String key = getDollMachineKey(roomId);
        long count = jedisHashMap.hset(key, DOLL_MACHINE_STATUS , dollMachineStatus + "");
        jedisProxy.expire(key, EXPIRE_TIME);
        return count;
    }

    public Boolean existsStartGame(int roomId) {
        String key = getStartGameKey(roomId);
        long num = jedisProxy.STRINGS.incrBy(key, 1);
        jedisProxy.KEYS.expire(key, 5);
        if(num > 1) {
            return true;
        }
        return false;
    }

    private static String getStartGameKey(int roomId) {
        return roomId + START_GAME_SUFFIX;
    }

    public void setRecentHeartbeatStatus(int dollMachineId, int cameraId, int pushFlowStatus) {
        String key = getRecentHeartbeatTimeKey(dollMachineId, cameraId);
        jedisProxy.STRINGS.setEx(key , 70, pushFlowStatus + "");
    }

    public Integer getRecentHeartbeatStatus(int dollMachineId, int cameraId) {
        String key = getRecentHeartbeatTimeKey(dollMachineId, cameraId);
        String recentHeartbeatStatusStr = jedisProxy.STRINGS.get(key);
        Integer recentHeartbeatStatus = null;
        if(StringUtils.isNotEmpty(recentHeartbeatStatusStr)) {
            recentHeartbeatStatus = Integer.parseInt(recentHeartbeatStatusStr);
        }
        return recentHeartbeatStatus;
    }

    public Boolean existsRecentHeartbeatStatus(int dollMachineId, int cameraId) {
        String key = getRecentHeartbeatTimeKey(dollMachineId, cameraId);
        return jedisProxy.KEYS.exists(key);
    }


    private static String getRecentHeartbeatTimeKey(int dollMachineId, int cameraId) {
        return dollMachineId + "_" + cameraId + RECENT_HEARTBEAT_STATUS_SUFFIX;
    }

}

package com.melot.kk.nationalPK.server.redis;

import com.melot.common.melot_jedis.JedisWrapper;
import com.site.lookup.util.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class NationalPKRelationSource {

    private static Logger logger = Logger.getLogger(NationalPKRelationSource.class);

    private static final int EXPIRE_TIME = 3 * 31 * 24 * 3600;

	// 天梯赛当前赛季key
	private static final String CURRENT_SEASON_KEY = "current_season_key";

    // 当前赛季id
    private static final String CURRENT_SEASON_ID = "current_season_id";

    // 当前赛季奖金池秀币总数量
    private static final String CURRENT_BONUS_POOL = "current_bonus_pool";

    // 开始发放奖励key后缀
    private static final String START_GIVE_REWARD_SUFFIX = "_start_give_reward";

	@Autowired
	@Qualifier("nationalPKdata")
	private JedisWrapper nationalPKRelationJedis;
	
	private JedisWrapper jedisProxy;

    private JedisWrapper.HashMap jedisHashMap;

	/**
	 * 初始化操作
	 * */
	@PostConstruct
	private void init() {
		try {
			jedisProxy = nationalPKRelationJedis;
            jedisHashMap = jedisProxy.HASH;
		} catch (Exception e) {
            logger.error("jedisProxy初始化失败", e);
		}
	}

	public Integer getCurrentSeasonId() {

        String currentSeasonIdStr = jedisHashMap.hget(CURRENT_SEASON_KEY, CURRENT_SEASON_ID);
        Integer currentSeasonId = null;
        if(StringUtils.isNotEmpty(currentSeasonIdStr)) {
            currentSeasonId = Integer.parseInt(currentSeasonIdStr);
        }
        return currentSeasonId;
	}

    public Long getCurrentBonusPool() {

        String currentBonusPoolStr = jedisHashMap.hget(CURRENT_SEASON_KEY, CURRENT_BONUS_POOL);
        Long currentBonusPool = null;
        if(StringUtils.isNotEmpty(currentBonusPoolStr)) {
            currentBonusPool = Long.parseLong(currentBonusPoolStr);
        }
        return currentBonusPool;
    }

    public void setCurrentSeason(int currentSeasonId, long currentBonusPool) {

        jedisHashMap.hset(CURRENT_SEASON_KEY, CURRENT_SEASON_ID , String.valueOf(currentSeasonId));
        jedisHashMap.hset(CURRENT_SEASON_KEY, CURRENT_BONUS_POOL , String.valueOf(currentBonusPool));
        jedisProxy.expire(CURRENT_SEASON_KEY, EXPIRE_TIME);
    }

    /**
     * 该主播是否已经开始发放奖励 防止多线程同一时刻并发执行发放奖励程序导致一个主播发放多次情况
     * true-已经在开始发放奖励(当前线程检测到true 不能再去发放奖励)
     * false-还没开始发放奖励(当前线程检测到false 可以去发放奖励)
     */
    public Boolean startGiveReward(int actorId) {

        String startGiveRewardKey = getStartGiveRewardKey(actorId);

        long num = jedisProxy.STRINGS.incrBy(startGiveRewardKey, 1);

        jedisProxy.KEYS.expire(startGiveRewardKey, 5);
        if(num > 1) {
            return true;
        }
        return false;
    }

    private static String getStartGiveRewardKey(int actorId) {
        return actorId + START_GIVE_REWARD_SUFFIX;
    }

}

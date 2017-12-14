package com.melot.kk.nationalPK.server.impl;


import com.google.common.collect.Lists;
import com.melot.kk.nationalPK.api.constant.GameDanEnum;
import com.melot.kk.nationalPK.api.constant.GiveRewardStatusEnum;
import com.melot.kk.nationalPK.api.constant.LadderMatchResultEnum;
import com.melot.kk.nationalPK.api.constant.LadderMatchStatusEnum;
import com.melot.kk.nationalPK.api.domain.DO.AddHisActorLadderMatchResultDO;
import com.melot.kk.nationalPK.api.domain.DO.ConfLadderMatchDO;
import com.melot.kk.nationalPK.api.domain.DO.HistActorLadderMatchDO;
import com.melot.kk.nationalPK.api.service.ConfLadderMatchService;
import com.melot.kk.nationalPK.api.service.HistActorLadderMatchService;
import com.melot.kk.nationalPK.server.dao.HistActorLadderMatchMapper;
import com.melot.kk.nationalPK.server.dao.ResActorLadderMatchMapper;
import com.melot.kk.nationalPK.server.model.HistActorLadderMatch;
import com.melot.kk.nationalPK.server.model.ResActorLadderMatch;
import com.melot.kk.nationalPK.server.redis.NationalPKRelationSource;
import com.melot.kktv.base.CommonStateCode;
import com.melot.kktv.base.Result;
import com.melot.kktv.util.BeanMapper;
import com.melot.kktv.util.DateUtils;
import com.melot.module.kkrpc.annotation.RpcService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * @description: 全民PK主播天梯赛历史记录接口服务
 * @author: shengjian
 * @date: 2017/10/20
 * @copyright: Copyright (c)2017
 * @company: melot
 * <p>
 * Modification History:
 * Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2017/10/20       shengjian     1.0
 */

@Service
@RpcService(interfaceName = "com.melot.kk.nationalPK.api.service.HistActorLadderMatchService", version = "1.0.0")
public class HistActorLadderMatchServiceImpl implements HistActorLadderMatchService {

    private static Logger logger = Logger.getLogger(HistActorLadderMatchServiceImpl.class);

    @Autowired
    private ConfLadderMatchService confLadderMatchService;

    @Autowired
    private HistActorLadderMatchMapper histActorLadderMatchMapper;

    @Autowired
    private ResActorLadderMatchMapper resActorLadderMatchMapper;

    @Autowired
    private NationalPKRelationSource nationalPKRelationSource;

    @Override
    public Result<AddHisActorLadderMatchResultDO> addHistActorLadderMatch(int actorId, int opponentActorId, long receiveShowMoney, long opponentReceiveShowMoney, int pkId) {

        Result<ConfLadderMatchDO> result = confLadderMatchService.getCurrentSeasonConf();
        if(result.getCode().equals(CommonStateCode.SUCCESS) && result.getData() != null) {

            ConfLadderMatchDO confLadderMatchDO = result.getData();
            int currentSeasonId = confLadderMatchDO.getSeasonId();
            int currentSeasonStatus = confLadderMatchDO.getStatus();
            // 如果天梯赛正在进行中 保存主播天梯赛记录
            if(currentSeasonStatus == LadderMatchStatusEnum.ONGOING) {

                // 获取本人的比赛结果
                int ladderMatchResult = LadderMatchResultEnum.TIE;
                if(receiveShowMoney > opponentReceiveShowMoney) {
                    ladderMatchResult = LadderMatchResultEnum.WIN;
                }else if(receiveShowMoney < opponentReceiveShowMoney) {
                    ladderMatchResult = LadderMatchResultEnum.FAIL;
                }

                // 获取对手的比赛结果
                int opponentLadderMatchResult = LadderMatchResultEnum.TIE;
                if(opponentReceiveShowMoney > receiveShowMoney) {
                    opponentLadderMatchResult = LadderMatchResultEnum.WIN;
                }else if(opponentReceiveShowMoney < receiveShowMoney) {
                    opponentLadderMatchResult = LadderMatchResultEnum.FAIL;
                }

                // 计算本人的获得分值
                int receiveScore = calculationReceiveScore(actorId, receiveShowMoney, currentSeasonId, ladderMatchResult);

                // 计算对手的获得分值
                int opponentReceiveScore = calculationReceiveScore(opponentActorId, opponentReceiveShowMoney, currentSeasonId, opponentLadderMatchResult);

                // 新增本人主播天梯赛历史记录并且新增或更新本人主播天梯赛资源
                addActorHistRecord(actorId, opponentActorId, receiveShowMoney, opponentReceiveShowMoney,
                        pkId, currentSeasonId, ladderMatchResult, receiveScore, opponentReceiveScore);

                // 新增对手主播天梯赛历史记录并且新增或更新对手主播天梯赛资源
                addActorHistRecord(opponentActorId, actorId, opponentReceiveShowMoney, receiveShowMoney,
                        pkId, currentSeasonId, opponentLadderMatchResult, opponentReceiveScore, receiveScore);

            }
        }

        return new Result(CommonStateCode.SUCCESS, "新增主播天梯赛历史记录成功", true);
    }

    private int calculationReceiveScore(int actorId, long receiveShowMoney, int currentSeasonId, int ladderMatchResult) {
        long lowestGiftConsume = 1000L;
        ResActorLadderMatch resActorLadderMatch = resActorLadderMatchMapper.selectByPrimaryKey(actorId, currentSeasonId);
        int gameDan = GameDanEnum.STUBBORN_BRONZE.getId();
        if(resActorLadderMatch != null) {
            gameDan = resActorLadderMatch.getGameDan();
        }
        if(gameDan == GameDanEnum.STUBBORN_BRONZE.getId()) {
            lowestGiftConsume = 1000;
        } else if(gameDan == GameDanEnum.HEROIC_SILVER.getId()) {
            lowestGiftConsume = 5000;
        } else if(gameDan == GameDanEnum.GLORY_OF_GOLD.getId()) {
            lowestGiftConsume = 10000;
        } else if(gameDan == GameDanEnum.PRECIOUS_PLATINUM_GOLD.getId()) {
            lowestGiftConsume = 50000;
        } else if(gameDan == GameDanEnum.RESPLENDENT_DIAMOND.getId()) {
            lowestGiftConsume = 100000;
        } else if(gameDan == GameDanEnum.STRONGEST_KING.getId()) {
            lowestGiftConsume = 500000;
        }
        int receiveScore = getReceiveScore(lowestGiftConsume, receiveShowMoney, ladderMatchResult);
        return receiveScore;
    }

    // 计算应获取分值
    private int getReceiveScore(long lowestGiftConsume, long receiveShowMoney, int ladderMatchResult) {
        int receiveScore = 0;
        if(receiveShowMoney < lowestGiftConsume && ladderMatchResult == LadderMatchResultEnum.FAIL) {
            // 不满足最低礼物消耗 并且比赛结果失败
            receiveScore = -1;
        }else if(receiveShowMoney >= lowestGiftConsume) {
            // 满足最低礼物消耗
            receiveScore = getMeetLowestGiftConsumeScore(receiveShowMoney, ladderMatchResult);
        }
        return receiveScore;
    }

    // 计算满足最低礼物消耗应获取的分值
    private int getMeetLowestGiftConsumeScore(long receiveShowMoney, int ladderMatchResult) {
        int receiveScore = 0;
        // 根据比赛结果计算分值
        if(ladderMatchResult == LadderMatchResultEnum.WIN) {
            receiveScore = 2;
        }else if(ladderMatchResult == LadderMatchResultEnum.FAIL) {
            receiveScore = -1;
        }
        // 礼物消耗达到要求并且比赛结果是胜或平 额外加分项
        if(ladderMatchResult == LadderMatchResultEnum.WIN || ladderMatchResult == LadderMatchResultEnum.TIE) {
            if(receiveShowMoney >= 50000 && receiveShowMoney <= 500000) {
                receiveScore += 1;
            }else if(receiveShowMoney >= 501000 && receiveShowMoney <= 1000000) {
                receiveScore += 2;
            }else if(receiveShowMoney >= 1001000) {
                receiveScore += 3;
            }
        }
        return receiveScore;
    }

    // 通过天梯赛积分获取游戏段位
    private int getGameDan(int integral) {

        int gameDan;
        if(integral < 100) {
            gameDan = GameDanEnum.STUBBORN_BRONZE.getId();
        }else if(integral < 180) {
            gameDan = GameDanEnum.HEROIC_SILVER.getId();
        }else if(integral < 240) {
            gameDan = GameDanEnum.GLORY_OF_GOLD.getId();
        }else if(integral < 280) {
            gameDan = GameDanEnum.PRECIOUS_PLATINUM_GOLD.getId();
        }else if(integral < 350) {
            gameDan = GameDanEnum.RESPLENDENT_DIAMOND.getId();
        }else {
            gameDan = GameDanEnum.STRONGEST_KING.getId();
        }

        return gameDan;
    }

    // 新增单个主播天梯赛历史记录并且新增或更新单个主播天梯赛资源 返回当前段位
    private int addActorHistRecord(int actorId, int opponentActorId, long receiveShowMoney, long opponentReceiveShowMoney,
                                    int pkId, int currentSeasonId, int ladderMatchResult, int receiveScore, int opponentReceiveScore) {

        // 新增单个主播天梯赛历史记录
        HistActorLadderMatch histActorLadderMatch = new HistActorLadderMatch();
        histActorLadderMatch.setActorId(actorId);
        histActorLadderMatch.setSeasonId(currentSeasonId);
        histActorLadderMatch.setOpponentActorId(opponentActorId);
        histActorLadderMatch.setReceiveShowMoney(receiveShowMoney);
        histActorLadderMatch.setOpponentReceiveShowMoney(opponentReceiveShowMoney);
        histActorLadderMatch.setPkId(pkId);
        histActorLadderMatch.setCreateTime(DateUtils.getCurrentDate());

        histActorLadderMatch.setLadderMatchResult(ladderMatchResult);

        histActorLadderMatch.setReceiveScore(receiveScore);
        histActorLadderMatch.setOpponentReceiveScore(opponentReceiveScore);

        histActorLadderMatchMapper.insert(histActorLadderMatch);

        // 新增或更新单个主播天梯赛资源
        ResActorLadderMatch resActorLadderMatch = resActorLadderMatchMapper.selectByPrimaryKey(actorId, currentSeasonId);
        int winningTime = ladderMatchResult == LadderMatchResultEnum.WIN ? 1 : 0;
        int gameDan = GameDanEnum.STUBBORN_BRONZE.getId();

        if(resActorLadderMatch == null) {
            resActorLadderMatch = new ResActorLadderMatch();
            resActorLadderMatch.setActorId(actorId);
            resActorLadderMatch.setSeasonId(currentSeasonId);

            resActorLadderMatch.setLadderMatchIntegral(receiveScore);
            resActorLadderMatch.setGameDan(gameDan);
            resActorLadderMatch.setLadderMatchTime(1);
            resActorLadderMatch.setWinningTime(winningTime);
            resActorLadderMatch.setWinningRate(winningTime * 100);

            resActorLadderMatch.setShowMoneyGiveReward(GiveRewardStatusEnum.NOT_GIVE_REWARD);
            resActorLadderMatch.setMedalGiveReward(GiveRewardStatusEnum.NOT_GIVE_REWARD);

            resActorLadderMatch.setReceiveShowMoney(receiveShowMoney);

            Date currentDate = DateUtils.getCurrentDate();
            resActorLadderMatch.setCreateTime(currentDate);
            resActorLadderMatch.setUpdateTime(currentDate);

            resActorLadderMatchMapper.insert(resActorLadderMatch);
        }else {

            int ladderMatchIntegral = resActorLadderMatch.getLadderMatchIntegral() + receiveScore;
            gameDan = getGameDan(ladderMatchIntegral);
            int ladderMatchTime = resActorLadderMatch.getLadderMatchTime() + 1;
            winningTime = resActorLadderMatch.getWinningTime() + winningTime;
            int winningRate = winningTime * 100 / ladderMatchTime;
            receiveShowMoney = resActorLadderMatch.getReceiveShowMoney() + receiveShowMoney;

            resActorLadderMatch.setLadderMatchIntegral(ladderMatchIntegral);
            resActorLadderMatch.setGameDan(gameDan);
            resActorLadderMatch.setLadderMatchTime(ladderMatchTime);
            resActorLadderMatch.setWinningTime(winningTime);
            resActorLadderMatch.setWinningRate(winningRate);

            resActorLadderMatch.setReceiveShowMoney(receiveShowMoney);

            resActorLadderMatch.setUpdateTime(DateUtils.getCurrentDate());

            resActorLadderMatchMapper.updateByPrimaryKey(resActorLadderMatch);
        }

        return gameDan;
    }

    @Override
    public Result<List<HistActorLadderMatchDO>> getRecentHistActorLadderMatchList(int actorId) {

        List<HistActorLadderMatchDO> histActorLadderMatchDOS = Lists.newArrayList();
        Integer currentSeasonId = nationalPKRelationSource.getCurrentSeasonId();

        if(currentSeasonId != null) {

            List<HistActorLadderMatch> histActorLadderMatches = histActorLadderMatchMapper.getRecentHistActorLadderMatchList(actorId, currentSeasonId);

            if(histActorLadderMatches != null) {
                for (HistActorLadderMatch histActorLadderMatch : histActorLadderMatches) {
                    HistActorLadderMatchDO histActorLadderMatchDO = BeanMapper.map(histActorLadderMatch, HistActorLadderMatchDO.class);
                    histActorLadderMatchDOS.add(histActorLadderMatchDO);
                }
            }
        }
        return new Result(CommonStateCode.SUCCESS, "获取主播天梯赛最近10场战绩成功", histActorLadderMatchDOS);
    }
}

package com.melot.kk.nationalPK.server.impl;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.melot.common.driver.service.MessageService;
import com.melot.kk.nationalPK.api.constant.GameDanEnum;
import com.melot.kk.nationalPK.api.constant.GiveRewardStatusEnum;
import com.melot.kk.nationalPK.api.constant.LadderMatchStatusEnum;
import com.melot.kk.nationalPK.api.domain.DO.ConfLadderMatchDO;
import com.melot.kk.nationalPK.api.domain.DO.ResActorLadderMatchDO;
import com.melot.kk.nationalPK.api.service.ConfLadderMatchService;
import com.melot.kk.nationalPK.api.service.ResActorLadderMatchService;
import com.melot.kk.nationalPK.server.config.ConfigService;
import com.melot.kk.nationalPK.server.dao.ConfLadderMatchMapper;
import com.melot.kk.nationalPK.server.dao.ResActorLadderMatchMapper;
import com.melot.kk.nationalPK.server.model.ConfLadderMatch;
import com.melot.kk.nationalPK.server.model.ResActorLadderMatch;
import com.melot.kk.nationalPK.server.redis.NationalPKRelationSource;
import com.melot.kkcore.user.api.ShowMoneyHistory;
import com.melot.kkcore.user.api.UserAssets;
import com.melot.kkcore.user.service.KkUserService;
import com.melot.kktv.base.CommonStateCode;
import com.melot.kktv.base.Result;
import com.melot.kktv.util.AppIdEnum;
import com.melot.kktv.util.BeanMapper;
import com.melot.module.kkrpc.annotation.RpcConsumer;
import com.melot.module.kkrpc.annotation.RpcService;
import com.melot.module.medal.driver.service.ActivityMedalService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * @description: 全民PK-主播天梯赛资源接口服务
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
@RpcService(interfaceName = "com.melot.kk.nationalPK.api.service.ResActorLadderMatchService", version = "1.0.0")
public class ResActorLadderMatchServiceImpl implements ResActorLadderMatchService {

    private static Logger logger = Logger.getLogger(ResActorLadderMatchServiceImpl.class);

    @Autowired
    private ResActorLadderMatchMapper resActorLadderMatchMapper;

    @Autowired
    private ConfLadderMatchService confLadderMatchService;

    @Autowired
    private ConfLadderMatchMapper confLadderMatchMapper;

    @Autowired
    private ConfigService configService;

    @Autowired
    private NationalPKRelationSource nationalPKRelationSource;

    @RpcConsumer(version="1.1.7")
    private KkUserService kkUserService;

    @RpcConsumer(version="1.0.1")
    private ActivityMedalService activityMedalService;

    @RpcConsumer
    private MessageService messageService;

    @Override
    public Result<ResActorLadderMatchDO> getResActorLadderMatch(int actorId) {

        ResActorLadderMatchDO resActorLadderMatchDO = null;
        Integer currentSeasonId = nationalPKRelationSource.getCurrentSeasonId();
        if(currentSeasonId != null) {

            ResActorLadderMatch resActorLadderMatch = resActorLadderMatchMapper.selectByPrimaryKey(actorId, currentSeasonId);

            // 获取当前赛季是否正在进行中
            Boolean currentSeasonOngoing = false;
            Result<ConfLadderMatchDO> result = confLadderMatchService.getConfLadderMatchBySeasonId(currentSeasonId);
            if (result.getCode().equals(CommonStateCode.SUCCESS) && result.getData() != null) {
                int currentSeasonStatus = result.getData().getStatus();
                if (currentSeasonStatus == LadderMatchStatusEnum.ONGOING) {
                    currentSeasonOngoing = true;
                }
            }

            if(resActorLadderMatch != null) {
                resActorLadderMatchDO = BeanMapper.map(resActorLadderMatch, ResActorLadderMatchDO.class);

                // 设置当前赛季是否正在进行中
                resActorLadderMatchDO.setCurrentSeasonOngoing(currentSeasonOngoing);

                // 设置游戏段位名称
                int gameDan = resActorLadderMatchDO.getGameDan();
                resActorLadderMatchDO.setGameDanName(GameDanEnum.parseId(gameDan).getValue());

                // 设置主播排名
                Integer ranking = resActorLadderMatchMapper.getActorRanking(actorId, currentSeasonId);
                if (ranking == null) {
                    ranking = 100;
                }
                resActorLadderMatchDO.setRanking(ranking);
            }else {

                int gameDan = GameDanEnum.STUBBORN_BRONZE.getId();
                resActorLadderMatchDO = new ResActorLadderMatchDO();
                resActorLadderMatchDO.setGameDan(gameDan);
                resActorLadderMatchDO.setGameDanName(GameDanEnum.parseId(gameDan).getValue());
                resActorLadderMatchDO.setLadderMatchIntegral(0);
                resActorLadderMatchDO.setLadderMatchTime(0);
                resActorLadderMatchDO.setWinningRate(0);
                resActorLadderMatchDO.setRanking(0);
                resActorLadderMatchDO.setCurrentSeasonOngoing(currentSeasonOngoing);
            }
        }else {
            return new Result(CommonStateCode.FAIL, "获取主播天梯赛当前赛季战绩资源失败");
        }
        return new Result(CommonStateCode.SUCCESS, "获取主播天梯赛当前赛季战绩资源成功", resActorLadderMatchDO);
    }

    @Override
    public Result<List<ResActorLadderMatchDO>> getLadderChart(int pageIndex, int countPerPage) {

        List<ResActorLadderMatchDO> resActorLadderMatchDOS = Lists.newArrayList();
        if(pageIndex <= 0) {
            pageIndex = 1;
        }
        int limit = countPerPage;
        int offset = (pageIndex - 1) * limit;
        Integer currentSeasonId = nationalPKRelationSource.getCurrentSeasonId();
        if(currentSeasonId != null) {
            List<ResActorLadderMatch> resActorLadderMatches = resActorLadderMatchMapper.getList(currentSeasonId, limit, offset);
            if(resActorLadderMatches != null) {

                for (int i = 0; i < resActorLadderMatches.size(); i++) {

                    ResActorLadderMatchDO resActorLadderMatchDO = BeanMapper.map(resActorLadderMatches.get(i), ResActorLadderMatchDO.class);

                    // 设置游戏段位名称
                    int gameDan = resActorLadderMatchDO.getGameDan();
                    resActorLadderMatchDO.setGameDanName(GameDanEnum.parseId(gameDan).getValue());

                    // 设置主播排名
                    int ranking = offset + (i + 1);
                    resActorLadderMatchDO.setRanking(ranking);

                    resActorLadderMatchDOS.add(resActorLadderMatchDO);
                }
            }
        }
        return new Result(CommonStateCode.SUCCESS, "获取天梯榜成功", resActorLadderMatchDOS);
    }

    @Override
    public Result<Boolean> giveReward(int seasonId) {

        // 判断本赛季是否已经结束并且还没发放奖励 如果是开始发放奖励
        Result<ConfLadderMatchDO> result = confLadderMatchService.getConfLadderMatchBySeasonId(seasonId);

        if(result.getCode().equals(CommonStateCode.SUCCESS) && result.getData() != null) {

            ConfLadderMatchDO confLadderMatchDO = result.getData();
            int status = confLadderMatchDO.getStatus();
            int giveReward = confLadderMatchDO.getGiveReward();
            if(status == LadderMatchStatusEnum.OVER && giveReward == GiveRewardStatusEnum.NOT_GIVE_REWARD) {

                int strongestKingCount = resActorLadderMatchMapper.getCountBySeasonIdAndGameDan(seasonId, GameDanEnum.STRONGEST_KING.getId());

                Integer count = resActorLadderMatchMapper.getListCount(seasonId);
                int limit = 20;
                int pages = count / limit + 1;
                for(int pageIndex = 0; pageIndex < pages; pageIndex++) {

                    int offset = pageIndex * limit;
                    List<ResActorLadderMatch> resActorLadderMatches = resActorLadderMatchMapper.getList(seasonId, limit, offset);
                    if(resActorLadderMatches == null || resActorLadderMatches.size() == 0) {
                        break;
                    }

                    logger.info("正在发放奖励的主播列表 " + new Gson().toJson(resActorLadderMatches));
                    for(ResActorLadderMatch resActorLadderMatch : resActorLadderMatches) {
                        int actorId = resActorLadderMatch.getActorId();
                        // 给单个主播发放奖励
                        this.giveReward(actorId, seasonId, confLadderMatchDO.getSeasonName(), strongestKingCount);
                    }
                }

                // 更新当前赛季奖励状态为已发放奖励
                ConfLadderMatch record = new ConfLadderMatch();
                record.setSeasonId(seasonId);
                record.setGiveReward(GiveRewardStatusEnum.ALREADY_GIVE_REWARD);
                confLadderMatchMapper.updateByPrimaryKey(record);
            }
        }
        return new Result(CommonStateCode.SUCCESS, "给本赛季所有主播发放奖励成功", true);
    }

    /**
     * 给本赛季单个主播发放奖励
     */
    private Result<Boolean> giveReward(int actorId, int seasonId, String seasonName, int strongestKingCount) {

        String errorMsg = "发放奖励失败 主播id: " + actorId + " seasonId: " + seasonId;

        try {

            /**
             * 该主播是否已经开始发放奖励 防止多线程同一时刻并发执行发放奖励程序导致一个主播发放多次情况
             * true-已经在开始发放奖励(当前线程检测到true 不能再去发放奖励)
             * false-还没开始发放奖励(当前线程检测到false 可以去发放奖励)
             */
            Boolean startGiveReward = nationalPKRelationSource.startGiveReward(actorId);
            if(startGiveReward) {

                logger.error("该主播已经在开始发放奖励 " + errorMsg);
                return new Result(CommonStateCode.FAIL, errorMsg);
            }

            ResActorLadderMatch resActorLadderMatch = resActorLadderMatchMapper.selectByPrimaryKey(actorId, seasonId);
            if(resActorLadderMatch != null) {

                int showMoneyGiveReward = resActorLadderMatch.getShowMoneyGiveReward();
                int gameDan = resActorLadderMatch.getGameDan();
                // 判断该主播是否满足发放秀币奖励条件并且还未发放秀币奖励
                if(gameDan >= GameDanEnum.PRECIOUS_PLATINUM_GOLD.getId() && showMoneyGiveReward == 0) {

                    Long currentBonusPool = nationalPKRelationSource.getCurrentBonusPool();
                    Double price = null;
                    if(gameDan == GameDanEnum.STRONGEST_KING.getId()) {
                        price = currentBonusPool * 0.85 / strongestKingCount;
                    }

                    if(price != null) {

                        // 发放秀币奖励
                        ShowMoneyHistory showMoneyHistory = getShowMoneyHistory(actorId, price.intValue(), seasonId);
                        UserAssets userAssets;
                        try {
                            userAssets = kkUserService.incUserAssets(actorId, price.longValue(), 0, showMoneyHistory);
                            if (userAssets == null) {
                                logger.error("发放秀币奖励失败 " + errorMsg);
                                return new Result(CommonStateCode.FAIL, errorMsg);
                            }
                        } catch (Exception e) {
                            logger.error("发放秀币奖励失败 " + errorMsg, e);
                            return new Result(CommonStateCode.FAIL, errorMsg);
                        }

                        // 更新主播天梯赛资源表发放秀币数量和状态为已发放
                        resActorLadderMatch.setShowMoneyCount(price.longValue());
                        resActorLadderMatch.setShowMoneyGiveReward(GiveRewardStatusEnum.ALREADY_GIVE_REWARD);
                        resActorLadderMatchMapper.updateByPrimaryKey(resActorLadderMatch);

                        // 发送系统消息
                        String title = seasonName + "奖励发放通知";
                        String desc = "恭喜你在" + seasonName + "中获得" + GameDanEnum.parseId(gameDan).getValue() + "段位, 特奖励" + price.longValue() + "秀币, 请在个人账户中查收!";
                        messageService.addSystemMessage(actorId, showMoneyHistory.getHistId(), 9, title, desc);
                    }
                }

                int medalGiveReward = resActorLadderMatch.getMedalGiveReward();
                // 判断该主播是否已经发放勋章奖励
                if(medalGiveReward == 0) {

                    try {
                        // 发放勋章奖励
                        // 通过disconf获取勋章id和勋章过期时间
                        int medalId = getMedalId(gameDan);
                        int medalDeadline = configService.getMedalDeadline();
                        activityMedalService.insertOperatorSendActivityMedalNew(String.valueOf(actorId), medalId, 0, medalDeadline, 0, 1, "天梯赛发放奖励获得段位勋章", 0);

                        // 更新主播天梯赛资源表发放秀币数量和状态为已发放
                        resActorLadderMatch.setMedalId(medalId);
                        resActorLadderMatch.setMedalGiveReward(GiveRewardStatusEnum.ALREADY_GIVE_REWARD);
                        resActorLadderMatchMapper.updateByPrimaryKey(resActorLadderMatch);
                    } catch (Exception e) {
                        logger.error("发放勋章奖励失败 " + errorMsg, e);
                        return new Result(CommonStateCode.FAIL, errorMsg);
                    }
                }
            }

            return new Result(CommonStateCode.SUCCESS, "给本赛季单个主播发放奖励成功", true);
        }catch (Exception e) {
            logger.error(errorMsg, e);
            return new Result(CommonStateCode.FAIL, errorMsg);
        }
    }

    // 通过disconf获取勋章id
    private int getMedalId(int gameDan) {

        int medalId = 0;
        if(gameDan == GameDanEnum.STUBBORN_BRONZE.getId()) {

            medalId = configService.getStubbornBronzeMedalId();
        } else if(gameDan == GameDanEnum.HEROIC_SILVER.getId()) {

            medalId = configService.getHeroicSilverMedalId();
        } else if(gameDan == GameDanEnum.GLORY_OF_GOLD.getId()) {

            medalId = configService.getGloryOfGoldMedalId();
        } else if(gameDan == GameDanEnum.PRECIOUS_PLATINUM_GOLD.getId()) {

            medalId = configService.getPreciousPlatinumGoldMedalId();
        } else if(gameDan == GameDanEnum.RESPLENDENT_DIAMOND.getId()) {

            medalId = configService.getResplendentDiamondMedalId();
        } else if(gameDan == GameDanEnum.STRONGEST_KING.getId()) {

            medalId = configService.getStrongestKingMedalId();
        }
        return medalId;
    }

    private ShowMoneyHistory getShowMoneyHistory(int actorId, int price, int seasonId) {

        ShowMoneyHistory showMoneyHistory = new ShowMoneyHistory();
        showMoneyHistory.setAppId(AppIdEnum.AMUSEMENT);
        showMoneyHistory.setCount(1);

        showMoneyHistory.setToUserId(actorId);
        showMoneyHistory.setIncomeAmount(price);
        showMoneyHistory.setType(54);
        showMoneyHistory.setProductDesc("天梯赛发放主播秀币奖励");
        showMoneyHistory.setTypeDesc("天梯赛发放主播秀币奖励 赛季id: " + seasonId + "主播id:" + actorId);
        showMoneyHistory.setDtime(new Date());

        return showMoneyHistory;
    }

}

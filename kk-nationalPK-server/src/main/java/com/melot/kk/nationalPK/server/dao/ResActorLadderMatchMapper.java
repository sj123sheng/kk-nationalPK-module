package com.melot.kk.nationalPK.server.dao;

import com.melot.kk.nationalPK.server.model.ResActorLadderMatch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResActorLadderMatchMapper {

    String insert(ResActorLadderMatch record);

    ResActorLadderMatch selectByPrimaryKey(@Param("actorId") Integer actorId, @Param("seasonId") Integer seasonId);

    String updateByPrimaryKey(ResActorLadderMatch record);

    // 获取指定赛季奖金池的秀币总数量
    Long getBonusPoolShowMoneyCount(int seasonId);

    // 获取主播排名 只查询排名前99名的主播 超过99名统一返回空
    Integer getActorRanking(@Param("actorId") Integer actorId, @Param("seasonId") Integer seasonId);

    // 获取天梯赛排名(天梯榜)
    List<ResActorLadderMatch> getLadderChart(@Param("seasonId") Integer seasonId, @Param("limit") Integer limit, @Param("offset") Integer offset);
}
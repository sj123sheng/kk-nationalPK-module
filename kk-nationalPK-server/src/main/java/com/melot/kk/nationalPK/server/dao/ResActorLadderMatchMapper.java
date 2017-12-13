package com.melot.kk.nationalPK.server.dao;

import com.melot.kk.nationalPK.server.model.ResActorLadderMatch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResActorLadderMatchMapper {

    String insert(ResActorLadderMatch record);

    ResActorLadderMatch selectByPrimaryKey(@Param("actorId") int actorId, @Param("seasonId") int seasonId);

    String updateByPrimaryKey(ResActorLadderMatch record);

    // 获取指定赛季奖金池的秀币总数量
    Long getBonusPoolShowMoneyCount(int seasonId);

    // 获取主播排名 只查询排名前99名的主播 超过99名统一返回空
    Integer getActorRanking(@Param("actorId") int actorId, @Param("seasonId") int seasonId);

    // 获取天梯赛排名列表(天梯榜)
    List<ResActorLadderMatch> getList(@Param("seasonId") int seasonId, @Param("limit") int limit, @Param("offset") int offset);

    Integer getListCount(@Param("seasonId") int seasonId);

    Integer getCountBySeasonIdAndGameDan(@Param("seasonId") int seasonId, @Param("gameDan") int gameDan);
}
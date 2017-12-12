package com.melot.kk.nationalPK.server.dao;

import com.melot.kk.nationalPK.server.model.ResActorLadderMatch;
import org.apache.ibatis.annotations.Param;

public interface ResActorLadderMatchMapper {

    String insert(ResActorLadderMatch record);

    ResActorLadderMatch selectByPrimaryKey(@Param("actorId") Integer actorId, @Param("seasonId") Integer seasonId);

    String updateByPrimaryKey(ResActorLadderMatch record);

    // 获取指定赛季奖金池的秀币总数量
    Long getBonusPoolShowMoneyCount(int seasonId);
}
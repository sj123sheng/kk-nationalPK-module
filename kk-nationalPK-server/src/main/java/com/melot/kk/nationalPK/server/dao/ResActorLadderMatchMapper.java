package com.melot.kk.nationalPK.server.dao;

import com.melot.kk.nationalPK.server.model.ResActorLadderMatch;
import org.apache.ibatis.annotations.Param;

public interface ResActorLadderMatchMapper {
    int deleteByPrimaryKey(@Param("actorId") Integer actorId, @Param("seasonId") Integer seasonId);

    int insert(ResActorLadderMatch record);

    ResActorLadderMatch selectByPrimaryKey(@Param("actorId") Integer actorId, @Param("seasonId") Integer seasonId);

    int updateByPrimaryKey(ResActorLadderMatch record);
}
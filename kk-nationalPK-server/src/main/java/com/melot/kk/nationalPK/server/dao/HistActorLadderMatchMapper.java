package com.melot.kk.nationalPK.server.dao;

import com.melot.kk.nationalPK.server.model.HistActorLadderMatch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HistActorLadderMatchMapper {

    int insert(HistActorLadderMatch record);

    HistActorLadderMatch selectByPrimaryKey(Integer ladderMatchRecordId);

    String updateByPrimaryKey(HistActorLadderMatch record);

    List<HistActorLadderMatch> getRecentHistActorLadderMatchList(@Param("actorId") int actorId, @Param("seasonId") int seasonId);

}
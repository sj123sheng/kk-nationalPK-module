package com.melot.kk.nationalPK.server.dao;

import com.melot.kk.nationalPK.server.model.HistActorLadderMatch;

public interface HistActorLadderMatchMapper {
    int deleteByPrimaryKey(Integer ladderMatchRecordId);

    int insert(HistActorLadderMatch record);

    HistActorLadderMatch selectByPrimaryKey(Integer ladderMatchRecordId);

    int updateByPrimaryKey(HistActorLadderMatch record);
}
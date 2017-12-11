package com.melot.kk.nationalPK.server.dao;

import com.melot.kk.nationalPK.server.model.HistActorLadderMatch;

public interface HistActorLadderMatchMapper {

    int insert(HistActorLadderMatch record);

    HistActorLadderMatch selectByPrimaryKey(Integer ladderMatchRecordId);

    String updateByPrimaryKey(HistActorLadderMatch record);
}
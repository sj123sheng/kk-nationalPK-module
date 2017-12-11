package com.melot.kk.nationalPK.server.dao;

import com.melot.kk.nationalPK.server.model.ConfLadderMatch;

public interface ConfLadderMatchMapper {
    int deleteByPrimaryKey(Integer seasonId);

    int insert(ConfLadderMatch record);

    ConfLadderMatch selectByPrimaryKey(Integer seasonId);

    int updateByPrimaryKey(ConfLadderMatch record);
}
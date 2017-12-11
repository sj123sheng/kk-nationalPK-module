package com.melot.kk.nationalPK.server.dao;

import com.melot.kk.nationalPK.server.model.ConfLadderMatch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConfLadderMatchMapper {

    int insert(ConfLadderMatch record);

    ConfLadderMatch selectByPrimaryKey(Integer seasonId);

    String updateByPrimaryKey(ConfLadderMatch record);

    int getListCount();

    List<ConfLadderMatch> getList(@Param("limit") int limit, @Param("offset") int offset);
}
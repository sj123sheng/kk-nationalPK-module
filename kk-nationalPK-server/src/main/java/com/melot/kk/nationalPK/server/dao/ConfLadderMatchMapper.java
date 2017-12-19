package com.melot.kk.nationalPK.server.dao;

import com.melot.kk.nationalPK.server.model.ConfLadderMatch;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ConfLadderMatchMapper {

    int insert(ConfLadderMatch record);

    ConfLadderMatch selectByPrimaryKey(Integer seasonId);

    String updateByPrimaryKey(ConfLadderMatch record);

    int getListCount();

    List<ConfLadderMatch> getList(@Param("limit") Integer limit, @Param("offset") Integer offset);

    // 获取正在进行的天梯赛赛季
    ConfLadderMatch getOngoingSeason(Date currentDate);
}
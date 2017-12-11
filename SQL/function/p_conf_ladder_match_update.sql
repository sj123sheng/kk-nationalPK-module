CREATE OR REPLACE FUNCTION p_conf_ladder_match_update
(
  IN  i_seasonId  INTEGER,
  IN  i_seasonName  TEXT,
  IN  i_bonusPoolMultiple  INTEGER,
  IN  i_giveReward  INTEGER,
  IN  i_startTime  TIMESTAMP,
  IN  i_endTime  TIMESTAMP,
  IN  i_createTime  TIMESTAMP,
  IN  i_updateTime  TIMESTAMP,
  OUT  o_tagcode  TEXT
)
RETURNS TEXT AS
$$
BEGIN
  UPDATE conf_ladder_match
  SET season_name = coalesce(i_seasonName, season_name),
    bonus_pool_multiple = coalesce(i_bonusPoolMultiple, bonus_pool_multiple),
    give_reward = coalesce(i_giveReward, give_reward),
    start_time = coalesce(i_startTime, start_time),
    end_time = coalesce(i_endTime, end_time),
    create_time = coalesce(i_createTime, create_time),
    update_time = coalesce(i_updateTime, update_time)
  WHERE season_id = i_seasonId;
  o_tagcode := '00000000';
EXCEPTION WHEN OTHERS THEN
  o_tagcode := '01';
END;
$$
LANGUAGE 'plpgsql';
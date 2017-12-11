CREATE OR REPLACE FUNCTION p_conf_ladder_match_insert
(
  IN  i_seasonName  TEXT,
  IN  i_bonusPoolMultiple  INTEGER,
  IN  i_giveReward  INTEGER,
  IN  i_startTime  TIMESTAMP,
  IN  i_endTime  TIMESTAMP,
  IN  i_createTime  TIMESTAMP,
  IN  i_updateTime  TIMESTAMP,
  OUT  o_seasonId  INTEGER
)
RETURNS INTEGER AS
$$
BEGIN
  o_seasonId := nextval('kkcx.seq_conf_ladder_match');
  INSERT INTO conf_ladder_match (season_id, season_name, bonus_pool_multiple, give_reward, 
    start_time, end_time, create_time, 
    update_time)
  VALUES (o_seasonId, i_seasonName, i_bonusPoolMultiple, i_giveReward, 
    i_startTime, i_endTime, i_createTime, 
    i_updateTime);
EXCEPTION WHEN OTHERS THEN
  o_seasonId := -1;
END;
$$
LANGUAGE 'plpgsql';
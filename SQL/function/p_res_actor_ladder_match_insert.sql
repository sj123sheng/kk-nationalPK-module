CREATE OR REPLACE FUNCTION p_res_actor_ladder_match_insert
(
  IN  i_actorId  INTEGER,
  IN  i_seasonId  INTEGER,
  IN  i_ladderMatchIntegral  INTEGER,
  IN  i_gameDan  INTEGER,
  IN  i_ladderMatchTime  INTEGER,
  IN  i_winningTime  INTEGER,
  IN  i_winningRate  INTEGER,
  IN  i_showMoneyGiveReward  INTEGER,
  IN  i_medalGiveReward  INTEGER,
  IN  i_showMoneyCount  BIGINT,
  IN  i_medalId  INTEGER,
  IN  i_receiveShowMoney  BIGINT,
  IN  i_createTime  TIMESTAMP,
  IN  i_updateTime  TIMESTAMP,
  OUT  o_tagcode  TEXT
)
RETURNS TEXT AS
$$
BEGIN
  INSERT INTO res_actor_ladder_match (actor_id, season_id, ladder_match_integral, 
    game_dan, ladder_match_time, winning_time, 
    winning_rate, show_money_give_reward, medal_give_reward, 
    show_money_count, medal_id, receive_show_money, 
    create_time, update_time)
  VALUES (i_actorId, i_seasonId, i_ladderMatchIntegral, 
    i_gameDan, i_ladderMatchTime, i_winningTime, 
    i_winningRate, i_showMoneyGiveReward, i_medalGiveReward, 
    i_showMoneyCount, i_medalId, i_receiveShowMoney, 
    i_createTime, i_updateTime);
  o_tagcode := '00000000';
EXCEPTION WHEN OTHERS THEN
  o_tagcode := '01';
END;
$$
LANGUAGE 'plpgsql';
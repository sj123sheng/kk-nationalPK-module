CREATE OR REPLACE FUNCTION p_res_actor_ladder_match_update
(
  IN  i_actorId  INTEGER,
  IN  i_seasonId  INTEGER,
  IN  i_ladderMatchIntegral  INTEGER,
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
  UPDATE res_actor_ladder_match
  SET ladder_match_integral = coalesce(i_ladderMatchIntegral, ladder_match_integral),
    ladder_match_time = coalesce(i_ladderMatchTime, ladder_match_time),
    winning_time = coalesce(i_winningTime, winning_time),
    winning_rate = coalesce(i_winningRate, winning_rate),
    show_money_give_reward = coalesce(i_showMoneyGiveReward, show_money_give_reward),
    medal_give_reward = coalesce(i_medalGiveReward, medal_give_reward),
    show_money_count = coalesce(i_showMoneyCount, show_money_count),
    medal_id = coalesce(i_medalId, medal_id),
    receive_show_money = coalesce(i_receiveShowMoney, receive_show_money),
    create_time = coalesce(i_createTime, create_time),
    update_time = coalesce(i_updateTime, update_time)
  WHERE actor_id = i_actorId
  AND season_id = i_seasonId;
  o_tagcode := '00000000';
EXCEPTION WHEN OTHERS THEN
  o_tagcode := '01';
END;
$$
LANGUAGE 'plpgsql';
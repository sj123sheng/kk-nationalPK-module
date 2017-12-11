CREATE OR REPLACE FUNCTION p_hist_actor_ladder_match_insert
(
  IN  i_actorId  INTEGER,
  IN  i_seasonId  INTEGER,
  IN  i_opponentActorId  INTEGER,
  IN  i_ladderMatchResult  INTEGER,
  IN  i_receiveScore  INTEGER,
  IN  i_receiveShowMoney  BIGINT,
  IN  i_createTime  TIMESTAMP,
  IN  i_pkId  INTEGER,
  OUT  o_ladderMatchRecordId  INTEGER
)
RETURNS INTEGER AS
$$
BEGIN
  o_ladderMatchRecordId := nextval('kkcx.seq_hist_actor_ladder_match');
  INSERT INTO hist_actor_ladder_match (ladder_match_record_id, actor_id, season_id, opponent_actor_id, 
    ladder_match_result, receive_score, receive_show_money, 
    create_time, pk_id)
  VALUES (o_ladderMatchRecordId, i_actorId, i_seasonId, i_opponentActorId, 
    i_ladderMatchResult, i_receiveScore, i_receiveShowMoney, 
    i_createTime, i_pkId);
EXCEPTION WHEN OTHERS THEN
  o_ladderMatchRecordId := -1;
END;
$$
LANGUAGE 'plpgsql';
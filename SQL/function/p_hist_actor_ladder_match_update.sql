CREATE OR REPLACE FUNCTION p_hist_actor_ladder_match_update
(
  IN  i_ladderMatchRecordId  INTEGER,
  IN  i_actorId  INTEGER,
  IN  i_seasonId  INTEGER,
  IN  i_opponentActorId  INTEGER,
  IN  i_ladderMatchResult  INTEGER,
  IN  i_receiveScore  INTEGER,
  IN  i_opponentReceiveScore  INTEGER,
  IN  i_receiveShowMoney  BIGINT,
  IN  i_opponentReceiveShowMoney  BIGINT,
  IN  i_createTime  TIMESTAMP,
  IN  i_pkId  INTEGER,
  OUT  o_tagcode  TEXT
)
RETURNS TEXT AS
$$
BEGIN
  UPDATE hist_actor_ladder_match
  SET actor_id = coalesce(i_actorId, actor_id),
    season_id = coalesce(i_seasonId, season_id),
    opponent_actor_id = coalesce(i_opponentActorId, opponent_actor_id),
    ladder_match_result = coalesce(i_ladderMatchResult, ladder_match_result),
    receive_score = coalesce(i_receiveScore, receive_score),
    opponent_receive_score = coalesce(i_opponentReceiveScore, opponent_receive_score),
    receive_show_money = coalesce(i_receiveShowMoney, receive_show_money),
    opponent_receive_show_money = coalesce(i_opponentReceiveShowMoney, opponent_receive_show_money),
    create_time = coalesce(i_createTime, create_time),
    pk_id = coalesce(i_pkId, pk_id)
  WHERE ladder_match_record_id = i_ladderMatchRecordId;
  o_tagcode := '00000000';
EXCEPTION WHEN OTHERS THEN
  o_tagcode := '01';
END;
$$
LANGUAGE 'plpgsql';
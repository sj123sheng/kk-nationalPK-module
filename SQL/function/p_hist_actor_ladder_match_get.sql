CREATE OR REPLACE FUNCTION p_hist_actor_ladder_match_get
(
  IN  i_ladderMatchRecordId  INTEGER
)
RETURNS SETOF hist_actor_ladder_match AS 
$$
  select ladder_match_record_id, actor_id, season_id, opponent_actor_id, ladder_match_result, 
          receive_score, opponent_receive_score, receive_show_money, opponent_receive_show_money, 
          create_time, pk_id from hist_actor_ladder_match where ladder_match_record_id = i_ladderMatchRecordId;
$$
LANGUAGE 'sql'
COST 100;
CREATE OR REPLACE FUNCTION p_hist_actor_ladder_match_delete
(
  IN  i_ladderMatchRecordId  INTEGER,
  OUT  o_tagcode  TEXT
)
RETURNS TEXT AS
$$
BEGIN
  DELETE FROM hist_actor_ladder_match WHERE ladder_match_record_id = i_ladderMatchRecordId;
  o_tagcode := '00000000';
EXCEPTION WHEN OTHERS THEN
  o_tagcode := '01';
END;
$$
LANGUAGE 'plpgsql';
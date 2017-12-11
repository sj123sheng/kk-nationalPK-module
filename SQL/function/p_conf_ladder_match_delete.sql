CREATE OR REPLACE FUNCTION p_conf_ladder_match_delete
(
  IN  i_seasonId  INTEGER,
  OUT  o_tagcode  TEXT
)
RETURNS TEXT AS
$$
BEGIN
  DELETE FROM conf_ladder_match WHERE season_id = i_seasonId;
  o_tagcode := '00000000';
EXCEPTION WHEN OTHERS THEN
  o_tagcode := '01';
END;
$$
LANGUAGE 'plpgsql';
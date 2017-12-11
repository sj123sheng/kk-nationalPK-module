CREATE OR REPLACE FUNCTION p_res_actor_ladder_match_delete
(
  IN  i_actorId  INTEGER,
  IN  i_seasonId  INTEGER,
  OUT  o_tagcode  TEXT
)
RETURNS TEXT AS
$$
BEGIN
  DELETE FROM res_actor_ladder_match WHERE actor_id = i_actorId  AND season_id = i_seasonId;
  o_tagcode := '00000000';
EXCEPTION WHEN OTHERS THEN
  o_tagcode := '01';
END;
$$
LANGUAGE 'plpgsql';
CREATE TABLE conf_ladder_match
(
  season_id           INTEGER NOT NULL
    CONSTRAINT conf_ladder_match_pkey
    PRIMARY KEY,
  season_name         VARCHAR(255),
  bonus_pool_multiple INTEGER,
  give_reward         INTEGER,
  start_time          TIMESTAMP,
  end_time            TIMESTAMP,
  create_time         TIMESTAMP,
  update_time         TIMESTAMP
);
COMMENT ON TABLE conf_ladder_match IS '天梯赛';
COMMENT ON COLUMN conf_ladder_match.season_id IS '赛季id';
COMMENT ON COLUMN conf_ladder_match.season_name IS '赛季名称';
COMMENT ON COLUMN conf_ladder_match.bonus_pool_multiple IS '奖金池倍率 (原始倍率*100存储)';
COMMENT ON COLUMN conf_ladder_match.give_reward IS '是否已发放奖励 0-未发放 1-已发放';
COMMENT ON COLUMN conf_ladder_match.start_time IS '开始时间';
COMMENT ON COLUMN conf_ladder_match.end_time IS '结束时间';
COMMENT ON COLUMN conf_ladder_match.create_time IS '创建时间';
COMMENT ON COLUMN conf_ladder_match.update_time IS '更新时间';

CREATE SEQUENCE seq_conf_ladder_match
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 99999999
  START 1
  CACHE 1;

CREATE TABLE res_actor_ladder_match
(
  actor_id               INTEGER NOT NULL,
  season_id              INTEGER NOT NULL,
  ladder_match_integral  INTEGER,
  ladder_match_time      INTEGER,
  winning_rate           INTEGER,
  show_money_give_reward INTEGER DEFAULT 0,
  medal_give_reward      INTEGER DEFAULT 0,
  show_money_count       BIGINT,
  medal_id               INTEGER,
  receive_show_money     BIGINT,
  create_time            TIMESTAMP,
  update_time            TIMESTAMP,
  CONSTRAINT res_actor_ladder_match_actor_id_season_id_pk
  PRIMARY KEY (actor_id, season_id)
);
COMMENT ON TABLE res_actor_ladder_match IS '主播天梯赛';
COMMENT ON COLUMN res_actor_ladder_match.actor_id IS '主播id';
COMMENT ON COLUMN res_actor_ladder_match.season_id IS '赛季id';
COMMENT ON COLUMN res_actor_ladder_match.ladder_match_integral IS '天梯赛积分';
COMMENT ON COLUMN res_actor_ladder_match.ladder_match_time IS '天梯赛场次';
COMMENT ON COLUMN res_actor_ladder_match.winning_rate IS '天梯赛胜率';
COMMENT ON COLUMN res_actor_ladder_match.show_money_give_reward IS '秀币是否已发放';
COMMENT ON COLUMN res_actor_ladder_match.medal_give_reward IS '勋章是否已发放';
COMMENT ON COLUMN res_actor_ladder_match.show_money_count IS '发放的秀币数量';
COMMENT ON COLUMN res_actor_ladder_match.medal_id IS '发放的勋章id';
COMMENT ON COLUMN res_actor_ladder_match.receive_show_money IS '获得礼物对应的秀币总数量';
COMMENT ON COLUMN res_actor_ladder_match.create_time IS '创建时间';
COMMENT ON COLUMN res_actor_ladder_match.update_time IS '更新时间';

CREATE TABLE hist_actor_ladder_match
(
  ladder_match_record_id      INTEGER NOT NULL
    CONSTRAINT hist_actor_ladder_match_pkey
    PRIMARY KEY,
  actor_id                    INTEGER,
  season_id                   INTEGER,
  opponent_actor_id           INTEGER,
  ladder_match_result         INTEGER,
  receive_score               INTEGER,
  receive_show_money          BIGINT,
  opponent_receive_show_money BIGINT,
  create_time                 TIMESTAMP,
  pk_id                       INTEGER
);
COMMENT ON TABLE hist_actor_ladder_match IS '主播天梯赛历史记录';
COMMENT ON COLUMN hist_actor_ladder_match.ladder_match_record_id IS '主播天梯赛历史记录id';
COMMENT ON COLUMN hist_actor_ladder_match.actor_id IS '主播id';
COMMENT ON COLUMN hist_actor_ladder_match.season_id IS '赛季id';
COMMENT ON COLUMN hist_actor_ladder_match.opponent_actor_id IS '对手主播id';
COMMENT ON COLUMN hist_actor_ladder_match.ladder_match_result IS '天梯赛比赛结果 1-胜利 2-失败 3-平局';
COMMENT ON COLUMN hist_actor_ladder_match.receive_score IS '获得分值';
COMMENT ON COLUMN hist_actor_ladder_match.receive_show_money IS '获得秀币总数';
COMMENT ON COLUMN hist_actor_ladder_match.opponent_receive_show_money IS '对手获得秀币总数';
COMMENT ON COLUMN hist_actor_ladder_match.create_time IS '创建时间';
COMMENT ON COLUMN hist_actor_ladder_match.pk_id IS '存储pk详情的hist_pkroom_stat表的主键id';

CREATE SEQUENCE seq_hist_actor_ladder_match
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 99999999
  START 1
  CACHE 1;
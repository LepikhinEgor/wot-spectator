CREATE SEQUENCE location_id_seq;

CREATE TABLE tank_location (
id bigint NOT NULL PRIMARY KEY,
battle_id uuid NOT NULL,
timing timestamp NOT NULL,
team smallint NOT NULL,
tank_id varchar(50),
hp integer,
location_x real NOT NULL,
location_y real NOT NULL,
hull_angle real NOT NULL,
turret_angle real NOT NULL,
FOREIGN KEY (battle_id) REFERENCES battle(id)
);

ALTER SEQUENCE location_id_seq OWNED BY tank_location.id;
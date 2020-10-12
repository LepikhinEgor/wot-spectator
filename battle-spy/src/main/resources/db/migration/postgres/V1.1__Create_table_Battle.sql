CREATE TABLE battle (
id UUID NOT NULL,
map varchar(30) NOT NULL,
author varchar(256),
CONSTRAINT battle_id PRIMARY KEY (id)
)
CREATE KEYSPACE IF NOT EXISTS testKeySpace WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}  AND durable_writes = true;

DROP TABLE IF EXISTS testKeySpace.person;

CREATE TABLE IF NOT EXISTS testKeySpace.person (
    id text,
    name text,
    age int,
    PRIMARY KEY (id)
);


INSERT into testKeySpace.person(id, name, age) values ('1','Kelly',34);
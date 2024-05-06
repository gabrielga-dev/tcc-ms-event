create table address
(
    uuid       varchar(36) primary key not null,
    event_uuid varchar(36)             not null,
    street     varchar(50)             not null,
    neighbour  varchar(50)             not null,
    complement varchar(10),
    city       varchar(50)             not null,
    state      varchar(50)             not null,
    country    varchar(50)             not null,
    zip_code   varchar(25)             not null,
    latitude   DECIMAL(10, 8),
    longitude  DECIMAL(10, 8),
    FOREIGN KEY (event_uuid) REFERENCES event (uuid)
);

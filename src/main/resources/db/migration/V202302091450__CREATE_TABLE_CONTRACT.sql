create table contract
(
    event_uuid    varchar(36) not null,
    business_uuid varchar(36) not null,
    quote_uuid    varchar(36) not null,
    bytes         MEDIUMBLOB  not null,
    creation_date timestamp   not null,
    update_date   timestamp,
    FOREIGN KEY (event_uuid, business_uuid) REFERENCES event_service (event_uuid, business_uuid),
    PRIMARY KEY (event_uuid, business_uuid, quote_uuid)
);
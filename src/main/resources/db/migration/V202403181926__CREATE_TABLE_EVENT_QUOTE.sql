create table event_quote
(
    event_uuid    varchar(36)    not null,
    quote_uuid    varchar(36)    not null,
    business_uuid varchar(36)    not null,
    hired         BOOLEAN        not null,
    hired_date    timestamp,
    FOREIGN KEY (event_uuid) REFERENCES event (uuid),
    PRIMARY KEY (event_uuid, business_uuid)
);

create table event_service
(
    event_uuid    varchar(36)    not null,
    business_uuid varchar(36)    not null,
    hired         BOOLEAN        not null,
    hired_date    timestamp,
    price         DECIMAL(10, 2) not null,
    FOREIGN KEY (event_uuid) REFERENCES event (uuid),
    PRIMARY KEY (event_uuid, business_uuid)
);
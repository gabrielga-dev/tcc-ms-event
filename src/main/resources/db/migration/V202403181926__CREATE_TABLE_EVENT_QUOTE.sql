create table quote_request
(
    event_uuid         varchar(36) not null,
    quote_request_uuid varchar(36) not null,
    business_uuid      varchar(36) not null,
    business_type      varchar(20) not null,

    quote_uuid         varchar(36),
    status             varchar(15) not null,
    update_date        timestamp,

    FOREIGN KEY (event_uuid) REFERENCES event (uuid),
    PRIMARY KEY (event_uuid, business_uuid, quote_request_uuid, business_type)
);

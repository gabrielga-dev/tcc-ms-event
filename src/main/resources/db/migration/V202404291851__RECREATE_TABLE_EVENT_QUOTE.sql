delete
from quote_request
where 1 = 1;

drop table quote_request;

create table quote_request
(
    uuid          varchar(36) primary key not null,

    event_uuid    varchar(36)             not null,

    business_uuid varchar(36)             not null,
    business_type varchar(20)             not null,

    quote_uuid    varchar(36),
    status        varchar(15)             not null,
    creation_date timestamp,
    update_date   timestamp,

    FOREIGN KEY (event_uuid) REFERENCES event (uuid)
);

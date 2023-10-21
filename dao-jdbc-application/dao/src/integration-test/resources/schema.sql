create table if not exists model_type
(
    id   int2 generated always as identity primary key,
    name varchar(50) unique not null
);

create table if not exists transport_type
(
    id   int2 generated always as identity primary key,
    name varchar(50) unique not null
);

create table if not exists client
(
    id         int4 generated always as identity primary key,
    first_name varchar(30) not null,
    last_name  varchar(30) not null
);

create table if not exists transport
(
    id                int4 generated always as identity primary key,
    model_type_id     int2 not null,
    transport_type_id int2 not null,
    client_id         int4,

    constraint fk_transport_model_type_id foreign key (model_type_id) references model_type (id),
    constraint fk_transport_transport_type_id foreign key (transport_type_id) references transport_type (id),
    constraint fk_transport_client_id foreign key (client_id) references client (id)
);

insert into model_type (name) values
('BMW_M3_1996'),
('AUDI_A7_2023'),
('MAN_L130_2020');

insert into transport_type (name) values
('Motorbike'),
('Car'),
('Bus');
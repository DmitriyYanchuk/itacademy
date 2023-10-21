create table model_type (
	id int2 generated always as identity primary key,
	name varchar(50) unique not null
);

insert into model_type (name) values
('BMW 520d'),
('Mercedes-Benz Viano'),
('Kawasaki ZZR'),
('Porsche Panamera'),
('Audi A7'),
('Volkswagen T6'),
('Suzuki GSX-S1000'),
('Yamaha FJR1300ES'),
('Ford Transit');

create table transport_type (
	id int2 generated always as identity primary key,
	name varchar(50) unique not null
);

insert into transport_type (name) values
('automobile'),
('motorbike'),
('minibus');

create table client (
	id int4 generated always as identity primary key,
	first_name varchar(30) not null,
	last_name varchar(30) not null
);

insert into client (first_name, last_name) values
('Patricia', 'Wolf'),
('Justyna', 'Gorska'),
('Eugene', 'Aliyeva'),
('Devika', 'Heuvelink'),
('Lucas', 'Marshall'),
('Noriko', 'Honda'),
('Agramant', 'Bourgouin'),
('Holly', 'Mingay'),
('Eufrosina', 'Romano');

create table transport (
	id int4 generated always as identity primary key,
	model_type_id int2 not null,
	transport_type_id int2 not null,
	client_id int4,

	constraint fk_transport_model_type_id foreign key (model_type_id) references model_type(id),
	constraint fk_transport_transport_type_id foreign key (transport_type_id) references transport_type(id),
	constraint fk_transport_client_id foreign key (client_id) references client(id)
);

insert into transport (model_type_id, transport_type_id, client_id) values
(1, 1, 4),
(2, 3, 2),
(3, 2, 6),
(4, 1, 3),
(5, 1, 1),
(6, 3, 8),
(7, 2, 9),
(8, 2, 5),
(9, 3, 7);

select tr.id, mt."name" as modelName, tt."name" as transportType, cl.first_name as firstName, cl.last_name as lastName  from transport tr 
	left join model_type mt on tr.model_type_id = mt.id
	left join transport_type tt on tr.transport_type_id = tt.id 
	left join client cl on tr.client_id = cl.id ;
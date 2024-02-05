create table student
(
id int4 generated always as identity primary key,
first_name varchar(30) not null,
last_name varchar(30) not null
);

create table teacher
(
id int4 generated always as identity primary key,
first_name varchar(30) not null,
last_name varchar(30) not null
);

create table subject
(
id int4 generated always as identity primary key,
name varchar(50) not null,

constraint uq__subject__name unique (name)
);

create table subject_teacher
(
id int4 generated always as identity primary key,
subject_id int4 not null,
teacher_id int4 not null,

constraint fk__subject_teacher__subject_id foreign key (subject_id) references subject(id),
constraint fk__subject_teacher__teacher_id foreign key (teacher_id) references teacher(id)
);

create table assessment
(
id int4 generated always as identity primary key,
assessment int2 not null,
subject_id int4 not null,
student_id int4 not null,

constraint fk__assessment__subject_id foreign key (subject_id) references subject(id),
constraint fk__assessment__student_id foreign key (student_id) references student(id)
);
create table address
(
	id int4 generated always as identity primary key,
	city varchar(30) not null,
	street varchar(50) not null,
	building_number varchar(10) not null
);

create table school
(
	id int4 generated always as identity primary key,
	name varchar(250) not null,
	address_id int4 not null,

	constraint fk__school__address__id foreign key (address_id) references address (id)
);

create table subject
(
    id int4 generated always as identity primary key,
    name varchar(50) not null,

    constraint uq__subject__name unique (name)
);

create table school_subject
(
    id          int4 generated always as identity primary key
    school_id   int4 not null,
    subject_id  int4 not null,

    constraint fk__school_subject__school_id foreign key (school_id) references school (id),
    constraint fk__school_subject__subject_id foreign key (subject_id) references subject (id)
);

create table parent
(
	id int4 generated always as identity primary key,
	first_name varchar(30) not null,
	last_name varchar(30) not null
);

create table student
(
    id int4 generated always as identity primary key,
    	first_name varchar(30) not null,
    	last_name varchar(30) not null
);

create table student_parent
(
    id          int4 generated always as identity primary key,
    student_id  int4 not null,
    parent_id   int4 not null,

    constraint fk__student_parent__student__id foreign key (student_id) references student (id),
    constraint fk__student_parent__parent__id foreign key (parent_id)  references parent (id)
);

create table student_school
(
    id          int4 generated always as identity primary key,
    student_id  int4 not null,
    school_id   int4 not null,

    constraint fk__student_school__student__id foreign key (student_id) references student (id),
    constraint fk__student_school__school__id foreign key (school_id) references school (id),
    constraint uq__student_school__student_id__school_id unique (student_id, school_id)
);

create table teacher
(
	id int4 generated always as identity primary key,
	first_name varchar(30) not null,
	last_name varchar(30) not null
);

create table school_teacher
(
	id int4 generated always as identity primary key,
	school_id int4 not null,
	teacher_id int4 not null,

	constraint fk__school_teacher__school__id foreign key (school_id) references school (id),
	constraint fk__school_teacher__teacher_id foreign key (teacher_id) references teacher (id)
);

create table student_group
(
    id              int4 generated always as identity primary key,
    name            varchar(30) not null,
    school_id       int4 not null,
    group_owner_id  int4 not null,

    constraint fk__student_group__school__id foreign key (school_id) references school(id),
    constraint fk__student_group__teacher__id foreign key (group_owner_id) references teacher (id)
);
create table student_group_student_link
(
    id                  int4 generated always as identity primary key,
    student_group_id    int4 not null,
    student_id          int4 not null,

    constraint fk__student_group_student_link__student_group__id foreign key (student_group_id) references student_group(id),
    constraint fk__student_group_student_link__student__id foreign key (student_id) references student(id),
    constraint uq__student_group_student_link__student_group_id__student_id unique (student_group_id, student_id)
);

create table student_group_subject_link
(
    id                   int4 generated always as identity primary key,
    student_group_id     int4 not null,
    teacher_id           int4 not null,
    subject_id           int4 not null,

    constraint fk__student_group_subject_link__student_group__id foreign key (student_group_id) references student_group(id),
    constraint fk__student_group_subject_link__teacher__id foreign key (teacher_id) references teacher(id),
    constraint fk__student_group_subject_link__subject__id foreign key (subject_id) references subject(id)
    constraint uq__student_group_subject_link__student_group_id__teacher_id__subject_id unique (student_group_id, teacher_id, subject_id)
);

create table schedule
(
    id                   int4 generated always as identity primary key,
    start_date           timestamp with time zone not null,
    end_date             timestamp with time zone not null,
    school_id            int4 not null,

    constraint fk__schedule__school__id foreign key (school_id) references school (id)
);

create table group_room
(
    id                   int4 generated always as identity primary key,
    student_group_id     int4 not null,
    room_owner_id             int4 not null,
    school_id            int4 not null,

    constraint fk__group_room__student_group__id foreign key (student_group_id) references student_group(id),
    constraint fk__group_room__teacher__id foreign key (room_owner_id) references teacher(id),
    constraint fk__group_room__school__id foreign key (school_id) references school(id)
);

create table lesson
(
    id                  int4 generated always as identity primary key,
    teacher_id          int4 not null,
    student_group_id    int4 not null,
    subject_id          int4 not null,
    group_room_id       int4 not null,
    schedule_id         int4 not null,

    constraint fk__lesson__teacher__id foreign key (teacher_id) references teacher (id),
    constraint fk__lesson__student_group__id foreign key (student_group_id) references student_group (id),
    constraint fk__lesson__subject__id foreign key (subject_id) references subject (id),
    constraint fk__lesson__group_room__id foreign key (group_room_id) references group_room (id),
    constraint fk__lesson__schedule__id foreign key (schedule_id) references schedule (id)
);

create table assessment
(
    id                  int4 generated always as identity primary key,
    lesson_id           int4 not null,
    student_id          int4 not null,
    assessment          int2 not null,

    constraint fk__assessment__lesson__id foreign key (lesson_id) references lesson(id),
    constraint fk__assessment__student__id foreign key (student_id) references student(id)
);

create table attend
(
    id                  int4 generated always as identity primary key,
    lesson_id           int4 not null,
    student_id          int4 not null,
    visited             boolean not null,

   constraint fk__attend__lesson__id foreign key (lesson_id) references lesson(id),
   constraint fk__attend__student__id foreign key (student_id) references student(id)
);
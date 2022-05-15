create table user (
	userId varchar(20) primary key,
    name varchar(10) not null,
    password varchar(20) not null,
    email varchar(30) not null,
    company varchar(20)
);
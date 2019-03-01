CREATE DATABASE post;USE post;
CREATE TABLE user (
id integer not null auto_increment,
email varchar (40) not null,
password   varchar(20) not null,
first_name varchar (30),
last_name varchar (30),
primary key (id)
constraint user_email_uindex
unique (email)
);


CREATE TABLE category (
id integer not null auto_increment,
category varchar (30),
primary key (id)
);

CREATE TABLE mail (
id integer not null auto_increment,
sender_id int not null,
recipient_id int not null,
date_time datetime not null default now(),
title varchar (50) default "Unknown",
tags varchar (50) default "Unknown",
category_id int not null,
message text,
primary key (id),
foreign key (sender_id) references user (id),
foreign key (recipient_id) references user (id),
foreign key (category_id) references category (id)
);
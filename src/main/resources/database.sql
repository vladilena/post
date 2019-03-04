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
sender varchar (30) not null,
recipient varchar (30) not null,
date_time timestamp not null default now(),
title varchar (50) default "Unknown",
tags varchar (50) default "Unknown",
category varchar (20) not null,
message text,
related_user int not null,
primary key (id),
foreign key (related_user) references user (id)
);

create table customer_category (
id int not null auto_increment primary key,
category varchar (20),
user_id int not null,
foreign key (user_id) references user (id)
);

 delimiter //
CREATE TRIGGER `spam_detector` BEFORE INSERT ON mail
FOR EACH ROW
       BEGIN
           IF NEW.title LIKE "discount" THEN
               SET NEW.category = "spam";
           END IF;
       END;//

delimiter ;

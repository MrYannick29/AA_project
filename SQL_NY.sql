/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  r0744479
 * Created: 14-okt-2021
 */


drop table NY_groepen;
drop table NY_gebruikers;
drop table NY_vaccincertificaat;
drop table NY_testcertificaat;
drop table NY_burgers;


create table NY_gebruikers (
	gebruikersnaam 	varchar(50) not null primary key,
	paswoord	varchar(50) not null
);

create table NY_groepen (
        gebruikersnaam  varchar(50) not null references NY_gebruikers primary key,
        groep varchar(50) not null
);

create table NY_burgers (
        bid  int not null primary key,
        naam varchar(50)
);


create table NY_vaccincertificaat (
        vcid int not null primary key,
        bid int references NY_burgers,
        dtm date,
        soort varchar(50)
);

create table NY_testcertificaat (
        tcid int not null primary key,
        bid int references NY_burgers,
        dtm date,
        res int

);


insert into NY_gebruikers values('Yannick', 'Yannick');
insert into NY_gebruikers values('Niels', 'Niels');
insert into NY_groepen values('Yannick', 'beheer');
insert into NY_groepen values('Niels', 'burger');





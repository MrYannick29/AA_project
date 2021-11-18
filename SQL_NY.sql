/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  r0744479
 * Created: 14-okt-2021
 */

drop table NY_gebruikers;
drop table NY_groepen ;



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

create table NY_certificaat (
    //subtabellen
);

create table NY_vaccincertificaat (
        cid int not null primary key,
);

create table NY_testcertificaat (
        cid int not null primary key,
);

create table NY_burgercertificaat (
        bid int not null references NY_burgers,
        cid int not null references NY_certificaat
);


insert into NY_gebruikers values('Yannick', 'Yannick');
insert into NY_gebruikers values('Niels', 'Niels');
insert into NY_groepen values('Yannick', 'beheer');
insert into NY_groepen values('Niels', 'beheer');





drop table customer cascade ;
create table customer
(
    id       integer  not null auto_increment primary key,
    password varchar(100) not null,
    name     varchar(100) unique,
    email    varchar(100),
    phone varchar(50)
);



drop table role cascade ;
create table  role
(
    id
    integer
    not
    null
    auto_increment
    primary
    key,
    name
    varchar
(
    100
) not null


    );



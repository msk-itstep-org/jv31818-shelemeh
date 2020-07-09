create table customer (
          customer_id  integer not null auto_increment primary key
          references  product(product_id),
          name varchar(20) not null,
          email varchar (20) not null,
          phone varchar (20) not null,
          password varchar (10) not null

);

create table product (
        product_id integer  unsigned not null auto_increment primary key
        references customer(customer_id),
        name_product varchar (100) not null,
        final_price double (10,0) not null





);

create table checklist (
        check_id  integer  unsigned not null auto_increment primary key,
         address varchar (1000) not null,
        manager varchar (1000) not null




);


create table role (
id integer not null auto_increment primary key,
name varchar (100) not null


);

create table custom_roles(
customer_id integer not null references customer(id),
roles_id integer  not null references roles(id),

primary key (customer_id,roles_id)


);




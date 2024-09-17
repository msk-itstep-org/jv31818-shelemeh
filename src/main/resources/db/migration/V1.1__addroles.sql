create table if not exists customer
(
    id integer unsigned not null auto_increment primary key
    references product
(
    id
) on delete cascade,
    name varchar
(
    20
) not null,
    email varchar
(
    20
),
    phone varchar
(
    20
),
    password varchar
(
    10
) not null,
    reset_password_token varchar
(
    20
),
    code varchar
(
    100
)

    );

create table if not exists product
(
    id integer unsigned not null auto_increment primary key
    references customer
(
    id
),
    name_product varchar
(
    100
) not null,
    final_price double
(
    10,
    0
) not null

    );



create table if not exists role
(
    id
    integer
    unsigned
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

create table if not exists custom_roles
(
    customer_id integer not null references role
(
    id
),
    roles_id integer not null references customer
(
    id
),
    primary key auto_increment
(
    customer_id,
    roles_id
)
    );

create table if not exists cart
(
    id
    integer
    not
    null
    auto_increment
    primary
    key,
    prod_list
    varchar
(
    100
)

    );

create table if not exists category
(
    id integer not null auto_increment primary key
    references product
(
    id
),
    category_name varchar
(
    100
) not null,
    image varchar
(
    20
)
    );






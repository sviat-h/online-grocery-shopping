create table address
(
    id             bigserial not null,
    city           varchar(32),
    house_number   varchar(8),
    state          varchar(32),
    street_address varchar(32),
    zip_code       varchar(16),
    primary key (id)
);

create table carts_users
(
    users_id int8 not null,
    cart_id  int8 not null
);

create table products
(
    id           bigserial not null,
    availability boolean,
    category     varchar not null,
    description  varchar(2048) not null,
    name         varchar(64) not null,
    price        numeric(19, 2) not null,
    quantity     int4 not null,
    primary key (id)
);

create table shopping_cart
(
    id          bigserial not null,
    quantity    int4,
    total_price numeric(19, 2),
    primary key (id)
);

create table shopping_cart_products
(
    shopping_cart_id int8 not null,
    products_id      int8 not null
);

create table user_role
(
    user_id int8 not null,
    roles   varchar(16)
);

create table users
(
    id              bigserial not null,
    activation_code varchar(128),
    active          boolean,
    email           varchar(32),
    password        varchar(256) not null,
    username        varchar(16) not null,
    address_id      int8,
    primary key (id)
);

alter table if exists carts_users add constraint cart_user_cart_fk foreign key (cart_id) references shopping_cart;
alter table if exists carts_users add constraint cart_user_user_fk foreign key (users_id) references products;
alter table if exists shopping_cart_products add constraint shopping_cart_product_product_fk foreign key (products_id) references products;
alter table if exists shopping_cart_products add constraint shopping_cart_product_cart_fk foreign key (shopping_cart_id) references shopping_cart;
alter table if exists user_role add constraint user_role_user_fk foreign key (user_id) references users;
alter table if exists users add constraint user_address_fk foreign key (address_id) references address;
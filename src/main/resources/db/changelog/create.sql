create table users(
    id serial primary key,
    login varchar(100) not null unique,
    password varchar(100) not null,
    role varchar(25)
);

create table product(
    id serial primary key,
    name varchar(255) not null,
    description text,
    price int not null,
    price_type varchar(25) not null,
    language varchar(50) not null,
    created timestamp not null,
    updated timestamp
);
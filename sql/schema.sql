drop database if exists orders_ws24_db;

drop table if exists orders_ws24_db;

create database orders_ws24_db;

use orders_ws24_db;

create table orders (
	order_id int auto_increment,
    order_date date,
    customer_name varchar(128),
    ship_address varchar(128),
    notes text,
    tax decimal(2,2) default '0.05',
    constraint pk_order_id primary key(order_id)
);

create table order_details (
	id int auto_increment,
    product varchar(64),
    unit_price decimal(3,2),
    discount decimal(2,2) default 0.99,
    quantity int, 	
    order_id int,
    constraint pk_order_details_id primary key (id),
    constraint fk_order_details_to_order foreign key (order_id) references orders(order_id)
);
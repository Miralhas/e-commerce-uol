set foreign_key_checks = 0;

delete from order_item;
delete from sales_order;
delete from product;
delete from user_roles;
delete from role;
delete from user;

set foreign_key_checks = 1;

alter table order_item auto_increment = 1;
alter table sales_order auto_increment = 1;
alter table product auto_increment = 1;
alter table role auto_increment = 1;
alter table user auto_increment = 1;

insert into role(name) values('ADMIN'), ('USER');

INSERT INTO user (id, email, username, telefone, created_at, password)
VALUES (1, 'admin@admin.com', 'admin', '1133004499', utc_timestamp, '$2a$10$CNr3GCdNp8wLUGP/XbUqHOjkSA2josmmAHu38jQgP11g/P/Xulgoa');

insert into user_roles(role_id, user_id) VALUES (1,1);
insert into user_roles(role_id, user_id) VALUES (2,1);

insert into product(name, description, stock, price, created_at, updated_at, status) values('a', 'aaa', 10, 10, utc_timestamp, utc_timestamp, 'ACTIVE');
insert into product(name, description, stock, price, created_at, updated_at, status) values('b', 'bbb', 10, 10, utc_timestamp, utc_timestamp, 'ACTIVE');

insert into sales_order(total_value, created_at, status, user_id) values(100, '2024-07-03 14:10:33.742389', 'CREATED', 1);
insert into order_item(sales_order_id, product_id, total_price, quantity) values(1, 1, 10, 1);

insert into sales_order(total_value, created_at, status, user_id) values(100, '2024-05-03 14:10:33.742389', 'CREATED', 1);
insert into order_item(sales_order_id, product_id, total_price, quantity) values(2, 2, 10, 1);

insert into sales_order(total_value, created_at, status, user_id) values(100, '2024-07-03 14:10:33.742389', 'CREATED', 1);
insert into order_item(sales_order_id, product_id, total_price, quantity) values(3, 2, 10, 1);

insert into sales_order(total_value, created_at, status, user_id) values(100, '2024-06-30 14:10:33.742389', 'CREATED', 1);
insert into order_item(sales_order_id, product_id, total_price, quantity) values(4, 2, 10, 1);

insert into sales_order(total_value, created_at, status, user_id) values(100, '2024-07-07 14:10:33.742389', 'CREATED', 1);
insert into order_item(sales_order_id, product_id, total_price, quantity) values(5, 2, 10, 1);

insert into sales_order(total_value, created_at, status, user_id) values(100, '2024-07-08 14:10:33.742389', 'CREATED', 1);
insert into order_item(sales_order_id, product_id, total_price, quantity) values(6, 2, 10, 1);


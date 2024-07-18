set foreign_key_checks = 0;

delete from order_item;
delete from sales_order;
delete from product;
delete from user_roles;
delete from role;
delete from user;
delete from password_reset_token;

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

INSERT INTO user (id, email, username, telefone, created_at, password)
VALUES (2, 'abc@g.com', 'abc', '11958512321', utc_timestamp, '$2a$10$Vb.FssW37mML9aIyFtoiz.S16N5BAqN60aphB5ftdR5BluSCYR1He');

insert into user_roles(role_id, user_id) VALUES (2,2);


insert into product(name, description, stock, price, created_at, updated_at, status) values('xx', 'Teclado Mecânico', 10, 200, utc_timestamp, utc_timestamp, 'ACTIVE');
insert into product(name, description, stock, price, created_at, updated_at, status) values('Mouse', 'Mouse Logitech', 10, 100, utc_timestamp, utc_timestamp, 'ACTIVE');

insert into sales_order(total_value, created_at, status, user_id) values(100, '2024-07-03 14:10:33.742389', 'CREATED', 1);
insert into order_item(sales_order_id, product_id, total_price, quantity) values(1, 1, 200, 1);

insert into sales_order(total_value, created_at, status, user_id) values(100, '2024-05-03 14:10:33.742389', 'CREATED', 1);
insert into order_item(sales_order_id, product_id, total_price, quantity) values(2, 2, 100, 1);

insert into sales_order(total_value, created_at, status, user_id) values(100, '2024-07-03 14:10:33.742389', 'CREATED', 1);
insert into order_item(sales_order_id, product_id, total_price, quantity) values(3, 2, 100, 1);

insert into sales_order(total_value, created_at, status, user_id) values(100, '2024-06-30 14:10:33.742389', 'CREATED', 1);
insert into order_item(sales_order_id, product_id, total_price, quantity) values(4, 2, 100, 1);

insert into sales_order(total_value, created_at, status, user_id) values(100, '2024-07-07 14:10:33.742389', 'CREATED', 1);
insert into order_item(sales_order_id, product_id, total_price, quantity) values(5, 2, 100, 1);

insert into sales_order(total_value, created_at, status, user_id) values(100, '2024-07-08 14:10:33.742389', 'CREATED', 1);
insert into order_item(sales_order_id, product_id, total_price, quantity) values(6, 2, 100, 1);


insert into product(name, description, stock, price, created_at, updated_at, status) values('a', 'aaa', 10, 10, utc_timestamp, utc_timestamp, 'ACTIVE');
insert into product(name, description, stock, price, created_at, updated_at, status) values('b', 'bbb', 10, 10, utc_timestamp, utc_timestamp, 'ACTIVE');

insert into sales_order(total_value, created_at, status) values(100, '2024-07-03 14:10:33.742389', 'CREATED');
insert into order_item(sales_order_id, product_id, total_price, quantity) values(1, 1, 10, 1);

insert into sales_order(total_value, created_at, status) values(100, '2024-05-03 14:10:33.742389', 'CREATED');
insert into order_item(sales_order_id, product_id, total_price, quantity) values(2, 2, 10, 1);

insert into sales_order(total_value, created_at, status) values(100, '2024-07-03 14:10:33.742389', 'CREATED');
insert into order_item(sales_order_id, product_id, total_price, quantity) values(3, 2, 10, 1);

insert into sales_order(total_value, created_at, status) values(100, '2024-06-30 14:10:33.742389', 'CREATED');
insert into order_item(sales_order_id, product_id, total_price, quantity) values(4, 2, 10, 1);

insert into sales_order(total_value, created_at, status) values(100, '2024-07-07 14:10:33.742389', 'CREATED');
insert into order_item(sales_order_id, product_id, total_price, quantity) values(5, 2, 10, 1);

insert into sales_order(total_value, created_at, status) values(100, '2024-07-08 14:10:33.742389', 'CREATED');
insert into order_item(sales_order_id, product_id, total_price, quantity) values(6, 2, 10, 1);
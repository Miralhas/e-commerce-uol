create table order_item (
    quantity integer not null,
    total_price decimal(38,2),
    id bigint not null auto_increment,
    product_id bigint not null,
    sales_order_id bigint not null,
    primary key (id)
)engine=InnoDB default charset=UTF8MB4;
alter table order_item add constraint FK551losx9j75ss5d6bfsqvijna foreign key (product_id) references product (id);
alter table order_item add constraint FKbxrm3app1yyhq2hsrq31r8r4t foreign key (sales_order_id) references sales_order (id);

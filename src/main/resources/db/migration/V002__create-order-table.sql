create table sales_order (
    total_value decimal(38,2),
    created_at datetime(6),
    id bigint not null auto_increment,
    updated_at datetime(6),
    status enum ('CANCELED','CONFIRMED','CREATED'),
    primary key (id)
)engine=InnoDB default charset=UTF8MB4;
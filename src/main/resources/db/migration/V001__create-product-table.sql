create table product (
    price decimal(38,2) not null,
    stock integer not null,
    created_at datetime(6),
    id bigint not null auto_increment,
    updated_at datetime(6),
    description varchar(255) not null,
    name varchar(255) not null,
    status enum ('ACTIVE','INACTIVE'),
    primary key (id)
)engine=InnoDB default charset=UTF8MB4;
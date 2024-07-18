create table role (
    id bigint not null auto_increment,
    name enum ('ADMIN','USER') not null,
    primary key (id)
)engine=InnoDB default charset=UTF8MB4;
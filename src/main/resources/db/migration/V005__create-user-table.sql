create table user (
    id bigint not null auto_increment,
    email varchar(255) not null,
    username varchar(255) not null,
    telefone varchar(255) not null,
    created_at datetime(6),
    password varchar(255) not null,
    primary key (id)
)engine=InnoDB default charset=UTF8MB4;

alter table user add constraint UKob8kqyqqgmefl0aco34akdtpe unique (email);
alter table user add constraint UKsb8bbouer5wak8vyiiy4pf2bx unique (username);
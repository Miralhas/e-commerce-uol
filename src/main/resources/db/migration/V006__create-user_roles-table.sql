create table user_roles (
    role_id bigint not null,
    user_id bigint not null,
    primary key (role_id, user_id)
)engine=InnoDB default charset=UTF8MB4;

alter table user_roles add constraint FKrhfovtciq1l558cw6udg0h0d3 foreign key (role_id) references role (id);
alter table user_roles add constraint FK55itppkw3i07do3h7qoclqd4k foreign key (user_id) references user (id);

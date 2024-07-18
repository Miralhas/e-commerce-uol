create table password_reset_token (
    user_id bigint not null,
    token VARCHAR(36) NOT NULL,
    primary key (user_id)
)engine=InnoDB default charset=UTF8MB4;

alter table password_reset_token add constraint FK5lwtbncug84d4ero33v3cfxvl foreign key (user_id) references user (id);
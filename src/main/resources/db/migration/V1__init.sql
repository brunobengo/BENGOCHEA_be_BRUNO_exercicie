create table role
(
    id   varchar(255) not null
        primary key,
    name varchar(255) not null
);

create table membership
(
    id      varchar(255) not null
        primary key,
    team_id varchar(255)  not null,
    user_id varchar(255)  not null,
    role_id varchar(255) not null,
    constraint UKovs2w4ph57xdtsrc5y3iqjvh1
        unique (role_id, team_id, user_id),
    constraint FK53w0pjjpc5me9htly7j74ym1s
        foreign key (role_id) references role (id)
);

insert into role(id, name)
values ('cb8b6322-d396-49ba-9fe9-3641c3ef9130', 'Developer');
insert into role(id, name)
values ('25bbb7d2-26f3-11ec-9621-0242ac130002', 'Product Owner');
insert into role(id, name)
values ('37969e22-26f3-11ec-9621-0242ac130002', 'Tester');
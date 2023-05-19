create table user
(
    id   varchar(255) not null
      primary key,
    first_name varchar(80) NOT NULL,
    last_name varchar(80) NOT NULL,
    display_name varchar(20) NOT NULL,
    avatar_url varchar(255) NOT NULL,
    location varchar(100) NOT NULL
);

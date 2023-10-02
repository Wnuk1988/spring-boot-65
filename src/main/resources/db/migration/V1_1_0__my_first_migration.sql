create table public.user_info
(
    id         bigserial
        primary key,
    first_name varchar not null,
    last_name  varchar,
    created    timestamp,
    updated    timestamp
);

alter table public.user_info
    owner to postgres;

create table public.author
(
    id          bigserial
        constraint author_pk
            primary key,
    author_name varchar(10)
);

alter table public.author
    owner to postgres;

create table public.books
(
    id        bigserial
        constraint books_pk
            primary key,
    book_name varchar(10)
);

alter table public.books
    owner to postgres;

create table public.l_author_book
(
    id        bigserial
        constraint l_author_book_pk
            primary key,
    author_id integer not null
        constraint l_author_book_author_id_fk
            references public.author
            on update cascade on delete cascade,
    book_id   integer not null
        constraint l_author_book_books_id_fk
            references public.books
            on update cascade on delete cascade
);

alter table public.l_author_book
    owner to postgres;

create table public.pages
(
    id      bigserial
        constraint pages_pk
            primary key,
    book_id integer not null
        constraint page_books_id_fk
            references public.books
            on update cascade on delete cascade,
    text    varchar(50)
);

alter table public.pages
    owner to postgres;

create table public.security_credentials
(
    id            bigserial
        primary key,
    user_login    varchar                                   not null
        unique,
    user_password varchar                                   not null,
    user_role     varchar default 'USER'::character varying not null,
    user_id       bigint                                    not null
        unique
        constraint security_credentials_user_info_id_fk
            references public.user_info
            on update cascade on delete cascade
);

alter table public.security_credentials
    owner to postgres;


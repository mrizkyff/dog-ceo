create table breeds
(
    id                 uuid         not null,
    created_date       timestamp(6) with time zone,
    last_modified_date timestamp(6) with time zone,
    name               varchar(255) not null,
    ttl                bigint,
    primary key (id)
);


create table sub_breeds
(
    id                 uuid         not null,
    created_date       timestamp(6) with time zone,
    last_modified_date timestamp(6) with time zone,
    name               varchar(255) not null,
    breed_id           uuid         not null,
    primary key (id)
);


create table sub_breed_images
(
    sub_breed_id uuid not null,
    images       varchar(255)
);


alter table if exists breeds
    drop constraint if exists UK_a3bphmgjsvn8aleiujdw0sbef;


alter table if exists breeds
    add constraint UK_a3bphmgjsvn8aleiujdw0sbef unique (name);


alter table if exists sub_breeds
    add constraint FKkil4eq9saoo5k2us1xwiqj1k6
        foreign key (breed_id)
            references breeds;


alter table if exists sub_breed_images
    add constraint FK3jsyfjyvv2oilocjfr3edqmhd
        foreign key (sub_breed_id)
            references sub_breeds;

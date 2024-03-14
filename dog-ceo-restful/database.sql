DROP TABLE IF EXISTS sub_breeds;
DROP TABLE IF EXISTS breeds;

CREATE TABLE IF NOT EXISTS breeds (
                        id uuid not null,
                        created_date timestamp(6) with time zone,
                        last_modified_date timestamp(6) with time zone,
                        name varchar(255),
                        primary key (id)
);

CREATE TABLE IF NOT EXISTS sub_breeds (
                            id uuid not null,
                            created_date timestamp(6) with time zone,
                            last_modified_date timestamp(6) with time zone,
                            name varchar(255),
                            breed_id uuid,
                            primary key (id)
);

alter table if exists sub_breeds
    add constraint FKkil4eq9saoo5k2us1xwiqj1k6
        foreign key (breed_id)
            references breeds;
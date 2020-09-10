drop table if exists product;
create table product
(
    id   bigint       not null primary key,
    name varchar(500) not null
);
create index ix_product_id on product (id);
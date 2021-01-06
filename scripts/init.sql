--////////////////////////////////////////////////////////////////////////////////////////////////////////////
create schema if not exists dev_pedidos;
set schema 'dev_pedidos';

--////////////////////////////////////////////////////////////////////////////////////////////////////////////
create sequence dev_pedidos.tb_order_id_seq;

create table dev_pedidos.tb_order   
(
    id        bigint not null DEFAULT nextval('dev_pedidos.tb_order_id_seq'),
    latitude  float,
    longitude float,
    moment    TIMESTAMP WITHOUT TIME ZONE,
    status    int,
    primary key (id)
);

create unique index uq_tb_order on dev_pedidos.tb_order (id);

alter table dev_pedidos.tb_order
    owner to postgres;

--////////////////////////////////////////////////////////////////////////////////////////////////////////////
create sequence dev_pedidos.tb_product_id_seq;

create table dev_pedidos.tb_product
(
    id          bigint not null DEFAULT nextval('dev_pedidos.tb_product_id_seq'),
    description TEXT,
    image_uri   varchar(255),
    name        varchar(255),
    price       float,
    primary key (id)
);

create unique index uq_tb_product on dev_pedidos.tb_product (id);

alter table dev_pedidos.tb_product
    owner to postgres;

--////////////////////////////////////////////////////////////////////////////////////////////////////////////
create table dev_pedidos.tb_order_product
(
    order_id   int not null,
    product_id int not null,
    primary key (order_id, product_id)
);

create unique index uq_tb_order_product on dev_pedidos.tb_order_product (order_id, product_id);

alter table dev_pedidos.tb_order_product
    owner to postgres;

--////////////////////////////////////////////////////////////////////////////////////////////////////////////

alter table if exists dev_pedidos.tb_order_product
    add constraint fk_tb_order_product_tb_product
        foreign key (product_id) references dev_pedidos.tb_product;

alter table if exists dev_pedidos.tb_order_product
    add constraint fk_tb_order_product_tb_order
        foreign key (order_id) references dev_pedidos.tb_order;

--////////////////////////////////////////////////////////////////////////////////////////////////////////////

INSERT INTO dev_pedidos.tb_product (name, price, image_Uri, description)
VALUES ('Pizza de Calabresa', 50.0, 'https://github.com/devsuperior/1.png',
        'Pizza calabresa com queijo, molho e massa especial'),
       ('Pizza Quatro Queijos', 40.0, 'https://github.com/devsuperior/2.png', 'Pizza quatro queijos muito boa'),
       ('Pizza de Escarola', 60.0, 'https://github.com/devsuperior/3.png', 'Pizza escarola muito boa');

INSERT INTO dev_pedidos.tb_order (status, latitude, longitude, moment)
VALUES (0, 213123, 12323, TIMESTAMP WITH TIME ZONE '2021-01-04T11:00:00Z'),
       (1, 3453453, 3534534, TIMESTAMP WITH TIME ZONE '2021-01-05T11:00:00Z');

INSERT INTO dev_pedidos.tb_order_product (order_id, product_id)
VALUES (1, 1),
       (1, 2),
       (2, 2),
       (2, 3);

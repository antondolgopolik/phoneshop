drop table if exists phone2color;
drop table if exists colors;
drop table if exists stocks;
drop table if exists phones;
drop table if exists users;
drop table if exists authorities;
drop table if exists customer_info;
drop table if exists orders;
drop table if exists order_items;

create table phones
(
    id                      bigint auto_increment,
    brand                   varchar(50)  not null,
    model                   varchar(255) not null,
    price                   float,
    display_size            float,
    weight                  smallint,
    length                  float,
    width                   float,
    height                  float,
    announced               datetime,
    device_type             varchar(50),
    os                      varchar(100),
    display_resolution      varchar(50),
    pixel_density           smallint,
    display_technology      varchar(50),
    back_camera_megapixels  float,
    front_camera_megapixels float,
    ram                     float,
    internal_storage        float,
    battery_capacity        smallint,
    talk_time               float,
    stand_by_time           float,
    bluetooth               varchar(50),
    positioning             varchar(100),
    image_url               varchar(255),
    description             varchar(4096),
    constraint PHONES_PK
        primary key (id)
);
create unique index PHONES_BRAND_MODEL_UINDEX
    on phones (brand, model);

create table colors
(
    id   bigint auto_increment,
    code varchar(50) not null,
    constraint COLORS_PK
        primary key (id)
);
create unique index COLORS_CODE_UINDEX
    on colors (code);

create table phone2color
(
    phone_id bigint,
    color_id bigint,
    constraint PHONE2COLOR_COLORS_ID_FK
        foreign key (color_id) references COLORS
            on update cascade on delete cascade,
    constraint PHONE2COLOR_PHONES_ID_FK
        foreign key (phone_id) references PHONES
            on update cascade on delete cascade
);

create table stocks
(
    phone_id int,
    stock    int not null,
    reserved int not null,
    constraint STOCKS_PK
        primary key (phone_id),
    constraint STOCKS_PHONES_ID_FK
        foreign key (phone_id) references PHONES
            on update cascade on delete cascade
);

create table users
(
    username varchar_ignorecase(50)  not null,
    password varchar_ignorecase(500) not null,
    enabled  boolean                 not null,
    constraint USERS_PK
        primary key (username)
);

create table authorities
(
    username  varchar_ignorecase(50) not null,
    authority varchar_ignorecase(50) not null,
    constraint AUTHORITIES_USERS_USERNAME_FK
        foreign key (username) references USERS
);
create unique index AUTHORITIES_USERNAME_AUTHORITY_UINDEX
    on authorities (username, authority);

create table customer_info
(
    id           bigint auto_increment,
    username     varchar_ignorecase(50),
    first_name   varchar(50) not null,
    last_name    varchar(50) not null,
    address      varchar     not null,
    phone_number varchar(25) not null,
    constraint CUSTOMER_INFO_PK
        primary key (id),
    constraint CUSTOMER_INFO_USERS_USERNAME_FK
        foreign key (username) references USERS
            on update cascade on delete set null
);

create table orders
(
    id              varchar,
    username        varchar_ignorecase(50),
    date            datetime    not null,
    total_quantity  int         not null,
    subtotal        float       not null,
    delivery_price  float       not null,
    total_price     float       not null,
    first_name      varchar(50) not null,
    last_name       varchar(50) not null,
    address         varchar     not null,
    phone_number    varchar(25) not null,
    additional_info varchar,
    status          tinyint     not null,
    constraint ORDERS_PK
        primary key (id),
    constraint ORDERS_USERS_USERNAME_FK
        foreign key (username) references USERS
            on update cascade on delete set null
);

create table order_items
(
    order_id varchar,
    phone_id bigint,
    quantity int not null,
    constraint ORDER_ITEMS_PK
        primary key (order_id, phone_id),
    constraint ORDER_ITEMS_ORDERS_ID_FK
        foreign key (order_id) references ORDERS
            on update cascade on delete cascade,
    constraint ORDER_ITEMS_PHONES_ID_FK
        foreign key (phone_id) references PHONES
);
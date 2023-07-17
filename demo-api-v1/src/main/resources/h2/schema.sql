create table if not exists goods_category (
    `id` bigint AUTO_INCREMENT NOT NULL primary key,
    `category_name` varchar(100) NOT NULL,
    `parent_id` bigint NOT NULL,
    `update_time` datetime NOT NULL
);
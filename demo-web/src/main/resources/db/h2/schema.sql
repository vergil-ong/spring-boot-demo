create table if not exists t_test1 (
    `id` bigint AUTO_INCREMENT NOT NULL primary key,
    `name` varchar(100) NOT NULL,
    `age` int NOT NULL,
    `create_time` datetime NOT NULL
);
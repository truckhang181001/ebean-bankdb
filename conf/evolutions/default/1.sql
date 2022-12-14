-- # --- !Ups
--
-- create table customer(
--                      'id'  bigint AUTO_INCREMENT,
--                      'customer_name' varchar,
--                      'birth' date,
--                      'token' varchar,
--                      'created_at' datetime,
--                      'updated_at' datetime,
--                      'deleted' BOOLEAN,
--                      constraint pk_user primary key (id));
--
-- create table customer_account(
--                              id bigint AUTO_INCREMENT,
--                              customer_id bigint,
--                              bank_account_id bigint,
--                              created_at datetime,
--                              updated_at datetime,
--                              deleted BOOLEAN,
--                              constraint pk_user_account primary key (id));
--
-- create table customer_device(
--                             id bigint AUTO_INCREMENT,
--                             customer_id bigint,
--                             token varchar,
--                             platform tinyint,
--                             expired_at datetime,
--                             created_at datetime,
--                             updated_at datetime,
--                             deleted BOOLEAN,
--                             constraint pk_user_device primary key (id));
-- create table bank_account(
--                              id bigint AUTO_INCREMENT,
--                              account_number varchar,
--                              expired_at datetime,
--                              created_at datetime,
--                              updated_at datetime,
--                              deleted BOOLEAN,
--                              constraint pk_bank_account primary key (id));
--
--
-- # --- !Downs
--
-- SET REFERENTIAL_INTEGRITY FALSE;
--
-- drop table if exists customer;
-- drop table if exists customer_account;
-- drop table if exists customer_account;
-- drop table if exists bank_account;
--
-- SET REFERENTIAL_INTEGRITY TRUE;

# create database

CREATE DATABASE ScutInformationSystem DEFAULT CHARACTER SET utf8;

# user table

CREATE TABLE user(
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(20) NOT NULL ,
    email VARCHAR(320) NOT NULL UNIQUE ,
    password VARCHAR(32)NOT NULL
)ENGINE=InnoDB

# priority table

CREATE TABLE priority(
    identity ENUM('admin', 'root', 'org' ) NOT NULL ,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
)ENGINE=InnoDB

# information type table

CREATE TABLE info_type(
    type_id INT PRIMARY KEY AUTO_INCREMENT,
    category VARCHAR(20) NOT NULL UNIQUE
)ENGINE=INNODB

# activity_info table

CREATE TABLE activity_info(
    info_id INT PRIMARY KEY AUTO_INCREMENT,
    type_id INT ,
    user_id INT,
    org_name VARCHAR(30) NOT NULL ,
    introduction TEXT NOT NULL ,
    name varchar(30) NOT NULL,
    start_time TIMESTAMP ,
    end_time TIMESTAMP,
    FOREIGN KEY (type_id) REFERENCES info_type(type_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
)ENGINE=InnoDB

# Team recruitment table

CREATE TABLE team_info(
    info_id INT PRIMARY KEY AUTO_INCREMENT,
    type_id INT ,
    title VARCHAR(30) NOT NULL ,
    introduction TEXT NOT NULL ,
    user_id INT ,
    FOREIGN KEY (type_id) REFERENCES info_type(type_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
)ENGINE=INNODB

# comment table

CREATE TABLE comment(
    user_id INT ,
    type_id INT NOT NULL ,
    info_id INT NOT NULL ,
    comment_id INT PRIMARY KEY AUTO_INCREMENT,
    parent_id INT ,
    create_time TIMESTAMP ,
    comment TEXT NOT NULL ,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
)ENGINE=INNODB

# info_addition_request table

CREATE TABLE info_addition_request (
    info_id INT ,
    type_id INT ,
    status ENUM('pass', 'waiting', 'rejected' ) NOT NULL,
    FOREIGN KEY (type_id) REFERENCES info_type(type_id)
)ENGINE=INNODB

# favor table
CREATE TABLE favor(
    user_id INT ,
    type_id INT ,
    info_id INT ,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (type_id) REFERENCES info_type(type_id)
)ENGINE=INNODB


CREATE TABLE `interview_spring`.`student` (
                                              `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                              `soname` VARCHAR(256) NOT NULL,
                                              `age` TINYINT UNSIGNED NOT NULL,
                                              PRIMARY KEY (`id`));

INSERT INTO `interview_spring`.`student` (`soname`, `age`) VALUES ('soname1', '18');
INSERT INTO `interview_spring`.`student` (`soname`, `age`) VALUES ('soname2', '19');
INSERT INTO `interview_spring`.`student` (`soname`, `age`) VALUES ('soname3', '20');
INSERT INTO `interview_spring`.`student` (`soname`, `age`) VALUES ('soname4', '21');
INSERT INTO `interview_spring`.`student` (`soname`, `age`) VALUES ('soname5', '22');

CREATE TABLE `roles` (
                         `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                         `soname` VARCHAR(45) NOT NULL,
                         PRIMARY KEY (`id`));

insert into roles (soname) values ('ROLE_ADMIN');
insert into roles (soname) values ('ROLE_USER');

CREATE TABLE `users` (
                         `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                         `login` VARCHAR(45) NOT NULL,
                         `password` VARCHAR(256) NOT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE);

CREATE TABLE `users_roles` (
                               `id_user` INT UNSIGNED NOT NULL,
                               `id_role` INT UNSIGNED NOT NULL,
                               PRIMARY KEY (`id_user`, `id_role`),
                               INDEX `fk_users_roles_role_id_idx` (`id_role` ASC) VISIBLE,
                               CONSTRAINT `fk_users_roles_user_id`
                                   FOREIGN KEY (`id_user`)
                                       REFERENCES `users` (`id`)
                                       ON DELETE CASCADE
                                       ON UPDATE RESTRICT,
                               CONSTRAINT `fk_users_roles_role_id`
                                   FOREIGN KEY (`id_role`)
                                       REFERENCES `roles` (`id`)
                                       ON DELETE RESTRICT
                                       ON UPDATE RESTRICT);
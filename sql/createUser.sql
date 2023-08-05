CREATE TABLE t_user (
	`id` INT (11) auto_increment,
	`username` VARCHAR (20) NOT NULL UNIQUE,
	`password` VARCHAR (32) NOT NULL,
	`email` VARCHAR (200),
	PRIMARY KEY (`id`)
);

INSERT INTO t_user (`username`,`password`,`email`)VALUES('admin','admin','admin@nwu.cn');

SELECT
	*
FROM
	t_user;
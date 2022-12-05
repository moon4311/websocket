CREATE TABLE `topic_tb` (
  `seq` varchar(10) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `regist_date` datetime DEFAULT NULL,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE `seq_tb` (
  `tbl_nm` varchar(20) DEFAULT NULL,
  `cd` varchar(10) DEFAULT NULL,
  `seq` int(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


insert into seq_tb values('topic','tp',1);


/* FUNCTION */
CREATE DEFINER=`sample`@`%` FUNCTION `fn_seq`(_CD varchar(20)) RETURNS varchar(8) CHARSET latin1
BEGIN
	UPDATE seq_tb set seq = seq+1 where cd =_CD;
RETURN (select concat( upper(cd) ,lpad(seq,'6','0')) from seq_tb where cd = _CD );
END
CREATE TABLE `process_event_log` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `msg_id` int(11) DEFAULT NULL,
  `msg_datetime` date DEFAULT NULL,
  `seq_no` int(11) DEFAULT NULL,
  `event_name` varchar(20) DEFAULT NULL,
  `batch_id` int(11) DEFAULT NULL,
  `thickness` int(11) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `event_datetime` datetime DEFAULT  CURRENT_TIMESTAMP,
  `event_status` int(1) DEFAULT 0,
  PRIMARY KEY (`event_id`),
  UNIQUE KEY `process_event_log_UNIQUE` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `output_batch` (
  `batch_id` int(11) DEFAULT NULL,
  `thickness` int(11) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `event_id` int(11) NOT NULL,
  PRIMARY KEY (`event_id`),
  UNIQUE KEY `output_batch_UNIQUE` (`batch_id`),
  CONSTRAINT `output_batch_fk1` FOREIGN KEY (`event_id`) REFERENCES `process_event_log` (`event_id`) ON DELETE CASCADE ON UPDATE CASCADE
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `input_batch` (
  `batch_id` int(11) DEFAULT NULL,
  `thickness` int(11) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `rejection_id` varchar(20) DEFAULT NULL,
  `event_id` int(11) NOT NULL,
  PRIMARY KEY (`batch_id`),
  UNIQUE KEY `input_batch_UNIQUE` (`batch_id`),
  CONSTRAINT `input_batch_fk1` FOREIGN KEY (`event_id`) REFERENCES `process_event_log` (`event_id`) ON DELETE CASCADE ON UPDATE CASCADE
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
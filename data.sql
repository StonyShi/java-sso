# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 10.0.11.172 (MySQL 5.5.5-10.0.14-MariaDB-log)
# Database: sso
# Generation Time: 2017-06-23 02:06:42 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table global_variable
# ------------------------------------------------------------

DROP TABLE IF EXISTS `global_variable`;

CREATE TABLE `global_variable` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `field` varchar(255) DEFAULT NULL COMMENT '字段',
  `value` varchar(255) DEFAULT NULL COMMENT '值',
  `description` varchar(250) DEFAULT NULL COMMENT '描述',
  `available` tinyint(3) DEFAULT '1' COMMENT '1 有效',
  `insert_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `update_date` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='全局变量';

LOCK TABLES `global_variable` WRITE;
/*!40000 ALTER TABLE `global_variable` DISABLE KEYS */;

INSERT INTO `global_variable` (`id`, `field`, `value`, `description`, `available`, `insert_date`, `update_date`)
VALUES
	(1,'monitor_url','http://127.0.0.1:8090','监控后台',1,'2016-09-06 15:34:18',NULL),
	(2,'boss_url','http://127.0.0.1:8095','后台地址',1,'2016-09-06 15:34:18',NULL),
	(3,'sso_url','http://127.0.0.1:8099','sso地址',1,'2016-09-06 15:34:18',NULL);

/*!40000 ALTER TABLE `global_variable` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sessions
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sessions`;

CREATE TABLE `sessions` (
  `id` varchar(200) NOT NULL DEFAULT '',
  `session` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;



# Dump of table sys_app
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_app`;

CREATE TABLE `sys_app` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT 'app名称',
  `app_key` varchar(100) DEFAULT NULL COMMENT 'appKey',
  `app_secret` varchar(100) DEFAULT NULL COMMENT 'app 安全码',
  `available` tinyint(1) DEFAULT '1' COMMENT '1 有效',
  `insert_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `update_date` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_app_app_key` (`app_key`),
  UNIQUE KEY `IDX_SYS_APP_NAME` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

LOCK TABLES `sys_app` WRITE;
/*!40000 ALTER TABLE `sys_app` DISABLE KEYS */;

INSERT INTO `sys_app` (`id`, `name`, `app_key`, `app_secret`, `available`, `insert_date`, `update_date`)
VALUES
	(1,'中心服务器','645ba616-370a-43a8-a8e0-993e7a590cf0','bb74abb6-bae0-47dd-a7b1-9571ea3a0f33',1,'2016-05-12 19:31:33',NULL),
	(2,'APP-1','645ba612-370a-43a8-a8e0-993e7a590cf0','bb74abb2-bae0-47dd-a7b1-9571ea3a0f33',1,'2016-05-12 19:31:33',NULL),
	(3,'APP-2','645ba613-370a-43a8-a8e0-993e7a590cf0','bb74abb3-bae0-47dd-a7b1-9571ea3a0f33',1,'2016-05-12 19:31:33',NULL),
	(19,'APP-MONITOR','5efaf7c8-8315-35c7-ab67-ac3bde4963bd','cfa383ca-468d-3851-848f-eb6e72eb32c5',1,'2016-05-27 14:11:21',NULL),
	(21,'APP-SUPPORT','c487d790-36aa-3956-8b40-411133d046ee','7b16c47a-eabb-34ae-acf7-6c643a8428df',1,'2016-05-31 20:20:56',NULL),
	(24,'APP-MANAGER','c3cd89f8-8a47-30c9-834b-03b248924a96','1918f439-6a43-3fb4-a004-49574b943ced',1,'2016-06-12 10:21:57',NULL),
	(25,'APP-BOSS','1cf212a6-af29-3707-b976-7b82f7b9c83b','269d09e0-333b-335c-a034-c922a9373dfe',1,'2016-06-12 10:22:26',NULL),
	(26,'APP-BUSINESS','987252a4-5afc-3d2b-8cfb-e1460e8e62d7','b3a05320-fc5c-3aac-8dfd-344cf8896b94',1,'2016-06-12 10:35:40',NULL);

/*!40000 ALTER TABLE `sys_app` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_icon
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_icon`;

CREATE TABLE `sys_icon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(85) DEFAULT NULL COMMENT '图标名称',
  `description` varchar(250) DEFAULT NULL COMMENT '描述',
  `url` varchar(200) DEFAULT NULL COMMENT '图标地址',
  `available` int(1) DEFAULT '1' COMMENT '1 有效',
  `insert_date` timestamp NULL DEFAULT NULL COMMENT '插入时间',
  `update_date` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图标管理';

LOCK TABLES `sys_icon` WRITE;
/*!40000 ALTER TABLE `sys_icon` DISABLE KEYS */;

INSERT INTO `sys_icon` (`id`, `name`, `description`, `url`, `available`, `insert_date`, `update_date`)
VALUES
	(3,'icon-compass',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(4,'icon-eur',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(5,'icon-dollar',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(6,'icon-yen',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(7,'icon-won',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(8,'icon-file-text',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(9,'icon-sort-by-attributes-alt',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(10,'icon-thumbs-down',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(11,'icon-xing-sign',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(12,'icon-instagram',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(13,'icon-bitbucket-sign',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(14,'icon-long-arrow-up',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(15,'icon-windows',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(16,'icon-skype',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(17,'icon-male',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(18,'icon-archive',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(19,'icon-renren',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(20,'icon-collapse',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(21,'icon-euro',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(22,'icon-inr',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(23,'icon-cny',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(24,'icon-btc',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(25,'icon-sort-by-alphabet',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(26,'icon-sort-by-order',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(27,'icon-youtube-sign',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(28,'icon-youtube-play',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(29,'icon-flickr',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(30,'icon-tumblr',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(31,'icon-long-arrow-left',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(32,'icon-android',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(33,'icon-foursquare',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(34,'icon-gittip',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(35,'icon-bug',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(36,'icon-collapse-top',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(37,'icon-gbp',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(38,'icon-rupee',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(39,'icon-renminbi',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(40,'icon-bitcoin',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(41,'icon-sort-by-alphabet-alt',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(42,'icon-sort-by-order-alt',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(43,'icon-youtube',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(44,'icon-dropbox',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(45,'icon-adn',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(46,'icon-tumblr-sign',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(47,'icon-long-arrow-right',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(48,'icon-linux',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(49,'icon-trello',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(50,'icon-sun',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(51,'icon-vk',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(52,'icon-expand',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(53,'icon-usd',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(54,'icon-jpy',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(55,'icon-krw',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(56,'icon-file',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(57,'icon-sort-by-attributes',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(58,'icon-thumbs-up',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(59,'icon-xing',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(60,'icon-stackexchange',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(61,'icon-bitbucket',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(62,'icon-long-arrow-down',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(63,'icon-apple',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(64,'icon-dribble',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(65,'icon-female',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(66,'icon-moon',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(67,'icon-weibo',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(68,'icon-adjust',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(69,'icon-asterisk',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(70,'icon-ban-circle',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(71,'icon-bar-chart',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(72,'icon-barcode',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(73,'icon-beaker',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(74,'icon-bell',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(75,'icon-bolt',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(76,'icon-book',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(77,'icon-bookmark',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(78,'icon-bookmark-empty',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(79,'icon-briefcase',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(80,'icon-bullhorn',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(81,'icon-calendar',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(82,'icon-camera',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(83,'icon-camera-retro',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(84,'icon-certificate',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(85,'icon-check',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(86,'icon-check-empty',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(87,'icon-cloud',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(88,'icon-cog',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(89,'icon-cogs',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(90,'icon-comment',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(91,'icon-comment-alt',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(92,'icon-comments',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(93,'icon-comments-alt',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(94,'icon-credit-card',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(95,'icon-dashboard',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(96,'icon-download',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(97,'icon-download-alt',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(98,'icon-edit',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(99,'icon-envelope',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(100,'icon-envelope-alt',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(101,'icon-exclamation-sign',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(102,'icon-external-link',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(103,'icon-eye-close',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(104,'icon-eye-open',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(105,'icon-facetime-video',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(106,'icon-film',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(107,'icon-filter',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(108,'icon-fire',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(109,'icon-flag',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(110,'icon-folder-close',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(111,'icon-folder-open',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(112,'icon-gift',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(113,'icon-glass',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(114,'icon-globe',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(115,'icon-group',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(116,'icon-hdd',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(117,'icon-headphones',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(118,'icon-heart',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(119,'icon-heart-empty',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(120,'icon-home',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(121,'icon-inbox',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(122,'icon-info-sign',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(123,'icon-key',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(124,'icon-leaf',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(125,'icon-legal',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(126,'icon-lemon',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(127,'icon-lock',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(128,'icon-unlock',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(129,'icon-magic',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(130,'icon-magnet',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(131,'icon-map-marker',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(132,'icon-minus',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(133,'icon-minus-sign',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(134,'icon-money',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(135,'icon-move',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(136,'icon-music',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(137,'icon-off',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(138,'icon-ok',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(139,'icon-ok-circle',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(140,'icon-ok-sign',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(141,'icon-pencil',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(142,'icon-picture',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(143,'icon-plane',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(144,'icon-plus',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(145,'icon-plus-sign',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(146,'icon-print',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(147,'icon-pushpin',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(148,'icon-qrcode',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(149,'icon-question-sign',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(150,'icon-random',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(151,'icon-refresh',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(152,'icon-remove',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(153,'icon-remove-circle',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(154,'icon-remove-sign',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(155,'icon-reorder',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(156,'icon-resize-horizontal',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(157,'icon-resize-vertical',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(158,'icon-retweet',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(159,'icon-road',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(160,'icon-rss',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(161,'icon-screenshot',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(162,'icon-search',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(163,'icon-share',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(164,'icon-share-alt',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(165,'icon-shopping-cart',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(166,'icon-signal',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(167,'icon-signin',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(168,'icon-signout',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(169,'icon-sitemap',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(170,'icon-sort',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(171,'icon-sort-down',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(172,'icon-sort-up',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(173,'icon-star',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(174,'icon-star-empty',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(175,'icon-star-half',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(176,'icon-tag',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(177,'icon-tags',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(178,'icon-tasks',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(179,'icon-thumbs-down',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(180,'icon-thumbs-up',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(181,'icon-time',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(182,'icon-tint',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(183,'icon-trash',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(184,'icon-trophy',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(185,'icon-truck',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(186,'icon-umbrella',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(187,'icon-upload',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(188,'icon-upload-alt',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(189,'icon-user',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(190,'icon-user-md',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(191,'icon-volume-off',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(192,'icon-volume-down',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(193,'icon-volume-up',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(194,'icon-warning-sign',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(195,'icon-wrench',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(196,'icon-zoom-in',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(197,'icon-zoom-out',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(198,'icon-bitcoin',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(199,'icon-eur',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(200,'icon-jpy',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(201,'icon-usd',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(202,'icon-btc',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(203,'icon-euro',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(204,'icon-won',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(205,'icon-cny',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(206,'icon-gbp',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(207,'icon-renminbi',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(208,'icon-dollar',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(209,'icon-inr',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(210,'icon-rupee',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(211,'icon-file',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(212,'icon-cut',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(213,'icon-copy',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(214,'icon-paste',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(215,'icon-save',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(216,'icon-undo',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(217,'icon-repeat',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(218,'icon-paper-clip',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(219,'icon-text-height',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(220,'icon-text-width',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(221,'icon-align-left',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(222,'icon-align-center',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(223,'icon-align-right',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(224,'icon-align-justify',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(225,'icon-indent-left',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(226,'icon-indent-right',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(227,'icon-font',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(228,'icon-bold',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(229,'icon-italic',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(230,'icon-strikethrough',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(231,'icon-underline',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(232,'icon-link',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(233,'icon-columns',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(234,'icon-table',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(235,'icon-th-large',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(236,'icon-th',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(237,'icon-th-list',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(238,'icon-list',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(239,'icon-list-ol',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(240,'icon-list-ul',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(241,'icon-list-alt',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(242,'icon-arrow-down',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(243,'icon-arrow-left',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(244,'icon-arrow-right',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(245,'icon-arrow-up',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(246,'icon-chevron-down',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(247,'icon-circle-arrow-down',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(248,'icon-circle-arrow-left',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(249,'icon-circle-arrow-right',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(250,'icon-circle-arrow-up',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(251,'icon-chevron-left',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(252,'icon-caret-down',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(253,'icon-caret-left',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(254,'icon-caret-right',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(255,'icon-caret-up',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(256,'icon-chevron-right',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(257,'icon-hand-down',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(258,'icon-hand-left',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(259,'icon-hand-right',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(260,'icon-hand-up',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(261,'icon-chevron-up',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(262,'icon-play-circle',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(263,'icon-play',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(264,'icon-pause',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(265,'icon-stop',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(266,'icon-step-backward',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(267,'icon-fast-backward',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(268,'icon-backward',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(269,'icon-forward',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(270,'icon-fast-forward',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(271,'icon-step-forward',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(272,'icon-eject',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(273,'icon-fullscreen',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(274,'icon-resize-full',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(275,'icon-resize-small',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(276,'icon-adn',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(277,'icon-bitbucket-sign',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(278,'icon-dribble',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(279,'icon-flickr',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(280,'icon-github-sign',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(281,'icon-html5',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(282,'icon-linux',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(283,'icon-renren',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(284,'icon-tumblr',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(285,'icon-vk',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(286,'icon-xing-sign',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(287,'icon-android',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(288,'icon-bitcoin',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(289,'icon-dropbox',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(290,'icon-foursquare',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(291,'icon-gittip',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(292,'icon-instagram',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(293,'icon-maxcdn',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(294,'icon-skype',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(295,'icon-tumblr-sign',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(296,'icon-weibo',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(297,'icon-youtube',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(298,'icon-apple',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(299,'icon-facebook',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(300,'icon-github',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(301,'icon-google-plus',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(302,'icon-linkedin',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(303,'icon-pinterest',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(304,'icon-stackexchange',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(305,'icon-twitter',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(306,'icon-windows',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(307,'icon-youtube-play',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(308,'icon-bitbucket',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(309,'icon-css3',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(310,'icon-facebook-sign',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(311,'icon-github-alt',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(312,'icon-google-plus-sign',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(313,'icon-linkedin-sign',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(314,'icon-pinterest-sign',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(315,'icon-trello',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(316,'icon-twitter-sign',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(317,'icon-xing',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(318,'icon-youtube-sign',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(319,'icon-ambulance',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(320,'icon-plus-sign-alt',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(321,'icon-h-sign',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(322,'icon-stethoscope',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(323,'icon-hospital',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(324,'icon-user-md',NULL,NULL,1,'2016-06-03 16:08:43',NULL),
	(325,'icon-medkit',NULL,NULL,1,'2016-06-03 16:08:43',NULL);

/*!40000 ALTER TABLE `sys_icon` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_organization
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_organization`;

CREATE TABLE `sys_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '组织名称',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父ID,顶级节点默认0',
  `parent_ids` varchar(100) DEFAULT NULL,
  `available` int(1) DEFAULT '1' COMMENT '1 有效',
  `logo` varchar(80) DEFAULT NULL COMMENT 'logo icon图标',
  `insert_date` timestamp NULL DEFAULT NULL COMMENT '插入时间',
  `update_date` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_sys_organization_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

LOCK TABLES `sys_organization` WRITE;
/*!40000 ALTER TABLE `sys_organization` DISABLE KEYS */;

INSERT INTO `sys_organization` (`id`, `name`, `parent_id`, `parent_ids`, `available`, `logo`, `insert_date`, `update_date`)
VALUES
	(1,'总公司',0,'0/',1,NULL,'2016-05-27 18:06:20',NULL),
	(2,'大中华区',1,'0/1/',1,NULL,'2016-05-27 18:06:20',NULL),
	(3,'太平洋区',1,'0/1/',1,NULL,'2016-05-27 18:06:20',NULL),
	(4,'美洲区',1,'0/1/2/',1,NULL,'2016-05-27 18:06:20',NULL),
	(5,'北极圈',1,'11,22,33',1,NULL,'2016-05-27 18:06:20',NULL),
	(13,'阿里巴巴',2,NULL,1,'icon-maxcdn','2016-05-27 18:06:20',NULL),
	(14,'百度',2,NULL,1,'icon-bitcoin','2016-05-27 18:06:56',NULL),
	(15,'新浪',2,NULL,0,'icon-weibo','2016-05-27 18:21:56',NULL),
	(16,'腾讯',2,NULL,0,'icon-linux','2016-05-30 11:41:18',NULL),
	(17,'Google',3,NULL,1,'icon-google-plus','2016-05-30 12:01:03',NULL),
	(18,'布鲁克',3,NULL,1,'icon-foursquare','2016-05-30 13:30:09',NULL),
	(23,'新华社',2,NULL,1,'icon-pinterest','2016-06-14 20:15:30',NULL),
	(24,'LinkIn',4,NULL,1,'icon-linkedin','2016-06-15 16:59:47',NULL);

/*!40000 ALTER TABLE `sys_organization` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_resource
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_resource`;

CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `type` varchar(50) DEFAULT NULL COMMENT 'MENU,BUTTON',
  `url` varchar(200) DEFAULT NULL COMMENT '资源路径',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父ID',
  `parent_ids` varchar(100) DEFAULT NULL,
  `permission` varchar(100) NOT NULL DEFAULT '' COMMENT '权限',
  `available` tinyint(1) DEFAULT '1' COMMENT '1 有效',
  `icon` varchar(60) DEFAULT NULL COMMENT '样式',
  `insert_date` timestamp NULL DEFAULT NULL COMMENT '插入时间',
  `update_date` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_SYS_RESOURCE_NAME_TYPE` (`name`,`type`,`permission`,`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

LOCK TABLES `sys_resource` WRITE;
/*!40000 ALTER TABLE `sys_resource` DISABLE KEYS */;

INSERT INTO `sys_resource` (`id`, `name`, `type`, `url`, `parent_id`, `parent_ids`, `permission`, `available`, `icon`, `insert_date`, `update_date`)
VALUES
	(1,'系统管理','MENU','',0,'0/','admin:menu',1,'icon-cog','2016-05-12 19:34:25',NULL),
	(11,'组织机构管理','MENU','/organization',1,'0/1/','admin:organization:menu',1,'icon-group','2016-05-12 19:34:25','2016-06-22 13:38:49'),
	(12,'组织机构新增','BUTTON','',11,'0/1/11/','admin:organization:create',1,'','2016-05-12 19:34:25','2016-06-22 13:39:01'),
	(13,'组织机构修改','BUTTON','',11,'0/1/11/','admin:organization:update',1,NULL,'2016-05-12 19:34:25',NULL),
	(14,'组织机构删除','BUTTON','',11,'0/1/11/','admin:organization:delete',1,NULL,'2016-05-12 19:34:25',NULL),
	(15,'组织机构查看','BUTTON','',11,'0/1/11/','admin:organization:view',1,NULL,'2016-05-12 19:34:25',NULL),
	(16,'应用管理','MENU','/app',1,'0/1/','admin:app:menu',1,' icon-apple','2016-05-12 19:34:25',NULL),
	(17,'应用新增','BUTTON','',16,'0/1/16/','admin:app:create',1,NULL,'2016-05-12 19:34:25',NULL),
	(18,'应用修改','BUTTON','',16,'0/1/16/','admin:app:update',1,NULL,'2016-05-12 19:34:25',NULL),
	(19,'应用删除','BUTTON','',16,'0/1/16/','admin:app:delete',1,NULL,'2016-05-12 19:34:25',NULL),
	(20,'应用查看','BUTTON','',16,'0/1/16/','admin:app:view',1,NULL,'2016-05-12 19:34:25',NULL),
	(21,'用户管理','MENU','/user',1,'0/1/','admin:user:menu',1,'icon-user','2016-05-12 19:34:25',NULL),
	(23,'用户修改','BUTTON','',21,'0/1/21/','admin:user:update',1,NULL,'2016-05-12 19:34:25',NULL),
	(24,'用户删除','BUTTON','',21,'0/1/21/','admin:user:delete',1,NULL,'2016-05-12 19:34:25',NULL),
	(25,'用户查看','BUTTON','',21,'0/1/21/','admin:user:view',1,NULL,'2016-05-12 19:34:25',NULL),
	(31,'资源管理','MENU','/resource',1,'0/1/','admin:resource:menu',1,'icon-globe','2016-05-12 19:34:25',NULL),
	(32,'资源新增','BUTTON','',31,'0/1/31/','admin:resource:create',1,NULL,'2016-05-12 19:34:25',NULL),
	(33,'资源修改','BUTTON','',31,'0/1/31/','admin:resource:update',1,NULL,'2016-05-12 19:34:25',NULL),
	(34,'资源删除','BUTTON','',31,'0/1/31/','admin:resource:delete',1,NULL,'2016-05-12 19:34:25',NULL),
	(35,'资源查看','BUTTON','',31,'0/1/31/','admin:resource:view',1,NULL,'2016-05-12 19:34:25',NULL),
	(41,'角色管理','MENU','/role',1,'0/1/','admin:role:menu',1,'icon-eye-open','2016-05-12 19:34:25',NULL),
	(42,'角色新增','BUTTON','',41,'0/1/41/','admin:role:create',1,NULL,'2016-05-12 19:34:25',NULL),
	(43,'角色修改','BUTTON','',41,'0/1/41/','admin:role:update',1,NULL,'2016-05-12 19:34:25',NULL),
	(44,'角色删除','BUTTON','',41,'0/1/41/','admin:role:delete',1,NULL,'2016-05-12 19:34:25',NULL),
	(45,'角色查看','BUTTON','',41,'0/1/41/','admin:role:view',1,NULL,'2016-05-12 19:34:25',NULL),
	(51,'授权管理','MENU','/authorization',1,'0/1/','admin:authorization:menu',1,'icon-key','2016-05-12 19:34:25',NULL),
	(52,'授权新增','BUTTON','',51,'0/1/51/','admin:authorization:create',1,NULL,'2016-05-12 19:34:25',NULL),
	(53,'授权修改','BUTTON','',51,'0/1/51/','admin:authorization:update',1,NULL,'2016-05-12 19:34:25',NULL),
	(54,'授权删除','BUTTON','',51,'0/1/51/','admin:authorization:delete',1,NULL,'2016-05-12 19:34:25',NULL),
	(55,'授权查看','BUTTON','',51,'0/1/51/','admin:authorization:view',1,NULL,'2016-05-12 19:34:25',NULL),
	(65,'统计管理','MENU','',0,NULL,'monitor:statistics:menu',1,'icon-share-alt','2016-06-03 16:08:43','2016-07-08 13:29:01'),
	(66,'司机统计','BUTTON','/statistics/driver',65,NULL,'monitor:statistics:driver:menu',1,'','2016-06-12 11:52:39','2016-06-22 16:05:51'),
	(67,'司机统计列表','MENU','/statistics/driver/list',65,NULL,'monitor:statistics:driver:list',1,'icon-archive','2016-06-12 11:53:42','2016-06-22 16:06:00'),
	(68,'订单列表','MENU','/statistics/order/list',65,NULL,'monitor:statistics:order:menu',1,'icon-sun','2016-06-12 11:56:44','2016-06-22 16:06:10'),
	(69,'图标管理','MENU','/icon',1,NULL,'admin:icon:menu',1,'icon-fire','2016-06-12 14:27:05','2016-06-22 13:46:05'),
	(71,'主题颜色','BUTTON','',1,NULL,'admin:theme:color',1,'','2016-06-14 17:14:13','2016-06-22 13:45:49'),
	(123,'定时任务表达式','MENU','/cron',1,NULL,'admin:cron:menu',1,'icon-time','2016-06-23 14:15:54',NULL),
	(141,'变量管理','MENU','/global/variable',1,NULL,'admin:variable:menu',1,'icon-gittip','2016-07-28 17:49:38',NULL),
	(142,'ZK管理','MENU','',0,NULL,'admin:zk:menu',1,'icon-beaker','2016-07-28 17:54:03',NULL),
	(143,'Host管理','MENU','/zk',142,NULL,'admin:zk:hostmenu',1,'icon-hospital','2016-07-28 17:55:35',NULL),
	(144,'Node管理','MENU','/zk/node',142,NULL,'admin:zk:node:menu',1,'icon-tint','2016-07-28 17:56:27',NULL);

/*!40000 ALTER TABLE `sys_resource` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) DEFAULT NULL COMMENT '角色',
  `description` varchar(250) DEFAULT NULL COMMENT '角色描述',
  `available` tinyint(1) DEFAULT '1' COMMENT '1 有效',
  `insert_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `update_date` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_SYS_ROLE_N` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;



# Dump of table sys_role_resource
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role_resource`;

CREATE TABLE `sys_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `resource_id` bigint(11) DEFAULT NULL COMMENT '资源ID',
  `available` int(1) NOT NULL DEFAULT '1' COMMENT '是否有效 1 有效',
  `insert_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `update_date` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_sys_role_resource_ids` (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色资源映射表';

LOCK TABLES `sys_role_resource` WRITE;
/*!40000 ALTER TABLE `sys_role_resource` DISABLE KEYS */;

INSERT INTO `sys_role_resource` (`id`, `role_id`, `resource_id`, `available`, `insert_date`, `update_date`)
VALUES
	(19,4,21,1,'2016-05-12 19:06:28',NULL),
	(24,4,15,1,'2016-05-12 22:06:28',NULL),
	(25,4,31,1,'2016-05-12 20:06:28',NULL),
	(94,11,67,1,'2016-06-15 15:08:36',NULL),
	(95,11,68,1,'2016-06-15 15:08:36',NULL),
	(96,11,65,1,'2016-06-15 15:08:36',NULL),
	(97,11,24,1,'2016-06-15 15:08:36',NULL),
	(98,11,15,1,'2016-06-15 15:08:36',NULL),
	(99,15,67,1,'2016-06-15 15:09:15',NULL),
	(100,15,21,1,'2016-06-15 15:09:15',NULL),
	(101,15,68,1,'2016-06-15 15:09:15',NULL),
	(102,15,31,1,'2016-06-15 15:09:15',NULL),
	(103,15,65,1,'2016-06-15 15:09:15',NULL),
	(104,15,16,1,'2016-06-15 15:09:15',NULL),
	(105,15,11,1,'2016-06-15 15:09:15',NULL),
	(177,2,21,1,'2016-06-16 15:39:41',NULL),
	(178,2,41,1,'2016-06-16 15:39:41',NULL),
	(179,2,31,1,'2016-06-16 15:39:41',NULL),
	(180,2,51,1,'2016-06-16 15:39:41',NULL),
	(181,2,16,1,'2016-06-16 15:39:41',NULL),
	(259,25,93,1,'2016-06-22 15:37:28',NULL),
	(260,25,92,1,'2016-06-22 15:37:28',NULL),
	(261,25,91,1,'2016-06-22 15:37:28',NULL),
	(262,25,90,1,'2016-06-22 15:37:28',NULL),
	(263,25,86,1,'2016-06-22 15:37:28',NULL),
	(264,25,87,1,'2016-06-22 15:37:28',NULL),
	(265,25,84,1,'2016-06-22 15:37:28',NULL),
	(266,25,88,1,'2016-06-22 15:37:28',NULL),
	(267,25,100,1,'2016-06-22 15:37:28',NULL),
	(268,25,85,1,'2016-06-22 15:37:28',NULL),
	(269,25,89,1,'2016-06-22 15:37:28',NULL),
	(467,43,112,1,'2016-07-01 12:06:52',NULL),
	(468,43,92,1,'2016-07-01 12:06:52',NULL),
	(469,43,91,1,'2016-07-01 12:06:52',NULL),
	(470,43,90,1,'2016-07-01 12:06:52',NULL),
	(471,43,86,1,'2016-07-01 12:06:52',NULL),
	(472,43,87,1,'2016-07-01 12:06:52',NULL),
	(473,43,100,1,'2016-07-01 12:06:52',NULL),
	(474,43,89,1,'2016-07-01 12:06:52',NULL),
	(493,39,92,1,'2016-07-01 12:07:31',NULL),
	(494,39,91,1,'2016-07-01 12:07:31',NULL),
	(495,39,90,1,'2016-07-01 12:07:31',NULL),
	(496,39,89,1,'2016-07-01 12:07:31',NULL),
	(497,38,92,1,'2016-07-01 12:07:47',NULL),
	(498,38,91,1,'2016-07-01 12:07:47',NULL),
	(499,38,90,1,'2016-07-01 12:07:47',NULL),
	(500,38,89,1,'2016-07-01 12:07:47',NULL),
	(501,3,92,1,'2016-07-01 12:08:30',NULL),
	(502,3,41,1,'2016-07-01 12:08:30',NULL),
	(503,3,91,1,'2016-07-01 12:08:30',NULL),
	(504,3,90,1,'2016-07-01 12:08:30',NULL),
	(505,3,51,1,'2016-07-01 12:08:30',NULL),
	(506,3,89,1,'2016-07-01 12:08:30',NULL),
	(507,16,21,1,'2016-07-01 12:08:53',NULL),
	(508,16,92,1,'2016-07-01 12:08:53',NULL),
	(509,16,91,1,'2016-07-01 12:08:53',NULL),
	(510,16,31,1,'2016-07-01 12:08:53',NULL),
	(511,16,90,1,'2016-07-01 12:08:53',NULL),
	(512,16,11,1,'2016-07-01 12:08:53',NULL),
	(513,16,89,1,'2016-07-01 12:08:53',NULL),
	(709,42,116,1,'2016-08-30 18:07:21',NULL),
	(710,42,117,1,'2016-08-30 18:07:21',NULL),
	(711,42,115,1,'2016-08-30 18:07:21',NULL),
	(712,42,95,1,'2016-08-30 18:07:21',NULL),
	(713,42,94,1,'2016-08-30 18:07:21',NULL),
	(714,42,121,1,'2016-08-30 18:07:21',NULL),
	(715,42,122,1,'2016-08-30 18:07:21',NULL),
	(716,42,92,1,'2016-08-30 18:07:21',NULL),
	(717,42,91,1,'2016-08-30 18:07:21',NULL),
	(718,42,90,1,'2016-08-30 18:07:21',NULL),
	(719,42,118,1,'2016-08-30 18:07:21',NULL),
	(720,42,119,1,'2016-08-30 18:07:21',NULL),
	(721,42,120,1,'2016-08-30 18:07:21',NULL),
	(722,42,80,1,'2016-08-30 18:07:21',NULL),
	(723,42,89,1,'2016-08-30 18:07:21',NULL),
	(1002,44,125,1,'2016-09-26 11:40:21',NULL),
	(1003,44,126,1,'2016-09-26 11:40:21',NULL),
	(1004,44,127,1,'2016-09-26 11:40:21',NULL),
	(1005,44,114,1,'2016-09-26 11:40:21',NULL),
	(1006,44,128,1,'2016-09-26 11:40:21',NULL),
	(1007,44,112,1,'2016-09-26 11:40:21',NULL),
	(1008,44,113,1,'2016-09-26 11:40:21',NULL),
	(1009,44,92,1,'2016-09-26 11:40:21',NULL),
	(1010,44,91,1,'2016-09-26 11:40:21',NULL),
	(1011,44,124,1,'2016-09-26 11:40:21',NULL),
	(1012,44,90,1,'2016-09-26 11:40:21',NULL),
	(1013,44,169,1,'2016-09-26 11:40:21',NULL),
	(1014,44,129,1,'2016-09-26 11:40:21',NULL),
	(1015,44,165,1,'2016-09-26 11:40:21',NULL),
	(1016,44,168,1,'2016-09-26 11:40:21',NULL),
	(1017,44,167,1,'2016-09-26 11:40:21',NULL),
	(1018,44,171,1,'2016-09-26 11:40:21',NULL),
	(1019,44,164,1,'2016-09-26 11:40:21',NULL),
	(1020,44,172,1,'2016-09-26 11:40:21',NULL),
	(1021,44,173,1,'2016-09-26 11:40:21',NULL),
	(1022,44,86,1,'2016-09-26 11:40:21',NULL),
	(1023,44,131,1,'2016-09-26 11:40:21',NULL),
	(1024,44,87,1,'2016-09-26 11:40:21',NULL),
	(1025,44,88,1,'2016-09-26 11:40:21',NULL),
	(1026,44,89,1,'2016-09-26 11:40:21',NULL),
	(1058,1,67,1,'2016-10-08 18:47:44',NULL),
	(1059,1,21,1,'2016-10-08 18:47:44',NULL),
	(1060,1,66,1,'2016-10-08 18:47:44',NULL),
	(1061,1,1,1,'2016-10-08 18:47:44',NULL),
	(1062,1,68,1,'2016-10-08 18:47:44',NULL),
	(1063,1,41,1,'2016-10-08 18:47:44',NULL),
	(1064,1,123,1,'2016-10-08 18:47:44',NULL),
	(1065,1,31,1,'2016-10-08 18:47:44',NULL),
	(1066,1,51,1,'2016-10-08 18:47:44',NULL),
	(1067,1,65,1,'2016-10-08 18:47:44',NULL),
	(1068,1,71,1,'2016-10-08 18:47:44',NULL),
	(1069,1,16,1,'2016-10-08 18:47:44',NULL),
	(1070,1,11,1,'2016-10-08 18:47:44',NULL),
	(1071,1,141,1,'2016-10-08 18:47:44',NULL),
	(1072,12,159,1,'2016-10-13 13:25:42',NULL),
	(1073,12,158,1,'2016-10-13 13:25:42',NULL),
	(1074,12,176,1,'2016-10-13 13:25:42',NULL),
	(1075,12,157,1,'2016-10-13 13:25:42',NULL),
	(1076,12,156,1,'2016-10-13 13:25:42',NULL),
	(1077,12,155,1,'2016-10-13 13:25:42',NULL),
	(1078,12,154,1,'2016-10-13 13:25:42',NULL),
	(1079,12,170,1,'2016-10-13 13:25:42',NULL),
	(1080,12,152,1,'2016-10-13 13:25:42',NULL),
	(1081,12,153,1,'2016-10-13 13:25:42',NULL),
	(1082,12,150,1,'2016-10-13 13:25:42',NULL),
	(1083,12,151,1,'2016-10-13 13:25:42',NULL),
	(1084,12,174,1,'2016-10-13 13:25:42',NULL),
	(1085,12,175,1,'2016-10-13 13:25:42',NULL),
	(1086,12,105,1,'2016-10-13 13:25:42',NULL),
	(1087,12,104,1,'2016-10-13 13:25:42',NULL),
	(1088,12,103,1,'2016-10-13 13:25:42',NULL),
	(1089,12,101,1,'2016-10-13 13:25:42',NULL),
	(1090,12,84,1,'2016-10-13 13:25:42',NULL),
	(1091,12,100,1,'2016-10-13 13:25:42',NULL),
	(1092,12,85,1,'2016-10-13 13:25:42',NULL),
	(1093,12,145,1,'2016-10-13 13:25:42',NULL),
	(1094,12,146,1,'2016-10-13 13:25:42',NULL),
	(1095,12,147,1,'2016-10-13 13:25:42',NULL),
	(1096,12,93,1,'2016-10-13 13:25:42',NULL),
	(1097,12,148,1,'2016-10-13 13:25:42',NULL),
	(1098,12,149,1,'2016-10-13 13:25:42',NULL),
	(1099,12,166,1,'2016-10-13 13:25:42',NULL),
	(1100,12,161,1,'2016-10-13 13:25:42',NULL),
	(1101,12,162,1,'2016-10-13 13:25:42',NULL),
	(1102,12,163,1,'2016-10-13 13:25:42',NULL),
	(1103,12,160,1,'2016-10-13 13:25:42',NULL);

/*!40000 ALTER TABLE `sys_role_resource` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `organization_id` bigint(20) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL COMMENT '用户名称',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(100) DEFAULT NULL COMMENT '盐',
  `locked` int(1) DEFAULT '0' COMMENT '1 锁定',
  `phone` varchar(13) DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `insert_date` timestamp NULL DEFAULT NULL COMMENT '插入时间',
  `update_date` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_user_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;

INSERT INTO `sys_user` (`id`, `organization_id`, `username`, `password`, `salt`, `locked`, `phone`, `email`, `avatar`, `insert_date`, `update_date`)
VALUES
	(1,1,'admin','d3c59d25033dbf980d29554025c23a75','8d78869f470951332959580424d4bf4f',0,'19230201033','admin@123.com',NULL,'2016-05-12 18:45:37',NULL),
	(2,4,'stony','3aa21e6b501f6e6cadc50d26188175a8','ad614f07308852096cccd6637ae14b46',0,'18210208025','stony@123.com',NULL,'2016-05-12 18:45:37',NULL);

/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_user_app_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_user_app_role`;

CREATE TABLE `sys_user_app_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `app_id` bigint(20) DEFAULT NULL COMMENT '应用ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `insert_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `update_date` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_app_role_un` (`user_id`,`app_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户APP角色映射表';

LOCK TABLES `sys_user_app_role` WRITE;
/*!40000 ALTER TABLE `sys_user_app_role` DISABLE KEYS */;

INSERT INTO `sys_user_app_role` (`id`, `user_id`, `app_id`, `role_id`, `insert_date`, `update_date`)
VALUES
	(1,1,1,1,'2016-05-12 18:45:37',NULL),
	(16,14,1,1,'2016-05-27 12:45:37',NULL),
	(19,14,19,1,'2016-05-27 14:13:03',NULL),
	(21,23,21,25,'2016-06-01 10:28:35',NULL),
	(22,24,21,12,'2016-06-01 10:31:39',NULL),
	(23,25,21,25,'2016-06-01 10:31:49',NULL),
	(26,17,21,1,'2016-06-02 20:59:22',NULL),
	(27,17,21,25,'2016-06-02 20:59:22',NULL),
	(29,17,1,1,'2016-06-02 20:59:50',NULL),
	(32,23,1,1,'2016-06-02 21:42:25',NULL),
	(34,15,1,1,'2016-06-02 21:43:22',NULL),
	(37,27,26,38,'2016-06-17 15:02:23',NULL),
	(40,29,26,38,'2016-06-20 09:59:55',NULL),
	(41,30,26,39,'2016-06-20 10:00:20',NULL),
	(42,28,21,39,'2016-06-21 10:37:48',NULL),
	(52,31,24,42,'2016-06-22 14:41:10',NULL),
	(53,1,21,43,'2016-06-23 12:06:16',NULL),
	(54,32,25,44,'2016-06-23 17:54:49',NULL),
	(55,24,24,42,'2016-06-24 13:52:33',NULL),
	(56,24,25,44,'2016-06-24 13:53:15',NULL),
	(59,33,24,42,'2016-08-30 18:03:13',NULL);

/*!40000 ALTER TABLE `sys_user_app_role` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

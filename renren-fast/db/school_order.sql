/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50738 (5.7.38-log)
 Source Host           : localhost:3306
 Source Schema         : school_order

 Target Server Type    : MySQL
 Target Server Version : 50738 (5.7.38-log)
 File Encoding         : 65001

 Date: 23/02/2024 16:51:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for business
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商家id',
  `business_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商家名称',
  `products` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所有商品',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `phone_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `score` float NULL DEFAULT NULL COMMENT '评分',
  `miaoshu` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of business
-- ----------------------------
INSERT INTO `business` VALUES (1, '新余炒菜', NULL, 1, 'images/business/1.jpg', '2024-01-09 10:44:13', '13698473240', '新余学院二食堂', 4.5, '新余炒菜');
INSERT INTO `business` VALUES (2, '老北京面条', NULL, 1, 'images/business/1.jpg', '2024-01-09 15:27:37', '13698473240', '新余学院二食堂', 4.5, '面条');
INSERT INTO `business` VALUES (4, '小吃大刀', NULL, 0, 'images/business/4_1.jpg', '2024-01-09 16:30:20', '13698473240', '新余学院一食堂', 4.5, '<p>好吃不贵</p>');
INSERT INTO `business` VALUES (6, '小吃大牛', NULL, 0, 'images/business/6_2.jpg', '2024-01-10 09:40:35', '13698473240', '新余学院一食堂', 4.5, '<p>好吃不贵</p>');
INSERT INTO `business` VALUES (7, '小吃99', NULL, 1, 'images/business/null_9.jpg', '2024-01-19 21:35:48', '13698473240', '新余学院一食堂', 4.2, '<p><br></p>');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '奶茶专区');
INSERT INTO `category` VALUES (2, '甜品/蛋糕');
INSERT INTO `category` VALUES (3, '包子/早点');
INSERT INTO `category` VALUES (4, '午饭套餐');
INSERT INTO `category` VALUES (5, '肉类炒菜');
INSERT INTO `category` VALUES (6, '素菜专区');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买家地址',
  `phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买家联系方式',
  `status` int(11) NULL DEFAULT 0 COMMENT '状态，0：普通用户，1：会员',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐',
  `creattime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE COMMENT '用户名唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (2, 'lm', '60c31372cd39a268077b6b3d9b678c63', '新余学院7栋', '13698473240', 1, '4tIY6VcX', '2024-01-10 12:58:17');
INSERT INTO `customer` VALUES (4, 'lss', '422de8eefda634172e1c86c4da077ec6', '新余学院', '13698473240', 1, '9tIY5VcX', '2024-01-03 12:58:34');
INSERT INTO `customer` VALUES (5, 'kd', '91ee52f3db55d854910253d099e16527', '新余学院', '13698473240', 1, '8tIY5VcZ', '2023-12-06 12:58:50');
INSERT INTO `customer` VALUES (6, 'lmm', '541eeb2b1102a87e2475092ed14a7374', '新余学院', '13698473240', 0, 'eN5wIq8k', '2024-01-14 13:28:08');
INSERT INTO `customer` VALUES (7, 'lmmm', '01c7934169a1ec57bdc31f5ff5ded2c3', '新余学院', '13698473248', 0, 'UgCwSsfx', '2024-01-14 13:43:35');

-- ----------------------------
-- Table structure for delivery_man
-- ----------------------------
DROP TABLE IF EXISTS `delivery_man`;
CREATE TABLE `delivery_man`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account_number`(`account_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of delivery_man
-- ----------------------------
INSERT INTO `delivery_man` VALUES (6, 'qishou', 'f08423e1153f2c0f9c30e7d0a32e9604', 'bIbtQmRy', '2024-01-19 22:26:33', '小张', '13698473299', 1);
INSERT INTO `delivery_man` VALUES (7, 'qishouz', 'c462122ff6ba06772eec81f8c70b98ad', 'uZsltM8f', '2024-01-19 22:27:49', '小张', '13698473299', 1);

-- ----------------------------
-- Table structure for order_
-- ----------------------------
DROP TABLE IF EXISTS `order_`;
CREATE TABLE `order_`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买家地址',
  `status` int(255) NULL DEFAULT 0 COMMENT '支付状态，0：未支付，1：已支付',
  `cstid` int(11) NULL DEFAULT NULL COMMENT '买家id',
  `createtime` timestamp NULL DEFAULT NULL COMMENT '下单时间',
  `businesses` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卖家',
  `paytype` int(11) NULL DEFAULT NULL COMMENT '支付方式，0：支付宝支付',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `products` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  `paypal_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付宝支付凭证',
  `deliveryman_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配送员信息',
  `delivery_status` int(11) NULL DEFAULT NULL COMMENT '配送状态 0 待接单 1已接单 2已取到 3配送中 4已送达，5已完结\r\n',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '完结时间',
  `refund_status` int(11) NULL DEFAULT NULL COMMENT '退款状态 0无 1申请退款  2拒绝退款 3同意退款',
  `refund_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '退款理由',
  `refusal_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拒绝理由',
  `delivery_man_id` int(11) NULL DEFAULT NULL COMMENT '配送人id',
  `review_status` int(11) NULL DEFAULT NULL COMMENT '评价状态0未评价1已评价',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE,
  INDEX `cstid`(`cstid`) USING BTREE,
  CONSTRAINT `order__ibfk_1` FOREIGN KEY (`cstid`) REFERENCES `customer` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of order_
-- ----------------------------
INSERT INTO `order_` VALUES (3, '20240119111724873547', '姓名：admin,配送地址：新余学院,手机号：13698473240', 1, 2, '2024-01-19 11:17:25', '2', 0, 17.50, '[2]', '2024-01-19 11:17:46', '2024011922001405740502118857', NULL, 0, NULL, 3, '不要了好吧', NULL, NULL, 0);
INSERT INTO `order_` VALUES (4, '202401191127410409044', '姓名：lm,配送地址：新余学院,手机号：13698473240', 1, 2, '2024-01-19 11:27:41', '1', 0, 38.30, '[3, 4, 5]', '2024-01-19 15:14:08', '2024011922001405740502126454', NULL, 0, NULL, 3, '不要了', NULL, NULL, 0);
INSERT INTO `order_` VALUES (5, '202401191513131371528', '姓名：lm,配送地址：新余学院,手机号：13698473240', 1, 2, '2024-01-19 15:13:13', '1', 0, 15.90, '[5]', '2024-01-19 15:13:33', '2024011922001405740502129080', NULL, 0, NULL, 3, '不要了', NULL, NULL, 0);
INSERT INTO `order_` VALUES (6, '202401191714355144549', '姓名：lm,配送地址：新余学院,手机号：13698473240', 1, 2, '2024-01-19 17:14:36', '1', 0, 13.50, '[3]', '2024-01-19 17:15:10', '2024011922001405740502131987', NULL, 0, NULL, 3, '不要了', NULL, NULL, 0);
INSERT INTO `order_` VALUES (7, '202401191716594086123', '姓名：lm,配送地址：新余学院,手机号：13698473240', 1, 2, '2024-01-19 17:16:59', '1', 0, 15.90, '[5]', '2024-01-19 17:17:17', '2024011922001405740502133780', NULL, 5, '2024-01-21 11:05:08', 2, '不要克', '                    害', NULL, 0);
INSERT INTO `order_` VALUES (8, '202401201311085984072', '姓名：admin,配送地址：新余学院,手机号：13698473240', 1, 2, '2024-01-20 13:11:09', '1', 0, 23.90, '[3, 4]', '2024-01-20 13:11:39', '2024012022001405740502135875', '姓名:小张, 手机：13698473299', 5, '2024-01-21 11:04:46', 0, NULL, NULL, 6, 1);
INSERT INTO `order_` VALUES (9, '202401201601309037304', '姓名：admin,配送地址：新余学院,手机号：13698473240', 1, 2, '2024-01-20 16:01:31', '1', 0, 107.90, '[11]', '2024-01-20 16:01:50', '2024012022001405740502139105', '姓名:小张, 手机：13698473299', 5, '2024-01-21 11:03:01', 0, NULL, NULL, 6, 1);
INSERT INTO `order_` VALUES (10, '202401211644045945986', '姓名：admin,配送地址：新余学院,手机号：13698473240', 2, 2, '2024-01-21 16:44:05', '1', 0, 15.90, '[5]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `order_` VALUES (11, '202401220906310711413', '姓名：admin,配送地址：新余学院,手机号：13698473240', 2, 2, '2024-01-22 09:06:31', '1', 0, 71.90, '[8]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `order_` VALUES (12, '202401220908498162344', '姓名：admin,配送地址：新余学院,手机号：13698473240', 1, 2, '2024-01-22 09:08:50', '2', 0, 9.50, '[2]', '2024-01-22 09:09:21', '2024012222001405740502151505', NULL, 5, '2024-01-22 09:12:16', 2, '不想要了', '                    ', NULL, 1);
INSERT INTO `order_` VALUES (13, '202401220917171743362', '姓名：admin,配送地址：新余学院,手机号：13698473240', 1, 2, '2024-01-22 09:17:17', '1', 0, 11.90, '[4]', '2024-01-22 09:17:38', '2024012222001405740502151506', NULL, 5, '2024-01-22 09:22:09', 0, NULL, NULL, NULL, 1);
INSERT INTO `order_` VALUES (14, '202401220930094278538', '姓名：admin,配送地址：新余学院,手机号：13698473240', 1, 2, '2024-01-22 09:30:09', '1', 0, 55.90, '[7]', '2024-01-22 09:30:32', '2024012222001405740502143356', NULL, 0, NULL, 3, '退款', NULL, NULL, 0);
INSERT INTO `order_` VALUES (15, '202401220942017987162', '姓名：admin,配送地址：新余学院,手机号：13698473240', 1, 2, '2024-01-22 09:42:02', '1', 0, 15.90, '[6]', '2024-01-22 09:42:27', '2024012222001405740502153129', '姓名:小张, 手机：13698473299', 5, '2024-01-22 09:45:20', 0, NULL, NULL, 6, 1);
INSERT INTO `order_` VALUES (16, '202401221454041824785', '姓名：admin,配送地址：新余学院,手机号：13698473240', 1, 2, '2024-01-22 14:54:04', '1', 0, 79.90, '[10]', '2024-01-22 14:54:23', '2024012222001405740502150170', '姓名:小张, 手机：13698473299', 5, '2024-01-22 15:56:15', 2, '退款', '                    已经收货了', 6, 1);
INSERT INTO `order_` VALUES (17, '202401221603126276517', '姓名：admin,配送地址：新余学院,手机号：13698473240', 2, 2, '2024-01-22 16:03:13', '2', 0, 9.50, '[2]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `order_` VALUES (18, '202401221611043658919', '姓名：admin,配送地址：新余学院,手机号：13698473240', 1, 2, '2024-01-22 16:11:04', '1', 0, 15.90, '[5]', '2024-01-22 16:11:41', '2024012222001405740502150172', '姓名:小张, 手机：13698473299', 5, '2024-01-22 16:29:04', 2, '退款', '                    已送达', 6, 0);
INSERT INTO `order_` VALUES (19, '202401221648552384466', '姓名：admin,配送地址：新余学院,手机号：13698473240', 1, 2, '2024-01-22 16:48:55', '1', 0, 15.90, '[6]', '2024-01-22 16:49:15', '2024012222001405740502153132', '姓名:小张, 手机：13698473299', 6, NULL, 3, '不要了', '                    先申请取消，再联系配送员取消订单', 6, 0);
INSERT INTO `order_` VALUES (20, '20240122171512236443', '姓名：admin,配送地址：新余学院,手机号：13698473240', 1, 2, '2024-01-22 17:15:12', '1', 0, 107.90, '[11]', '2024-01-22 17:15:31', '2024012222001405740502151510', NULL, 0, NULL, 3, '', NULL, NULL, 0);
INSERT INTO `order_` VALUES (21, '202401230927393407814', '姓名：admin,配送地址：新余学院,手机号：13698473240', 1, 2, '2024-01-23 09:27:39', '1', 0, 5.50, '[14]', '2024-01-23 09:27:59', '2024012322001405740502155543', '姓名:小张, 手机：13698473299', 5, '2024-01-23 09:29:26', 2, '不要了', '                    已确认收货', 6, 1);
INSERT INTO `order_` VALUES (22, '202401230934473338850', '姓名：admin,配送地址：新余学院,手机号：13698473240', 1, 2, '2024-01-23 09:34:47', '1', 0, 37.50, '[9]', '2024-01-23 09:35:13', '2024012322001405740502161113', '姓名:小张, 手机：13698473299', 5, '2024-01-23 09:36:35', 2, '', '                    ', 6, 0);
INSERT INTO `order_` VALUES (23, '202401230937335301698', '姓名：admin,配送地址：新余学院,手机号：13698473240', 1, 2, '2024-01-23 09:37:34', '1', 0, 15.90, '[6]', '2024-01-23 09:37:54', '2024012322001405740502156947', NULL, 0, NULL, 3, '', NULL, NULL, 0);
INSERT INTO `order_` VALUES (24, '202401231010139747001', '姓名：admin,配送地址：新余学院,手机号：13698473240', 1, 2, '2024-01-23 10:10:14', '1', 0, 11.90, '[4]', '2024-01-23 10:10:31', '2024012322001405740502162166', '姓名:小张, 手机：13698473299', 3, NULL, 1, '', NULL, 6, 0);
INSERT INTO `order_` VALUES (25, '202401231039296992883', '姓名：admin,配送地址：新余学院,手机号：13698473240', 1, 2, '2024-01-23 10:39:30', '1', 0, 15.90, '[6]', '2024-01-23 10:41:10', '2024012322001405740502163434', '姓名:小张, 手机：13698473299', 6, NULL, 3, '', NULL, 6, 0);
INSERT INTO `order_` VALUES (26, '202401231044464754536', '姓名：admin,配送地址：新余学院,手机号：13698473240', 2, 2, '2024-01-23 10:44:46', '1', 0, 15.90, '[5]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `order_` VALUES (27, '202401231045133758332', '姓名：lm,配送地址：新余学院,手机号：13698473240', 1, 2, '2024-01-23 10:45:13', '1', 0, 15.90, '[6]', '2024-01-23 10:45:34', '2024012322001405740502164701', '姓名:小张, 手机：13698473299', 5, '2024-01-23 11:20:31', 2, '', '                    已取到', 6, 0);
INSERT INTO `order_` VALUES (28, '202401231052053997596', '姓名：admin,配送地址：新余学院,手机号：13698473240', 1, 2, '2024-01-23 10:52:05', '1', 0, 11.90, '[4]', '2024-01-23 10:52:23', '2024012322001405740502162167', '姓名:小张, 手机：13698473299', 5, '2024-01-23 11:14:54', 2, '', '                    已收货', 6, 0);
INSERT INTO `order_` VALUES (29, '202402181901343924199', '姓名：admin,配送地址：新余学院,手机号：13698473240', 1, 2, '2024-02-18 19:01:34', '1', 0, 55.90, '[7]', '2024-02-18 19:11:16', '2024021822001405740502335205', '姓名:小张, 手机：13698473299', 5, '2024-02-18 19:20:54', 2, '', '                    货已取到', 6, 0);

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NULL DEFAULT NULL COMMENT '商品id',
  `oid` int(11) NULL DEFAULT NULL COMMENT '订单id',
  `cstid` int(11) NULL DEFAULT NULL COMMENT '买家id',
  `number` int(11) NULL DEFAULT NULL COMMENT '数量',
  `bid` int(11) NULL DEFAULT NULL COMMENT '卖家id',
  `check_status` int(11) NULL DEFAULT NULL COMMENT '购物车选中状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE,
  INDEX `oid`(`oid`) USING BTREE,
  INDEX `cstid`(`cstid`) USING BTREE,
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `product` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`oid`) REFERENCES `order_` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `orderitem_ibfk_3` FOREIGN KEY (`cstid`) REFERENCES `customer` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES (4, 3, 4, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (5, 4, 4, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (6, 5, 4, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (7, 2, 3, 2, 2, 2, 1);
INSERT INTO `orderitem` VALUES (8, 5, 5, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (9, 3, 6, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (10, 5, 7, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (12, 3, 8, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (13, 4, 8, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (14, 2, 12, 2, 1, 2, 1);
INSERT INTO `orderitem` VALUES (17, 5, 10, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (19, 7, 14, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (25, 8, 11, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (28, 11, 9, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (30, 4, 13, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (31, 6, 15, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (32, 2, 17, 2, 1, 2, 1);
INSERT INTO `orderitem` VALUES (33, 10, 16, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (34, 5, 18, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (35, 6, 19, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (36, 11, 20, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (37, 14, 21, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (38, 9, 22, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (39, 6, 23, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (40, 4, 24, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (41, 6, 25, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (42, 5, 26, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (43, 6, 27, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (44, 4, 28, 2, 1, 1, 1);
INSERT INTO `orderitem` VALUES (45, 5, NULL, 2, 1, 1, 0);
INSERT INTO `orderitem` VALUES (46, 7, 29, 2, 1, 1, 1);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `desc_` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '删除分类', '删除分类', '/category/delCategory');
INSERT INTO `permission` VALUES (2, '分类列表', '查看分类列表', '/category/list');
INSERT INTO `permission` VALUES (15, '修改分类页面', '修改分类页面', '/category/editCategory');
INSERT INTO `permission` VALUES (17, '添加商品分类', '添加商品分类', '/category/addCategory');
INSERT INTO `permission` VALUES (18, '查看管理员列表', '查看管理员列表', '/config/listUser');
INSERT INTO `permission` VALUES (20, '查看角色列表', '可增删改查', '/config/listRole');
INSERT INTO `permission` VALUES (21, '查看权限列表', '无', '/config/listPermission');
INSERT INTO `permission` VALUES (23, '修改管理员信息界面', '修改界面', '/config/editUser');
INSERT INTO `permission` VALUES (24, '更新管理员信息', '无', '/config/updateUser');
INSERT INTO `permission` VALUES (25, '管理员删除', '删除', '/config/deleteUser');
INSERT INTO `permission` VALUES (26, '添加管理员界面', '无', '/config/adminAdd');
INSERT INTO `permission` VALUES (27, '添加管理员', '无', '/config/addUser');
INSERT INTO `permission` VALUES (28, '商品列表', '查看商品列表', '/product/list');
INSERT INTO `permission` VALUES (29, '添加商品', '添加上线商品', '/product/addProduct');
INSERT INTO `permission` VALUES (30, '删除商品', '暂无', '/product/deleteProduct');
INSERT INTO `permission` VALUES (31, '修改商品界面', '暂无', '/product/editProduct');
INSERT INTO `permission` VALUES (32, '更新商品', '暂无', '/product/updateProduct');
INSERT INTO `permission` VALUES (33, '添加商品界面', '暂无', '/product/productAddUI');
INSERT INTO `permission` VALUES (34, '商品上线', '暂无', '/product/enableStatus');
INSERT INTO `permission` VALUES (35, '商品下线', '暂无', '/product/stopStatus');
INSERT INTO `permission` VALUES (36, '订单发货', '暂无', '/order/orderDelivery');
INSERT INTO `permission` VALUES (37, '查看订单项', '暂无', '/order/seeOrderItem');
INSERT INTO `permission` VALUES (38, '评论列表', '暂无', '/review/list');
INSERT INTO `permission` VALUES (39, '删除评论', '暂无', '/review/del');
INSERT INTO `permission` VALUES (40, '查看订单列表', '暂无', '/order/list');
INSERT INTO `permission` VALUES (41, '添加权限UI', '暂无', '/config/adminPerAddUI');
INSERT INTO `permission` VALUES (42, '添加权限', '暂无', '/config/addPermission');
INSERT INTO `permission` VALUES (43, '删除权限', '暂无', '/config/deletePermission');
INSERT INTO `permission` VALUES (44, '修改权限ui', '暂无', '/config/editPermission');
INSERT INTO `permission` VALUES (45, '更新权限', '暂无', '/config/updatePermission');
INSERT INTO `permission` VALUES (46, '查看用户列表', '暂无', '/customer/list');
INSERT INTO `permission` VALUES (47, '删除角色', '暂无', '/config/deleteRole');
INSERT INTO `permission` VALUES (48, '添加管理员UI', '暂无', '/config/adminAdd');
INSERT INTO `permission` VALUES (49, '添加管理员', '暂未', '/config/addUser');
INSERT INTO `permission` VALUES (50, '修改角色UI', '暂无', '/config/editRole');
INSERT INTO `permission` VALUES (51, '修改角色', '暂无', '/config/updateRole');
INSERT INTO `permission` VALUES (52, '添加角色UI', '暂无', '/config/addRoleUI');
INSERT INTO `permission` VALUES (53, '添加角色', '暂无', '/config/addRole');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `price` float(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `zan` int(100) NULL DEFAULT NULL COMMENT '赞',
  `number` int(100) NULL DEFAULT NULL COMMENT '销量',
  `status` int(10) NULL DEFAULT 1 COMMENT '状态，1：上线，0：下线',
  `imageurl` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片url',
  `miaoshu` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '商品描述',
  `cid` int(11) NULL DEFAULT NULL COMMENT '分类id',
  `bid` bigint(11) NULL DEFAULT NULL COMMENT '商家id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `product_ibfk_1`(`cid`) USING BTREE,
  INDEX `product_ibfk_2`(`bid`) USING BTREE,
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `category` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '红豆布丁烧奶', 9.00, 54, 94, 1, 'images/product/1_3.jpg', '软糯红豆嫩布丁，鲜香甘甜，浓郁的奶香与红豆完美相融，满足感充分体现在你的唇齿和心间', 1, 2);
INSERT INTO `product` VALUES (2, '焦糖珍奶', 10.00, 98, 103, 1, 'images/product/2_1.jpg', '奶茶中的经典款，用不过时，焦糖的甜珍奶的鲜，永远有人喜欢。', 2, 2);
INSERT INTO `product` VALUES (3, '葡萄柚益菌多', 15.00, 45, 63, 1, 'images/product/3_5.jpg', '新鲜葡萄柚与优菌多相遇，酸甜的滋味在唇齿间流转，只需闭上眼用心品味这让人欲罢不能的清爽感。', 3, 1);
INSERT INTO `product` VALUES (4, '芒果布丁奶绿', 13.00, 41, 43, 1, 'images/product/4.jpg', '清香可口的海南大金煌与柔滑香的甜奶布丁，层层叠加，混搭滋味，随心选择。', 1, 1);
INSERT INTO `product` VALUES (5, '金桔柠檬茶', 18.00, 72, 168, 1, 'images/product/5.jpg', '打破传统奶盖与茶饮分离的做法，将咸奶盖与芒果冰均匀融合。芒果冰的爽滑细腻与咸奶盖的香浓完美融合，口感清甜饱满，独家特制芒砂糖将芒果本身的香甜气息发挥到极致。', 1, 1);
INSERT INTO `product` VALUES (6, '蜂蜜柚子茶', 18.00, 74, 159, 1, 'images/product/6.jpg', '不仅是味道清香可口，更是一款有美白祛斑、嫩肤养颜功效的食品。蜂蜜中含有的L－半胱氨酸具有排毒作用，具有一定的祛斑效果。', 1, 1);
INSERT INTO `product` VALUES (7, '巧克力水果蛋糕', 68.00, 75, 101, 1, 'images/product/7.jpg', '本店蛋糕现做现卖，由于蛋糕的特殊性，请顾客提前义雄安史预定。联系电话：12312345678', 2, 1);
INSERT INTO `product` VALUES (8, '心心相印', 88.00, 45, 99, 1, 'images/product/8.jpg', '为了保证蛋糕的新鲜！所有商品均为顾客下单现做！为了不耽误亲们的宝贵时间，请尽量提前一小时下单！详情致电：12312345678', 2, 1);
INSERT INTO `product` VALUES (9, '美味奶油蛋糕', 45.00, 45, 56, 1, 'images/product/9.jpg', '本店蛋糕现做现卖，请顾客朋友提前预定电话：12312345678', 2, 1);
INSERT INTO `product` VALUES (10, '至尊奶油蛋糕', 98.00, 235, 420, 1, 'images/product/10_8.jpg', '本店蛋糕现做现卖，由于蛋糕的特殊性，请顾客提前2小时预定。联系电话：12312345678', 1, 1);
INSERT INTO `product` VALUES (11, '丰盛果园', 133.00, 356, 2762, 1, 'images/product/11.jpg', '（15人餐）本店蛋糕现做现卖，请顾客提亲1小时预定，联系电话：12312345678', 2, 1);
INSERT INTO `product` VALUES (12, '太阳花', 166.00, 124, 369, 1, 'images/product/12.jpg', '（20人餐）本店蛋糕现做现卖，请顾客提前一小时预定。联系电话：12312345678', 2, 1);
INSERT INTO `product` VALUES (13, '初见', 105.00, 124, 234, 1, 'images/product/13.jpg', '（12餐）本店蛋糕现做现卖，请顾客提前一小时预定，联系电话：12312345678', 2, 1);
INSERT INTO `product` VALUES (14, '香辣鸡腿堡', 5.00, 124, 254, 1, 'images/product/14.jpg', '开业特惠，豪吃不贵！限时特价商品', 3, 1);
INSERT INTO `product` VALUES (15, '墨西哥肌肉卷', 19.00, 453, 454, 1, 'images/product/15.jpg', '里面用的是番茄酱哈', 3, 1);
INSERT INTO `product` VALUES (16, '爆浆鸡排', 12.00, 45, 4654, 1, 'images/product/16.jpg', '香脆可口', 3, 1);
INSERT INTO `product` VALUES (17, '椰奶芝士蛋糕', 11.00, 54, 132, 1, 'images/product/17.jpg', '该商品暂无描述', 3, 1);
INSERT INTO `product` VALUES (18, '南瓜脆皮芝士包', 8.00, 15, 232, 1, 'images/product/18.jpg', '暂无商品信息', 3, 1);
INSERT INTO `product` VALUES (19, '核桃布里奥斯', 23.00, 33, 323, 1, 'images/product/19.jpg', '定制设计', 3, 1);
INSERT INTO `product` VALUES (20, '卤肉配牛丸', 45.00, 45, 555, 1, 'images/product/20.jpg', '把简单的事情做到不简单，这就是存在的意义。优质猪后腿肉，在浓郁的老卤的怀抱中千回百转。30余味的精选香料与肉块的完美融合。', 4, 1);
INSERT INTO `product` VALUES (25, '雪花鸡排饭', 42.00, 444, 444, 1, 'images/product/25.jpg', '鸡腿+脆骨肠+配菜+饮料+米饭', 4, 1);
INSERT INTO `product` VALUES (26, '劲爆麻辣牛肉饭', 35.00, 23, 43, 1, 'images/product/26.jpg', '牛排+脆骨肠+配菜+饮料+米饭', 4, 1);
INSERT INTO `product` VALUES (27, '蜜汁牛排饭', 25.00, 34, 53, 1, 'images/product/27.jpg', '牛排+脆骨肠+配菜+饮料+米饭', 4, 1);
INSERT INTO `product` VALUES (28, '小熊卤肉饭', 23.00, 34, 23, 1, 'images/product/28.jpg', '卤香十足，香甜可口，回味无穷，超级好吃', 4, 1);
INSERT INTO `product` VALUES (30, '清蒸大闸蟹', 98.00, 456, 998, 1, 'images/product/30.jpg', '3.5重的大公蟹，肉质鲜美，淘宝都卖一百四五一只，本店只卖98', 5, 1);
INSERT INTO `product` VALUES (31, '单煎牛排', 45.00, 98, 564, 1, 'images/product/31.jpg', '暂无介绍', 6, 1);
INSERT INTO `product` VALUES (33, 'foresee', 99.00, 89, 33, 1, 'images/product/33_10.jpg', '<p>奶茶</p>', 1, 1);
INSERT INTO `product` VALUES (34, 'foresee22', 99.00, 89, 33, 1, 'images/product/34_11.jpg', '<p>时代发生的发</p>', 1, 1);

-- ----------------------------
-- Table structure for receiving_orders
-- ----------------------------
DROP TABLE IF EXISTS `receiving_orders`;
CREATE TABLE `receiving_orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `delivery_status` int(11) NULL DEFAULT NULL COMMENT '0未完结 1已完结',
  `receiving_time` timestamp NULL DEFAULT NULL COMMENT '接单时间',
  `delivered_time` timestamp NULL DEFAULT NULL COMMENT '送达时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '完结时间',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配送地址',
  `business_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商家名称',
  `product_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品信息，以List形式保存',
  `business_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商家地址',
  `delivery_man_id` int(11) NULL DEFAULT NULL COMMENT '配送员id',
  `order_id` int(11) NULL DEFAULT NULL COMMENT '订单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of receiving_orders
-- ----------------------------
INSERT INTO `receiving_orders` VALUES (1, '202401201601309037304', 1, '2024-01-20 18:22:13', '2024-01-20 20:45:04', '2024-01-21 11:03:01', '姓名：admin,配送地址：新余学院,手机号：13698473240', '新余炒菜', '[丰盛果园(数量：x1, 价格106.4)]', '手机：13698473240， 地址：新余学院二食堂', 6, 9);
INSERT INTO `receiving_orders` VALUES (2, '202401201311085984072', 1, '2024-01-20 18:23:06', '2024-01-20 20:45:05', '2024-01-21 11:04:46', '姓名：admin,配送地址：新余学院,手机号：13698473240', '新余炒菜', '[葡萄柚益菌多(数量：x1, 价格12.0), 芒果布丁奶绿(数量：x1, 价格10.4)]', '手机：13698473240， 地址：新余学院二食堂', 6, 8);
INSERT INTO `receiving_orders` VALUES (3, '202401220942017987162', 1, '2024-01-22 09:42:50', '2024-01-22 09:44:51', '2024-01-22 09:45:20', '姓名：admin,配送地址：新余学院,手机号：13698473240', '新余炒菜', '[蜂蜜柚子茶(数量：x1, 价格14.4)]', '手机：13698473240, 地址:新余学院二食堂', 6, 15);
INSERT INTO `receiving_orders` VALUES (4, '202401221454041824785', 1, '2024-01-22 15:26:18', NULL, '2024-01-22 15:56:15', '姓名：admin,配送地址：新余学院,手机号：13698473240', '新余炒菜', '[至尊奶油蛋糕(数量：x1, 价格78.4)]', '手机：13698473240, 地址:新余学院二食堂', 6, 16);
INSERT INTO `receiving_orders` VALUES (5, '202401221611043658919', 1, '2024-01-22 16:12:05', '2024-01-22 16:16:59', '2024-01-22 16:29:04', '姓名：admin,配送地址：新余学院,手机号：13698473240', '新余炒菜', '[金桔柠檬茶(数量：x1, 价格14.4)]', '手机：13698473240, 地址:新余学院二食堂', 6, 18);
INSERT INTO `receiving_orders` VALUES (6, '202401221648552384466', 0, '2024-01-22 16:49:44', NULL, NULL, '姓名：admin,配送地址：新余学院,手机号：13698473240', '新余炒菜', '[蜂蜜柚子茶(数量：x1, 价格14.4)]', '手机：13698473240, 地址:新余学院二食堂', 6, 19);
INSERT INTO `receiving_orders` VALUES (7, '202401230927393407814', 1, '2024-01-23 09:28:19', '2024-01-23 09:28:37', '2024-01-23 09:29:26', '姓名：admin,配送地址：新余学院,手机号：13698473240', '新余炒菜', '[香辣鸡腿堡(数量：x1, 价格4.0)]', '手机：13698473240, 地址:新余学院二食堂', 6, 21);
INSERT INTO `receiving_orders` VALUES (8, '202401230934473338850', 1, '2024-01-23 09:35:29', '2024-01-23 09:35:53', '2024-01-23 09:36:35', '姓名：admin,配送地址：新余学院,手机号：13698473240', '新余炒菜', '[美味奶油蛋糕(数量：x1, 价格36.0)]', '手机：13698473240, 地址:新余学院二食堂', 6, 22);
INSERT INTO `receiving_orders` VALUES (9, '202401231010139747001', 0, '2024-01-23 10:11:34', NULL, NULL, '姓名：admin,配送地址：新余学院,手机号：13698473240', '新余炒菜', '[芒果布丁奶绿(数量：x1, 价格10.4)]', '手机：13698473240, 地址:新余学院二食堂', 6, 24);
INSERT INTO `receiving_orders` VALUES (10, '202401231039296992883', 0, '2024-01-23 10:41:29', NULL, NULL, '姓名：admin,配送地址：新余学院,手机号：13698473240', '新余炒菜', '[蜂蜜柚子茶(数量：x1, 价格14.4)]', '手机：13698473240, 地址:新余学院二食堂', 6, 25);
INSERT INTO `receiving_orders` VALUES (11, '202401231045133758332', 1, '2024-01-23 10:45:50', '2024-01-23 11:20:16', '2024-01-23 11:20:31', '姓名：lm,配送地址：新余学院,手机号：13698473240', '新余炒菜', '[蜂蜜柚子茶(数量：x1, 价格14.4)]', '手机：13698473240, 地址:新余学院二食堂', 6, 27);
INSERT INTO `receiving_orders` VALUES (12, '202401231045133758332', 0, '2024-01-23 10:48:00', NULL, NULL, '姓名：lm,配送地址：新余学院,手机号：13698473240', '新余炒菜', '[蜂蜜柚子茶(数量：x1, 价格14.4)]', '手机：13698473240, 地址:新余学院二食堂', 6, 27);
INSERT INTO `receiving_orders` VALUES (13, '202401231045133758332', 0, '2024-01-23 10:49:14', NULL, NULL, '姓名：lm,配送地址：新余学院,手机号：13698473240', '新余炒菜', '[蜂蜜柚子茶(数量：x1, 价格14.4)]', '手机：13698473240, 地址:新余学院二食堂', 6, 27);
INSERT INTO `receiving_orders` VALUES (14, '202401231052053997596', 1, '2024-01-23 10:52:39', '2024-01-23 11:14:06', '2024-01-23 11:14:54', '姓名：admin,配送地址：新余学院,手机号：13698473240', '新余炒菜', '[芒果布丁奶绿(数量：x1, 价格10.4)]', '手机：13698473240, 地址:新余学院二食堂', 6, 28);
INSERT INTO `receiving_orders` VALUES (15, '202401231052053997596', 0, '2024-01-23 11:08:49', NULL, NULL, '姓名：admin,配送地址：新余学院,手机号：13698473240', '新余炒菜', '[芒果布丁奶绿(数量：x1, 价格10.4)]', '手机：13698473240, 地址:新余学院二食堂', 6, 28);
INSERT INTO `receiving_orders` VALUES (16, '202401231052053997596', 0, '2024-01-23 11:10:29', NULL, NULL, '姓名：admin,配送地址：新余学院,手机号：13698473240', '新余炒菜', '[芒果布丁奶绿(数量：x1, 价格10.4)]', '手机：13698473240, 地址:新余学院二食堂', 6, 28);
INSERT INTO `receiving_orders` VALUES (17, '202402181901343924199', 1, '2024-02-18 19:12:25', '2024-02-18 19:20:36', '2024-02-18 19:20:54', '姓名：admin,配送地址：新余学院,手机号：13698473240', '新余炒菜', '[巧克力水果蛋糕(数量：x1, 价格54.4)]', '手机：13698473240, 地址:新余学院二食堂', 6, 29);
INSERT INTO `receiving_orders` VALUES (18, '202401231010139747001', 0, '2024-02-18 19:25:47', NULL, NULL, '姓名：admin,配送地址：新余学院,手机号：13698473240', '新余炒菜', '[芒果布丁奶绿(数量：x1, 价格10.4)]', '手机：13698473240, 地址:新余学院二食堂', 6, 24);

-- ----------------------------
-- Table structure for review
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论',
  `cstid` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `bid` int(11) NULL DEFAULT NULL COMMENT '商家id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `order_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `re`(`cstid`) USING BTREE,
  INDEX `re1`(`bid`) USING BTREE,
  CONSTRAINT `re` FOREIGN KEY (`cstid`) REFERENCES `customer` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `re1` FOREIGN KEY (`bid`) REFERENCES `business` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of review
-- ----------------------------
INSERT INTO `review` VALUES (2, '好吃不贵', 2, 1, '2024-01-21 16:30:58', '202401201311085984072');
INSERT INTO `review` VALUES (4, '好吃', 2, 1, '2024-01-22 14:47:36', '202401220917171743362');
INSERT INTO `review` VALUES (5, '好吃', 2, 2, '2024-01-22 14:50:17', '202401220908498162344');
INSERT INTO `review` VALUES (6, '好吃', 2, 1, '2024-01-22 14:52:01', '202401220942017987162');
INSERT INTO `review` VALUES (7, '好吃', 2, 1, '2024-01-22 16:01:08', '202401221454041824785');
INSERT INTO `review` VALUES (8, '好吃', 2, 1, '2024-01-23 09:30:22', '202401230927393407814');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `desc_` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin', '超级管理员');
INSERT INTO `role` VALUES (2, 'productManager', '商品管理员');
INSERT INTO `role` VALUES (3, '游客', '只能查看，不能增删改');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rid` bigint(20) NULL DEFAULT NULL,
  `pid` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 474 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (134, 3, 2);
INSERT INTO `role_permission` VALUES (135, 3, 18);
INSERT INTO `role_permission` VALUES (136, 3, 20);
INSERT INTO `role_permission` VALUES (137, 3, 21);
INSERT INTO `role_permission` VALUES (138, 3, 28);
INSERT INTO `role_permission` VALUES (139, 3, 37);
INSERT INTO `role_permission` VALUES (140, 3, 38);
INSERT INTO `role_permission` VALUES (141, 3, 40);
INSERT INTO `role_permission` VALUES (384, 2, 40);
INSERT INTO `role_permission` VALUES (385, 2, 38);
INSERT INTO `role_permission` VALUES (386, 2, 37);
INSERT INTO `role_permission` VALUES (387, 2, 36);
INSERT INTO `role_permission` VALUES (388, 2, 35);
INSERT INTO `role_permission` VALUES (389, 2, 34);
INSERT INTO `role_permission` VALUES (390, 2, 33);
INSERT INTO `role_permission` VALUES (391, 2, 32);
INSERT INTO `role_permission` VALUES (392, 2, 31);
INSERT INTO `role_permission` VALUES (393, 2, 30);
INSERT INTO `role_permission` VALUES (394, 2, 29);
INSERT INTO `role_permission` VALUES (395, 2, 28);
INSERT INTO `role_permission` VALUES (396, 2, 17);
INSERT INTO `role_permission` VALUES (397, 2, 15);
INSERT INTO `role_permission` VALUES (398, 2, 2);
INSERT INTO `role_permission` VALUES (436, 1, 53);
INSERT INTO `role_permission` VALUES (437, 1, 52);
INSERT INTO `role_permission` VALUES (438, 1, 51);
INSERT INTO `role_permission` VALUES (439, 1, 50);
INSERT INTO `role_permission` VALUES (440, 1, 49);
INSERT INTO `role_permission` VALUES (441, 1, 48);
INSERT INTO `role_permission` VALUES (442, 1, 47);
INSERT INTO `role_permission` VALUES (443, 1, 46);
INSERT INTO `role_permission` VALUES (444, 1, 45);
INSERT INTO `role_permission` VALUES (445, 1, 44);
INSERT INTO `role_permission` VALUES (446, 1, 43);
INSERT INTO `role_permission` VALUES (447, 1, 42);
INSERT INTO `role_permission` VALUES (448, 1, 41);
INSERT INTO `role_permission` VALUES (449, 1, 40);
INSERT INTO `role_permission` VALUES (450, 1, 39);
INSERT INTO `role_permission` VALUES (451, 1, 38);
INSERT INTO `role_permission` VALUES (452, 1, 37);
INSERT INTO `role_permission` VALUES (453, 1, 36);
INSERT INTO `role_permission` VALUES (454, 1, 35);
INSERT INTO `role_permission` VALUES (455, 1, 34);
INSERT INTO `role_permission` VALUES (456, 1, 33);
INSERT INTO `role_permission` VALUES (457, 1, 32);
INSERT INTO `role_permission` VALUES (458, 1, 31);
INSERT INTO `role_permission` VALUES (459, 1, 30);
INSERT INTO `role_permission` VALUES (460, 1, 29);
INSERT INTO `role_permission` VALUES (461, 1, 28);
INSERT INTO `role_permission` VALUES (462, 1, 27);
INSERT INTO `role_permission` VALUES (463, 1, 26);
INSERT INTO `role_permission` VALUES (464, 1, 25);
INSERT INTO `role_permission` VALUES (465, 1, 24);
INSERT INTO `role_permission` VALUES (466, 1, 23);
INSERT INTO `role_permission` VALUES (467, 1, 21);
INSERT INTO `role_permission` VALUES (468, 1, 20);
INSERT INTO `role_permission` VALUES (469, 1, 18);
INSERT INTO `role_permission` VALUES (470, 1, 17);
INSERT INTO `role_permission` VALUES (471, 1, 15);
INSERT INTO `role_permission` VALUES (472, 1, 2);
INSERT INTO `role_permission` VALUES (473, 1, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员姓名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员密码',
  `salt` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(10) NULL DEFAULT NULL COMMENT '状态，1：启用，0：停用',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '暂无' COMMENT '商家管理员地址',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lasttime` datetime NULL DEFAULT NULL COMMENT '最后登陆时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '9044ecf0406f5a9dc72bc50773b9b966', '2t6fM0uou2wGc76aiA/fRg==', 1, '暂无', '18397806903', '2024-02-21 19:56:45');
INSERT INTO `user` VALUES (2, 'youke', 'c0f4522d3df4cc911671997d347b50fb', '5Bg0LuSb2KoYlkcHjQNtYQ==', 1, '新余学院', '13698473240', '2024-02-21 19:56:36');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) NULL DEFAULT NULL,
  `rid` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 97 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (88, 1, 1);
INSERT INTO `user_role` VALUES (91, 28, 2);
INSERT INTO `user_role` VALUES (92, 29, 2);
INSERT INTO `user_role` VALUES (93, 30, 2);
INSERT INTO `user_role` VALUES (94, 31, 2);
INSERT INTO `user_role` VALUES (95, 15, 3);
INSERT INTO `user_role` VALUES (96, 2, 3);

-- ----------------------------
-- Table structure for zixun
-- ----------------------------
DROP TABLE IF EXISTS `zixun`;
CREATE TABLE `zixun`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资讯内容',
  `cstid` int(11) NULL DEFAULT NULL COMMENT '客户id',
  `status` int(255) NULL DEFAULT 0 COMMENT '审核状态，1：通过，0：未通过;',
  `fabudate` datetime NULL DEFAULT NULL COMMENT '发布时间;',
  `refuse_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拒绝理由',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of zixun
-- ----------------------------
INSERT INTO `zixun` VALUES (7, '端午节活动', 1, 2, '2023-06-26 14:53:11', NULL);
INSERT INTO `zixun` VALUES (8, '祝各位考试人期末考试过过过', 1, 2, '2023-06-28 11:35:33', NULL);
INSERT INTO `zixun` VALUES (12, '下雪了，好冷', 2, 1, '2024-01-22 09:03:37', '还行吧，不冷        ');
INSERT INTO `zixun` VALUES (13, '下雪了', 2, 1, '2024-01-22 09:50:23', '                    补了');
INSERT INTO `zixun` VALUES (14, '你好', 2, 2, '2024-01-22 09:54:34', NULL);

SET FOREIGN_KEY_CHECKS = 1;

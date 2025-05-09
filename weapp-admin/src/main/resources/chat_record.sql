/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : rental_car

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 01/03/2025 12:19:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chat_record
-- ----------------------------
DROP TABLE IF EXISTS `chat_record`;
CREATE TABLE `chat_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NULL DEFAULT NULL COMMENT '发送用户ID',
  `toUserId` int(11) NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '聊天内容',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '0未读 1已读',
  `deleted` int(11) NULL DEFAULT 0 COMMENT '0未删除 1删除',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `roles` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'common 普通用户   manage管理员',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of chat_record
-- ----------------------------
INSERT INTO `chat_record` VALUES (64, 4, 0, '嗯我也', '1', 0, '2025-02-24 12:46:06', '2025-02-28 10:06:57', 'common');
INSERT INTO `chat_record` VALUES (65, NULL, 4, '3123123', '1', 0, '2025-02-27 23:32:46', '2025-02-28 11:14:38', 'manage');
INSERT INTO `chat_record` VALUES (66, NULL, 4, 'parapAddparapAddparapAddparapAddparapAddparapAddparapAddparapAddparapAddparapAddparapAddparapAddparapAddparapAddparapAddparapAddparapAddparapAddparapAddparapAddparapAddparapAdd', '1', 0, '2025-02-27 23:32:59', '2025-02-28 11:14:38', 'manage');
INSERT INTO `chat_record` VALUES (67, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:00', '2025-02-28 11:14:38', 'manage');
INSERT INTO `chat_record` VALUES (68, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:01', '2025-02-28 11:14:38', 'manage');
INSERT INTO `chat_record` VALUES (69, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:01', '2025-02-28 11:14:38', 'manage');
INSERT INTO `chat_record` VALUES (70, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:01', '2025-02-28 11:14:38', 'manage');
INSERT INTO `chat_record` VALUES (71, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:02', '2025-02-28 11:14:38', 'manage');
INSERT INTO `chat_record` VALUES (72, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:02', '2025-02-28 11:14:38', 'manage');
INSERT INTO `chat_record` VALUES (73, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:02', '2025-02-28 11:14:38', 'manage');
INSERT INTO `chat_record` VALUES (74, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:03', '2025-02-28 11:14:38', 'manage');
INSERT INTO `chat_record` VALUES (75, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:03', '2025-02-28 11:14:38', 'manage');
INSERT INTO `chat_record` VALUES (76, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:03', '2025-02-28 11:14:38', 'manage');
INSERT INTO `chat_record` VALUES (77, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:19', '2025-02-28 11:14:38', 'manage');
INSERT INTO `chat_record` VALUES (78, 5, 0, '嗯我也', '1', 0, '2025-02-24 12:46:06', '2025-02-28 10:09:45', 'common');
INSERT INTO `chat_record` VALUES (79, NULL, 4, '撒大声地', '1', 0, '2025-02-27 23:44:24', '2025-02-28 11:14:38', 'manage');
INSERT INTO `chat_record` VALUES (80, 4, 0, '嗯我也1111', '1', 0, '2025-02-24 12:46:06', '2025-02-28 10:10:21', 'common');
INSERT INTO `chat_record` VALUES (81, NULL, 4, '132132', '1', 0, '2025-02-28 10:10:27', '2025-02-28 11:14:38', 'manage');
INSERT INTO `chat_record` VALUES (82, 4, 0, '嗯我也1111', '1', 0, '2025-02-24 12:46:06', '2025-02-28 10:10:38', 'common');
INSERT INTO `chat_record` VALUES (83, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:01', '2025-02-28 11:21:40', 'manage');
INSERT INTO `chat_record` VALUES (84, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:02', '2025-02-28 11:21:40', 'manage');
INSERT INTO `chat_record` VALUES (85, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:02', '2025-02-28 11:21:40', 'manage');
INSERT INTO `chat_record` VALUES (86, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:02', '2025-02-28 11:21:40', 'manage');
INSERT INTO `chat_record` VALUES (87, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:03', '2025-02-28 11:21:40', 'manage');
INSERT INTO `chat_record` VALUES (88, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:01', '2025-02-28 11:24:19', 'manage');
INSERT INTO `chat_record` VALUES (89, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:02', '2025-02-28 11:24:19', 'manage');
INSERT INTO `chat_record` VALUES (90, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:02', '2025-02-28 11:24:19', 'manage');
INSERT INTO `chat_record` VALUES (91, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:02', '2025-02-28 11:24:19', 'manage');
INSERT INTO `chat_record` VALUES (92, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:03', '2025-02-28 11:24:19', 'manage');
INSERT INTO `chat_record` VALUES (93, 4, 0, '5321.', '1', 0, '2025-02-28 11:24:28', '2025-02-28 13:32:27', 'common');
INSERT INTO `chat_record` VALUES (94, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:03', '2025-02-28 11:35:36', 'manage');
INSERT INTO `chat_record` VALUES (95, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:01', '2025-02-28 11:35:36', 'manage');
INSERT INTO `chat_record` VALUES (96, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:02', '2025-02-28 11:35:36', 'manage');
INSERT INTO `chat_record` VALUES (97, NULL, 4, 'parapAdd', '1', 0, '2025-02-27 23:33:02', '2025-02-28 11:35:36', 'manage');
INSERT INTO `chat_record` VALUES (98, 4, 0, '测试哈', '1', 0, '2025-02-28 13:32:40', '2025-02-28 13:32:41', 'common');
INSERT INTO `chat_record` VALUES (99, NULL, 4, '嗯嗯  好的', '1', 0, '2025-02-28 13:32:45', '2025-02-28 13:32:47', 'manage');
INSERT INTO `chat_record` VALUES (100, 4, 0, '民咕咕', '1', 0, '2025-02-28 16:01:42', '2025-02-28 16:24:06', 'common');
INSERT INTO `chat_record` VALUES (101, 4, 0, '苏州新区', '1', 0, '2025-02-28 16:07:32', '2025-02-28 16:24:06', 'common');
INSERT INTO `chat_record` VALUES (102, 4, 0, '嗯呐', '0', 0, '2025-03-01 11:39:52', '2025-03-01 11:39:52', 'common');
INSERT INTO `chat_record` VALUES (103, 4, 0, '兔兔', '0', 0, '2025-03-01 11:42:45', '2025-03-01 11:42:45', 'common');

-- ----------------------------
-- Table structure for evaluate_feedback
-- ----------------------------
DROP TABLE IF EXISTS `evaluate_feedback`;
CREATE TABLE `evaluate_feedback`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '评价内容',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '0 未处理  1已处理',
  `dealResult` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '处理结果描述',
  `deleted` int(11) NULL DEFAULT 0 COMMENT '0未删除 1已删除',
  `createTime` datetime NULL DEFAULT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of evaluate_feedback
-- ----------------------------
INSERT INTO `evaluate_feedback` VALUES (5, 4, '阿斯达蛇口街道哈卡十九大哈萨抗打击', '1', '好吧 处理了', 0, '2025-02-28 00:13:16', '2025-02-28 00:20:34');
INSERT INTO `evaluate_feedback` VALUES (6, 4, '阿斯达肯德基啊灰色空间等哈阿斯达肯德基啊灰色空间等哈阿斯达肯德基啊灰色空间等哈阿斯达肯德基啊灰色空间等哈阿斯达肯德基啊灰色空间等哈阿斯达肯德基啊灰色空间等哈', '1', '啊撒大声地', 0, '2025-02-28 12:39:18', '2025-02-28 13:37:11');
INSERT INTO `evaluate_feedback` VALUES (7, 4, '测试工单', '0', '处理了了    处理了了    处理了了    ', 0, '2025-02-28 13:33:00', '2025-02-28 13:33:16');
INSERT INTO `evaluate_feedback` VALUES (8, 4, '考虑考虑他', '0', NULL, 0, '2025-03-01 11:40:26', '2025-03-01 11:40:26');
INSERT INTO `evaluate_feedback` VALUES (9, 4, '旅途', '0', NULL, 0, '2025-03-01 11:43:02', '2025-03-01 11:43:02');

-- ----------------------------
-- Table structure for maintenance_record
-- ----------------------------
DROP TABLE IF EXISTS `maintenance_record`;
CREATE TABLE `maintenance_record`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `carId` int(12) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `createTime` datetime NULL DEFAULT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  `mainTime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of maintenance_record
-- ----------------------------
INSERT INTO `maintenance_record` VALUES (1, 13, '车辆机油检测', '2025-02-27 19:29:09', '2025-02-27 19:29:09', '2025-02-27 19:29:09');
INSERT INTO `maintenance_record` VALUES (2, 13, '底盘剐蹭', '2025-02-27 19:29:25', '2025-02-27 19:29:25', '2025-02-27 19:29:25');
INSERT INTO `maintenance_record` VALUES (3, 14, '底盘剐蹭', '2025-02-27 19:29:25', '2025-02-27 19:29:25', '2025-02-27 19:29:25');
INSERT INTO `maintenance_record` VALUES (5, 13, '测试', '2025-02-27 19:45:44', '2025-02-27 19:45:44', '2025-02-11 01:01:01');

-- ----------------------------
-- Table structure for rbac_resource
-- ----------------------------
DROP TABLE IF EXISTS `rbac_resource`;
CREATE TABLE `rbac_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '功能名称',
  `parentId` int(11) NOT NULL COMMENT '父ID',
  `deleted` int(11) NOT NULL DEFAULT 0 COMMENT '0',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `iconStyle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '功能图标',
  `jumpPath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转路由路径',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  `operId` int(11) NULL DEFAULT NULL COMMENT '操作人ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_evt_id`(`id`) USING BTREE,
  INDEX `idx_evt_name`(`name`) USING BTREE,
  INDEX `idx_evt_partId`(`parentId`) USING BTREE,
  INDEX `idx_evt_desc`(`description`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 95 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of rbac_resource
-- ----------------------------
INSERT INTO `rbac_resource` VALUES (1, '系统资源', 0, 0, '系统资源', 0, NULL, NULL, '2023-12-24 15:54:35', '2023-12-24 15:54:35', 1);
INSERT INTO `rbac_resource` VALUES (2, '权限管理', 1, 0, '权限管理', 7, 'fa fa-bar-chart', '/resource', '2023-12-24 16:26:59', '2025-02-19 00:30:06', 1);
INSERT INTO `rbac_resource` VALUES (24, '资源管理', 2, 0, '资源管理', 0, 'fa fa-bar-chart', '/resource', '2023-12-24 16:26:59', '2023-12-25 09:55:01', 1);
INSERT INTO `rbac_resource` VALUES (25, '角色管理', 2, 0, '角色管理', 1, 'fa fa-bar-chart', '/role', '2023-12-25 09:53:34', '2023-12-25 09:55:07', 1);
INSERT INTO `rbac_resource` VALUES (26, '角色授权', 2, 0, '角色授权', 2, 'fa fa-bar-chart', '/userRole', '2023-12-25 09:53:52', '2023-12-25 09:55:19', 1);
INSERT INTO `rbac_resource` VALUES (27, '用户管理', 2, 0, '用户管理', 3, 'fa fa-bar-chart', '/userManager', '2023-12-25 09:54:09', '2023-12-25 09:54:09', 1);
INSERT INTO `rbac_resource` VALUES (72, '主页', 1, 0, '主页', 0, 'fa fa-bar-chart', '/home', '2025-02-19 00:24:40', '2025-02-19 00:31:30', 1);
INSERT INTO `rbac_resource` VALUES (73, '主页', 72, 0, '主页', 9, 'fa fa-bar-chart', '/home', '2025-02-19 00:29:00', '2025-02-19 00:29:00', NULL);
INSERT INTO `rbac_resource` VALUES (87, '租赁管理', 1, 0, '租赁管理', 0, 'fa fa-bar-chart', '/rentalInfoAudit', '2025-02-26 19:45:52', '2025-02-26 19:45:52', NULL);
INSERT INTO `rbac_resource` VALUES (88, '租车人信息审核', 87, 0, '租车人信息审核', 0, 'fa fa-bar-chart', '/rentalInfoAudit', '2025-02-26 19:46:10', '2025-02-26 19:46:10', NULL);
INSERT INTO `rbac_resource` VALUES (89, '车辆管理', 87, 0, '车辆管理', 1, 'fa fa-bar-chart', '/carsManage', '2025-02-26 19:47:39', '2025-02-26 19:47:39', NULL);
INSERT INTO `rbac_resource` VALUES (90, '订单管理', 87, 0, '订单管理', 2, 'fa fa-bar-chart', '/ordersManage', '2025-02-26 19:48:44', '2025-02-26 19:48:44', NULL);
INSERT INTO `rbac_resource` VALUES (91, '安全管理', 87, 0, '安全管理', 3, 'fa fa-bar-chart', '/securityManage', '2025-02-26 19:49:51', '2025-02-26 19:49:51', NULL);
INSERT INTO `rbac_resource` VALUES (92, '在线客服', 87, 0, '在线客服', 4, 'fa fa-bar-chart', '/weChat', '2025-02-26 19:51:01', '2025-02-26 19:51:01', NULL);
INSERT INTO `rbac_resource` VALUES (93, '工单管理', 87, 0, '工单管理', 6, 'fa fa-bar-chart', '/gongdan', '2025-02-26 19:52:33', '2025-02-26 19:52:33', NULL);
INSERT INTO `rbac_resource` VALUES (94, '租赁记录', 87, 0, '租赁记录', 6, 'fa fa-bar-chart', '/rentalRecord', '2025-02-26 23:36:28', '2025-02-26 23:36:28', NULL);

-- ----------------------------
-- Table structure for rbac_role
-- ----------------------------
DROP TABLE IF EXISTS `rbac_role`;
CREATE TABLE `rbac_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `opaId` int(11) NOT NULL,
  `deleted` int(11) NOT NULL DEFAULT 0,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_evt_id`(`id`) USING BTREE,
  INDEX `idx_evt_name`(`name`) USING BTREE,
  INDEX `idx_evt_opaId`(`opaId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of rbac_role
-- ----------------------------
INSERT INTO `rbac_role` VALUES (8, '超级管理员', '超级管理员', 1, 0, '2023-12-24 16:24:11', '2023-12-24 16:24:13');
INSERT INTO `rbac_role` VALUES (9, '普通管理员', '普通管理员', 2, 0, '2023-12-27 14:32:50', '2023-12-27 14:32:50');
INSERT INTO `rbac_role` VALUES (14, '管理员', '管理员', 1, 0, '2025-02-11 11:58:35', '2025-02-11 11:58:35');

-- ----------------------------
-- Table structure for rbac_roleresource
-- ----------------------------
DROP TABLE IF EXISTS `rbac_roleresource`;
CREATE TABLE `rbac_roleresource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL,
  `resourceId` int(11) NOT NULL,
  `opaId` int(11) NOT NULL,
  `deleted` int(11) NULL DEFAULT 0,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_evt_id`(`id`) USING BTREE,
  INDEX `idx_evt_roleId`(`roleId`) USING BTREE,
  INDEX `idx_evt_resourId`(`resourceId`) USING BTREE,
  INDEX `idx_evt_creTi`(`createTime`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 199105 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of rbac_roleresource
-- ----------------------------
INSERT INTO `rbac_roleresource` VALUES (198467, 10, 31, 2, 0, '2023-12-27 14:33:26', '2023-12-27 14:33:26');
INSERT INTO `rbac_roleresource` VALUES (198468, 10, 32, 2, 0, '2023-12-27 14:33:26', '2023-12-27 14:33:26');
INSERT INTO `rbac_roleresource` VALUES (198469, 10, 2, 2, 0, '2023-12-27 14:33:26', '2023-12-27 14:33:26');
INSERT INTO `rbac_roleresource` VALUES (198601, 9, 35, 1, 0, '2025-02-04 15:38:01', '2025-02-04 15:38:01');
INSERT INTO `rbac_roleresource` VALUES (198602, 9, 36, 1, 0, '2025-02-04 15:38:01', '2025-02-04 15:38:01');
INSERT INTO `rbac_roleresource` VALUES (198603, 9, 38, 1, 0, '2025-02-04 15:38:01', '2025-02-04 15:38:01');
INSERT INTO `rbac_roleresource` VALUES (198604, 9, 39, 1, 0, '2025-02-04 15:38:01', '2025-02-04 15:38:01');
INSERT INTO `rbac_roleresource` VALUES (198605, 9, 40, 1, 0, '2025-02-04 15:38:01', '2025-02-04 15:38:01');
INSERT INTO `rbac_roleresource` VALUES (198606, 9, 41, 1, 0, '2025-02-04 15:38:01', '2025-02-04 15:38:01');
INSERT INTO `rbac_roleresource` VALUES (198607, 9, 42, 1, 0, '2025-02-04 15:38:01', '2025-02-04 15:38:01');
INSERT INTO `rbac_roleresource` VALUES (198608, 9, 43, 1, 0, '2025-02-04 15:38:01', '2025-02-04 15:38:01');
INSERT INTO `rbac_roleresource` VALUES (198609, 9, 44, 1, 0, '2025-02-04 15:38:01', '2025-02-04 15:38:01');
INSERT INTO `rbac_roleresource` VALUES (198838, 11, 45, 1, 0, '2025-02-11 11:57:14', '2025-02-11 11:57:14');
INSERT INTO `rbac_roleresource` VALUES (198839, 11, 35, 1, 0, '2025-02-11 11:57:14', '2025-02-11 11:57:14');
INSERT INTO `rbac_roleresource` VALUES (198840, 12, 45, 1, 0, '2025-02-11 11:57:57', '2025-02-11 11:57:57');
INSERT INTO `rbac_roleresource` VALUES (198841, 12, 48, 1, 0, '2025-02-11 11:57:57', '2025-02-11 11:57:57');
INSERT INTO `rbac_roleresource` VALUES (198842, 12, 55, 1, 0, '2025-02-11 11:57:57', '2025-02-11 11:57:57');
INSERT INTO `rbac_roleresource` VALUES (198843, 12, 57, 1, 0, '2025-02-11 11:57:57', '2025-02-11 11:57:57');
INSERT INTO `rbac_roleresource` VALUES (198844, 12, 35, 1, 0, '2025-02-11 11:57:57', '2025-02-11 11:57:57');
INSERT INTO `rbac_roleresource` VALUES (198851, 14, 46, 1, 0, '2025-02-11 11:59:00', '2025-02-11 11:59:00');
INSERT INTO `rbac_roleresource` VALUES (198852, 14, 47, 1, 0, '2025-02-11 11:59:00', '2025-02-11 11:59:00');
INSERT INTO `rbac_roleresource` VALUES (198853, 14, 51, 1, 0, '2025-02-11 11:59:00', '2025-02-11 11:59:00');
INSERT INTO `rbac_roleresource` VALUES (198854, 14, 52, 1, 0, '2025-02-11 11:59:00', '2025-02-11 11:59:00');
INSERT INTO `rbac_roleresource` VALUES (198855, 14, 53, 1, 0, '2025-02-11 11:59:00', '2025-02-11 11:59:00');
INSERT INTO `rbac_roleresource` VALUES (198856, 14, 56, 1, 0, '2025-02-11 11:59:00', '2025-02-11 11:59:00');
INSERT INTO `rbac_roleresource` VALUES (198857, 14, 57, 1, 0, '2025-02-11 11:59:00', '2025-02-11 11:59:00');
INSERT INTO `rbac_roleresource` VALUES (198858, 14, 58, 1, 0, '2025-02-11 11:59:00', '2025-02-11 11:59:00');
INSERT INTO `rbac_roleresource` VALUES (198859, 14, 59, 1, 0, '2025-02-11 11:59:00', '2025-02-11 11:59:00');
INSERT INTO `rbac_roleresource` VALUES (198860, 14, 35, 1, 0, '2025-02-11 11:59:00', '2025-02-11 11:59:00');
INSERT INTO `rbac_roleresource` VALUES (198861, 13, 49, 1, 0, '2025-02-11 11:59:05', '2025-02-11 11:59:05');
INSERT INTO `rbac_roleresource` VALUES (198862, 13, 50, 1, 0, '2025-02-11 11:59:05', '2025-02-11 11:59:05');
INSERT INTO `rbac_roleresource` VALUES (198863, 13, 54, 1, 0, '2025-02-11 11:59:05', '2025-02-11 11:59:05');
INSERT INTO `rbac_roleresource` VALUES (198864, 13, 57, 1, 0, '2025-02-11 11:59:05', '2025-02-11 11:59:05');
INSERT INTO `rbac_roleresource` VALUES (198865, 13, 35, 1, 0, '2025-02-11 11:59:05', '2025-02-11 11:59:05');
INSERT INTO `rbac_roleresource` VALUES (198894, 15, 60, 1, 1, '2025-02-15 00:00:31', '2025-02-15 00:00:31');
INSERT INTO `rbac_roleresource` VALUES (198895, 15, 61, 1, 1, '2025-02-15 00:00:31', '2025-02-15 00:00:31');
INSERT INTO `rbac_roleresource` VALUES (198896, 15, 62, 1, 1, '2025-02-15 00:00:31', '2025-02-15 00:00:31');
INSERT INTO `rbac_roleresource` VALUES (198897, 15, 63, 1, 1, '2025-02-15 00:00:31', '2025-02-15 00:00:31');
INSERT INTO `rbac_roleresource` VALUES (198898, 15, 64, 1, 1, '2025-02-15 00:00:31', '2025-02-15 00:00:31');
INSERT INTO `rbac_roleresource` VALUES (198899, 15, 65, 1, 1, '2025-02-15 00:00:31', '2025-02-15 00:00:31');
INSERT INTO `rbac_roleresource` VALUES (198900, 15, 66, 1, 1, '2025-02-15 00:00:31', '2025-02-15 00:00:31');
INSERT INTO `rbac_roleresource` VALUES (198901, 15, 67, 1, 1, '2025-02-15 00:00:31', '2025-02-15 00:00:31');
INSERT INTO `rbac_roleresource` VALUES (198933, 8, 60, 1, 1, '2025-02-16 01:15:24', '2025-02-16 01:15:24');
INSERT INTO `rbac_roleresource` VALUES (198934, 8, 61, 1, 1, '2025-02-16 01:15:24', '2025-02-16 01:15:24');
INSERT INTO `rbac_roleresource` VALUES (198935, 8, 62, 1, 1, '2025-02-16 01:15:24', '2025-02-16 01:15:24');
INSERT INTO `rbac_roleresource` VALUES (198936, 8, 63, 1, 1, '2025-02-16 01:15:24', '2025-02-16 01:15:24');
INSERT INTO `rbac_roleresource` VALUES (198937, 8, 64, 1, 1, '2025-02-16 01:15:24', '2025-02-16 01:15:24');
INSERT INTO `rbac_roleresource` VALUES (198938, 8, 65, 1, 1, '2025-02-16 01:15:24', '2025-02-16 01:15:24');
INSERT INTO `rbac_roleresource` VALUES (198939, 8, 66, 1, 1, '2025-02-16 01:15:24', '2025-02-16 01:15:24');
INSERT INTO `rbac_roleresource` VALUES (198940, 8, 67, 1, 1, '2025-02-16 01:15:24', '2025-02-16 01:15:24');
INSERT INTO `rbac_roleresource` VALUES (198941, 8, 68, 1, 1, '2025-02-16 01:15:24', '2025-02-16 01:15:24');
INSERT INTO `rbac_roleresource` VALUES (198942, 8, 70, 1, 1, '2025-02-16 01:15:24', '2025-02-16 01:15:24');
INSERT INTO `rbac_roleresource` VALUES (198943, 8, 69, 1, 1, '2025-02-16 01:15:24', '2025-02-16 01:15:24');
INSERT INTO `rbac_roleresource` VALUES (198944, 8, 71, 1, 1, '2025-02-16 01:15:24', '2025-02-16 01:15:24');
INSERT INTO `rbac_roleresource` VALUES (198945, 8, 2, 1, 1, '2025-02-16 01:15:24', '2025-02-16 01:15:24');
INSERT INTO `rbac_roleresource` VALUES (198946, 8, 24, 1, 1, '2025-02-16 01:15:24', '2025-02-16 01:15:24');
INSERT INTO `rbac_roleresource` VALUES (198947, 8, 25, 1, 1, '2025-02-16 01:15:24', '2025-02-16 01:15:24');
INSERT INTO `rbac_roleresource` VALUES (198948, 8, 26, 1, 1, '2025-02-16 01:15:24', '2025-02-16 01:15:24');
INSERT INTO `rbac_roleresource` VALUES (198949, 8, 27, 1, 1, '2025-02-16 01:15:24', '2025-02-16 01:15:24');
INSERT INTO `rbac_roleresource` VALUES (198952, 16, 70, 1, 0, '2025-02-16 01:20:45', '2025-02-16 01:20:45');
INSERT INTO `rbac_roleresource` VALUES (198953, 16, 71, 1, 0, '2025-02-16 01:20:45', '2025-02-16 01:20:45');
INSERT INTO `rbac_roleresource` VALUES (198954, 16, 60, 1, 0, '2025-02-16 01:20:45', '2025-02-16 01:20:45');
INSERT INTO `rbac_roleresource` VALUES (198955, 15, 63, 1, 0, '2025-02-16 09:55:50', '2025-02-16 09:55:50');
INSERT INTO `rbac_roleresource` VALUES (198956, 15, 64, 1, 0, '2025-02-16 09:55:50', '2025-02-16 09:55:50');
INSERT INTO `rbac_roleresource` VALUES (198957, 15, 67, 1, 0, '2025-02-16 09:55:50', '2025-02-16 09:55:50');
INSERT INTO `rbac_roleresource` VALUES (198958, 15, 60, 1, 0, '2025-02-16 09:55:50', '2025-02-16 09:55:50');
INSERT INTO `rbac_roleresource` VALUES (198959, 17, 61, 1, 0, '2025-02-16 09:56:48', '2025-02-16 09:56:48');
INSERT INTO `rbac_roleresource` VALUES (198960, 17, 60, 1, 0, '2025-02-16 09:56:48', '2025-02-16 09:56:48');
INSERT INTO `rbac_roleresource` VALUES (198961, 18, 65, 1, 0, '2025-02-16 09:57:14', '2025-02-16 09:57:14');
INSERT INTO `rbac_roleresource` VALUES (198962, 18, 60, 1, 0, '2025-02-16 09:57:14', '2025-02-16 09:57:14');
INSERT INTO `rbac_roleresource` VALUES (198963, 19, 66, 1, 0, '2025-02-16 09:57:33', '2025-02-16 09:57:33');
INSERT INTO `rbac_roleresource` VALUES (198964, 19, 60, 1, 0, '2025-02-16 09:57:33', '2025-02-16 09:57:33');
INSERT INTO `rbac_roleresource` VALUES (198965, 20, 68, 1, 0, '2025-02-16 09:57:54', '2025-02-16 09:57:54');
INSERT INTO `rbac_roleresource` VALUES (198966, 20, 60, 1, 0, '2025-02-16 09:57:54', '2025-02-16 09:57:54');
INSERT INTO `rbac_roleresource` VALUES (198967, 8, 60, 1, 1, '2025-02-19 00:26:44', '2025-02-19 00:26:44');
INSERT INTO `rbac_roleresource` VALUES (198968, 8, 61, 1, 1, '2025-02-19 00:26:44', '2025-02-19 00:26:44');
INSERT INTO `rbac_roleresource` VALUES (198969, 8, 62, 1, 1, '2025-02-19 00:26:44', '2025-02-19 00:26:44');
INSERT INTO `rbac_roleresource` VALUES (198970, 8, 63, 1, 1, '2025-02-19 00:26:44', '2025-02-19 00:26:44');
INSERT INTO `rbac_roleresource` VALUES (198971, 8, 64, 1, 1, '2025-02-19 00:26:44', '2025-02-19 00:26:44');
INSERT INTO `rbac_roleresource` VALUES (198972, 8, 65, 1, 1, '2025-02-19 00:26:44', '2025-02-19 00:26:44');
INSERT INTO `rbac_roleresource` VALUES (198973, 8, 66, 1, 1, '2025-02-19 00:26:44', '2025-02-19 00:26:44');
INSERT INTO `rbac_roleresource` VALUES (198974, 8, 67, 1, 1, '2025-02-19 00:26:44', '2025-02-19 00:26:44');
INSERT INTO `rbac_roleresource` VALUES (198975, 8, 68, 1, 1, '2025-02-19 00:26:44', '2025-02-19 00:26:44');
INSERT INTO `rbac_roleresource` VALUES (198976, 8, 70, 1, 1, '2025-02-19 00:26:44', '2025-02-19 00:26:44');
INSERT INTO `rbac_roleresource` VALUES (198977, 8, 69, 1, 1, '2025-02-19 00:26:44', '2025-02-19 00:26:44');
INSERT INTO `rbac_roleresource` VALUES (198978, 8, 71, 1, 1, '2025-02-19 00:26:44', '2025-02-19 00:26:44');
INSERT INTO `rbac_roleresource` VALUES (198979, 8, 2, 1, 1, '2025-02-19 00:26:44', '2025-02-19 00:26:44');
INSERT INTO `rbac_roleresource` VALUES (198980, 8, 24, 1, 1, '2025-02-19 00:26:44', '2025-02-19 00:26:44');
INSERT INTO `rbac_roleresource` VALUES (198981, 8, 25, 1, 1, '2025-02-19 00:26:44', '2025-02-19 00:26:44');
INSERT INTO `rbac_roleresource` VALUES (198982, 8, 26, 1, 1, '2025-02-19 00:26:44', '2025-02-19 00:26:44');
INSERT INTO `rbac_roleresource` VALUES (198983, 8, 27, 1, 1, '2025-02-19 00:26:44', '2025-02-19 00:26:44');
INSERT INTO `rbac_roleresource` VALUES (198984, 8, 72, 1, 1, '2025-02-19 00:26:44', '2025-02-19 00:26:44');
INSERT INTO `rbac_roleresource` VALUES (198985, 8, 2, 1, 1, '2025-02-19 00:29:13', '2025-02-19 00:29:13');
INSERT INTO `rbac_roleresource` VALUES (198986, 8, 24, 1, 1, '2025-02-19 00:29:13', '2025-02-19 00:29:13');
INSERT INTO `rbac_roleresource` VALUES (198987, 8, 25, 1, 1, '2025-02-19 00:29:13', '2025-02-19 00:29:13');
INSERT INTO `rbac_roleresource` VALUES (198988, 8, 26, 1, 1, '2025-02-19 00:29:13', '2025-02-19 00:29:13');
INSERT INTO `rbac_roleresource` VALUES (198989, 8, 27, 1, 1, '2025-02-19 00:29:13', '2025-02-19 00:29:13');
INSERT INTO `rbac_roleresource` VALUES (198990, 8, 72, 1, 1, '2025-02-19 00:29:13', '2025-02-19 00:29:13');
INSERT INTO `rbac_roleresource` VALUES (198991, 8, 73, 1, 1, '2025-02-19 00:29:13', '2025-02-19 00:29:13');
INSERT INTO `rbac_roleresource` VALUES (198992, 8, 72, 1, 1, '2025-02-19 00:45:40', '2025-02-19 00:45:40');
INSERT INTO `rbac_roleresource` VALUES (198993, 8, 73, 1, 1, '2025-02-19 00:45:40', '2025-02-19 00:45:40');
INSERT INTO `rbac_roleresource` VALUES (198994, 8, 74, 1, 1, '2025-02-19 00:45:40', '2025-02-19 00:45:40');
INSERT INTO `rbac_roleresource` VALUES (198995, 8, 75, 1, 1, '2025-02-19 00:45:40', '2025-02-19 00:45:40');
INSERT INTO `rbac_roleresource` VALUES (198996, 8, 76, 1, 1, '2025-02-19 00:45:40', '2025-02-19 00:45:40');
INSERT INTO `rbac_roleresource` VALUES (198997, 8, 77, 1, 1, '2025-02-19 00:45:40', '2025-02-19 00:45:40');
INSERT INTO `rbac_roleresource` VALUES (198998, 8, 78, 1, 1, '2025-02-19 00:45:40', '2025-02-19 00:45:40');
INSERT INTO `rbac_roleresource` VALUES (198999, 8, 79, 1, 1, '2025-02-19 00:45:40', '2025-02-19 00:45:40');
INSERT INTO `rbac_roleresource` VALUES (199000, 8, 80, 1, 1, '2025-02-19 00:45:40', '2025-02-19 00:45:40');
INSERT INTO `rbac_roleresource` VALUES (199001, 8, 81, 1, 1, '2025-02-19 00:45:40', '2025-02-19 00:45:40');
INSERT INTO `rbac_roleresource` VALUES (199002, 8, 82, 1, 1, '2025-02-19 00:45:40', '2025-02-19 00:45:40');
INSERT INTO `rbac_roleresource` VALUES (199003, 8, 83, 1, 1, '2025-02-19 00:45:40', '2025-02-19 00:45:40');
INSERT INTO `rbac_roleresource` VALUES (199004, 8, 84, 1, 1, '2025-02-19 00:45:40', '2025-02-19 00:45:40');
INSERT INTO `rbac_roleresource` VALUES (199005, 8, 85, 1, 1, '2025-02-19 00:45:40', '2025-02-19 00:45:40');
INSERT INTO `rbac_roleresource` VALUES (199006, 8, 2, 1, 1, '2025-02-19 00:45:40', '2025-02-19 00:45:40');
INSERT INTO `rbac_roleresource` VALUES (199007, 8, 24, 1, 1, '2025-02-19 00:45:40', '2025-02-19 00:45:40');
INSERT INTO `rbac_roleresource` VALUES (199008, 8, 25, 1, 1, '2025-02-19 00:45:40', '2025-02-19 00:45:40');
INSERT INTO `rbac_roleresource` VALUES (199009, 8, 26, 1, 1, '2025-02-19 00:45:40', '2025-02-19 00:45:40');
INSERT INTO `rbac_roleresource` VALUES (199010, 8, 27, 1, 1, '2025-02-19 00:45:40', '2025-02-19 00:45:40');
INSERT INTO `rbac_roleresource` VALUES (199011, 8, 72, 1, 1, '2025-02-21 01:16:36', '2025-02-21 01:16:36');
INSERT INTO `rbac_roleresource` VALUES (199012, 8, 73, 1, 1, '2025-02-21 01:16:36', '2025-02-21 01:16:36');
INSERT INTO `rbac_roleresource` VALUES (199013, 8, 74, 1, 1, '2025-02-21 01:16:36', '2025-02-21 01:16:36');
INSERT INTO `rbac_roleresource` VALUES (199014, 8, 75, 1, 1, '2025-02-21 01:16:36', '2025-02-21 01:16:36');
INSERT INTO `rbac_roleresource` VALUES (199015, 8, 2, 1, 1, '2025-02-21 01:16:36', '2025-02-21 01:16:36');
INSERT INTO `rbac_roleresource` VALUES (199016, 8, 24, 1, 1, '2025-02-21 01:16:36', '2025-02-21 01:16:36');
INSERT INTO `rbac_roleresource` VALUES (199017, 8, 25, 1, 1, '2025-02-21 01:16:36', '2025-02-21 01:16:36');
INSERT INTO `rbac_roleresource` VALUES (199018, 8, 26, 1, 1, '2025-02-21 01:16:36', '2025-02-21 01:16:36');
INSERT INTO `rbac_roleresource` VALUES (199019, 8, 27, 1, 1, '2025-02-21 01:16:36', '2025-02-21 01:16:36');
INSERT INTO `rbac_roleresource` VALUES (199020, 8, 80, 1, 1, '2025-02-21 01:16:36', '2025-02-21 01:16:36');
INSERT INTO `rbac_roleresource` VALUES (199021, 8, 81, 1, 1, '2025-02-21 01:16:36', '2025-02-21 01:16:36');
INSERT INTO `rbac_roleresource` VALUES (199022, 8, 86, 1, 1, '2025-02-21 01:16:36', '2025-02-21 01:16:36');
INSERT INTO `rbac_roleresource` VALUES (199023, 8, 76, 1, 1, '2025-02-21 01:16:36', '2025-02-21 01:16:36');
INSERT INTO `rbac_roleresource` VALUES (199024, 8, 77, 1, 1, '2025-02-21 01:16:36', '2025-02-21 01:16:36');
INSERT INTO `rbac_roleresource` VALUES (199025, 8, 78, 1, 1, '2025-02-21 01:16:36', '2025-02-21 01:16:36');
INSERT INTO `rbac_roleresource` VALUES (199026, 8, 79, 1, 1, '2025-02-21 01:16:36', '2025-02-21 01:16:36');
INSERT INTO `rbac_roleresource` VALUES (199027, 8, 82, 1, 1, '2025-02-21 01:16:36', '2025-02-21 01:16:36');
INSERT INTO `rbac_roleresource` VALUES (199028, 8, 83, 1, 1, '2025-02-21 01:16:36', '2025-02-21 01:16:36');
INSERT INTO `rbac_roleresource` VALUES (199029, 8, 84, 1, 1, '2025-02-21 01:16:36', '2025-02-21 01:16:36');
INSERT INTO `rbac_roleresource` VALUES (199030, 8, 85, 1, 1, '2025-02-21 01:16:36', '2025-02-21 01:16:36');
INSERT INTO `rbac_roleresource` VALUES (199031, 8, 72, 1, 1, '2025-02-26 19:46:16', '2025-02-26 19:46:16');
INSERT INTO `rbac_roleresource` VALUES (199032, 8, 73, 1, 1, '2025-02-26 19:46:16', '2025-02-26 19:46:16');
INSERT INTO `rbac_roleresource` VALUES (199033, 8, 87, 1, 1, '2025-02-26 19:46:16', '2025-02-26 19:46:16');
INSERT INTO `rbac_roleresource` VALUES (199034, 8, 88, 1, 1, '2025-02-26 19:46:16', '2025-02-26 19:46:16');
INSERT INTO `rbac_roleresource` VALUES (199035, 8, 2, 1, 1, '2025-02-26 19:46:16', '2025-02-26 19:46:16');
INSERT INTO `rbac_roleresource` VALUES (199036, 8, 24, 1, 1, '2025-02-26 19:46:16', '2025-02-26 19:46:16');
INSERT INTO `rbac_roleresource` VALUES (199037, 8, 25, 1, 1, '2025-02-26 19:46:16', '2025-02-26 19:46:16');
INSERT INTO `rbac_roleresource` VALUES (199038, 8, 26, 1, 1, '2025-02-26 19:46:16', '2025-02-26 19:46:16');
INSERT INTO `rbac_roleresource` VALUES (199039, 8, 27, 1, 1, '2025-02-26 19:46:16', '2025-02-26 19:46:16');
INSERT INTO `rbac_roleresource` VALUES (199040, 8, 72, 1, 1, '2025-02-26 19:47:44', '2025-02-26 19:47:44');
INSERT INTO `rbac_roleresource` VALUES (199041, 8, 73, 1, 1, '2025-02-26 19:47:44', '2025-02-26 19:47:44');
INSERT INTO `rbac_roleresource` VALUES (199042, 8, 87, 1, 1, '2025-02-26 19:47:44', '2025-02-26 19:47:44');
INSERT INTO `rbac_roleresource` VALUES (199043, 8, 88, 1, 1, '2025-02-26 19:47:44', '2025-02-26 19:47:44');
INSERT INTO `rbac_roleresource` VALUES (199044, 8, 89, 1, 1, '2025-02-26 19:47:44', '2025-02-26 19:47:44');
INSERT INTO `rbac_roleresource` VALUES (199045, 8, 2, 1, 1, '2025-02-26 19:47:44', '2025-02-26 19:47:44');
INSERT INTO `rbac_roleresource` VALUES (199046, 8, 24, 1, 1, '2025-02-26 19:47:44', '2025-02-26 19:47:44');
INSERT INTO `rbac_roleresource` VALUES (199047, 8, 25, 1, 1, '2025-02-26 19:47:44', '2025-02-26 19:47:44');
INSERT INTO `rbac_roleresource` VALUES (199048, 8, 26, 1, 1, '2025-02-26 19:47:44', '2025-02-26 19:47:44');
INSERT INTO `rbac_roleresource` VALUES (199049, 8, 27, 1, 1, '2025-02-26 19:47:44', '2025-02-26 19:47:44');
INSERT INTO `rbac_roleresource` VALUES (199050, 8, 72, 1, 1, '2025-02-26 19:48:50', '2025-02-26 19:48:50');
INSERT INTO `rbac_roleresource` VALUES (199051, 8, 73, 1, 1, '2025-02-26 19:48:50', '2025-02-26 19:48:50');
INSERT INTO `rbac_roleresource` VALUES (199052, 8, 87, 1, 1, '2025-02-26 19:48:50', '2025-02-26 19:48:50');
INSERT INTO `rbac_roleresource` VALUES (199053, 8, 88, 1, 1, '2025-02-26 19:48:50', '2025-02-26 19:48:50');
INSERT INTO `rbac_roleresource` VALUES (199054, 8, 89, 1, 1, '2025-02-26 19:48:50', '2025-02-26 19:48:50');
INSERT INTO `rbac_roleresource` VALUES (199055, 8, 90, 1, 1, '2025-02-26 19:48:50', '2025-02-26 19:48:50');
INSERT INTO `rbac_roleresource` VALUES (199056, 8, 2, 1, 1, '2025-02-26 19:48:50', '2025-02-26 19:48:50');
INSERT INTO `rbac_roleresource` VALUES (199057, 8, 24, 1, 1, '2025-02-26 19:48:50', '2025-02-26 19:48:50');
INSERT INTO `rbac_roleresource` VALUES (199058, 8, 25, 1, 1, '2025-02-26 19:48:50', '2025-02-26 19:48:50');
INSERT INTO `rbac_roleresource` VALUES (199059, 8, 26, 1, 1, '2025-02-26 19:48:50', '2025-02-26 19:48:50');
INSERT INTO `rbac_roleresource` VALUES (199060, 8, 27, 1, 1, '2025-02-26 19:48:50', '2025-02-26 19:48:50');
INSERT INTO `rbac_roleresource` VALUES (199061, 8, 72, 1, 1, '2025-02-26 19:52:39', '2025-02-26 19:52:39');
INSERT INTO `rbac_roleresource` VALUES (199062, 8, 73, 1, 1, '2025-02-26 19:52:39', '2025-02-26 19:52:39');
INSERT INTO `rbac_roleresource` VALUES (199063, 8, 87, 1, 1, '2025-02-26 19:52:39', '2025-02-26 19:52:39');
INSERT INTO `rbac_roleresource` VALUES (199064, 8, 88, 1, 1, '2025-02-26 19:52:39', '2025-02-26 19:52:39');
INSERT INTO `rbac_roleresource` VALUES (199065, 8, 89, 1, 1, '2025-02-26 19:52:39', '2025-02-26 19:52:39');
INSERT INTO `rbac_roleresource` VALUES (199066, 8, 90, 1, 1, '2025-02-26 19:52:39', '2025-02-26 19:52:39');
INSERT INTO `rbac_roleresource` VALUES (199067, 8, 91, 1, 1, '2025-02-26 19:52:39', '2025-02-26 19:52:39');
INSERT INTO `rbac_roleresource` VALUES (199068, 8, 92, 1, 1, '2025-02-26 19:52:39', '2025-02-26 19:52:39');
INSERT INTO `rbac_roleresource` VALUES (199069, 8, 93, 1, 1, '2025-02-26 19:52:39', '2025-02-26 19:52:39');
INSERT INTO `rbac_roleresource` VALUES (199070, 8, 2, 1, 1, '2025-02-26 19:52:39', '2025-02-26 19:52:39');
INSERT INTO `rbac_roleresource` VALUES (199071, 8, 24, 1, 1, '2025-02-26 19:52:39', '2025-02-26 19:52:39');
INSERT INTO `rbac_roleresource` VALUES (199072, 8, 25, 1, 1, '2025-02-26 19:52:39', '2025-02-26 19:52:39');
INSERT INTO `rbac_roleresource` VALUES (199073, 8, 26, 1, 1, '2025-02-26 19:52:39', '2025-02-26 19:52:39');
INSERT INTO `rbac_roleresource` VALUES (199074, 8, 27, 1, 1, '2025-02-26 19:52:39', '2025-02-26 19:52:39');
INSERT INTO `rbac_roleresource` VALUES (199075, 8, 72, 1, 1, '2025-02-26 23:36:32', '2025-02-26 23:36:32');
INSERT INTO `rbac_roleresource` VALUES (199076, 8, 73, 1, 1, '2025-02-26 23:36:32', '2025-02-26 23:36:32');
INSERT INTO `rbac_roleresource` VALUES (199077, 8, 87, 1, 1, '2025-02-26 23:36:32', '2025-02-26 23:36:32');
INSERT INTO `rbac_roleresource` VALUES (199078, 8, 88, 1, 1, '2025-02-26 23:36:32', '2025-02-26 23:36:32');
INSERT INTO `rbac_roleresource` VALUES (199079, 8, 89, 1, 1, '2025-02-26 23:36:32', '2025-02-26 23:36:32');
INSERT INTO `rbac_roleresource` VALUES (199080, 8, 90, 1, 1, '2025-02-26 23:36:32', '2025-02-26 23:36:32');
INSERT INTO `rbac_roleresource` VALUES (199081, 8, 91, 1, 1, '2025-02-26 23:36:32', '2025-02-26 23:36:32');
INSERT INTO `rbac_roleresource` VALUES (199082, 8, 92, 1, 1, '2025-02-26 23:36:32', '2025-02-26 23:36:32');
INSERT INTO `rbac_roleresource` VALUES (199083, 8, 93, 1, 1, '2025-02-26 23:36:32', '2025-02-26 23:36:32');
INSERT INTO `rbac_roleresource` VALUES (199084, 8, 94, 1, 1, '2025-02-26 23:36:32', '2025-02-26 23:36:32');
INSERT INTO `rbac_roleresource` VALUES (199085, 8, 2, 1, 1, '2025-02-26 23:36:32', '2025-02-26 23:36:32');
INSERT INTO `rbac_roleresource` VALUES (199086, 8, 24, 1, 1, '2025-02-26 23:36:32', '2025-02-26 23:36:32');
INSERT INTO `rbac_roleresource` VALUES (199087, 8, 25, 1, 1, '2025-02-26 23:36:32', '2025-02-26 23:36:32');
INSERT INTO `rbac_roleresource` VALUES (199088, 8, 26, 1, 1, '2025-02-26 23:36:32', '2025-02-26 23:36:32');
INSERT INTO `rbac_roleresource` VALUES (199089, 8, 27, 1, 1, '2025-02-26 23:36:32', '2025-02-26 23:36:32');
INSERT INTO `rbac_roleresource` VALUES (199090, 8, 72, 1, 0, '2025-02-26 23:38:13', '2025-02-26 23:38:13');
INSERT INTO `rbac_roleresource` VALUES (199091, 8, 73, 1, 0, '2025-02-26 23:38:13', '2025-02-26 23:38:13');
INSERT INTO `rbac_roleresource` VALUES (199092, 8, 87, 1, 0, '2025-02-26 23:38:13', '2025-02-26 23:38:13');
INSERT INTO `rbac_roleresource` VALUES (199093, 8, 88, 1, 0, '2025-02-26 23:38:13', '2025-02-26 23:38:13');
INSERT INTO `rbac_roleresource` VALUES (199094, 8, 89, 1, 0, '2025-02-26 23:38:13', '2025-02-26 23:38:13');
INSERT INTO `rbac_roleresource` VALUES (199095, 8, 90, 1, 0, '2025-02-26 23:38:13', '2025-02-26 23:38:13');
INSERT INTO `rbac_roleresource` VALUES (199096, 8, 91, 1, 0, '2025-02-26 23:38:13', '2025-02-26 23:38:13');
INSERT INTO `rbac_roleresource` VALUES (199097, 8, 92, 1, 0, '2025-02-26 23:38:13', '2025-02-26 23:38:13');
INSERT INTO `rbac_roleresource` VALUES (199098, 8, 93, 1, 0, '2025-02-26 23:38:13', '2025-02-26 23:38:13');
INSERT INTO `rbac_roleresource` VALUES (199099, 8, 94, 1, 0, '2025-02-26 23:38:13', '2025-02-26 23:38:13');
INSERT INTO `rbac_roleresource` VALUES (199100, 8, 2, 1, 0, '2025-02-26 23:38:13', '2025-02-26 23:38:13');
INSERT INTO `rbac_roleresource` VALUES (199101, 8, 24, 1, 0, '2025-02-26 23:38:13', '2025-02-26 23:38:13');
INSERT INTO `rbac_roleresource` VALUES (199102, 8, 25, 1, 0, '2025-02-26 23:38:13', '2025-02-26 23:38:13');
INSERT INTO `rbac_roleresource` VALUES (199103, 8, 26, 1, 0, '2025-02-26 23:38:13', '2025-02-26 23:38:13');
INSERT INTO `rbac_roleresource` VALUES (199104, 8, 27, 1, 0, '2025-02-26 23:38:13', '2025-02-26 23:38:13');

-- ----------------------------
-- Table structure for rbac_userresource
-- ----------------------------
DROP TABLE IF EXISTS `rbac_userresource`;
CREATE TABLE `rbac_userresource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `resourceId` int(11) NOT NULL,
  `opaId` int(11) NOT NULL,
  `deleted` int(11) NULL DEFAULT 0,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_evt_id`(`id`) USING BTREE,
  INDEX `idx_evt_account`(`userId`) USING BTREE,
  INDEX `idx_evt_resour`(`resourceId`) USING BTREE,
  INDEX `idx_evt_opaId`(`opaId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 815 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of rbac_userresource
-- ----------------------------
INSERT INTO `rbac_userresource` VALUES (813, 1, 1, 1, 0, '2023-12-24 16:24:32', '2023-12-24 16:24:34');
INSERT INTO `rbac_userresource` VALUES (814, 2, 1, 1, 0, '2023-12-24 16:24:32', '2023-12-24 16:24:34');

-- ----------------------------
-- Table structure for rbac_userrole
-- ----------------------------
DROP TABLE IF EXISTS `rbac_userrole`;
CREATE TABLE `rbac_userrole`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  `opaId` int(11) NOT NULL,
  `deleted` int(11) NULL DEFAULT 0,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_evt_id`(`id`) USING BTREE,
  INDEX `idx_evt_account`(`userId`) USING BTREE,
  INDEX `idx_evt_roleId`(`roleId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 211 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of rbac_userrole
-- ----------------------------
INSERT INTO `rbac_userrole` VALUES (199, 3, 9, 1, 0, '2025-02-04 16:04:14', '2025-02-04 16:04:14');
INSERT INTO `rbac_userrole` VALUES (200, 8, 11, 999, 0, '2025-02-11 12:27:55', '2025-02-11 12:27:55');
INSERT INTO `rbac_userrole` VALUES (202, 9, 12, 999, 0, '2025-02-11 12:31:28', '2025-02-11 12:31:28');
INSERT INTO `rbac_userrole` VALUES (204, 10, 15, 1, 0, '2025-02-15 00:00:49', '2025-02-15 00:00:49');
INSERT INTO `rbac_userrole` VALUES (206, 1, 8, 1, 0, '2025-02-15 00:07:32', '2025-02-15 00:07:32');
INSERT INTO `rbac_userrole` VALUES (207, 2, 16, 1, 0, '2025-02-16 01:17:01', '2025-02-16 01:17:01');
INSERT INTO `rbac_userrole` VALUES (208, 12, 15, 999, 0, '2025-02-16 09:59:34', '2025-02-16 09:59:34');
INSERT INTO `rbac_userrole` VALUES (209, 13, 15, 999, 1, '2025-02-26 12:33:58', '2025-02-26 12:33:58');
INSERT INTO `rbac_userrole` VALUES (210, 13, 8, 1, 0, '2025-02-26 12:34:28', '2025-02-26 12:34:28');

-- ----------------------------
-- Table structure for rental_cars
-- ----------------------------
DROP TABLE IF EXISTS `rental_cars`;
CREATE TABLE `rental_cars`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '型号',
  `brand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '品牌',
  `price` int(10) NULL DEFAULT NULL COMMENT '每天价格（分）',
  `availability` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '可用性',
  `vehicleModel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车型',
  `endurance` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '续航（km）',
  `photosUrl` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '轮播图  多张，分隔',
  `glbFileUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '3D模型链接',
  `lng` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '经度',
  `lat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '纬度',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '0待租赁 1已租赁',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '0未删除 1已删除',
  `createTime` datetime NULL DEFAULT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  `classify` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `width` int(11) NULL DEFAULT 30,
  `height` int(11) NULL DEFAULT 30,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rental_cars
-- ----------------------------
INSERT INTO `rental_cars` VALUES (14, '宝马7系', '宝马', 100000, NULL, '七座', '2000', 'http://192.168.104.251:8082/upload/93538b9f-f2c7-4298-9907-48df5d31c5a2.jpg,http://192.168.104.251:8082/upload/6484d1f9-6747-4fe2-87c7-ff2f220409d9.jpg,http://192.168.104.251:8082/upload/9af7b538-2b48-409d-8a18-f0c79ec3ae78.jpg', 'http://192.168.104.251:8082/upload/4f10f7ef-6827-4898-baf7-c1a0a3fa59aa.glb', '117.0242004', '36.7120765', '1', 0, '2025-02-28 13:25:37', '2025-03-01 11:39:26', '新能源一', NULL, 30, 30, '测试2');
INSERT INTO `rental_cars` VALUES (15, '奔驰E300', '奔驰', 300000, NULL, '六座', '2500', 'http://192.168.104.251:8082/upload/c2feaabe-a912-450a-b9c1-7343d052ccf2.png,http://192.168.104.251:8082/upload/c8d9c669-1b47-479c-b55c-378342ed8bb0.jpg', 'http://192.168.104.251:8082/upload/4f10f7ef-6827-4898-baf7-c1a0a3fa59aa.glb', '116.9359509', '36.6604458', '1', 0, '2025-02-28 13:28:07', '2025-03-01 11:42:30', '新能源二', NULL, 30, 30, '测试3');
INSERT INTO `rental_cars` VALUES (16, '特斯拉mode3', '特斯拉mode', 150000, NULL, '五座', '1200', 'http://192.168.104.251:8082/upload/897b3659-94e1-4259-ab09-cd0a5afc3f70.jpg,http://192.168.104.251:8082/upload/73e67745-bc15-4f35-8abe-aaebc254e364.jpeg', 'http://192.168.104.251:8082/upload/4f10f7ef-6827-4898-baf7-c1a0a3fa59aa.glb', '117.0046533', '36.6460848', '0', 0, '2025-02-28 13:28:56', '2025-02-28 13:28:56', '新能源三', NULL, 30, 30, '测试4');

-- ----------------------------
-- Table structure for rental_orders
-- ----------------------------
DROP TABLE IF EXISTS `rental_orders`;
CREATE TABLE `rental_orders`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `userId` int(12) NULL DEFAULT NULL COMMENT '用户ID',
  `carId` int(12) NULL DEFAULT NULL COMMENT '车辆ID',
  `totalMoney` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单价格 分',
  `payType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付方式  alipay：支付宝  wxpay：微信 unionpay：银联',
  `rentalStartTime` datetime NULL DEFAULT NULL COMMENT '租赁开始时间',
  `rentalEndTime` datetime NULL DEFAULT NULL COMMENT '租赁结束时间',
  `returnlat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `rentalStatus` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '0租赁中  1已还车',
  `returnlng` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `payStatus` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '0待支付  1支付中  2支付成功',
  `pdfUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `deleted` int(1) NULL DEFAULT 0 COMMENT '0未删除  1已删除',
  `createTime` datetime NULL DEFAULT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rental_orders
-- ----------------------------
INSERT INTO `rental_orders` VALUES (12, 4, 14, '7320000', 'alipay', '2015-01-01 00:00:00', '2016-01-01 00:00:00', NULL, '0', NULL, '1', 'http://192.168.104.251:8082/upload/4b13594f-e452-499b-9b63-363f05c153b4.pdf', 0, '2025-02-24 12:45:45', '2025-02-24 12:45:45');
INSERT INTO `rental_orders` VALUES (13, 4, 14, '366000', 'alipay', '2015-01-01 00:00:00', '2016-01-01 00:00:00', NULL, '0', NULL, '1', 'http://192.168.104.251:8082/upload/c08cb544-d238-4ce3-a658-8fd6697eaddb.pdf', 0, '2025-02-28 16:01:10', '2025-02-28 16:01:10');
INSERT INTO `rental_orders` VALUES (14, 4, 14, '3200000', 'alipay', '2015-01-01 00:00:00', '2015-02-01 00:00:00', NULL, '0', NULL, '1', 'http://192.168.104.251:8082/upload/042761f7-dcd8-4fef-8c17-4a9d26f24200.pdf', 0, '2025-03-01 11:39:26', '2025-03-01 11:39:26');
INSERT INTO `rental_orders` VALUES (15, 4, 15, '600000', 'wxpay', '2015-01-02 00:00:00', '2015-01-03 00:00:00', NULL, '0', NULL, '1', 'http://192.168.104.251:8082/upload/f2e193e4-4022-49c6-bd5c-2fd9bdf7c2fd.pdf', 0, '2025-03-01 11:42:30', '2025-03-01 11:42:30');

-- ----------------------------
-- Table structure for trace_lng_lat
-- ----------------------------
DROP TABLE IF EXISTS `trace_lng_lat`;
CREATE TABLE `trace_lng_lat`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `lng` decimal(9, 6) NULL DEFAULT NULL COMMENT '经度',
  `lat` decimal(9, 6) NULL DEFAULT NULL COMMENT '纬度',
  `orderId` int(12) NULL DEFAULT NULL COMMENT '租赁订单ID',
  `carId` int(12) NULL DEFAULT NULL COMMENT '车辆ID',
  `createTime` datetime NULL DEFAULT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of trace_lng_lat
-- ----------------------------
INSERT INTO `trace_lng_lat` VALUES (4, 116.397428, 39.909230, 12, 13, NULL, NULL);
INSERT INTO `trace_lng_lat` VALUES (5, 116.407428, 39.919230, 12, 13, NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `loginName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `enabled` int(11) NULL DEFAULT 1 COMMENT '是否可用 1 可用   0不可',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `photoUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '15614679306', '213545313221', 'sZD6oZlCQDqcOeC22OM2iA==', 1, '2025-01-23 09:35:00', '2025-02-26 13:50:18', 'http://localhost:8082/upload/40aaabe5-cdcf-4575-973e-8815d4a6f10b.jpeg');
INSERT INTO `user` VALUES (3, '15614679308', '测试2', 'wFrRFsK2G1x0+dNC6n3w1A==', 0, '2025-02-04 16:02:17', '2025-02-04 16:05:25', 'http://localhost:8082/upload/bcc37d1e-1c62-41e9-990c-d17ec2a6f9d9.jpeg');
INSERT INTO `user` VALUES (9, '15614648541', '测试老师', 'sZD6oZlCQDqcOeC22OM2iA==', 1, '2025-02-11 12:28:43', '2025-02-11 12:42:17', 'http://localhost:8082/upload/2e7a632f-8d31-47db-bc15-0b92f5de8766.jpg');
INSERT INTO `user` VALUES (10, '15614648542', '测试学生2', 'sZD6oZlCQDqcOeC22OM2iA==', 1, '2025-02-11 12:31:56', '2025-02-11 12:40:16', 'http://localhost:8082/upload/1f878292-de03-4fba-a5a2-0bf4143badc7.jpg');
INSERT INTO `user` VALUES (12, '15646545245', '测试企业', 'sZD6oZlCQDqcOeC22OM2iA==', 1, '2025-02-16 09:59:34', '2025-02-16 10:03:33', 'http://localhost:8082/upload/8bf4255f-e3a2-4f34-aaa7-9bfd3da57df9.jpeg');
INSERT INTO `user` VALUES (13, 'Yanyu', 'Yanyu031223', 'Eo/LNUjJ9P/syM/fYkBtAQ==', 1, '2025-02-26 12:33:58', '2025-02-26 12:33:58', NULL);

-- ----------------------------
-- Table structure for weappuser
-- ----------------------------
DROP TABLE IF EXISTS `weappuser`;
CREATE TABLE `weappuser`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `imageUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否有效',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `idCardUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `dlCardUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `audit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '0待审核   1审核通过 2审核失败',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of weappuser
-- ----------------------------
INSERT INTO `weappuser` VALUES (4, '15614679306', '123456', '普通用户L', 'http://192.168.104.251:8082/upload/27140f95-f893-4c47-8ad1-6366af1493fd.jpg', 0, '2024-10-20 11:33:20', '2025-03-01 11:40:05', 'http://192.168.104.251:8082/upload/d151dbd1-e7ed-4356-9073-5ef37679f3be.jpeg', 'http://192.168.104.251:8082/upload/d151dbd1-e7ed-4356-9073-5ef37679f3be.jpeg', '313089026@qq.com', '1');

SET FOREIGN_KEY_CHECKS = 1;

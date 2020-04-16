/*
 Navicat Premium Data Transfer

 Source Server         : MYSQLXJF
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : jigou_data

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 02/03/2020 20:27:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for export
-- ----------------------------
DROP TABLE IF EXISTS `export`;
CREATE TABLE `export`  (
  `id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `exportCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '导出主题英文名',
  `exportName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '导出主题中文名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '导出主题表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of export
-- ----------------------------
INSERT INTO `export` VALUES (1, 'institution_info', '机构基本信息');
INSERT INTO `export` VALUES (2, 'institution_domain', '机构认可领域');
INSERT INTO `export` VALUES (3, 'institution_test', '机构认可领域测试标准');

-- ----------------------------
-- Table structure for export_field
-- ----------------------------
DROP TABLE IF EXISTS `export_field`;
CREATE TABLE `export_field`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `exportId` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '导出主表ID',
  `fieldCode` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段英文名',
  `fieldName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段中文名',
  `sort` int(11) UNSIGNED NULL DEFAULT 1 COMMENT '排序字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '导出字段表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of export_field
-- ----------------------------
INSERT INTO `export_field` VALUES (1, 1, 'id', '序号', 1);
INSERT INTO `export_field` VALUES (2, 1, 'name', '机构名称', 1);
INSERT INTO `export_field` VALUES (3, 1, 'rnumber', '注册编号', 1);
INSERT INTO `export_field` VALUES (4, 1, 'oname', '报告/证书允许使用认可标识的其他名称', 1);
INSERT INTO `export_field` VALUES (5, 1, 'cperson', '联系人', 1);
INSERT INTO `export_field` VALUES (6, 1, 'cnumber', '联系电话', 1);
INSERT INTO `export_field` VALUES (7, 1, 'pcode', '邮政编码', 1);
INSERT INTO `export_field` VALUES (8, 1, 'fnumber', '传真号码', 1);
INSERT INTO `export_field` VALUES (9, 1, 'weburl', '网站地址', 1);
INSERT INTO `export_field` VALUES (10, 1, 'email', '电子邮件', 1);
INSERT INTO `export_field` VALUES (11, 1, 'address', '单位地址', 1);
INSERT INTO `export_field` VALUES (12, 1, 'start', '认证开始时间', 1);
INSERT INTO `export_field` VALUES (13, 1, 'end', '认证结束时间', 1);
INSERT INTO `export_field` VALUES (14, 1, 'abasis', '认可依据', 1);
INSERT INTO `export_field` VALUES (15, 1, 'parameter', '暂停项目参数', 1);
INSERT INTO `export_field` VALUES (16, 1, 'baseinfoid', '机构结构化数据id', 1);
INSERT INTO `export_field` VALUES (17, 2, 'id', '序号', 1);
INSERT INTO `export_field` VALUES (18, 2, 'iname', '机构名称', 1);
INSERT INTO `export_field` VALUES (19, 2, 'vperiod', '有效期限', 1);
INSERT INTO `export_field` VALUES (20, 2, 'name', '姓名', 1);
INSERT INTO `export_field` VALUES (21, 2, 'content', '评估说明', 1);
INSERT INTO `export_field` VALUES (22, 2, 'domain', '授权签字人领域', 1);
INSERT INTO `export_field` VALUES (23, 2, 'description', '说明', 1);
INSERT INTO `export_field` VALUES (24, 2, 'status', '状态', 1);
INSERT INTO `export_field` VALUES (25, 3, 'id', '序号', 1);
INSERT INTO `export_field` VALUES (26, 3, 'iname', '机构名称', 1);
INSERT INTO `export_field` VALUES (27, 3, 'vperiod', '认可有效期限', 1);
INSERT INTO `export_field` VALUES (28, 3, 'bigtypename', '大分组名称', 1);
INSERT INTO `export_field` VALUES (29, 3, 'typename', '分组名称', 1);
INSERT INTO `export_field` VALUES (30, 3, 'num', '检验对象序号', 1);
INSERT INTO `export_field` VALUES (31, 3, 'fieldch', '检验对象', 1);
INSERT INTO `export_field` VALUES (32, 3, 'detnum', '检验项目名称序号', 1);
INSERT INTO `export_field` VALUES (33, 3, 'descriptch', '检验项目名称', 1);
INSERT INTO `export_field` VALUES (34, 3, 'stdnum', '依据的检验标准/方法程序及编号序号', 1);
INSERT INTO `export_field` VALUES (35, 3, 'standardchorder', '说明依据的检测标准/方法及编号', 1);
INSERT INTO `export_field` VALUES (36, 3, 'restrictch', '说明', 1);
INSERT INTO `export_field` VALUES (37, 3, 'status', '状态', 1);

-- ----------------------------
-- Table structure for institution_domain
-- ----------------------------
DROP TABLE IF EXISTS `institution_domain`;
CREATE TABLE `institution_domain`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `iname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构名称',
  `vperiod` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '有效期限',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评估说明',
  `domain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权签字领域',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3256 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of institution_domain
-- ----------------------------
略
-- ----------------------------
-- Table structure for institution_info
-- ----------------------------
DROP TABLE IF EXISTS `institution_info`;
CREATE TABLE `institution_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构名称',
  `rnumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册编号',
  `oname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报告/证书允许使用认可标识的其他名称',
  `cperson` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `cnumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `pcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮政编码',
  `fnumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '传真号码',
  `weburl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网站地址',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位地址',
  `start` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '认证开始时间',
  `end` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '认证结束时间',
  `abasis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '认可依据',
  `parameter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '暂停项目/参数',
  `baseinfoid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构结构化数据id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 747 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '机构基本信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of institution_info
-- ----------------------------
略
-- ----------------------------
-- Table structure for institution_test
-- ----------------------------
DROP TABLE IF EXISTS `institution_test`;
CREATE TABLE `institution_test`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `iname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构名称',
  `vperiod` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '认可有效期限',
  `bigtypename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '大分组名称',
  `typename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分组名称',
  `num` int(20) NULL DEFAULT NULL COMMENT '检验对象序号',
  `fieldch` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检验对象',
  `detnum` int(20) NULL DEFAULT NULL COMMENT '检验项目名称序号',
  `descriptch` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检验项目名称',
  `stdnum` int(20) NULL DEFAULT NULL COMMENT '依据的检验标准/方法程序及编号序号',
  `standardchorder` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '依据的检验标准/方法程序及编号',
  `restrictch` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21632 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of institution_test
-- ----------------------------
略
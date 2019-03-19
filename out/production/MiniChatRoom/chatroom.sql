/*
 Navicat Premium Data Transfer

 Source Server         : Rothyu
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : chatroom

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 31/12/2018 12:12:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `log_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `log_message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('2018-12-29 21:04:28', 'rothyu 上线了！');
INSERT INTO `log` VALUES ('2018-12-29 21:05:05', 'rothyu 下线了！');
INSERT INTO `log` VALUES ('2018-12-29 21:05:38', 'rothyu 上线了！');
INSERT INTO `log` VALUES ('2018-12-29 21:05:49', '花衣魔笛手 上线了！');
INSERT INTO `log` VALUES ('2018-12-29 21:06:32', '花衣魔笛手 talk to rothyu');
INSERT INTO `log` VALUES ('2018-12-29 21:06:51', 'rothyu talk to 花衣魔笛手');
INSERT INTO `log` VALUES ('2018-12-29 21:07:05', '花衣魔笛手 下线了！');
INSERT INTO `log` VALUES ('2018-12-29 21:07:11', 'rothyu 下线了！');
INSERT INTO `log` VALUES ('2018-12-29 21:15:22', 'Mini聊天室服务器已开启！');
INSERT INTO `log` VALUES ('2018-12-29 21:15:34', 'rothyu 上线了！');
INSERT INTO `log` VALUES ('2018-12-29 21:15:52', '花衣魔笛手 上线了！');
INSERT INTO `log` VALUES ('2018-12-29 21:16:26', '花衣魔笛手 talk to rothyu');
INSERT INTO `log` VALUES ('2018-12-29 21:16:49', 'rothyu talk to 花衣魔笛手');
INSERT INTO `log` VALUES ('2018-12-29 21:16:58', '花衣魔笛手 下线了！');
INSERT INTO `log` VALUES ('2018-12-29 21:17:23', 'rothyu 下线了！');
INSERT INTO `log` VALUES ('2018-12-29 21:17:55', 'Mini聊天室服务器已关闭！');
INSERT INTO `log` VALUES ('2018-12-29 21:22:00', 'Mini聊天室服务器已开启！');
INSERT INTO `log` VALUES ('2018-12-29 21:22:36', 'Mini聊天室服务器已关闭！');
INSERT INTO `log` VALUES ('2018-12-30 00:09:28', 'Mini聊天室服务器已开启！');
INSERT INTO `log` VALUES ('2018-12-30 00:10:42', 'Mini聊天室服务器已关闭！');
INSERT INTO `log` VALUES ('2018-12-30 10:09:53', 'Mini聊天室服务器已开启！');
INSERT INTO `log` VALUES ('2018-12-30 10:10:42', 'rothyu 上线了！');
INSERT INTO `log` VALUES ('2018-12-30 10:11:24', '花衣魔笛手 上线了！');
INSERT INTO `log` VALUES ('2018-12-30 10:11:52', '花衣魔笛手 talk to rothyu');
INSERT INTO `log` VALUES ('2018-12-30 10:12:00', 'rothyu talk to 花衣魔笛手');
INSERT INTO `log` VALUES ('2018-12-30 10:13:01', '花衣魔笛手 下线了！');
INSERT INTO `log` VALUES ('2018-12-30 10:13:09', 'rothyu 下线了！');
INSERT INTO `log` VALUES ('2018-12-30 10:13:17', 'Mini聊天室服务器已关闭！');
INSERT INTO `log` VALUES ('2018-12-30 10:22:17', 'Mini聊天室服务器已开启！');
INSERT INTO `log` VALUES ('2018-12-30 10:22:31', 'rothyu 上线了！');
INSERT INTO `log` VALUES ('2018-12-30 10:23:00', 'rothyu 下线了！');
INSERT INTO `log` VALUES ('2018-12-30 10:25:00', 'MySuperSoul 上线了！');
INSERT INTO `log` VALUES ('2018-12-30 10:25:50', 'MySuperSoul 下线了！');
INSERT INTO `log` VALUES ('2018-12-30 10:26:08', 'Mini聊天室服务器已关闭！');
INSERT INTO `log` VALUES ('2018-12-30 11:09:17', 'Mini聊天室服务器已开启！');
INSERT INTO `log` VALUES ('2018-12-30 11:15:31', 'rothyu 上线了！');
INSERT INTO `log` VALUES ('2018-12-30 11:16:37', 'rothyu 下线了！');
INSERT INTO `log` VALUES ('2018-12-30 11:16:46', 'Mini聊天室服务器已关闭！');
INSERT INTO `log` VALUES ('2018-12-30 11:17:21', 'Mini聊天室服务器已开启！');
INSERT INTO `log` VALUES ('2018-12-30 11:17:37', 'rothyu 上线了！');
INSERT INTO `log` VALUES ('2018-12-30 11:20:43', 'rothyu 下线了！');
INSERT INTO `log` VALUES ('2018-12-30 11:20:47', 'Mini聊天室服务器已关闭！');
INSERT INTO `log` VALUES ('2018-12-30 11:20:58', 'Mini聊天室服务器已开启！');
INSERT INTO `log` VALUES ('2018-12-30 11:21:10', 'rothyu 上线了！');
INSERT INTO `log` VALUES ('2018-12-30 11:21:25', 'rothyu 修改密码！');
INSERT INTO `log` VALUES ('2018-12-30 11:22:49', 'rothyu 下线了！');
INSERT INTO `log` VALUES ('2018-12-30 11:23:08', 'Mini聊天室服务器已关闭！');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `send_side` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `recv_side` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  INDEX `send_side`(`send_side`) USING BTREE,
  INDEX `recv_side`(`recv_side`) USING BTREE,
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`send_side`) REFERENCES `user` (`account`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`recv_side`) REFERENCES `user` (`account`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1144358493', '1144358492', '2018-12-29 10:18:57', '我： 2018-12-29 10:18:57\nΣ(￣д￣；)你！！\n\n');
INSERT INTO `message` VALUES ('1144358492', '1144358493', '2018-12-29 10:18:57', '花衣魔笛手对你说： 2018-12-29 10:18:57\nΣ(￣д￣；)你！！\n\n');
INSERT INTO `message` VALUES ('1144358492', '1144358493', '2018-12-29 10:19:36', '我： 2018-12-29 10:19:36\n蛤，你想干啥\n\n');
INSERT INTO `message` VALUES ('1144358493', '1144358492', '2018-12-29 10:19:36', 'rothyu对你说： 2018-12-29 10:19:36\n蛤，你想干啥\n\n');
INSERT INTO `message` VALUES ('1144358493', '1144358492', '2018-12-29 10:20:03', 'rothyu对你说： 2018-12-29 10:20:03\n我跟你讲，这样是不行滴\n\n');
INSERT INTO `message` VALUES ('1144358492', '1144358493', '2018-12-29 10:20:03', '我： 2018-12-29 10:20:03\n我跟你讲，这样是不行滴\n\n');
INSERT INTO `message` VALUES ('1144358493', '1144358492', '2018-12-29 10:20:20', '我： 2018-12-29 10:20:20\n╭(╯^╰)╮，我要去洗漱了\n\n');
INSERT INTO `message` VALUES ('1144358492', '1144358493', '2018-12-29 10:20:20', '花衣魔笛手对你说： 2018-12-29 10:20:20\n╭(╯^╰)╮，我要去洗漱了\n\n');
INSERT INTO `message` VALUES ('1144358492', '1144358493', '2018-12-29 10:20:26', '花衣魔笛手对你说： 2018-12-29 10:20:26\n拜拜\n\n');
INSERT INTO `message` VALUES ('1144358493', '1144358492', '2018-12-29 10:20:26', '我： 2018-12-29 10:20:26\n拜拜\n\n');
INSERT INTO `message` VALUES ('1144358493', '1144358492', '2018-12-29 10:20:33', 'rothyu对你说： 2018-12-29 10:20:33\n白白~~~\n\n');
INSERT INTO `message` VALUES ('1144358492', '1144358493', '2018-12-29 10:20:33', '我： 2018-12-29 10:20:33\n白白~~~\n\n');
INSERT INTO `message` VALUES ('1144358493', '1144358492', '2018-12-29 15:27:23', '我： 2018-12-29 15:27:23\n现在我可以继续聊天了，你呢？\n\n');
INSERT INTO `message` VALUES ('1144358492', '1144358493', '2018-12-29 15:27:23', '花衣魔笛手对你说： 2018-12-29 15:27:23\n现在我可以继续聊天了，你呢？\n\n');
INSERT INTO `message` VALUES ('1144358492', '1144358493', '2018-12-29 15:30:15', '我： 2018-12-29 15:30:15\n啊，怎么排序呢？？！！\n\n');
INSERT INTO `message` VALUES ('1144358493', '1144358492', '2018-12-29 15:30:15', 'rothyu对你说： 2018-12-29 15:30:15\n啊，怎么排序呢？？！！\n\n');
INSERT INTO `message` VALUES ('1144358493', '1144358492', '2018-12-29 16:34:23', '我： 2018-12-29 16:34:23\n不知道啊\n\n');
INSERT INTO `message` VALUES ('1144358492', '1144358493', '2018-12-29 16:34:23', '花衣魔笛手对你说： 2018-12-29 16:34:23\n不知道啊\n\n');
INSERT INTO `message` VALUES ('1144358492', '1144358493', '2018-12-29 16:45:02', '我： 2018-12-29 16:45:02\n你好\n\n');
INSERT INTO `message` VALUES ('1144358493', '1144358492', '2018-12-29 16:45:02', 'rothyu对你说： 2018-12-29 16:45:02\n你好\n\n');
INSERT INTO `message` VALUES ('88888888', '1144358493', '2018-12-29 16:58:07', '我： 2018-12-29 16:58:07\n你好花衣魔笛手，我是刺客~\n\n');
INSERT INTO `message` VALUES ('1144358493', '88888888', '2018-12-29 16:58:07', '刺客对你说： 2018-12-29 16:58:07\n你好花衣魔笛手，我是刺客~\n\n');
INSERT INTO `message` VALUES ('1144358492', '88888888', '2018-12-29 20:07:16', '我： 2018-12-29 20:07:16\n你好，我是Roth\n\n');
INSERT INTO `message` VALUES ('88888888', '1144358492', '2018-12-29 20:07:16', 'rothyu对你说： 2018-12-29 20:07:16\n你好，我是Roth\n\n');
INSERT INTO `message` VALUES ('88888888', '1144358492', '2018-12-29 20:07:39', '我： 2018-12-29 20:07:39\n你好啊，我是刺客\n\n');
INSERT INTO `message` VALUES ('1144358492', '88888888', '2018-12-29 20:07:39', '刺客对你说： 2018-12-29 20:07:39\n你好啊，我是刺客\n\n');
INSERT INTO `message` VALUES ('1144358493', '66666666', '2018-12-29 20:10:21', '我： 2018-12-29 20:10:21\n今天晚上好冷啊\n\n');
INSERT INTO `message` VALUES ('66666666', '1144358493', '2018-12-29 20:10:21', '花衣魔笛手对你说： 2018-12-29 20:10:21\n今天晚上好冷啊\n\n');
INSERT INTO `message` VALUES ('1144358493', '66666666', '2018-12-29 20:10:42', '我： 2018-12-29 20:10:42\n多穿点衣服\n\n');
INSERT INTO `message` VALUES ('66666666', '1144358493', '2018-12-29 20:10:42', '花衣魔笛手对你说： 2018-12-29 20:10:42\n多穿点衣服\n\n');
INSERT INTO `message` VALUES ('1144358493', '88888888', '2018-12-29 20:15:14', '我： 2018-12-29 20:15:14\n啊，刚刚没看到，sorry~\n\n');
INSERT INTO `message` VALUES ('88888888', '1144358493', '2018-12-29 20:15:14', '花衣魔笛手对你说： 2018-12-29 20:15:14\n啊，刚刚没看到，sorry~\n\n');
INSERT INTO `message` VALUES ('88888888', '1144358493', '2018-12-29 20:15:35', '我： 2018-12-29 20:15:35\n没事，我常年在线的啊红红火火\n\n');
INSERT INTO `message` VALUES ('1144358493', '88888888', '2018-12-29 20:15:35', '刺客对你说： 2018-12-29 20:15:35\n没事，我常年在线的啊红红火火\n\n');
INSERT INTO `message` VALUES ('1144358492', '66666666', '2018-12-29 20:18:40', '我： 2018-12-29 20:18:40\n我好像还没和你说过话哎\n\n');
INSERT INTO `message` VALUES ('66666666', '1144358492', '2018-12-29 20:18:40', 'rothyu对你说： 2018-12-29 20:18:40\n我好像还没和你说过话哎\n\n');
INSERT INTO `message` VALUES ('66666666', '1144358492', '2018-12-29 20:18:52', '我： 2018-12-29 20:18:52\n太真实了\n\n');
INSERT INTO `message` VALUES ('1144358492', '66666666', '2018-12-29 20:18:52', 'MySuperSoul对你说： 2018-12-29 20:18:52\n太真实了\n\n');
INSERT INTO `message` VALUES ('1144358492', '66666666', '2018-12-29 20:19:11', '我： 2018-12-29 20:19:11\n卧槽，所以你是谁呢\n\n');
INSERT INTO `message` VALUES ('66666666', '1144358492', '2018-12-29 20:19:11', 'rothyu对你说： 2018-12-29 20:19:11\n卧槽，所以你是谁呢\n\n');
INSERT INTO `message` VALUES ('66666666', '1144358492', '2018-12-29 20:19:29', '我： 2018-12-29 20:19:29\n不说了，写作业去了\n\n');
INSERT INTO `message` VALUES ('1144358492', '66666666', '2018-12-29 20:19:29', 'MySuperSoul对你说： 2018-12-29 20:19:29\n不说了，写作业去了\n\n');
INSERT INTO `message` VALUES ('66666666', '1144358493', '2018-12-29 20:20:21', '我： 2018-12-29 20:20:21\n嗯，知道了\n\n');
INSERT INTO `message` VALUES ('1144358493', '66666666', '2018-12-29 20:20:21', 'MySuperSoul对你说： 2018-12-29 20:20:21\n嗯，知道了\n\n');
INSERT INTO `message` VALUES ('1144358493', '1144358492', '2018-12-29 21:06:32', '我： 2018-12-29 21:06:32\n为什么不回我啊？？！！\n\n');
INSERT INTO `message` VALUES ('1144358492', '1144358493', '2018-12-29 21:06:32', '花衣魔笛手对你说： 2018-12-29 21:06:32\n为什么不回我啊？？！！\n\n');
INSERT INTO `message` VALUES ('1144358493', '1144358492', '2018-12-29 21:06:51', 'rothyu对你说： 2018-12-29 21:06:51\n啊，我没看到嘤嘤嘤\n\n');
INSERT INTO `message` VALUES ('1144358492', '1144358493', '2018-12-29 21:06:51', '我： 2018-12-29 21:06:51\n啊，我没看到嘤嘤嘤\n\n');
INSERT INTO `message` VALUES ('1144358493', '1144358492', '2018-12-29 21:16:26', '我： 2018-12-29 21:16:26\n刚刚洗澡去了，哎，怎么就快期末了呢\n\n');
INSERT INTO `message` VALUES ('1144358492', '1144358493', '2018-12-29 21:16:26', '花衣魔笛手对你说： 2018-12-29 21:16:26\n刚刚洗澡去了，哎，怎么就快期末了呢\n\n');
INSERT INTO `message` VALUES ('1144358493', '1144358492', '2018-12-29 21:16:49', 'rothyu对你说： 2018-12-29 21:16:49\n快睡吧！熬夜伤身体\n\n');
INSERT INTO `message` VALUES ('1144358492', '1144358493', '2018-12-29 21:16:49', '我： 2018-12-29 21:16:49\n快睡吧！熬夜伤身体\n\n');
INSERT INTO `message` VALUES ('1144358492', '1144358493', '2018-12-30 10:11:52', '花衣魔笛手对你说： 2018-12-30 10:11:52\n新的一天开始了\n\n');
INSERT INTO `message` VALUES ('1144358493', '1144358492', '2018-12-30 10:11:52', '我： 2018-12-30 10:11:52\n新的一天开始了\n\n');
INSERT INTO `message` VALUES ('1144358493', '1144358492', '2018-12-30 10:12:00', 'rothyu对你说： 2018-12-30 10:12:00\n是啊是啊\n\n');
INSERT INTO `message` VALUES ('1144358492', '1144358493', '2018-12-30 10:12:00', '我： 2018-12-30 10:12:00\n是啊是啊\n\n');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` int(11) NOT NULL,
  `isonline` int(255) NOT NULL DEFAULT 0,
  PRIMARY KEY (`account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1144358492', '25f9e794323b453885f5181f1b624d0b', 'rothyu', '17367078037', 'I can love', '男', 19, 0);
INSERT INTO `user` VALUES ('1144358493', '827ccb0eea8a706c4c34a16891f84e7b', '花衣魔笛手', '17367078037', 'smart insert', '男', 18, 0);
INSERT INTO `user` VALUES ('66666666', '827ccb0eea8a706c4c34a16891f84e7b', 'MySuperSoul', '13807947765', '我来自github', '男', 16, 0);
INSERT INTO `user` VALUES ('88888888', '827ccb0eea8a706c4c34a16891f84e7b', '刺客', '17367078037', '我是一枚刺客', '男', 13, 0);

SET FOREIGN_KEY_CHECKS = 1;

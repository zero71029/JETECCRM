-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- 主機： localhost:3306
-- 產生時間： 2021-10-05 08:28:27
-- 伺服器版本： 5.7.24
-- PHP 版本： 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `crm`
--

-- --------------------------------------------------------

--
-- 資料表結構 `admin`
--

CREATE TABLE `admin` (
  `adminid` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_bin NOT NULL,
  `phone` varchar(20) COLLATE utf8_bin NOT NULL,
  `email` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(20) COLLATE utf8_bin NOT NULL,
  `state` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `position` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '職位',
  `create_data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `department` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '部門',
  `director` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '主管'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 傾印資料表的資料 `admin`
--

INSERT INTO `admin` (`adminid`, `name`, `phone`, `email`, `address`, `password`, `state`, `position`, `create_data`, `department`, `director`) VALUES
(1, '灰灰', '0926123456', 'AAA@AAA.com', 'BBB', 'AAA', '在職', '職員', '2021-10-05 02:46:30', '', ''),
(2, '久德女神', '023729418', 'BBB@AAA.com', '福人街11號', 'aaa', '在職', '美工', '2021-10-05 02:31:47', '行銷部', 'ssss');

-- --------------------------------------------------------

--
-- 資料表結構 `agreement`
--

CREATE TABLE `agreement` (
  `agreementid` int(21) NOT NULL,
  `company` varchar(20) COLLATE utf8_bin NOT NULL,
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `fax` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `contactname` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `contactmoblie` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `contactjobtitle` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `city` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `postal` varchar(20) COLLATE utf8_bin NOT NULL,
  `address` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `specialterms` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '特殊條款',
  `agreementdescribe` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `agreementname` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '客戶簽約人',
  `agreementjobtitle` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT ' 客戶簽約人職稱',
  `agreementtime` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '客戶簽約日期',
  `companyname` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '公司簽約人',
  `companyjobtitle` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT ' 公司簽約人職稱',
  `companytime` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '公司簽約日期',
  `user` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '負責人',
  `createtime` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `endtime` varchar(20) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 傾印資料表的資料 `agreement`
--

INSERT INTO `agreement` (`agreementid`, `company`, `phone`, `email`, `fax`, `contactname`, `contactmoblie`, `contactjobtitle`, `city`, `postal`, `address`, `specialterms`, `agreementdescribe`, `agreementname`, `agreementjobtitle`, `agreementtime`, `companyname`, `companyjobtitle`, `companytime`, `user`, `createtime`, `endtime`) VALUES
(1, '台積電', '023729418', 'AAA@AAA.com', 'xxxxx', '', '22222222', '處長x', '臺中市', '400', 'dqwd', 'qqqqqqqqqqqqq', 'wwwwwwwwwww', 'rrrr', 'rrrr', '2021-10-04', 'rrrrr', '', '2021-10-04', '灰灰', '2021-10-04', '2021-10-04');

-- --------------------------------------------------------

--
-- 資料表結構 `billboard`
--

CREATE TABLE `billboard` (
  `billboardid` int(21) NOT NULL,
  `user` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '發表人',
  `theme` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '主題',
  `content` varchar(500) COLLATE utf8_bin NOT NULL COMMENT '內容',
  `state` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '狀態',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '創造時間',
  `endtime` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '結束時間'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 傾印資料表的資料 `billboard`
--

INSERT INTO `billboard` (`billboardid`, `user`, `theme`, `content`, `state`, `createtime`, `endtime`) VALUES
(1, '灰灰', '杜甫《登高》', '風急天高猿嘯哀，渚清沙白鳥飛回。\r<br>無邊落木蕭蕭下，不盡長江滾滾來。\r<br>萬里悲秋常作客，百年多病獨登台。\r<br>艱難苦恨繁霜鬢，潦倒新停濁酒杯。', '發佈', '2021-10-05 07:13:43', NULL),
(2, '灰灰', '木蘭花·擬古決絕詞柬友', '人生若只如初見，何事秋風悲畫扇。\r<br>等閒變卻故人心，卻道故人心易變。\r<br>驪山語罷清宵半，淚雨霖鈴終不怨。\r<br>何如薄倖錦衣郎，比翼連枝當日願。\r<br>', '發佈', '2021-10-05 06:49:47', NULL);

-- --------------------------------------------------------

--
-- 資料表結構 `client`
--

CREATE TABLE `client` (
  `clientid` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_bin NOT NULL,
  `sort` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '類別',
  `url` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '網站',
  `industry` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '產業',
  `uniformnumber` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '統一號碼',
  `email` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `fax` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `peoplenumber` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '員工人數',
  `billcity` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '帳單城市',
  `billtown` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '帳單地區',
  `billpostal` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '帳單郵遞區號',
  `billaddress` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '帳單地址',
  `delivercity` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '送貨城市',
  `delivertown` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '送貨地區',
  `deliverpostal` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '送貨郵遞區號',
  `deliveraddress` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '送貨地址',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `user` varchar(10) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 傾印資料表的資料 `client`
--

INSERT INTO `client` (`clientid`, `name`, `sort`, `url`, `industry`, `uniformnumber`, `email`, `phone`, `fax`, `peoplenumber`, `billcity`, `billtown`, `billpostal`, `billaddress`, `delivercity`, `delivertown`, `deliverpostal`, `deliveraddress`, `remark`, `user`) VALUES
(1, '海貓食屋', '客戶', 'https://wizard71029.synology.me:443/OceanCatHouse/', '食品飲料', '123245678', '', '042123456', '042123658', '', '臺北市', '大安區', '106', '福人街11號', '臺北市', '大安區', '106', 'hhhhhhhh', ' ＿', '無'),
(2, '台積電', '客戶', 'https://www.tsmc.com/chinese', '電子', '12354689', 'tsmc_pepd@tsmc.com', '035636688', 'xxxxxxxxx', '1000', '臺中市', '太平區', '411', '中科路1號', '臺中市', '太平區', '400', '中科路1號', ' 0000', '灰灰');

-- --------------------------------------------------------

--
-- 資料表結構 `contact`
--

CREATE TABLE `contact` (
  `contactid` int(21) NOT NULL,
  `name` varchar(20) COLLATE utf8_bin NOT NULL,
  `clientid` int(21) NOT NULL,
  `company` varchar(20) COLLATE utf8_bin NOT NULL,
  `jobtitle` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '職務',
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'Email',
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '電話',
  `moblie` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '手機',
  `address` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `department` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '部門',
  `director` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '直屬主管',
  `fax` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '傳真',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '備註',
  `user` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `contacttime` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '上次聯絡時間'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 傾印資料表的資料 `contact`
--

INSERT INTO `contact` (`contactid`, `name`, `clientid`, `company`, `jobtitle`, `email`, `phone`, `moblie`, `address`, `department`, `director`, `fax`, `remark`, `user`, `contacttime`) VALUES
(1, '柯宗杰', 2, '台積電', '處長', 'tsmc_mscm@tsmc.com', '0421111111', '0911111111', '中科路1號', '資材供應鏈管理處', 'ssss', 'xxxxxxxxx', '哈哈', '灰灰', '2021-09-30'),
(2, '傅學士', 2, '台積電', '處長x', 'tsmc_pepd@tsmc.com', 'xxxxxxxxx', '22222222', '中科路1號', '資材暨風險管理', 'ssss', 'xxxxxxxxx', '哼哼', '灰灰', '2021-09-30');

-- --------------------------------------------------------

--
-- 資料表結構 `market`
--

CREATE TABLE `market` (
  `marketid` int(20) NOT NULL,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  `user` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '業務',
  `createtime` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `endtime` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `message` varchar(1000) COLLATE utf8_bin NOT NULL COMMENT '描述',
  `cost` int(30) DEFAULT NULL COMMENT '花費',
  `ccc` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
  `client` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '客戶',
  `contactname` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '聯絡人',
  `contactphone` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `contactmoblie` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '類型',
  `source` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '機會來源',
  `clinch` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '成交機率',
  `stage` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '階段',
  `need` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '需求確認',
  `roianalyze` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT 'ROI分析',
  `product` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `producttype` varchar(20) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 傾印資料表的資料 `market`
--

INSERT INTO `market` (`marketid`, `name`, `user`, `createtime`, `endtime`, `message`, `cost`, `ccc`, `client`, `contactname`, `contactphone`, `contactmoblie`, `type`, `source`, `clinch`, `stage`, `need`, `roianalyze`, `product`, `producttype`) VALUES
(1, '採購溫濕度', '灰灰', '2021-09-24', '2021-11-23', ' Yahoo關鍵字                          ', 5000000, '2021-10-01 07:15:49', '台積電', '柯宗杰', '0421111111', '0911111111', '電子業', '自己打來', '80', '尚未處理', 'ee', 'ee', 'xxx', '溫濕-E+E'),
(2, 'Yahoo關鍵字2', '灰灰', '2021-09-23', '2021-11-23', ' Yahoo關鍵字  ', 5000000, '2021-10-01 02:29:44', '台積電', '', '', '', '尚未分類', '自己打來', '', '尚未處理', '', '', '', '尚未分類'),
(9, 'Yahoo關鍵字xxxxx', '灰灰', '2021-09-24', '2021-11-23', ' Yahoo關鍵字           ', 5000000, '2021-10-01 03:45:22', '海貓食屋', '', '', '', '尚未分類', '自己打來', '', '尚未處理', '', '', '', '尚未分類'),
(10, '自動化展', '灰灰', '2021-09-24', '', ' xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx ', 5000000, '2021-09-30 08:36:30', '台積電', NULL, NULL, NULL, '', '自己打來', '', '尚未處理', '', '', NULL, NULL);

-- --------------------------------------------------------

--
-- 資料表結構 `marketremark`
--

CREATE TABLE `marketremark` (
  `id` int(20) NOT NULL,
  `marketid` int(20) NOT NULL,
  `user` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '留言人',
  `remark` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '留言',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 傾印資料表的資料 `marketremark`
--

INSERT INTO `marketremark` (`id`, `marketid`, `user`, `remark`, `createtime`) VALUES
(1, 1, '灰灰', ' 第一天沒反應', '2021-09-24 06:17:54'),
(9, 1, '灰灰', '第2天  300點擊數', '2021-09-24 06:21:25'),
(10, 1, '灰灰', '第3天 1000點擊數', '2021-09-24 07:49:56'),
(11, 10, '灰灰', '123', '2021-09-27 00:45:00'),
(14, 9, '灰灰', 'aaaa', '2021-09-27 03:37:01'),
(15, 9, '灰灰', 'bbbbb', '2021-09-27 03:37:04');

-- --------------------------------------------------------

--
-- 資料表結構 `potentialcustomer`
--

CREATE TABLE `potentialcustomer` (
  `customerid` int(21) NOT NULL,
  `name` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '名稱',
  `company` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '公司',
  `jobtitle` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '職稱',
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `moblie` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '手機號',
  `fax` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '傳真',
  `department` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '部門',
  `director` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '主管',
  `industry` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '產業',
  `companynum` int(21) DEFAULT NULL COMMENT '公司人數',
  `source` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '來源',
  `fromactivity` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '來自活動',
  `user` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '業務',
  `contacttime` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '上次聯絡時間',
  `status` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '狀態',
  `address` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '備註'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- 資料表結構 `quotation`
--

CREATE TABLE `quotation` (
  `quotationid` int(21) NOT NULL COMMENT '報價單',
  `name` varchar(20) COLLATE utf8_bin NOT NULL,
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `fax` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `contactname` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `contactmoblie` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `contactjobtitle` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `user` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 傾印資料表的資料 `quotation`
--

INSERT INTO `quotation` (`quotationid`, `name`, `phone`, `email`, `fax`, `contactname`, `contactmoblie`, `contactjobtitle`, `remark`, `user`, `createtime`) VALUES
(3, '台積電', '023729418', 'AAA@AAA.com', 'xxxxxxxxx', '', '0911111111', '', ' Yahoo關鍵字                          ', '灰灰', '2021-10-05 07:28:44'),
(4, '台積電', '023729418', 'AAA@AAA.com', '2021-10-04', '柯宗杰', '0911111111', '處長', ' Yahoo關鍵字                          ', '灰灰', '2021-10-05 07:59:05');

-- --------------------------------------------------------

--
-- 資料表結構 `quotationdetail`
--

CREATE TABLE `quotationdetail` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `quotationid` int(21) DEFAULT NULL,
  `product` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `producttype` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `price` int(20) DEFAULT NULL,
  `num` int(20) DEFAULT NULL,
  `total` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 傾印資料表的資料 `quotationdetail`
--

INSERT INTO `quotationdetail` (`id`, `quotationid`, `product`, `producttype`, `price`, `num`, `total`) VALUES
('66e0741b2f1f40af8b44678e03ce6555', 4, '444', '444', 444, 444, 444),
('9e83dea0574e40b3a1117eb1492fbf73', 3, '666', 'rrr', 33, 33, 33),
('db2842065c834a14a099cd7311b7fc59', 4, '999', '999', 999, 999, 999),
('f208a9b398cd49b1bf6b4b6264346c7e', 4, '777', '777', 777, 777, 777);

-- --------------------------------------------------------

--
-- 資料表結構 `track`
--

CREATE TABLE `track` (
  `id` int(20) NOT NULL,
  `customerid` int(20) NOT NULL,
  `trackdescribe` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '描述',
  `result` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '結果',
  `remark` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '備註',
  `tracktime` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '時間'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`adminid`);

--
-- 資料表索引 `agreement`
--
ALTER TABLE `agreement`
  ADD PRIMARY KEY (`agreementid`);

--
-- 資料表索引 `billboard`
--
ALTER TABLE `billboard`
  ADD PRIMARY KEY (`billboardid`);

--
-- 資料表索引 `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`clientid`);

--
-- 資料表索引 `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`contactid`);

--
-- 資料表索引 `market`
--
ALTER TABLE `market`
  ADD PRIMARY KEY (`marketid`);

--
-- 資料表索引 `marketremark`
--
ALTER TABLE `marketremark`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `potentialcustomer`
--
ALTER TABLE `potentialcustomer`
  ADD PRIMARY KEY (`customerid`);

--
-- 資料表索引 `quotation`
--
ALTER TABLE `quotation`
  ADD PRIMARY KEY (`quotationid`);

--
-- 資料表索引 `quotationdetail`
--
ALTER TABLE `quotationdetail`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `track`
--
ALTER TABLE `track`
  ADD PRIMARY KEY (`id`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `admin`
--
ALTER TABLE `admin`
  MODIFY `adminid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `agreement`
--
ALTER TABLE `agreement`
  MODIFY `agreementid` int(21) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `billboard`
--
ALTER TABLE `billboard`
  MODIFY `billboardid` int(21) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `client`
--
ALTER TABLE `client`
  MODIFY `clientid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `contact`
--
ALTER TABLE `contact`
  MODIFY `contactid` int(21) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `market`
--
ALTER TABLE `market`
  MODIFY `marketid` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `marketremark`
--
ALTER TABLE `marketremark`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `potentialcustomer`
--
ALTER TABLE `potentialcustomer`
  MODIFY `customerid` int(21) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `quotation`
--
ALTER TABLE `quotation`
  MODIFY `quotationid` int(21) NOT NULL AUTO_INCREMENT COMMENT '報價單', AUTO_INCREMENT=5;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `track`
--
ALTER TABLE `track`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

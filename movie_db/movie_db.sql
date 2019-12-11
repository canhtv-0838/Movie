-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 12, 2019 lúc 12:30 AM
-- Phiên bản máy phục vụ: 10.4.8-MariaDB
-- Phiên bản PHP: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `movie_db`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `id` bigint(20) NOT NULL,
  `username` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `id_user` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`id`, `username`, `password`, `email`, `id_user`) VALUES
(1, 'Account1', '123456', 'account1@movie.com', 1574481351811),
(2, 'Account 2', '123456', 'account2@movie.com', 1574484261908),
(3, 'ac3', 'a', 'ac3@a.com', 1574484500486),
(4, 'a', 'a', 'a', 1574484596482),
(5, '123', '123', '123', 1574485078845),
(6, 'asfasf', 'fas', 'asfasf', 1574485628736),
(7, '123123', '123123', '123123@gmai.com', 1574503254911),
(8, '123123', '12312', '12312@sa.com', 1574503618068),
(9, '', '', '', 0),
(10, '1123123', '123123', '123@mgail.com', 1574506570363),
(11, 'qwer', 'qwer', 'qwer@qwer.com', 1574520768260),
(12, 'Tran Canh', '1234', 'tr@gmail.com', 1574871132086),
(13, 'Tran Canh', '123456', 'trvcanh97@gmail.com', 1575213367503);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `shared_post`
--

CREATE TABLE `shared_post` (
  `id` bigint(20) NOT NULL,
  `id_movie` bigint(20) NOT NULL,
  `title_movie` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `overview_movie` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `poster_path_movie` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `id_user` bigint(20) NOT NULL,
  `content` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `post_time` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `shared_post`
--

INSERT INTO `shared_post` (`id`, `id_movie`, `title_movie`, `overview_movie`, `poster_path_movie`, `id_user`, `content`, `post_time`) VALUES
(21, 330457, 'Frozen II', 'Elsa, Anna, Kristoff and Olaf head far into the forest to learn the truth about an ancient mystery of their kingdom.', '/qdfARIhgpgZOBh3vfNhWS4hmSo3.jpg', 1574481351811, 'Nữ hoàng băng giá 2 là phim điện ảnh hoạt hình máy tính nhạc kịch kỳ ảo của Mỹ sắp ra mắt do xưởng phim Walt Disney Animation Studios sản xuất, đồng thời là phần tiếp theo của bộ phim Nữ hoàng băng giá.', '2019-12-01 21:31:33'),
(24, 290859, 'Terminator: Dark Fate', 'Decades after Sarah Connor prevented Judgment Day, a lethal new Terminator is sent to eliminate the future leader of the resistance. In a fight to save mankind, battle-hardened Sarah Connor teams up with an unexpected ally and an enhanced super soldier to stop the deadliest Terminator yet.', '/vqzNJRH4YyquRiWxCCOH0aXggHI.jpg', 1574481351811, 'Kẻ hủy diệt: Vận mệnh đen tối là phần thứ sáu của thương hiệu phim Kẻ Hủy Diệt được phát hành. Không giống như các phần trước bộ phim được hậu thuẫn sản xuất bởi chính James Cameron sau khi đã giành lại được quyền chủ thương hiệu Terminator đã bán trước đó.', '2019-12-01 21:39:20'),
(26, 475557, 'Joker', 'During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.', '/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg', 1574520768260, 'Me emo', '2019-12-01 21:57:30'),
(27, 238, 'The Godfather', 'Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.', '/rPdtLWNsZmAtoZl9PK7S2wE3qiS.jpg', 1574520768260, '', '2019-12-01 21:58:07'),
(28, 474350, 'It Chapter Two', '27 years after overcoming the malevolent supernatural entity Pennywise, the former members of the Losers Club, who have grown up and moved away from Derry, are brought back together by a devastating phone call.', '/zfE0R94v1E8cuKAerbskfD3VfUt.jpg', 1574481351811, '', '2019-12-01 22:04:32'),
(29, 278, 'The Shawshank Redemption', 'Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.', '/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg', 1575213367503, '', '2019-12-01 22:16:17'),
(30, 540901, 'Hustlers', 'A crew of savvy former strip club employees band together to turn the tables on their Wall Street clients.', '/zBhv8rsLOfpFW2M5b6wW78Uoojs.jpg', 1575213367503, '', '2019-12-01 22:31:22'),
(33, 540901, 'Hustlers', 'A crew of savvy former strip club employees band together to turn the tables on their Wall Street clients.', '/zBhv8rsLOfpFW2M5b6wW78Uoojs.jpg', 1574481351811, 'He he', '2019-12-03 23:36:51'),
(34, 474350, 'It Chapter Two', '27 years after overcoming the malevolent supernatural entity Pennywise, the former members of the Losers Club, who have grown up and moved away from Derry, are brought back together by a devastating phone call.', '/zfE0R94v1E8cuKAerbskfD3VfUt.jpg', 1574520768260, '', '2019-12-04 22:39:29'),
(35, 330457, 'Frozen II', 'Elsa, Anna, Kristoff and Olaf head far into the forest to learn the truth about an ancient mystery of their kingdom.', '/qdfARIhgpgZOBh3vfNhWS4hmSo3.jpg', 1574520768260, '', '2019-12-04 22:39:37'),
(36, 290859, 'Terminator: Dark Fate', 'Decades after Sarah Connor prevented Judgment Day, a lethal new Terminator is sent to eliminate the future leader of the resistance. In a fight to save mankind, battle-hardened Sarah Connor teams up with an unexpected ally and an enhanced super soldier to stop the deadliest Terminator yet.', '/vqzNJRH4YyquRiWxCCOH0aXggHI.jpg', 1574520768260, '', '2019-12-04 22:39:49');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` bigint(11) NOT NULL,
  `fullname` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `birthday` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `gender` tinyint(1) NOT NULL DEFAULT 0,
  `place_of_birth` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `profile_path` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `fullname`, `birthday`, `gender`, `place_of_birth`, `profile_path`) VALUES
(1574481205814, 'Account1', '05/11/2019', 0, 'Hanou', 'ic_profile_female.png'),
(1574481205878, 'Account1', '05/11/2019', 0, 'Hanou', NULL),
(1574481215792, 'Account1', '05/11/2019', 0, 'Hanou', NULL),
(1574481351811, 'Account1', '05/11/2019', 0, 'Hanou', 'ic_profile_male.png'),
(1574484261908, 'Account2', '14/11/2019', 0, 'Hanoi', NULL),
(1574484500486, 'ac3', '23/11/2019', 0, 'ac3', NULL),
(1574484596482, 'q', '23/11/2019', 0, 'a', NULL),
(1574485078845, 'asdfa', '23/11/2019', 0, '23', NULL),
(1574485628736, 'asfa', '23/11/2019', 0, 'safas', NULL),
(1574503254911, '12312', '23/11/2019', 0, '123123', NULL),
(1574503618068, '1231232', '23/11/2019', 0, '21312', NULL),
(1574506570363, 'hehe', '23/11/2019', 0, 'emo12', NULL),
(1574520768260, 'qwer', '23/11/2019', 1, 'qwer', 'ic_profile_female.png'),
(1574871132086, 'Tran Van Canh', '18/06/1997', 1, 'Hanoi Vietnam', 'ic_profile_male.png'),
(1575213367503, 'Canh Tran', '01/12/2004', 0, 'Hanoi', 'ic_profile_male.png');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `shared_post`
--
ALTER TABLE `shared_post`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `account`
--
ALTER TABLE `account`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `shared_post`
--
ALTER TABLE `shared_post`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

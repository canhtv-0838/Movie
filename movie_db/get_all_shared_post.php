<?php
require_once('connection.php');
mysqli_set_charset($con,'utf8');
/** Array for JSON response*/
$response = array();
$resultValue = array();
$querySelectAllSharedPost = "SELECT * FROM shared_post";
$resultSelectAllSharedPost = @mysqli_query($con,$querySelectAllSharedPost);

if ($resultSelectAllSharedPost) {
	while($row = mysqli_fetch_array($resultSelectAllSharedPost)){
		$id = $row['id'];
		$id_movie = $row['id_movie'];
		$title_movie = $row['title_movie'];
		$overview_movie = $row['overview_movie'];
		$poster_path_movie = $row['poster_path_movie'];
		$id_user = $row['id_user'];
		$content = $row['content'];
		$post_time = $row['post_time'];

		$querySelectProfilePathUser = "SELECT fullname, profile_path FROM user WHERE id = '$id_user'";
		$resultSelectProfilePathUser = @mysqli_query($con,$querySelectProfilePathUser);
		if (mysqli_num_rows($resultSelectProfilePathUser)!=0) {
			$arrFetch = mysqli_fetch_array($resultSelectProfilePathUser);
			$fullname = $arrFetch['fullname'];
			$profile_path = $arrFetch['profile_path'];

			array_push($resultValue, new SharedPost($id,$id_user, $fullname, $profile_path,$id_movie,$title_movie, $overview_movie, $poster_path_movie,$content,$post_time));
		} else {
			$response["result_code"] = 0;
			$response["error_code"] = 1;
			$response["data"] = null;
			$response["message"] = "Select profile_path failed";
		}
		$response["result_code"] = 0;
		$response["error_code"] = 0;
		$response["data"] = json_encode($resultValue);
		$response["message"] = "Select SharedPost success";
	}
} else {
	$response["result_code"] = 1;
	$response["error_code"] = 99;
	$response["data"] = null;
	$response["message"] = "GET failed";
}
/**Return json*/
echo json_encode($response);

class SharedPost{
	var $id;
	var $id_user;
	var $fullname;
	var $profile_path_user;
	var $id_movie;
	var $title_movie;
	var $overview_movie;
	var $poster_path_movie;
	var $content;
	var $post_time;

	function SharedPost($id, $id_user, $fullname, $profile_path_user, $id_movie, $title_movie, $overview_movie, $poster_path_movie, $content, $post_time){
		$this->id = $id;
		$this->id_user = $id_user;
		$this->fullname = $fullname;
		$this->profile_path_user = $profile_path_user;
		$this->id_movie = $id_movie;
		$this->title_movie = $title_movie;
		$this->overview_movie = $overview_movie;
		$this->poster_path_movie= $poster_path_movie;
		$this->content = $content;
		$this->post_time = $post_time;
	}
}
?>
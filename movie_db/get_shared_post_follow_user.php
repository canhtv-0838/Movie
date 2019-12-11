<?php
require_once('connection.php');
mysqli_set_charset($con,'utf8');
$response = array();
$resultValue = array();
if($_SERVER['REQUEST_METHOD'] == 'POST') {
	$id_user = $_POST['id_user'];

	$querySelectSharedPost = "SELECT SHARED_POST.*, USER.fullname, USER.profile_path
	FROM shared_post SHARED_POST
	INNER JOIN user USER ON USER.id = '$id_user'
	WHERE SHARED_POST.id_user = '$id_user'";	
	$resultSelectSharedPost = @mysqli_query($con,$querySelectSharedPost);
	if ($resultSelectSharedPost) {
		while($row = mysqli_fetch_array($resultSelectSharedPost)){
			$id = $row['id'];
			$id_movie = $row['id_movie'];
			$title_movie = $row['title_movie'];
			$overview_movie = $row['overview_movie'];
			$poster_path_movie = $row['poster_path_movie'];
			$content = $row['content'];
			$post_time = $row['post_time'];
			$profile_path = $row['profile_path'];
			$fullname = $row['fullname'];

			array_push($resultValue, new SharedPost($id,$id_user, $fullname, $profile_path,$id_movie,$title_movie, $overview_movie, $poster_path_movie,$content,$post_time));
		}

		$response["result_code"] = 0;
		$response["error_code"] = 0;
		$response["data"] = json_encode($resultValue);
		$response["message"] = "Select SharedPost success";
	} else {
		$response["result_code"] = 0;
		$response["error_code"] = 1;
		$response["data"] = null;
		$response["message"] = "Select SharedPost failed";
	}
} else {
	$response["result_code"] = 1;
	$response["error_code"] = 99;
	$response["data"] = null;
	$response["message"] = "POST failed";
}
// echoing JSON response
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

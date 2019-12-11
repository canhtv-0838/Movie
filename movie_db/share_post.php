<?php
require_once('connection.php');
mysqli_set_charset($con,'utf8');
/** Array for JSON response*/
$response = array();
if($_SERVER['REQUEST_METHOD'] == 'POST'){
	$id_user = $_POST['id_user'];
	$id_movie = $_POST['id_movie'];
	$title_movie = $_POST['title_movie'];
	$overview_movie = $_POST['overview_movie'];
	$poster_path_movie = $_POST['poster_path_movie'];
	$content = $_POST['content'];

	$queryInsertSharedPost = "INSERT INTO shared_post(id_movie, title_movie, overview_movie, poster_path_movie, id_user, content) 
	VALUES('$id_movie', '$title_movie', '$overview_movie','$poster_path_movie','$id_user', '$content')";
	$resultInsertSharedPost = mysqli_query($con,$queryInsertSharedPost);

	if ($resultInsertSharedPost) {
		$response["result_code"] = 0;
		$response["error_code"] = 0;
		$response["message"] = "Shared success";
	} else {
		$response["result_code"] = 0;
		$response["error_code"] = 1;
		$response["message"] = "Shared failed";
	}

} else {
	$response["result_code"] = 1;
	$response["error_code"] = 99;
	$response["message"] = "POST failed";
}
/*Return json*/
echo json_encode($response);
?>

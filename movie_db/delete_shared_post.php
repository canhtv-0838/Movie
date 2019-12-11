<?php
require_once('connection.php');
mysqli_set_charset($con,'utf8');
$response = array();
if($_SERVER['REQUEST_METHOD'] == 'POST') {
	$id = $_POST['id'];
	$queryDeleteSharedPost = "DELETE  FROM shared_post WHERE id = '$id'";	
	if (mysqli_query($con,$queryDeleteSharedPost)) {
		$response["result_code"] = 0;
		$response["error_code"] = 0;
		$response["message"] = "Delete success";
	} else {
		$response["result_code"] = 0;
		$response["error_code"] = 1;
		$response["message"] = "Delete failed";
	}
} else {
	$response["result_code"] = 1;
	$response["error_code"] = 99;
	$response["message"] = "POST failed";
}
		// echoing JSON response
echo json_encode($response);
?>
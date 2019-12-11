<?php
require_once('connection.php');
mysqli_set_charset($con,'utf8');
$response = array();
if($_SERVER['REQUEST_METHOD'] == 'POST') {
	$id_user = $_POST['id_user'];

	$querySelectUser = "SELECT * FROM user WHERE id = '$id_user'";	
	$resultSelectUser = @mysqli_query($con,$querySelectUser);
	if (mysqli_num_rows($resultSelectUser) != 0) {
		$row = mysqli_fetch_array($resultSelectUser);
		$id = $row["id"];
		$fullname = $row["fullname"];
		$birthday = $row["birthday"];
		$gender = $row["gender"];
		$place_of_birth = $row["place_of_birth"];
		$profile_path = $row["profile_path"];

		// if ($gender == 0) {
		// 	$profile_path = "ic_profile_male.png";
		// } else {
		// 	$profile_path = "ic_profile_female.png";
		// }

		$response["result_code"] = 0;
		$response["error_code"] = 0;
		$response["data"] = json_encode(new User($id, $fullname, $birthday, $gender, $place_of_birth, $profile_path));
		$response["message"] = "Select user success";
	} else {
		$response["result_code"] = 0;
		$response["error_code"] = 1;
		$response["data"] = "";
		$response["message"] = "Select user failed";
	}
} else {
	$response["result_code"] = 1;
	$response["error_code"] = 99;
	$response["data"] = null;
	$response["message"] = "POST failed";
}
// echoing JSON response
echo json_encode($response);

class User {
	var $id;
	var $fullname;
	var $birthday;
	var $gender;
	var $place_of_birth;
	var $profile_path;

	function User($id, $fullname, $birthday, $gender, $place_of_birth, $profile_path){
		$this ->id = $id;
		$this ->fullname = $fullname;
		$this ->birthday = $birthday;
		$this ->gender = $gender;
		$this ->place_of_birth = $place_of_birth;
		$this ->profile_path = $profile_path;
	}
}
?>
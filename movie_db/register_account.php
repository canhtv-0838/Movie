<?php
require_once('connection.php');
mysqli_set_charset($con,'utf8');
/** Array for JSON response*/
$response = array();
if($_SERVER['REQUEST_METHOD'] == 'POST'){
	$fullname = $_POST['fullname'];
	$birthday = $_POST['birthday'];
	$gender = $_POST['gender'];
	$place_of_birth = $_POST['place_of_birth'];
	$username = $_POST['username'];
	$email = $_POST['email'];
	$password = $_POST['password'];
	$id_user = $_POST['id_user'];

	$queryCheckEmailExist = "SELECT email FROM account WHERE email = '$email'";
	$resultCheckEmailExist = @mysqli_query($con,$queryCheckEmailExist);
	if (mysqli_num_rows($resultCheckEmailExist) != 0) {
		$response["result_code"] = 1;
		$response["error_code"] = 1;
		$response["message"] = "The email has been existed";
	} else {
		if ($gender == 0) {
			$profile_path = "ic_profile_male.png";
		} else {
			$profile_path = "ic_profile_female.png";
		}
		$queryInsertUser = "INSERT INTO user(id,fullname,birthday,gender,place_of_birth, profile_path) VALUES('$id_user', '$fullname','$birthday','$gender','$place_of_birth', '$profile_path')";
		$resultInsertUser = mysqli_query($con,$queryInsertUser);
		if ($resultInsertUser) {
			$queryInsertAccount = "INSERT INTO account(username,password,email,id_user) VALUES('$username','$password','$email','$id_user')";
			$resultInsertAccount = mysqli_query($con,$queryInsertAccount);
			if ($resultInsertAccount) {
				$response["result_code"] = 0;
				$response["error_code"] = 0;
				$response["message"] = "Registation success";
			} else {
				$response["result_code"] = 1;
				$response["error_code"] = 3;
				$response["message"] = "Insert account failed";
			}
		} else {
			$response["result_code"] = 1;
			$response["error_code"] = 2;
			$response["message"] = "Insert user failed";
		}
	}	
} else {
	$response["result_code"] = 1;
	$response["error_code"] = 99;
	$response["message"] = "POST failed";
}
/*Return json*/
echo json_encode($response);
?>

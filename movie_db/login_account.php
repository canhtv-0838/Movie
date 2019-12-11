<?php
require_once('connection.php');
mysqli_set_charset($con,'utf8');
/** Array for JSON response*/
$response = array();
if($_SERVER['REQUEST_METHOD']=='POST'){
	$email = $_POST['email'];
	$password = $_POST['password'];
	
	$queryCheckAccount = "SELECT *  FROM account WHERE email = '$email' AND password='$password'";
	if(mysqli_num_rows(@mysqli_query($con,$queryCheckAccount)) > 0){
		$result= mysqli_query($con,$queryCheckAccount);
		$row = mysqli_fetch_array($result);
		
		$response["result_code"] = 0;
		$response["error_code"] = 0;
		$response["data"] = $row["id_user"];
		$response["message"] = "Login success";
	} else {
		$response["result_code"] = 0;
		$response["error_code"] = 1;
		$response["data"] = "";
		$response["message"] = "Login failed";
	}
} else {
	$response["result_code"] = 1;
	$response["error_code"] = 99;
	$response["message"] = "POST failed";
}
/**Return json*/
echo json_encode($response);
?>
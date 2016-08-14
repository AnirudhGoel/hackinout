<?php

require_once("inc/functions.inc.php");

$name = mysqli_real_escape_string($con, $_GET['name']);
$mobile = mysqli_real_escape_string($con, $_GET['mobile']);
$otp = mysqli_real_escape_string($con, $_GET['otp']);
$password = mysqli_real_escape_string($con, $_GET['password']);

if(check_otp($mobile, $otp)) {
	if(create_account($name, $mobile, $password)) {
		$response = "Account Created";
	} else {
		$response = "OTP verified. Error creating account";
	}
} else {
	$response = "Wrong OTP.";
}

print_r(json_encode($response));

?>
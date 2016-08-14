<?php

require_once("inc/functions.inc.php");

$name = mysqli_real_escape_string($con, $_GET['name']);
$mobile = mysqli_real_escape_string($con, $_GET['mobile']);
$password = mysqli_real_escape_string($con, $_GET['password']);


if(check_account_exists($mobile)) {
	$response = "Account already Exists";
	print_r(json_encode($response, true));
} else {
	$otp = mt_rand(100000,999999);
	$post_data = array(
	    // 'From' doesn't matter; For transactional, this will be replaced with your SenderId;
	    // For promotional, this will be ignored by the SMS gateway
	    'From'   => '8808891988',
	    'To'    => $mobile,
	    'Body'  => "Hi $otp,Thank you for registering; Your account has been activated"
	);
	 
	$exotel_sid = "one15"; // Your Exotel SID - Get it from here: http://my.exotel.in/Exotel/settings/site#api-settings
	$exotel_token = "d0991ef97709da506393eea3def6ad7a40d47699"; // Your exotel token - Get it from here: http://my.exotel.in/Exotel/settings/site#api-settings
	 
	$url = "https://".$exotel_sid.":".$exotel_token."@twilix.exotel.in/v1/Accounts/".$exotel_sid."/Sms/send";
	 
	$ch = curl_init();
	curl_setopt($ch, CURLOPT_VERBOSE, 1);
	curl_setopt($ch, CURLOPT_URL, $url);
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
	curl_setopt($ch, CURLOPT_FAILONERROR, 0);
	curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, 0);
	curl_setopt($ch, CURLOPT_POSTFIELDS, http_build_query($post_data));
	 
	$http_result = curl_exec($ch);
	$error = curl_error($ch);
	$http_code = curl_getinfo($ch ,CURLINFO_HTTP_CODE);
	 
	curl_close($ch);

	add_otp($mobile, $otp);

	error_log($http_result, 0);
	$response = "OTP Sent";
	print_r(json_encode($response, true));

}

?>
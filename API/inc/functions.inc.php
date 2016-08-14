<?php

require_once("connection.inc.php");

function check_account_exists($mobile) {
	GLOBAL $con;

	$sql = "SELECT name FROM user WHERE mobile=$mobile";
	error_log($sql, 0);
	$result = mysqli_query($con, $sql);

	if (mysqli_num_rows($result) > 0) {
	    return true;
	} else {
	    return false;
	}
}

function create_account($name, $mobile, $password) {
	GLOBAL $con;

	$sql = "INSERT INTO user(name, mobile, password)
	VALUES (\"$name\", $mobile, \"$password\")";
	error_log($sql, 0);

	if (mysqli_query($con, $sql)) {
	    error_log("Success in otp_check.php and account creation: SignUp record added.", 0);
		return true;
	} else {
	    error_log("Error in otp_check.php and account creation: " . $sql . "<br>" . mysqli_error($con), 0);
		return false;
	}
}

function add_otp($mobile, $otp) {
	GLOBAL $con;

	$sql = "INSERT INTO otp (mobile, otp) VALUES($mobile, $otp) ON DUPLICATE KEY UPDATE otp=$otp, mobile=$mobile";
	error_log($sql, 0);

	if (mysqli_query($con, $sql)) {
	    error_log("Success in signup.php: OTP added in DB.", 0);
		return true;
	} else {
	    error_log("Error in signup.php: " . $sql . "<br>" . mysqli_error($con), 0);
		return false;
	}
}

function check_otp($mobile, $otp) {
	GLOBAL $con;

	$sql = "SELECT mobile, otp FROM otp WHERE mobile=$mobile AND otp=$otp";
	error_log($sql, 0);
	$result = mysqli_query($con, $sql);

	if (mysqli_num_rows($result) > 0) {
	    return true;
	} else {
	    return false;
	}
}

function login($mobile, $password) {
	GLOBAL $con;

	$sql = "SELECT mobile, password FROM user WHERE mobile=$mobile AND password=\"$password\"";
	error_log($sql, 0);
	$result = mysqli_query($con, $sql);

	if (mysqli_num_rows($result) > 0) {
	    return true;
	} else {
	    return false;
	}
}

?>
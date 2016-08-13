<?php

require_once("connection.inc.php");

function check_account_exists($mobile) {
	GLOBAL $con;

	$sql = "SELECT name FROM user WHERE mobile=$mobile";
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

	if (mysqli_query($con, $sql)) {
		return true;
	    error_log("Success in signup.php: SignUp record added.", 0);
	} else {
	    error_log("Error in signup.php: " . $sql . "<br>" . mysqli_error($con), 0);
		return false;
	}
}

?>
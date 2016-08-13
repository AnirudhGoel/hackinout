<?php

require_once("inc/functions.inc.php");

$name = mysql_real_escape_string($_GET['name']);
$mobile = mysql_real_escape_string($_GET['mobile']);
$password = mysql_real_escape_string($_GET['password']);

if(check_account_exists($mobile)) {
	$response = "Account already Exists";
} else {
	if(create_account($name, $mobile, $password)) {
		$response = "Account Created";
	} else {
		$response = "Error creating account";
	}
}

print_r(json_encode($response, true));

?>
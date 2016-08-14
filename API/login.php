<?php

require_once("inc/functions.inc.php");

$mobile = mysqli_real_escape_string($con, $_GET['mobile']);
$password = mysqli_real_escape_string($con, $_GET['password']);

if(login($mobile, $password)) {
	$response = "successful";
} else {
	$response = "unsuccessful";
}

echo($response);

?>
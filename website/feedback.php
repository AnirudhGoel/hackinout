<?php require_once("inc/functions.inc.php");

$token = $_GET['token'];
$mobile = $_GET['mobile'];
$feedback = $_GET['feedback'];

if (check_token($token, $mobile)) {
	if(update_feed($token, $mobile, $feedback)) {
		echo "Feedback updated !";
	} else {
		echo "Feedback update Failed !";
	}
} else {
	echo "Token and Mobile do not match !";
}

?>
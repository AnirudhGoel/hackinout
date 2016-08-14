<?php
	
/**
 * @Author: anirudh
 * @Date:   2016-08-12 8:45:00
 * @Last Modified by:   Anirudh Goel
 * @Last Modified time: 2016-08-13 11:15:00
 */

// $host = "localhost";
// $user = "root";
// $password = "password";
// $db = "health";

$host = "mysql.hostinger.in";
$user = "u247778564_user";
$password = "password";
$db = "u247778564_heal";

$con = mysqli_connect($host, $user, $password, $db);

if (!$con) {
    echo("Connection failed: " . mysqli_connect_error());
} else {
	error_log("Connection Established...", 0);
}
	
?>
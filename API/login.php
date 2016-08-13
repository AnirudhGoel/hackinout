<?php

require_once("inc/connection.inc.php")

$mobile = $_GET['mobile'];
$password = $_GET['password'];

$sql = "INSERT INTO user(mobile, password)
VALUES ($mobile, $password)";

if (mysqli_query($conn, $sql)) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

?>
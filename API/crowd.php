<?php

require_once("inc/connection.inc.php");

$result = mysqli_query($con, "SELECT count(*) FROM feedback WHERE status=0");


if (!$result) echo mysqli_error($con);

$row = mysqli_fetch_row($result);

// Should show you an integer result.
print_r($row[0]);

?>
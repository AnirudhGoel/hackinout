<?php

require_once("connection.inc.php");

function check_duplicate_mobile($mobile) {
	GLOBAL $con;

	$sql = "SELECT mobile FROM feedback WHERE mobile=$mobile and status=0";
	error_log($sql, 0);
	$result = mysqli_query($con, $sql);

	if (mysqli_num_rows($result) > 0) {
	    return true;
	} else {
	    return false;
	}
}

function get_token($name, $mobile) {
	GLOBAL $con;

	$sql = "INSERT INTO feedback (name, mobile, status)
	VALUES (\"$name\", $mobile, 0)";
	error_log($sql, 0);

	if (mysqli_query($con, $sql)) {
	    error_log("Success in token.php.", 0);
		$sql = "SELECT token FROM feedback WHERE mobile=$mobile AND status=0";
		error_log($sql, 0);
		$result = mysqli_query($con, $sql);

		if (mysqli_num_rows($result) == 1) {
		    return mysqli_fetch_assoc($result)["token"];
		} else {
		    return false;
		}
	} else {
	    error_log("Error in token.php: " . $sql . "<br>" . mysqli_error($con), 0);
		return false;
	}
}

function check_token($token, $mobile) {
	GLOBAL $con;

	$sql = "SELECT token, mobile FROM feedback WHERE token=$token AND mobile=$mobile AND status=0";
	error_log($sql, 0);
	$result = mysqli_query($con, $sql);

	if (mysqli_num_rows($result) > 0) {
	    return true;
	} else {
	    return false;
	}
}

function update_feed($token, $mobile, $feedback) {
	GLOBAL $con;

	error_log("Here");

	$feedback_ana = urlencode($feedback);

	$data_json = file_get_contents("https://api.havenondemand.com/1/api/sync/analyzesentiment/v1?text=$feedback_ana&apikey=c7b3fa19-7afa-43a0-a7bc-034c3b192f5c");
	$data = json_decode($data_json, true);
	$score = $data["aggregate"]["score"];
	$score = round($score, 1);

	$sql = "UPDATE feedback
			SET feedback=\"$feedback\", status=1, score=$score
			WHERE status=0 AND token=$token AND feedback IS NULL";
	error_log($sql, 0);

	if (mysqli_query($con, $sql)) {
	    error_log("Success in feedback.php.", 0);
		return true;
	} else {
	    error_log("Error in feedback.php: " . $sql . "<br>" . mysqli_error($con), 0);
		return false;
	}
}

function get_rating() {
	GLOBAL $con;

	$sql = "SELECT AVG(score) FROM feedback WHERE score IS NOT NULL";
	error_log($sql, 0);
	$result = mysqli_query($con, $sql);

	if (mysqli_num_rows($result) == 1) {
	    return mysqli_fetch_assoc($result)["AVG(score)"];
	} else {
	    return "Error fetching rating";
	}
}
?>
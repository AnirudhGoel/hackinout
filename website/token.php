<?php require_once("inc/functions.inc.php"); ?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
	<meta name="author" content="Anirudh Goel" />
	<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Josefin+Sans:400,300" />
	<link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css' />
	<title>Your Token Number</title>
	<style type="text/css">
		body {
			background-color: rgba(255, 0, 0, 0);
			text-align: center;
			color: black;
			font-family: 'Josefin Sans', sans-serif;
		}
		p {
			margin-top: 30vh;
			font-size: 5em;
		}
	</style>
</head>
<body>

<?php

if (isset($_GET['token'])) {
	if (isset($_GET['name']) && isset($_GET['mobile'])) {
		$name = $_GET['name'];
		$mobile = $_GET['mobile'];

		if(check_duplicate_mobile($mobile)) {
			header("Location: index.php?retry=1");
		} else {
			$token = get_token($name, $mobile); ?>
			
			<p>Hi <?php echo($name); ?> !<br>Your token number is <span style="color: red;"><?php echo($token); ?></span></p>

			<?php 
			header("refresh:5;url=index.php");
		}
	}
}

?>

</body>
</html>
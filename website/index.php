<?php require_once("inc/functions.inc.php"); ?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
	<meta name="author" content="Anirudh Goel" />
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/css/materialize.min.css">
	<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Josefin+Sans:400,600,300" />
	<link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css' />
	<link href='css/style.css' rel='stylesheet' type='text/css' />
	<title>Form</title>
</head>
<body>
	<div class="container">
		<ul class="nav nav-tabs">
			<li class="active"><a data-toggle="tab" href="#entry">Entry</a></li>
			<li><a data-toggle="tab" href="#feedback">Feedback</a></li>
		</ul>

		<div class="tab-content">
			<div id="entry" class="tab-pane fade in active">
				<form method="GET" action="token.php">
					<div class="input-field">
						<input id="name" type="text" class="validate" required="required" name="name">
	          			<label for="name">Name</label>
          			</div>

          			<div class="input-field">
						<input id="mobile" class="validate" required="required" name="mobile" type="number">
	          			<label for="mobile">Mobile</label>
	          			<br>
	          			<?php if (isset($_GET['retry']) && $_GET['retry'] == 1) { ?>
	          				<span style="color: red; font-size:18px;">Mobile Number already exits !</span>
	          			<?php } ?>
					</div>
					<br>
					<input class="btn waves-effect waves-light" type="submit" name="token" style="left: -17vw !important;" value="Generate Token">
						<!-- <i class="material-icons right">send</i> -->
				</form>
			</div>
		
			<div id="feedback" class="tab-pane fade">
				<h5 align="left"><span style="color: Grey;">Doctor :</span> Dr. Anirudh Goel</h5>
				<h6 align="left"><span style="color: Grey;">Rating :</span><?php $rating = get_rating();
								echo($rating*10);
				?></h6>
				<br>
				<form>
					<div class="input-field">
						<input id="token" type="number" class="validate" required="required" name="token">
	          			<label for="token">Token</label>
					</div>
					<div class="input-field">
						<input id="mobile1" class="validate" required="required" name="mobile1" type="number">
	          			<label for="mobile1">Mobile</label>
					</div>
					<div class="input-field col s12">
			        	<textarea id="textarea1" class="materialize-textarea" required="required" name="feedback" maxlength="280"></textarea>
			        	<label for="textarea1">Feedback</label>
			        </div>
			        <br>
			        <input class="btn waves-effect waves-light" type="submit" name="submit" style="left: -17vw !important;" value="Submit Feedback" onclick="feed(event);">
				</form>
				<br>
				<div id="result"></div>
			</div>
		</div>
	</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>
<script src="js/index.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    $("input[id='mobile']").attr({
       "max" : 9999999999,
       "min" : 1000000000
    });
    $("input[id='token']").attr({
       "min" : 0
    });
});

function feed(event) {
	event.preventDefault(event);

	var token = $("#token").val();
	var mobile = $("#mobile1").val();
	var feedback = $("#textarea1").val();
	$.get("feedback.php", {token: token, mobile: mobile, feedback: feedback}, function(data) {
		$("#result").html(data);
	});
}
</script>
</html>
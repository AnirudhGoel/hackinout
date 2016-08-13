<?php

$url = "https://api.havenondemand.com/1/api/sync/querytextindex/v1?text=health+india&absolute_max_results=10&ignore_operators=false&indexes=news_eng&promotion=false&total_results=false&apikey=c7b3fa19-7afa-43a0-a7bc-034c3b192f5c";

$data = file_get_contents($url);

print_r($data);

?>
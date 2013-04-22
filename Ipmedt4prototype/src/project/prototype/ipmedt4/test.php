  <?php
$link = mysqli_connect('localhost', 'root', '', 'movedb');

    $query = "SELECT Attraction_ID, Attraction_name_Dutch, Attraction_description_Dutch FROM attractions WHERE Attraction_ID <=6 ORDER BY Attraction_ID ASC";

    $result = mysqli_query($link, $query) or die(mysqli_error($link));

    //if ($result = mysqli_query($link, $query)) {

		/* fetch associative array */
	$rows = array();
	while($r = $result->fetch_assoc())
	{
		$rows[] = array_map('utf8_encode', $r);
	}
	
	// function cb($string) 
	// {
		// return iconv("iso-8859-1", "utf-8", $string);
	// }

	// $r = array_map('cb', $r);

	// $rows[] = $r;
	
	//display the array in php
	//var_dump($rows);
	//echo '<br>';

	//json encode it
	//$json = json_encode($rows);
	//var_dump($json);
	//echo '<br>';

	//json encode just the values
	//$json = json_encode(array_values($rows));
	//var_dump($json);
	
	//print json_encode($json);
	
	print json_encode($rows);
    //echo '<br>';
?>
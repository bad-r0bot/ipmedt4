  <?php
$link = mysqli_connect('localhost', 'root', '', 'movedb');

    $query = "SELECT Ville_ID, Ville FROM city_type";

    $result = mysqli_query($link, $query) or die(mysqli_error($link));

    //if ($result = mysqli_query($link, $query)) {

		/* fetch associative array */
		$rows = array();
		while($r = $result->fetch_assoc())
		{
			$rows[] = $r;
		}
	
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
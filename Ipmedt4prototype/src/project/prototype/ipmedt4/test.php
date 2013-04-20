  <?php
$link = mysqli_connect('localhost', 'root', '', 'movedb');

    $query = "SELECT * FROM city_type";

    $result = mysqli_query($link, $query) or die(mysqli_error($link));

    //if ($result = mysqli_query($link, $query)) {

		/* fetch associative array */
		$rows = array();
		while($r = $result->fetch_assoc())
		{
			$rows[] = $r;
		}
	
	print json_encode($rows);
    //
?>
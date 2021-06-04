<?php 

	$connection = new mysqli(
        "localhost",
        "root",
        "",
        "conectando"
    );
	
	$result = array();
	$result['datos'] = array();
	$select= "SELECT *from datos";
	$responce = mysqli_query($connection,$select);
	
	while($row = mysqli_fetch_array($responce))
		{
			$index['id']      = $row['0'];
			$index['nombre']    = $row['1'];
			$index['email']   = $row['2'];
			$index['contacto'] = $row['3'];
			$index['direccion'] = $row['4'];
			
			array_push($result['datos'], $index);
		}
			
			$result["success"]="1";
			echo json_encode($result);
			mysqli_close($connection);

 ?>
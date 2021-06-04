<?php 
	$mysql = new mysqli(
		"localhost",
		"root",
		"",
		"conectando"
	);

	if($mysql->connect_error){
		die("Error de conexión". $mysql->connect_error);
	}else{
		echo "Conexión exitosa";
	}

?>
<?php
    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        require_once("db.php");

        $name = $_POST['nombre'];
        $email = $_POST['email'];
        $contact = $_POST['contacto'];
        $address = $_POST['direccion'];

        $query = "INSERT INTO datos (nombre, email, contacto, direccion) VALUES ('$name', '$email', '$contact', '$address')";
        $response = $mysql->query($query);
        if($result == true){
            echo "El usuario se ha creado satisfactoriamente";
        }else{
            echo "error";
        }

        $mysql->close();

    }

?>
<?php

    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        require_once("db.php");
    
     $id = $_POST['id'];
     $name = $_POST['nombre'];
     $email = $_POST['email'];
     $contact = $_POST['contacto'];
     $address = $_POST['direccion'];
     
     $query = "UPDATE datos SET  nombre = '$name', email = '$email', contacto = '$contact', direccion = '$address' WHERE id = '$id' ";
     
    $result = $mysql->query($query);

    if($mysql->affected_rows > 0){
        if($result == TRUE){
            echo "Actualización exitosa";
        }else{
            echo "Error al actualizar";
        }
    }else{
        echo "Sind concidencias";
    }
    

    $mysql->close();
     
    }
?>
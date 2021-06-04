<?php
    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        require_once("db.php");
    
    $id = $_POST['id'];
     
     
    $query = "DELETE FROM datos WHERE id='$id' ";
     
    $result = $mysql->query($query);

    if($mysql->affected_rows > 0){
        if($result == TRUE){
            echo "El usuario se ha eliminado";
        }
    }else{
        echo "Error al eliminar";
    }
    

    $mysql->close();
     
    }


?>
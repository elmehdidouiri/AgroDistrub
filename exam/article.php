<?php
$con = mysqli_connect("localhost", "root", "", "andoid");

if ($con->connect_error) {
    die("Connect error: " . $con->connect_error);
}
$matricule = $_POST['matricule'];
$produit = $_POST['produit'];


$query = "SELECT SUM(CASE WHEN situation = 'entree' THEN quantite ELSE -quantite END) AS quantite FROM stock WHERE matricule = '$matricule' AND produit = '$produit'";
$result = mysqli_query($con, $query);

if ($result) {
   

    $row = $result->fetch_assoc();
   
    $response =  $row['quantite'];
    echo $response;
   

} else {
    echo "Error: " . mysqli_error($con);
}


$con->close();




?>
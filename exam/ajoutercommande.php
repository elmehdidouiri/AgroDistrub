<?php
$con = mysqli_connect("localhost", "root", "", "andoid");
if ($con->connect_error) {
    die("Connect error: " . $con->connect_error);
}

$t = $_POST["gamme"];
$s = $_POST["produit"];
$u = $_POST["quantite"];
$v = $_POST["prix"];
$w = $_POST["n_commande"];
$x = $_POST["Total"];


$maxIdQuery = "SELECT MAX(id) FROM commande";
$maxIdResult = mysqli_query($con, $maxIdQuery);
$maxIdRow = mysqli_fetch_row($maxIdResult);
$maxId = $maxIdRow[0];


$nextId = $maxId + 1;

$query = "INSERT INTO commande (id, gamme, produit, quantite, prix, n_commande, total) 
          VALUES ('$nextId', '$t', '$s', '$u', '$v', '$w', '$x')";

if (mysqli_query($con, $query)) {
    echo "Data inserted successfully. Inserted ID: " . $nextId;
} else {
    echo "Error: " . mysqli_error($con);
}

mysqli_close($con);
?>

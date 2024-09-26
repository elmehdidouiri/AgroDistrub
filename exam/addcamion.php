<?php
$con = mysqli_connect("localhost", "root", "", "andoid");

if ($con->connect_error) {
    die("Connect error: " . $con->connect_error);
}

$t = $_POST["matricule"];
$s = $_POST["chauffeur"];
$u=$_POST["vendeur"];
$v=$_POST["aide_vendeur"];

$query = "INSERT INTO camion (matricule, chauffeur,vendeur, aide_vendeur) VALUES ('$t', '$s', '$u', '$v')";

if (mysqli_query($con, $query)) {
    echo "Data inserted successfully.";
} else {
    echo "Error: " . mysqli_error($con);
}

mysqli_close($con);

?>
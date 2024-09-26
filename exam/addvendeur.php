<?php
$con = mysqli_connect("localhost", "root", "", "andoid");

if ($con->connect_error) {
    die("Connect error: " . $con->connect_error);
}

$t = $_POST["matricule"];
$s = $_POST["name"];
$u=$_POST["password"];
$v=$_POST["contact"];

$query = "INSERT INTO vendeur (matricule, name,password, contact) VALUES ('$t', '$s', '$u', '$v')";

if (mysqli_query($con, $query)) {
    echo "Data inserted successfully.";
} else {
    echo "Error: " . mysqli_error($con);
}

mysqli_close($con);

?>
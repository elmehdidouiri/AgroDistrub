<?php
$con = mysqli_connect("localhost", "root", "", "andoid");

if ($con->connect_error) {
    die("Connect error: " . $con->connect_error);
}

$t = $_POST["code"];
$s = $_POST["name"];
$u=$_POST["adresse"];
$v=$_POST["contact"];

$query = "INSERT INTO client (code, name,adress, contact) VALUES ('$t', '$s', '$u', '$v')";

if (mysqli_query($con, $query)) {
    echo "Data inserted successfully.";
} else {
    echo "Error: " . mysqli_error($con);
}

mysqli_close($con);

?>
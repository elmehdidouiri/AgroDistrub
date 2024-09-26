<?php
$con = mysqli_connect("localhost", "root", "", "andoid");
if ($con->connect_error) {
    die("Connect error: " . $con->connect_error);
}
$t = $_POST["gamme"];
$s = $_POST["produit"];
$u=$_POST["quantite"];
$v=$_POST["matricule"];
$w=$_POST["situation"];
$x=$_POST["date"];
$query = "INSERT INTO stock (gamme,produit,quantite,matricule,situation,date) VALUES ('$t', '$s', '$u', '$v' , '$w', '$x')";
if (mysqli_query($con, $query)) {
    echo "Data inserted successfully.";
} else {

    echo "Error: " . mysqli_error($con);

}
mysqli_close($con);
?>
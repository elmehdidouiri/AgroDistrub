<?php
$con = mysqli_connect("localhost", "root", "", "andoid");
if ($con->connect_error) {
    die("Connect error: " . $con->connect_error);
}

$nCommande = isset($_GET["n_commande"]) ? $_GET["n_commande"] : null; //  wach kyn n commande
$query = "SELECT * FROM commande";
if (!empty($nCommande)) {
    $query .= " WHERE n_commande = '$nCommande'";
}

$result = mysqli_query($con, $query);

if (mysqli_num_rows($result) > 0) {
    $response = array();
    while ($row = mysqli_fetch_assoc($result)) {
        $response[] = $row;
    }
    echo json_encode($response);
} else {
    echo "No data found";
}

mysqli_close($con);
?>

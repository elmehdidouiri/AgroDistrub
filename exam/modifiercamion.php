<?php
$con = mysqli_connect("localhost", "root", "", "andoid");

if ($con->connect_error) {
    die("Connect error: " . $con->connect_error);
}


$matricule = $_POST['matricule'];
$chauffeur = $_POST['chauffeur'];
$vendeur = $_POST['vendeur'];
$aide_vendeur = $_POST['aide_vendeur'];

if (!empty($matricule) && !empty($chauffeur) && !empty($vendeur) && !empty($aide_vendeur)) {
  
    $stmt = $con->prepare("UPDATE camion SET chauffeur = ?, vendeur = ?, aide_vendeur = ? WHERE matricule = ?");
    $stmt->bind_param("ssss", $chauffeur, $vendeur, $aide_vendeur, $matricule);
    $stmt->execute();

    if ($stmt->affected_rows > 0) {
        echo "Client updated successfully";
    } else {
        echo "Failed to update camion";
    }

    $stmt->close();
} else {
    echo "Invalid parameters";
}

$con->close();
?>

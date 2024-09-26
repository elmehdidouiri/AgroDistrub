<?php
$con = mysqli_connect("localhost", "root", "", "andoid");

if ($con->connect_error) {
    die("Connect error: " . $con->connect_error);
}

$matricule = $_POST['matricule'];

if (!empty($matricule)) {
    
    $stmt = $con->prepare("SELECT chauffeur, vendeur, aide_vendeur FROM camion WHERE matricule = ?");
    $stmt->bind_param("s", $matricule);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
      
        $row = $result->fetch_assoc();

       
        $response = $row['chauffeur'] . ';' . $row['vendeur'] . ';' . $row['aide_vendeur'];
        echo $response;
    } else {
        echo "camion not found";
    }

    $stmt->close();
} else {
    echo "Invalid matricule parameter";
}

$con->close();
?>

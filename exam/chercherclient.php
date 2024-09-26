<?php
$con = mysqli_connect("localhost", "root", "", "andoid");

if ($con->connect_error) {
    die("Connect error: " . $con->connect_error);
}


$code = $_POST['code'];

if (!empty($code)) {
   
    $stmt = $con->prepare("SELECT name, adress, contact FROM client WHERE code = ?");
    $stmt->bind_param("s", $code);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
       
        $row = $result->fetch_assoc();

       
        $response = $row['name'] . ';' . $row['adress'] . ';' . $row['contact'];
        echo $response;
    } else {
        echo "Client not found";
    }

    $stmt->close();
} else {
    echo "Invalid code parameter";
}

$con->close();
?>

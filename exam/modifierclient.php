<?php
$con = mysqli_connect("localhost", "root", "", "andoid");

if ($con->connect_error) {
    die("Connect error: " . $con->connect_error);
}


$code = $_POST['code'];
$name = $_POST['name'];
$adress = $_POST['adress'];
$contact = $_POST['contact'];

if (!empty($code) && !empty($name) && !empty($adress) && !empty($contact)) {

    $stmt = $con->prepare("UPDATE client SET name = ?, adress = ?, contact = ? WHERE code = ?");
    $stmt->bind_param("ssss", $name, $adress, $contact, $code);
    $stmt->execute();

    if ($stmt->affected_rows > 0) {
        echo "Client updated successfully";
    } else {
        echo "Failed to update client";
    }

    $stmt->close();
} else {
    echo "Invalid parameters";
}

$con->close();
?>

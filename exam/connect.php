<?php
$con = mysqli_connect("localhost", "root", "", "andoid");

if ($con->connect_error) {
    die("Connect error: " . $con->connect_error);
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $enteredName = $_POST['code'];
    $enteredPassword = $_POST['password'];

    $query = "SELECT name, password FROM vendeur WHERE name = '$enteredName'";

    $result = mysqli_query($con, $query);

    if ($result && mysqli_num_rows($result) > 0) {
        $row = mysqli_fetch_assoc($result);
        $name = $row['name'];
        $password = $row['password'];

        if ($enteredPassword == $password) {
            echo "Login successful. Name: " . $name . ", Password: " . $password;
        } else {
            echo "Incorrect password.";
        }
    } else {
        echo "Invalid username.";
    }
}

mysqli_close($con);
?>

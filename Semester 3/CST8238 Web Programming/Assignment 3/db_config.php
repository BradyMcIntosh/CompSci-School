<?php
$host = "localhost";
$username = "bradyjmc_eatery";
$password = "assignment2";
$database = "bradyjmc_eatery";


// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
//echo "Connected successfully";

?>

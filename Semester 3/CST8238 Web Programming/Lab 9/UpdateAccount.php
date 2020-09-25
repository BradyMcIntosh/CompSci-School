<?php

  session_start();

  require "MySQLCreds.php";

  $dbConnection = mysqli_connect($host, $username, $password);

  if(!$dbConnection)
    die("Could not connect to the database.");

  mysqli_select_db($dbConnection, $database);

  $sqlQuery = "SELECT * FROM Employee WHERE EmployeeID = '". $_POST["empid"]."'";


  $result = mysqli_query($dbConnection,$sqlQuery);

  $rowCount = mysqli_num_rows($result);

  if($rowCount == 0) {
    header("Location: SelectAccount.php");
    exit;
  }
  else {
    $row = mysqli_fetch_row($result);
    $ch_id = $row[0];
    $ch_fname = $row[1];
    $ch_lname = $row[2];
    $ch_email = $row[3];
    $ch_phone = $row[4];
    $ch_desig = $row[7];
    $ch_admincode = $row[8];
  }

  mysqli_close($dbConnection);

 ?>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Update Account</title>
    <link rel="stylesheet" type="text/css" href="StyleSheet.css">
  </head>

<?php
  include "Header.php";
?>

<form action="UpdateComplete.php" method="post">
  <h2>Create your new account: </h2>

  <input type="hidden" name="empid" value=<?php echo $ch_id; ?> />

  <label for="fname">First Name</label>
  <input type="text" id="fname" name="fname" value=<?php echo $ch_fname; ?>><br>

  <label for="lname">Last Name</label>
  <input type="text" id="lname" name="lname" value=<?php echo $ch_lname; ?>><br>

  <label for="email">Email Address</label>
  <input type="text" id="email" name="email" value=<?php echo $ch_email; ?>><br>

  <label for="phone">Phone Number</label>
  <input type="text" id="phone" name="phone" value=<?php echo $ch_phone; ?>><br>

  <label for="desig">Designation</label>
  <input type="text" id="desig" name="desig" value=<?php echo $ch_desig; ?>><br>

  <label for="admincode">Admin Code</label>
  <input type="text" id="admincode" name="admincode" value=<?php echo $ch_admincode; ?>><br>

  <input type="submit" value="Update Record"/>
</form>

<?php include "Footer.php"; ?>

<?php

  session_start();

  require "MySQLCreds.php";

  $dbConnection = mysqli_connect($host, $username, $password);

  if(!$dbConnection)
    die("Could not connect to the database.");

  mysqli_select_db($dbConnection, $database);

  $sqlQuery = "UPDATE Employee SET FirstName = '".$_POST["fname"]."', LastName = '".$_POST["lname"]."', EmailAddress = '".$_POST["email"]."', TelephoneNumber = '".$_POST["phone"]."', Designation = '".$_POST["desig"]."', AdminCode = '".$_POST["admincode"]."' WHERE EmployeeID = '".$_POST["empid"]."'";

  if (mysqli_query($dbConnection, $sqlQuery)) {
		$error = "Employee Information was successfully updated.";
	} else {
		$error = "Employee Information was not updated. <br> Query: ".$sqlQuery."<br> Error: ".mysqli_error($dbConnection);
	}

  mysqli_close($dbConnection);

 ?>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Update Outcome</title>
    <link rel="stylesheet" type="text/css" href="StyleSheet.css">
  </head>

<?php
  include "Header.php";
?>

<?php echo $error; ?>

<?php include "Footer.php"; ?>

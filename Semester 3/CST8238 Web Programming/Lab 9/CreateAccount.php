<?php

  session_start();

  require "MySQLCreds.php";

  $warning = "";
  $ret = 0;

  if(empty($_POST["fname"])) {
    $ret = 1;
  }

  if(empty($_POST["lname"])) {
    $ret = 1;
  }

  if(empty($_POST["email"])) {
    $ret = 1;
  }

  if(empty($_POST["phone"])) {
    $ret = 1;
  }

  if(empty($_POST["sin"])) {
    $ret = 1;
  }

  if(empty($_POST["pass"])) {
    $ret = 1;
  }

  if(empty($_POST["desig"])) {
    $ret = 1;
  }
  else {
    if($_POST["desig"] != ITDeveloper && $_POST["desig"] != Manager) {
      $warning = "Designation may only be \"ITDeveloper\" or \"Manager\"";
      $ret = 1;
    }
  }

  if(empty($_POST["admincode"])) {
    $ret = 1;
  }
  else {
    if(($_POST["desig"] == ITDeveloper && $_POST["admincode"] != "111") || ($_POST["desig"] == Manager) && $_POST["admincode"] != "999") {
      $warning = "Admin Code (".$_POST["admincode"].") does not match designation (".$_POST["desig"].").";
      $ret = 1;
    }
  }

  if($ret == 1 && $warning == "") {
    $warning = "All fields are required to be filled in.";
  }

  if($ret != 1) {

    $dbConnection = mysqli_connect($host, $username, $password);

		// Check connection
		if ($dbConnection->connect_error) {
			die("Connection failed: " . $dbConnection->connect_error);
		}

		mysqli_select_db($dbConnection, $database);

		$sqlQuery = "INSERT INTO Employee (FirstName, LastName, EmailAddress, TelephoneNumber, SocialInsuranceNumber, Password, Designation, AdminCode) VALUES('".$_POST["fname"]."', '".$_POST["lname"]."', '".$_POST["email"]."', '".$_POST["phone"]."', '".$_POST["sin"]."', '".$_POST["pass"]."', '".$_POST["desig"]."', '".$_POST["admincode"]."')";

		if (mysqli_query($dbConnection, $sqlQuery)) {
			$warning = "Employee Successfully Added";
		} else {
			$warning = "Person Could not be added: " . mysqli_error($dbConnection);
		}

		mysqli_close($dbConnection);

    $_SESSION["fname"] = $_POST["fname"];
    $_SESSION["lname"] = $_POST["lname"];
    $_SESSION["email"] = $_POST["email"];
    $_SESSION["phone"] = $_POST["phone"];
    $_SESSION["sin"] = $_POST["sin"];
    $_SESSION["pass"] = $_POST["pass"];
    $_SESSION["desig"] = $_POST["desig"];
    $_SESSION["admincode"] = $_POST["admincode"];

    header("Location: ViewAllEmployees.php");
    exit;
  }



 ?>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Create Account</title>
    <link rel="stylesheet" type="text/css" href="StyleSheet.css">
  </head>

<?php
  include "Header.php";
?>

  <form method="post">
    <h2>Create your new account: </h2>
    <p><?php echo $warning; ?></p>

    <label for="fname">First Name</label>
    <input type="text" id="fname" name="fname"><br>

    <label for="lname">Last Name</label>
    <input type="text" id="lname" name="lname"><br>

    <label for="email">Email Address</label>
    <input type="text" id="email" name="email" ><br>

    <label for="phone">Phone Number</label>
    <input type="text" id="phone" name="phone" ><br>

    <label for="sin">SIN</label>
    <input type="text" id="sin" name="sin" ><br>

    <label for="pass">Password</label>
    <input type="password" id="pass" name="pass" ><br>

    <label for="desig">Designation</label>
    <input type="text" id="desig" name="desig" ><br>

    <label for="admincode">Admin Code</label>
    <input type="text" id="admincode" name="admincode" ><br>

    <input type="submit" value="Submit Information" />
  </form>

<?php include "Footer.php"; ?>

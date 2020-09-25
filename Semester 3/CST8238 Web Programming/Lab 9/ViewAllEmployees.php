<?php

  session_start();

  require "MySQLCreds.php";

  $dbConnection = mysqli_connect($host, $username, $password);

  if(!$dbConnection)
    die("Could not connect to the database.");

  mysqli_select_db($dbConnection, $database);

  $sqlQuery = "SELECT * FROM Employee WHERE EmailAddress = '". $_SESSION["email"]."' AND Password = '".$_SESSION["pass"]."'";

  $result = mysqli_query($dbConnection,$sqlQuery);

  $rowCount = mysqli_num_rows($result);

  if($rowCount == 0) {
    header("Location: Login.php");
    exit;
  }

  mysqli_close($dbConnection);

 ?>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>View All Employees</title>
    <link rel="stylesheet" type="text/css" href="StyleSheet.css">
  </head>

<?php
  include "Header.php";
?>

<?php

  $dbConnection = mysqli_connect($host, $username, $password);

  if(!$dbConnection)
    die("Could not connect to the database.");

  mysqli_select_db($dbConnection, $database);

  $sqlQuery = "SELECT * FROM Employee";

  $result = mysqli_query($dbConnection,$sqlQuery);

  $rowCount = mysqli_num_rows($result);


  echo "<h2>Session State Data</h2>";
  echo "<p>First Name: ".$_SESSION["fname"]."</p>";
  echo "<p>Last Name: ".$_SESSION["lname"]."</p>";
  echo "<p>Email Address: ".$_SESSION["email"]."</p>";
  echo "<p>Phone Number: ".$_SESSION["phone"]."</p>";
  echo "<p>SIN: ".$_SESSION["sin"]."</p>";
  echo "<p>Designation: ".$_SESSION["desig"]."</p>";


  if($rowCount == 0)
    echo "*** There are no rows to display from the Employee table ***";
  else
  {
    echo "<h2>Database Data</h2>";
    echo "<table id=\"data\">";
    echo "<th>First Name</th>";
    echo "<th>Last Name</th>";
    echo "<th>Email Address</th>";
    echo "<th>Phone Number</th>";
    echo "<th>SIN</th>";
    echo "<th>Designation</th>";

    for($i=0; $i<$rowCount; ++$i) {
      $row = mysqli_fetch_row($result);
      echo "<tr>";
      echo "<td>".$row[1]."</td>";
      echo "<td>".$row[2]."</td>";
      echo "<td>".$row[3]."</td>";
      echo "<td>".$row[4]."</td>";
      echo "<td>".$row[5]."</td>";
      echo "<td>".$row[7]."</td>";
      echo "</tr>";
    }
    echo "</table>";
  }

  mysqli_close($dbConnection);
?>

<?php include "Footer.php"; ?>

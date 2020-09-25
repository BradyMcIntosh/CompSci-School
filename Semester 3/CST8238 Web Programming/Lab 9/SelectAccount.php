<?php

  session_start();

  require "MySQLCreds.php";

  $dbConnection = mysqli_connect($host, $username, $password);

  if(!$dbConnection)
    die("Could not connect to the database.");

  mysqli_select_db($dbConnection, $database);

  $sqlQuery = "SELECT * FROM Employee WHERE EmailAddress = '". $_SESSION["email"]."' AND Password = '".$_SESSION["pass"]."' AND AdminCode = '".$_SESSION["admincode"]."'";

  $result = mysqli_query($dbConnection,$sqlQuery);

  $rowCount = mysqli_num_rows($result);

  if($rowCount == 0 || $_SESSION["admincode"] != "999") {
    header("Location: Admin.php");
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

<h2>Select an Employee to update</h2>

<?php

$dbConnection = mysqli_connect($host, $username, $password);

if(!$dbConnection)
  die("Could not connect to the database.");

mysqli_select_db($dbConnection, $database);

$sqlQuery = "SELECT * FROM Employee";

$result = mysqli_query($dbConnection,$sqlQuery);

$rowCount = mysqli_num_rows($result);

echo "<table class=\"hidden\">";

for($i=0; $i<$rowCount; ++$i) {
  $row = mysqli_fetch_row($result);
  echo "<tr>";
    echo "<td>";
      echo "<form action=\"UpdateAccount.php\" method=\"post\">";
      echo "<input type=\"hidden\" name=\"empid\" value=\"".$row[0]."\" />";
      echo "<input type=\"submit\" name=\"edit\" value=\"Edit Employee\" />";
      echo "</form>";
    echo "</td>";
    echo "<td>";
    echo "First Name: ".$row[1]."<br />";
    echo "Last Name: ".$row[2]."<br />";
    echo "</td>";
  echo "</tr>";
}
echo "</table>";


?>

<?php include "Footer.php"; ?>

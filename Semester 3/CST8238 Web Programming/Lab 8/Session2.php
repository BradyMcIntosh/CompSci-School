<?php
  session_start();

function display() {
  echo "<p>Employee Name: ";
  if(!empty($_SESSION["name"])) {
    echo $_SESSION["name"];
  }
  else {
    echo "not set";
  }
  echo "</p>";

  echo "<p>Employee ID: ";
  if(!empty($_SESSION["id"])) {
    echo $_SESSION["id"];
  }
  else {
    echo "not set";
  }
  echo "</p>";

  echo "<p>Phone Number: ";
  if(!empty($_SESSION["phone"])) {
    echo $_SESSION["phone"];
  }
  else {
    echo "not set";
  }
  echo "</p>";

  echo "<p>Email Address: ";
  if(!empty($_SESSION["email"])) {
    echo $_SESSION["email"];
  }
  else {
    echo "not set";
  }
  echo "</p>";

  echo "<p>Employee Type: ";
  if(isset($_SESSION["type"])) {
    echo $_SESSION["type"];
  }
  else {
    echo "not set";
  }
  echo "</p>";

  echo "<p>Project: ";
  if(isset($_SESSION["project"])) {
    echo $_SESSION["project"];
  }
  else {
    echo "not set";
  }
  echo "</p>";
}
?>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Array1</title>
    <link rel="stylesheet" type="text/css" href="StyleSheet.css">
  </head>

<?php include "Header.php"; ?>
<?php display(); ?>
<?php include "Footer.php"; ?>

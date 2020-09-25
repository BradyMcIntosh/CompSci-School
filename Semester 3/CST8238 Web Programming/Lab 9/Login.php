<?php

  session_start();

  require "MySQLCreds.php";

  $warning = "";

  if(!empty($_POST["email"]) && !empty($_POST["pass"])) {

      $dbConnection = mysqli_connect($host, $username, $password);

      if(!$dbConnection)
        die("Could not connect to the database.");

      mysqli_select_db($dbConnection, $database);

      $sqlQuery = "SELECT * FROM Employee WHERE EmailAddress = '". $_POST["email"]."' AND Password = '".$_POST["pass"]."'";

      $result = mysqli_query($dbConnection,$sqlQuery);

      $rowCount = mysqli_num_rows($result);

      if($rowCount != 0) {

        $row = mysqli_fetch_row($result);

        $_SESSION["fname"] = $row[1];
        $_SESSION["lname"] = $row[2];
        $_SESSION["email"] = $row[3];
        $_SESSION["phone"] = $row[4];
        $_SESSION["sin"] = $row[5];
        $_SESSION["pass"] = $row[6];
        $_SESSION["desig"] = $row[7];
        $_SESSION["admincode"] = $row[8];

        header("Location: ViewAllEmployees.php");
        exit;
      }
      else {
        $warning = "<p>That account does not exist!</p>"
        ."<p>Query sent: ".$sqlQuery."</p>";
      }

      mysqli_close($dbConnection);
  }

 ?>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="StyleSheet.css">
  </head>

<?php
  include "Header.php";
?>

<form method="post">
  <h2>Login</h2>
  <?php echo $warning; ?>
  <label for="email">Email Address</label>
  <input type="text" id="email" name="email" ><br>

  <label for="pass">Password</label>
  <input type="password" id="pass" name="pass" ><br>

  <input type="submit" value="Log in"/>
</form>

<?php include "Footer.php"; ?>

<?php

  session_start();

  if(!empty($_POST["name"])) {
    $_SESSION["name"] = $_POST["name"];
  }
  else {
    $_SESSION["name"] = "not set";
  }

  if(!empty($_POST["id"])) {
    $_SESSION["id"] = $_POST["id"];
  }
  else {
    $_SESSION["id"] = "not set";
  }

  if(!empty($_POST["phone"])) {
    $_SESSION["phone"] = $_POST["phone"];
  }
  else {
    $_SESSION["phone"] = "not set";
  }

  if(!empty($_POST["email"])) {
    $_SESSION["email"] = $_POST["email"];
  }
  else {
    $_SESSION["email"] = "not set";
  }

  if(isset($_POST["type"])) {
    $_SESSION["type"] = $_POST["type"];
  }
  else {
    $_SESSION["type"] = "not set";
  }

  if(isset($_POST["project"])) {
    $_SESSION["project"] = $_POST["project"];
  }
  else {
    $_SESSION["project"] = "not set";
  }

  if(!empty($_POST["name"]) || !empty($_POST["id"]) || !empty($_POST["phone"]) || !empty($_POST["email"]) || isset($_POST["type"])) {
    header("Location: Session2.php");
    exit;
  }

 ?>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Session1</title>
    <link rel="stylesheet" type="text/css" href="StyleSheet.css">
  </head>

<?php include "Header.php"; ?>

<?php
echo <<<_END
<div class="session">

  <form method="post">
    <p>Employee information: </p>

    <label for="name">Employee Name</label>
    <input type="text" id="name" name="name"><br>
    <label for="id">Employee ID</label>
    <input type="text" id="id" name="id" ><br>
    <label for="phone">Phone Number</label>
    <input type="text" id="phone" name="phone" ><br>
    <label for="email">Email Address</label>
    <input type="text" id="email" name="email" ><br>

    <p>Type of employee: </p>

    <label for="manager">Manager</label>
    <input type="radio" id="manager" name="type" value="manager"><br>
    <label for="teamlead">Team Lead</label>
    <input type="radio" id="teamlead" name="type" value="teamlead"><br>
    <label for="itdev">IT Developer</label>
    <input type="radio" id="itdev" name="type" value="itdev"><br>
    <label for="itana">IT Analyst</label>
    <input type="radio" id="itana" name="type" value="itana"><br>

    <p>Project: </p>

    <select name="project">
      <option value="proja">Project A
      <option value="projb">Project B
      <option value="projc">Project C
      <option value="projd">Project D
    </select><br><br>

    <input type="submit" />
  </form>
_END;

  display();


echo <<<_END
</div>
_END;
?>

<?php include "Footer.php"; ?>

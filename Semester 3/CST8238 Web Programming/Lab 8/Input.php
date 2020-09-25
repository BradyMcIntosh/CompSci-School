<?php

  function display() {
    echo "<p>Employee Name: ";
    if(!empty($_POST["name"])) {
      echo $_POST["name"];
    }
    else {
      echo "not set";
    }
    echo "</p>";

    echo "<p>Employee ID: ";
    if(!empty($_POST["id"])) {
      echo $_POST["id"];
    }
    else {
      echo "not set";
    }
    echo "</p>";

    echo "<p>Phone Number: ";
    if(!empty($_POST["phone"])) {
      echo $_POST["phone"];
    }
    else {
      echo "not set";
    }
    echo "</p>";

    echo "<p>Email Address: ";
    if(!empty($_POST["email"])) {
      echo $_POST["email"];
    }
    else {
      echo "not set";
    }
    echo "</p>";

    echo "<p>Employee Type: ";
    if(isset($_POST["type"])) {
      echo $_POST["type"];
    }
    else {
      echo "not set";
    }
    echo "</p>";

    echo "<p>Project: ";
    if(isset($_POST["project"])) {
      echo $_POST["project"];
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
    <title>Input</title>
    <link rel="stylesheet" type="text/css" href="StyleSheet.css">
  </head>

<?php include "Header.php"; ?>

<?php
echo <<<_END
<div class="session">
<table>
<tr>
  <td>

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
  </td>
  <td>
_END;

  display();


echo <<<_END
  </td>
</tr>
</table>
</div>
_END;
?>

<?php include "Footer.php"; ?>

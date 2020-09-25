<?php
  session_start();

  unset($_SESSION["username"]);
  unset($_SESSION["password"]);
  unset($_SESSION["adminid"]);
  unset($_SESSION["adminlevel"]);
  unset($_SESSION["lastlogin"]);

  header("Location: login.php");
  exit;

 ?>

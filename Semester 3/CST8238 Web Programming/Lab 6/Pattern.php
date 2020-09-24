<?php

  function pattern1() {
    for($i = 0; $i < 6; $i++) {
      echo "<p>";
      for($j = 0; $j < 5 - $i; $j++) {
        echo "&nbsp;";
      }
      for($j = 0; $j < 1 + ($i * 2); $j++) {
        echo "*";
      }
      for($j = 0; $j < 5 - $i; $j++) {
        echo "&nbsp;";
      }
      echo "</p>";
    }
  }

  function pattern2() {
    for($i = 0; $i < 6; $i++) {
      echo "<p>";
      for($j = 0; $j < $i; $j++) {
        echo "&nbsp;";
      }
      for($j = 0; $j < 1 + ((5 - $i)* 2); $j++) {
        echo "*";
      }
      for($j = 0; $j <$i; $j++) {
        echo "&nbsp;";
      }
      echo "</p>";
    }


  }

?>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Pattern</title>
    <link rel="stylesheet" type="text/css" href="StyleSheet.css">
  </head>
  <body>
    <?php
      include "Header.php";

      echo "<div id=\"content\">";
      pattern1();
      pattern2();
      echo "</div>";

      include "Footer.php";
    ?>
  </body>
</html>

<?php



?>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>DivideByThree</title>
    <link rel="stylesheet" type="text/css" href="StyleSheet.css">
  </head>
  <body>
    <?php
      include "Header.php";

      echo "<div id=\"content\">";
      $i = 99;
      while ($i > 0) {
        if($i % 3 == 0) {
          $not = "a";
        }
        else {
          $not = "NOT a";
        }
        echo "<p>$i bottles of beer is $not multiple of 3...</p>";
        $i--;
      }
      echo "</div>";

      include "Footer.php";
    ?>
  </body>
</html>

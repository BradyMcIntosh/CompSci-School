<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Index</title>
    <link rel="stylesheet" type="text/css" href="StyleSheet.css">
  </head>
  <body>
    <?php
      include "Header.php";

      echo "<div id=\"content\">";
      $i = 99;
      while ($i > 0) {
        echo "<p>$i bottles of beer on the wall!</p>";
        echo "<p>$i bottles of beer!</p>";
        echo "<p>Take one down, pass it around,</p>";
        $i--;
        echo "<p>$i bottles of beer on the wall!</p>";
        echo "<p> </p>";
      }
      echo "<p>There are no more bottles of beer.</p>";
      echo "</div>";

      include "Footer.php";
    ?>
  </body>
</html>

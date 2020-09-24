<?php
  $calendar = array(1 => "January", 2 => "February", 3 => "March", 4 => "April", 5 => "May", 6 => "June", 7 => "July", 8 => "August", 9 => "September", 10 => "October", 11 => "November", 12 => "December");
?>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Array1</title>
    <link rel="stylesheet" type="text/css" href="StyleSheet.css">
  </head>
  <body>
    <?php
      include "Header.php";
      echo "<h1>FOR Loop</h1>";

      for($i = 1; $i <= sizeof($calendar); $i++) {
        echo "<p>Month $i:";
        print_r($calendar[$i]);
        echo "</p>";
      }

      echo "<h1>FOREACH Loop</h1>";
      $i = 1;
      foreach ($calendar as $month) {
        echo "<p>Month $i: $month</p>";
        $i++;
      }

      echo "<h1>WHILE Loop</h1>";
      $i = 1;
      while ($i <= sizeof($calendar)) {

        $month = $calendar[$i];
        echo "<p>Month $i: $month has ";
        switch($i) {
          case 4: case 6: case 9: case 11:
            echo "30";
            break;
          case 2:
            echo "28 or 29";
            break;
          default:
            echo "31";
            break;
        }
        echo " days.</p>";
        $i++;
      }

      include "Footer.php";
    ?>
  </body>
</html>

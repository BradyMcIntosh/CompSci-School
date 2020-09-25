

<h1>Table</h1>

<?php

  class UtilC {

    function __construct() {}

    public function isPrime($p) {
      if ($p < 2) {
          return false;
      }
      for ($i = 2; $i <= $p / 2; $i++) {
          if ($p % $i == 0) {
              return false;
          }
      }

      return true;
  }

    public function isFactor($f, $b) {
      if($f % $b == 0) {
        return true;
      }
      else {
        return false;
      }
    }
  }

  echo "<table style=\"border: 1px solid black;\">";
  $ut = new UtilC();
  $p = 1;
  $t = 1;
  for($i = 0; $i < 28; $i++) {
    do {
      $p++;
    } while (!$ut->isPrime($p));
    do {
      $t++;
    } while (!$ut->isFactor($t, 3));
    if($p < 28 || $t < 28) {
      echo "<tr style=\"border: 1px solid black;\">";

      echo "<td style=\"border: 1px solid black;\">";
      if($p < 28) {
        echo $p;
      }
      echo "</td>";

      echo "<td style=\"border: 1px solid black;\">";
      if($t < 28) {
        echo $t;
      }
      echo "</td>";
      echo "</tr>";
    }
  }

  echo "</table>";
 ?>

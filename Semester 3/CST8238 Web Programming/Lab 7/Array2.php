<?php
  $Product = array(
    array("Category" => "Printer", "Brand" => "Epson", "Quantity" => 100, "Price" => 2500),
    array("Category" => "Printer", "Brand" => "Canon", "Quantity" => 100, "Price" => 3000),
    array("Category" => "Printer", "Brand" => "Xerox", "Quantity" => 500, "Price" => 2000),

    array("Category" => "Laptop", "Brand" => "Apple", "Quantity" => 200, "Price" => 2000),
    array("Category" => "Laptop", "Brand" => "HP", "Quantity" => 300, "Price" => 1500),
    array("Category" => "Laptop", "Brand" => "Toshiba", "Quantity" => 100, "Price" => 1200),

    array("Category" => "TV", "Brand" => "Samsun", "Quantity" => 500, "Price" => 1200),
    array("Category" => "TV", "Brand" => "LG", "Quantity" => 500, "Price" => 1050),
    array("Category" => "TV", "Brand" => "Toshiba", "Quantity" => 200, "Price" => 1000)
  );


?>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Array2</title>
    <link rel="stylesheet" type="text/css" href="StyleSheet.css">
  </head>
  <body>
    <?php
      include "Header.php";

      echo "<h1>Var Dump</h1>";
      var_dump($Product);

      echo "<h1>Element Display</h1>";
      $i = 0;
      foreach ($Product as $thing) {
        $cat = $thing["Category"];
        $brand = $thing["Brand"];
        $quant = $thing["Quantity"];
        $price = $thing["Price"];
        if($i % 3 == 0) {
          echo "<p><b><u>$cat</u></b></p>";
        }
        echo "<p>Brand: $brand</p>";
        echo "<p>Quantity: $quant</p>";
        echo "<p>Price: $price</p>";
        echo "<p></p>";
        $i++;
      }

      echo "<h1>Table Display</h1>";
      echo "<table>";

      echo "<tr>";
      echo "<th>Category</th>";
      echo "<th>Brand</th>";
      echo "<th>Quantity</th>";
      echo "<th>Price</th>";
      echo "</tr>";

      foreach ($Product as $thing) {
        echo "<tr>";

        $cat = $thing["Category"];
        $brand = $thing["Brand"];
        $quant = $thing["Quantity"];
        $price = $thing["Price"];
        if($i % 3 == 0) {
          echo "<td>$cat</td>";
        }
        else {
          echo "<td></td>";
        }
        echo "<td>$brand</td>";
        echo "<td>$quant</td>";
        echo "<td>$price</td>";
        $i++;

        echo "</tr>";
      }
      echo "</table>";

      include "Footer.php";
    ?>
  </body>
</html>

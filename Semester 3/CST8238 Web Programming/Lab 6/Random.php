<?php
  function display() {

    if(empty($_POST["range1"])) {
      $n1 = 1;
    }
    else {
      $n1 = $_POST["range1"];
    }
    if(empty($_POST["range2"])) {
      $n2 = 99;
    }
    else {
      $n2 = $_POST["range2"];
    }


    $num = rand($n1, $n2);
    if($num % 2 == 0) {
      $evod = "even";
    }
    else {
      $evod = "odd";
    }

    printf("<p>The web server has selected the random number %d from the range %d to %d</p>", $num, $n1, $n2);
    printf("<p>%d bottles of beer can serve %s numbers of guests.</p>", $num, $evod);
  }
?>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Random</title>
    <link rel="stylesheet" type="text/css" href="StyleSheet.css">
  </head>
  <body>
    <?php include "Header.php"; ?>
    <div id="content">
      <p>How many beers would you like? Please select a maximum and minimum amount.</p>
      <form action="Random.php" method="post">
        Range min: <input type="number" name="range1"><br>
        Range max: <input type="number" name="range2"><br>
        <input type="submit">
      </form>
      <?php
        // if(isset($_POST['submit'])) {
          display();
        // }
      ?>
    </div>
    <?php include "Footer.php"; ?>
  </body>
</html>

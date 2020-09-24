<?php 
$fname = "Brady";
$lname = "McIntosh";
$snum = "040706980";

$h = "Hello ";
$w = "World!";
$d = date("H:i:s");
?>

<!DOCTYPE html>

<html>

	<head lang="en">
    	<meta charset="utf-8">
    	<title>Lab 5 Index</title>
	</head>
	
	<body>
    	<?php
    	   echo "<h1>Lab 5</h1>";
    	   echo "<h2>Body</h2>";
    	   echo "<p>First Name: $fname</p>";
           echo "<p>Last Name: $lname</p>";
           echo "<p>Student Number: $snum</p>";
           echo "<p>Server time: $d</p>";
           echo "<p>\"".$h.$w."\"</p>";
           echo "<hr>";
           echo "<h2>Links</h2>";
           echo "<p><a href=\"../Lab1\">Lab 1</p>";
           echo "<p><a href=\"../Lab2\">Lab 2</p>";
           echo "<p><a href=\"../Lab3\">Lab 3</p>";
           echo "<p><a href=\"../Lab4\">Lab 4</p>";
           echo "<p><a href=\"../Assignment1_Part1\">A1 Part 1</p>";
           echo "<p><a href=\"../Assignment1_Part2\">A1 Part 2</p>";
    	?>
	</body>

</html>



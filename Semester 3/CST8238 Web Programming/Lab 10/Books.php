<?php

  $q = $_GET["q"];
  $y = array();

  $xml = file_get_contents("Books.xml");
  $xmlDoc = new DOMDocument();
  $xmlDoc->loadXML($xml);

  $books = $xmlDoc->getElementsByTagName('book');

  // add all matching nodes to $y
  foreach($books as $currBook) {
    foreach($currBook->childNodes as $node) {
      if($node->nodeName == "genre") {
        if($node->nodeValue == $q) {
          $y[] = ($node->parentNode);
        }
      }
    }
  }

  foreach($y as $par) {
    foreach ($par->childNodes as $node) {
      if($node->nodeName == "author" || $node->nodeName == "title" || $node->nodeName == "genre" || $node->nodeName == "price" || $node->nodeName == "publish_date" || $node->nodeName == "description") {
        echo "<b>" . $node->nodeName . ": </b>";
        echo $node->nodeValue;
        echo "<br>";
      }
    }
    echo "<br>";
  }

 ?>

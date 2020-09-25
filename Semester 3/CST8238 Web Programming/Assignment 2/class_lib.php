<?php
  class Eventitem {
    private $eventName;
    private $eventDate;
    private $eventDesc;
    private $eventPrice;

    function __construct($name, $date, $desc, $price) {
      $this->eventName = $name;
      $this->eventDate = $date;
      $this->eventDesc = $desc;
      $this->eventPrice = $price;
    }

    public function getName() {
      return $this->eventName;
    }

    public function getDate() {
      return $this->eventDate;
    }

    public function getDesc() {
      return $this->eventDesc;
    }

    public function getPrice() {
      return $this->eventPrice;
    }
  }
 ?>

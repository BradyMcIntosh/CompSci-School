<?php
  interface Employee {
    public function displayEmployeeInfo();
  }

  class Management implements Employee {
    protected $sin;
    protected $age;
    protected $salary;

    function __construct($sin, $age, $salary) {
      $this->sin = $sin;
      $this->age = $age;
      $this->salary = $salary;
    }

    public function displayEmployeeInfo() {
      $string = "<b>SIN:</b> " . $this->sin . ", ";
      $string .= "<b>Age:</b> " . $this->age . ", ";
      $string .= "<b>Salary:</b> " . $this->salary . ", ";
      return $string;
    }
  }

  class Manager extends Management {
    private $adminLevel;

    function __construct($sin, $age, $salary, $adminLevel) {
      parent::__construct($sin, $age, $salary);
      $this->adminLevel = $adminLevel;
    }

    public function displayEmployeeInfo() {
      $string = parent::displayEmployeeInfo();
      $string .= "<b>Admin Level:</b> " . $this->adminLevel;
      return $string;
    }
  }

  class Development implements Employee {
    protected $sin;
    protected $age;
    protected $salary;

    function __construct($sin, $age, $salary) {
      $this->sin = $sin;
      $this->age = $age;
      $this->salary = $salary;
    }

    public function displayEmployeeInfo() {
      $string = "<b>SIN:</b> " . $this->sin . ", ";
      $string .= "<b>Age:</b> " . $this->age . ", ";
      $string .= "<b>Salary:</b> " . $this->salary . ", ";
      return $string;
    }
  }

  class ITSpecialist extends Development {
    private $projectAssigned;

    function __construct($sin, $age, $salary, $projectAssigned) {
      parent::__construct($sin, $age, $salary);
      $this->projectAssigned = $projectAssigned;
    }

    public function displayEmployeeInfo() {
      $string = parent::displayEmployeeInfo();
      $string .= "<b>Assigned Project:</b> " . $this->projectAssigned;
      return $string;
    }
  }
?>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>object</title>
    <link rel="stylesheet" type="text/css" href="StyleSheet.css">
  </head>
  <body>
    <?php
      include "Header.php";

      $man1 = new Manager("12345", 32, 72500, "R33");
      $man2 = new Manager("qwert", 47, 95000, "P33");

      $it1 = new ITSpecialist("ggfgd", 19, 17000, "Washing Dishes");
      $it2 = new ITSpecialist("loool", 90, 63000, "Biding Time");

      echo "<h1>Managers</h1>";
      echo "<p>" . $man1->displayEmployeeInfo() . "</p>";
      echo "<p>" . $man2->displayEmployeeInfo() . "</p>";
      echo "<h1>IT Specialists</h1>";
      echo "<p>" . $it1->displayEmployeeInfo() . "</p>";
      echo "<p>" . $it2->displayEmployeeInfo() . "</p>";

      include "Footer.php";
    ?>
  </body>
</html>

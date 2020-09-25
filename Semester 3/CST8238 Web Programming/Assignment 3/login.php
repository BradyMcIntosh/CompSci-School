<?php

  session_start();
  require "db_config.php";

  $warning = "";

  if(empty($_POST["username"]) || empty($_POST["password"])) {
    $warning = "Please make sure all fields are completed and options selected.<br>";

    if(empty($_POST["username"])) {
      $warning = $warning . "\"Username\" must not be blank.<br>";
    }
    if(empty($_POST["password"])) {
      $warning = $warning . "\"Password\" must not be blank.<br>";
    }
  }
  else {
    mysqli_select_db($conn, $database);

    $sqlQuery = "SELECT * FROM adminusers WHERE Username = '". $_POST["username"]."' AND Password = '".$_POST["password"]."'";

    $result = mysqli_query($conn,$sqlQuery);
    $rowCount = mysqli_num_rows($result);

    if($rowCount == 0) {
      $warning = "That is not a valid username or password.";
    }
    else {
      $row = mysqli_fetch_row($result);
      $_SESSION["username"] = $_POST["username"];
      $_SESSION["password"] = $_POST["password"];
      $_SESSION["adminid"] = $row[0];
      $_SESSION["adminlevel"] = $row[3];
      $date = date("Ymd");
      $_SESSION["lastlogin"] = $date;

      $sqlQuery = "UPDATE adminusers SET LastLogin = '".$date. "' WHERE Username = '". $_POST["username"]."' AND Password = '".$_POST["password"]."'";

      if (mysqli_query($conn, $sqlQuery)) {
        header("Location: internal.php");
        exit;
      } else {
      	$warning = "Login update failed: " . mysqli_error($conn) . "<br>" . $sqlQuery;
      }
    }
  }
?>

<?php include("header.php"); ?>

  <div id="content" class="clearfix">
      <aside>
              <h2>Mailing Address</h2>
              <h3>1385 Woodroffe Ave<br>
                  Ottawa, ON K4C1A4</h3>
              <h2>Phone Number</h2>
              <h3>(613)727-4723</h3>
              <h2>Fax Number</h2>
              <h3>(613)555-1212</h3>
              <h2>Email Address</h2>
              <h3>info@wpeatery.com</h3>
      </aside>
      <div class="main">

        <h1>Log in to access the internal website.</h1>
        <?php echo $warning; ?>
        <form name="login" id="login" method="post">
            <table>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username" id="username" size='40'></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="text" name="password" id="password" size='40'></td>
                </tr>
                <tr>
                    <td colspan='2'><input type='submit' name='btnSubmit' id='btnSubmit' value='Log in'>&nbsp;&nbsp;<input type='reset' name="btnReset" id="btnReset" value="Reset Form"></td>
                </tr>
            </table>
        </form>

      </div><!-- End Main -->
  </div><!-- End Content -->

<?php include("footer.php"); ?>

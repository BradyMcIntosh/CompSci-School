<?php
  require "db_config.php";

  $warning = "";

  if(empty($_POST["customerfName"]) || empty($_POST["customerlName"]) || empty($_POST["phoneNumber"]) || empty($_POST["emailAddress"]) || empty($_POST["username"]) || !isset($_POST["referral"])) {
    $warning = "Please make sure all fields are completed and options selected.<br>";

    if(empty($_POST["customerfName"])) {
      $warning = $warning . "\"First Name\" must not be blank.<br>";
    }
    if(empty($_POST["customerlName"])) {
      $warning = $warning . "\"Last Name\" must not be blank.<br>";
    }
    if (empty($_POST["phoneNumber"])) {
      $warning = $warning . "\"Phone Number\" must not be blank.<br>";
    }
    if (empty($_POST["emailAddress"])) {
      $warning = $warning . "\"Email Address\" must not be blank.<br>";
    }
    if (empty($_POST["username"])) {
      $warning = $warning . "\"Username\" must not be blank.<br>";
    }
    if (!isset($_POST["referral"])) {
      $warning = $warning . "You must specify a referrer.<br>";
    }

  }
  else {

    mysqli_select_db($conn, $database);

    $sqlQuery = "SELECT * FROM mailingList WHERE EmailAddress = '". $_POST["email"]."'";

    $result = mysqli_query($conn,$sqlQuery);

    $rowCount = mysqli_num_rows($result);

    if($rowCount != 0) {
      $warning = $warning . "That email address has already been used. Please use another one.";
    }
    else {

      $sqlQuery = "INSERT INTO mailingList (firstName, lastName, phoneNumber, EmailAddress, userName, referrer) VALUES('".$_POST["customerfName"]."', '".$_POST["customerlName"]."', '".$_POST["phoneNumber"]."', '".$_POST["emailAddress"]."', '".$_POST["username"]."', '".$_POST["referrer"]."')";

      if (!mysqli_query($conn, $sqlQuery)) {
      	$warning = "Contact could not be added: " . mysqli_error($conn);
      }
      else {
        $warning = "Contact added successfully. Thanks OwO";
      }
    }
    mysqli_close($conn);
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
                    <h1>Sign up for our newsletter</h1>
                    <p>Please fill out the following form to be kept up to date with news, specials, and promotions from the WP eatery!</p>
                    <?php echo $warning; ?>
                    <form name="frmNewsletter" id="frmNewsletter" method="post">
                        <table>
                            <tr>
                                <td>First Name:</td>
                                <td><input type="text" name="customerfName" id="customerfName" size='40'></td>
                            </tr>
                            <tr>
                                <td>Last Name:</td>
                                <td><input type="text" name="customerlName" id="customerlName" size='40'></td>
                            </tr>
                            <tr>
                                <td>Phone Number:</td>
                                <td><input type="text" name="phoneNumber" id="phoneNumber" size='40'></td>
                            </tr>
                            <tr>
                                <td>Email Address:</td>
                                <td><input type="text" name="emailAddress" id="emailAddress" size='40'>
                            </tr>
                             <tr>
                                <td>Username:</td>
                                <td><input type="text" name="username" id="username" size='20'>
                            </tr>
                            <tr>
                                <td>How did you hear<br> about us?</td>
                                <td>
                                   <select name="referral" size="1">
                                      <option>Select referer</option>
                                      <option value="newspaper">Newspaper</option>
                                      <option value="radio">Radio</option>
                                      <option value="tv">Television</option>
                                      <option value="other">Other</option>
                                   </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan='2'><input type='submit' name='btnSubmit' id='btnSubmit' value='Sign up!'>&nbsp;&nbsp;<input type='reset' name="btnReset" id="btnReset" value="Reset Form"></td>
                            </tr>
                        </table>
                    </form>
                </div><!-- End Main -->
            </div><!-- End Content -->

    <?php include("footer.php"); ?>

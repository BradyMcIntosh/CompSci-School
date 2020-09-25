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

        <?php
          require "db_config.php";

          mysqli_select_db($conn, $database);

          $sqlQuery = "SELECT * FROM mailingList";

          $result = mysqli_query($conn, $sqlQuery);

          $rowCount = mysqli_num_rows($result);
          if($rowCount == 0)
            echo "<b>*** There are no rows to display from the mailingList table ***<b>";
          else
          {
            echo "<h2>Mailing List Data</h2>";
            echo "<table id=\"data\">";
            echo "<th>Full Name</th>";
            echo "<th>Email Address</th>";
            echo "<th>Phone Number</th>";

            for($i=0; $i<$rowCount; ++$i) {
              $row = mysqli_fetch_row($result);
              echo "<tr>";
              echo "<td>".$row[1]. " " .$row[2]."</td>";
              echo "<td>".$row[4]."</td>";
              echo "<td>".$row[3]."</td>";
              echo "</tr>";
            }
            echo "</table>";
          }

          mysqli_close($dbConnection);
        ?>

      </div><!-- End Main -->
    </div><!-- End Content -->

<?php include("footer.php"); ?>

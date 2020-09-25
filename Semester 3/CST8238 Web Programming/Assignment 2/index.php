<?php
  include "class_lib.php";

  $curday = date("l");

  $events = array(
    new Eventitem("St. Patty's Day Party", "Tuesday Oct 17, 2017", "Join us for an authentic Irish four course meal, complete with shepard's pie and one green beer!", 35.00),
    new Eventitem("Samy's Spring Fling!", "Saturday Oct 18, 2017", "Join us for to kick off the beginning of spring! This event will include 4 of Samy's infamous appetizers and one cocktail!", 40.00)
  );
 ?>

<!DOCTYPE html>
<html>
  <?php include "header.php"; ?>
            <div id="content" class="clearfix">
                <aside>
                        <h2><?php echo $curday; ?>'s Specials</h2>
                        <hr>
                        <img src="images/burger_small.jpg" alt="Burger" title="Monday's Special - Burger">
                        <h3>The WP Burger</h3>
                        <p>Freshly made all-beef patty served up with homefries - $14</p>
                        <hr>
                        <img src="images/kebobs.jpg" alt="Kebobs" title="WP Kebobs">
                        <h3>WP Kebobs</h3>
                        <p>Tender cuts of beef and chicken, served with your choice of side - $17</p>
                        <hr>
                        <h2>Private Dining</h2>
                        <img src="images/dining_room_sm.jpg" width="228" alt="Dining Room" title="The WP Eatery Dining Room">
                        <p>Call us to find out more about our private dinning options.</p>
                </aside>
                <div class="main">
                    <h1>Welcome to WP Eatery!</h1>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum</p>
                    <h3>Upcoming Events ...</h3>

                    <?php
                      foreach($events as $e) {
                        $name = $e->getName();
                        $date = $e->getDate();
                        $desc = $e->getDesc();
                        $price = $e->getPrice();
                        echo "<p>";
                        echo "<strong class=\"event\">$name</strong><br/>";
                        echo "<strong>Date:</strong> $date<br/>";
                        echo "<strong>Price:</strong> $$price<br/>";
                        echo $desc;
                        echo "</p>";
                      }
                    ?>

                    <h2>Book your Private Party!</h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum</p>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
                </div><!-- End Main -->
            </div><!-- End Content -->
  <?php include "footer.php"; ?>
</html>

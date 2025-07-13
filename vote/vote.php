<html>
  <head>
    <title>Results of the Vote</title>
  </head>
  <body bgcolor="#CCFFCC" text="330099">
    <?php
	  #error_reporting(E_ALL);
	  #ini_set('display_errors', 1);
	  
	  #get the value
	  $choice = $_POST['choice'];
	  
	  #security locks
	  $fp = fopen("vote.dat", "r+b");
	  $flock = flock($fp, LOCK_EX);
	  
	  #Count variables
	  $shrekcount = (int) fgets($fp, 80);
	  $kittycount = (int) fgets($fp, 80);
	  $snoopycount = (int) fgets($fp, 80);
	  
	  #increment variables
	  if ($choice == "shrek") {
		  $shrekcount = $shrekcount + 1;
	  }
	  else if ($choice == "kitty") {
		  $kittycount = $kittycount + 1;
	  }
	  else if ($choice == "snoopy") {
		  $snoopycount = $snoopycount + 1;
	  }
	  else {
		  # if the user didn't vote, don't increment anything
	  }
	  
	  # put the three numbers back in the file, one per line
	  fseek ($fp, 0);
	  fwrite($fp, "$shrekcount\n$kittycount\n$snoopycount\n");
	  
	  flock($fp, LOCK_UN);
	  fclose($fp);
	  
	  if ($choice == "shrek") {
		  echo "<h2>You voted for Shrek!</h2> \n";
	  }
	  else if($choice == "kitty") {
		  echo "<h2>You voted for Hello Kitty!</h2> \n";
	  }
	  else if ($choice == "snoopy") {
		  echo "<h2>You voted for Snoopy</h2>\n";
	  }
	  else {
		  echo "<h2>You didn't vote!</h2>\n";
	  }
	  ?>
	  <table>
	    <tr>
		  <td>Shrek</td>
		  <td><?php echo $shrekcount; ?></td>
		</tr>
		<tr>
		  <td>Hello Kitty</td>
		  <td><?php echo $kittycount; ?></td>
		</tr>
		<tr>
		  <td>Snoopy</td>
		  <td><?php echo $snoopycount; ?></td>
		</tr>
	  </table>
   </body>
<html>


<html>
<head>
  <title>Results of the Vote</title>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
  <style>
    body {
      background-color:rgb(43, 43, 43);
    }

    .header {
      text-align: center;
    }

    table {
      border-spacing: 50px;
      width = 50%;
    }

    td {
      width = 10%;
    }
  </style>
</head>
<body>
<?php
    # Uncomment the following two lines to see errors in PHP.
    error_reporting(E_ALL);
    ini_set('display_errors', 1);
    # see which figure the user selected and set
    # variable $choice to the value associated with 
    # the radio button selected
    $choice = $_GET['choice'];

    $fp = fopen("vote.dat", "r+b");
    
    flock($fp, LOCK_EX);
    $shreksoncount = (int) fgets($fp, 80);
    $swansonbondcount = (int) fgets($fp, 80);
    $swansteriocount = (int) fgets($fp, 80);
    $swolsoncount = (int) fgets($fp, 80);

    switch($choice) {
        case "shrekson":
            $shreksoncount++;
            echo "<h2 class=\"header\">You voted for Shrekson!</h2>";
            break;
        case "swanson-bond":
            $swansonbondcount++;
            echo "<h2 class=\"header\">You voted for Joeles Swond!</h2>";
            break;
        case "swansterio":
            $swansteriocount++;
            echo "<h2 class=\"header\">You voted for Swansterio!</h2>";
            break;
        case "swolson":
            $swolsoncount++;
            echo "<h2 class=\"header\">You voted for Swolson!</h2>";
            break;
        default:
            echo "<h2 class=\"header\">You didn't vote!</h2>";
    }

    fseek($fp, 0);
    fwrite($fp, "$shreksoncount\n$swansonbondcount\n$swansteriocount\n$swolsoncount");
    flock($fp, LOCK_UN);
    fclose($fp);

    ?>

<h5 class="header">So far the votes are as follows:</h5><br><br>
<div style="width: 30%; text-align: center; margin: auto;">
  <table class="centered highlight">
    <tr>
      <td>Shrekson</td><td><?php echo $shreksoncount; ?></td>
    </tr>
    <tr>
      <td>Joeles Swond</td><td><?php echo $swansonbondcount; ?></td>
    </tr>
    <tr>
      <td>Swansterio</td><td><?php echo $swansteriocount; ?></td>
    </tr>
    <tr>
      <td>Swolson</td><td><?php echo $swolsoncount; ?></td>
    </tr>
  </table>
</div>
</body>
</html>
<!DOCTYPE html>

<html lang="en">
  <head>
 <!--This is in charge of handling the style of each voted page-->   
    <style>
    
    img {
        width:200px;
        height:200px;
        float:right;
    }
    body {
        background-color:black;
    }
    h1 {
        margin-left:25%;
        color:white;

    }
    table {
    margin-top:25%;
    border: 2px dotted;
    width:50%;
    height:30%;
    text-align:center;
    margin-left:25%;

}
    th,td {
    color:white;
    font-size:40px;
    font-weight:bolder;
    border:1px solid;
    background-color:black;
    }
    </style>
    <meta charset='utf-8'>
    <title>RESULTS</title>
  </head>

<body>
<!--Start of the php program, which grabs choice and writes to a file to keep count of votes.-->
<?php
$choice = $_GET['choice'];
$fp = fopen("vote.dat", "r+b");
flock($fp, LOCK_EX);

$shrekcount = (int) fgets($fp, 80);
$kittycount = (int) fgets($fp, 80);
$snoopycount = (int) fgets($fp, 80);
$goofycount = (int) fgets($fp, 80);

    if ($choice == "shrek"){
        $shrekcount++;
    }
    else if ($choice == "kitty"){
        $kittycount++;
    }
    else if ($choice == "snoopy"){
        $snoopycount++;
    }
    else if ($choice == "goofy"){
        $goofycount++;
    }
    else{
        echo "<h1> please go back and cast a vote weirdo </h1>";
    }
fseek($fp, 0);
fwrite($fp, "$shrekcount\n$kittycount\n$snoopycount\n$goofycount\n");

flock($fp, LOCK_UN);
fclose($fp);
# we use php variables to hold the string which overwrites the base style page.
    $header = "<h1>You voted for ";
    $img = "<img src=";
# this one is for shrek
    $bkgimgsh = "<style>
                body{
                    
                    background-image:url(\"images/shrekswamp.png\");
                    background-size: cover;
                    background-repeat:no-repeat;
                }
                </style>";
# this one is for hellokitty
    $bkgimgk = "<style>
                body{
                    
                    background-image:url(\"images/kittyland.png\");
                    background-size: cover;
                    background-repeat:no-repeat;
                }
                </style>";
# this one is for snoopy
    $bkgimgsn = "<style>
                body{
                    
                    background-image:url(\"images/snoopyhome.png\");
                    background-size: cover;
                    background-repeat:no-repeat;
                }
                </style>";
# this one is for goofy                
    $bkgimggo = "<style>
                body{
                    
                    background-image:url(\"images/goofyland.jpg\");
                    background-size: cover;
                    background-repeat:no-repeat;
                }
                table{
                    margin-left:0%;
                    width:30%;
                }

                </style>";
# below is how the webpage decides what to show dependent on choice of user. you will see we do small changes to the tables style to make everything be easily visible
if ($choice == "shrek"){
    echo $header."Shrek the big green ogre!</h1>".$img."\"images/shrek.png\">";
    echo $bkgimgsh;
}
else if ($choice == "kitty"){
    echo $header."The weird catgirl!</h1>".$img."\"images/hellokitty.png\">";
    echo $bkgimgk;
    echo "<style>
            h1{
                color:green;
            }
            th,td{
                   color:black;
                   background-color:pink;
            }
            </style>";
    
}
else if ($choice == "snoopy"){
    echo $header."Snoopy the depressed DOG!</h1>".$img."\"images/snoopy.png\">";
    echo $bkgimgsn;
}

# we can do style changes inside of the echo, or at the start where we initialized the variable. below we use varaible example, and above you will see style changes inside of echo tags.
else if ($choice == "goofy"){
    echo $header."Goofy the SINGLE DAD DOING HIS BEST</h1>".$img."\"images/goofy.png\">";
    echo $bkgimggo;
}
else{
    echo $header."nothing, what is wrong with you?</h1>";
}




# 1. Uncomment the following to see errors in PHP.
#error_reporting(E_ALL);
#ini_set('display_errors', 1);
# 2. see which figure the user selected and set
# variable $choice to the value associated with 
# the radio button selected
# 3. open the file named vote.dat for reading and writing (fopen)
# 4. lock the file so nobody else can open it right now (flock)
# 5. read first number in file and put it in $shrekcount (fgets)
# 6. read second number and put it in $kittycount
# 7. read third number and put it in $snoopycount
# 8. increment the right variable (big if)
# 9. put the three numbers back in the file, one per line 
#(fseek back to start, then fwrite)
# 10. unlock and close the file (flock, fclose)
# 11. print the user's vote acknowledgement (print you voted for ...)


//End the php
?> 
<!-- print the vote counts in a table using HTML mixed with php-->

<!--Below you will see how we have our table appear with some php to show our vote counter-->
<table>
<th>PIC</th><th>Votes</th>
    <tr>
        <td>SHRONK</td><td><?php echo $shrekcount; ?></td>
    </tr>
    <tr>
        <td>AYAYAYKITTY</td><td><?php echo $kittycount;?></td>
    </tr>
    <tr>
        <td>SNOOP DOGGY DOG </td><td><?php echo $snoopycount;?></td>
    </tr>
    <tr>
        <td> SINGLE DAD HUNK GOOFY </td><td><?php echo $goofycount;?></td>
    </tr>
</table>
</body>
</html>
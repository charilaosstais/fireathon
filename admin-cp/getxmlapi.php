<?php

// get latt and long from device
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "mcm";

function parseToXML($htmlStr)
{
$xmlStr=str_replace('<','&lt;',$htmlStr);
$xmlStr=str_replace('>','&gt;',$xmlStr);
$xmlStr=str_replace('"','&quot;',$xmlStr);
$xmlStr=str_replace("'",'&#39;',$xmlStr);
$xmlStr=str_replace("&",'&amp;',$xmlStr);
return $xmlStr;
}


// Opens a connection to a MySQL server
$link= new mysqli('localhost', $username, $password,$dbname);
/* check connection */
if (mysqli_connect_errno()) {
    printf("Connect failed: %s\n", mysqli_connect_error());
    exit();
}


// Select all the rows in the markers table
$query = "SELECT * FROM location ";
$result = mysqli_query($link,$query);
if (!$result) {
  die('Invalid query: ' . mysql_error());
}

header("Content-type: text/xml");

// Start XML file, echo parent node
echo '<markers>';

// Iterate through the rows, printing XML nodes for each
while ($row = @mysqli_fetch_assoc($result)){
  // Add to XML document node
  echo '<marker ';
  echo 'id="' . $row['id'] . '" ';
  echo 'name="' . parseToXML($row['phone_id']) . '" ';
  echo 'address="Πολίτης" ';
  echo 'lat="' . $row['latitude'] . '" ';
  echo 'lng="' . $row['longtitude'] . '" ';
  echo 'type="A" ';
  echo '/>';
}

// End XML file
echo '</markers>';
mysqli_close($link);
?>
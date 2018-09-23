
<?php
$N_latt = 38.014794;
$E_long = 22.654615;

$S_latt = 37.960300;
$W_long = 22.669353;

// get latt and long from device
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "mcm";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

for ($x = 0; $x <= 200; $x++){
sleep(5);

$sql = "SELECT * FROM location";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
     $devices[]=array(
						'phone_id' => $row["phone_id"],
						'latitude'=> $row["latitude"],
						'longtitude'=> $row["longtitude"]);
    }
} else {
    echo "0 results";
}



foreach ($devices as $d ){
	$latt=$d['latitude'];
	$long=$d['longtitude'];
	
	if ($latt==0 or $long==0) {
		$l1 = number_format((float)rand(1,55000)/100000,6);
		$l2 = number_format((float)rand(1,55262)/100000,6);
		$latt=  $S_latt + (float)$l1 ;
		$long= $W_long + (float)$l2;
		
	}
	
	echo "moving Device ".$d['phone_id']."<br>";
	
	$latt+=(float)((int)(rand(0,2)-1)/1000);
	$long+=(float)((int)(rand(0,2)-1)/1000);


	$sql = "UPDATE location SET latitude='".$latt."',longtitude='".$long."'  WHERE phone_id='". $d['phone_id']."'";
	
	if ($conn->query($sql) === TRUE) {
		echo "Record updated successfully";
	} else {
		echo "Error updating record: " . $conn->error;
	}
	
	
	
	
	
	
}


}


$conn->close();



?>

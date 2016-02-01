<?php
 
// response json
$json = array();
 
/**
 * Registering a user device
 * Store reg id in users table
 */
if (isset($_POST["uid"])&&isset($_POST["Name"])&& isset($_POST["Email"]) && isset($_POST["Password"]) && isset($_POST["RingKey"])isset($_POST["CameraKey"])&& isset($_POST["Longitude"])isset($_POST["Latitude"]) ) {
    
	$uid = $_POST["uid"];
	$Name = $_POST["Name"];
	$Email = $_POST["Email"];
     $Password = $_POST["Password"];
	$RingKey = $_POST["RingKey"];
	$Longitude = $_POST["Longitude"];
	$Latitude = $_POST["Latitude"];
	$CameraKey = $_POST["CameraKey"];
	
    // Store user details in db
    include_once './db_functions.php';
    
 
    $db = new DB_Functions();
    
 
    $res = $db->storeUser($uid, $Name, $Email, $Password, 		$RingKey, $Longitude, $Latitude, $CameraKey);

 
    //echo $result;
} else {
    // user details missing
}
?>
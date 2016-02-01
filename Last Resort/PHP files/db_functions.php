<?php
 
class DB_Functions {
 
    private $db;
 
    //put your code here
    // constructor
   
	 function __construct() {
        include_once './db_connect.php';
        // connecting to database
        $this->db = new DB_Connect();
        $this->db->connect();
    }
 
    // destructor
    function __destruct() {
         
    }
 
    /**
     * Storing new user
     * returns user details
     */
    public function storeUser($uid, $Name, $Email, $Password, 		$RinkKey, $Longitude, $Latitude, $CameraKey){
        $result = mysql_query("INSERT INTO user_details(uid ,Name ,Email,Password,RingKey,Longitude,Latitude, CameraKey) VALUES('$uid', '$Name', '$Email', '$Password', 		'$RingKey', '$Longitude', '$Latitude', '$CameraKey')");
        
// check for successful store
    
    }
 
    /**
     * Getting all users
     */
    /*
	public function getAllUsers() {
        $result = mysql_query("select * FROM user_");
        return $result;
    */
	}
 
}
 
?>
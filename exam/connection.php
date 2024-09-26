
<html> 
<body> 
 <?php 
 $cnx = mysqli_connect("localhost","root","");
 $db = mysqli_select_db($cnx,"gestion");
 $gestion= mysqli_query($cnx,"SELECT id_pr, designation, qte FROM gestion WHERE id = '" . $_GET['id_pr'] . "' ORDER BY id_pr");
 while($ligne = mysqli_fetch_row($gestion))
 { 
 echo "ID: " . $ligne[0] . "<br>\n"; 
 echo "Designation: " . $ligne[1] . "<br>\n"; 
  
 echo "Qte : " . $ligne[2] . "<br>\n"; 
 } 
 ?> 
</body> 
</html>

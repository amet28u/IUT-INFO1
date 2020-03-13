<?php
$titre = "puissance";
$content = <<<TXT
<table>
  <tr><th>i</th><th>i^i</th></tr>
TXT;

for ($i=0; $i<10; $i++){
  $content .= "<tr><td>$i</td><td>".pow($i, $i)."</td></tr>\n";
}
$content .= "</table>";

include 'template1.php';
?>

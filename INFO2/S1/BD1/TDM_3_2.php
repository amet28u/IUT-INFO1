<?php include 'html5_page.php'; begin_html('Exercice 3.1'); ?>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>
      <?php print $titre ?>
    </title>
    <style>
      body { background-color: black; color: white;}
      table, td, th { border: 1px solid white;}
      table { border-collapse: collapse;}
      td { text-align: right;}
    </style>
  </head>
  <body style="background-color: black; color: white;">
    <h1>
      <?php print $titre ?>
    </h1>
    <div id="content">
      <?php print $content ?>
    </div>
  </body>
</html>

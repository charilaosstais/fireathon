<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
<div id="mapdiv" style="height:500px; width:900px; background:#010100"></div>
<script
src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js">
</script>
  <script src="http://www.openlayers.org/api/OpenLayers.js"></script>
  <script>
 
    map = new OpenLayers.Map("mapdiv");
    map.addLayer(new OpenLayers.Layer.OSM());

    var lonLat = new OpenLayers.LonLat(  22.753837,38.015652 )
          .transform(
            new OpenLayers.Projection("EPSG:4326"), // transform from WGS 1984
            map.getProjectionObject() // to Spherical Mercator Projection
          );
          
    var zoom=16;

	function doNothing() {}  
    map.setCenter (lonLat, zoom);
  </script>
</body>

</html>
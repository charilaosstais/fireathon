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

    var lonLat = new OpenLayers.LonLat(  22.674029,37.974827 )
          .transform(
            new OpenLayers.Projection("EPSG:4326"), // transform from WGS 1984
            map.getProjectionObject() // to Spherical Mercator Projection
          );
          
    var zoom=11;
    
    var pointLayer = new OpenLayers.Layer.Markers( "Markers" );
    map.addLayer(pointLayer);
    
    $.getJSON('locations.json', function(data) {
    	console.log(data);
      $.each(data.locationList, function() {
    	//  console.log(this)
       // var pointFeatures = [];
        var px = this.logntitude;
        var py = this.latitude;
        // Create a lonlat instance and transform it to the map projection.
        var lonlat = new OpenLayers.LonLat(parseFloat(px), parseFloat(py));
        lonlat.transform(new OpenLayers.Projection("EPSG:4326"), map.getProjectionObject());
        var marker = new OpenLayers.Marker(lonlat);

        marker.display(true);
        var role = this.device.actor.teamActors[0].team.id;
        switch(role) {
	        case 0:
	        	marker.setUrl('resources/mapicons/person.png');
	            break;
	        case 1:
	        	marker.setUrl('resources/mapicons/firefighter.png');
	            break;
	        case 2:
	        	marker.setUrl('resources/mapicons/doctor.png');
	            break;
	        case 3:
	        	marker.setUrl('resources/mapicons/technical.png');
	            break;
	        case 4:
	        	marker.setUrl('resources/mapicons/watersupply.png');
	            break;
	        case 5:
	        	marker.setUrl('resources/mapicons/groundworker.png');
	            break;
	        case 6:
	        	marker.setUrl('resources/mapicons/various.png');
	            break;
	        default:
	            
	    } 
        marker.display(true);
        var markerInfo = "Name: " + this.device.actor.name + "\nTeam: " + this.device.actor.teamActors[0].team.name; 
        /*var infobox = new khtml.maplib.overlay.InfoWindow({content: markerInfo});

		marker.attachEvent( 'click', function() {
		    	infobox.open(map, this);
		});*/
        pointLayer.addMarker(marker);
        /*var pointGeometry = new OpenLayers.Geometry.Point(lonlat.lon, lonlat.lat);
        var pointFeature = new OpenLayers.Feature.Vector(pointGeometry, null, {
            pointRadius: 16,
            fillOpacity: 0.7,
        });

        pointFeatures.push(pointFeature);
        pointLayer.addFeatures(pointFeatures);*/
        
        /*var marker = new google.maps.Marker({
        map: map,
        position: point,
        label: icon.label,
        draggable: true
      });
      marker.addListener('click', function() {
        infoWindow.setContent(infowincontent);
        infoWindow.open(map, marker);
        
      });
      marker.addListener('dragend', function(evt) {
        document.getElementById('current').innerHTML = '<p>Marker dropped: Current Lat: ' + evt.latLng.lat().toFixed(3) + ' Current Lng: ' + evt.latLng.lng().toFixed(3) + '</p>';
        
      });
      
      
    });
  });
markers.addMarker(new OpenLayers.Marker(point));*/
      });
      
      function doNothing() {}  
      map.setCenter (lonLat, zoom);
    });
  </script>
</body>

</html>
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

    
    var pointLayer = new OpenLayers.Layer.Markers( "Markers" );
    map.addLayer(pointLayer);
    var counter = 0;
    doSomething();
    
    function doSomething() {
    	pointLayer.clearMarkers();
    	
    
    $.getJSON('locations.json', function(data) {
    	console.log(data);
    	var longPoints = [];
    	var latPoints = [];
      $.each(data.locationList, function() {
    	//  console.log(this)
       // var pointFeatures = [];
    	
        var px = this.logntitude;
        longPoints.push(px);
        
        var py = this.latitude;
        latPoints.push(py);
        
        // Create a lonlat instance and transform it to the map projection.
        var lonlat = new OpenLayers.LonLat(parseFloat(px), parseFloat(py));
        lonlat.transform(new OpenLayers.Projection("EPSG:4326"), map.getProjectionObject());
        var marker = new OpenLayers.Marker(lonlat);

        var markerInfo = "<p>Name: " + this.device.actor.name + 
        				 "<br/>Team: " + this.device.actor.teamActors[0].team.name + "</p>" +
        				 "<button>Send message</button>"; 
     	marker.events.register("click", marker, function(e){
     	   popup = new OpenLayers.Popup("chicken",
     	                   map.getLonLatFromPixel(e.xy),
     	                   new OpenLayers.Size(200,200),
     	                   markerInfo,
     	                   true);

     		map.addPopup(popup);
     	}); 
        
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
        
        pointLayer.addMarker(marker);
       
      });
      
      if (counter == 0){
    	  counter = counter + 1;
    	  console.log(Math.max(...longPoints));
    	  var mediumLong = (Math.max(...longPoints) + Math.min(...longPoints))/2.0;
          var mediumLat = (Math.max(...latPoints) + Math.min(...latPoints))/2.0;
          
          console.log('long' + mediumLong);
          console.log('lat' + mediumLat);
          
          var lonLat = new OpenLayers.LonLat( mediumLong,mediumLat )
          .transform(
            new OpenLayers.Projection("EPSG:4326"), // transform from WGS 1984
            map.getProjectionObject() // to Spherical Mercator Projection
          );
          
    		var zoom=11;
    		map.setCenter (lonLat, zoom);
      }
      
      
    });
    
    }
    
    setInterval(doSomething, 5000);
  </script>
</body>

</html>
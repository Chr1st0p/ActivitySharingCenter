<div class="panel panel-default">
    <div class="panel-heading">Create Activity</div>

    <div class="panel-body">
        <form action="CreateActivity" method="post">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" name="name" class="form-control" value="" />
            </div>  
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea name="description" class="form-control" rows="5" id="dexcription"></textarea>
            </div>
            <div class="form-group">
                <label for="time">Date And Time:</label>
                <div class='input-group date' id='datetimepicker1'>
                    <input name="time" type='text' class="form-control" value=""/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>

            <div class="form-group">
                <label for="categoryID">Category</label>
                <select class="form-control" name="categoryID">

                    <c:forEach items="${requestScope.categoryList}" var="emp">
                        <option value='<c:out value="${emp.id}">></c:out>'><c:out value="${emp.name}"></c:out></option>
                    </c:forEach>
                </select>
            </div>


            <div class="form-group">

                <label for="name">Address</label>
                <input type="text" id="Address" name="location" class="form-control" value="" >
            </div>  


            <div class="form-group"><button type="submit" class="btn btn-primary">Create</button></div>
            <div class="form-group" id="map" style="height: 200px">

                <!--            <input type="hidden" name="latitude" value="33.3267867832">
                            <input type="hidden" name="longitude" value="53.3267867832">-->
        </form>
    </div>
    <script>
        $(document).ready(function () {
            $('#datetimepicker1').datetimepicker({
                defaultDate: new Date(),
                format: 'YYYY-MM-DD HH:mm:ss'
            });

        });
    </script>
    <!--Address auto complete-->  
    <script>
        function initMap() {
            var map = new google.maps.Map(document.getElementById('map'), {

            });
            var geocoder = new google.maps.Geocoder();
            var location = "Singapore";
            geocoder.geocode({'address': location}, function (results, status) {
                if (status === google.maps.GeocoderStatus.OK) {
                    map.setCenter(results[0].geometry.location);
                    map.setZoom(12);
                } else {
                    alert("Could not find location: " + location);
                }
            });
            var input = /** @type {!HTMLInputElement} */(
                    document.getElementById('Address'));

            var autocomplete = new google.maps.places.Autocomplete(input);
            autocomplete.setComponentRestrictions({country: 'SG'});

            var infowindow = new google.maps.InfoWindow();
            var marker = new google.maps.Marker({
                map: map,
                anchorPoint: new google.maps.Point(0, -29)
            });
//            autocomplete.setTypes(['address']);
            autocomplete.addListener('place_changed', function () {
                infowindow.close();
                marker.setVisible(false);
                var place = autocomplete.getPlace();
                if (!place.geometry) {
                    window.alert("No details available for input: '" + place.name + "'");
                    return;
                }

                // If the place has a geometry, then present it on a map.
                if (place.geometry.viewport) {
                    map.fitBounds(place.geometry.viewport);
                } else {
                    map.setCenter(place.geometry.location);
                    map.setZoom(17);  // Why 17? Because it looks good.
                }
                marker.setIcon(/** @type {google.maps.Icon} */({
                    url: place.icon,
                    size: new google.maps.Size(71, 71),
                    origin: new google.maps.Point(0, 0),
                    anchor: new google.maps.Point(17, 34),
                    scaledSize: new google.maps.Size(35, 35)
                }));
                marker.setPosition(place.geometry.location);
                marker.setVisible(true);

                var address = '';
                if (place.address_components) {
                    address = [
                        (place.address_components[0] && place.address_components[0].short_name || ''),
                        (place.address_components[1] && place.address_components[1].short_name || ''),
                        (place.address_components[2] && place.address_components[2].short_name || '')
                    ].join(' ');
                }

                infowindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
                infowindow.open(map, marker);
            });
        }
    </script>
    <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDknTuQNUudZrTPv3B_WM9rJf4Rpm9FQSs&libraries=places&callback=initMap" async defer></script>
</body>
</html>
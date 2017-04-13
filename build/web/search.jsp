
<%@page import="com.jevents.model.Activity"%>
<style>
    html { height: 100% } 
    body { height: 100%; margin: 0; padding: 0 } 
    #map_canvas { height: 500px; } 
    #wrapper { position: relative; }
    #over_map { position: absolute; background-color: transparent; top: 10px; left: 110px; z-index: 99;  }
    #over_map_right { position: absolute; background-color: transparent; top: 10px; right: 10px; z-index: 99; background: white; }
</style>

</head>
<body>
    <div id="wrapper">
        <div id="map_canvas"></div>
        <div id="over_map">
            <form action="Search" method="POST">
                <input type="text" name="keyword" placeholder="Title Keyword" />
                <input type="text" id="Address" name="location" placeholder="Location" />
                <input type="text" name="description" placeholder="Description" />
                Activity Category: <select name="category">
                    <option value="">ALL</option>
                    <c:forEach var="categoryItem" items="${categroyList}">
                        <option value="${categoryItem.getId()}">${categoryItem.getName()}</option>
                    </c:forEach>
                </select>
                <input type="submit" name="searchBTN" value="Search">
            </form>  
        </div>

    </div>

    <script>
        function initMap() {
            var autocomplete = new google.maps.places.Autocomplete(
                    /** @type {!HTMLInputElement} */(document.getElementById('Address')));
            autocomplete.setComponentRestrictions({country: 'SG'});
            var map = new google.maps.Map(document.getElementById('map_canvas'), {
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

            //        
            //        var beachMarker = new google.maps.Marker({
            //            position: {lat: -33.890, lng: 151.274},
            //            map: map,
            //            icon: image
            //        });
            var soccer = '${initParam.img}/icons/soccer.png';
            var basket = '${initParam.img}/icons/basketball.png';
            var defaultimage = '${initParam.img}/icons/default.png';
            var markers = [];
            var i = 0;
            var infowindows = [];
        <c:if test="${!empty resultlist}">
            <c:forEach items="${resultlist}" var="result" >
                <% Activity activity = (Activity) pageContext.getAttribute("result");%>
                <c:choose>
                    <c:when test="${result.getCategory()==1}">
            markers[i] = new google.maps.Marker({
                position: {lat:<%= activity.getLatitude()%>, lng:<%= activity.getLongitude()%>},
                map: map,
                icon: soccer
            });

            google.maps.event.addListener(markers[i], 'click', function () {
                window.location.href = "/JED_Sports_Organization_System/ViewActivity?id=" +<%= activity.getId()%>;
            });
                    </c:when>
                    <c:when test="${result.getCategory()==3}">
            markers[i] = new google.maps.Marker({
                position: {lat:<%= activity.getLatitude()%>, lng:<%= activity.getLongitude()%>},
                map: map,
                icon: basket
            });
            infowindows[i] = new google.maps.InfoWindow();
            google.maps.event.addListener(markers[i], 'click', function () {
                window.location.href = "/JED_Sports_Organization_System/ViewActivity?id=" +<%= activity.getId()%>;
            });

                    </c:when>
                    <c:otherwise>
            markers[i] = new google.maps.Marker({
                position: {lat:<%= activity.getLatitude()%>, lng:<%= activity.getLongitude()%>},
                map: map,
                icon: defaultimage
            });
            infowindows[i] = new google.maps.InfoWindow();
            google.maps.event.addListener(markers[i], 'click', function () {
                window.location.href = "/JED_Sports_Organization_System/ViewActivity?id=" +<%= activity.getId()%>;
            });

                    </c:otherwise>
                </c:choose>

            i = i + 1;
            </c:forEach>
        </c:if>

        }

    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDknTuQNUudZrTPv3B_WM9rJf4Rpm9FQSs&libraries=places&callback=initMap"></script>
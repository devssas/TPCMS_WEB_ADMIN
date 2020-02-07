"use strict";

var main = {
    cardTransactionLocations: function () {

        var markersPin = {
                url: "assets/images/layout/pin.png",
                size: new google.maps.Size(100, 102),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(50, 48)
            },
            mapMarkersList = null,
            map = null,
            zoom,
            index,
            markers = [],
            markersList;

        function markerList() {

            $.ajax({
                type: "GET",
                url: "assets/ajax/marker.json"
            })
                .done(function (response) {
                    markersList = response;
                    createdMap();
                });
        }

        function createdMap() {
            map = new google.maps.Map(document.getElementById('map'), {
                center: new google.maps.LatLng(markersList.mapsCenter[0]),
                zoom: 11,
                mapTypeControl: false,
                streetViewControl: false,
                fullscreenControl: false
            });

            if (mapMarkersList) {
                mapMarkersList.clearMarkers();
                markers = [];
            }

            for (var i = 0; i < markersList.data.length; ++i) {
                var latLng = new google.maps.LatLng(markersList.data[i].lat, markersList.data[i].lng);

                var marker = new google.maps.Marker({
                    position: latLng,
                    icon: markersPin,
                    markerLat: markersList.data[i].lat,
                    markerLng: markersList.data[i].lng,
                    selectedId: i,
                    selectedState: false
                });

                markers.push(marker);
            }

            mapMarkersList = new MarkerClusterer(map, markers, {
                gridSize: 40,
                styles: [
                    {
                        url: "assets/images/layout/map-cluster.png",
                        width: 52,
                        height: 52,
                        textColor: '#ffffff'
                    }
                ]
            });
        }

        markerList();

    },
    inputAction: function () {
        if ($(".next-input-field").length) {
            var _this = $(".next-input-field"),
                charLimit = _this.attr("maxlength");

            _this.keydown(function (e) {

                var keys = [8, 9, /*16, 17, 18,*/ 19, 20, 27, 33, 34, 35, 36, 37, 38, 39, 40, 45, 46, 144, 145];

                if (e.which == 8 && this.value.length == 0) {
                    $(this).prev('input').focus();
                } else if ($.inArray(e.which, keys) >= 0) {
                    return true;
                } else if (this.value.length >= charLimit) {
                    $(this).next('input').focus();
                    return false;
                }
            }).keyup(function () {
                if (this.value.length >= charLimit) {
                    $(this).next('input').focus();
                    // return false;
                } else {
                    $(this).parents("form").valid();
                }
            });

        }
    }
}

$(function () {
    main.inputAction();
});

function initMap() {
    main.cardTransactionLocations();
}
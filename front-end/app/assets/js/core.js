"use strict";

var main = {
    cardTransactionLocations: function () {

        var markersPin = {
                url: "assets/images/layout/pin.png",
                size: new google.maps.Size(100, 102),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(50, 48)
            },
            markersSelectedPin = {
                url: "assets/images/layout/pin-selected.png",
                size: new google.maps.Size(100, 102),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(50, 48)
            },
            mapsRadioBtn = $("input[name='cardTransactionLocations']"),
            mapsAdressListContent = $(".maps-adress-list"),
            mapsAdressList = $(".maps-list", mapsAdressListContent),
            mapMarkersList = null,
            map = null,
            zoom,
            index,
            markers = [],
            markersList;

        $(".maps-close").off("click").on({
            click: function () {
                $(this).parents(".mobile-maps-content").fadeOut(function () {
                    $(this).removeClass("active");
                });
            }
        });

        function markerList() {

            $.ajax({
                type: "GET",
                url: "assets/ajax/marker.json"
            })
                .done(function (response) {
                    markersList = response;
                    mapsAdressListContent.addClass("loading");
                    createdMap();
                });

        }

        function createdMap() {
            map = new google.maps.Map(document.getElementById('map'), {
                center: new google.maps.LatLng(markersList.mapsCenter[0]),
                zoom: 11,
                mapTypeControl: false,
                streetViewControl: false,
                fullscreenControl: false,
                styles: [
                    {
                        "featureType": "administrative",
                        "elementType": "labels.text.fill",
                        "stylers": [
                            {
                                "color": "#444444"
                            }
                        ]
                    },
                    {
                        "featureType": "landscape",
                        "elementType": "all",
                        "stylers": [
                            {
                                "color": "#f2f2f2"
                            }
                        ]
                    },
                    {
                        "featureType": "poi",
                        "elementType": "all",
                        "stylers": [
                            {
                                "visibility": "off"
                            }
                        ]
                    },
                    {
                        "featureType": "road",
                        "elementType": "all",
                        "stylers": [
                            {
                                "saturation": -100
                            },
                            {
                                "lightness": 45
                            }
                        ]
                    },
                    {
                        "featureType": "road.highway",
                        "elementType": "all",
                        "stylers": [
                            {
                                "visibility": "simplified"
                            }
                        ]
                    },
                    {
                        "featureType": "road.arterial",
                        "elementType": "labels.icon",
                        "stylers": [
                            {
                                "visibility": "off"
                            }
                        ]
                    },
                    {
                        "featureType": "transit",
                        "elementType": "all",
                        "stylers": [
                            {
                                "visibility": "off"
                            }
                        ]
                    },
                    {
                        "featureType": "water",
                        "elementType": "all",
                        "stylers": [
                            {
                                "color": "#46bcec"
                            },
                            {
                                "visibility": "on"
                            }
                        ]
                    },
                    {
                        "featureType": "water",
                        "elementType": "geometry.fill",
                        "stylers": [
                            {
                                "lightness": "46"
                            },
                            {
                                "gamma": "1.30"
                            }
                        ]
                    },
                    {
                        "featureType": "water",
                        "elementType": "labels.text.fill",
                        "stylers": [
                            {
                                "lightness": "0"
                            },
                            {
                                "gamma": "1.00"
                            }
                        ]
                    }
                ]
            });

            selectRadioBtn();
        }

        function selectRadioBtn() {

            mapsRadioBtn.on({
                change:function () {
                    var selectedMapsRadioButton = $("input[name='cardTransactionLocations']:checked"),
                        selectedMapsRadioButtonValue = selectedMapsRadioButton.val();

                    refreshMap(selectedMapsRadioButtonValue);
                }
            });

            if(mapsRadioBtn.is(":checked")){
                var selectedMapsRadioButton = $("input[name='cardTransactionLocations']:checked"),
                    selectedMapsRadioButtonValue = selectedMapsRadioButton.val();

                refreshMap(selectedMapsRadioButtonValue);
            } else {
                $(".checkbox-content > div:first-child input").prop("checked", true).trigger("change");
            }

        }

        function refreshMap(selectedMapRadioBtnValue) {

            mapsAdressListContent.addClass("loading");

            mapsAdressList.html("");

            if (mapMarkersList) {
                mapMarkersList.clearMarkers();
                markers = [];
            }


            for (var i = 0; i < markersList.data.length; ++i) {
                if(selectedMapRadioBtnValue == "transactionCenter"){
                    if(markersList.data[i].transactionCenter){
                        newsMarkerList(markersList.data[i])
                    }
                } else if(selectedMapRadioBtnValue == "cardCases"){
                    if(markersList.data[i].cardCases){
                        newsMarkerList(markersList.data[i])
                    }
                }

            }

            function newsMarkerList (newsMarkersList) {
                var latLng = new google.maps.LatLng(newsMarkersList.lat, newsMarkersList.lng),
                    list ="<div class='list-content' data-maps-selected='" + i + "'>" +
                        "<div class='list'>" +
                        "<h3>"+ newsMarkersList.title +" </h3>" +
                        "<div>" + newsMarkersList.adress + "</div>" +
                        "<div class='direction-link'>" +
                        "<a href='http://www.google.com/maps/dir/?api=1&destination="+ newsMarkersList.lat +","+ newsMarkersList.lng +"' class='text-link-v1' target='_blank'><span>Yol Tarifi Al</span> <i></i></a>" +
                        "</div>" +
                        "</div>" +
                        "</div>";

                var marker = new google.maps.Marker({
                    position: latLng,
                    icon: markersPin,
                    markerLat: newsMarkersList.lat,
                    markerLng: newsMarkersList.lng,
                    selectedId: i,
                    selectedState: false
                });

                markers.push(marker);
                mapsAdressList.append(list);
                addClickEvent(marker, list);
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

            $(".maps-list-content").nanoScroller({
                alwaysVisible: true
            });

            $(".maps-list .list-content").off("click").on({
                click:function (event) {
                    var _this = $(this),
                        thisContentIndex = _this.index();

                    google.maps.event.trigger(markers[thisContentIndex], 'click');

                    mapsListTop();

                    if($(window).width() < 768){
                        if(!event.target.closest('.direction-link') && !event.target.matches('.direction-link')){
                            $(".mobile-maps-content").fadeIn(function () {
                                $(this).addClass("active");
                            });
                        }

                    }
                }
            });

            mapsAdressListContent.removeClass("loading");

        }

        function addClickEvent(marker, list) {

            var infoWindow = new google.maps.InfoWindow({
                content: list,
                maxWidth: 250
            });

            marker.addListener("click", function () {

                clearSelected();

                map.panTo({
                    lat: this.markerLat,
                    lng: this.markerLng
                });

                map.setZoom(15);

                marker.setIcon(markersSelectedPin);

                marker.selectedState = true;

                $(".maps-list .list-content[data-maps-selected='" + this.selectedId + "']").addClass("active");

                if($(window).width() < 768 ){
                    infoWindow.open(map, marker);
                }

                google.maps.event.addListener(infoWindow, "closeclick", function(){
                    clearSelected();
                });

                mapsListTop();

            });
        }

        function clearSelected() {
            $.each(markers, function (index, value) {
                if(value.selectedState){
                    this.selectedState = false;
                    this.setIcon(markersPin);
                }
            });

            $(".maps-list .list-content").removeClass("active");
        }

        function mapsListTop() {
            var listContent = $(".maps-list-content .nano-content"),
                mapListContentOffsetTop = listContent.offset().top,
                mapListContentScrollTop = listContent.scrollTop(),
                selectedOffsetTop = $(".maps-list-content .list-content.active").length ? $(".maps-list-content .list-content.active").offset().top : 0;

            listContent.animate({scrollTop: (selectedOffsetTop - mapListContentOffsetTop) + mapListContentScrollTop});
        }


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
    main.cardTransactionLocations();
});
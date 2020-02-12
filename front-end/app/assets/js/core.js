"use strict";

var main = {
    init: function(){
        // handlebars add URL

        Handlebars.registerHelper("link", function(icon, url) {
            var url = Handlebars.escapeExpression(url),
                icon = Handlebars.escapeExpression(icon);
            return new Handlebars.SafeString("<a href='" + url + "' class='button-v1 btn-color-1'><i class='" + icon + "'></i></a>");
        });

        Handlebars.registerHelper("inc", function(value, options){
            return parseInt(value) + 1;
        });
    },
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
    },
    customSelectbox: function(){
        if($(".select-not-find").length){
            $(".select-not-find").each(function (index, element) {
                if (!$('select', element).data('select2')) {
                    $("select", element).select2({
                        minimumResultsForSearch: -1,
                        dropdownParent: $(element)
                    }).off("select2:open").on("select2:open", function (e) {
                        $(".select2-results .select2-results__options").niceScroll({
                            autohidemode: false,
                            cursorwidth: 4,
                            cursorcolor: "#BCC3CA",
                            cursorborder: 2,
                            cursorborderradius: 2,
                            horizrailenabled: true,
                        });
                    });
                }
            });
        }

        if($(".select-find").length){
            $(".select-find").each(function (index, element) {
                if (!$('select', element).data('select2')) {
                    $("select", element).select2({
                        ajax: {
                            url: $(element).find("select").data("json-url"),
                            data: function (params) {
                                var query = {
                                    search: params.term
                                }
                                return query;
                            }
                        },
                        dropdownParent: $(element)
                    }).off("select2:open").on("select2:open", function (e) {
                        $(".select2-results .select2-results__options").niceScroll({
                            autohidemode: false,
                            cursorwidth: 4,
                            cursorcolor: "#BCC3CA",
                            cursorborder: 2,
                            cursorborderradius: 2,
                            horizrailenabled: true,
                        });
                    });
                }
            });
        }

      /*  if($(".country-select")){
            $(".country-select").each(function (index, element) {
                $.ajax({
                    url: $(this).data("url"),
                    method: "get",
                    dataType: "json",
                }).done(function (response) {

                    var template;

                    for (var i = 0; i < response.length; i++){
                        template += "<option value='" + response[i].name + "'>" + response[i].name + "</option>";
                    }

                    $(".country-select").append(template).select2({
                        minimumResultsForSearch: -1
                    }).off("select2:open").on("select2:open", function (e) {
                        $(".select2-results .select2-results__options").niceScroll({
                            autohidemode: false,
                            cursorwidth: 4,
                            cursorcolor: "#BCC3CA",
                            cursorborder: 2,
                            cursorborderradius: 2,
                            horizrailenabled: true,
                        });
                    });

                });
            });
        }

        if($(".country-and-flag-select").length){

            $(".country-and-flag-select").each(function () {

                $.ajax({
                    url: $(this).data("url"),
                    method: "get",
                    dataType: "json",
                }).done(function (response) {
                    var template;

                    for (var i = 0; i < response.length; i++){
                        template += "<option value='" + response[i].phone + "'>" + response[i].alpha_2 + "</option>";
                    }

                    $(".country-and-flag-select").append(template).select2({
                        minimumResultsForSearch: -1,
                        templateSelection: formatState,
                        templateResult: formatState
                    }).off("select2:open").on("select2:open", function (e) {
                        $(".select2-results .select2-results__options").niceScroll({
                            autohidemode: false,
                            cursorwidth: 4,
                            cursorcolor: "#BCC3CA",
                            cursorborder: 2,
                            cursorborderradius: 2,
                            horizrailenabled: true,
                        });
                    });

                    if($("[data-selected]").length){
                        $("[data-selected]").each(function () {
                            $(this).val($(this).attr("data-selected")).trigger('change.select2');
                        });
                    }

                });
            });


        }*/

        function formatState (state) {
            if (!state.id) {
                return state.text;
            }
            var $state = $("<div><span class='flag flag-"+ state.text.toLowerCase() +"'></span>"+ state.id +"</div>");
            return $state;
        }
    },
    datepicker: function () {

        $(".datepicker").each( function () {
            $(this).datepicker();
        });

    },
    dropzone: function () {

        if($(".photo-upload").length){

            var uploadUrl = $(".photo-upload").data("upload-url");

            $(".photo-upload-inner").dropzone({
                url: uploadUrl,
                maxFiles: 1,
                thumbnailMethod: "contain"/*,
                init: function() {
                    this.on("addedfile", function(file) {
                    });
                }*/
            });
        }

    },
    filterControl: function () {

        if($(".dynamic-content").length && Boolean($(".dynamic-content").data("template-url"))){

            var _this = $(".dynamic-content"),
                templateUrl = _this.data("template-url"),
                jsonUrl = _this.data("json-url"),
                jsonTemplate;


            $.ajax({
                url: templateUrl,
                method: "GET"
            })
                .done(function (responseTemplate) {
                    jsonTemplate = responseTemplate;

                    if($("#filter-form").length){

                        $( "#filter-form" ).submit(function( event ) {
                            event.preventDefault();

                            var filter = $("#filter-form").serialize();
                            getJson(jsonUrl, jsonTemplate, filter);
                        }).submit();

                    } else {
                        getJson(jsonUrl,jsonTemplate);
                    }

                });

        }


        function getJson(jsonUrl, jsonTemplate, filter) {

            $('.dynamic-page').addClass("loading").find(".dynamic-content").html("");

            $.ajax({
                url: jsonUrl,
                method: "GET",
                dataType: "json",
                data: filter,
                async: false
            })
                .done(function (responseJson) {

                    var template = Handlebars.compile(jsonTemplate),
                        tableHTML = template(responseJson);

                    $('.dynamic-content').html(tableHTML).parents(".dynamic-page").removeClass("loading");

                    main.fancybox();

                });
        }

    },
    fancybox: function () {
        if($("[data-fancybox-card]").length){
            $("[data-fancybox-card]").fancybox({
                smallBtn:false
            });
        }
    }
}

$(function () {
    main.init();
    main.inputAction();
    main.customSelectbox();
    main.datepicker();
    main.dropzone();
    main.filterControl();
});

function initMap() {
    if($("#map").length){
        main.cardTransactionLocations();
    }
}
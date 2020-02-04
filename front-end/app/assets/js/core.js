"use strict";

var main = {
    maps: function (){
        function initMap() {
            var myLatLng = {lat: -25.363, lng: 131.044};

            var map = new google.maps.Map(document.getElementById('map'), {
                zoom: 4,
                center: myLatLng
            });

            var marker = new google.maps.Marker({
                position: myLatLng,
                map: map,
                title: 'Hello World!'
            });
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
});
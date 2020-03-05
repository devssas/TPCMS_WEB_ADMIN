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
                url: "assets/ajax/dashboard/dashboard.json"
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

            refreshMap();
        }

        function refreshMap() {

            $.ajax({
                url: "assets/ajax/dashboard/dashboard.html",
                type: "GET"
            })
                .done(function (jsonTemplate) {
                    var template = Handlebars.compile(jsonTemplate),
                        tableHTML = template(markersList);

                    $(".horizontal-list.v1").html(tableHTML);

                });

            if (mapMarkersList) {
                mapMarkersList.clearMarkers();
                markers = [];
            }

            for (var i = 0; i < markersList.data.length; ++i) {
                newsMarkerList(markersList.data[i])
            }

            function newsMarkerList (newsMarkersList) {
                var latLng = new google.maps.LatLng(newsMarkersList.lat, newsMarkersList.lng);
                var marker = new google.maps.Marker({
                    position: latLng,
                    icon: markersPin,
                    markerLat: newsMarkersList.lat,
                    markerLng: newsMarkersList.lng,
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

        setInterval(function () {
            markerList();
        },300000);

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

        if($(".field-query").length){
            $(".field-query").each(function () {

                $(this).on({
                    keyup: function () {

                        var _this = $(this),
                            _minLength = _this.data("min-lenght"),
                            _url = _this.data("url"),
                            _method = _this.data("method"),
                            _value = _this.val();

                        if(_value.length >= _minLength){
                            $.ajax({
                                url: _url+_value,
                                method: _method,
                                dataType: "json",
                            }).done(function (response) {
                                if(!response.status && response.message){
                                    _this.parents(".form-row").addClass("error").find(".text-danger").remove();
                                    _this.parent().append("<span class='text-danger'>"+ response.message +"</span>");
                                } else {
                                    _this.parents(".form-row").removeClass("error").find(".text-danger").remove();
                                }
                            });
                        }

                    }
                });

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

    },
    addWitness: function(){
        if($(".addWitness").length){
            var index = 1;

            $(".addWitness").on({
                click: function () {
                    var addWitnessHtml = "<div class='form-row'><a href='javascript:;' class='button button-v4 color-1 line-height-sml removeWitness'><i class='icon-cancel'></i></a><span class='label witness-label'>Witness Statments</span><div class='input-group-content'><label><input type='text' name='witnessName"+index+"' placeholder='Name'></label><label><input type='text' name='witnessSurname"+index+"' placeholder='Surname'></label></div><div class='form-row'><label><span class='label'></span><textarea name='witnessStatement"+index+"' placeholder='Statement'></textarea></label></div></div>";
                    $(".witness-container").append(addWitnessHtml);
                    index++;

                    $(".removeWitness").off("click").on({
                        click: function () {
                            $(this).parents(".form-row").remove();
                        }
                    });

                }
            });
        }
    },
    datePicker: function () {

        if($(".datepicker").length){
            $(".datepicker").each( function () {
                $(this).datepicker();
            });
        }

        if($("#calendar").length){
            var nextUrl = $("#calendar").data("next-url");

            $( "#calendar" ).datepicker({
                onSelect: function(date, datepicker) {
                    window.location.href = nextUrl+"?calendarDate="+date
                },
            });
        }

    },
    timePicker: function(){
      if($('.timepicker').length){
          $('.timepicker').timepicker({
              timeFormat: 'h:mm p',
              dynamic: false,
              dropdown: true,
              scrollbar: true,
              zindex: 99999
          });
      }
    },
    dropzone: function () {

        if($(".photo-upload").length){

            $(".photo-upload").each(function () {

                var _this = $(this).find(".photo-upload-inner"),
                    uploadUrl = _this.data("upload-url"),
                    deleteUrl = _this.data("delete-url"),
                    maxFiles = _this.data("max-files") ? _this.data("max-files") : null ;

                if(!_this.hasClass("dz-started")){
                    _this.dropzone({
                        url: uploadUrl,
                        maxFiles: maxFiles,
                        addRemoveLinks: true,
                        dictRemoveFile: "",
                        thumbnailMethod: "contain",
                        dictDefaultMessage: null,
                        removedfile: function(file) {
                            var name = file.name;
                            $.ajax({
                                url: deleteUrl,
                                type: "POST",
                                data: "id=" + name,
                                dataType: "html"
                            });
                            var _ref;
                            return (_ref = file.previewElement) != null ? _ref.parentNode.removeChild(file.previewElement) : void 0;
                        },
                        init: function() {

                            var _initThis = this;

                            _initThis.on("addedfile", function(file) {
                                if (_initThis.files[maxFiles]!=null){
                                    _initThis.removeFile(_initThis.files[0]);
                                }
                            });

                            if(_this.parents(".photo-upload").hasClass("upload-view")){
                                var uploadAjaxUrl = _this.data("upload-ajax-url");

                                $.ajax({
                                    url: uploadAjaxUrl
                                })
                                    .done(function (response) {

                                        for(var i = 0; i < response.data.length ; i++){

                                            _initThis.files.push(response.data[i]);
                                            _initThis.emit('addedfile', response.data[i]);
                                            _initThis.emit("thumbnail", response.data[i], response.data[i].url);
                                            _initThis.emit('complete', response.data[i]);
                                            response.data[i].previewElement.classList.add('dz-success');
                                            response.data[i].previewElement.classList.add('dz-complete')

                                        }

                                    });

                            }

                        }
                    });
                }

            });
        }

    },
    pagination: function () {
        $(".pagination").each(function (index, pagination) {
            $("select", pagination).on({
                change: function () {
                    var prev = $("option:selected", this).prev("option");
                    var next = $("option:selected", this).next("option");

                    if (prev.length) {
                        $(".prev", pagination).removeClass("disabled");
                    } else {
                        $(".prev", pagination).addClass("disabled");
                    }

                    if (next.length) {
                        $(".next", pagination).removeClass("disabled");
                    } else {
                        $(".next", pagination).addClass("disabled");
                    }
                }
            }).trigger("change");

            $(".prev", pagination).on({
                click: function (event) {
                    var select = $("select", pagination);
                    var prev = $("option:selected", select).prev("option");

                    if (prev.length) {
                        prev.prop("selected", true);
                        select.trigger("change");
                    }

                    event.preventDefault();
                }
            });

            $(".next", pagination).on({
                click: function (event) {
                    var select = $("select", pagination);
                    var next = $("option:selected", select).next("option");

                    if (next.length) {
                        next.prop("selected", true);
                        select.trigger("change");
                    }

                    event.preventDefault();
                }
            });
        });
    },
    filterControl: function () {
        var deferredData = $.Deferred();

        if($(".site-data-container").length){

            $(".site-data-container").each(function () {
                var container 	= $(this),
                    filters		= $(".site-data-filters", container),
                    content 	= $(".site-data-content", container),
                    pagination	= $(".site-data-pagination", container),

                    templateURL	= container.data("template-url"),
                    dataURL		= container.data("json-url"),

                    templateHtml;

                $.ajax({
                    type	: "GET",
                    url		: templateURL,
                    async	: false,
                    success	: function (response) {
                        templateHtml = response;
                    }
                });

                function getList (isPaging) {

                    container.addClass("loading");

                    if (!isPaging){
                        $("select", pagination).val("1").data("value", "1");
                    }

                    var data = $(":input", container).serialize();

                    $.ajax({
                        type		:"GET",
                        url			: dataURL,
                        dataType	: "json",
                        data		: data,
                        success		: function (response) {

                            console.log(response);

                            if(response.data.length || response.data.tbody.length){

                                var template = Handlebars.compile(templateHtml),
                                    output = template(response);

                                $(".error-text", container).text("").hide();
                                $(".item-length", container).html(response.itemLength);
                                // $(".result-summary", container).show();

                                if($(".complaints-detail-content").length){
                                    $(".complaints-detail-content").html("");
                                }
                                content.html(output);
                                container.removeClass("loading");
                                content.show();

                                //pagination show/hide
                                if (response.pages > 1) {
                                    pagination.show();
                                } else {
                                    pagination.hide();
                                }

                                //pagination first
                                if (!isPaging) {
                                    $("select", pagination).html("");
                                    for (var i = 1; i <= response.pages; i++) {
                                        $("select", pagination).append('<option value="' + i + '">' + i + '</option>');
                                    }
                                    $("select", pagination).trigger("change");
                                }

                                if($(".show-complaints-detail").length){
                                    $(".show-complaints-detail").off("click").on({
                                        click: function () {
                                            var _this = $(this),
                                                jsonUrl = _this.data("complaints-datail-json-url"),
                                                templateUrl = _this.data("complaints-datail-template-url"),
                                                id = _this.data("complaints-id");


                                            $.ajax({
                                                url: jsonUrl,
                                                dataType: "json",
                                                data: {"id":id},
                                            })
                                                .done(function (detailData) {

                                                    $.ajax({
                                                        url: templateUrl,
                                                        method: "GET"
                                                    })
                                                        .done(function (responseTemplate) {

                                                            var template = Handlebars.compile(responseTemplate),
                                                                detail = template(detailData);

                                                            $('.complaints-detail-content').html(detail);
                                                        });
                                                });



                                        }
                                    })
                                }

                                deferredData.resolve();

                            } else {
                                container.removeClass("loading");
                                // $(".result-summary", container).hide();
                                $(".item-length", container).text("");
                                pagination.hide();

                                deferredData.resolve();
                            }

                        }
                    });

                }

                //submit
                var filtersData;
                var currentFiltersData = $(":input", filters).serialize();
                // filters events
                filters.on({
                    submit: function (event) {
                        filtersData = $(":input", filters).serialize();
                        if(currentFiltersData != filtersData){
                            currentFiltersData = filtersData;
                            getList();
                        }
                        event.preventDefault();
                    }
                });

                if($(".change-on-submit").length){
                    $(".change-on-submit").on({
                        change: function () {
                            getList();
                        }
                    })
                }

                //pagination change
                $("select", pagination).on({
                    change: function () {
                        if ($(this).val() != $(this).data("value")) {
                            $(this).data("value", $(this).val());
                            getList(true);
                        }
                    }
                });

                getList();

            });

        }else{
            deferredData.resolve();
        }

        return deferredData;
    },
    /*filterControl: function () {

        if($(".dynamic-content").length && Boolean($(".dynamic-content").data("template-url")) && $(".dynamic-content").is(':empty')){

            $(".dynamic-content").each(function () {
                var windowUrl = window.location.href,
                    _this = $(this),
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
                                getJson(jsonUrl, jsonTemplate, _this, filter);
                            }).submit();

                        } else {

                            if(windowUrl.indexOf("?calendarDate") > 0){
                                var date  = windowUrl.split("?");
                                getJson(jsonUrl, jsonTemplate, _this, date[1]);
                            } else {
                                getJson(jsonUrl, jsonTemplate, _this);
                            }

                        }

                    });
            });

            function getJson(jsonUrl, jsonTemplate, _this, filter) {

                $('.dynamic-page').addClass("loading");
                _this.html("");

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

                        _this.html(tableHTML).parents(".dynamic-page").removeClass("loading");

                        main.fancybox();

                    });
            }

        }

    },*/
    carousel: function (){
        if($(".card-carousel").length){
            $(".card-carousel").owlCarousel({
                rtl: true,
                margin: 15,
                nav: true,
                navText: ["<i class='icon-right-open'></i>","<i class='icon-left-open'></i>"],
                dots: false
            });
        }
    },
    fancybox: function () {

        $.fancybox.defaults.touch = false;

        if($("[data-fancybox-card]").length){
            $("[data-fancybox-card]").fancybox({
                smallBtn: false,
                toolbar: false
            });
        }

        if($("[data-fancybox-search]").length){
            $("[data-fancybox-search]").fancybox({
                smallBtn: false,
                toolbar: false,
                beforeLoad: function (e,i,s) {
                    var clickedElement = s.opts.$orig;

                    if(clickedElement){
                        clickedElement.parents(".search-box-v2").addClass("fancy-click-element");
                    }
                }
            });
        }

        // $(document).on("beforeLoad.fb", function (event, instance, slide) {

            // instance.$refs.container.addClass("_loading"); // needed for transition on side panel
            //
            // var clickedElement = slide.opts.$orig;

            // if (clickedElement) {
            //     clickedElement.addClass("melih");
                // var direction = clickedElement.data("direction");

                // if (direction) {
                //     instance.$refs.container.addClass("side-" + direction);
                // }
            // }
        // });

        $(document).on("afterLoad.fb", function (event, instance) {
            main.carousel();
            main.timePicker();
            main.dropzone();
            main.validationsCommon();

            // if($(".print-button").length){
            //     $(".print-button").off("click").on({
            //         click: function () {
            //         }
            //     })
            // }
            // console.log(event);
            // console.log(instance.$refs.container);
            // instance.$refs.container.removeClass("_loading").addClass("_loaded"); // needed for transition on side panel
        });


    },
    validationMethods: function () {

        $('.no-paste').bind('cut copy paste', function (e) {
            e.preventDefault(); //disable cut,copy,paste
        });

        //only number
        $(".only-number").on({
            keydown: function (event) {
                // Allow: backspace, delete, tab, escape, enter and .
                if ($.inArray(event.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
                    // Allow: Ctrl+A, Command+A
                    (event.keyCode == 65 && (event.ctrlKey === true || event.metaKey === true)) ||
                    // Allow: home, end, left, right, down, up
                    (event.keyCode >= 35 && event.keyCode <= 40)) {
                    // let it happen, don't do anything
                    return;
                }

                // Ensure that it is a number and stop the keypress
                if ((event.shiftKey || (event.keyCode < 48 || event.keyCode > 57)) && (event.keyCode < 96 || event.keyCode > 105)) {
                    event.preventDefault();
                }
            }
        });

        //only string
        $.validator.addMethod("lettersonly", function (value, element) {
            return this.optional(element) || /^[a-z ğüöçıİş]+$/i.test(value);
        });

    },
    validationsCommon: function () {

        var $success,
            $error;

        if ($("form").length) {
            $("form").each(function (index, element) {
                $success = $(".form-general-success", element);
                $error = $(".form-general-error", element);
            });
        }

        // message lang control
        function validationLang (msgTr,msgEn,msgAr){
            var lang = $("html").attr("lang");

            if(lang == "tr"){
                return msgTr
            } else if(lang == "en"){
                return msgEn
            } else if(lang == "ar"){
                return msgAr
            }
        }

        // Google Recaptcha
        // $.validator.addMethod('reCaptchaMethod', function (value, element, param) {
        //     if (grecaptcha.getResponse() == ''){
        //         return false;
        //     } else {
        //         // I would like also to check server side if the recaptcha response is good
        //         return true
        //     }
        // }, 'You must complete the antispam verification');


        var defaults = {
            ignore: ".skip-validation, .dynamic-skip-validation",
            errorElement: "span",
            errorPlacement: function (error, element) {
                error.addClass("text-danger").appendTo(element.parents(".form-row"));
            },
            highlight: function (element, errorClass, validClass) {
                $(element).parents(".form-row").removeClass(validClass).addClass(errorClass);
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).parents(".form-row").removeClass(errorClass).addClass(validClass);
            },
            submitHandler: function (form) {

                $success.hide();
                $error.hide();

                if($(form).hasClass("login-user-name") || $(form).hasClass("login-user-code") || $(form).hasClass("login-pass-code")){
                    $(form).find(".form-container").addClass("loading");
                }

                if ($(form).data("url")) {

                    var url = $(form).data("url"),
                        type = $(form).attr("method") || "GET",
                        enctype = $(form).attr("enctype"),
                        data = $(form).serialize(),
                        newData = "";

                    if($(form).hasClass("login-user-code") || $(form).hasClass("login-pass-code")){
                        $(form).find("input[type='text']").each(function () {
                            var _this = $(this),
                                val = _this.val();
                            newData += val;
                        });

                        data = "code=" + newData;
                    }

                    var ajaxOptions = {
                        url: url,
                        type: type,
                        dataType: "json",
                        data: data,
                        cache: false,
                        timeout: 900000
                    };

                    // buttonLoading(form);

                    if (enctype === "multipart/form-data") {
                        $.extend(ajaxOptions, {
                            processData: false,
                            contentType: false,
                            timeout: 60000,
                            data: new FormData(form)
                        });
                    }

                    $.ajax(ajaxOptions)
                        .done(function (response) {

                            if (!response.status) {
                                if (response.message) {
                                    $(form).find(".form-general-error .alert-title").text(response.message);
                                    $(form).find(".form-general-error").show();
                                }

                                if($(form).hasClass("login-user-name") || $(form).hasClass("login-user-code") || $(form).hasClass("login-pass-code")){
                                    $(form).find(".form-container").removeClass("loading");
                                }

                            } else if(response.nextUrl){
                                window.location.href = response.nextUrl;
                            }
                            // buttonLoading(form);
                        });

                } else {
                    form.submit();
                }
            }
        };

        //
        // function buttonLoading (form) {
        //     $(form).find("button.button-1").toggleClass("btn-loading")
        // }

        // criminal Previous Case
        var previousCase = $(".previousCase");
        if (previousCase.length) {
            var options = $.extend({}, defaults);
            previousCase.validate(options);
        }

        var loginUserName = $(".login-user-name");
        if (loginUserName.length) {
            var options = $.extend({}, defaults, {
                rules: {
                    username: {
                        required: true
                    }
                },
                messages: {
                    username:{
                        required: "Bu alan boş geçilemez."
                    }

                }
            });
            loginUserName.validate(options);
        }


        var loginUserCode = $(".login-user-code");
        if (loginUserCode.length) {
            var options = $.extend({}, defaults, {
                groups: {
                    inputGroup: "userCode1 userCode2 userCode3 userCode4 userCode5",
                },
                rules: {
                    userCode1: {
                        required: true
                    },
                    userCode2: {
                        required: true
                    },
                    userCode3: {
                        required: true
                    },
                    userCode4: {
                        required: true
                    },
                    userCode5: {
                        required: true
                    }
                },
                messages: {
                    userCode1: {
                        required: "Bu alan boş geçilemez."
                    },
                    userCode2: {
                        required: "Bu alan boş geçilemez."
                    },
                    userCode3: {
                        required: "Bu alan boş geçilemez."
                    },
                    userCode4: {
                        required: "Bu alan boş geçilemez."
                    },
                    userCode5: {
                        required: "Bu alan boş geçilemez."
                    }
                }
            });
            loginUserCode.validate(options);
        }


        var loginPassCode = $(".login-pass-code");
        if (loginPassCode.length) {
            var options = $.extend({}, defaults, {
                groups: {
                    inputGroup: "passCode1 passCode2 passCode3",
                },
                rules: {
                    passCode1: {
                        required: true
                    },
                    passCode2: {
                        required: true
                    },
                    passCode3: {
                        required: true
                    }
                },
                messages: {
                    passCode1: {
                        required: "Bu alan boş geçilemez."
                    },
                    passCode2: {
                        required: "Bu alan boş geçilemez."
                    },
                    passCode3: {
                        required: "Bu alan boş geçilemez."
                    }
                }
            });
            loginPassCode.validate(options);
        }

    },
    alertify: function (status, message, time) {
        var html = "<div class='alertify "+ status +"'><p class='message'>"+ message +"</p></div>"
        $("body").append(html);
        $(".alertify").fadeIn(300);
        setInterval(function () {
            $(".alertify").fadeOut(300, function () {
                $(this).remove();
            });
        }, time)
    }
}

$(function () {
    main.init();
    main.inputAction();
    main.customSelectbox();
    main.addWitness();
    main.datePicker();
    main.timePicker();
    main.dropzone();
    main.carousel();
    main.fancybox();
    main.pagination();
    main.filterControl();
    main.validationMethods();
    main.validationsCommon();
});

function initMap() {
    if($("#map").length){
        main.cardTransactionLocations();
    }
}
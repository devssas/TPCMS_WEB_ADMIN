"use strict";

var main = {
    init: function () {
        // handlebars add URL

        Handlebars.registerHelper("link", function (icon, url) {
            var url = Handlebars.escapeExpression(url),
                icon = Handlebars.escapeExpression(icon);
            return new Handlebars.SafeString("<a href='" + url + "' class='button-v1 btn-color-1'><i class='" + icon + "'></i></a>");
        });

        Handlebars.registerHelper("inc", function (value, options) {
            return parseInt(value) + 1;
        });

        if ($("[name='httpError']").length && $("[name='httpError']").val() != "") {
            var message = $("[name='httpError']").val();
            $.fancybox.open({
                src: "assets/ajax/card/error.html",
                type: "ajax",
                smallBtn: false,
                afterLoad: function () {
                    if (message != "true") {
                        $(".custom-error-message").text(message);
                    }
                }
            });
        }
    },
    cardTransactionLocations: function () {

        var map = $("#map"),
            templateURL = map.data("template-url"),
            dataURL = map.data("json-url");

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
                url: dataURL,
                cache: false
            })
                .done(function (response) {
                    markersList = response;
                    createdMap();
                });

        }

        function createdMap() {
            map = new google.maps.Map(document.getElementById('map'), {
                center: new google.maps.LatLng(markersList.mapCenter.lat, markersList.mapCenter.lng),
                zoom: 11,
                mapTypeControl: false,
                streetViewControl: false,
                fullscreenControl: false
            });

            refreshMap();
        }

        function refreshMap() {
            if (templateURL) {
                $.ajax({
                    url: templateURL,
                    type: "GET",
                    cache: false
                })
                    .done(function (jsonTemplate) {
                        var template = Handlebars.compile(jsonTemplate),
                            tableHTML = template(markersList);

                        $(".horizontal-list.v1").html(tableHTML);

                    });
            }

            if (mapMarkersList) {
                mapMarkersList.clearMarkers();
                markers = [];
            }

            for (var i = 0; i < markersList.data.length; ++i) {
                newsMarkerList(markersList.data[i])
            }

            function newsMarkerList(newsMarkersList) {
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
        }, 300000);

    },
    inputAction: function () {
        if ($(".next-input-field").length) {
            var _this = $(".next-input-field"),
                charLimit = _this.attr("maxlength");

            _this.keydown(function (e) {

                var keys = [8, 9, 13,/*16, 17, 18,*/ 19, 20, 27, 33, 34, 35, 36, 37, 38, 39, 40, 45, 46, 144, 145];


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
                }
            });

        }

        if ($(".active-content-event").length) {


            $(".change-active-class").on({
                click: function () {
                    var _this = $(this),
                        _thisType = _this.attr("type"),
                        activeClass = _this.data("active-class");

                    if (_thisType == "checkbox") {


                        if (_this.is(":checked")) {
                            _this.parents(".active-content-event").find(".change-active-content[data-active-content='" + activeClass + "']").addClass("active").stop(false, false).slideDown();
                        } else {
                            _this.parents(".active-content-event").find(".change-active-content[data-active-content='" + activeClass + "']").removeClass("active").stop(false, false).slideUp();
                        }

                    } else if (_thisType == "radio") {
                        var _thisOpenClose = _this.data("open-close");

                        if (_thisOpenClose) {
                            _this.parents(".active-content-event").find(".change-active-content[data-active-content='" + activeClass + "']").addClass("active").stop(false, false).slideDown();
                        } else {
                            _this.parents(".active-content-event").find(".change-active-content[data-active-content='" + activeClass + "']").removeClass("active").stop(false, false).slideUp();
                        }


                    }


                    // _this.parents(".active-content-event").find(".change-active-content").each(function () {
                    //     var _this = $(this),
                    //         activeContent = _this.data("active-content");
                    //
                    //     if(_this.hasClass("active") && activeClass != activeContent){
                    //         _this.removeClass("active").find("input").val("");
                    //     } else if(activeClass == activeContent){
                    //         _this.addClass("active");
                    //     }
                    //
                    // });

                }
            }).each(function () {
                if ($(this).is(":checked")) {
                    $(this).trigger("click");
                }
            });

        }
    },
    customSelectbox: function () {

        if ($(".select-not-find").length) {
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

        if ($(".select-find").length) {
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
                            },
                            cache: false
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

        if ($(".crime-select").length) {

            $(".crime-select").each(function () {

                $(this).select2({
                    ajax: {
                        url: $(this).data("url"),
                        dataType: 'json',
                        processResults: function (data, params) {
                            // parse the results into the format expected by Select2
                            // since we are using custom formatting functions we do not need to
                            // alter the remote JSON data, except to indicate that infinite
                            // scrolling can be used
                            params.page = params.page || 1;
                            return {
                                results: data.data,
                                pagination: {
                                    more: (params.page * 30) < data.total_count
                                }
                            };
                        },
                        cache: false
                    },
                    minimumResultsForSearch: -1,
                    templateResult: formatRepo,
                    templateSelection: formatRepoSelection
                });

                function formatRepo(repo) {

                    if (repo.loading) {
                        return repo.text;
                    }

                    var $container = $(
                        "<div class='select-custom-options'><div class='title'>" + repo.title + "</div><div class='description'>" + repo.description + "</div></div>"
                    );

                    return $container;
                }

                function formatRepoSelection(repo) {
                    return repo.id;
                }

            });

        }

    },
    addWitness: function () {
        if ($(".addWitness").length) {
            var index = 1;

            $(".addWitness").on({
                click: function () {
                    var addWitnessHtml = "<div class='form-row'><a href='javascript:;' class='button button-v4 color-1 line-height-sml removeWitness'><i class='icon-cancel'></i></a><span class='label witness-label'>Witness Statments</span><div class='input-group-content'><label><input type='text' name='witnessName" + index + "' placeholder='Name'></label><label><input type='text' name='witnessSurname" + index + "' placeholder='Surname'></label></div><div class='form-row'><label><span class='label'></span><textarea name='witnessStatement" + index + "' placeholder='Statement'></textarea></label></div></div>";
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

        if ($(".datepicker").length) {
            $(".datepicker").each(function () {
                $(this).datepicker();
            });
        }

        if ($("#calendar").length) {
            var nextUrl = $("#calendar").data("next-url");

            $("#calendar").datepicker({
                onSelect: function (date, datepicker) {
                    window.location.href = nextUrl + "?calendarDate=" + date
                },
            });
        }

    },
    timePicker: function () {
        if ($('.timepicker').length) {
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

        if ($(".photo-upload").length) {

            Dropzone.autoDiscover = false;

            $(".photo-upload").each(function () {

                var _this = $(this).find(".photo-upload-inner"),
                    uploadUrl = _this.data("upload-url"),
                    deleteUrl = _this.data("delete-url"),
                    maxFiles = _this.data("max-files") ? _this.data("max-files") : null;

                if (!_this.hasClass("dz-started")) {
                    _this.dropzone({
                        autoProcessQueue: false,
                        url: uploadUrl,
                        maxFiles: maxFiles,
                        addRemoveLinks: true,
                        dictRemoveFile: "",
                        thumbnailMethod: "contain",
                        dictDefaultMessage: null,
                        removedfile: function (file) {

                            var name = file.name;

                            $.ajax({
                                url: deleteUrl,
                                type: "POST",
                                data: {fileName: name},
                                dataType: "html"
                            })
                                .done(function (response) {

                                    if (JSON.parse(response).status) {
                                        var _ref;
                                        return (_ref = file.previewElement) != null ? _ref.parentNode.removeChild(file.previewElement) : void 0;
                                    } else {
                                        $.fancybox.open({
                                            src: '/tpcmsWebAdmin/assets/ajax/card/error.html',
                                            type: "ajax",
                                            smallBtn: false
                                        });
                                    }

                                })
                                .fail(function (response) {
                                    $.fancybox.open({
                                        src: '/tpcmsWebAdmin/assets/ajax/card/error.html',
                                        type: "ajax",
                                        smallBtn: false
                                    });
                                });

                        },
                        init: function () {

                            var _initThis = this;

                            _initThis.on("addedfile", function (file) {
                                if (_initThis.files[maxFiles] != null) {
                                    _initThis.removeFile(_initThis.files[0]);
                                }
                            });

                            if (_this.parents(".photo-upload").hasClass("upload-view")) {
                                var uploadAjaxUrl = _this.data("upload-ajax-url");

                                $.ajax({
                                    url: uploadAjaxUrl
                                })
                                    .done(function (response) {

                                        for (var i = 0; i < response.data.length; i++) {

                                            _initThis.files.push(response.data[i]);
                                            _initThis.emit('addedfile', response.data[i]);
                                            _initThis.emit("thumbnail", response.data[i], response.data[i].url);
                                            _initThis.emit('complete', response.data[i]);
                                            response.data[i].previewElement.classList.add('dz-success');
                                            response.data[i].previewElement.classList.add('dz-complete')

                                        }

                                    });

                            }

                        },
                        accept: function (file, done) {

                            var reader = new FileReader();
                            reader.onload = handleReaderLoad;
                            reader.readAsDataURL(file);

                            function handleReaderLoad(evt) {

                                $.ajax({
                                    url: uploadUrl,
                                    method: "POST",
                                    dataType: "html",
                                    data: {
                                        fileBase64: evt.target.result,
                                        fileName: file.name,
                                        fileType: file.type,
                                        fileSize: file.size,
                                        key: null
                                    },
                                    cache: false,
                                    timeout: 900000,
                                    xhr: function () {

                                        var myXhr = $.ajaxSettings.xhr();

                                        if (file.previewElement) {

                                            var progressElement = file.previewElement.querySelector("[data-dz-uploadprogress]");

                                            if (myXhr.upload) {

                                                myXhr.upload.addEventListener("progress", function (event) {

                                                    var total = event.total,
                                                        loaded = event.loaded;

                                                    if (event.lengthComputable) {
                                                        var percent = Math.floor((loaded / total) * 100);

                                                        progressElement.style.width = percent + "%";

                                                    }
                                                }, false);
                                            }

                                        }

                                        return myXhr;
                                    }
                                })
                                    .done(function (response) {

                                        if (JSON.parse(response).status) {
                                            $(file.previewElement).addClass("dz-success dz-complete");
                                        } else {
                                            $(file.previewElement).addClass("dz-error dz-complete");
                                            $(file.previewElement).find("[data-dz-errormessage]").text(JSON.parse(response).message);

                                            $.fancybox.open({
                                                src: '/tpcmsWebAdmin/assets/ajax/card/error.html',
                                                type: "ajax",
                                                smallBtn: false
                                            });
                                        }
                                    })
                                    .fail(function (response) {
                                        $(file.previewElement).addClass("dz-error dz-complete");
                                        $(file.previewElement).find("[data-dz-errormessage]").text(response.responseText);

                                        $.fancybox.open({
                                            src: '/tpcmsWebAdmin/assets/ajax/card/error.html',
                                            type: "ajax",
                                            smallBtn: false
                                        });
                                    });
                            }

                            // done();
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

        if ($(".site-data-container").length) {

            $(".site-data-container").each(function () {
                var container = $(this),
                    filters = $(".site-data-filters", container),
                    content = $(".site-data-content", container),
                    pagination = $(".site-data-pagination", container),

                    templateURL = container.data("template-url"),
                    dataURL = container.data("json-url"),

                    templateHtml;

                $.ajax({
                    type: "GET",
                    url: templateURL,
                    async: false,
                    cache: false,
                    success: function (response) {
                        templateHtml = response;
                    }
                });

                function getList(isPaging) {

                    container.addClass("loading");

                    if (!isPaging) {
                        $("select", pagination).val("1").data("value", "1");
                    }

                    var data = $(":input", container).serialize();

                    $.ajax({
                        type: "GET",
                        url: dataURL,
                        dataType: "json",
                        data: data,
                        cache: false,
                        success: function (response) {

                            if (response.data.length || (response.data.tbody.length && response.data.thead.length )) {

                                var template = Handlebars.compile(templateHtml),
                                    output = template(response);

                                $(".error-text", container).text("").hide();

                                if ($(".complaints-detail-content").length) {
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
                                }

                                main.fancybox();

                                deferredData.resolve();

                            } else {
                                container.removeClass("loading");
                                pagination.hide();
                                content.html("").hide();
                                $(".error-text", container).text(response.message).show();

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
                        if (currentFiltersData != filtersData) {
                            currentFiltersData = filtersData;
                            getList();
                        }
                        event.preventDefault();
                    }
                });

                if ($(".change-on-submit").length) {
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

        } else {
            deferredData.resolve();
        }

        return deferredData;
    },
    filterControlv1: function () {
        var deferredData = $.Deferred();

        if ($(".site-data-container-v1").length) {

            $(".site-data-container-v1").each(function () {
                var container = $(this),
                    filters = $(".site-data-filters", container),
                    content = $(".site-data-content", container),
                    pagination = $(".site-data-pagination", container),

                    templateURL = container.data("template-url"),
                    dataURL = container.data("json-url"),

                    templateHtml;

                $.ajax({
                    type: "GET",
                    url: templateURL,
                    async: false,
                    cache: false,
                    success: function (response) {
                        templateHtml = response;
                    }
                });

                function getList(isPaging) {

                    container.addClass("loading");

                    if (!isPaging) {
                        $("select", pagination).val("1").data("value", "1");
                    }

                    var data = $(":input", container).serialize();

                    $.ajax({
                        type: "GET",
                        url: dataURL,
                        dataType: "json",
                        data: data,
                        cache: false,
                        success: function (response) {

                            if (response.data.length || response.data.thead.length) {

                                var template = Handlebars.compile(templateHtml),
                                    output = template(response);

                                $(".error-text", container).text("").hide();

                                if ($(".complaints-detail-content").length) {
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
                                }

                                main.fancybox();

                                deferredData.resolve();

                            } else {
                                container.removeClass("loading");
                                pagination.hide();
                                content.html("").hide();
                                $(".error-text", container).text(response.message).show();

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
                        if (currentFiltersData != filtersData) {
                            currentFiltersData = filtersData;
                            getList();
                        }
                        event.preventDefault();
                    }
                });

                if ($(".change-on-submit").length) {
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

        } else {
            deferredData.resolve();
        }

        return deferredData;
    },
    carousel: function () {
        if ($(".card-carousel").length) {
            $(".card-carousel").owlCarousel({
                rtl: true,
                margin: 15,
                nav: true,
                navText: ["<i class='icon-right-open'></i>", "<i class='icon-left-open'></i>"],
                dots: false
            });
        }
    },
    fancybox: function () {

        $.fancybox.defaults.touch = false;

        if ($("[data-fancybox-card]").length) {
            $("[data-fancybox-card]").fancybox({
                smallBtn: false,
                toolbar: false
            });
        }

        if ($("[data-fancybox-search]").length) {
            $("[data-fancybox-search]").fancybox({
                smallBtn: false,
                toolbar: false,
                beforeLoad: function (e, i, s) {
                    var clickedElement = s.opts.$orig;

                    if (clickedElement && clickedElement.parents(".search-box-v2").length) {
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
        function validationLang(msgTr, msgEn, msgAr) {
            var lang = $("html").attr("lang");

            if (lang == "tr") {
                return msgTr
            } else if (lang == "en") {
                return msgEn
            } else if (lang == "ar") {
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

                if ($(form).hasClass("login-user-name") || $(form).hasClass("login-user-code") || $(form).hasClass("login-pass-code")) {
                    $(form).find(".form-container").addClass("loading");
                }

                if ($(form).data("url")) {

                    var url = $(form).data("url"),
                        type = $(form).attr("method") || "GET",
                        enctype = $(form).attr("enctype"),
                        data = $(form).serialize(),
                        newData = "";

                    if ($(form).hasClass("login-user-code") || $(form).hasClass("login-pass-code")) {
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

                                if ($(form).hasClass("login-user-name") || $(form).hasClass("login-user-code") || $(form).hasClass("login-pass-code")) {
                                    $(form).find(".form-container").removeClass("loading");
                                }

                            } else if (response.nextUrl) {
                                window.location.href = response.nextUrl;
                            } else {

                                if ($(form).hasClass("previousCase") || $(form).hasClass("permitsCreateMissionCard")) {
                                    var templateUrl = $(form).data("template-url");

                                    $.ajax({
                                        url: templateUrl,
                                        method: "GET"
                                    })
                                        .done(function (responseTemplate) {

                                            $(form).find(".dynamic-content").addClass("loading");

                                            var template = Handlebars.compile(responseTemplate),
                                                tableHTML = template(response);

                                            $(form).find(".dynamic-content").html(tableHTML).removeClass("loading");

                                            if ($(".previous-case-id").length) {
                                                $(".previous-case-id").off("click").on({
                                                    click: function () {
                                                        var _this = $(this),
                                                            caseId = _this.data("case-id");

                                                        $(".fancy-click-element").find(".add-previous-case-id").val(caseId);

                                                        $.fancybox.close();
                                                    }
                                                });
                                            }

                                            if ($(".create-mission-card").length) {
                                                $(".create-mission-card").off("click").on({
                                                    click: function () {
                                                        var _this = $(this),
                                                            officerId = _this.data("officer-id"),
                                                            officerName = _this.data("officer-name"),
                                                            commandCenter = _this.data("command-center");

                                                        $(".add-create-mission-card-id").val(officerId);
                                                        $(".add-create-mission-card-name").val(officerName);
                                                        $(".add-create-mission-card-command-center").val(commandCenter);

                                                        $.fancybox.close();
                                                    }
                                                });
                                            }
                                        });
                                }
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

        // permits Create Mission Card
        var permitsCreateMissionCard = $(".permitsCreateMissionCard");
        if (permitsCreateMissionCard.length) {
            var options = $.extend({}, defaults);
            permitsCreateMissionCard.validate(options);
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
                    username: {
                        required: "This field cannot be empty"
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
                        required: "This field cannot be empty"
                    },
                    userCode2: {
                        required: "This field cannot be empty"
                    },
                    userCode3: {
                        required: "This field cannot be empty"
                    },
                    userCode4: {
                        required: "This field cannot be empty"
                    },
                    userCode5: {
                        required: "This field cannot be empty"
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
                        required: "This field cannot be empty"
                    },
                    passCode2: {
                        required: "This field cannot be empty"
                    },
                    passCode3: {
                        required: "This field cannot be empty"
                    }
                }
            });
            loginPassCode.validate(options);
        }

        var notification = $(".notification");
        if (notification.length) {
            var options = $.extend({}, defaults, {});
            notification.validate(options);
        }

    },
    alertify: function (status, message, time) {
        var html = "<div class='alertify " + status + "'><p class='message'>" + message + "</p></div>"
        $("body").append(html);
        $(".alertify").fadeIn(300);
        setInterval(function () {
            $(".alertify").fadeOut(300, function () {
                $(this).remove();
            });
        }, time)
    }
};

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
    main.filterControlv1();
    main.validationMethods();
    main.validationsCommon();
});

function initMap() {
    if ($("#map").length) {
        main.cardTransactionLocations();
    }
}
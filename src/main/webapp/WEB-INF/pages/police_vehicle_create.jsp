<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!doctype html>
<html lang="ar">
<head>
    <title>TPcms</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="TPcms">

    <meta property="og:title" content="TPcms">
    <meta property="og:description" content="TPcms">
    <meta property="og:url" content="">
    <meta property="og:image" content="assets/images/head/og-image.jpg">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/core.css">

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/head/favicon.png">
    <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/assets/images/head/60x60.png">
    <link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/assets/images/head/76x76.png">
    <link rel="apple-touch-icon" sizes="120x120" href="${pageContext.request.contextPath}/assets/images/head/120x120.png">
    <link rel="apple-touch-icon" sizes="152x152" href="${pageContext.request.contextPath}/assets/images/head/152x152.png">

</head>
<body>

<div class="page-wrapper">

    <div class="page-inner">

        <section class="content-wrapper">

            <jsp:include page="menu_bar_dashboard.jsp">
                <jsp:param name="policeVehicles" value="active" />
                <jsp:param name="officerName" value="${officerName}" />
                <jsp:param name="officerProfilePicture" value="${officerProfilePicture}" />
                <jsp:param name="accessRole" value="${accessRole}" />
                <jsp:param name="disabled" value="${disabled}" />
            </jsp:include>

            <section class="content-inner">

                <section class="content">

                    <jsp:include page="highlight_back_to_overview.jsp">
                        <jsp:param name="header" value="Add New Officer" />
                        <jsp:param name="redirect" value="policeVehiclesMenu" />
                        <jsp:param name="pageName" value="Police Vehicle Menu" />
                    </jsp:include>

                    <form action="">

                        <section class="content-box">
                            <div class="divided-content flout-2">
                                <div>
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>VEHICLE DETAILS</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Vehicle ID *</span>
                                                        <input type="text" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Vehicle Name *</span>
                                                        <input type="text" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Vehicle Name *</span>
                                                        <input type="text" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Plate Number *</span>
                                                        <input type="text" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Chasis Number</span>
                                                        <input type="text" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Command Center *</span>
                                                        <input type="text" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <span class="label">Activation Date</span>
                                                    <label>
                                                        <span class="icons-content">
                                                            <input type="text" class="datepicker" placeholder="Select Date">
                                                            <i class="icon-calender"></i>
                                                        </span>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <span class="label">Expiry Date</span>
                                                    <label>
                                                        <span class="icons-content">
                                                            <input type="text" class="datepicker" placeholder="Select Date">
                                                            <i class="icon-calender"></i>
                                                        </span>
                                                    </label>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>MISSION DETAILS</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row checkbox-row">
                                                    <label>
                                                        <span class="label">Permission to Carry Civilians</span>
                                                        <input type="checkbox" id="">
                                                        <span class="checkmark"></span>
                                                    </label>
                                                </div>
                                                <hr>
                                                <div class="form-row select-not-find">
                                                    <label>
                                                        <span class="label">Allowed Weapon Type 1</span>
                                                        <select name="" id="">
                                                            <option value="">Placeholder</option>
                                                        </select>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Weapon 1 Serial Number</span>
                                                        <input type="text" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                                <div class="form-row select-not-find">
                                                    <label>
                                                        <span class="label">Allowed Weapon Type 2</span>
                                                        <select name="" id="">
                                                            <option value="">Placeholder</option>
                                                        </select>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Weapon 2 Serial Number</span>
                                                        <input type="text" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                                <div class="form-row select-not-find">
                                                    <label>
                                                        <span class="label">Allowed Weapon Type 3</span>
                                                        <select name="" id="">
                                                            <option value="">Placeholder</option>
                                                        </select>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Weapon 3 Serial Number</span>
                                                        <input type="text" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>MISSION DETAILS</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row checkbox-row">
                                                    <label>
                                                        <span class="label">Permission to Carry Civilians</span>
                                                        <input type="checkbox" id="">
                                                        <span class="checkmark"></span>
                                                    </label>
                                                </div>
                                                <div class="form-row checkbox-row">
                                                    <label>
                                                        <span class="label">Permission to Carry Prisoners</span>
                                                        <input type="checkbox" id="">
                                                        <span class="checkmark"></span>
                                                    </label>
                                                </div>
                                                <div class="form-row checkbox-row">
                                                    <label>
                                                        <span class="label">Permission for Night Patrol</span>
                                                        <input type="checkbox" id="">
                                                        <span class="checkmark"></span>
                                                    </label>
                                                </div>
                                                <div class="form-row checkbox-row">
                                                    <label>
                                                        <span class="label">Permission Drive Outside City</span>
                                                        <input type="checkbox" id="">
                                                        <span class="checkmark"></span>
                                                    </label>
                                                </div>
                                                <hr>
                                                <div class="form-row form-with-icon">
                                                    <label>
                                                        <span class="label">Driver - Officer ID 1</span>
                                                        <input type="text" placeholder="Placeholder">
                                                        <i class="icon-search"></i>
                                                    </label>
                                                </div>
                                                <div class="form-row form-with-icon">
                                                    <label>
                                                        <span class="label">Driver - Officer ID 2</span>
                                                        <input type="text" placeholder="Placeholder">
                                                        <i class="icon-search"></i>
                                                    </label>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                </div>
                                <div>
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>VEHICLE PHOTOS</h3>
                                                <!--<a href="javascript:;" class="button button-v4 icon-btn-with-text-v2 color-1"><i class="icon-plus"></i>Add Photo</a>-->
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row multiple-photo-upload">
                                                    <div class="photo-upload upload-view">
                                                        <div class="photo-upload-inner" data-upload-url="/file/post" data-delete-url="/file/post" data-max-files="5" data-upload-ajax-url="/assets/ajax/upload-file/upload-file-multiple.json" ></div>
                                                        <div class="custom-upload">
                                                            <i class="icon-plus"></i>
                                                            <span class="description">Upload Photo</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>ADDITION REMARKS</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Crime Title</span>
                                                        <textarea name="" id="" placeholder="Placeholder"></textarea>
                                                    </label>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                </div>
                            </div> <!-- divided-content flout-2 -->
                            <div class="button-row">
                                <input type="submit" class="button button-v4 color-1 fix-size-sml" value="Create">
                                <a href="javascript:;" class="button button-v4 color-2 fix-size-sml">Cancel</a>
                            </div>
                        </section> <!-- content-box -->
                    </form>

                </section> <!-- content -->



            </section> <!-- content-inner -->

        </section> <!-- content-wrapper -->

    </div> <!-- page-inner -->

</div> <!-- page-wrapper -->
<script src="${pageContext.request.contextPath}/assets/js/lib.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/core.min.js"></script>
<script src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDp5oJvfmqhGjGaKJePviTrPeB4f9QCrEc&callback=initMap"></script>

</body>
</html>
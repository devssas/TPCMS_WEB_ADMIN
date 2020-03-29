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
                <jsp:param name="criminalDatabase" value="active" />
                <jsp:param name="officerName" value="${officerName}" />
                <jsp:param name="officerProfilePicture" value="${officerProfilePicture}" />
                <jsp:param name="accessRole" value="${accessRole}" />
                <jsp:param name="disabled" value="${disabled}" />
                <jsp:param name="dashboardPage" value="${dashboardPage}" />
                <jsp:param name="prosecutorPage" value="${prosecutorPage}" />
            </jsp:include>

            <section class="content-inner">

                <section class="content">

                    <jsp:include page="highlight_back_to_overview.jsp">
                        <jsp:param name="header" value="Manage Crime Report"/>
                        <jsp:param name="redirect" value="crimeReports"/>
                        <jsp:param name="pageName" value="Crime Reports"/>
                        <jsp:param name="httpError" value="${httpError}"/>
                    </jsp:include>

                    <section class="content-box">

                        <form:form id="create-misison-card-form" modelAttribute="updateCrimeReport" method="post" >
                            <section class="content-box">
                                <div class="divided-content flout-2">
                                    <div>
                                        <div class="box-v3">
                                            <div class="inner">
                                                <div class="highlight box-highlight">
                                                    <h3>Crime Details</h3>
                                                </div>
                                                <div class="form-container">
                                                    <div class="form-row">
                                                        <label>
                                                            <span class="label">Crime Location</span>
                                                            <form:input id="crimeLocation" path="crimeLocation" readonly="true"/>
                                                        </label>
                                                    </div>
                                                    <div class="form-row">
                                                        <label>
                                                            <span class="label">Crime Type</span>
                                                            <form:input id="crimeType" path="crimeType" readonly="true"/>
                                                        </label>
                                                    </div>
                                                    <div class="form-row">
                                                        <label>
                                                            <span class="label">Suspect Name (s)</span>
                                                            <form:input id="suspectName1" path="suspectName1" readonly="true"/>
                                                        </label>
                                                    </div>
                                                    <div class="form-row">
                                                        <label>
                                                            <span class="label">Suspect Name (s)</span>
                                                            <form:input id="suspectName2" path="suspectName2" readonly="true"/>
                                                        </label>
                                                    </div>
                                                    <div class="form-row">
                                                        <label>
                                                            <span class="label">Suspect Name (s)</span>
                                                            <form:input id="suspectName3" path="suspectName3" readonly="true"/>
                                                        </label>
                                                    </div>
                                                    <div class="form-row">
                                                        <label>
                                                            <span class="label">Crime Name</span>
                                                            <form:input id="crimeName" path="crimeName" readonly="true"/>
                                                        </label>
                                                    </div>
                                                    <div class="form-row">
                                                        <label>
                                                            <span class="label">Crime Scene</span>
                                                            <form:input id="crimeScene" path="crimeScene" readonly="true"/>
                                                        </label>
                                                    </div>
                                                    <div class="form-row">
                                                        <label>
                                                            <span class="label">National ID</span>
                                                            <form:input id="nationalId" path="nationalId" readonly="true"/>
                                                        </label>
                                                    </div>
                                                    <div class="form-row">
                                                        <label>
                                                            <span class="label">Passport Number</span>
                                                            <form:input id="passportNumber" path="passportNumber" readonly="true"/>
                                                        </label>
                                                    </div>
                                                    <div class="form-row">
                                                        <label>
                                                            <span class="label">Driving License Number</span>
                                                            <form:input id="drivingLicenseNumber" path="drivingLicenseNumber" readonly="true"/>
                                                        </label>
                                                    </div>
                                                    <div class="form-row phone-number-row">
                                                        <label>
                                                            <span class="label">Contact Mobile</span>
                                                            <span class="phone-number-content">
                                                            <form:input id="mobileNumberCountryCode" path="mobileNumberCountryCode" readonly="true"/>
                                                            <form:input id="mobileNumber" path="mobileNumber" readonly="true"/>
                                                        </span>
                                                        </label>
                                                    </div>
                                                    <div class="form-row">
                                                        <label>
                                                            <span class="label">Suspect Known Address</span>
                                                            <form:textarea id="suspectKnownAddress" path="suspectKnownAddress" readonly="true"/>
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
                                                    <h3>CRIME DETAILS</h3>
                                                </div>
                                                <div class="form-container">
                                                    <div class="form-row multiple-photo-upload">
                                                        <label>
                                                            <span class="label">Images</span>
                                                        </label>
                                                        <div class="photo-upload upload-view">
                                                            <div class="photo-upload-inner" data-upload-url="/file/post" data-delete-url="/file/post" data-max-files="5" data-upload-ajax-url="/assets/ajax/upload-file/upload-file-multiple.json" ></div>
                                                            <div class="custom-upload">
                                                                <i class="icon-plus"></i>
                                                                <span class="description">Upload Photo</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-row">
                                                        <label>
                                                            <span class="label">Crime Brief</span>
                                                            <form:textarea id="crimeBrief" path="crimeBrief" readonly="true"/>
                                                        </label>
                                                    </div>
                                                    <div class="form-row text-row">
                                                        <div class="input-group-content">
                                                            <label>
                                                                <span class="label">Reporting Officer</span>
                                                                <span class="text-v1">"${updateCrimeReport.reportingOfficer}"</span>
                                                            </label>
                                                            <label>
                                                                <span class="label">ID Number</span>
                                                                <span class="text-v1">"${updateCrimeReport.idNumber}"</span>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div> <!-- form-container -->
                                            </div> <!-- inner -->
                                        </div> <!-- box-v3 -->
                                    </div>
                                </div> <!-- divided-content flout-2 -->
                                <div class="button-row">
                                    <input name="acceptButton" type="submit" class="button button-v4 color-3 fix-size-sml" value="Approve" ${buttonType}>
                                    <input name="rejectButton" type="submit" class="button button-v4 color-4 fix-size-sml" value="Reject" ${buttonType}>
                                    <a href="${pageContext.request.contextPath}/crimeReports" class="button button-v4 color-2 fix-size-sml">Cancel</a>
                                </div>
                            </section> <!-- content-box -->
                        </form:form>

                    </section>

                </section>



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
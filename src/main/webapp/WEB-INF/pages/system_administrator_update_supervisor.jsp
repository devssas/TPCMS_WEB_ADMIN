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
                <jsp:param name="administrator" value="active" />
                <jsp:param name="officerName" value="${officerName}" />
                <jsp:param name="officerProfilePicture" value="${officerProfilePicture}" />
                <jsp:param name="accessRole" value="${accessRole}" />
                <jsp:param name="dashboardPage" value="${dashboardPage}" />
            </jsp:include>

            <section class="content-inner">

                <section class="content">

                    <jsp:include page="highlight_back_to_overview.jsp">
                        <jsp:param name="header" value="Update Supervisor" />
                        <jsp:param name="redirect" value="systemAdministrator"/>
                        <jsp:param name="pageName" value="Overview"/>
                        <jsp:param name="httpError" value="${httpError}"/>
                    </jsp:include>

                    <form:form id="new-supervisor" modelAttribute="updateSupervisor" method="post" enctype="multipart/form-data">

                    <section class="content-box">
                            <div class="divided-content flout-2">
                                <div>
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>IDENTITIY</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row single-photo-upload">
                                                    <div class="photo-upload">
                                                        <div class="photo-upload-inner" data-upload-url="/file/post" data-delete-url="/file/post" data-max-files="1" data-upload-ajax-url="/assets/ajax/upload-file/upload-file-single.json"></div>
                                                        <i class="icon-admin-profile"></i>
                                                        <span class="description">Upload Photo</span>
                                                    </div>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">National ID *</span>
                                                        <form:input id="nationalId" path="nationalId" />
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <span class="label">Name*</span>
                                                    <div class="input-group-content">
                                                        <label>
                                                            <form:input id="firstName" path="firstName" />
                                                        </label>
                                                        <label>
                                                            <form:input id="middleName" path="middleName" />
                                                        </label>
                                                        <label>
                                                            <form:input id="lastName" path="lastName" />
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="form-row">
                                                    <span class="label">Date of Birth</span>
                                                    <label>
                                                        <span class="icons-content">
                                                            <form:input id="dateOfBirth" class="datepicker" path="dateOfBirth" />
                                                            <i class="icon-calender"></i>
                                                        </span>
                                                    </label>
                                                </div>
                                                <div class="form-row radio-btn-v1-row">
                                                    <span class="label">Gender</span>
                                                    <div class="radio-btn-group">
                                                        <div>
                                                            <label>
                                                                <span class="text">Male</span>
                                                                <form:radiobutton id="genderMale" path="gender" value="true" checked="checked"/>
                                                                <span class="checkmark"></span>
                                                            </label>
                                                        </div>
                                                        <div>
                                                            <label>
                                                                <span class="text">Female</span>
                                                                <form:radiobutton id="genderFemale" path="gender" value="false"/>
                                                                <span class="checkmark"></span>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Passport Number</span>
                                                        <form:input id="passportNumber" path="passportNumber" />
                                                    </label>
                                                </div>
                                                <div class="form-row phone-number-row">
                                                    <label>
                                                        <span class="label">Mobile Number *</span>
                                                        <span class="phone-number-content">
                                                            <form:input id="countryCode" path="countryCode"  value="218"/>
                                                            <form:input id="mobileNumber" path="mobileNumber" />
                                                        </span>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Email *</span>
                                                        <form:input id="email" path="email" />
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">OfficerId *</span>
                                                        <form:input id="officerId" path="officerId"/>
                                                    </label>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="form-container">
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">UserCode *</span>
                                                        <form:input id="userCode" path="userCode" maxlength="5"/>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">PassCode *</span>
                                                        <form:input id="passCode" path="passCode" maxlength="3"/>
                                                    </label>
                                                </div>
                                                <div class="form-row select-not-find">
                                                    <label>
                                                        <span class="label">Reporting Officer Id *</span>
                                                        <form:input id="reportingOfficer" path="reportingOfficer" />
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
                                                <h3>LOCATION</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Location</span>
                                                        <form:input id="location" path="location" />
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">City *</span>
                                                        <form:input id="city" path="city" />
                                                    </label>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>LOCATION</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row select-not-find">
                                                    <div class="input-group-content">
                                                        <label>
                                                            <span class="label">Command Center *</span>
                                                            <form:select id="commandCenter" path="commandCenter" items="${commandCenters}" />
                                                        </label>
                                                        <label>
                                                            <span class="label">Location ICON/Color *</span>
                                                            <form:input id="locationIconColor" class="locationIconColor" path="locationIconColor" />
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="form-row select-not-find">
                                                    <label>
                                                        <span class="label">Username *</span>
                                                        <form:input id="userName" class="userName" path="userName" />
                                                    </label>
                                                </div>
                                                <div class="form-row checkbox-row">
                                                    <label>
                                                        <span class="label">Permission to Web Access</span>
                                                        <form:checkbox id="permissionToWebAccess" path="permissionToWebAccess" />
                                                        <span class="checkmark"></span>
                                                    </label>
                                                </div>
                                                <div class="form-row select-not-find">
                                                    <div class="input-group-content">
                                                        <label>
                                                            <span class="label">Type *</span>
                                                            <form:select id="supervisorType" path="supervisorType" items="${accessRoles}" />
                                                        </label>
                                                        <label>
                                                            <span class="label">Unit *</span>
                                                            <form:select id="supervisorUnit" path="supervisorUnit" items="${reportingUnits}" />
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="form-row select-not-find">
                                                    <div class="input-group-content">
                                                        <label>
                                                            <span class="label">Officer Grade</span>
                                                            <form:select id="officerGrade" path="supervisorOfficerGrade"  items="${officerGrades}" />
                                                        </label>
                                                        <label>
                                                            <span class="label">Officer Rank</span>
                                                            <form:select id="officerRank" path="supervisorOfficerRank"  items="${officerRanks}" />
                                                        </label>
                                                    </div>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>EMERGENCY</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Next of Kin *</span>
                                                        <form:input id="nextOfKin" class="nextOfKin" path="nextOfKin" />
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Emergency Contact Person *</span>
                                                        <form:input id="emergencyContactPerson" class="emergencyContactPerson" path="emergencyContactPerson" />
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Relationship with Contact Person *</span>
                                                        <form:input id="relationshipWithContactPerson" class="relationshipWithContactPerson" path="relationshipWithContactPerson" />
                                                    </label>
                                                </div>
                                                <div class="form-row phone-number-row">
                                                    <label>
                                                        <span class="label">Emergency Contact Number *</span>
                                                        <span class="phone-number-content">
                                                            <form:input id="emergencyContactCountryCode" class="emergencyContactCountryCode" path="emergencyContactCountryCode" />
                                                            <form:input id="emergencyContactNumber" class="emergencyContactNumber" path="emergencyContactNumber" />
                                                      </span>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Blood Group *</span>
                                                        <form:input id="bloodGroup" class="bloodGroup" path="bloodGroup" />
                                                    </label>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                </div>
                            </div> <!-- divided-content flout-2 -->
                            <div class="button-row">
                                <input type="submit" class="button button-v4 color-1 fix-size-sml" value="Update">
                                <a href="${pageContext.request.contextPath}/systemAdministratorMenu" class="button button-v4 color-2 fix-size-sml">Cancel</a>
                            </div>
                        </section> <!-- content-box -->
                    </form:form>

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
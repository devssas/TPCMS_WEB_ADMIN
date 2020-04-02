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
                <jsp:param name="staff" value="active" />
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
                        <jsp:param name="header" value="Add New Officer" />
                        <jsp:param name="redirect" value="policeStaffMenu" />
                        <jsp:param name="pageName" value="Police Staff Menu" />
                        <jsp:param name="httpError" value="${httpError}" />
                    </jsp:include>

                    <form:form id="new-notification-form" modelAttribute="newPoliceStaff" method="post" enctype="multipart/form-data" >

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
                                                        <div class="photo-upload-inner" data-upload-url="${pageContext.request.contextPath}/api/image/save?pageName=${currentPageName}" data-delete-url="${pageContext.request.contextPath}/api/image/delete?pageName=${currentPageName}" data-max-files="1"></div>
                                                        <i class="icon-admin-profile"></i>
                                                        <span class="description">Upload Photo</span>
                                                    </div>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">National ID *</span>
                                                        <form:input id="nationalId" path="nationalId" placeholder="National Id"/>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">First Name *</span>
                                                        <form:input id="firstName" path="firstName" placeholder="First Name"/>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Middle Name</span>
                                                        <form:input id="middleName" path="middleName" placeholder="Middle Name"/>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Last Name *</span>
                                                        <form:input id="lastName" path="lastName" placeholder="Last Name"/>
                                                    </label>
                                                </div>

                                                <div class="form-row">
                                                    <span class="label">Date of Birth</span>
                                                    <label>
                                                        <span class="icons-content">
                                                            <form:input id="dateOfBirth" class="datepicker" path="dateOfBirth" placeholder="Date Of Birth"/>
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
                                                        <form:input id="passportNumber" path="passportNumber" placeholder="Passport number"/>
                                                    </label>
                                                </div>
                                                <div class="form-row phone-number-row">
                                                    <label>
                                                        <span class="label">Mobile Number *</span>
                                                        <span class="phone-number-content">
                                                             <form:input id="countryCode" path="countryCode" placeholder="218" value="218"/>
                                                             <form:input id="mobileNumber" path="mobileNumber" placeholder="5555555"/>
                                                        </span>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Email</span>
                                                        <form:input id="email" path="email" placeholder="tp@gmail.com"/>
                                                    </label>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>WEAPONARY DETAILS</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row radio-btn-v1-row">
                                                    <span class="label">Permission to Carry</span>
                                                    <div class="radio-btn-group">
                                                        <div class="form-row checkbox-row">
                                                            <label>
                                                                <span class="label">Permission to Carry Weapon</span>
                                                                <form:checkbox id="isPermittedToCarryWeapon" path="permittedToCarryWeapon" data-active-class="permission-to-carry-weapon" cssClass="change-active-class"/>
                                                                <span class="checkmark"></span>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="change-active-content" data-active-content="permission-to-carry-weapon">
                                                    <div class="form-row select-not-find">
                                                        <label>
                                                            <span class="label">Type</span>
                                                            <form:select id="weaponType" path="weaponType" items="${weaponTypes}" />
                                                        </label>
                                                    </div>
                                                    <div class="form-row">
                                                        <label>
                                                            <span class="label">SRL Number</span>
                                                            <form:input id="serialNumber" path="serialNumber" placeholder="34DF455"/>
                                                        </label>
                                                    </div>
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
                                                        <span class="label">Contact Address*</span>
                                                        <form:input id="contactAddress" path="contactAddress" placeholder="Address"/>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">City *</span>
                                                        <form:input id="city" path="city" placeholder="City"/>
                                                    </label>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->

                                    <!--   <div class="box-v3">
                                           <div class="inner">
                                               <div class="highlight box-highlight">
                                                   <h3>PROFILE CREDENTIALS</h3>
                                               </div>
                                               <div class="form-container">
                                                   <div class="form-row">
                                                       <a href="" class="button button-v4 color-2">Reset Password</a>
                                                   </div>
                                               </div>
                                        </div>
                                    </div>  box-v3 -->

                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>PROFILE DETAILS</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Reporting Officer *</span>
                                                        <form:input id="reportingOfficer" path="reportingOfficer" placeholder="Reporting Officer Id"/>
                                                    </label>
                                                </div>
                                                <div class="form-row select-not-find">
                                                    <label>
                                                        <span class="label">Officer Grade</span>
                                                        <form:select id="officerGrade" path="officerGrade"  items="${officerGrades}" />
                                                    </label>
                                                </div>
                                                <div class="form-row select-not-find">
                                                    <label>
                                                        <span class="label">Officer Rank</span>
                                                        <form:select id="officerRank" path="officerRank"  items="${officerRanks}" />
                                                    </label>
                                                </div>
                                                <div class="form-row select-not-find">
                                                    <label>
                                                        <span class="label">Reporting Unit *</span>
                                                        <form:select id="reportingUnit" path="reportingUnit"  items="${reportingUnits}" />
                                                    </label>
                                                </div>
                                                <div class="form-row select-not-find">
                                                    <label>
                                                        <span class="label">Access Role *</span>
                                                        <form:select id="accessRole" path="accessRole"  items="${accessRoles}" />
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Officer ID *</span>
                                                        <form:input id="officerId" path="officerId" placeholder="Officer Id"/>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <span class="label">Employment Start Date</span>
                                                    <label>
                                                        <span class="icons-content">
                                                            <form:input id="employmentStartDate" class="datepicker" path="employmentStartDate" placeholder="Start Date"/>
                                                            <i class="icon-calender"></i>
                                                        </span>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <span class="label">Expiry Date</span>
                                                    <label>
                                                        <span class="icons-content">
                                                            <form:input id="expiryDate" class="datepicker" path="expiryDate" placeholder="Expiry Date"/>
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
                                                <h3>EMERGENCY</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Next of Kin *</span>
                                                        <form:input id="nextOfKin" class="nextOfKin" path="nextOfKin" placeholder="Next Of Kin"/>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Emergency Contact Person *</span>
                                                        <form:input id="emergencyContactPerson" class="emergencyContactPerson" path="emergencyContactPerson" placeholder="Emergency Contact Person"/>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Relationship with Contact Person *</span>
                                                        <form:input id="relationshipWithContactPerson" class="relationshipWithContactPerson" path="relationshipWithContactPerson" placeholder="Relationship With Contact Person"/>
                                                    </label>
                                                </div>
                                                <div class="form-row phone-number-row">
                                                    <label>
                                                        <span class="label">Emergency Contact Number 1 *</span>
                                                        <span class="phone-number-content">
                                                            <form:input id="emergencyContactCountryCode1" class="emergencyContactCountryCode1" path="emergencyContactCountryCode1" placeholder="+218"/>
                                                            <form:input id="emergencyContactNumber1" class="emergencyContactNumber1" path="emergencyContactNumber1" placeholder="1234567890"/>
                                                        </span>
                                                    </label>
                                                </div>
                                                <div class="form-row phone-number-row">
                                                    <label>
                                                        <span class="label">Emergency Contact Number 2 *</span>
                                                        <span class="phone-number-content">
                                                            <form:input id="emergencyContactCountryCode2" class="emergencyContactCountryCode2" path="emergencyContactCountryCode2" placeholder="+218"/>
                                                            <form:input id="emergencyContactNumber2" class="emergencyContactNumber2" path="emergencyContactNumber2" placeholder="1234567890"/>
                                                        </span>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Blood Group *</span>
                                                        <form:input id="bloodGroup" class="bloodGroup" path="bloodGroup" placeholder="A(+) rh"/>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Visual Identification Mark *</span>
                                                        <form:input id="visualIdentificationMark" class="visualIdentificationMark" path="visualIdentificationMark" placeholder="Identification Mark"/>
                                                    </label>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                </div>
                            </div> <!-- divided-content flout-2 -->
                            <div class="button-row">
                                <input type="submit" class="button button-v4 color-1 fix-size-sml" value="Create User">
                                <a href="${pageContext.request.contextPath}/officerProfiles" class="button button-v4 color-2 fix-size-sml">Cancel</a>
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

</body>
</html>
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
                <jsp:param name="dashboardPage" value="${dashboardPage}" />
                <jsp:param name="prosecutorPage" value="${prosecutorPage}" />
            </jsp:include>

            <section class="content-inner">

                <section class="content">

                    <jsp:include page="highlight_back_to_overview.jsp">
                        <jsp:param name="header" value="Update Vehicle" />
                        <jsp:param name="redirect" value="policeVehicles" />
                        <jsp:param name="pageName" value="Police Vehicle List" />
                        <jsp:param name="httpError" value="${httpError}" />
                    </jsp:include>

                    <form:form id="update-police-vehicle" modelAttribute="updatePoliceVehicle" method="post" enctype="multipart/form-data">

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
                                                        <span class="label">Vehicle Id *</span>
                                                        <form:input id="id" path="id" placeholder="Vehicle Id" disabled="true"/>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Vehicle Name *</span>
                                                        <form:input id="vehicleName" path="vehicleName" placeholder="Vehicle Name"/>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Plate Number *</span>
                                                        <form:input id="plateNumber" path="plateNumber" placeholder="Plate Name"/>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Chase Number</span>
                                                        <form:input id="chaseNumber" path="chaseNumber" placeholder="Chase Name"/>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Command Center *</span>
                                                        <form:select id="commandCenter" path="commandCenter"  items="${commandCenters}" />
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Reporting Unit *</span>
                                                        <form:select id="unit" path="unit"  items="${reportingUnits}" />
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <span class="label">Activation Date</span>
                                                    <label>
                                                        <span class="icons-content">
                                                            <form:input id="activationDate" class="datepicker" path="activationDate" placeholder="Activation Date"/>
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
                                                <h3>MISSION DETAILS</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="active-content-event">
                                                    <div class="form-row checkbox-row">
                                                        <label>
                                                            <span class="label">Permission to Carry Weapons</span>
                                                            <form:checkbox id="isPermittedToCarryWeapon" path="permittedToCarryWeapon" data-active-class="permission-to-carry-weapon" cssClass="change-active-class"/>
                                                            <span class="checkmark"></span>
                                                        </label>
                                                    </div>
                                                    <hr>
                                                    <div class="change-active-content" data-active-content="permission-to-carry-weapon">
                                                        <div class="form-row select-not-find">
                                                            <label>
                                                                <span class="label">Allowed Weapon Type 1</span>
                                                                <form:select id="weaponType1" path="allowedWeaponType1"  items="${weaponTypes}" />
                                                            </label>
                                                        </div>
                                                        <div class="form-row">
                                                            <label>
                                                                <span class="label">Weapon 1 Serial Number</span>
                                                                <form:input id="weapon1SerialNumber" path="weapon1SerialNumber" placeholder="Serial Number"/>
                                                            </label>
                                                        </div>
                                                        <div class="form-row select-not-find">
                                                            <label>
                                                                <span class="label">Allowed Weapon Type 2</span>
                                                                <form:select id="weaponType2" path="allowedWeaponType2"  items="${weaponTypes}" />

                                                            </label>
                                                        </div>
                                                        <div class="form-row">
                                                            <label>
                                                                <span class="label">Weapon 2 Serial Number</span>
                                                                <form:input id="weapon2SerialNumber" path="weapon2SerialNumber" placeholder="Serial Number"/>
                                                            </label>
                                                        </div>
                                                        <div class="form-row select-not-find">
                                                            <label>
                                                                <span class="label">Allowed Weapon Type 3</span>
                                                                <form:select id="weaponType3" path="allowedWeaponType3"  items="${weaponTypes}" />
                                                            </label>
                                                        </div>
                                                        <div class="form-row">
                                                            <label>
                                                                <span class="label">Weapon 3 Serial Number</span>
                                                                <form:input id="weapon3SerialNumber" path="weapon3SerialNumber" placeholder="Serial Number"/>
                                                            </label>
                                                        </div>
                                                    </div>
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
                                                        <form:checkbox id="isPermittedToCarryCivilians" path="permittedToCarryCivilians" />
                                                        <span class="checkmark"></span>
                                                    </label>
                                                </div>
                                                <div class="form-row checkbox-row">
                                                    <label>
                                                        <span class="label">Permission to Carry Prisoners</span>
                                                        <form:checkbox id="isPermittedToCarryPrisoners" path="permittedToCarryPrisoners" />
                                                        <span class="checkmark"></span>
                                                    </label>
                                                </div>
                                                <div class="form-row checkbox-row">
                                                    <label>
                                                        <span class="label">Permission for Night Patrol</span>
                                                        <form:checkbox id="isPermittedToNightPatrol" path="permittedToNightPatrol" />
                                                        <span class="checkmark"></span>
                                                    </label>
                                                </div>
                                                <div class="form-row checkbox-row">
                                                    <label>
                                                        <span class="label">Permission Drive Outside City</span>
                                                        <form:checkbox id="isPermittedToDriveOutsideCity" path="permittedToDriveOutsideCity" />
                                                        <span class="checkmark"></span>
                                                    </label>
                                                </div>
                                                <hr>
                                                <div class="form-row form-with-icon">
                                                    <label>
                                                        <span class="label">Driver - Officer ID 1</span>
                                                        <form:input id="driverOfficerId1" path="driverOfficerId1" placeholder="Officer Id"/>
                                                        <i class="icon-search"></i>
                                                    </label>
                                                </div>
                                                <div class="form-row form-with-icon">
                                                    <label>
                                                        <span class="label">Driver - Officer ID 2</span>
                                                        <form:input id="driverOfficerId2" path="driverOfficerId2" placeholder="Officer Id"/>
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
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row multiple-photo-upload">
                                                    <div class="photo-upload upload-view">
                                                        <div class="photo-upload-inner" data-upload-ajax-url="assets/ajax/upload-file/upload-file-multiple.json" data-upload-url="/file/post" data-delete-url="/file/post" data-max-files="5"></div>
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
                                                        <form:textarea id="additionalRemarks" path="additionalRemarks" placeholder="Placeholder"/>
                                                    </label>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                </div>
                            </div> <!-- divided-content flout-2 -->
                            <div class="button-row">
                                <input type="submit" class="button button-v4 color-1 fix-size-sml" value="Update">
                                <a href="${pageContext.request.contextPath}/policeVehicles" class="button button-v4 color-2 fix-size-sml">Cancel</a>
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
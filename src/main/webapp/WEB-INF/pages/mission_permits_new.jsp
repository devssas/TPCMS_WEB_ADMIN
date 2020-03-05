<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8"  %>

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
                <jsp:param name="missionPermits" value="active" />
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
                        <jsp:param name="header" value="Create Mission Card" />
                        <jsp:param name="redirect" value="missionPermitsMenu" />
                        <jsp:param name="pageName" value="Overview" />
                    </jsp:include>

                    <form:form id="create-misison-card-form" modelAttribute="newMissionPermit" method="post" >

                        <section class="content-box">
                            <div class="divided-content flout-2">
                                <div>
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>OFFICER DETAILS</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row form-with-icon">
                                                    <label>
                                                        <span class="label">Officer ID *</span>
                                                        <form:input id="officerId" class="field-query officerId-call" path="officerId" placeholder="Ex:123456" data-url="${pageContext.request.contextPath}/api/reporting-officer/" data-min-lenght="2" data-method="post" />
                                                        <i class="icon-search"></i>
                                                    </label>
                                                    <form:errors id="officerId" path="officerId" cssClass="text-danger" />
                                                </div>
                                                <div class="form-row form-with-icon">
                                                    <label>
                                                        <span class="label">Officer Name *</span>
                                                        <form:input id="officerName" class="field-query officerName-call" path="officerName" placeholder="Ex:Test test" data-url="${pageContext.request.contextPath}/api/reporting-officer/" data-min-lenght="3"/>
                                                        <i class="icon-search"></i>
                                                    </label>
                                                    <form:errors id="officerName" path="officerName" cssClass="text-danger" />
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Command Center *</span>
                                                        <form:input id="commandCenter" path="commandCenter" placeholder="Ex: Command Center"/>
                                                    </label>
                                                    <form:errors id="commandCenter" path="commandCenter" cssClass="text-danger" />
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
                                                        <span class="label">Permission to Carry Weapon</span>
                                                        <form:checkbox id="isPermittedToCarryWeapon" path="permittedToCarryWeapon" />
                                                        <span class="checkmark"></span>
                                                    </label>
                                                </div>
                                                <hr>
                                                <div class="form-row select-not-find">
                                                    <label>
                                                        <span class="label">Weapon Type</span>
                                                        <form:select id="weaponType" path="weaponType" type="text" items="${weaponTypes}"/>
                                                    </label>
                                                    <form:errors id= "weaponType"  path="weaponType" cssClass="text-danger" />
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Mission Type</span>
                                                        <form:input id="missionType" path="missionType" placeholder="Ex: Mission Type"/>
                                                    </label>
                                                    <form:errors id="missionType" path="missionType" cssClass="text-danger" />
                                                </div>
                                                <div class="form-row select-not-find">
                                                    <label>
                                                        <span class="label">Mission Description</span>
                                                        <form:textarea id="missionDescription" path="missionDescription" class="mid" placeholder="Mission Description"/>
                                                    </label>
                                                    <form:errors id="missionDescription" path="missionDescription" cssClass="text-danger" />
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
                                </div>
                                <div>
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>ADDITIONAL REMARKS</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row">
                                                    <label>
                                                        <form:textarea id="additionalRemarks" path="additionalRemarks" class="mid" placeholder="Statement"/>
                                                    </label>
                                                    <form:errors id="additionalRemarks" path="additionalRemarks" cssClass="text-danger" />
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                </div>
                            </div> <!-- divided-content flout-2 -->
                            <div class="button-row">
                                <input type="submit" class="button button-v4 color-1 fix-size-sml" value="Create">
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
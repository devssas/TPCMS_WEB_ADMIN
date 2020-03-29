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

    <link rel="stylesheet" href="assets/css/core.css">

    <link rel="shortcut icon" href="assets/images/head/favicon.png">
    <link rel="apple-touch-icon" href="assets/images/head/60x60.png">
    <link rel="apple-touch-icon" sizes="76x76" href="assets/images/head/76x76.png">
    <link rel="apple-touch-icon" sizes="120x120" href="assets/images/head/120x120.png">
    <link rel="apple-touch-icon" sizes="152x152" href="assets/images/head/152x152.png">

</head>
<body>

<div class="page-wrapper">

    <div class="page-inner">

        <section class="content-wrapper">

            <jsp:include page="menu_bar_dashboard.jsp">
                <jsp:param name="home" value="active"/>
                <jsp:param name="officerName" value="${officerName}"/>
                <jsp:param name="officerProfilePicture" value="${officerProfilePicture}"/>
                <jsp:param name="accessRole" value="${accessRole}"/>
                <jsp:param name="disabled" value="${disabled}"/>
                <jsp:param name="dashboardPage" value="${dashboardPage}"/>
                <jsp:param name="prosecutorPage" value="${prosecutorPage}"/>
            </jsp:include>

            <section class="content-inner">

                <section class="content">

                    <jsp:include page="highlight_back_to_overview.jsp">
                        <jsp:param name="header" value="SOS Calls Details "/>
                        <jsp:param name="redirect" value="sos"/>
                        <jsp:param name="pageName" value="SOS menu"/>
                        <jsp:param name="httpError" value="${httpError}" />
                    </jsp:include>

                    <form:form id="update-police-vehicle" modelAttribute="updateSos" method="post"
                               enctype="multipart/form-data">
                        <section class="content-box">
                            <div class="inner">
                                <div class="form-container">
                                    <div class="form-row text-row select-not-find">
                                        <div class="input-group-content">
                                            <input type="hidden" name="id" value=${updateSos.id}>
                                            <label>
                                                <span class="label">Request SRL Number</span>
                                                <span class="text-v1">${updateSos.requestSerialNumber}</span>
                                                <input type="hidden" name="requestSerialNumber"
                                                       value=${updateSos.requestSerialNumber}>
                                            </label>
                                            <label>
                                                <span class="label">Request Date</span>
                                                <span class="text-v1">${updateSos.requestDate}</span>
                                                <input type="hidden" name="requestDate" value=${updateSos.requestDate}>
                                            </label>
                                            <label>
                                                <span class="label">Username</span>
                                                <span class="text-v1">${updateSos.username}</span>
                                                <input type="hidden" name="username" value=${updateSos.username}>
                                            </label>
                                            <label>
                                                <span class="label">Phone Number</span>
                                                <span class="text-v1">${updateSos.phoneNumber}</span>
                                                <input type="hidden" name="phoneNumber" value=${updateSos.phoneNumber}>
                                            </label>
                                            <label>
                                                <span class="label">Location</span>
                                                <span class="text-v1">${updateSos.location}</span>
                                                <input type="hidden" name="location" value=${updateSos.location}>
                                            </label>
                                            <label>
                                                <span class="label">Status</span>
                                                <form:select id="statuses" name="status" path="status" type="text"
                                                             items="${statuses}" cssClass="change-on-submit"/>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label>
                                            <span class="label">Remarks</span>
                                            <form:textarea id="remarks" path="remarks" placeholder="Placeholder"/>
                                        </label>
                                    </div>
                                </div> <!-- form-container -->
                            </div> <!-- inner -->
                            <div class="button-row">
                                <input type="submit" class="button button-v4 color-3 fix-size-sml"
                                       value="Update" ${buttonType}>
                                <a href="${pageContext.request.contextPath}/sos"
                                   class="button button-v4 color-2 fix-size-sml">Cancel</a>
                            </div>

                        </section>
                        <!-- content-box -->
                    </form:form>

                    <section>
                        <div class="maps mt-20">
                            <div id="map" data-json-url="${pageContext.request.contextPath}/api/sosCall/details/map"></div>
                        </div>
                    </section>

                    <section class="dynamic-content">

                        <section class="site-data-container" data-template-url="assets/ajax/table/table-v2/table-v2.html" data-json-url="${pageContext.request.contextPath}/api/sosCall/details?sosId=${sosId}">
                            <div class="site-data-content"></div>
                            <div class="error-text"></div>
                            <div class="pagination site-data-pagination">
                                <ul>
                                    <li><a href="#" class="prev"></a></li>

                                    <li>
                                        <div class="form-row select select-not-find">
                                            <select name="page">
                                                <option value="1">1</option>
                                            </select>
                                        </div>
                                    </li>
                                    <li><a href="#" class="next"></a></li>
                                </ul>
                            </div><!-- /pagination-row -->
                        </section>

                    </section> <!-- dynamic-content -->

                </section> <!-- content-inner -->

            </section> <!-- content-wrapper -->

    </div> <!-- page-inner -->

</div> <!-- page-wrapper -->
<script src="${pageContext.request.contextPath}/assets/js/lib.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/core.js"></script>
<script src="${pageContext.request.contextPath}/https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDp5oJvfmqhGjGaKJePviTrPeB4f9QCrEc&callback=initMap"></script>

</body>
</html>
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

                    <jsp:include page="highlight.jsp">
                        <jsp:param name="header" value="Manage Criminals" />
                    </jsp:include>

                    <section>
                        <ul class="horizontal-list v1">
                            <li>
                                <a href="${pageContext.request.contextPath}/crimeReports" class="box-v2">
                                    <i class="icon-manage-cases"></i>
                                    <h3>Manage Crime Reports</h3>
                                    <p>In this Section, you can View and Manage Cases filled by your Unit.</p>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/criminalCases" class="box-v2">
                                    <i class="icon-manage-criminals"></i>
                                    <h3>Manage Criminal Cases</h3>
                                    <p>In this Section, you can View and Manage Criminals and Wanted Persons Profiles.</p>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/fileCriminalCase" class="box-v2">
                                    <i class="icon-file"></i>
                                    <h3>File Criminal Case to Prosecution Office</h3>
                                    <p>In this Section, you can File a New Case to Prosecution office.</p>
                                </a>
                            </li>
                        </ul>
                    </section>

                </section>



            </section> <!-- content-inner -->

        </section> <!-- content-wrapper -->

    </div> <!-- page-inner -->

</div> <!-- page-wrapper -->

<script src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDp5oJvfmqhGjGaKJePviTrPeB4f9QCrEc&callback=initMap"></script>

<script src="${pageContext.request.contextPath}/assets/js/lib.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/core.min.js"></script>


</body>
</html>
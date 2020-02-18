<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

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

    <style>
        #map {
            width: 100%;
            height: 400px;
            background-color: grey;
        }
    </style>

</head>
<body>

<div class="page-wrapper">

    <div class="page-inner">

        <section class="content-wrapper">

            <jsp:include page="big_main_menu_bar.jsp">
                <jsp:param name="home" value="active" />
            </jsp:include>

            <section class="content-inner">

                <section class="content">

                    <jsp:include page="highlight.jsp">
                        <jsp:param name="header" value="Dashboard" />
                    </jsp:include>

                    <section>
                        <div class="maps">
                            <div id="map"></div>
                        </div>
                    </section>

                    <section>
                        <ul class="horizontal-list v1">
                            <li>
                                <a href="${pageContext.request.contextPath}/missionPermitsMenu" class="box-v1">
                                    <h3>Permits</h3>
                                    <p>${missionPermitCount}</p>
                                    <span>Valid Permits</span>
                                    <i class="icon-mission-permits"></i>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/criminalsMenu" class="box-v1">
                                    <h3>Cases</h3>
                                    <p>${caseCount}</p>
                                    <span>Cases Added</span>
                                    <i class="icon-manage-cases"></i>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/notificationMenu" class="box-v1">
                                    <h3>Notifications<span class="notification">${notificationCount}</span></h3>
                                    <p>${notificationCount}</p>
                                    <span>Notifications</span>
                                    <i class="icon-notification"></i>
                                </a>
                            </li>
                            <li class="cols-2">
                                <a href="javascript:;" class="box-v1">
                                    <h3>SOS</h3>
                                    <p>82</p>
                                    <span>Log Sheet</span>
                                    <i class="icon-sos"></i>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/sos" class="box-v1">
                                    <h3>SOS<span class="notification">${sosNotificationCount}</span></h3>
                                    <p>${sosCount}</p>
                                    <span>Live Alerts</span>
                                    <i class="icon-sos"></i>
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

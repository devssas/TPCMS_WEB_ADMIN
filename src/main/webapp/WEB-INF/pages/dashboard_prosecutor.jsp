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

            <jsp:include page="menu_bar_prosecutor_dashboard.jsp">
                <jsp:param name="home" value="active" />
                <jsp:param name="prosecutorName" value="${prosecutorName}" />
                <jsp:param name="prosecutorProfilePicture" value="${prosecutorProfilePicture}" />
                <jsp:param name="accessRole" value="${accessRole}" />
            </jsp:include>

            <section class="content-inner">

                <section class="content">

                    <jsp:include page="highlight.jsp">
                        <jsp:param name="header" value="Prosecutor Dashboard" />
                    </jsp:include>

                    <section>
                        <ul class="horizontal-list v1">
                            <li>
                                <a href="${pageContext.request.contextPath}/missionPermitsMenu" class="box-v1">
                                    <h3>Request For Evidence</h3>
                                    <span>Cases Pending Review</span>
                                    <i class="icon-mission-permits"></i>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/criminalsMenu" class="box-v1">
                                    <h3>Submitted For Review</h3>
                                    <span>Cases Added</span>
                                    <i class="icon-manage-cases"></i>
                                </a>
                            </li>

                            <li>
                                <a href="${pageContext.request.contextPath}/notificationMenu" class="box-v1">
                                    <h3>Notifications<c:if test="${!notificationCount.equals('0')}"><span class="notification">${notificationCount}</span></c:if></h3>
                                    <p>${notificationCount}</p>
                                    <span>Notifications</span>
                                    <i class="icon-notification"></i>
                                </a>
                            </li>
                        </ul>
                    </section>

                </section>

            </section> <!-- content-inner -->

        </section> <!-- content-wrapper -->

    </div> <!-- page-inner -->

</div> <!-- page-wrapper -->

<script src="${pageContext.request.contextPath}/assets/js/lib.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/core.min.js"></script>

</body>
</html>

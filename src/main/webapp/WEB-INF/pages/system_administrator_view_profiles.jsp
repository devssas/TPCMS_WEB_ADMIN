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
    <link rel="apple-touch-icon" sizes="120x120"
          href="${pageContext.request.contextPath}/assets/images/head/120x120.png">
    <link rel="apple-touch-icon" sizes="152x152"
          href="${pageContext.request.contextPath}/assets/images/head/152x152.png">

</head>
<body>

<div class="page-wrapper">

    <div class="page-inner">

        <section class="content-wrapper">

            <jsp:include page="menu_bar_dashboard.jsp">
                <jsp:param name="administrator" value="active"/>
                <jsp:param name="officerName" value="${officerName}"/>
                <jsp:param name="officerProfilePicture" value="${officerProfilePicture}"/>
                <jsp:param name="accessRole" value="${accessRole}"/>
                <jsp:param name="dashboardPage" value="${dashboardPage}"/>
                <jsp:param name="prosecutorPage" value="${prosecutorPage}"/>
            </jsp:include>

            <section class="content-inner">

                <section class="content">

                    <jsp:include page="highlight.jsp">
                        <jsp:param name="header" value="Manage Supervisors"/>
                    </jsp:include>

                    <section class="content-box">
                        <section class="site-data-container" data-template-url="assets/ajax/table/table-v2/table-v2.html" data-json-url="/tpcmsWebAdmin/api/supervisor">
                            <div class="table-filter">
                                <form action="">
                                    <div class="form-container">
                                        <div class="search-box">
                                            <div class="form-row search-row">
                                                <label>
                                                    <input type="text" placeholder="Search">
                                                    <i class="icon-search"></i>
                                                </label>
                                            </div>
                                            <div class="form-row submit-row">
                                                <button type="submit"
                                                        class="button button-v3 button-icons fix-size-sml">
                                                    Search <i class="icon-search"></i></button>
                                            </div>
                                        </div>
                                        <div class="table-filter-inner">
                                            <div class="form-row select-not-find">
                                                <label>
                                                    <span class="label">Status</span>
                                                    <form:select id="statuses" name="status" path="statuses" type="text" items="${statuses}"/>
                                                </label>
                                            </div>
                                            <div class="form-row select-not-find">
                                                <label>
                                                    <span class="label">User Type</span>
                                                    <form:select id="userTypes" name="userTypes" path="userTypes" type="text" items="${userTypes}" />
                                                </label>
                                            </div>
                                            <a href="${pageContext.request.contextPath}/systemAdministratorMenu"
                                               class="button button-v3 fix-size-sml right-fix">Back to Overview</a>
                                        </div>
                                    </div>
                                </form>
                            </div>
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
                    </section>

                </section>

            </section> <!-- content-inner -->

        </section> <!-- content-wrapper -->

    </div> <!-- page-inner -->

</div> <!-- page-wrapper -->


<script src="${pageContext.request.contextPath}/assets/js/lib.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/core.min.js"></script>
<script src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDp5oJvfmqhGjGaKJePviTrPeB4f9QCrEc&callback=initMap"></script>

</body>
</html>

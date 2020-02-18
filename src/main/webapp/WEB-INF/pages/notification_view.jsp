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

            <jsp:include page="big_main_menu_bar.jsp">
                <jsp:param name="notification" value="active" />
            </jsp:include>

            <section class="content-inner">

                <section class="content">

                    <jsp:include page="highlight.jsp">
                        <jsp:param name="header" value="Manage Notifications" />
                    </jsp:include>

                    <section class="content-box">
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
                                            <button type="submit" class="button button-v3 button-icons fix-size-sml">Search <i class="icon-search"></i></button>
                                        </div>
                                    </div>
                                    <div class="table-filter-inner">
                                        <div class="form-row select-not-find">
                                            <label>
                                                <span class="label">User Type</span>
                                                <select name="" id="select-1">
                                                    <option value="">All/Supervisors/Officers</option>
                                                    <option value="">All/Supervisors/Officers</option>
                                                    <option value="">All/Supervisors/Officers</option>
                                                    <option value="">All/Supervisors/Officers</option>
                                                    <option value="">All/Supervisors/Officers</option>
                                                    <option value="">All/Supervisors/Officers</option>
                                                </select>
                                            </label>
                                        </div>
                                        <div class="form-row select-not-find">
                                            <label>
                                                <span class="label">Status</span>
                                                <select name="" id="select-2">
                                                    <option value="">All/Active/Inactive</option>
                                                    <option value="">All/Active/Inactive</option>
                                                    <option value="">All/Active/Inactive</option>
                                                    <option value="">All/Active/Inactive</option>
                                                    <option value="">All/Active/Inactive</option>
                                                    <option value="">All/Active/Inactive</option>
                                                </select>
                                            </label>
                                        </div>
                                        <a href="${pageContext.request.contextPath}/notificationMenu" class="button button-v3 fix-size-sml right-fix">Back to Overview</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <section class="dynamic-content" data-template-url="assets/ajax/table/table-v8/table-v8.html" data-json-url="/tpcmsWebAdmin/api/notification"></section>
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
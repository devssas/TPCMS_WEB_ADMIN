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
                <jsp:param name="notification" value="active" />
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
                        <jsp:param name="header" value="Create New Notification" />
                        <jsp:param name="redirect" value="notificationMenu" />
                        <jsp:param name="pageName" value="Notification Menu" />
                        <jsp:param name="httpError" value="${httpError}" />
                    </jsp:include>

                    <form:form id="new-notification-form" modelAttribute="newNotificationCreateModel" method="post" enctype="multipart/form-data" >
                        <section class="content-box">
                                <div>
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>NOTIFICATION DETAILS</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row single-photo-upload">
                                                    <div class="photo-upload">
                                                        <div class="photo-upload-inner" data-upload-url="${pageContext.request.contextPath}/api/notification/image" data-delete-url="${pageContext.request.contextPath}/api/notification/image/delete" data-max-files="1" ></div>
                                                        <i class="icon-plus"></i>
                                                        <span class="description">Upload Photo</span>
                                                    </div>
                                                </div>

                                                <div class="divided-content flout-2">
                                                    <div>
                                                        <div class="form-row select-not-find">
                                                            <label>
                                                                <span class="label">Notification Type *</span>
                                                                <form:select id="notificationType" path="notificationType" items="${notificationTypes}"/>
                                                            </label>
                                                        </div>

                                                        <div class="form-row select-not-find">
                                                            <label>
                                                                <span class="label">Nature Of Announcement *</span>
                                                                <form:select id="natureOfAnnouncement" path="natureOfAnnouncement" items="${natureOfAnnouncement}"/>
                                                            </label>
                                                        </div>
                                                    </div>

                                                    <div>
                                                        <div class="form-row">
                                                            <label>
                                                                <span class="label">Crime Type</span>
                                                                <select name="select1" class="crime-select" data-method="get" id="select1" data-url="${pageContext.request.contextPath}/api/reference/crime-types">
                                                                    <option value="">Placeholder</option>
                                                                </select>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Statement *</span>
                                                        <form:textarea id="statement" path="statement" placeholder="Example..."/>
                                                    </label>
                                                    <form:errors id="statement" path="statement" cssClass="text-danger" />
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                </div>
                                <div class="button-row">
                                    <input type="submit" id="button" class="button button-v4 color-1 fix-size-sml" value="Create">
                                    <a href="javascript:;" class="button button-v4 color-2 fix-size-sml">Cancel</a>
                                </div>
                            </section> <!-- content-box -->

                    </form:form>

                </section> <!-- content -->

            </section> <!-- content-inner -->

        </section> <!-- content-wrapper -->

    </div> <!-- page-inner -->

</div> <!-- page-wrapper -->

<script src="${pageContext.request.contextPath}/assets/js/lib.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/core.js"></script>

</body>
</html>
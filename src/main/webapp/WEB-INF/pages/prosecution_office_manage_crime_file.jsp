<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!doctype html>
<html>
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

            <jsp:include page="menu_bar_prosecutor_dashboard.jsp">
                <jsp:param name="home" value="active" />
                <jsp:param name="prosecutorName" value="${prosecutorName}" />
                <jsp:param name="prosecutorProfilePicture" value="${prosecutorProfilePicture}" />
                <jsp:param name="accessRole" value="${accessRole}" />
            </jsp:include>

            <section class="content-inner">

                <section class="content">

                    <jsp:include page="highlight_back_to_overview.jsp">
                        <jsp:param name="header" value="Manage Crime File" />
                        <jsp:param name="redirect" value="dashboardProsecutor" />
                        <jsp:param name="redirect" value="dashboardProsecutor" />
                    </jsp:include>

                    <form action="">

                        <section class="content-box">
                            <div class="divided-content flout-2">
                                <div>
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="form-container">
                                                <div class="form-row text-row">
                                                    <div class="input-group-content">
                                                        <label>
                                                            <span class="label">Case ID</span>
                                                            <span class="text-v1">${manageCrimeFileModel.caseId}</span>
                                                        </label>
                                                        <label>
                                                            <span class="label">Case Date</span>
                                                            <span class="text-v1">${manageCrimeFileModel.caseDate}</span>
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="form-row text-row">
                                                    <div class="input-group-content">
                                                        <label>
                                                            <span class="label">Officer Name</span>
                                                            <span class="text-v1">${manageCrimeFileModel.officerName}</span>
                                                        </label>
                                                        <label>
                                                            <span class="label">Crime Location</span>
                                                            <span class="text-v1">${manageCrimeFileModel.crimeLocation}</span>
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="form-row text-row">
                                                    <div class="input-group-content">
                                                        <label>
                                                            <span class="label">Status</span>
                                                            <span class="text-v1">${manageCrimeFileModel.status}</span>
                                                        </label>
                                                        <label>
                                                            <span class="label">Suspects</span>
                                                            <c:forEach items="${manageCrimeFileModel.suspects}"  var="suspect">
                                                                <ul>
                                                                    <li>${suspect}</li>
                                                                </ul>
                                                            </c:forEach>
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Officer Statement</span>
                                                        <textarea name="" id="" placeholder="Officer Statement">${manageCrimeFileModel.officerStatement}</textarea>
                                                    </label>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                </div>

                                <div>
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="form-container">

                                                <div class="form-row multiple-photo-upload">
                                                    <label>
                                                        <span class="label">Images</span>
                                                    </label>
                                                    <div class="card-carousel owl-carousel owl-theme">
                                                        <c:forEach items="${manageCrimeFileModel.images}"  var="image">
                                                            <div class="item">
                                                                <div class="box-v4">
                                                                    <img src="data:image/png;base64,${image}" alt="" data-fancybox="images">
                                                                </div>
                                                            </div>
                                                        </c:forEach>
                                                    </div>

                                                </div>

                                                <div class="form-row">
                                                    <div class="input-group-content">
                                                        <label>
                                                            <span class="label">Crime Type</span>
                                                            <input type="text" value="${manageCrimeFileModel.crimeType}"></label>
                                                        </label>
                                                        <label>
                                                            <span class="label">Crime Title</span>
                                                            <input type="text" value="${manageCrimeFileModel.crimeTitle}">
                                                        </label>
                                                    </div>
                                                </div>

                                                <div class="form-row">
                                                    <div class="input-group-content">
                                                        <label>
                                                            <span class="label">Crime Classification</span>
                                                            <input type="text" value="${manageCrimeFileModel.crimeClassification}" >
                                                        </label>
                                                    </div>
                                                </div>

                                                <div class="form-row">
                                                    <span class="label"></span>
                                                    <div class="input-group-content">
                                                        <label>
                                                            <span class="label">Witness First Name</span>
                                                            <input type="text" value="${manageCrimeFileModel.witnessStatementFirstName}">
                                                        </label>
                                                        <label>
                                                            <span class="label">Witness Last Name</span>
                                                            <input type="text" value="${manageCrimeFileModel.witnessStatementLastName}">
                                                        </label>
                                                    </div>
                                                    <div class="form-row">
                                                        <label>
                                                            <span class="label">Statement</span>
                                                            <textarea name="" id="2">${manageCrimeFileModel.witnessStatement}</textarea>
                                                        </label>
                                                    </div>
                                                </div>

                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                </div>
                            </div> <!-- divided-content flout-2 -->
                            <div class="button-row">
                                <input type="submit" class="button button-v4 color-3 fix-size-sml" value="Add Update">
                                <a href="javascript:;" class="button button-v4 color-2 fix-size-sml">Cancel</a>
                            </div>

                            <!--     <section class="dynamic-content" data-template-url="assets/ajax/table/table-v11/table-v11.html" data-json-url="assets/ajax/table/table-v11/table-v11.json"></section> -->

                        </section> <!-- content-box -->
                    </form>

                </section> <!-- content -->

            </section> <!-- content-inner -->

        </section> <!-- content-wrapper -->

    </div> <!-- page-inner -->

</div> <!-- page-wrapper -->
<script src="assets/js/lib.min.js"></script>
<script src="assets/js/core.min.js"></script>
<script src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDp5oJvfmqhGjGaKJePviTrPeB4f9QCrEc&callback=initMap"></script>

</body>
</html>
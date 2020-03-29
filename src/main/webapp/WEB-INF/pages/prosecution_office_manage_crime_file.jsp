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
                <jsp:param name="prosecutionOffice" value="active" />
                <jsp:param name="prosecutorName" value="${prosecutorName}" />
                <jsp:param name="prosecutorProfilePicture" value="${prosecutorProfilePicture}" />
                <jsp:param name="accessRole" value="${accessRole}" />
            </jsp:include>

            <section class="content-inner">

                <section class="content">

                    <jsp:include page="highlight_back_to_overview.jsp">
                        <jsp:param name="header" value="Manage Crime File" />
                        <jsp:param name="redirect" value="prosecutionRequestEvidence" />
                        <jsp:param name="pageName" value="Prosecution Request Evidence" />
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
                                                            <input type="text" value="${manageCrimeFileModel.caseId}" readonly />
                                                        </label>
                                                        <label>
                                                            <span class="label">Case Date</span>
                                                            <input type="text" value="${manageCrimeFileModel.caseDate}" readonly />
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="form-row text-row">
                                                    <div class="input-group-content">
                                                        <label>
                                                            <span class="label">Officer Name</span>
                                                            <input type="text" value="${manageCrimeFileModel.officerName}" readonly />
                                                        </label>
                                                        <label>
                                                            <span class="label">Crime Location</span>
                                                            <input type="text" value="${manageCrimeFileModel.crimeLocation}" readonly>
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="form-row text-row">
                                                    <div class="input-group-content">
                                                        <label>
                                                            <span class="label">Status</span>
                                                            <input type="text" value="${manageCrimeFileModel.status}" readonly>
                                                        </label>
                                                        <label>
                                                            <span class="label">Suspects</span>
                                                            <c:forEach items="${manageCrimeFileModel.suspects}"  var="suspect">
                                                                <ul>
                                                                    <li><input type="text" value="${suspect}" readonly></li>
                                                                </ul>
                                                            </c:forEach>
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Officer Statement</span>
                                                        <textarea name="officerStatement" id="officerStatement" placeholder="Officer Statement" readonly>${manageCrimeFileModel.officerStatement}</textarea>
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
                                                            <input type="text" value="${manageCrimeFileModel.crimeType}" readonly></label>
                                                        </label>
                                                        <label>
                                                            <span class="label">Crime Title</span>
                                                            <input type="text" value="${manageCrimeFileModel.crimeTitle}" readonly>
                                                        </label>
                                                    </div>
                                                </div>

                                                <div class="form-row">
                                                    <div class="input-group-content">
                                                        <label>
                                                            <span class="label">Crime Classification</span>
                                                            <input type="text" value="${manageCrimeFileModel.crimeClassification}" readonly>
                                                        </label>
                                                    </div>
                                                </div>

                                                <div class="form-row">
                                                    <span class="label"></span>
                                                    <div class="input-group-content">
                                                        <label>
                                                            <span class="label">Witness First Name</span>
                                                            <input type="text" value="${manageCrimeFileModel.witnessStatementFirstName}" readonly>
                                                        </label>
                                                        <label>
                                                            <span class="label">Witness Last Name</span>
                                                            <input type="text" value="${manageCrimeFileModel.witnessStatementLastName}" readonly>
                                                        </label>
                                                    </div>
                                                    <div class="form-row">
                                                        <label>
                                                            <span class="label">Statement</span>
                                                            <textarea name="witnessStatement" id="witnessStatement" readonly>${manageCrimeFileModel.witnessStatement}</textarea>
                                                        </label>
                                                    </div>
                                                </div>

                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                </div>
                            </div> <!-- divided-content flout-2 -->
                            <div class="button-row">
                                    <a href="javascript:;" data-fancybox-card data-type="ajax" class="button button-v4 color-3" data-src="assets/ajax/card/add-case-update.html">Add Update</a>
                                    <a href="${pageContext.request.contextPath}/prosecutionRequestEvidence" class="button button-v4 color-2 fix-size-sml">Cancel</a>
                                </div>

                                <section class="dynamic-content">

                                    <section class="site-data-container" data-template-url="assets/ajax/table/table-v2/table-v2.html" data-json-url="assets/ajax/table/table-v2/table-v2.json">
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
                        </section> <!-- content-box -->
                    </form>

                </section> <!-- content -->

            </section> <!-- content-inner -->

        </section> <!-- content-wrapper -->

    </div> <!-- page-inner -->

</div> <!-- page-wrapper -->
<script src="${pageContext.request.contextPath}/assets/js/lib.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/core.min.js"></script>

</body>
</html>
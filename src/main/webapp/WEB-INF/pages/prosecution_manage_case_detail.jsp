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
    <link rel="apple-touch-icon" sizes="120x120"
          href="${pageContext.request.contextPath}/assets/images/head/120x120.png">
    <link rel="apple-touch-icon" sizes="152x152"
          href="${pageContext.request.contextPath}/assets/images/head/152x152.png">

</head>
<body>

<div class="page-wrapper">

    <div class="page-inner">

        <section class="content-wrapper">

            <jsp:include page="menu_bar_prosecutor_dashboard.jsp">
                <jsp:param name="prosecutionOffice" value="active"/>
                <jsp:param name="prosecutorName" value="${prosecutorName}"/>
                <jsp:param name="prosecutorProfilePicture" value="${prosecutorProfilePicture}"/>
                <jsp:param name="accessRole" value="${accessRole}"/>
            </jsp:include>

            <section class="content-inner">

                <section class="content">

                    <jsp:include page="highlight_back_to_overview.jsp">
                        <jsp:param name="header" value="Manage Case"/>
                        <jsp:param name="redirect" value="prosecutionManageCases"/>
                        <jsp:param name="pageName" value="Manage Cases"/>
                        <jsp:param name="httpError" value="${httpError}" />
                    </jsp:include>

                    <form:form id="updateReport" modelAttribute="updateReport" method="post">
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
                                                        <form:input id="caseId" path="caseId" readonly="true"/>
                                                    </label>
                                                    <label>
                                                        <span class="label">Case Date</span>
                                                        <form:input id="caseDate" path="caseDate" readonly="true"/>
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="form-row text-row">
                                                <div class="input-group-content">
                                                    <label>
                                                        <span class="label">Officer Name</span>
                                                        <form:input id="officerName" path="officerName" readonly="true"/>
                                                    </label>
                                                    <label>
                                                        <span class="label">Crime Location</span>
                                                        <form:input id="crimeLocation" path="crimeLocation" readonly="true"/>
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="form-row text-row">
                                                <div class="input-group-content">
                                                    <label>
                                                        <span class="label">Status</span>
                                                        <form:input id="status" path="status" readonly="true"/>
                                                    </label>
                                                    <label>
                                                        <span class="label">Suspects</span>
                                                        <c:forEach items="${manageCrimeFileModel.suspects}" var="suspect">
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
                                                    <form:textarea id="officerStatement" path="officerStatement" readonly="true"/>
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
                                                <div class="photo-upload upload-view">
                                                    <div class="photo-upload-inner" data-upload-url="/file/post" data-delete-url="/file/post" data-max-files="5" data-upload-ajax-url="/assets/ajax/upload-file/upload-file-multiple.json"></div>
                                                    <div class="custom-upload">
                                                        <i class="icon-plus"></i>
                                                        <span class="description">Upload Photo</span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-row">
                                                <div class="input-group-content">
                                                    <label>
                                                        <span class="label">Crime Type</span>
                                                        <form:input id="crimeType" path="crimeType" readonly="true"/>
                                                    </label>
                                                    <label>
                                                        <span class="label">Crime Title</span>
                                                        <form:input id="crimeTitle" path="crimeTitle" readonly="true"/>
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="form-row">
                                                <div class="input-group-content">
                                                    <label>
                                                        <span class="label">Crime Classification</span>
                                                        <form:input id="crimeClassification" path="crimeClassification" readonly="true"/>
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="form-row">
                                                <span class="label"></span>
                                                <div class="input-group-content">
                                                    <label>
                                                        <span class="label">Witness First Name</span>
                                                        <form:input id="witnessStatementFirstName" path="witnessStatementFirstName" readonly="true"/>
                                                    </label>
                                                    <label>
                                                        <span class="label">Witness Last Name</span>
                                                        <form:input id="witnessStatementLastName" path="witnessStatementLastName" readonly="true"/>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Statement</span>
                                                        <form:textarea id="witnessStatement" path="witnessStatement" readonly="true"/>
                                                    </label>
                                                </div>
                                            </div>

                                        </div> <!-- form-container -->
                                    </div> <!-- inner -->
                                </div> <!-- box-v3 -->
                            </div>
                        </div> <!-- divided-content flout-2 -->

                        <div class="button-row">
                            <input type="submit" name="requestForEvidence" class="button button-v4 color-1" value="Request More Evidence">
                            <input type="submit" name="approveButton" class="button button-v4 color-3 fix-size-sml" value="Approve">
                            <input type="submit" name="rejectButton" class="button button-v4 color-4 fix-size-sml" value="Reject">
                            <a href="${pageContext.request.contextPath}/prosecutionManageCases" class="button button-v4 color-2 fix-size-sml">Cancel</a>
                        </div>


                    </section> <!-- content-box -->
                    </form:form>

                    <section class="dynamic-content">

                        <section class="site-data-container" data-template-url="assets/ajax/table/table-v11/table-v11.html" data-json-url="assets/ajax/table/table-v11/table-v11.json">
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



                </section> <!-- content -->

            </section> <!-- content-inner -->

        </section> <!-- content-wrapper -->

    </div> <!-- page-inner -->

</div> <!-- page-wrapper -->
<script src="${pageContext.request.contextPath}/assets/js/lib.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/core.min.js"></script>

</body>
</html>
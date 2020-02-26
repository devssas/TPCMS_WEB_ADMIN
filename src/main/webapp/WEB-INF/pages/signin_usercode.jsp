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
</head>
<body class="login-page">

<div class="page-wrapper">

    <div class="page-inner">

        <header>
            <div class="container">
                <div class="inner">
                    <figure>
                        <img src="assets/images/text/header-title-text-1.png" alt="">
                    </figure>
                    <figure>
                        <img src="assets/images/text/header-title-text-2.png" alt="">
                    </figure>
                    <figure>
                        <img src="assets/images/logo/logo.png" alt="">
                    </figure>
                </div> <!-- inner -->
            </div> <!-- container -->
        </header>

        <section class="content-wrapper">

            <section class="content-inner">

                <section class="login-verify">
                    <div class="container">
                        <div class="inner">
                            <form:form id="signInUserCode" modelAttribute="signInUserCodeModel" method="post">
                                <div class="form-container login-form-contaier">
                                    <div class="form-row">
                                        <label>
                                            <span class="label">Usercode *</span>
                                            <div class="form-group">
                                                <form:input id="signInUserCode1" cssClass="signInUserCode1 next-input-field" path="userCode1" maxlength="1"/>
                                                <form:input id="signInUserCode2" cssClass="signInUserCode2 next-input-field" path="userCode2" maxlength="1"/>
                                                <form:input id="signInUserCode3" cssClass="signInUserCode3 next-input-field" path="userCode3" maxlength="1"/>
                                                <form:input id="signInUserCode4" cssClass="signInUserCode4 next-input-field" path="userCode4" maxlength="1"/>
                                                <form:input id="signInUserCode5" cssClass="signInUserCode5 next-input-field" path="userCode5" maxlength="1"/>
                                            </div>
                                            <form:errors id="userCodeFull" path="userCodeFull" cssClass="text-danger" />
                                            <form:errors id="userCode1" path="userCode1" cssClass="text-danger" />
                                            <form:errors id="userCode2" path="userCode2" cssClass="text-danger" />
                                            <form:errors id="userCode3" path="userCode3" cssClass="text-danger" />
                                            <form:errors id="userCode4" path="userCode4" cssClass="text-danger" />
                                            <form:errors id="userCode5" path="userCode5" cssClass="text-danger" />
                                        </label>
                                    </div>
                                    <div class="form-row button-row">
                                        <input type="submit" class="button button-v1 full-width" value="VERIFY">
                                    </div>
                                </div> <!-- form-container login-form-contaier -->
                            </form:form>
                        </div> <!-- inner -->
                    </div> <!-- container -->
                </section> <!-- login-verify -->

            </section> <!-- content-inner -->

        </section> <!-- content-wrapper -->

    </div> <!-- page-inner -->

</div> <!-- page-wrapper -->

<script src="${pageContext.request.contextPath}/assets/js/lib.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/core.min.js"></script>
</body>
</html>
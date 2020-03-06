<input type="hidden" name="httpError" value="${param.httpError}">
<section class="highlight">
    <h2>${param.header}</h2>
    <a href="${pageContext.request.contextPath}/${param.redirect}" class="button button-v2 button-icons">Back to ${param.pageName}<i class="icon-back"></i></a>
    <a href="${pageContext.request.contextPath}/signInUsername" class="button button-v2 button-icons sign-out">Sign out<i class="icon-logout"></i></a>
</section>

<section class="big-menu">
    <div class="inner">
        <div class="menu-top">
            <figure class="logo">
                <img src="${pageContext.request.contextPath}/assets/images/logo/logo.png" alt="">
            </figure>
            <figure class="figure-text-1">
                <img src="${pageContext.request.contextPath}/assets/images/text/header-title-text-2.png" alt="">
            </figure>
        </div>
        <div class="menu-center">
            <div class="inner">
                <div class="profile-description">
                    <figure>
                        <img src="data:image/png;base64,${param.prosecutorProfilePicture}" alt="" width="20" height="35">
                    </figure>
                    <div class="text">
                        <h3>${param.prosecutorName}</h3>
                        <h4>${param.accessRole}</h4>
                    </div>
                </div>
                <nav class="menu">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/dashboardProsecutor" class="${param.home}"><i class="icon-dashboard"></i>Dashboard</a></li>
                        <li><a href="${pageContext.request.contextPath}/prosecutionOfficeMenu" class="${param.prosecutionOffice}"><i class="icon-prosecution-office"></i>Prosecution Office</a></li>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="menu-bottom">
            <span class="description">Tripoli POLICE<br>Web Admin<br>v.1.0</span>
        </div>
    </div>

</section>
<section class="big-menu">
    <div class="inner">
        <div class="menu-top">
            <figure class="logo">
                <img src="assets/images/logo/logo.png" alt="">
            </figure>
            <figure class="figure-text-1">
                <img src="assets/images/text/header-title-text-2.png" alt="">
            </figure>
        </div>
        <div class="menu-center">
            <div class="inner">
                <div class="profile-description">
                    <figure>
                        <img src="data:image/png;base64,${param.officerProfilePicture}" alt="" width="20" height="35">
                    </figure>
                    <div class="text">
                        <h3>${param.officerName}</h3>
                        <h4>${param.accessRole}</h4>
                    </div>
                </div>
                <nav class="menu">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/dashboard" class="${param.home}"><i class="icon-dashboard"></i>Dashboard</a></li>
                        <li><a href="${pageContext.request.contextPath}/criminalsMenu" class="${param.criminalDatabase}"><i class="icon-criminal-database"></i>Prosecution Office</a></li>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="menu-bottom">
            <span class="description">Tripoli POLICE<br>Web Admin<br>v.1.0</span>
        </div>
    </div>

</section>
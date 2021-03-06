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
                        <li><a href="${pageContext.request.contextPath}/${param.dashboardPage}" class="${param.home}"><i class="icon-dashboard"></i>Dashboard</a></li>
                        <li><a href="${pageContext.request.contextPath}/missionPermitsMenu" class="${param.missionPermits}"><i class="icon-mission-permits"></i>Mission Permits</a></li>
                        <li><a href="${pageContext.request.contextPath}/criminalsMenu" class="${param.criminalDatabase}"><i class="icon-criminal-database"></i>Criminal Database</a></li>
                        <li style="${param.disabled}"><a href="${pageContext.request.contextPath}/${param.prosecutorPage}" class="${param.prosecutionOffice}"><i class="icon-prosecution-office"></i>Prosecution Office</a></li>
                        <li><a href="${pageContext.request.contextPath}/policeStaffMenu" class="${param.staff}"><i class="icon-police-staff"></i>Police Staff</a></li>
                        <li><a href="${pageContext.request.contextPath}/policeVehiclesMenu" class="${param.policeVehicles}"><i class="icon-police_vehicles"></i>Police Vehicles</a></li>
                        <li><a href="${pageContext.request.contextPath}/notificationMenu" class="${param.notification}" ><i class="icon-notification"></i>Notifications</a></li>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="menu-bottom">
            <nav class="menu">
                <ul>
                    <li style="${param.disabled}"><a href="${pageContext.request.contextPath}/systemAdministratorMenu" class="${param.administrator}"><i class="icon-system"></i>System Administrator</a></li>
                </ul>
            </nav>
            <span class="description">Tripoli POLICE<br>Web Admin<br>v.1.0</span>
        </div>
    </div>

</section>
<%@ page pageEncoding="UTF-8" %>

<section class="fancy-card-box-v1">
    <div class="fancy-top-content">
        <div class="fancy-card-title">
            <h3>Vehicle Card</h3>
            <ul>
                <li><a href="javascript:;"><i class="icon-share-1"></i></a></li>
                <li><a href="javascript:;"><i class="icon-print"></i></a></li>
                <li><a href="javascript:;"><i class="icon-cancel" data-fancybox-close></i></a></li>
            </ul>
        </div>
        <div class="figure">
            <figure>
                <img src="data:image/png;base64,${image}" alt="">
                <span class="icons"><i class="icon-vehicle-card"></i></span>
            </figure>
        </div>
    </div>
    <div class="content">
        <div class="divided-content flout-2 custom-size-v1">
            <div>
                <div class="form-row">
                    <label>
                        <span class="label">Vehicle Name</span>
                        <span class="text big-size">${vehicleName}</span>
                    </label>
                </div>
            </div>
            <div>
                <div class="form-row">
                    <label>
                        <span class="label">Command Center</span>
                        <span class="text big-size">${commandCenter}</span>
                    </label>
                </div>
            </div>
        </div>
        <div class="divided-content flout-4">
            <div>
                <div class="form-row">
                    <label>
                        <span class="label">ID</span>
                        <span class="text">${vehicleId}</span>
                    </label>
                </div>
            </div>
            <div>
                <div class="form-row">
                    <label>
                        <span class="label">Unit</span>
                        <span class="text">${unit}</span>
                    </label>
                </div>
            </div>
            <div>
                <div class="form-row">
                    <label>
                        <span class="label">Plate Number</span>
                        <span class="text"><span class="nowrap">${plateNumber}</span></span>
                    </label>
                </div>
            </div>
            <div>
                <div class="form-row">
                    <label>
                        <span class="label">Expiry Date</span>
                        <span class="text">${expiryDate}</span>
                    </label>
                </div>
            </div>
        </div>
        <div class="divided-content flout-2">
            <div>
                <div class="form-row">
                    <label>
                        <span class="label">Permission to Carry Weapon</span>
                        <span class="text">${hasPermissionToCarryWeapon}</span>
                    </label>
                </div>
            </div>
            <div>
                <div class="form-row">
                    <label>
                        <span class="label">Weapon Type</span>
                        <span class="text">${weaponType}</span>
                    </label>
                </div>
            </div>
        </div>
        <div class="divided-content flout-2">
            <div>
                <div class="form-row">
                    <label>
                        <span class="label">Weapon SRL</span>
                        <span class="text">${weaponSrl}</span>
                    </label>
                </div>
                <div class="qr">
                    <figure>
                        <img src="${pageContext.request.contextPath}/assets/images/layout/qr.png" alt="">
                    </figure>
                </div>
            </div>
            <div>
                <div class="form-row">
                    <label>
                        <span class="label">Mission Details</span>
                        <span class="text"><c:if test="${!hasPermissionToCarryCivilians.equals('Y')}"><span class="notification">Permission, Carry Civilians</span></c:if></span>
                        <span class="text"><c:if test="${!hasPermissionToCarryPrisoner.equals('Y')}"><span class="notification">Permission, Carry Prisoners</span></c:if></span>
                        <span class="text"><c:if test="${!hasPermissionToNightPatrol.equals('Y')}"><span class="notification">Permission, Night Patrol</span></c:if></span>
                        <span class="text"><c:if test="${!hasPermissionToDriveOutsideCity.equals('Y')}"><span class="notification">Permission, Use Outside City</span></c:if></span>
                    </label>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ page pageEncoding="UTF-8" %>
<section class="fancy-card-box-v1">
    <div class="fancy-top-content">
        <div class="fancy-card-title">
            <h3>Mission Card</h3>
            <ul>
                <li><a href="javascript:;"><i class="icon-share-1"></i></a></li>
                <li><a href="javascript:;" onclick="window.print();return false;"><i class="icon-print"></i></a></li>
                <li><a href="javascript:;"><i class="icon-cancel" data-fancybox-close></i></a></li>
            </ul>
        </div>
        <figure>
            <img src="data:image/png;base64,${image}" alt="">
            <span class="icons"><i class="icon-mission-card"></i></span>
        </figure>
    </div>
    <div class="content">
        <div class="divided-content flout-2 custom-size-v1">
            <div>
                <div class="form-row">
                    <label>
                        <span class="label">Officer Name:</span>
                        <span class="text big-size">${officerName}</span>
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
                        <span class="label">Rank</span>
                        <span class="text">${rank}</span>
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
                        <span class="label">Officer ID</span>
                        <span class="text">${officerId}</span>
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
                        <span class="label">Permission to Carry Weapon During Mission</span>
                        <span class="text">${isPermittedCarryWeapon}</span>
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
                        <span class="label">Mission Type</span>
                        <span class="text">${missionType}</span>
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
                        <span class="label">Mission Description</span>
                        <span class="text">${missionDescription}</span>
                    </label>
                </div>
            </div>
        </div>
    </div>
</section>

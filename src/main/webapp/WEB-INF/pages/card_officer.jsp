<%@ page pageEncoding="UTF-8" %>

<section class="fancy-card-box-v1">
    <div class="fancy-top-content">
        <div class="fancy-card-title">
            <h3>Officer Card</h3>
            <ul>
                <li><a href="javascript:;"><i class="icon-share-1"></i></a></li>
                <li><a href="javascript:;"><i class="icon-print"></i></a></li>
                <li><a href="javascript:;"><i class="icon-cancel" data-fancybox-close></i></a></li>
            </ul>
        </div>
        <figure>
            <img src="data:image/png;base64,${image}" alt="">
            <span class="icons"><i class="icon-officer-card"></i></span>
        </figure>

        <div class="form-content">
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
        </div>
    </div>
    <div class="content">
        <div class="divided-content flout-2">
            <div>
                <div class="form-row">
                    <label>
                        <span class="label">Permission to Carry Weapon</span>
                        <span class="text">${isCarryWeapon}</span>
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
            </div>
            <div>
                <div class="form-row">
                    <label>
                        <span class="label">Blood Group</span>
                        <span class="text">${bloodGroup}</span>
                    </label>
                </div>
            </div>
        </div>
        <div class="qr">
            <figure>
                <img src="${pageContext.request.contextPath}/assets/images/layout/qr.png" alt="">
            </figure>
        </div>
    </div>
</section>
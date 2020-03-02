<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section class="fancy-card-box-v5 width-sml">
    <div class="fancy-top-content">
        <div class="fancy-card-title">
            <div class="icon-box">
                <img src="data:image/png;base64,${crimeTypeLogo1}" alt="" />
            </div>
            <h4>${natureOfAnnouncement}</h4>
            <div class="date">${notificationDate}</div>
        </div>
    </div>
    <div class="content">
        <div class="form-content">
            <div class="form-row">
                <label>
                    <span class="text">
                        ${announcementDesc}
                    </span>
                </label>
            </div>
            <div class="x-row">
                <a href="javascript:;" class="button color-2" data-fancybox-close>X</a>
            </div>
        </div>
    </div>
</section>

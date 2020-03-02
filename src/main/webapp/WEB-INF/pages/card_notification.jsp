<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section class="fancy-card-box-v5 width-sml">
    <div class="fancy-top-content">
        <div class="fancy-card-title">
            <div class="icon-box">
                <i class="icon-notification"></i>
            </div>
            <h4></h4>
            <h5>${natureOfAnnouncement}</h5>
            <span class="date">${notificationDate}</span>
            <h1>${announcementId}</h1>
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

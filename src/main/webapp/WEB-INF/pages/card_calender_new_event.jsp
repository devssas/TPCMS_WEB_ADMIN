<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

<section class="fancy-card-box-v4">
    <div class="content">
        <form action="">
            <div class="form-container">
                <div class="form-row">
                    <label>
                        <input type="text" placeholder="Enter Title">
                    </label>
                </div>
                <div class="divided-content flout-2 custom-size-v3">
                    <div>
                        <div class="form-row">
                            <label>
                                <input type="text" placeholder="Name of person">
                            </label>
                        </div>
                    </div>
                    <div>
                        <div class="form-row">
                            <label>
                                <input type="text" class="timepicker" placeholder="11:00 AM">
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <label>
                        <span class="text big-size">06/02/2020, Thursday</span>
                    </label>
                </div>
                <div class="form-row">
                    <label>
                        <textarea name="" id="" cols="30" rows="10" placeholder=""></textarea>
                    </label>
                </div>
                <div class="form-row multiple-photo-upload">
                    <div class="photo-upload">
                        <div class="photo-upload-inner" data-upload-url="/file/post" data-delete-url="/file/post" data-max-files="5" ></div>
                        <div class="custom-upload">
                            <i class="icon-plus"></i>
                            <span class="description">Upload Photo</span>
                        </div>
                    </div>
                </div>
                <div class="form-row button-row">
                    <a href="javascript:;" class="button button-v1 color-3 fix-size-sml" data-fancybox-close>Close</a>
                    <input type="submit" class="button button-v1 color-1 fix-size-sml" value="Create">
                </div>
            </div>
        </form>
    </div>
</section>
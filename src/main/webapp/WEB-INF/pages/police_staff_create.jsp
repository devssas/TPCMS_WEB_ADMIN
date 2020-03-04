<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!doctype html>
<html lang="ar">
<head>
    <title>TPcms</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="TPcms">

    <meta property="og:title" content="TPcms">
    <meta property="og:description" content="TPcms">
    <meta property="og:url" content="">
    <meta property="og:image" content="assets/images/head/og-image.jpg">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/core.css">

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/head/favicon.png">
    <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/assets/images/head/60x60.png">
    <link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/assets/images/head/76x76.png">
    <link rel="apple-touch-icon" sizes="120x120" href="${pageContext.request.contextPath}/assets/images/head/120x120.png">
    <link rel="apple-touch-icon" sizes="152x152" href="${pageContext.request.contextPath}/assets/images/head/152x152.png">

</head>
<body>

<div class="page-wrapper">

    <div class="page-inner">

        <section class="content-wrapper">

            <jsp:include page="menu_bar_dashboard.jsp">
                <jsp:param name="staff" value="active" />
                <jsp:param name="officerName" value="${officerName}" />
                <jsp:param name="officerProfilePicture" value="${officerProfilePicture}" />
                <jsp:param name="accessRole" value="${accessRole}" />
                <jsp:param name="disabled" value="${disabled}" />
            </jsp:include>

            <section class="content-inner">

                <section class="content">

                    <jsp:include page="highlight_back_to_overview.jsp">
                        <jsp:param name="header" value="Add New Officer" />
                        <jsp:param name="redirect" value="policeStaffMenu" />
                        <jsp:param name="pageName" value="Police Staff Menu" />
                    </jsp:include>

                    <form action="">

                        <section class="content-box">
                            <div class="divided-content flout-2">
                                <div>
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>IDENTITIY</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row single-photo-upload">
                                                    <div class="photo-upload upload-view">
                                                        <div class="photo-upload-inner" data-upload-url="/file/post" data-delete-url="/file/post" data-max-files="1" data-upload-ajax-url="/assets/ajax/upload-file/upload-file-single.json"></div>
                                                        <i class="icon-admin-profile"></i>
                                                        <span class="description">Upload Photo</span>
                                                    </div>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">National ID *</span>
                                                        <input type="text" placeholder="Placeholder" disabled>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">First Name *</span>
                                                        <input type="text" placeholder="Placeholder" disabled>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Middle Name</span>
                                                        <input type="text" placeholder="Placeholder" disabled>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Last Name *</span>
                                                        <input type="text" placeholder="Placeholder" disabled>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">First Name (Arabic) *</span>
                                                        <input type="text" placeholder="Placeholder" disabled>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Middle Name (Arabic)</span>
                                                        <input type="text" placeholder="Placeholder" disabled>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Last Name (Arabic) *</span>
                                                        <input type="text" placeholder="Placeholder" disabled>
                                                    </label>
                                                </div>
                                                <div class="form-row side-by-side-v1-row">
                                                    <span class="label">Date of Birth</span>
                                                    <label>
                                                        <span class="icons-content">
                                                            <input type="text" class="datepicker" placeholder="Select Date" disabled>
                                                            <i class="icon-calender"></i>
                                                        </span>
                                                    </label>
                                                </div>
                                                <div class="form-row radio-btn-v1-row">
                                                    <span class="label">Gender</span>
                                                    <div class="radio-btn-group">
                                                        <div>
                                                            <label>
                                                                <span class="text">Male</span>
                                                                <input name="gender" type="radio" value="Male" disabled>
                                                                <span class="checkmark"></span>
                                                            </label>
                                                        </div>
                                                        <div>
                                                            <label>
                                                                <span class="text">Female</span>
                                                                <input name="gender" type="radio" value="Female" disabled>
                                                                <span class="checkmark"></span>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Passport Number</span>
                                                        <input type="text" placeholder="Placeholder" disabled>
                                                    </label>
                                                </div>
                                                <div class="form-row phone-number-row">
                                                    <label>
                                                        <span class="label">Mobile Number *</span>
                                                        <span class="phone-number-content">
                                                            <input type="text" placeholder="+218" disabled>
                                                            <input type="text" placeholder="1234567890" disabled>
                                                        </span>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Email</span>
                                                        <input type="text" placeholder="Placeholder" disabled>
                                                    </label>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>WEAPONARY DETAILS</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row radio-btn-v1-row">
                                                    <span class="label">Permission to Carry</span>
                                                    <div class="radio-btn-group">
                                                        <div>
                                                            <label>
                                                                <span class="text">Yes</span>
                                                                <input name="permissionToCarry" type="radio" value="Yes" disabled>
                                                                <span class="checkmark"></span>
                                                            </label>
                                                        </div>
                                                        <div>
                                                            <label>
                                                                <span class="text">No</span>
                                                                <input name="permissionToCarry" type="radio" value="No" disabled>
                                                                <span class="checkmark"></span>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-row select-not-find">
                                                    <label>
                                                        <span class="label">Type</span>
                                                        <select name="" id="" disabled>
                                                            <option value="">Choose</option>
                                                        </select>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">SRL Number</span>
                                                        <input type="text" placeholder="Type.." disabled>
                                                    </label>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>STATUS *</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row select-not-find">
                                                    <label>
                                                        <span class="label">Type</span>
                                                        <select name="" id="" disabled>
                                                            <option value="">Active/inactive</option>
                                                        </select>
                                                    </label>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                </div>
                                <div>
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>LOCATION</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Contact Address*</span>
                                                        <input type="text" placeholder="Placeholder" disabled>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">City *</span>
                                                        <input type="text" placeholder="Placeholder" disabled>
                                                    </label>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>PROFILE CREDENTIALS</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row">
                                                    <a href="javascript:;" class="button button-v4 color-2">Reset Password</a>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>PROFILE DETAILS</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Reporting Officer *</span>
                                                        <input type="text" placeholder="Placeholder" disabled>
                                                    </label>
                                                </div>
                                                <div class="form-row select-not-find">
                                                    <label>
                                                        <span class="label">Officer Grade</span>
                                                        <select name="" id="" disabled>
                                                            <option value="">Grade 1/2/3 etc</option>
                                                        </select>
                                                    </label>
                                                </div>
                                                <div class="form-row select-not-find">
                                                    <label>
                                                        <span class="label">Officer Rank</span>
                                                        <select name="" id="" disabled>
                                                            <option value="">Lautinent, Carnel, etc</option>
                                                        </select>
                                                    </label>
                                                </div>
                                                <div class="form-row select-not-find">
                                                    <label>
                                                        <span class="label">Reporting Unit *</span>
                                                        <select name="" id="" disabled>
                                                            <option value="">105, 106 etc</option>
                                                        </select>
                                                    </label>
                                                </div>
                                                <div class="form-row select-not-find">
                                                    <label>
                                                        <span class="label">Access Role *</span>
                                                        <select name="" id="" disabled>
                                                            <option value="">Super-admin, admin, Officer (Mobile), etc</option>
                                                        </select>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Officer ID</span>
                                                        <input type="text" placeholder="Placeholder" disabled>
                                                    </label>
                                                </div>
                                                <div class="form-row side-by-side-v1-row">
                                                    <span class="label">Employment Start Date</span>
                                                    <label>
                                                        <span class="icons-content">
                                                            <input type="text" class="datepicker" placeholder="Select Date" disabled>
                                                            <i class="icon-calender"></i>
                                                        </span>
                                                    </label>
                                                </div>
                                                <div class="form-row side-by-side-v1-row">
                                                    <span class="label">Expiry Date</span>
                                                    <label>
                                                        <span class="icons-content">
                                                            <input type="text" class="datepicker" placeholder="Select Date" disabled>
                                                            <i class="icon-calender"></i>
                                                        </span>
                                                    </label>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>EMERGENCY</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Next of Kin</span>
                                                        <input type="text" placeholder="Placeholder" disabled>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Emergency Contact Person</span>
                                                        <input type="text" placeholder="Placeholder" disabled>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Relationship with Contact Person</span>
                                                        <input type="text" placeholder="Placeholder" disabled>
                                                    </label>
                                                </div>
                                                <div class="form-row phone-number-row">
                                                    <label>
                                                        <span class="label">Emergency Contact Number 1</span>
                                                        <span class="phone-number-content">
                                                            <input type="text" placeholder="+218" disabled>
                                                            <input type="text" placeholder="1234567890" disabled>
                                                        </span>
                                                    </label>
                                                </div>
                                                <div class="form-row phone-number-row">
                                                    <label>
                                                        <span class="label">Emergency Contact Number 2</span>
                                                        <span class="phone-number-content">
                                                            <input type="text" placeholder="+218" disabled>
                                                            <input type="text" placeholder="1234567890" disabled>
                                                        </span>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Blood Group</span>
                                                        <input type="text" placeholder="Placeholder" disabled>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Visual Identification Mark</span>
                                                        <textarea name="" id="" placeholder="placeholder" disabled></textarea>
                                                    </label>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                </div>
                            </div> <!-- divided-content flout-2 -->
                            <div class="button-row">
                                <input type="submit" class="button button-v4 color-1 fix-size-sml" value="Create User">
                                <a href="javascript:;" class="button button-v4 color-2 fix-size-sml">Cancel</a>
                            </div>
                        </section> <!-- content-box -->
                    </form>

                </section> <!-- content -->



            </section> <!-- content-inner -->

        </section> <!-- content-wrapper -->

    </div> <!-- page-inner -->

</div> <!-- page-wrapper -->


<script src="${pageContext.request.contextPath}/assets/js/lib.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/core.min.js"></script>
<script src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDp5oJvfmqhGjGaKJePviTrPeB4f9QCrEc&callback=initMap"></script>

</body>
</html>
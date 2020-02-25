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

            <jsp:include page="big_main_menu_bar.jsp">
                <jsp:param name="criminalDatabase" value="active" />
                <jsp:param name="officerName" value="${officerName}" />
                <jsp:param name="officerProfilePicture" value="${officerProfilePicture}" />
                <jsp:param name="accessRole" value="${accessRole}" />
            </jsp:include>

            <section class="content-inner">

                <section class="content">

                    <jsp:include page="highlight.jsp">
                        <jsp:param name="header" value="Manage Criminals" />
                    </jsp:include>

                    <form action="">

                        <section class="content-box">
                            <div class="divided-content flout-2">
                                <div>
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>IDENTITY</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row single-photo-upload">
                                                    <div class="photo-upload upload-view">
                                                        <div class="photo-upload-inner" data-upload-url="/file/post" data-delete-url="/file/post" data-max-files="1" data-upload-ajax-url="/assets/ajax/upload-file/upload-file-single.json"></div>
                                                        <i class="icon-plus"></i>
                                                        <span class="description">Upload Photo</span>
                                                    </div>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">National ID *</span>
                                                        <input type="text" placeholder="Placeholder" class="field-query" data-min-lenght="2" data-url="assets/ajax/form-request/branding-name-call.json/">
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">First Name *</span>
                                                        <input type="text" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Middle Name</span>
                                                        <input type="text" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Last Name</span>
                                                        <input type="text" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <span class="label">Date of Birth</span>
                                                    <label>
                                                        <span class="icons-content">
                                                            <input type="text" class="datepicker" placeholder="Select Date">
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
                                                                <input name="gender" type="radio" value="Male">
                                                                <span class="checkmark"></span>
                                                            </label>
                                                        </div>
                                                        <div>
                                                            <label>
                                                                <span class="text">Female</span>
                                                                <input name="gender" type="radio" value="Female">
                                                                <span class="checkmark"></span>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-row select-not-find">
                                                    <label>
                                                        <span class="label">Crime Classification</span>
                                                        <select name="" id="">
                                                            <option value="">Placeholder</option>
                                                        </select>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Driving License Number</span>
                                                        <input type="text" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Passport Number</span>
                                                        <input type="text" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Blood Group</span>
                                                        <input type="text" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Visual Identification Mark </span>
                                                        <textarea name="" id="" placeholder="Placeholder"></textarea>
                                                    </label>
                                                </div>
                                                <div class="form-row phone-number-row">
                                                    <label>
                                                        <span class="label">Mobile Number *</span>
                                                        <span class="phone-number-content">
                                                            <input type="tel" placeholder="+218">
                                                            <input type="tel" placeholder="1234567890">
                                                        </span>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Email</span>
                                                        <input type="email" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Personal ID Card</span>
                                                        <input type="email" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <span class="label">Flaged Date</span>
                                                    <label>
                                                        <span class="icons-content">
                                                            <input type="text" class="datepicker" placeholder="Select Date">
                                                            <i class="icon-calender"></i>
                                                        </span>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Wanted By</span>
                                                        <input type="email" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Case ID</span>
                                                        <input type="email" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Case Brief</span>
                                                        <textarea name="" id="" placeholder="Placeholder"></textarea>
                                                    </label>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="form-container">
                                                <div class="form-row select-not-find">
                                                    <label>
                                                        <span class="label">Status</span>
                                                        <select name="" id="">
                                                            <option value="">Placeholder</option>
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
                                                <div class="form-row select-not-find">
                                                    <label>
                                                        <span class="label">Wanted Type</span>
                                                        <select name="" id="">
                                                            <option value="">Placeholder</option>
                                                        </select>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Contact Address *</span>
                                                        <input type="text" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Parents Address</span>
                                                        <input type="text" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Friends Address</span>
                                                        <input type="text" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Relatives Address</span>
                                                        <input type="text" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Personal City</span>
                                                        <input type="text" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>CRIME DETAILS</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row select-not-find">
                                                    <label>
                                                        <span class="label">Crime Type</span>
                                                        <select name="" id="">
                                                            <option value="">Choose</option>
                                                        </select>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Crime Title</span>
                                                        <input type="email" placeholder="Type">
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Crime Scene</span>
                                                        <textarea name="" id="" placeholder="Officer Statment"></textarea>
                                                    </label>
                                                </div>
                                                <div class="form-row">
                                                    <label>
                                                        <span class="label">Crime Location</span>
                                                        <input type="email" placeholder="Placeholder">
                                                    </label>
                                                </div>
                                                <div class="form-row multiple-photo-upload">
                                                    <div class="photo-upload upload-view">
                                                        <div class="photo-upload-inner" data-upload-url="/file/post" data-delete-url="/file/post" data-max-files="5" data-upload-ajax-url="/assets/ajax/upload-file/upload-file-multiple.json" ></div>
                                                        <div class="custom-upload">
                                                            <i class="icon-plus"></i>
                                                            <span class="description">Upload Photo</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>CASE</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="search-box-v2">
                                                    <div class="form-row search-row">
                                                        <label>
                                                            <span class="label">Connect Crime to Previous Case</span>
                                                            <input type="text" placeholder="Search">
                                                        </label>
                                                    </div>
                                                    <div class="form-row submit-row">
                                                        <button type="submit" class="button button-v3 button-icons fix-size-sml">Search <i class="icon-search"></i></button>
                                                    </div>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>SUPPORTING STATMENTS</h3>
                                                <a href="javascript:;" class="button button-v4 color-1 line-height-sml addWitness"><i class="icon-plus"></i></a>
                                            </div>
                                            <div class="form-container witness-container">
                                                <div class="form-row">
                                                    <span class="label">Witness Statments</span>
                                                    <div class="input-group-content">
                                                        <label>
                                                            <input type="text"  name="witnessName" placeholder="Name">
                                                        </label>
                                                        <label>
                                                            <input type="text" name="witnessSurname" placeholder="Surname">
                                                        </label>
                                                    </div>
                                                    <div class="form-row">
                                                        <label>
                                                            <span class="label"></span>
                                                            <textarea name="witnessStatement" placeholder="Statement"></textarea>
                                                        </label>
                                                    </div>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                    <div class="box-v3">
                                        <div class="inner">
                                            <div class="highlight box-highlight">
                                                <h3>CASE PHOTOS</h3>
                                            </div>
                                            <div class="form-container">
                                                <div class="form-row multiple-photo-upload">
                                                    <div class="photo-upload upload-view">
                                                        <div class="photo-upload-inner" data-upload-url="/file/post" data-delete-url="/file/post" data-max-files="5" data-upload-ajax-url="/assets/ajax/upload-file/upload-file-multiple.json" ></div>
                                                        <div class="custom-upload">
                                                            <i class="icon-plus"></i>
                                                            <span class="description">Upload Photo</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div> <!-- form-container -->
                                        </div> <!-- inner -->
                                    </div> <!-- box-v3 -->
                                </div>
                            </div> <!-- divided-content flout-2 -->
                            <div class="button-row">
                                <input type="submit" class="button button-v4 color-3" value="Submit to Prosecution">
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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

<section class="fancy-card-box-v2">
    <div class="fancy-top-content">
        <div class="fancy-card-title">
            <h3>Crime Report Card</h3>
            <ul>
                <li><a href="javascript:;" onclick="window.print();return false;"><i class="icon-print"></i></a></li>
                <li><a href="javascript:;"><i class="icon-cancel" data-fancybox-close></i></a></li>
            </ul>
        </div>
        <div class="form-content">
            <div class="form-row">
                <label>
                    <span class="label">Crime Title</span>
                    <span class="text">${crimeTitle}</span>
                </label>
            </div>
            <div class="form-row">
                <label>
                    <span class="label">Report ID</span>
                    <span class="text">${reportId}</span>
                </label>
            </div>
            <div class="form-row">
                <label>
                    <span class="label">Status</span>
                    <span class="text">${status}</span>
                </label>
            </div>
            <div class="form-row">
                <label>
                    <span class="label">Crime Scene</span>
                    <span class="text">${crimeScene}</span>
                </label>
            </div>
            <div class="form-row">
                <label>
                    <span class="label">Suspects</span>
                    <span class="text">${suspects}</span>
                </label>
            </div>
            <div class="form-row">
                <label>
                    <span class="label">Reported Date</span>
                    <span class="text">${reportedDate}</span>
                </label>
            </div>
            <div class="form-row">
                <label>
                    <span class="label">Case Photos</span>
                    <div class="card-carousel owl-carousel owl-theme">
                        <div class="item">
                            <div class="box-v4"></div>
                            <c:if test="${not empty images}">
                                <c:forEach items="${images}" var="image">
                                    <img src="data:image/png;base64,${image}" alt=""
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                </label>
            </div>
            <div class="form-row">
                <label>
                    <span class="label">Case Brief</span>
                    <p>${caseBrief}</p>
                </label>
            </div>
        </div>
    </div>
</section>
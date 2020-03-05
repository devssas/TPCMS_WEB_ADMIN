<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

<section class="fancy-card-box-v3">
    <div class="fancy-top-content">
        <div class="fancy-card-title">
            <h3>ةيئانج ةقاطب</h3>
            <ul>
                <li><a href="javascript:;" onclick="window.print();return false;"><i class="icon-print"></i></a></li>
                <li><a href="javascript:;"><i class="icon-cancel" data-fancybox-close></i></a></li>
            </ul>
        </div>
        <figure>
            <img src="/assets/images/layout/card-figure-v2.jpg" alt="">
        </figure>
        <div class="divided-content flout-2 custom-size-v2">
            <div>
                <div class="form-row">
                    <label>
                        <span class="label">مرحبا كيف الحال</span>
                        <span class="text"></span>
                    </label>
                </div>
            </div>
            <div>
                <div class="form-row">
                    <label>
                        <span class="label"></span>
                        <span class="text"></span>
                    </label>
                </div>
            </div>
        </div>
    </div>
    <div class="content">
        <div class="form-content">
            <div class="divided-content flout-2 wrap bordered">
                <div>
                    <div class="form-row">
                        <label>
                            <span class="label">${caseId}</span>
                            <span class="text">${date}</span>
                        </label>
                    </div>
                </div>
                <div>
                    <div class="form-row">
                        <label>
                            <span class="label">ID ﺔﻴﻀﻘﻟا</span>
                            <span class="text">${caseId}</span>
                        </label>
                    </div>
                </div>
                <div>
                    <div class="form-row">
                        <label>
                            <span class="label">1 ﺔﻟﺎﺤﻟا</span>
                            <span class="text">105 ﺓﺪﺣﻮﻟا</span>
                        </label>
                    </div>
                </div>
                <div>
                    <div class="form-row">
                        <label>
                            <span class="label">ﺔﻟﺎﺤﻟا</span>
                            <span class="text">ﻞﺒﻗ ﻦﻣ ﺏﻮﻠﻄﻣ</span>
                        </label>
                    </div>
                </div>
            </div>

            <div class="form-row-v2">
                <label>
                    <span class="label">ﺔﻴﻀﻘﻟا ﺰﺟﻮﻣ</span>
                    <span class="text">${caseBrief}</span>
                </label>
            </div>
        </div>
    </div>
</section>
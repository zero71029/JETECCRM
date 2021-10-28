<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <html lang="zh-TW">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">

            <link rel="preconnect" href="https://fonts.gstatic.com">
            <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap" rel="stylesheet">

            <!-- bootstrap的CSS、JS樣式放這裡  -->
            <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-5.0.1-dist/css/bootstrap.min.css">
            <link rel="stylesheet"
                href="${pageContext.request.contextPath}/bootstrap-5.0.1-dist/css/bootstrap.rtl.min.css">
            <script src="${pageContext.request.contextPath}/bootstrap-5.0.1-dist/js/bootstrap.bundle.min.js"></script>
            <!-- <%-- jQuery放這裡 --%> -->
            <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
            <script src="${pageContext.request.contextPath}/jquery-ui-1.13.0.custom/jquery-ui.min.js"></script>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-ui-1.13.0.custom/jquery-ui.min.css">

            <!-- <%-- 主要的CSS、JS放在這裡--%> -->
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
            <c:set var="ddd" value="有未讀訊息(${user.mail.size()})"></c:set>
            <c:set var="CRM" value='${empty user?"未登入":"CRM"}'></c:set>
            <span style="color: red;"></span>
            <title>${user.mail.size() > 0 ? ddd:CRM}</title>
        </head>


        <body>
            <!-- <%-- 插入側邊欄--%> -->
            <jsp:include page="/Sidebar.jsp"></jsp:include>
            <!-- <%-- 中間主體////////////////////////////////////////////////////////////////////////////////////////--%> -->
            <div class="container-fluid">
                <div class="row justify-content-end">
                    <div class="col-lg-11">

                        <!-- 導覽列 -->
                        <nav class="navbar navbar-expand-lg navbar-light bg-light" style="text-align: left;">
                            <div class="container-fluid">
                                <a class="navbar-brand" href="${pageContext.request.contextPath}/">管理討論平台</a>
                                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                                    aria-expanded="false" aria-label="Toggle navigation">
                                    <span class="navbar-toggler-icon"></span>
                                </button>

                                <div class="collapse navbar-collapse " id="navbarSupportedContent">
                                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ">
                                        <li class="nav-item dropdown">
                                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                                                role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                一般公告
                                            </a>
                                            <ul class="dropdown-menu " aria-labelledby="navbarDropdown"
                                                style="text-align: left;">
                                                <c:forEach varStatus="loop" begin="0" end="${billboardgroup.size()-1}"
                                                    items="${billboardgroup}" var="s">
                                                    <c:if test='${s.billboardgroup == "一般公告"}'>
                                                        <li><a class="dropdown-item"
                                                                href="${pageContext.request.contextPath}/selectBillboardGroup/${s.billboardgroupid}">${s.billboardoption}</a>
                                                        </li>
                                                    </c:if>
                                                </c:forEach>
                                            </ul>
                                        </li>
                                        <li class="nav-item dropdown">
                                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                                                role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                業務
                                            </a>
                                            <ul class="dropdown-menu " aria-labelledby="navbarDropdown"
                                                style="text-align: left;">
                                                <c:forEach varStatus="loop" begin="0" end="${billboardgroup.size()-1}"
                                                    items="${billboardgroup}" var="s">
                                                    <c:if test='${s.billboardgroup == "業務"}'>
                                                        <li><a class="dropdown-item"
                                                                href="${pageContext.request.contextPath}/selectBillboardGroup/${s.billboardgroupid}">${s.billboardoption}</a>
                                                        </li>
                                                    </c:if>
                                                </c:forEach>
                                            </ul>
                                        </li>
                                        <li class="nav-item dropdown">
                                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                                                role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                採購
                                            </a>
                                            <ul class="dropdown-menu " aria-labelledby="navbarDropdown"
                                                style="text-align: left;">
                                                <c:forEach varStatus="loop" begin="0" end="${billboardgroup.size()-1}"
                                                    items="${billboardgroup}" var="s">
                                                    <c:if test='${s.billboardgroup == "採購"}'>
                                                        <li><a class="dropdown-item"
                                                                href="${pageContext.request.contextPath}/selectBillboardGroup/${s.billboardgroupid}">${s.billboardoption}</a>
                                                        </li>
                                                    </c:if>
                                                </c:forEach>
                                            </ul>
                                        </li>
                                        <li class="nav-item dropdown">
                                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                                                role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                生產
                                            </a>
                                            <ul class="dropdown-menu " aria-labelledby="navbarDropdown"
                                                style="text-align: left;">
                                                <c:forEach varStatus="loop" begin="0" end="${billboardgroup.size()-1}"
                                                    items="${billboardgroup}" var="s">
                                                    <c:if test='${s.billboardgroup == "生產"}'>
                                                        <li><a class="dropdown-item"
                                                                href="${pageContext.request.contextPath}/selectBillboardGroup/${s.billboardgroupid}">${s.billboardoption}</a>
                                                        </li>
                                                    </c:if>
                                                </c:forEach>
                                            </ul>
                                        </li>

                                        <li class="nav-item dropdown">
                                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                                                role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                研發
                                            </a>
                                            <ul class="dropdown-menu " aria-labelledby="navbarDropdown"
                                                style="text-align: left;">
                                                <c:forEach varStatus="loop" begin="0" end="${billboardgroup.size()-1}"
                                                    items="${billboardgroup}" var="s">
                                                    <c:if test='${s.billboardgroup == "研發"}'>
                                                        <li><a class="dropdown-item"
                                                                href="${pageContext.request.contextPath}/selectBillboardGroup/${s.billboardgroupid}">${s.billboardoption}</a>
                                                        </li>
                                                    </c:if>
                                                </c:forEach>
                                            </ul>
                                        </li>
                                        <li class="nav-item dropdown">
                                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                                                role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                財務
                                            </a>
                                            <ul class="dropdown-menu " aria-labelledby="navbarDropdown"
                                                style="text-align: left;">
                                                <c:forEach varStatus="loop" begin="0" end="${billboardgroup.size()-1}"
                                                    items="${billboardgroup}" var="s">
                                                    <c:if test='${s.billboardgroup == "財務"}'>
                                                        <li><a class="dropdown-item"
                                                                href="${pageContext.request.contextPath}/selectBillboardGroup/${s.billboardgroupid}">${s.billboardoption}</a>
                                                        </li>
                                                    </c:if>
                                                </c:forEach>
                                            </ul>
                                        </li>
                                        <li class="nav-item dropdown">
                                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                                                role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                行銷
                                            </a>
                                            <ul class="dropdown-menu " aria-labelledby="navbarDropdown"
                                                style="text-align: left;">
                                                <c:forEach varStatus="loop" begin="0" end="${billboardgroup.size()-1}"
                                                    items="${billboardgroup}" var="s">
                                                    <c:if test='${s.billboardgroup == "行銷"}'>
                                                        <li><a class="dropdown-item"
                                                                href="${pageContext.request.contextPath}/selectBillboardGroup/${s.billboardgroupid}">${s.billboardoption}</a>
                                                        </li>
                                                    </c:if>
                                                </c:forEach>
                                            </ul>
                                        </li>
                                    </ul>
                                    <!-- 小鈴鐺 -->
                                    <c:if test="${not empty user}">
                                        <div style="position: relative; cursor: pointer;" onclick="sh()">
                                            <img src="${pageContext.request.contextPath}/img/bell.png" alt="未讀">
                                        </div>
                                        <div style=" color: red;  font-weight:bold; cursor: pointer;">
                                            <!-- @ -->
                                            <script>var b = 0;</script>
                                            <c:if test="${not empty user.advice}">
                                                <c:forEach varStatus="loop" begin="0" end="${user.advice.size()-1}"
                                                    items="${user.advice}" var="ad">
                                                    <script>
                                                        var a = ${ ad.reply };
                                                        b += a;
                                                    </script>
                                                </c:forEach>
                                                <span onclick="sh()" class="aaa"></span>
                                            </c:if>


                                            <!-- 未讀 -->
                                            <c:if test="${not empty user.mail}">
                                                <span onclick="showUnread()">未讀:${user.mail.size()}</span>
                                            </c:if>
                                        </div>


                                        <c:if test="${not empty user.advice}">

                                            <script>if (b > 0) $(".aaa").append("@:" + b + "/");</script>
                                        </c:if>
                                    </c:if>
                                    <!-- search -->
                                    <form class="d-flex" method="post"
                                        action="${pageContext.request.contextPath}/selectBillboard">
                                        <input class="form-control me-2" type="search" placeholder="主題 or 發佈者"
                                            aria-label="Search" name="search">
                                        <button class="btn btn-outline-success" type="submit">Search</button>
                                    </form>

                                </div>
                            </div>
                        </nav>
                        <!-- 導覽列/////////////////////////////////////////// -->
                    </div>
                </div>
                <div class="row justify-content-end">
                    <div class="col-lg-8">

                        <!-- <%-- 中間主體--%> -->
                        <h1 style="color: red;">${param.mess=="1"?"權限不夠":""}</h1>
                        <h1 style="color: red;">${param.mess=="2"?"須先登入":""}</h1>
                        <h1 style="color: red;">${param.mess=="3"?"授權碼過期":""}</h1>
                        <table class="table table-hover">
                            <thead>
                                <tr style="text-align:center">
                                    <th scope="col-lg" style="width: 700px;">主題</th>
                                    <th scope="col-lg">發布時間</th>
                                    <th scope="col-lg">最後回覆時間</th>
                                    <th scope="col-lg">回應</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${not empty list}">
                                    <c:forEach varStatus="loop" begin="0" end="${list.size()-1}" items="${list}"
                                        var="s">
                                        <tr style="Cursor: pointer ;vertical-align: middle;"
                                            onclick="location.href='${pageContext.request.contextPath}/billboardReply/${s.billboardid}'">
                                            <!-- //////////////////////////////////////////////////// -->
                                            <td>
                                                <!--置頂圖片  -->
                                                <c:set var="img"
                                                    value="<img src='${pageContext.request.contextPath}/img/TTT.png' alt='置頂'>">
                                                </c:set>
                                                <span style="color: red;">${s.top == "置頂"?img:""}</span>
                                                <!-- 如果 .......就是 個人置頂 -->
                                                <c:if test="${not empty user.top}">
                                                    <c:forEach varStatus="loop" begin="0" end="${user.top.size()-1}"
                                                        items="${user.top}" var="top">
                                                        <span style="color: red;">
                                                            <c:if test="${top.billboardid == s.billboardid}">
                                                                <img src="${pageContext.request.contextPath}/img/topA.png"
                                                                    alt="">
                                                            </c:if>
                                                        </span>
                                                    </c:forEach>
                                                </c:if>
                                                <!-- 分類 -->
                                                <span
                                                    style="color: #777;font-size: 0.9rem;">[${s.billtowngroup}][${s.bgb.billboardoption}]
                                                    &nbsp;</span>

                                                <!-- 如果 mail.billboardid = 留言id 就是未讀 -->
                                                <c:if test="${not empty user.mail}">
                                                    <c:forEach varStatus="loop" begin="0" end="${user.mail.size()-1}"
                                                        items="${user.mail}" var="mail"><span style="color: red;">
                                                            ${mail.billboardid == s.billboardid? "未讀":""}</span>
                                                    </c:forEach>
                                                </c:if>
                                                <!-- 標提 -->
                                                ${s.theme}
                                                <span style="color: #777;">

                                                    <!-- 如果 .......就是被@ -->
                                                    <c:if test="${not empty user.advice}">
                                                        <c:forEach varStatus="loop" begin="0"
                                                            end="${user.advice.size()-1}" items="${user.advice}"
                                                            var="advice">
                                                            ${advice.billboardid == s.billboardid? "您已被標註":""}
                                                        </c:forEach>
                                                    </c:if>
                                                </span>
                                                <span style="color: #569b92;"> ${empty s.file?"":"有附件📎"}</span>
                                            </td>
                                            <!-- //////////////////////////////////////////////////////////////// -->
                                            <td style="text-align: center;">${s.user} <br> ${s.createtime}</td>
                                            <td style="text-align: center;">${s.reply[0].name}
                                                <br>${s.reply[0].createtime}
                                            </td>
                                            <td style="text-align: center;">${s.reply.size()}</td>
                                        </tr>

                                    </c:forEach>
                                </c:if>

                            </tbody>
                        </table>




                    </div>

                    <div class="col-lg-3">
                        <!-- 分頁 -->
                        <nav aria-label="Page navigation example">
                            <ul class="pagination">
                                <li class="page-item"><a class="page-link"
                                        href="${pageContext.request.contextPath}/billboard?pag=${param.pag<=1?1:param.pag-1}">Previous</a>
                                </li>
                                <li class="page-item"><a class="page-link"
                                        href="${pageContext.request.contextPath}/billboard?pag=1">1</a></li>
                                <li class="page-item"><a class="page-link"
                                        href="${pageContext.request.contextPath}/billboard?pag=2">2</a></li>
                                <li class="page-item"><a class="page-link"
                                        href="${pageContext.request.contextPath}/billboard?pag=3">3</a></li>
                                <li class="page-item"><a class="page-link"
                                        href="${pageContext.request.contextPath}/billboard?pag=${param.pag+1}">Next</a>
                                </li>
                            </ul>
                        </nav>
                        <!-- 分頁 ＿////////////////////-->
                        <!-- 彈窗 -->
                        <c:if test="${not empty unread}">
                            <c:forEach varStatus="loop" begin="0" end="${unread.size()}" items="${unread}" var="unread">
                                <div class="unread" title="${unread.theme}未讀">
                                    <p>${unread.content}</p>
                                </div>
                            </c:forEach>
                        </c:if>
                        <c:if test="${not empty advice}">
                            <c:forEach varStatus="loop" begin="0" end="${advice.size()}" items="${advice}" var="advice">
                                <div class="dialog" title="${advice.theme}被標記">
                                    <p>${advice.content}</p>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>
            <script>
                window.setTimeout(function () {
                    location.href = '${pageContext.request.contextPath}/';
                }, 600000);
                // 彈窗
                $(".dialog").dialog({
                    autoOpen: false,
                    position: {
                        at: "right top"
                    }
                });
                function sh() {
                    $('.dialog').dialog("open");
                }
                $(".unread").dialog({
                    autoOpen: false,
                    position: {
                        at: "right top"
                    }
                });
                function showUnread() {
                    $('.unread').dialog("open");
                }

                // function read(billboardid, username) {
                //     console.log(username);
                //     $.ajax({
                //         url: '${pageContext.request.contextPath}/read/' + billboardid + '/' + username,//接受請求的Servlet地址
                //         type: 'POST',
                //         // data: formdata,
                //         // async: false,//同步請求
                //         // cache: false,//不快取頁面
                //         // contentType: false,//當form以multipart/form-data方式上傳檔案時，需要設定為false
                //         // processData: false,//如果要傳送Dom樹資訊或其他不需要轉換的資訊，請設定為false
                //         success: function (json) {
                //             alert(json);
                //             location.href = "${pageContext.request.contextPath}/";
                //         },
                //         error: function (returndata) {
                //             console.log(returndata);
                //         }
                //     });
                // }

            </script>
        </body>

        </html>
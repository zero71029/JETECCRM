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
            <link rel="stylesheet" href="${pageContext.request.contextPath}jquery-ui-1.13.0.custom/jquery-ui.min.css">



            <!-- <%-- 主要的CSS、JS放在這裡--%> -->
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
            <title>CRM客戶管理系統</title>
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
                                <a class="navbar-brand" href="${pageContext.request.contextPath}/">首頁</a>
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


                                    未讀${user.mail.size()}

                                    <form class="d-flex" method="post"
                                        action="${pageContext.request.contextPath}/selectBillboard">
                                        <input class="form-control me-2" type="search" placeholder="主題 or 發佈者"
                                            aria-label="Search" name="search">
                                        <button class="btn btn-outline-success" type="submit">Search</button>
                                    </form>
                                </div>
                            </div>
                        </nav>
                        <!-- 導覽列 -->
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
                                            <td>
                                                <span style="color: red;">${s.top}</span> [${s.billtowngroup}] &nbsp;
                                                ${s.theme} <span style="color: red;">
                                                    <!-- 如果 mail.billboardid = 留言id 就是未讀 -->
                                                    <c:if test="${not empty user.mail}">
                                                        <c:forEach varStatus="loop" begin="0"
                                                            end="${user.mail.size()-1}" items="${user.mail}" var="mail">
                                                            ${mail.billboardid == s.billboardid? "未讀":""}
                                                        </c:forEach>
                                                    </c:if>

                                                </span>
                                            </td>
                                            <td style="text-align: center;">${s.user} <br> ${s.createtime}</td>
                                            <td style="text-align: center;">${s.reply[0].name}
                                                <br>${s.reply[0].createtime}</td>
                                            <td style="text-align: center;">${s.reply.size()}</td>
                                        </tr>

                                    </c:forEach>
                                </c:if>

                            </tbody>
                        </table>


                        <%-- <div class="row">
                            <div class="col-lg-12 row">
                                <c:if test="${not empty list}">
                                    <c:forEach varStatus="loop" begin="0" end="${list.size()-1}" items="${list}"
                                        var="s">
                                        <table border="2" class="table">

                                            <tr class="table-primary">
                                                <td class="table-primary" colspan="2"
                                                    onclick="location.href='${pageContext.request.contextPath}/billboardReply/${s.billboardid}'"
                                                    style="Cursor: pointer">
                                                    ${s.theme} <span style="color: red;">${s.top}</span></td>
                                            </tr>
                                            <tr style="position: relative;">
                                                <td td class="table-primary" style=" height: 100px; width: 100px;">
                                                    ${s.user} <br>(${s.billtowngroup})
                                                </td>
                                                <td>${s.content}
                                                    <c:if test="${not empty user}">
                                                        <!-- 有登入才顯示 -->
                                                        <c:set var="i" value="false"></c:set>
                                                        <c:forEach varStatus="loop" begin="0" end="${s.read.size()}"
                                                            items="${s.read}" var="read">
                                                            <!-- 已讀迴圈 -->
                                                            <!-- 登入者 已讀 i == ture -->
                                                            <c:if test="${user.name == read.name}">
                                                                <c:set var="i" value="ture"></c:set>
                                                                <c:set var="exitID" value="0"></c:set>
                                                            </c:if>
                                                        </c:forEach>
                                                        <!--  已讀 才顯示 -->
                                                        <c:if test='${i == "ture"}'>
                                                            <a href='${pageContext.request.contextPath}/ReRead/${s.billboardid}/${user.name}'
                                                                style='position: absolute ; right: 1%; bottom: 30px;'>取消已讀</a>
                                                        </c:if>
                                                        <c:if test='${i != "ture"}'>
                                                            <a href="javascript:read(${s.billboardid},'${user.name}')"
                                                                style='position: absolute ; right: 1%; bottom: 30px;'>已讀點擊</a>
                                                        </c:if>
                                                    </c:if>
                                                    <span
                                                        style="position: absolute ; right: 0%; bottom: 0%;">發佈時間:${s.createtime}</span>
                                                </td>
                                            </tr>
                                            <c:if test="${not empty s.reply}">
                                                <c:forEach varStatus="loop" begin="0" end="${s.reply.size()-1}"
                                                    items="${s.reply}" var="reply">
                                                    <tr>
                                                        <td class="table-primary">${reply.name} 回覆</td>
                                                        <td>${reply.content}</td>
                                                    </tr>
                                                </c:forEach>
                                            </c:if>



                                        </table><br>

                                    </c:forEach>
                                </c:if>


                            </div>
                            <div class="col-lg-1"></div>
                    </div>
                    --%>
                </div>
                <div class="col-lg-3">

                    <!-- 基本的对话框 -->
                    <c:if test="${not empty unread}">
                        <c:forEach varStatus="loop" begin="0" end="${unread.size()}" items="${unread}" var="unread">
                            <div class="dialog" title="新訊息">
                                <p>${unread}</p>
                            </div>
                        </c:forEach>

                    </c:if>
                </div>
            </div>
            </div>
            <script>

                $(".dialog").dialog({
                    autoOpen: true,
                    position:{
                        at:"right bottom"
                }
                    
                });
                function read(billboardid, username) {
                    console.log(username);
                    $.ajax({
                        url: '${pageContext.request.contextPath}/read/' + billboardid + '/' + username,//接受請求的Servlet地址
                        type: 'POST',
                        // data: formdata,
                        // async: false,//同步請求
                        // cache: false,//不快取頁面
                        // contentType: false,//當form以multipart/form-data方式上傳檔案時，需要設定為false
                        // processData: false,//如果要傳送Dom樹資訊或其他不需要轉換的資訊，請設定為false
                        success: function (json) {
                            alert(json);
                            location.href = "${pageContext.request.contextPath}/";
                        },
                        error: function (returndata) {
                            console.log(returndata);
                        }
                    });
                }
            </script>
        </body>

        </html>
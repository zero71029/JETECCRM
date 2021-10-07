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
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.rtl.min.css">
            <!-- <%-- jQuery放這裡 --%> -->
            <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
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
                    <div class="col-md-10">
                        <!-- <%-- 中間主體--%> -->
                        <h1>公佈欄</h1>
                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-8 row"   >
                                <c:if test="${not empty list}">
                                    <c:forEach varStatus="loop" begin="0" end="${list.size()-1}" items="${list}"
                                        var="s">
                                        <table border="2" class="table" onclick="location.href='${pageContext.request.contextPath}/billboardReply/${s.billboardid}'">

                                            <tr class="table-primary">
                                                <td class="table-primary" colspan="2" >${s.theme}   <span style="color: red;">${s.top}</span></td>
                                            </tr>
                                            <tr style="position: relative;">
                                                <td td class="table-primary" style=" height: 100px; width: 100px;">${s.user} <br>(${s.billtowngroup})
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
                                                            <a  href='${pageContext.request.contextPath}/ReRead/${s.billboardid}/${user.name}'
                                                            style='position: absolute ; right: 1%; bottom: 30px;'>取消已讀</a>
                                                        </c:if>
                                                        <c:if test='${i != "ture"}'>
                                                            <a  href="javascript:read(${s.billboardid},'${user.name}')"
                                                            style='position: absolute ; right: 1%; bottom: 30px;'>已讀點擊</a>
                                                        </c:if>
                                                    </c:if>
                                                    <span
                                                        style="position: absolute ; right: 0%; bottom: 0%;">發佈時間:${s.createtime}</span>
                                                </td>
                                            </tr>
                                            <c:if test="${not empty s.reply}">
                                                <c:forEach varStatus="loop" begin="0" end="${s.reply.size()-1}" items="${s.reply}" var="reply">
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
                            <div class="col-md-1"></div>
                        </div>
                    </div>
                </div>
            </div>
            <script>
                function read(billboardid,username) {
                    console.log(username);                    
                    $.ajax({
                    url: '${pageContext.request.contextPath}/read/'+billboardid+'/'+username,//接受請求的Servlet地址
                    type: 'POST',
                    // data: formData,
                    // async: false,//同步請求
                    // cache: false,//不快取頁面
                    // contentType: false,//當form以multipart/form-data方式上傳檔案時，需要設定為false
                    // processData: false,//如果要傳送Dom樹資訊或其他不需要轉換的資訊，請設定為false
                    success: function (json) {
                        alert(json);
                        location.href="${pageContext.request.contextPath}/";
                    },
                    error: function (returndata) {
                        console.log(returndata);
                    }
                });




                }
            </script>
        </body>

        </html>
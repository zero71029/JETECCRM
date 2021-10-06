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
                            <div class="col-md-8 row">
                                <c:if test="${not empty list}">
                                    <c:forEach varStatus="loop" begin="0" end="${list.size()-1}" items="${list}"
                                        var="s">

                                        <table border="2" class="table">

                                            <tr class="table-primary">
                                                <td class="table-primary" colspan="2">${s.theme}</td>
                                            </tr>
                                            <tr style="position: relative;">
                                                <td td class="table-primary" style=" height: 100px; width: 100px;"> 發佈者: <br> ${s.user}
                                                </td>
                                                <td>${s.content}<span
                                                        style="position: absolute ; right: 0%; bottom: 0%;">發佈時間:${s.createtime}</span>
                                                </td>
                                            </tr><br><br>

                                        </table><br>

                                    </c:forEach>
                                </c:if>


                            </div>
                            <div class="col-md-1"></div>
                        </div>








                    </div>
                </div>
            </div>
        </body>

        </html>
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
        </head>

        <body>


            <!-- <%-- 插入側邊欄--%> -->
            <jsp:include page="/Sidebar.jsp"></jsp:include>
            <!-- <%-- 中間主體////////////////////////////////////////////////////////////////////////////////////////--%> -->
            <div class="container-fluid">
                <div class="row justify-content-end">
                    <div class="col-md-10">
                        <!-- <%-- 中間主體--%> -->
                        <div class="row">
                            <form action="${pageContext.request.contextPath}/CRM/SaveMarket" method="post">
                                <input type="hidden" name="id" value="${bean.id}">
                                <div class="row">
                                    <div class="col-md-6">所有人<input type="text" name="user" style="width: 100%;"value="${bean.user}" maxlength="20" required></div>
                                    <div class="col-md-6">名稱<input type="text" name="name" style="width: 100%;"  value="${bean.name}" maxlength="50" required></div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">開始日期<input type="date" name="createtime" style="width: 100%;" value="${bean.createtime}" required></div>
                                    <div class="col-md-6">結束日期<input type="date" name="endtime" style="width: 100%;" value="${bean.endtime}" required></div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">成本<input type="number" name="cost" style="width: 100%;" value="${bean.cost}" required></div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">描述<textarea name="message" id="message" maxlength="9000"
                                            style="width: 100%; height: 150px;" maxlength="1000" required>${bean.message} </textarea><br><br>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary">新增/修改</button>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </body>

        </html>
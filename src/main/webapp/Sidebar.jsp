<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!-- <%-- jQuery放這裡 --%> -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
        <script src="${pageContext.request.contextPath}/jquery-ui-1.13.0.custom/jquery-ui.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-ui-1.13.0.custom/jquery-ui.min.css">
        <!-- <%-- VUE放這裡 --%> -->
        <!-- <script src="${pageContext.request.contextPath}/js/vue.js"></script> -->
        <!-- <%-- 頁首--%> -->
        <header class="container-fluid mainColor headtop">
            <div class="row">
                <a href='${pageContext.request.contextPath}/' class='col-lg-2' style="font-size: 2.5rem;">CRM</a>
                <div class='col-lg-7'></div>
                <span class='col-lg-3' style="font-size: 2.5rem;">${user.name}
                    <c:if test='${empty user}'>
                        <a href="${pageContext.request.contextPath}/newAdmin.jsp">註冊</a>
                    </c:if>  /
                    <a class="Signout"  href="${pageContext.request.contextPath}/Signout">登出</a>
                </span><br>
            </div>
        </header>
        <!--側邊  -->
        <div class="col-lg-1 navfix mainColor">
            <ul class="list-group">
                <button class="list-group-item" onclick="market()">
                    營銷模塊
                </button>
                <button class="market"
                    onclick="javascript:location.href='${pageContext.request.contextPath}/CRM/MarketList'">銷售機會</button>

                <button class="market"
                    onclick="javascript:location.href='${pageContext.request.contextPath}/CRM/QuotationList'">報價單</button>
                <button class="market"
                    onclick="javascript:location.href='${pageContext.request.contextPath}/CRM/PotentialCustomerList'">潛在客戶</button>

                <button class="list-group-item" onclick="client()">
                    客戶管理
                </button>
                <button class="client"
                    onclick="javascript:location.href='${pageContext.request.contextPath}/CRM/ClientList'">客戶訊息</button>
                <button class="client"
                    onclick="javascript:location.href='${pageContext.request.contextPath}/CRM/ContactList'">聯絡人</button>
                <button class="client">流失客戶</button>
                <button class="list-group-item" onclick="javascript:location.href=''">
                    服務管理
                </button>
                <button class="list-group-item " onclick="javascript:location.href=''">
                    數據管理
                </button>
                <c:if test='${user.position == "主管"}'>
                    <button class="list-group-item " onclick="system()">
                        系統管理
                    </button>
                    <button class="system"
                        onclick="javascript:location.href='${pageContext.request.contextPath}/system/adminList'">員工管理</button>
                    <button class="system"
                        onclick="javascript:location.href='${pageContext.request.contextPath}/system/billboardList'">討論區管理</button>
                </c:if>

            </ul>
        </div>

        <script>
            $(".market").hide();
            $(".client").hide();
            $(".system").hide();
            function market() {
                $(".market").toggle();
                $(".client").hide();
                $(".system").hide();
            }

            function client() {
                $(".client").toggle();
                $(".market").hide();
                $(".system").hide();
            }
            function system() {
                $(".system").toggle();
                $(".market").hide();
                $(".client").hide();
            }
            var Signout = '${user.name}';
            if (Signout == '') {
                $('.Signout').text("登入");
                $('.Signout').attr("href", "${pageContext.request.contextPath}/time.jsp");
            } else {

            }
        </script>
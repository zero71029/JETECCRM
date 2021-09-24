<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- <%-- 頁首--%> -->
    <header class="container-fluid mainColor headtop">
        <div class="row">
            <a href='${pageContext.request.contextPath}/backstage/shopBack.jsp' class='col-md-2'>CRM</a>
            <div class='col-md-8'></div>
            <span class='col-md-2' style="">${adminLogin.name} <a href="${pageContext.request.contextPath}/backstage/Signout">登出</a></span>
        </div>

    </header>
    <!--側邊  -->
        <div class="col-md-2 navfix mainColor">
            <ul class="list-group">
                <button class="list-group-item"
                    onclick="market()">
                    營銷模塊
                </button>             
                <button class="market" onclick="javascript:location.href='${pageContext.request.contextPath}/CRM/MarketList'">行銷計畫</button>                
                <button class="market">潛在客戶</button>
                
                <button class="list-group-item"
                    onclick="client()">
                    客戶管理
                </button>
                <button class="client">客戶訊息</button>                
                <button class="client">流失客戶</button>
                <button class="list-group-item"
                    onclick="javascript:location.href='${pageContext.request.contextPath}/backstage/userList/0'">
                    服務管理
                </button>
                <button class="list-group-item "
                    onclick="javascript:location.href='${pageContext.request.contextPath}/backstage/admin'">
                    數據管理
                </button>
                <button class="list-group-item "
                    onclick="javascript:location.href='${pageContext.request.contextPath}/backstage/messList?state=1'">
                    系統管理
                </button>

            </ul>
        </div>

        <script>
            $(".market").hide();
            $(".client").hide();
            function market(){
                $(".market").toggle();
                $(".client").hide();
            }

            function client(){
                $(".client").toggle();
                $(".market").hide();
            }
        </script>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!-- <%-- 頁首--%> -->
        <header class="container-fluid mainColor headtop">
            <div class="row">
                <a href='${pageContext.request.contextPath}/CRM.jsp' class='col-md-2'>CRM</a>
                <div class='col-md-8'></div>
                <span class='col-md-2' style="">${adminLogin.name} <a
                        href="${pageContext.request.contextPath}/backstage/Signout">登出</a></span>
            </div>

        </header>
        <!--側邊  -->
        <div class="col-md-2 navfix mainColor">
            <ul class="list-group">
                <button class="list-group-item" onclick="market()">
                    營銷模塊
                </button>
                <button class="market"
                    onclick="javascript:location.href='${pageContext.request.contextPath}/CRM/MarketList'">銷售機會</button>
                <button class="market"
                    onclick="javascript:location.href='${pageContext.request.contextPath}/CRM/PotentialCustomerList'">潛在客戶</button>

                <button class="list-group-item" onclick="client()">
                    客戶管理
                </button>
                <button class="client" onclick="javascript:location.href='${pageContext.request.contextPath}/CRM/ClientList'">客戶訊息</button>
                <button class="client" onclick="javascript:location.href='${pageContext.request.contextPath}/CRM/ContactList'">聯絡人</button>
                <button class="client">流失客戶</button>
                <button class="list-group-item" onclick="javascript:location.href=''">
                    服務管理
                </button>
                <button class="list-group-item " onclick="javascript:location.href=''">
                    數據管理
                </button>
                <button class="list-group-item " onclick="system()">
                    系統管理
                </button>
                <button class="system"
                    onclick="javascript:location.href='${pageContext.request.contextPath}/CRM/adminList'">員工管理</button>

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
        </script>
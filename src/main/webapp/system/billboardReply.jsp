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
            <!-- <%-- jQueryUI放這裡 --%> -->
            <link rel="stylesheet"
                href="${pageContext.request.contextPath}/jquery-ui-192/css/base/jquery-ui-1.9.2.custom.css">
            <script src="${pageContext.request.contextPath}/jquery-ui-192/js/jquery-ui-1.9.2.custom.js"></script>
            <!-- <%-- 主要的CSS、JS放在這裡--%> -->
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">


            <title>CRM客戶管理系統</title>
        </head>
        <style>
            .cell {
                border: 0px solid black;
                border-bottom: 1px solid black;


            }

            .cellbackgroud {
                background-color: #AAA;
            }

            .cellFrom {
                border: 0px solid black;
                /* width: 33%; */
            }

            .error {
                color: red;
            }
        </style>

        <body>
            <!-- <%-- 插入側邊欄--%> -->
            <jsp:include page="/Sidebar.jsp"></jsp:include>
            <!-- <%-- 中間主體////////////////////////////////////////////////////////////////////////////////////////--%> -->
            <div class="container-fluid">
                <div class="row justify-content-end">
                    <div class="col-md-10">
                        <!-- <%-- 中間主體--%> -->
                        
                        <br>




                        <br>
                        <form action="${pageContext.request.contextPath}/system/" method="post" id="myform"
                            style="line-height: 2rem;" class=" g-3" novalidate>
                            <div class="row">
                                <input type="hidden" name="billboardid" value="${bean.billboardid}">
                                <input type="hidden" name="user" value="${user.name}">

                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell position-relative cellbackgroud">主題*</div>
                                    <div class="col-md-5 cell">
                                        ${bean.theme}
                                    </div>
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell position-relative cellbackgroud">發佈者</div>
                                    <div class="col-md-1 cell">${bean.user}</div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell position-relative cellbackgroud">內容*</div>
                                    <div class="col-md-5 cell " style="position: relative;">
                                        ${bean.content}


                                        <c:if test="${not empty user}">
                                            <!-- 有登入才顯示 -->
                                            <c:set var="i" value="false"></c:set>
                                            <c:forEach varStatus="loop" begin="0" end="${bean.read.size()}"
                                                items="${bean.read}" var="read">

                                                <!-- 已讀迴圈 -->
                                                <!-- 登入者 已讀 i == ture -->
                                                <c:if test="${user.name == read.name}">
                                                    <c:set var="i" value="ture"></c:set>
                                                    <c:set var="exitID" value="0"></c:set>
                                                </c:if>
                                            </c:forEach>
                                            <!--  已讀 才顯示 -->
                                            <c:if test='${i == "ture"}'>
                                                <a href='${pageContext.request.contextPath}/ReRead/${bean.billboardid}/${user.name}'
                                                    style='position: absolute ; right: 1%; bottom: 30px;'>取消已讀</a>
                                            </c:if>
                                            <c:if test='${i != "ture"}'>
                                                <a href="javascript:read(${bean.billboardid},'${user.name}')"
                                                    style='position: absolute ; right: 1%; bottom: 30px;'>已讀點擊</a>
                                            </c:if>
                                        </c:if>
                                    </div>

                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell cellbackgroud">日期</div>
                                    <div class="col-md-2 cell">${bean.createtime}</div>
                                    <div class="col-md-1 cell cellbackgroud">閱讀人數</div>
                                    <div class="col-md-2 cell">${bean.read.size()}</div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell cellbackgroud">群組</div>
                                    <div class="col-md-2 cell">${bean.billtowngroup}</div>
                                    <div class="col-md-1 cell cellbackgroud">子項</div>
                                    <div class="col-md-2 cell">
                                        
                                    </div>
                                </div>
                            </div>
                        </form>


                        <!-- 回覆內容 -->
                        <c:if test="${not empty bean.reply}">
                            <c:forEach varStatus="loop" begin="0" end="${bean.reply.size()-1}" items="${bean.reply}"
                                var="s">
                                <div class="row" style="line-height: 2rem;">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell cellbackgroud">${s.name}</div>
                                    <div class="col-md-5 cell" style="position: relative;">${s.content} <span
                                            style="position: absolute;right: 0%;">${s.createtime}</span></div>
                                </div>
                            </c:forEach>
                        </c:if>



                        <!-- 回覆輸入 -->
                        <br>
                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-10">
                                <h3>回覆</h3>
                            </div>
                        </div>
                        <form action="${pageContext.request.contextPath}/system/saveReply" method="post" id="formReply"
                            class=" g-3 needs-validation">
                            <input type="hidden" name="billboardid" value="${bean.billboardid}">
                            <input type="hidden" name="name" value="${user.name}">
                            <div class="row">
                                <div class="col-md-1"></div>
                                <div class="col-md-1 cell cellbackgroud">留言</div>
                                <div class="col-md-5 cell ">
                                    <textarea class="" name="content" cols="78" rows="5" required
                                        maxlength="450"></textarea>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-1"></div>
                                <div class="col-md-6">
                                    <button type="submit" style="width: 100%;" class="btn btn-primary">留言</button>
                                </div>
                            </div>
                        </form>




                    </div>
                </div>
            </div>
        </body>
        <!-- 驗證UI -->
        <script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
        <script>
            $(".system").show();

            $(function () {
                // 日期UI
                $(".contacttime").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: "yy-mm-dd"
                });


                // 密碼驗證
                jQuery.validator.setDefaults({
                    submitHandler: function () {
                        if ("${user}" == "") {
                            alert('須登入');
                            // location.href = "${pageContext.request.contextPath}/billboardReply/${bean.billboardid}";
                        } else if (confirm("確定題交?")) form.submit();
                    }
                });
                $.extend($.validator.messages, {
                    required: "這是必填字段",
                    email: "請输入有效的電子郵件地址",
                    url: "请输入有效的网址",
                    date: "请输入有效的日期",
                    dateISO: "请输入有效的日期 (YYYY-MM-DD)",
                    number: "请输入有效的数字",
                    digits: "只能输入数字",
                    creditcard: "请输入有效的信用卡号码",
                    equalTo: "你的输入不相同",
                    extension: "请输入有效的后缀",
                    maxlength: $.validator.format("最多可以输入 {0} 个字符"),
                    minlength: $.validator.format("最少要输入 {0} 个字符"),
                    rangelength: $.validator.format("请输入长度在 {0} 到 {1} 之间的字符串"),
                    range: $.validator.format("请输入范围在 {0} 到 {1} 之间的数值"),
                    max: $.validator.format("请输入不大于 {0} 的数值"),
                    min: $.validator.format("请输入不小于 {0} 的数值")
                });
                $("#formReply").validate();

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
                        location.href = "${pageContext.request.contextPath}/billboardReply/"+billboardid;
                    },
                    error: function (returndata) {
                        console.log(returndata);
                    }
                });
            }
        </script>

        </html>
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

            .cellbackgroud{
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
                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-10">
                                <h3>員工資料</h3>
                            </div>
                        </div>



                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-2">
                                <a href="javascript:history.back()"
                                    style="text-decoration: none;text-align: center; width: 100px;background-color: #AAA;display: block;">＜</a>
                            </div>
                        </div>
                        <br>
                        <form action="${pageContext.request.contextPath}/system/SaveAdmin" method="post" id="myform"
                            class="basefrom g-3 needs-validation">
                            <div class="row">
                                <input type="hidden" name="adminid" value="${bean.adminid}">


                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell position-relative cellbackgroud">名稱*</div>
                                    <div class="col-md-2 cell">
                                        <input type="text" class=" form-control cellFrom" name="name"
                                            value="${bean.name}" maxlength="20" required>
                                        <div class="invalid-tooltip">須輸入</div>
                                    </div>
                                    <div class="col-md-1 cell cellbackgroud">編號</div>
                                    <div class="col-md-2 cell">${bean.adminid}</div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell position-relative cellbackgroud">電話*</div>
                                    <div class="col-md-2 cell">
                                        <input type="text" class=" form-control cellFrom" name="phone"
                                            value="${bean.phone}" maxlength="20" required>
                                        <div class="invalid-tooltip">須輸入</div>
                                    </div>
                                    <div class="col-md-1 cell cellbackgroud">Email</div>
                                    <div class="col-md-2 cell">
                                        <input type="email" class=" form-control cellFrom" name="email"
                                            value="${bean.email}" maxlength="90" required>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell cellbackgroud">地址</div>
                                    <div class="col-md-5 cell">
                                        <input type="text" class=" form-control cellFrom" name="address"
                                            value="${bean.address}" maxlength="100">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell cellbackgroud">狀態</div>
                                    <div class="col-md-2 cell">
                                        <select input type="text" class=" form-select cellFrom" name="state">
                                            <option ${bean.state=="在職" ?"selected":null} class="selItemOff">在職</option>
                                            <option ${bean.state=="留職停薪" ?"selected":null} class="selItemOff">留職停薪</option>
                                            <option ${bean.state=="離職" ?"selected":null} class="selItemOff">離職</option>
                                            <option ${bean.state=="試用" ?"selected":null} class="selItemOff">試用</option>
                                            <option ${bean.state=="合約到期" ?"selected":null} class="selItemOff">合約到期</option>
                                            <option ${bean.state=="退休" ?"selected":null} class="selItemOff">退休</option>                                           
                                        </select>
                                    </div>
                                    <div class="col-md-1 cell cellbackgroud">職位</div>
                                    <div class="col-md-2 cell">
                                            <select input type="text" class=" form-select cellFrom" name="position">
                                                <option ${bean.position=="職員" ?"selected":null} class="selItemOff">職員</option>
                                                <option ${bean.position=="主管" ?"selected":null} class="selItemOff">主管</option> 
                                            </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell cellbackgroud"> 部門</div>
                                    <div class="col-md-2 cell">
                                        <input type="text" class=" form-control cellFrom" name="department"
                                            value="${bean.department}" maxlength="20">
                                    </div>
                                    <div class="col-md-1 cell cellbackgroud">直屬主管</div>
                                    <div class="col-md-2 cell">
                                        <input type="text" class=" form-control cellFrom" name="director"
                                            value="${bean.director}" maxlength="20">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell cellbackgroud">密碼</div>
                                    <div class="col-md-2 cell position-relative">
                                        <input type="password" class=" form-control cellFrom" name="password"
                                            id="password" value="${bean.password}" maxlength="20">
                                        <div class="invalid-tooltip">
                                            須輸入密碼
                                        </div>
                                    </div>
                                    <div class="col-md-1 cell cellbackgroud">密碼確認</div>
                                    <div class="col-md-2 cell position-relative">
                                        <input type="password" class=" form-control cellFrom" name="password_again"
                                            id="password_again" value="${bean.password}" maxlength="20">
                                        <div class="invalid-tooltip">
                                            須輸入密碼
                                        </div>
                                    </div>
                                </div>



                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-6">
                                        <button type="submit" style="width: 100%;"
                                            class="btn btn-primary">新增/修改</button>
                                    </div>
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
                        if (confirm("題交確認")) form.submit();
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
                $("#myform").validate({
                    rules: {
                        password: "required",
                        password_again: {
                            equalTo: "#password"
                        }
                    }
                });

            });
            function basefrom() {
                if (confirm("確定修改?")) $(".basefrom").submit();
            }
        </script>

        </html>
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
            <!-- 台灣縣市二聯式選單 -->
            <script src="${pageContext.request.contextPath}/js/jquery.twzipcode.min.js"></script>
            <!-- 驗證UI -->
            <script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
            <title>CRM客戶管理系統</title>
        </head>
        <style>
            .cell {
                border: 0px solid black;
                border-bottom: 1px solid black;
                margin-bottom: 5px;

            }

            .cellFrom {
                border: 0px solid black;
                /* width: 33%; */
            }

            .btn a {
                text-decoration: none;
                text-align: center;
                background-color: #BBB;
                display: block;
            }

            .log {
                text-align: center;
                background-color: rgb(102, 102, 102);
                color: white;
                border-radius: 5px 5px 0 0;
            }

            .error {
                color: red;
            }

            .contact {
                margin: 10px 0;
            }

            .contact:hover {
                background-color: rgb(234, 169, 48);
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
                                <h3>報價單</h3>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-1 btn">
                                <a href="javascript:history.back()"
                                    style="text-decoration: none;text-align: center;background-color: #BBB;display: block;">＜</a>
                            </div>

                            <div class="col-md-1 btn">
                                <a href="javascript:"
                                    style="text-decoration: none;text-align: center;background-color: #BBB;display: block;">..</a>
                            </div>

                        </div>
                        <br>
                        <form action="${pageContext.request.contextPath}/CRM/SaveQuotation" method="post" id="myform"
                            class="basefrom g-3 ">
                            <div class="row">
                                <input type="hidden" name="quotationid" value="${bean.quotationid}">


                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-6 log">基本資訊</div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">客戶*</div>
                                    <div class="col-md-2 cell">
                                        <input type="text" class=" form-control cellFrom" name="name"
                                            value="${bean.name}" maxlength="20" required>
                                    </div>
                                    <div class="col-md-1 cell">報價日期</div>
                                    <div class="col-md-2 cell">${beac.createtime}</div>
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">負責人*</div>
                                    <div class="col-md-2 cell">
                                        <select name="user" class="form-select cellFrom"
                                            aria-label="Default select example">
                                            <option value="無" ${bean.user=="無" ?"selected":null}>無</option>
                                            <c:if test="${not empty admin}">
                                                <c:forEach varStatus="loop" begin="0" end="${admin.size()-1}"
                                                    items="${admin}" var="s">
                                                    <option value="${s.name}" ${bean.user==s.name ?"selected":null}>
                                                        ${s.name}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </div>
                                </div>


                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">電話</div>
                                    <div class="col-md-2 cell">
                                        <input type="text" class=" form-control cellFrom" name="phone"
                                            value="${bean.phone}" maxlength="20">
                                    </div>
                                    <div class="col-md-1 cell">聯絡人</div>
                                    <div class="col-md-2 cell">
                                        <input type="text" class=" form-control cellFrom" name="contactname"
                                            value="${bean.contactname}" maxlength="20">
                                    </div>

                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">報價單編號</div>
                                    <div class="col-md-2 cell">${bean.quotationid}
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">Email</div>
                                    <div class="col-md-2 cell">
                                        <input type="text" class=" form-control cellFrom" name="email"
                                            value="${bean.email}" maxlength="50">
                                    </div>
                                    <div class="col-md-1 cell">聯絡人手機</div>
                                    <div class="col-md-2 cell">
                                        <input type="text" class=" form-control cellFrom" name="contactmoblie"
                                            value="${bean.contactmoblie}" maxlength="20">
                                    </div>

                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">傳真</div>
                                    <div class="col-md-2 cell">
                                        <input type="text" class=" form-control cellFrom" name="fax" value="${bean.fax}"
                                            maxlength="20">
                                    </div>
                                    <div class="col-md-1 cell">聯絡人職務</div>                                    
                                    <div class="col-md-2 cell">
                                        <input type="text" class=" form-control cellFrom" name="contactjobtitle"
                                            value="${bean.contactjobtitle}" maxlength="20">
                                    </div>
                                </div>

                            </div>

                            <div class="row">
                                <div class="col-md-1"></div>
                                <div class="col-md-1 cell">備註</div>
                                <div class="col-md-5 cell">
                                    <textarea name="remark" class="col-md-9" id="message" maxlength="450"
                                        style="width: 100%; ">${bean.remark}</textarea>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-1">&nbsp;</div>
                                <div class="col-md-8"></div>
                            </div>
                            <br><br>
                            <div class="row">
                                <div class="col-md-1"></div>
                                <div class="col-md-6 log">xxxx</div>
                            </div>
                            <div class="row">
                                <div class="col-md-1"></div>
                                <div class="col-md-6 cell row">
                                    <div class="col-md-2 ">商品型號</div>
                                    <div class="col-md-2 ">商品規格</div>
                                    <div class="col-md-2 ">單價</div>
                                    <div class="col-md-2 ">數量</div>
                                    <div class="col-md-1"></div>
                                    <div class="col-md-2 ">金額</div>
                                </div>
                            </div>
                            <div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-6  row">
                                        <div class="col-md-2 "><input value="${bean.qdb[0].product}" type="text" name="qdb[0].product" ></div>
                                        <div class="col-md-2 "><input value="${bean.qdb[0].producttype}" type="text" name="qdb[0].producttype" ></div>
                                        <div class="col-md-2 "><input value="${bean.qdb[0].price}" type="number" name="qdb[0].price" ></div>
                                        <div class="col-md-2 "><input value="${bean.qdb[0].num}" type="number" name="qdb[0].num" ></div>
                                        <div class="col-md-2 "><input value="${bean.qdb[0].total}" type="number" name="qdb[0].total" ></div>
                                    </div>
                                    <div class="col-md-1">✚</div>
                                </div><br>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-6  row">
                                        <div class="col-md-2 "><input type="text" name="" id=""></div>
                                        <div class="col-md-2 "><input type="text" name="" id=""></div>
                                        <div class="col-md-2 "><input type="text" name="" id=""></div>
                                        <div class="col-md-2 "><input type="text" name="" id=""></div>
                                        <div class="col-md-2 "><input type="text" name="" id=""></div>
                                    </div><br>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-1"></div>
                                <div class="col-md-6"></div>
                            </div>
                            <br><br><br><br><br>


                            <div class="row">
                                <div class="col-md-1"></div>
                                <div class="col-md-6">
                                    <button type="submit" style="width: 100%;" class="btn btn-primary"
                                        onclick="">新增/修改</button>
                                </div>
                            </div>




                            <!-- ////////////////////////////////////////////////////////////////// -->
                        </div>
                    </form>
                </div>
            </div>
        </body>
        <script>

            $(".client").show();
            // 日期UI
            $(function () {
                $(".contacttime").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: "yy-mm-dd",
                    beforeShow: function (input, inst) {
                        inst.dpDiv.css({ marginTop: -input.offsetHeight + 'px' });
                    }
                });
                $(".tracktime").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: "yy-mm-dd",
                    beforeShow: function (input, inst) {
                        inst.dpDiv.css({ marginTop: -input.offsetHeight - 210 + 'px' });
                    }
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
                $("#myform").validate();
            });




        </script>

        </html>
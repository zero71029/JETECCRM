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
                background-color: #CCC;
            }

            .cellFrom {
                width: 33%;
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
                            <div class="col-md-2">
                                <a href="${pageContext.request.contextPath}/CRM/MarketList" style="text-decoration: none;text-align: center; width: 100px;background-color: #AAA;display: block;">＜</a>
                            </div>
                        </div>
                        <br>
                        <form action="${pageContext.request.contextPath}/CRM/SaveMarket" method="post"
                            class="basefrom g-3 needs-validation" novalidate>
                            <div class="row">
                                <input type="hidden" name="marketid" value="${bean.marketid}">
                                <div class="row" style="text-align: center;">

                                    <div class="col-md-1"></div>
                                    <div class="col-md-10"
                                        style="background-color: blue;font-size: 1.5rem;color: white;border-radius: 5px 5px 0 0 ;">銷售機會</div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">機會名稱*</div>
                                    <input type="text" class="col-md-9 form-control " name="name" value="${bean.name}"
                                        maxlength="50" required style="width: 74%;">
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">客戶*</div>
                                    <input type="text" class="col-md-4 form-control cellFrom" name="client"
                                        value="${bean.client}" maxlength="20" required>
                                    <div class="col-md-1 cell">機會編號</div>
                                    <div class="col-md-4">${bean.marketid}</div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">聯絡人</div>
                                    <input type="text" class=" form-control cellFrom col-md-4" name="" value=""
                                        maxlength="20">

                                    <div class="col-md-1 cell">負責人*</div>
                                    <input type="text" class="col-md-4 form-control cellFrom" name="user"
                                        value="${bean.user}" maxlength="20" required>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">聯絡人電話</div>
                                    <input type="text" class="col-md-4 form-control cellFrom" name="" value=""
                                        maxlength="20">
                                    <div class="col-md-1 cell">類型</div>
                                    <input type="text" class="col-md-4 form-control cellFrom" name="type"
                                        value="${bean.type}" maxlength="100">
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">聯絡人Email</div>
                                    <input type="text" class="col-md-4 form-control cellFrom" name="" value=""
                                        maxlength="20">
                                    <div class="col-md-1 cell">來源</div>
                                    <input type="text" class="col-md-4 form-control cellFrom" name="source"
                                        value="${bean.source}" maxlength="100">
                                </div>
                                <!-- ////////////////////////////////////////////////////////////////////////////////// -->
                                <div class="row">&nbsp;</div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">金額</div>
                                    <input type="number" class="col-md-4 form-control cellFrom" name="cost"
                                        value="${bean.cost}" maxlength="30">
                                    <div class="col-md-1 cell">成交機率</div>
                                    <input type="text" class="col-md-4 form-control cellFrom" name="clinch"
                                        value="${bean.clinch}" maxlength="20">
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">需求</div>
                                    <input type="text" class="col-md-4 form-control cellFrom" name="need"
                                        value="${bean.need}" maxlength="20">
                                    <div class="col-md-1 cell">階段</div>
                                    <input type="text" class="col-md-4 form-control cellFrom" name="stage"
                                        value="${bean.stage}" maxlength="20">
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">ROI分析</div>
                                    <input type="text" class="col-md-4 form-control cellFrom" name="roianalyze"
                                        value="${bean.roianalyze}" maxlength="100">
                                    <div class="col-md-1 cell">..</div>
                                    <input type="text" class="col-md-4 form-control cellFrom" name="" value=""
                                        maxlength="50">
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">開始時間</div>
                                    <input type="text" class="col-md-4  form-control cellFrom CreateTime"
                                        name="createtime" readonly value="${bean.createtime}">
                                    <div class="col-md-1 cell">結束時間</div>
                                    <input type="text" class="col-md-4 form-control cellFrom EndTime" name="endtime"
                                        value="${bean.endtime}" readonly>
                                </div>
                                <div class="row">&nbsp;</div>
                                <!-- /////////////////////////////////////////////////////////////////////////// -->
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">描述*</div>
                                    <textarea name="message" class="col-md-9" id="message" maxlength="9000"
                                        style=" height: 150px;" required>${bean.message} </textarea><br><br>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-10">
                                        <button type="submit" style="width: 100%;" class="btn btn-primary"
                                            onclick="return window.confirm('確定修改')">新增/修改</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <!-- ///////////////////////////////////////////////////////////////////////////// -->
                        <hr>
                        <form action="${pageContext.request.contextPath}/CRM/SaveRemark" method="post"
                            class="row g-3 needs-validation" novalidate>
                            <div class="row">
                                <input type="hidden" name="marketid" value="${bean.marketid}">
                                <input type="hidden" name="user" value="灰灰">

                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-mb-3">
                                        <label for="validationTextarea" class="form-label">備註</label>
                                        <textarea class="form-control " id="validationTextarea" required name="remark"
                                            maxlength="200"></textarea>
                                        <div class="invalid-feedback">須填寫</div>
                                    </div>
                                    <div class="col-md-2">
                                        <button style="width: 100%;" class="btn btn-primary" onclick="">確認</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-7">內容</div>
                            <div class="col-md-1">創建人 </div>
                            <div class="col-md-1"> 創建時間</div>
                            <div class="col-md-1"></div>
                        </div>
                        <br>
                        <c:if test="${not empty bean.mrb}">
                            <c:forEach varStatus="loop" begin="0" end="${bean.mrb.size()-1}" items="${bean.mrb}"
                                var="s">
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-7">${s.remark}</div>
                                    <div class="col-md-1">${s.user}</div>
                                    <div class="col-md-1">${s.createtime}</div>
                                    <div class="col-md-1"><a href="javascript:delRemark(${s.id})" style="text-decoration: none;">remove</a></div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>
        </body>
        <script>
            $(".market").show();

            // 日期UI
            $(function () {
                $(".EndTime").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: "yy-mm-dd"
                });
                $(".CreateTime").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: "yy-mm-dd"
                });
            });
            function basefrom() {
                if (confirm("確定修改?")) $(".basefrom").submit();
            }
            //表單驗證
            // Example starter JavaScript for disabling form submissions if there are invalid fields
            (function () {
                'use strict'

                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.querySelectorAll('.needs-validation')

                // Loop over them and prevent submission
                Array.prototype.slice.call(forms)
                    .forEach(function (form) {
                        form.addEventListener('submit', function (event) {
                            if (!form.checkValidity()) {
                                event.preventDefault()
                                event.stopPropagation()
                            }

                            form.classList.add('was-validated')
                        }, false)
                    })
            })()
            // ${pageContext.request.contextPath}/CRM/delRemark/${s.id}
            function delRemark(id){
                if(confirm("確定刪除?")){
                    window.location.href="${pageContext.request.contextPath}/CRM/delRemark/"+id+"/${bean.marketid}";
                }
            }

        </script>

        </html>
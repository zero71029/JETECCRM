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
                                <h3>潛在各戶</h3>
                            </div>
                        </div>



                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-1 btn">
                                <a href="javascript:history.back()"
                                    style="text-decoration: none;text-align: center;background-color: #BBB;display: block;">＜</a>
                            </div>
                            <div class="col-md-1 btn">
                                <a href=""
                                    style="text-decoration: none;text-align: center; background-color: #BBB;display: block;">轉成客戶</a>
                            </div>
                        </div>
                        <br>
                        <form action="${pageContext.request.contextPath}/CRM/SavePotentialCustomer" method="post"
                            class="basefrom g-3 needs-validation" novalidate>
                            <div class="row">
                                <input type="hidden" name="customerid" value="${bean.customerid}">

                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">名稱*</div>
                                    <div class="col-md-2 cell">
                                        <input type="text" class=" form-control cellFrom" name="name"
                                            value="${bean.name}" maxlength="20" required>
                                    </div>
                                    <div class="col-md-1 cell">編號</div>
                                    <div class="col-md-2 cell">${bean.customerid}</div>


                                    <div class="col-md-1 cell">負責人*</div>
                                    <div class="col-md-2 cell">
                                        <select name="user" class="form-select cellFrom"
                                            aria-label="Default select example">
                                            <option value="無" ${bean.user=="無" ?"selected":null}>無</option>
                                            <c:forEach varStatus="loop" begin="0" end="${admin.size()-1}"
                                                items="${admin}" var="s">
                                                <option value="${s.name}" ${bean.user==s.name ?"selected":null}>
                                                    ${s.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>


                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">公司*</div>
                                    <div class="col-md-2 cell">
                                        <input type="text" class="col-md-4 form-control cellFrom client" name="company"
                                            list="company" value="${bean.company}" maxlength="20" required>
                                        <datalist id="company">
                                            <c:if test="${not empty client}">
                                                <c:forEach varStatus="loop" begin="0" end="${client.size()-1}"
                                                    items="${client}" var="s">
                                                    <option value="${s.name}">
                                                </c:forEach>
                                            </c:if>
                                        </datalist>



                                    </div>
                                    <div class="col-md-1 cell">部門</div>
                                    <div class="col-md-2 cell"><input type="text" class=" form-control cellFrom"
                                            name="department" value="${bean.department}" maxlength="20">
                                    </div>


                                    <div class="col-md-1 cell">上次聯絡時間</div>
                                    <div class="col-md-2 cell">
                                        <input type="text" class=" form-control cellFrom contacttime" name="contacttime"
                                            value="${bean.contacttime}" maxlength="20" readonly>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">職稱</div>
                                    <div class="col-md-2 cell">
                                        <input type="text" class=" form-control cellFrom" name="jobtitle"
                                            value="${bean.jobtitle}" maxlength="20">
                                    </div>
                                    <div class="col-md-1 cell">主管</div>
                                    <div class="col-md-2 cell">
                                        <input type="text" class=" form-control cellFrom" name="director"
                                            value="${bean.director}" maxlength="20">
                                    </div>

                                    <div class="col-md-1 cell">狀態</div>
                                    <div class="col-md-2 cell">
                                        <select name="status" class="form-select cellFrom"
                                            aria-label="Default select example">
                                            <option value="未處理" ${bean.status=="未處理" ?"selected":null}>未處理</option>
                                            <option value="已聯繫" ${bean.status=="已聯繫" ?"selected":null}>已聯繫</option>
                                            <option value="不合格" ${bean.status=="不合格" ?"selected":null}>不合格</option>
                                            <option value="合格" ${bean.status=="合格" ?"selected":null}>合格</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">Email</div>
                                    <div class="col-md-2 cell">
                                        <input type="email" class=" form-control cellFrom" name="email"
                                            value="${bean.email}" maxlength="50">
                                    </div>

                                    <div class="col-md-1 cell">產業</div>

                                    <div class="col-md-2 cell">
                                        <select name="industry" class="form-select cellFrom"
                                            aria-label="Default select example">
                                            <option value="農業" ${bean.industry=="農業" ?"selected":null}
                                                class="selItemOff"> 農業</option>
                                            <option value="服裝" ${bean.industry=="服裝" ?"selected":null}
                                                class="selItemOff"> 服裝</option>
                                            <option value="金融" ${bean.industry=="金融" ?"selected":null}
                                                class="selItemOff"> 金融</option>
                                            <option value="生技" ${bean.industry=="生技" ?"selected":null}
                                                class="selItemOff"> 生技</option>
                                            <option value="化工" ${bean.industry=="化工" ?"selected":null}
                                                class="selItemOff"> 化工</option>
                                            <option value="通訊" ${bean.industry=="通訊" ?"selected":null}
                                                class="selItemOff"> 通訊</option>
                                            <option value="建築" ${bean.industry=="建築" ?"selected":null}
                                                class="selItemOff"> 建築</option>
                                            <option value="顧問" ${bean.industry=="顧問" ?"selected":null}
                                                class="selItemOff"> 顧問</option>
                                            <option value="教育" ${bean.industry=="教育" ?"selected":null}
                                                class="selItemOff"> 教育</option>
                                            <option value="電子" ${bean.industry=="電子" ?"selected":null}
                                                class="selItemOff"> 電子</option>
                                            <option value="能源" ${bean.industry=="能源" ?"selected":null}
                                                class="selItemOff"> 能源</option>
                                            <option value="工程" ${bean.industry=="工程" ?"selected":null}
                                                class="selItemOff"> 工程</option>
                                            <option value="娛樂" ${bean.industry=="娛樂" ?"selected":null}
                                                class="selItemOff"> 娛樂</option>
                                            <option value="環境" ${bean.industry=="環境" ?"selected":null}
                                                class="selItemOff"> 環境</option>
                                            <option value="政府" ${bean.industry=="政府" ?"selected":null}
                                                class="selItemOff"> 政府</option>
                                            <option value="旅館" ${bean.industry=="旅館" ?"selected":null}
                                                class="selItemOff"> 旅館</optionv>
                                            <option value="保險" ${bean.industry=="保險" ?"selected":null}
                                                class="selItemOff"> 保險</optionv>
                                            <option value="機械" ${bean.industry=="機械" ?"selected":null}
                                                class="selItemOff"> 機械</optionv>
                                            <option value="製造" ${bean.industry=="製造" ?"selected":null}
                                                class="selItemOff"> 製造</optionv>
                                            <option value="媒體" ${bean.industry=="媒體" ?"selected":null}
                                                class="selItemOff"> 媒體</optionv>
                                            <option value="零售" ${bean.industry=="零售" ?"selected":null}
                                                class="selItemOff"> 零售</option>
                                            <option value="貨運" ${bean.industry=="貨運" ?"selected":null}
                                                class="selItemOff"> 貨運</option>
                                            <option value="科技" ${bean.industry=="科技" ?"selected":null}
                                                class="selItemOff"> 科技</option>
                                            <option value="電信" ${bean.industry=="電信" ?"selected":null}
                                                class="selItemOff"> 電信</option>
                                            <option value="非營利" ${bean.industry=="非營利" ?"selected":null}
                                                class="selItemOff">非營利</option>
                                            <option value="食品飲料" ${bean.industry=="食品飲料" ?"selected":null}
                                                class="selItemOff">食品飲料</option>
                                            <option value="醫療保健" ${bean.industry=="醫療保健" ?"selected":null}
                                                class="selItemOff">醫療保健</option>
                                            <option value="交通運輸" ${bean.industry=="交通運輸" ?"selected":null}
                                                class="selItemOff">交通運輸</option>
                                            <option value="公共事業" ${bean.industry=="公共事業" ?"selected":null}
                                                class="selItemOff">公共事業</option>
                                            <option value="其他" ${bean.industry=="其他" ?"selected":null}
                                                class="selItemOff"> 其他</option>
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
                                    <div class="col-md-1 cell">公司人數</div>
                                    <div class="col-md-2 cell"><input type="number" class=" form-control cellFrom"
                                            name="companynum" value="${bean.companynum}" maxlength="20"></div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">傳真</div>
                                    <div class="col-md-2 cell">
                                        <input type="text" class=" form-control cellFrom" name="fax" value="${bean.fax}"
                                            maxlength="20">
                                    </div>
                                    <div class="col-md-1 cell">來源</div>
                                    <div class="col-md-2 cell"> <select name="source" class="form-select cellFrom"
                                            aria-label="Default select example">
                                            <option value="廣告" ${bean.source=="廣告" ?"selected":null} class="selItemOff">
                                                廣告 </option>
                                            <option value="員工推薦" ${bean.source=="員工推薦" ?"selected":null}
                                                class="selItemOff">員工推薦</option>
                                            <option value="外部推薦" ${bean.source=="外部推薦" ?"selected":null}
                                                class="selItemOff">外部推薦</option>
                                            <option value="合作夥伴" ${bean.source=="合作夥伴" ?"selected":null}
                                                class="selItemOff">合作夥伴</option>
                                            <option value="公共關係" ${bean.source=="公共關係" ?"selected":null}
                                                class="selItemOff">公共關係</option>
                                            <option value="研討會 - 內部" ${bean.source=="研討會 - 內部" ?"selected":null}
                                                class="selItemOff">研討會 - 內部 </option>
                                            <option value="研討會 - 合作夥伴" ${bean.source=="研討會 - 合作夥伴" ?"selected":null}
                                                class="selItemOff">研討會 - 合作夥伴</option>
                                            <option value="參展" ${bean.source=="參展" ?"selected":null} class="selItemOff">
                                                參展</option>
                                            <option value="網絡" ${bean.source=="網絡" ?"selected":null} class="selItemOff">
                                                網絡</option>
                                            <option value="口碑" ${bean.source=="口碑" ?"selected":null} class="selItemOff">
                                                口碑</option>
                                            <option value="其他" ${bean.source=="其他" ?"selected":null} class="selItemOff">
                                                其他</option>
                                            <!-- <option class="multiSel" style="color: rgb(170, 170, 170);" nowrap="nowrap"
                                                onclick="initOptionAdding(event);">新增選項</div> -->
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">手機</div>
                                    <div class="col-md-2 cell">
                                        <input type="text" class=" form-control cellFrom" name="moblie"
                                            value="${bean.moblie}" maxlength="20">
                                    </div>
                                    <div class="col-md-1 cell">來自活動</div>
                                    <div class="col-md-2 cell"><input type="text" class=" form-control cellFrom"
                                            name="fromactivity" value="${bean.fromactivity}" maxlength="50">
                                    </div>
                                </div>
                                <div class="row">&nbsp;</div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">地址</div>
                                    <div class="col-md-8 cell">
                                        <input type="text" class=" form-control cellFrom" name="address"
                                            value="${bean.address}" maxlength="50">
                                    </div>
                                </div>
                                <div class="row">&nbsp;</div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-9 cell">
                                        <label for="validationTextarea" class="form-label">備註</label>
                                        <textarea class="form-control " id="validationTextarea" name="remark"
                                            maxlength="200">${bean.remark}</textarea>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-9">
                                        <button type="submit" style="width: 100%;" class="btn btn-primary"
                                            onclick="return window.confirm('確定修改')">新增/修改</button>
                                    </div>
                                </div>


                            </div>
                        </form>


                        <!-- ///////////////////////////////////////////////////////////////////////////// -->
                        <hr>
                        <br><br><br>
                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-9"
                                style="text-align: center;background-color: rgb(234, 169, 48);color: white;">
                                <h5>追蹤資訊</h5>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-1">編號</div>
                            <div class="col-md-2">描述</div>
                            <div class="col-md-2">結果</div>
                            <div class="col-md-2">時間</div>
                            <div class="col-md-2">備註</div>
                        </div>
                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-9">
                                <hr>
                            </div>
                        </div>
                        <form action="${pageContext.request.contextPath}/CRM/SaveTrack" method="post"
                            class="row g-3 needs-validation" novalidate>
                            <input type="hidden" name="customerid" value="${bean.customerid}">
                            <div class="row">
                                <div class="col-md-1"></div>
                                <div class="col-md-1">編號</div>
                                <div class="col-md-2">
                                    <input type="text" class=" form-control cellFrom" name="trackdescribe" required
                                        maxlength="190">
                                </div>
                                <div class="col-md-2">
                                    <input type="text" class=" form-control cellFrom" name="result" maxlength="90">
                                </div>
                                <div class="col-md-2">
                                    <input type="text" class=" form-control cellFrom tracktime" name="tracktime"
                                        maxlength="20" required>
                                </div>
                                <div class="col-md-2">
                                    <input type="text" class=" form-control cellFrom" name="remark" maxlength="190">
                                </div>
                                <div class="col-md-1">
                                    <button style="width: 100%;" class="btn btn-primary" onclick="">新增</button>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-1"></div>
                                <div class="col-md-9">
                                    <hr>
                                </div>
                            </div>
                        </form>

                        <c:if test="${not empty bean.trackbean}">
                            <c:forEach varStatus="loop" begin="0" end="${bean.trackbean.size()-1}"
                                items="${bean.trackbean}" var="s">
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1">${s.id}</div>
                                    <div class="col-md-2">${s.trackdescribe}</div>
                                    <div class="col-md-2">${s.result}</div>
                                    <div class="col-md-2">${s.tracktime}</div>
                                    <div class="col-md-2">${s.remark}</div>
                                </div>
                                <br>
                            </c:forEach>
                        </c:if>
                        <br>
                        <br><br><br><br><br>
                        <div class="row">&nbsp;</div>
                    </div>

                </div>
            </div>
        </body>
        <script>
            $(".market").show();
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
            function delRemark(id) {
                if (confirm("確定刪除?")) {
                    window.location.href = "${pageContext.request.contextPath}/CRM/delRemark/" + id + "/${bean.customerid}";
                }
            }

        </script>

        </html>
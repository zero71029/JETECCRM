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
                background-color: rgb(234, 169, 48);
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
                                <h3>各戶</h3>
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
                        <form action="${pageContext.request.contextPath}/CRM/SaveClient" method="post" id="myform"
                            class="basefrom g-3 ">
                            <div class="row">
                                <input type="hidden" name="clientid" value="${bean.clientid}">


                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-9 log">基本資訊</div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">名稱*</div>
                                    <div class="col-md-5 cell">
                                        <input type="text" class=" form-control cellFrom" name="name"
                                            value="${bean.name}" maxlength="20" required>
                                    </div>

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

                                        <input type="text" class="col-md-4 form-control cellFrom  contactname" name="contact"
                                            list="company" value="${bean.contact[0].name}" maxlength="20" required>
                                        <datalist id="company">
                                            <c:if test="${not empty bean.contact}">
                                                <c:forEach varStatus="loop" begin="0" end="${bean.contact.size()-1}"
                                                    items="${bean.contact}" var="s">
                                                    <option value="${s.name}">
                                                </c:forEach>
                                            </c:if>
                                        </datalist>

                                    </div>


                                    <div class="col-md-1 cell">客戶編號</div>
                                    <div class="col-md-2 cell">${bean.clientid}
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">Email</div>
                                    <div class="col-md-2 cell">
                                        <input type="text" class=" form-control cellFrom" name="email"
                                            value="${bean.email}" maxlength="100">
                                    </div>
                                    <div class="col-md-1 cell">聯絡人手機</div>
                                    <div class="col-md-2 cell moblie">${bean.contact[0].moblie}
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
                                    <div class="col-md-2 cell jobtitle">
                                        ${bean.contact[0].jobtitle}
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">統一編號</div>
                                    <div class="col-md-2 cell">
                                        <input type="text" class=" form-control cellFrom" name="uniformnumber"
                                            value="${bean.uniformnumber}" maxlength="20">
                                    </div>
                                    <div class="col-md-1 cell">員工人數</div>
                                    <div class="col-md-2 cell">
                                        <input type="text" class=" form-control cellFrom" name="peoplenumber"
                                            value="${bean.peoplenumber}" maxlength="20">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">類別</div>
                                    <div class="col-md-2 cell">


                                        <select name="sort" class="form-select cellFrom"
                                            aria-label="Default select example">
                                            <option value="客戶" ${bean.sort=="客戶" ?"selected":null} class="selItemOff">客戶
                                            </option>
                                            <option value="潛在客戶" ${bean.sort=="潛在客戶" ?"selected":null}
                                                class="selItemOff">潛在客戶</option>
                                            <option value="經銷商" ${bean.sort=="經銷商" ?"selected":null} class="selItemOff">
                                                經銷商</option>
                                            <option value="分析師" ${bean.sort=="分析師" ?"selected":null} class="selItemOff">
                                                分析師</option>
                                            <option value="競爭對手" ${bean.sort=="競爭對手" ?"selected":null}
                                                class="selItemOff">競爭對手</option>
                                            <option value="整合商" ${bean.sort=="整合商" ?"selected":null} class="selItemOff">
                                                整合商</option>
                                            <option value="投資人" ${bean.sort=="投資人" ?"selected":null} class="selItemOff">
                                                投資人</option>
                                            <option value="合作夥伴" ${bean.sort=="合作夥伴" ?"selected":null}
                                                class="selItemOff">合作夥伴</option>
                                            <option value="媒體" ${bean.sort=="媒體" ?"selected":null} class="selItemOff">媒體
                                            </option>
                                            <option value="其他" ${bean.sort=="其他" ?"selected":null} class="selItemOff">其他
                                            </option>
                                        </select>



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
                                    <div class="col-md-1 cell">網站</div>
                                    <div class="col-md-5 cell">
                                        <input type="text" class=" form-control cellFrom" name="url" value="${bean.url}"
                                            maxlength="100">
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
                                    <div class="col-md-9 log">地址資訊</div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">帳單城市</div>
                                    <div class="col-md-3 cell">
                                        <div class="row" id="twzipcode"></div>
                                    </div>
                                    <div class="col-md-1 cell">送貨城市</div>
                                    <div class="col-md-3 cell">
                                        <div class="row" id="twzipcode2"></div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">帳單地址</div>
                                    <div class="col-md-3 cell">
                                        <input type="text" class=" form-control cellFrom" name="billaddress"
                                            value="${bean.billaddress}" maxlength="20">
                                    </div>
                                    <div class="col-md-1 cell">送貨地址</div>
                                    <div class="col-md-3 cell">
                                        <input type="text" class=" form-control cellFrom" name="deliveraddress"
                                            value="${bean.deliveraddress}" maxlength="20">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1"></div>
                                    <div class="col-md-3">
                                        <button type="submit" style="width: 100%;" class="btn btn-primary">送出</button>
                                    </div>
                                    <div class="col-md-1 "></div>
                                    <div class="col-md-3 ">
                                        <input type="checkbox" name="" id="SameAddress" value="SSS">
                                        <label for="SameAddress">同帳單地址</label>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <div class="row">
                            <div class="col-md-1">&nbsp;</div>
                            <div class="col-md-8"></div>
                        </div>

                        <!-- ///////////////////////////////聯絡人/////////////////////////////////// -->
                        <hr>
                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-9 log">
                                <h5>聯絡人</h5>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-9 row">
                                <div class="col-md-2">名稱</div>
                                <div class="col-md-2">職務</div>
                                <div class="col-md-2">電話</div>
                                <div class="col-md-2">手機</div>
                                <div class="col-md-2">Email</div>
                                <div class="col-md-2">備註</div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-9">
                                <hr>
                            </div>
                        </div>
                        <c:if test="${not empty bean.contact}">
                            <c:forEach varStatus="loop" begin="0" end="${bean.contact.size()-1}" items="${bean.contact}"
                                var="s">
                                <div class="row ">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-9 row contact"
                                        onclick="javascript:location.href='${pageContext.request.contextPath}/CRM/contact/${s.contactid}'">
                                        <div class="col-md-2">${s.name}</div>
                                        <div class="col-md-2">${s.jobtitle}</div>
                                        <div class="col-md-2">${s.phone}</div>
                                        <div class="col-md-2">${s.moblie}</div>
                                        <div class="col-md-2">Email</div>
                                        <div class="col-md-2">${s.remark}</div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-9 row contact"
                                onclick="javascript:location.href='${pageContext.request.contextPath}/CRM/contact/0'">
                                <div class="col-md-2">新增</div>
                                <div class="col-md-2"></div>
                                <div class="col-md-2"></div>
                                <div class="col-md-2"></div>
                                <div class="col-md-2"></div>
                                <div class="col-md-2"></div>
                            </div>
                        </div>

                        <!-- ///////////////////////////////銷售機會/////////////////////////////////// -->
                        <hr>
                        <br>
                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-9 log">
                                <h5>銷售機會</h5>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-9 row">
                                <div class="col-md-2">編號</div>
                                <div class="col-md-2">名稱</div>
                                <div class="col-md-2">聯絡人</div>
                                <div class="col-md-2">負責人</div>
                                <div class="col-md-2">金額</div>
                                <div class="col-md-2">階段</div>
                            </div>
                        </div>
                        <div class="row">
                            <c:if test="${not empty market}">
                                <c:forEach varStatus="loop" begin="0" end="${market.size()-1}" items="${market}"
                                    var="s">
                                    <div class="row ">
                                        <div class="col-md-1"></div>
                                        <div class="col-md-9 row contact"
                                            onclick="javascript:location.href='${pageContext.request.contextPath}/CRM/Market/${s.marketid}'">
                                            <div class="col-md-2">${s.marketid}</div>
                                            <div class="col-md-2">${s.name}</div>
                                            <div class="col-md-2">${s.contactname}</div>
                                            <div class="col-md-2">${s.user}</div>
                                            <div class="col-md-2">${s.cost}</div>
                                            <div class="col-md-2">${s.stage}</div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-9 row contact"
                                onclick="javascript:location.href='${pageContext.request.contextPath}/Market/Market.jsp'">
                                <div class="col-md-2">新增</div>
                                <div class="col-md-2"></div>
                                <div class="col-md-2"></div>
                                <div class="col-md-2"></div>
                                <div class="col-md-2"></div>
                                <div class="col-md-2"></div>
                            </div>
                        </div>

                        <!-- ///////////////////////////////報價單/////////////////////////////////// -->
                        <hr>
                        <br>
                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-9 log">
                                <h5>報價單</h5>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-9 row">
                                <div class="col-md-2">編號</div>
                                <div class="col-md-2">報價日期</div>
                                <div class="col-md-2">聯絡人</div>
                                <div class="col-md-2">電話</div>
                                <div class="col-md-2">金額</div>
                                <div class="col-md-2">負責人</div>
                            </div>
                        </div>
                        <div class="row">
                            <c:if test="${not empty quotation}">
                                <c:forEach varStatus="loop" begin="0" end="${quotation.size()-1}" items="${quotation}"
                                    var="s">
                                    <div class="row ">
                                        <div class="col-md-1"></div>
                                        <div class="col-md-9 row contact"
                                            onclick="javascript:location.href='${pageContext.request.contextPath}/CRM/Quotation/${s.quotationid}'">
                                            <div class="col-md-2">${s.quotationid}</div>
                                            <div class="col-md-2">${s.createtime}</div>
                                            <div class="col-md-2">${s.contactname}</div>
                                            <div class="col-md-2">${s.user}</div>
                                            <div class="col-md-2">${s.quotationid}</div>
                                            <div class="col-md-2">${s.user}</div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                        <!-- ///////////////////////////////合約/////////////////////////////////// -->
                        <hr>
                        <br>
                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-9 log">
                                <h5>合約</h5>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-9 row">
                                <div class="col-md-2">合約編號</div>
                                <div class="col-md-2">負責人</div>
                                <div class="col-md-2">生效日</div>
                                <div class="col-md-2">終止日</div>
                                <div class="col-md-2">負責人</div>
                                <div class="col-md-2">狀態</div>
                            </div>
                        </div>






                        <!-- ////////////////////////////////////////////////////////////////// -->
                    </div>
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
            // 地區ui
            $("#twzipcode").twzipcode({
                countySel: "${bean.billcity}",
                districtSel: "${bean.billtown}",
                "zipcodeIntoDistrict": true,
                // "css": ["city form-control", "town form-control"],
                "countyName": "billcity", // 指定城市 select name
                "districtName": "billtown", // 指定地區 select name
                "zipcodeName": "billpostal" // 指定號碼 select name
            });
            $("#twzipcode2").twzipcode({
                countySel: "${bean.delivercity}",
                districtSel: "${bean.delivertown}",
                "zipcodeIntoDistrict": true,
                // "css": ["city form-control", "town form-control"],
                "countyName": "delivercity", // 指定城市 select name
                "districtName": "delivertown", // 指定地區 select name
                "zipcodeName": "deliverpostal" // 指定號碼 select name
            });
            //同帳單地址
            $("#SameAddress").change(function () {
                if ($("#SameAddress:checked").val() == "SSS") {
                    $("select[name='delivercity']").val($("select[name='billcity']").val());
                    $("select[name='delivercity']").change();
                    $("select[name='delivertown']").val($("select[name='billtown']").val());
                    $("input[name='deliveraddress']").val($("input[name='billaddress']").val());
                }
            })

            var moblie = new Object;
            var jobtitle = new Object;
        </script>

        <c:if test="${not empty bean.contact}">
            <c:forEach varStatus="loop" begin="0" end="${bean.contact.size()-1}" items="${bean.contact}" var="s">
                <script>
                    moblie['${s.name}'] = '${s.moblie}';
                    jobtitle['${s.name}'] = '${s.jobtitle}';
                </script>
            </c:forEach>
        </c:if>
        <script>
            $("input[name='contact']").change(function () {
                var s = $("input[name='contact']").val();
                $(".moblie").text(moblie[s]);
                $(".jobtitle").text(jobtitle[s]);

            });

        </script>

        </html>
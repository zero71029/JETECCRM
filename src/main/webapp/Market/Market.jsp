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

            /* 彈窗 */
            .hazy {

                position: fixed;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                z-index: 40;
                visibility: visible;
            }

            .cat {
                border: blue 1px solid;
                background-color: white;
                width: 830px;
                height: 450px;
                z-index: 50;
                position: absolute;
                left: 0%;
                right: 0%;
                margin: auto;
                top: 150px;
                border-radius: 15px;
                visibility: visible;

            }



            .cat form {
                top: 10px;
                position: relative;
                left: 20px;
            }

            .cat input {
                width: 95%;
            }

            .cat select {
                width: 95%;
            }

            /* 購物車返回 */
            .catReturn {
                top: -10px;
                right: -10px;
                position: absolute;
                background-color: red;
                width: 40px;
                height: 40px;
                border-radius: 50%;
                z-index: 20;
            }

            .TTT:hover {
                background-color: #0d6efd;
            }
        </style>

        <body>
            <!-- <%-- 彈窗--%> -->
            <div class="hazy"></div>
            <div class="cat">
                <button class="catReturn">X</button>
                <br>
                <span>&nbsp;&nbsp;&nbsp; 選擇聯絡人</span>
                <hr>
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-3">名稱</div>
                    <div class="col-md-3">電話</div>
                    <div class="col-md-3">手機</div>
                </div>
                <div class="CCC">
                    <!-- append -->
                </div>
                <hr>
                <br>
                <span>&nbsp;&nbsp;&nbsp; 輸入聯絡人</p>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-10">
                            <div class="input-group input-group-sm mb-3">
                                <span class="input-group-text" id="inputGroup-sizing-sm">名稱</span>
                                <input type="text" class="form-control" aria-label="Sizing example input"
                                    aria-describedby="inputGroup-sizing-sm" name="catin">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <button class="col-md-10 catbtn" onclick="catbtn()">提交</button>

                    </div>
            </div>
            <!-- <%-- 彈窗/////////////////////////////////////--%> -->
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
                                <h3>銷售機會</h3>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-2">
                                <a href="javascript:history.back()"
                                    style="text-decoration: none;text-align: center; width: 100px;background-color: #AAA;display: block;">＜</a>
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
                                        style="background-color: blue;font-size: 1.5rem;color: white;border-radius: 5px 5px 0 0 ;">
                                        基本資料</div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">機會名稱*</div>
                                    <input type="text" class="col-md-9 form-control " name="name" value="${bean.name}"
                                        maxlength="50" required style="width: 74%;">
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">公司名*</div>
                                    <input type="text" class="col-md-4 form-control cellFrom client" name="client"
                                        list="company" value="${bean.client}" maxlength="20" required>
                                    <datalist id="company">
                                        <c:if test="${not empty client}">
                                            <c:forEach varStatus="loop" begin="0" end="${client.size()-1}"
                                                items="${client}" var="s">
                                                <option value="${s.name}">
                                            </c:forEach>
                                        </c:if>
                                    </datalist>
                                    <div class="col-md-1 cell">機會編號</div>
                                    <div class="col-md-4">${bean.marketid}</div>
                                </div>






                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">聯絡人</div>
                                    <input type="text" class=" form-control cellFrom col-md-4" name="contactname"
                                        value="${bean.contactname}" maxlength="20" readonly onclick="contact()">

                                    <div class="col-md-1 cell">負責人*</div>
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
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">聯絡人電話</div>
                                    <input type="text" class="col-md-4 form-control cellFrom" name="contactphone"
                                        value="${bean.contactphone}" maxlength="20">
                                    <div class="col-md-1 cell">產業類別</div>
                                    <input type="text" class="col-md-4 form-control cellFrom" name="type"
                                        value="${bean.type}" maxlength="100">
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">聯絡人手機</div>
                                    <input type="text" class="col-md-4 form-control cellFrom" name="contactmoblie"
                                        value="${bean.contactmoblie}" maxlength="20">
                                    <div class="col-md-1 cell">來源</div>
                                    <select class="col-md-4 form-select cellFrom" name="source">
                                        <option value="自己打來" class="selItemOff" ${bean.source==s.name ?"selected":null}>
                                            自己打來</option>
                                        <option value="員工推薦" class="selItemOff" ${bean.source==s.name ?"selected":null}>
                                            員工推薦</option>
                                        <option value="外部推薦" class="selItemOff" ${bean.source==s.name ?"selected":null}>
                                            外部推薦</option>
                                        <option value="合作夥伴" class="selItemOff" ${bean.source==s.name ?"selected":null}>
                                            合作夥伴</option>
                                        <option value="公共關係" class="selItemOff" ${bean.source==s.name ?"selected":null}>
                                            公共關係</option>
                                        <option value="研討會 - 內部" class="selItemOff" ${bean.source==s.name
                                            ?"selected":null}>研討會 - 內部 </option>
                                        <option value="研討會 - 合作夥伴" class="selItemOff" ${bean.source==s.name
                                            ?"selected":null}>研討會 - 合作夥伴 </option>
                                        <option value="廣告" class="selItemOff" ${bean.source==s.name ?"selected":null}>廣告
                                        </option>
                                        <option value="參展" class="selItemOff" ${bean.source==s.name ?"selected":null}>參展
                                        </option>
                                        <option value="網絡" class="selItemOff" ${bean.source==s.name ?"selected":null}>網絡
                                        </option>
                                        <option value="口碑" class="selItemOff" ${bean.source==s.name ?"selected":null}>口碑
                                        </option>
                                        <option value="其他" class="selItemOff" ${bean.source==s.name ?"selected":null}>其他
                                        </option>
                                    </select>

                                </div>
                                <!-- ////////////////////////////////////////////////////////////////////////////////// -->
                                <div class="row">&nbsp;</div>
                                <div class="row" style="text-align: center;">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-10"
                                        style="background-color: blue;font-size: 1.5rem;color: white;border-radius: 5px 5px 0 0 ;">
                                        需求</div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">產品類別</div>
                                    <select name="Product_Type" id="Product_Type" class="col-md-4 form-select cellFrom">
                                        <option value="尚未分類" selected="selected">請選擇...</option>
                                        <option value="大型顯示器">大型顯示器</option>
                                        <option value="空氣品質">空氣品質</option>
                                        <option value="流量-AICHI">流量-AICHI</option>
                                        <option value="流量-RGL">流量-RGL</option>
                                        <option value="流量-Siargo">流量-Siargo</option>
                                        <option value="流量-其他">流量-其他</option>
                                        <option value="記錄器">記錄器</option>
                                        <option value="資料收集器-JETEC">資料收集器-JETEC</option>
                                        <option value="資料收集器-其他">資料收集器-其他</option>
                                        <option value="溫濕-JETEC">溫濕-JETEC</option>
                                        <option value="溫濕-GALLTEC">溫濕-GALLTEC</option>
                                        <option value="溫濕-E+E">溫濕-E+E</option>
                                        <option value="溫濕-其他">溫濕-其他</option>
                                        <option value="紅外線">紅外線</option>
                                        <option value="壓力-JETEC">壓力-JETEC</option>
                                        <option value="壓力-HUBA">壓力-HUBA</option>
                                        <option value="壓力-COPAL">壓力-COPAL</option>
                                        <option value="壓力-其他">壓力-其他</option>
                                        <option value="差壓">差壓</option>
                                        <option value="氣體-JETEC">氣體-JETEC</option>
                                        <option value="氣體-Senko">氣體-Senko</option>
                                        <option value="氣體-GASDNA">氣體-GASDNA</option>
                                        <option value="氣體-手持">氣體-手持</option>
                                        <option value="氣體-其他">氣體-其他</option>
                                        <option value="氣象儀器-土壤/pH">氣象儀器-土壤/pH</option>
                                        <option value="氣象儀器-日照/紫外線">氣象儀器-日照/紫外線</option>
                                        <option value="氣象儀器-風速/風向">氣象儀器-風速/風向</option>
                                        <option value="氣象儀器-雨量">氣象儀器-雨量</option>
                                        <option value="氣象儀器-其他">氣象儀器-其他</option>
                                        <option value="水質相關">水質相關</option>
                                        <option value="液位/料位-JETEC">液位/料位-JETEC</option>
                                        <option value="液位/料位-DINEL">液位/料位-DINEL</option>
                                        <option value="液位/料位-HONDA">液位/料位-HONDA</option>
                                        <option value="液位/料位-其他">液位/料位-其他</option>
                                        <option value="溫度貼紙">溫度貼紙</option>
                                        <option value="溫控器-TOHO">溫控器-TOHO</option>
                                        <option value="溫控器-其他">溫控器-其他</option>
                                        <option value="感溫線棒">感溫線棒</option>
                                        <option value="無線傳輸">無線傳輸</option>
                                        <option value="編碼器/電位計">編碼器/電位計</option>
                                        <option value="能源管理控制">能源管理控制</option>
                                        <option value="食品">食品</option>
                                        <option value="其它">其它</option>
                                    </select>
                                    <div class="col-md-1 cell">產品名稱</div>
                                    <input type="text" class="col-md-4 form-control cellFrom" name="" value=""
                                        maxlength="20">
                                </div>


                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell">預算</div>
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

                                    <select class="col-md-4 form-select cellFrom" name="stage">
                                        <option value="尚未處理" class="selItemOff" ${bean.source==s.name ?"selected":null}>
                                            尚未處理</option>
                                        <option value="需求確認" class="selItemOff" ${bean.source==s.name ?"selected":null}>
                                            需求確認</option>
                                        <option value="聯繫中" class="selItemOff" ${bean.source==s.name ?"selected":null}>
                                            聯繫中 </option>
                                        <option value="處理中" class="selItemOff" ${bean.source==s.name ?"selected":null}>
                                            處理中</option>
                                        <option value="已報價" class="selItemOff" ${bean.source==s.name ?"selected":null}>
                                            已報價</option>
                                        <option value="成功結案" class="selItemOff" ${bean.source==s.name ?"selected":null}>
                                            成功結案</option>
                                        <option value="失敗結案" class="selItemOff" ${bean.source==s.name ?"selected":null}>
                                            失敗結案</option>
                                    </select>


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
                                    <div class="col-md-1"><a href="javascript:delRemark(${s.id})"
                                            style="text-decoration: none;">remove</a></div>
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
            function delRemark(id) {
                if (confirm("確定刪除?")) {
                    window.location.href = "${pageContext.request.contextPath}/CRM/delRemark/" + id + "/${bean.marketid}";
                }
            }
            function contact() {
                $(".cat").show();
                $(".hazy").show();
                console.log($("input[name='client']").val());
                $.ajax({
                    url: '${pageContext.request.contextPath}/CRM/selectContactByClientName/' + $("input[name='client']").val(),//接受請求的Servlet地址
                    type: 'POST',
                    // data: formData,
                    // async: false,//同步請求
                    // cache: false,//不快取頁面
                    // contentType: false,//當form以multipart/form-data方式上傳檔案時，需要設定為false
                    // processData: false,//如果要傳送Dom樹資訊或其他不需要轉換的資訊，請設定為false
                    success: function (json) {
                        $(".CCC").empty();

                        for (var j of json) {
                            console.log(j);

                            $(".CCC").append('<div class="row TTT" onclick="clickContact(`' + j.name + '`,`' + j.phone + '`,`' + j.moblie + '`)">' +
                                '<div class="col-md-1"></div>' +
                                '<div class="col-md-3">' + j.name + '</div>' +
                                '<div class="col-md-3">' + j.phone + '</div>' +
                                '<div class="col-md-3">' + j.moblie + '</div>' +
                                '</div>');
                        }
                    },
                    error: function (returndata) {
                        console.log(returndata);
                    }
                });


            }
            $(".cat").hide();
            $(".hazy").hide();
            $(".catReturn").click(function () {
                $(".cat").hide();
                $(".hazy").hide();
            });
            function clickContact(name, phone, moblie) {
                $(".cat").hide();
                $(".hazy").hide();
                $("input[name='contactname']").val(name);
                $("input[name='contactphone']").val(phone);
                $("input[name='contactmoblie']").val(moblie);
            }
            function catbtn() {
                $(".cat").hide();
                $(".hazy").hide();
                $("input[name='contactname']").val($("input[name='catin']").val());
                $("input[name='contactphone']").val("");
                $("input[name='contactmoblie']").val("");
            }



        </script>

        </html>
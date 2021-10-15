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
            <!-- <%-- 彈窗--%> -->
            <div class="hazy"></div>
            <div class="cat">
                <button class="catReturn">X</button>
                <br>
                <ul class="optinUL">

                </ul>
                <div class="input-group mb-3">
                    <input type="text" class="form-control addOption" placeholder="新增" aria-label="Recipient's username"
                        aria-describedby="button-addon2">
                    <button class="btn btn-outline-secondary" type="button" id="button-addon2"
                        onclick="addOption()">提交</button>
                </div>


            </div>
            <!-- <%-- 彈窗/////////////////////////////////////--%> -->
            <!-- <%-- 插入側邊欄--%> -->
            <jsp:include page="/Sidebar.jsp"></jsp:include>
            <!-- <%-- 中間主體////////////////////////////////////////////////////////////////////////////////////////--%> -->
            <div class="container-fluid">
                <div class="row justify-content-end">
                    <div class="col-lg-7">
                        <!-- <%-- 中間主體--%> -->
                        <br>
                        <br>

                        <form action="${pageContext.request.contextPath}/saveAuthorize/${authorizeBean.id}"
                            method="post" id="myform" class="g-3 needs-validation">
                            <div class="row">
                                <input type="hidden" name="authorize" value="${param.authorize}">
                                <input type="hidden" name="billboardid" value="${bean.billboardid}">
                                <input type="hidden" name="user" value="${user.name}">
                                <div class="row">
                                    <div class="col-lg-1 cell position-relative cellbackgroud">發佈者</div>
                                    <div class="col-lg-9 cell">${bean.user}${param.authorize}</div>
                                </div>

                                <div class="row">

                                    <div class="col-lg-1 cell position-relative cellbackgroud">主題*</div>
                                    <div class="col-lg-9 cell">
                                        <input type="text" class=" form-control cellFrom" name="theme"
                                            value="${bean.theme}" maxlength="90" required>
                                    </div>

                                </div>
                                <div class="row">

                                    <div class="col-lg-1 cell position-relative cellbackgroud">內容*</div>
                                    <div class="col-lg-9 cell ">
                                        <textarea class="cellFrom" name="content" cols="100" rows="10" required
                                            maxlength="450">${bean.content}</textarea>
                                    </div>

                                </div>
                                <input type="hidden" name="state" value="發佈">
                                <div class="row">

                                    <div class="col-lg-1 cell cellbackgroud">置頂</div>
                                    <div class="col-lg-4 cell">
                                        <select input type="text" class=" form-select cellFrom" name="top">
                                            <option ${bean.top=="" ?"selected":null} class="selItemOff" value="">無
                                            </option>
                                            <option ${bean.top=="置頂" ?"selected":null} class="selItemOff" value="置頂">置頂
                                            </option>

                                        </select>
                                    </div>
                                    <div class="col-lg-1 cell cellbackgroud">XX</div>
                                    <div class="col-lg-4 cell"> <a href="javascript:ReadNum()"></a>

                                    </div>
                                </div>
                                <div class="row">

                                    <div class="col-lg-1 cell cellbackgroud">群組</div>
                                    <div class="col-lg-4 cell">
                                        <select input type="text" class=" form-select cellFrom billboardGroup"
                                            name="billtowngroup">
                                            <option ${bean.billtowngroup=="一般公告" ?"selected":null} class="selItemOff"
                                                value="一般公告">一般公告</option>
                                            <option ${bean.billtowngroup=="生產" ?"selected":null} class="selItemOff"
                                                value="生產">生產</option>
                                            <option ${bean.billtowngroup=="採購" ?"selected":null} class="selItemOff"
                                                value="採購">採購</option>
                                            <option ${bean.billtowngroup=="研發" ?"selected":null} class="selItemOff"
                                                value="研發">研發</option>
                                            <option ${bean.billtowngroup=="業務" ?"selected":null} class="selItemOff"
                                                value="業務">業務</option>
                                            <option ${bean.billtowngroup=="行銷" ?"selected":null} class="selItemOff"
                                                value="行銷">行銷</option>
                                        </select>
                                    </div>
                                    <div class="col-lg-1 cell cellbackgroud">子項</div>
                                    <div class="col-lg-4 cell">
                                        <select name="billboardgroupid" id="" class="form-select billtownoption">
                                            <!--  -->

                                            <!--  -->
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-10">
                                        <button type="submit" style="width: 100%;" class="btn btn-primary">儲存</button>
                                    </div>
                                </div>


                            </div>
                        </form>
                        <br><br>

                        <c:if test="${empty authorizeBean}">
                            <form action="${pageContext.request.contextPath}/system/authorize" method="POST"
                                name="authorize">
                                <div class="row">

                                    <div class="col-lg-1 cell cellbackgroud">授權</div>
                                    <div class="col-lg-9 cell">
                                        <select input type="text" class=" form-select cellFrom" name="adminid">

                                            <c:if test="${not empty admin}">
                                                <c:forEach varStatus="loop" begin="0" end="${admin.size()-1}"
                                                    items="${admin}" var="s">
                                                    <option class="selItemOff" value="${s.adminid}">${s.name}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="row">

                                        <div class="col-lg-10">
                                            <button type="submit" style="width: 100%;"
                                                class="btn btn-primary">發送</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </c:if>
                        <!-- 回覆內容 -->
                        <c:if test="${not empty bean.reply}">
                            <c:forEach varStatus="loop" begin="0" end="${bean.reply.size()-1}" items="${bean.reply}"
                                var="s">
                                <div class="row" style="line-height: 2rem;">
                                    <div class="col-md-1 cell cellbackgroud">${s.name}</div>
                                    <div class="col-md-9 cell" style="position: relative;">${s.content} <span
                                            style="position: absolute;right: 0%;">${s.createtime}</span></div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>


                    <div class="col-lg-3">
                        <!-- 附件 -->
                        <div class="row fileDiv">
                            <div class="col-lg-6 ">
                                <a href="${pageContext.request.contextPath}/file/${s.url}">${s.url}</a>
                            </div>
                            <div class="col-lg-6 ">
                                <a
                                    href="${pageContext.request.contextPath}/system/remove/${s.fileid}/${bean.billboardid}">remove</a>
                            </div>
                        </div>
                        <br>
                        <!-- 上傳 -->
                        <div class="row upDiv">
                            <form class="row uppdf" action="" method="post" enctype='multipart/form-data'>
                                <input type="file" name="file1" onchange="upfile(0);" class="fileInput" value="" />
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </body>
        <!-- 驗證UI -->
        <script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
        <!-- 分類 -->
        <script>
            var billboardgroup = new Array();
        </script>
        <c:forEach varStatus="loop" begin="0" end="${billboardgroup.size()-1}" items="${billboardgroup}" var="s">
            <script>
                billboardgroup.push({ "${s.billboardgroup}": "${s.billboardoption}" });
            </script>
        </c:forEach>
        <script>
            var group = "${bean.billtowngroup}";
 
            if (group == "") group = "一般公告";

            // 插入子項
            for (var option of billboardgroup) {
                if (Object.keys(option)[0] == group) $(".billtownoption").append('<option  value="' + option[group] + '">' + option[group] + '</option>');
            }
            var aaa = '${bean.bgb.billboardoption}';
            if (aaa == '') {

            } else {
                $(".billtownoption").val(aaa);
            }

            var $group = $(".billboardGroup");
            var $option = $(".billtownoption");

            //切換群組
            $group.change(function () {
                $group = $(".billboardGroup");
                group = $group.val();
                console.log(group);




                $option.empty();
                for (var b of billboardgroup) {
                    if (Object.keys(b)[0] == $group.val()) {
                        $option.append('<option  value="' + b[$group.val()] + '">' + b[$group.val()] + '</option>');
                    }
                }

            })




        </script>
        <!-- 分類結束///////////// -->
        <script>
            $(".system").show();
            $(".cat").hide();
            $(".hazy").hide();
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
                    // rules: {
                    //     password: "required",
                    //     password_again: {
                    //         equalTo: "#password"
                    //     }
                    // }
                });

            });
            //上傳型錄
            upfile = function (i) {
                var formData = new FormData($(".uppdf")[0]);
                console.log(formData);
                $.ajax({
                    url: '${pageContext.request.contextPath}/upFile/${authorizeBean.id}',//接受請求的Servlet地址
                    type: 'POST',
                    data: formData,
                    async: false,//同步請求
                    cache: false,//不快取頁面
                    contentType: false,//當form以multipart/form-data方式上傳檔案時，需要設定為false
                    processData: false,//如果要傳送Dom樹資訊或其他不需要轉換的資訊，請設定為false
                    success: function (url) {
                        alert(url);
                        selectFile();
                    },
                    error: function (returndata) {
                        console.log(returndata);
                    }
                });
            }
            $(function () {
                $("#dialog").dialog({ autoOpen: false });
            });
            function ReadNum() {
                $("#dialog").dialog("open");
            }
            //要求附件
            function selectFile() {
                $.ajax({
                    url: '${pageContext.request.contextPath}/selectFile/${authorizeBean.id}',//接受請求的Servlet地址
                    type: 'POST',
                    // data: formData,
                    // async: false,//同步請求
                    // cache: false,//不快取頁面
                    // contentType: false,//當form以multipart/form-data方式上傳檔案時，需要設定為false
                    // processData: false,//如果要傳送Dom樹資訊或其他不需要轉換的資訊，請設定為false
                    success: function (json) {
                        console.log(json);
                        $(".fileDiv").empty();
                        for(var f of json){
                            $(".fileDiv").append('<div class="col-lg-6 "><a href="${pageContext.request.contextPath}/file/'+f.url+'">'+f.url+'</a></div>'+
                            '<div class="col-lg-6 "><a href="${pageContext.request.contextPath}/remove/'+f.fileid+'">remove</a></div>');
                        }
                        
                        $(".upDiv").empty();
                        $(".upDiv").append('<form class="row uppdf" action="" method="post" enctype="multipart/form-data">'+
                                '<input type="file" name="file1" onchange="upfile(0);" class="fileInput" value="" /></form>');


                    },
                    error: function (returndata) {
                        console.log(returndata);
                    }
                });
            }

            selectFile();


        </script>
        <div id="dialog" title="已讀人員">
            <p>
                <c:forEach varStatus="loop" begin="0" end="${bean.read.size()}" items="${bean.read}" var="s">
                    ${s.name} <br>
                </c:forEach>


            </p>
        </div>

        </html>
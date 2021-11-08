<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <html lang="zh-TW">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">

            <link rel="preconnect" href="https://fonts.gstatic.com">
            <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap" rel="stylesheet">





            <title>CRM客戶管理系統</title>
        </head>
        <style>
            .cell {
                border: 1px solid #8e8e8e;
            }

            .cellbackgroud {
                background-color: #CCC;
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
                    <div class="col-md-7">
                        <!-- <%-- 中間主體--%> -->
                        <br>
                        <br>


                        <form action="" method="post" id="myform" style="line-height: 2rem;" class=" g-3" novalidate>

                            <div class="row">
                                <input type="hidden" name="billboardid" value="${bean.billboardid}">
                                <input type="hidden" name="user" value="${user.name}">
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-lg-8">
                                        <!-- 上一頁 -->
                                        <a href="#" onclick="self.location=document.referrer;" .
                                            style="text-decoration: none;">
                                            <img src="${pageContext.request.contextPath}/img/Pre.png" alt="上一頁">
                                        </a>${bean.bgb.billboardgroup}>${bean.bgb.billboardoption}
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-9"
                                        style="background-color: #569b92; border: solid 1px #569b92; position: relative; color: white;font-size: 18px;">
                                        討論區

                                        <!-- 有登入才顯示 -->
                                        <c:if test='${not empty user}'>
                                            <span style="position: absolute; right: 0%; top: 0%;">
                                                <img src="${pageContext.request.contextPath}/img/copy.png" alt="複製"
                                                    onclick="cop()" style="cursor: pointer;" data-bs-toggle="tooltip"
                                                    data-bs-placement="bottom" title="複製">


                                                <img src="${pageContext.request.contextPath}/img/未釘選-01.png" alt="未釘選"
                                                    onclick="tops()" style="cursor: pointer;" data-bs-toggle="tooltip"
                                                    data-bs-placement="bottom" title="追蹤" id="topImg">
                                                <c:if test='${not empty user.top}'>
                                                    <c:forEach varStatus="loop" begin="0" end="${user.top.size()-1}"
                                                        items="${user.top}" var="top">
                                                        <script>
                                                            var billboardid = ${ top.billboardid };
                                                            if (billboardid == ${ bean.billboardid }) {
                                                                $("#topImg").attr("src", "${pageContext.request.contextPath}/img/CCC.png");
                                                                $("#topImg").attr("title", "取消追蹤");
                                                            }

                                                        </script>
                                                    </c:forEach>
                                                </c:if>

                                                <!-- 有資料才顯示 -->
                                                <c:set var="i" value="false"></c:set>
                                                <c:forEach varStatus="loop" begin="0" end="${user.advice.size()}"
                                                    items="${user.advice}" var="advice">
                                                    <!-- 已讀迴圈 -->
                                                    <!-- 登入者  有被@   i == ture -->
                                                    <c:if test="${bean.billboardid == advice.billboardid}">
                                                        <c:set var="i" value="ture"></c:set>
                                                    </c:if>
                                                </c:forEach>

                                                <c:set var="m" value="false"></c:set>
                                                <c:forEach varStatus="loop" begin="0" end="${user.mail.size()}"
                                                    items="${user.mail}" var="mail">
                                                    <!-- 已讀迴圈 -->
                                                    <!-- 登入者  有mail     i == ture -->
                                                    <c:if test="${bean.billboardid == mail.billboardid}">
                                                        <c:set var="m" value="ture"></c:set>
                                                    </c:if>
                                                </c:forEach>

                                                <!--  已讀 才顯示 -->
                                                <c:if test='${m != "ture" }'>

                                                    <img src="${pageContext.request.contextPath}/img/unread.png"
                                                        alt="已讀" data-bs-toggle="tooltip" data-bs-placement="bottom"
                                                        title="已讀">
                                                </c:if>
                                                <c:if test='${m == "ture"}'>
                                                    <a href="javascript:read(${bean.billboardid},${user.adminid})"><img
                                                            src="${pageContext.request.contextPath}/img/read.png"
                                                            alt="已讀點擊" data-bs-toggle="tooltip"
                                                            data-bs-placement="bottom" title="已讀點擊"></a>
                                                </c:if>
                                            </span>
                                        </c:if>

                                    </div>

                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell position-relative cellbackgroud" style="font-size: 18px;">
                                        發佈者</div>
                                    <div class="col-md-8 cell">${bean.user}</div>
                                </div>


                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell position-relative cellbackgroud" style="font-size: 18px;">
                                        主題</div>
                                    <div class="col-md-8 cell" style="position: relative;">
                                        ${bean.theme}
                                        <span
                                            style="color: #8e8e8e; position: absolute ;right: 0%;">${bean.createtime}</span>

                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell position-relative cellbackgroud" style="font-size: 18px;">
                                        內容</div>
                                    <div class="col-md-8 cell content" style="word-wrap:break-word;">
                                        ${bean.content}
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell position-relative cellbackgroud" style="font-size: 18px;">
                                        標記</div>
                                    <div class="col-md-8 cell content" style="word-wrap:break-word;">&nbsp;
                                        <c:if test="${not empty bean.advice}">
                                            <c:forEach varStatus="loop" begin="0" end="${bean.advice.size()-1}"
                                                items="${bean.advice}" var="ad">
                                                <span class="ad${ad.adviceto}"> ${ad.formname}</span>
                                                &nbsp;&nbsp;&nbsp;
                                            </c:forEach>
                                        </c:if>




                                    </div>
                                </div>

                            </div>
                        </form>


                        <!-- 回覆內容 -->
                        <style>
                            .replyA a {
                                font-size: 14px;
                                color: #569b92;
                                text-decoration: none;
                            }
                        </style>
                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-9 row">
                                <c:if test="${not empty reply}">
                                    <c:forEach varStatus="loop" begin="0" end="${reply.size()-1}" items="${reply}"
                                        var="s">
                                        <hr style="color: #569b92; opacity: 1;">
                                        <div class="row" style="min-height: 70px;">
                                            <div class="col-md-2" style="color: #569b92;font-size: 18px;">
                                                ${s.name}</div>
                                            <div class="col-md-10" style="position: relative; word-wrap:break-word;">
                                                ${s.content}
                                                <c:if test="${s.name == user.name}">
                                                    <form action="${pageContext.request.contextPath}/replyChange"
                                                        class="replyText showText${s.replyid}" method="post">
                                                        <textarea name="content" id="" cols="70" rows="3"
                                                            maxlength="950">${s.content}</textarea>
                                                        <input type="hidden" name="replyid" value="${s.replyid}">
                                                        <input type="hidden" name="billboardid"
                                                            value="${s.billboardid}">
                                                        <input type="hidden" name="name" value="${s.name}">
                                                        <button type="submit"
                                                            style="width: 95%; background-color: #569b92;"
                                                            class="btn ">修改</button>
                                                    </form>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="row replyA" style="font-size: 12;">
                                            <div class="col-md-9 "
                                                style="position: relative; word-wrap:break-word;color: #8e8e8e; ">
                                                ${s.createtime}
                                            </div>
                                            <div class="col-md-3 ccc" style="text-align: right;">
                                                <c:if test="${s.name == user.name}">
                                                    <a href="javascript:replyChange('${s.replyid}')">修改</a>&nbsp;
                                                    <a
                                                        href="javascript:if(confirm('確定刪除'))location.href='${pageContext.request.contextPath}/replyRemove/${s.replyid}'">刪除</a>&nbsp;
                                                    
                                                </c:if>
                                                <c:if test="${not empty user}">
                                                    <a href="javascript:showReplyText('${s.replyid}');">回復</a>
                                                </c:if>

                                            </div>
                                            <br><br>
                                        </div>

                                        <!-- 評論 -->
                                        <c:if test="${not empty s.reply}">
                                            <c:forEach varStatus="loop" begin="0" end="${s.reply.size()-1}"
                                                items="${s.reply}" var="reply">
                                                <div class="row">
                                                    <div class="col-md-1"></div>
                                                    <div class="col-md-11 ">
                                                        <div class="row replyA">
                                                            <hr>
                                                            <div class="col-md-2 " style="color: #569b92;">${reply.name}
                                                            </div>
                                                            <div class="col-md-8" style="word-wrap:break-word;">
                                                                ${reply.content} </div>
                                                            <div class="col-md-2 ">
                                                                <c:if test="${reply.name == user.name}">
                                                                    <a
                                                                        href="javascript:removeReplyreply('${reply.id}')">remove</a>
                                                                </c:if>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <br><br><br>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${not empty user}">
                                            <form action="${pageContext.request.contextPath}/saveReplyreply"
                                                method="post">
                                                <div class="row replyreply show${s.replyid}">
                                                    <div class="col-md-1 "></div>
                                                    <div class="col-md-11 ccc">
                                                        <input type="hidden" name="replyid" value="${s.replyid}">
                                                        <input type="hidden" name="name" value="${user.name}">
                                                        <input type="hidden" name="billboardid"
                                                            value="${bean.billboardid}">
                                                        <div class="input-group mb-3">
                                                            <input type="text" class="form-control" name="content"
                                                                placeholder="" aria-label="Recipient's username"
                                                                aria-describedby="button-addon2" required
                                                                maxlength="950"
                                                                style="background-color: #E8e8e8; border: 1px solid #ccc;">
                                                            <button class="btn  btn-primary" type="submit"
                                                                id="button-addon2 ">回覆</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </c:if>

                                    </c:forEach>
                                </c:if>

                            </div>
                        </div>

                        <c:if test="${not empty user}">
                            <!-- 回覆輸入 -->
                            <br>
                            <div class="row">
                                <div class="col-md-1"></div>
                                <div class="col-md-9"
                                    style="background-color: #569b92; border: solid 1px #569b92;color: white;">
                                    回覆</div>
                            </div>
                            <form action="${pageContext.request.contextPath}/saveReply" method="post" id="formReply"
                                class=" g-3 needs-validation">
                                <input type="hidden" name="billboardid" value="${bean.billboardid}">
                                <input type="hidden" name="name" value="${user.name}">
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-1 cell cellbackgroud">留言</div>
                                    <div class="col-md-8 cell ">
                                        <textarea class="" name="content" cols="70" rows="5" required placeholder=""
                                            maxlength="950"></textarea>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-9">
                                        <button type="submit"
                                            style="width: 100%;background-color: #08604f; color: white;"
                                            class="btn ">留言</button>
                                    </div>
                                </div>
                            </form>
                        </c:if>
                        <br><br><br><br>
                        <div class="row"></div>



                    </div>

                    <div class="col-md-4">
                        <br><br><br>

                        <br>
                        <!-- 附件 -->
                        <c:if test="${not empty bean.file}">
                            <div class="row">
                                <div class="col-lg-7 cell" style="background-color : #569b92 ;text-align: center;">附件
                                </div>
                            </div>
                            <c:forEach varStatus="loop" begin="0" end="${bean.file.size()-1}" items="${bean.file}"
                                var="s">
                                <c:set var="url" value="${pageContext.request.contextPath}/file/${s.url}"></c:set>
                                <div class="row" draggable="true"
                                    ondragstart="event.dataTransfer.setData('text/plain', '<img width=100% src=${url} onerror=errorOne()>')">
                                    <div class="col-md-2 cell position-relative cellbackgroud">附件</div>
                                    <div class="col-lg-5 cell" style="word-wrap: break-word;"><a target="_blank"
                                            href="${pageContext.request.contextPath}/file/${s.url}">${s.name}</a></div>

                                </div>
                            </c:forEach>
                        </c:if>
                        <!-- 新訊息 -->
                        <br><br>
                        <c:if test="${not empty news}">
                            <c:forEach varStatus="loop" begin="0" end="${news.size()-1}" items="${news}" var="s">
                                <a style="color: #08604f; opacity: 100%; text-decoration: none;"
                                    href="${pageContext.request.contextPath}/billboardReply/${s.billboardid}">
                                    <div class="row">

                                        <div class="col-lg-5 "
                                            style="border-bottom: 1px solid black; position: relative; word-wrap:break-word;">
                                            ${s.theme}
                                        </div>
                                        <div class="col-lg-2 " style="border-bottom: 1px solid black;"><img
                                                src="${pageContext.request.contextPath}/img/news.png" alt="取消已讀"></div>
                                    </div>
                                </a>
                            </c:forEach>
                        </c:if>

                    </div>
                </div>
            </div>
            <script>
                $('.replyText').hide();
                $('.replyreply').hide();
                function showReplyText(replyid) {
                    $('.show' + replyid).toggle();
                }









                function unread(billboardid, adminid) {
                    alert("取消已讀");
                    location.href = '${pageContext.request.contextPath}/ReRead/' + billboardid + '/' + adminid;

                }

                function read(billboardid, adminid) {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/read/' + billboardid + '/' + adminid,//接受請求的Servlet地址
                        type: 'POST',
                        // data: formdata,
                        // async: false,//同步請求
                        // cache: false,//不快取頁面
                        // contentType: false,//當form以multipart/form-data方式上傳檔案時，需要設定為false
                        // processData: false,//如果要傳送Dom樹資訊或其他不需要轉換的資訊，請設定為false
                        success: function (json) {
                            alert(json);
                            location.href = "${pageContext.request.contextPath}/billboardReply/" + billboardid;
                        },
                        error: function (returndata) {
                            console.log(returndata);
                        }
                    });
                }
                // var reg = "((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";
                // var aaa = $(".content").text();
                // console.log(aaa);
                // var ccc = aaa.match(reg);
                // console.log(ccc);
                // for (let a of ccc) console.log(a);

                //複製網址
                function cop() {
                    const value = location.href;
                    const el = document.createElement('textarea');
                    el.value = value;
                    document.body.appendChild(el);
                    el.select();
                    document.execCommand('copy');
                    document.body.removeChild(el);
                    alert("複製成功");
                }
                //點擊置頂
                function tops() {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/top/${bean.billboardid}/${user.adminid}',//接受請求的Servlet地址
                        type: 'POST',
                        // data: formdata,
                        // async: false,//同步請求
                        // cache: false,//不快取頁面
                        // contentType: false,//當form以multipart/form-data方式上傳檔案時，需要設定為false
                        // processData: false,//如果要傳送Dom樹資訊或其他不需要轉換的資訊，請設定為false
                        success: function (json) {
                            alert(json);
                            location.href = "${pageContext.request.contextPath}/billboardReply/${bean.billboardid}";
                        },
                        error: function (returndata) {
                            console.log(returndata);
                        }
                    });

                }
                //讀取錯誤 縮小img
                function errorOne(id) {
                    console.log('first image load error!');
                    $(".content img").attr("width", "50px");
                }
                //修改留言
                function replyChange(replyid) {
                    $('.showText'+replyid).toggle();
                }

                $(".dialog").dialog({
                    autoOpen: false,
                    position: {
                        at: "right bottom"
                    }

                });
                //刪除留言的留言
                function removeReplyreply(replyid) {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/removeReplyreply/' + replyid,//接受請求的Servlet地址
                        type: 'POST',
                        // data: formdata,
                        // async: false,//同步請求
                        // cache: false,//不快取頁面
                        // contentType: false,//當form以multipart/form-data方式上傳檔案時，需要設定為false
                        // processData: false,//如果要傳送Dom樹資訊或其他不需要轉換的資訊，請設定為false
                        success: function (json) {
                            alert(json);
                            location.href = "${pageContext.request.contextPath}/billboardReply/${bean.billboardid}";
                        },
                        error: function (returndata) {
                            console.log(returndata);
                        }
                    });


                }


            </script>
            <input type="hidden" name="myInput" class="myInput">

        </body>

        </html>
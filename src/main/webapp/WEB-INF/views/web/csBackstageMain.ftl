<html xmlns="" xmlns="http://www.w3.org/1999/html">
<head>
    <meta http-equiv=Content-Type content="text/html;charset=utf-8">
    <!--<meta http-equiv="Content-Language" content="zh-CN" />-->
    <meta name="roots" content="" />
    <meta name="Keywords" content="" />
    <meta name="Description" content="" />
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/csBackstage_style.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/commConfig.js"></script>
    <script type="text/javascript" src="js/config.js"></script>
    <title></title>
    <style>
        #nav {
            width: 450px;
            height: 280px;
            border: 1px solid #D4CD49;
            position: fixed;
            background: #d1dbe5;
            font-size: 16px;
            text-align: center;
            left: 30%;
            top: 30%;}
    </style>
    <script>

        function btnClick(_this) {

            var accountId = $("#accountId").val();//账号Id
            var gameLanguage = $("#gameLanguage").val();
            var aqId = _this.getAttribute("aqId_data");
            $.ajax({
                url: "../csBackstage_aqFlag",
                data: "gameLanguage="+gameLanguage+"&accountId="+accountId+"&aqId="+aqId,
                cache: false
            }).done(function( jsonData ) {
                var jsonTemp = JSON.parse(jsonData);
                //console.log(jsonData);
                if (1000 == jsonTemp.code){
                    var aqUniqueId = _this.getAttribute("aqId_data");
                    var playerUserId = _this.getAttribute("userId_data");//玩家账号
                    var gameCode = _this.getAttribute("gameCode_data");

                    var accountId = $("#accountId").val();//账号Id
                    var gameLanguage = $("#gameLanguage").val();

                    window.location.href="web/csBackstageChat.html?aqUniqueId="+aqUniqueId+"&playerUserId="+playerUserId
                            +"&gameCode="+gameCode+"&userId="+accountId+"&gameLanguage="+gameLanguage;
                }else{
                    var r=confirm(_languageSelect(gameLanguage, "refreshInterface"));
                    if (r==true){
                        location.reload(true);
                    }
                }
            });


        }

        function btnClick_endChat(data) {
            var accountId = $("#accountId").val();//账号Id
            var gameLanguage = $("#gameLanguage").val();
            $.ajax({
                url: "../csBackstage_endDetail",
                data: "gameLanguage="+gameLanguage+"&accountId="+accountId,
                cache: false
            }).done(function( html ) {
                $( "#contentAQEnd" ).append(html);
                $("#btn_end_chat").css("display","none");
                $( "#contentDivId").css("display","none");
            });
        }

        function message_touch(_this){
            var message = _this.getAttribute("data-message");
            $("#nav").css("display","inline");
            $("#nav span").remove();
            $("#nav").append("<span>"+message+"</span>")
        }

        function message_out() {
            $("#nav").css("display","none");
            $("#nav span").remove();
        }
    </script>
</head>
<body>

<div id="nav" style="display: none;"></div>

<div id="btn_end_chat">
    <button style="color:red; margin: 10 auto;" type="button" onclick="javascript:btnClick_endChat(this);">已关闭问题列表</button>
</div>

<div class="content" id="contentAQEnd" style="overflow-y:hidden;">

</div>

<div class="content" id="contentDivId" style="overflow-y:hidden;">
    <input type="hidden" id="accountId" name="accountId" value="${accountId!''}">
    <input type="hidden" id="gameLanguage" name="gameLanguage" value="${gameLanguage!''}">
    <div class="chart_box">
        <table id="table" class="table_l" width="1280px">
            <thead>
            <tr class="table_title_r column_select">
                <td class="ALL" width="15px;">序号</td>
                <td class="ALL" width="15px;" style="display: none">aqId</td>
                <td class="ALL" width="25px;" style="display: none">游戏标识</td>
                <td class="ALL" width="25px;" style="display: none">包名</td>
                <td class="ALL" width="15px;" >用户Id</td>
                <td class="ALL" width="15px;" >游戏名称</td>
                <td class="ALL" width="15px;" >消息内容</td>
                <td class="ALL" width="15px;" >信息所属人</td>
                <td class="ALL" width="25px;" style="display: none">创建时间</td>
                <td class="ALL" width="25px;">更新时间</td>
                <td class="ALL" width="25px;">详情</td>
            </tr>

            <#list csAskQuestionsList as csAskQuestions>
                <tr id=${csAskQuestions.id} class="table_title_r column_select">
                    <td name="id" id="id"  class="ALL" width="80px;" >${csAskQuestions_index+1}</td>
                    <td name="aqId" id="aqId"  class="ALL" width="80px;" style="display: none">${csAskQuestions.id!''}</td>
                    <td name="gameCode" id="gameCode"  class="ALL" width="80px;" style="display: none">${csAskQuestions.gameCode!''}</td>
                    <td name="packageName" id="packageName"  class="ALL" width="80px;" style="display: none">${csAskQuestions.packageName!''}</td>
                    <td name="userId" id="userId"  class="ALL" width="80px;">${csAskQuestions.userId?c!''}</td>
                    <td name="gameName" id="gameName"  class="ALL" width="80px;">${csAskQuestions.gameName!''}</td>
                    <td name="message" id="message"  class="ALL" width="80px;">
                        <#if  (csAskQuestions.message?length>15)>
                            <span>${csAskQuestions.message[0..15]?replace("/<br>/g","","ri")?default("")}...<span>
                        <#else>
                            ${csAskQuestions.message!''}
                        </#if>
                    </td>
                    <td name="senderType" id="senderType"  class="ALL" width="80px;">${csAskQuestions.senderId!''}</td>
                    <td name="createdTime" id="createdTime"  class="ALL" width="80px;" style="display: none">
                        ${(csAskQuestions.createdTime*1000)?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}
                    </td>
                    <td name="modifiedTime" id="modifiedTime"  class="ALL" width="80px;">
                        ${(csAskQuestions.modifiedTime*1000)?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}
                    </td>
                    <td name="btn_td" id="btn_td"  class="ALL" width="80px;">
                        <button type="button" onclick="javascript:btnClick(this);" aqId_data="${csAskQuestions.id?c!''}" userId_data="${csAskQuestions.userId?c!''}" gameCode_data="${csAskQuestions.gameCode!''}">回复</button>
                    </td>
                </tr>
            </#list>

            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
</div>
</body>
</html>
<html xmlns="" xmlns="http://www.w3.org/1999/html">
<head>
    <meta http-equiv=Content-Type content="text/html;charset=utf-8">
    <!--<meta http-equiv="Content-Language" content="zh-CN" />-->
    <meta name="roots" content="" />
    <meta name="Keywords" content="" />
    <meta name="Description" content="" />
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/config.js"></script>
    <title></title>
    <style type="text/css">
        body {
            color: #787878;
            font-size: 13px;
            font-family: "微软雅黑";
            background: #fff;
            padding-top: 1px;
            text-align:center;
        }

        #Tab{
            overflow: hidden;
            zoom: 1;
            margin: 0;
            padding: 0;
            width: 820px;
            height:552px;
        }

        .Menubox {
            float: left;
        }

        .main_menu{
            float: right;
            width: 45px;
        }

        .Contentbox{
            float: right;
            border-left: 1px solid #c7c7c7;
            width: 745px;
            height: auto !important;
            height: 500px;
            min-height: 500px;
        }

        #con_menu_1{
            float: left;
            width: 660px;
        }


        .questions-top {
            float: left;
            margin-left: 40px;
            margin-bottom: 20px;
            display: inline;
            width: 580px;
        }
        .questions-top li {
            float: left;
            margin-top: 25px;
            width: 100%;
        }
        .left-q {
            float: left;
            width: 280px;
        }
        .right-q {
            float: right;
            width: 280px;
            font-size: 18px;
            color: #484848;
        }
        .q1 {
            float: left;
            width: 110px;
            text-align: right;
            line-height: 24px;
            font-size: 18px;
            color: #484848;
        }

        .q3 {
            float: right;
            width: 430px;
        }

        .q4 {
            float: left;
            width: 400px;
            margin: 10px auto auto 10px;
        }

        .textarea-in {
            float: left;
            padding: 5px;
            resize: none;
            outline: none;
            border-radius: 4px;
            border: 1px solid #cfcfcf;
            -webkit-box-sizing: border-box;
            width: 100%;
            height: 120px;
            font-size: 14px;
            color: #8c8c8c;
        }
        .related-list {
            float: left;
            width: 300px;
        }
        .related-list li {
            float: left;
            margin-top: 10px;
            width: 100%;
            line-height: 24px;
            font-size: 16px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
        .related-list li a {
            color: #35addf;
        }
        .photo-upload {
            float: right;
            margin-top: 10px;
            width: 140px;
        }
        .photo {
            float: left;
            position: relative;
            margin-left: 13px;
            display: inline;
            border: 2px solid #e8e8e8;
            border-radius: 4px;
            width: 106px;
            height: 70px;
        }
        .photo p {
            text-align: center;
            line-height: 70px;
            color: #7a7a7a;
        }
        .photo img {
            position: absolute;
            left: 0;
            top: 0;
            width: 106px;
            height: 70px;
        }
        .upload {
            float: left;
            position: relative;
            margin-left: 13px;
            display: inline;
            margin-top: 5px;
            margin-bottom: 10px;
            border-radius: 6px;
            width: 110px;
            height: 26px;
            line-height: 26px;
            text-align: center;
            background: #5fd0ff;
            color: #fff;
        }
        .btn-add {
            float: left;
            border: none;
            border-radius: 6px;
            width: 100%;
            height: 30px;
            line-height: 30px;
            font-size: 18px;
            background: #5fd0ff;
            color: #fff;
        }
        .file-up {
            position: absolute;
            top: 0;
            right: 0;
            width: 100%;
            height: 30px;
            filter:alpha(opacity:0);
            opacity: 0;
            cursor: pointer;
        }
        .upload-tips {
            float: left;
            width: 100%;
            text-align: center;
            font-size: 16px;
            color: #848484;
        }
        .input-box {
            float: left;
            position: relative;
            border: 1px solid #d5d5d5;
            border-radius: 8px;
            width: 300px;
            height: 28px;
        }
        .area {
            float: left;
            position: relative;
            margin-left: 5px;
            border-right: 1px solid #d5d5d5;
            display: inline;
            width: 100px;
            height: 28px;
            line-height: 28px;
        }
        .select-area {
            float: left;
            outline: none;
            border: none;
            width: 100%;
            height: 28px;
            line-height: 28px;
            direction: ltr;
            appearance: none;
            -moz-appearance: none;
            -webkit-appearance: none;
            font-size: 15px;
            color: #6b6b6b;
        }
        .select-area::-ms-expand {
            display: none;
        }
        .input-text1 {
            float: left;
            margin-left: 10px;
            display: inline;
            border: none;
            outline: none;
            width: 150px;
            height: 28px;
            line-height: 28px;
            font-size: 14px;
            color: #8c8c8c;
        }
        .input-text2 {
            float: left;
            margin-left: 10px;
            display: inline;
            border: none;
            outline: none;
            width: 260px;
            height: 28px;
            line-height: 28px;
            font-size: 14px;
            color: #8c8c8c;
        }
        .q-tips {
            float: left;
            width: 300px;
            font-size: 16px;
            text-align: center;
            color: #868686;
        }

        .service-tit{
            float: left;
            position: relative;
            margin-left: 55px;
            padding-bottom: 10px;
            display: inline;
            border-bottom: 1px solid #e5e5e5;
            width: 550px;
            text-align: center;
            font-size: 24px;
            font-weight: 700;
            color: #01b8ef;
        }










        a:link, a:visited {
            font-size: 12px;
            color: #666;
            text-decoration: none;
        }

        a:hover {
            color: #ff0000;
            text-decoration: underline;
        }



        .Menubox ul {
            list-style: none;
            margin: 0;
            padding: 0;
            height: 100%;
        }

        .Menubox ul li {
            line-height: 100%;
            display: block;
            cursor: pointer;
            width: 20px;
            text-align: center;
            color: #0c0c0c;
            font-weight: bold;
            max-width:0;
            padding: 0;
            height: 184px;
            font-size: 18px;
        }

        .Menubox .li-tit{
            text-align: center;
            margin: 10px auto;
        }

        .Menubox ul li.hover {
            background: #fff;
            color: red;
            text-align: center;
            font-size: 18px;
            font-weight: 700;
            margin: 10px auto;
            width: 45px;
        }


        .btn-confirm{
            margin: 0 auto;
            border-radius: 8px;
            display: block;
            overflow: hidden;
            width: 142px;
            height: 36px;
            line-height: 36px;
            text-align: center;
            font-size: 20px;
            background: #4fd2c2;
        }

        .btn-confirm-send{
            margin: 10px auto auto 10px;
            border-radius: 8px;
            display: block;
            overflow: hidden;
            width: 142px;
            height: 36px;
            line-height: 36px;
            text-align: center;
            font-size: 20px;
            background: #4fd2c2;
        }

        .btn-confirm-complete{
            margin: 10px auto auto 10px;
            border-radius: 8px;
            display: block;
            overflow: hidden;
            width: 142px;
            height: 36px;
            line-height: 36px;
            text-align: center;
            font-size: 20px;
            background: #4fd2c2;
        }

        .contact-box{
            float: left;
            margin-right: 4px;
            display: inline;
            padding: 20px;
            margin-top: 20px;
            border-radius: 10px;
            width: 745px;
            height: 300px;
            font-size: 16px;
        }

        .service-h3{
            padding-bottom: 10px;
            font-size: 20px;
            color: #01b8ef;
        }

        .p_btn {
            float: left;
            margin: 10px auto;
        }

    </style>
    <script>
        /**
         * tab切换
         **/
        function setTab(name,cursel,n){
            for(i=1;i<=n;i++){
                var menu=document.getElementById(name+i);
                var con=document.getElementById("con_"+name+"_"+i);
                menu.className=i==cursel?"hover li-tit":"li-tit";
                con.style.display=i==cursel?"block":"none";
                if (i==2){
                    autoScrollTop();
                }
            }
        }

        /**
         * 自己发送消息添加到面版
         **/
        function setMyselefMessage(obj, msg) {
            $(obj).append(msg);
        }

        /**
         * 滚动条拉到最后
         **/
        function autoScrollTop() {
            $(".chat-thread").scrollTop(10000000000000000000);
        }


        var Sock = function () {
            var socket;
            if (!window.WebSocket) {
                window.WebSocket = window.MozWebSocket;
            }

            if (window.WebSocket) {
                var userId = $("#userId").val();
                var roleName = $("#roleName").val();
                //socket = new WebSocket("ws://10.10.10.200:26789/websocket?{\"userId\":\""+userId+"\",\"roleName\":\""+roleName+"\",\"senderType\":\"player\"}");//
                socket = new WebSocket("ws://imcs.starb168.com:26789/websocket?{\"userId\":\""+userId+"\",\"roleName\":\""+roleName+"\",\"senderType\":\"player\"}");//
                socket.onopen = onopen;
                socket.onmessage = onmessage;
                socket.onclose = onclose;
            } else {
                alert("Your browser does not support Web Socket.");
            }

            function onopen(event) {
                console.log(event.data);
                console.log("Web Socket opened!");

            }

            function onmessage(event) {
                if(typeof(event.data)=="string") {
                    if(event.data.indexOf("{\"code\":1000")>=0){//发送成功，放入自己发送的消息
                        var msgJson = JSON.parse(event.data);
                        var foreignName = msgJson.foreignName;
                        var message = $(".q4 #contentChat").val().replace(/\n/g,"<br/>");
                        var msg = "<li class='li-child-odd' data-attr="+foreignName+">"+message+"</li>";
                        setMyselefMessage("#convo .chat-thread",msg);
                        autoScrollTop();
                    }else if (event.data.indexOf("{\"code\":2003")>=0){//系统异常
                        var data = JSON.parse(event.data);
                        alert(data.message);
                    }else{//回复的消息
                        var msgJson = JSON.parse(event.data);
                        var foreignName = msgJson.foreignName;
                        var msg = "<li class='li-child-even' data-attr="+foreignName+">"+msgJson.message+"</li>";
                        setMyselefMessage("#convo .chat-thread",msg);
                        autoScrollTop();
                    }
                }else{
                    console.log("don't string")

                }
            }

            function onclose(event) {
                console.log(event.data);
                console.log("Web Socket closed");
            }

            function send(event) {
                event.preventDefault();
                if (window.WebSocket) {
                    if (socket.readyState == WebSocket.OPEN) {
                        var message = $(".q4 #contentChat").val().replace(/\n/g,"<br/>");
                        //var msg = "<li class='li-child-odd'>"+message+"</li>";
                        //setMyselefMessage("#convo .chat-thread",msg);
                        //autoScrollTop();
                        var gameLanguage = $("#gameLanguage").val();
                        if (''== gameLanguage){
                            gameLanguage = lang_en_US;
                        }

                        if(''==message){
                            alert(_languageSelect(gameLanguage, "paramsException"));
                            return;
                        }
                        var aqUniqueId = $("#aqUniqueId").val();
                        if(''==aqUniqueId){
                            alert(_languageSelect(gameLanguage, "aqUniqueIdIsNull"));
                            return;
                        }
                        var gameCode = $("#gameCode").val();
                        var sendMessage = "{\"message\":\""+message + "\",\"gameCode\":\""+gameCode+ "\",\"aqUniqueId\":\""+aqUniqueId+"\",\"gameLanguage\":\""
                                +gameLanguage+"\"}";
                        socket.send(sendMessage);
                    } else {
                        alert("The socket is not open.");
                    }
                }
            }

            $(".p_btn #sub_send").click(send);
        }

        $(function () {
            new Sock();


            var gameLanguage = $("#gameLanguage").val();
            if (''== gameLanguage){
                gameLanguage = lang_en_US;
            }
            var aqUniqueId = $("#aqUniqueId").val();
            var userId = $("#userId").val();
            var gameCode = $("#gameCode").val();
            $.ajax({
                url: "../csChat_message",
                data: "gameLanguage="+gameLanguage+"&aqId="+aqUniqueId+"&userId="+userId+"&gameCode="+gameCode,
                cache: false
            }).done(function( html ) {
                console.log(html);
                $( "#con_menu_2 #override_div" ).append(html );
            });

            $('#sub').click(function () {
                var ip = $("#ip").val();
                var gameCode = $("#gameCode").val();
                var packageName = $("#packageName").val();
                var serverCode = $("#serverCode").val();
                var userId = $("#userId").val();
                var uniqueId = $("#uniqueId").val();
                var mac = $("#mac").val();
                var imei = $("#imei").val();
                var androidId = $("#androidId").val();
                var adid = $("#adid").val();
                var idfa = $("#idfa").val();
                var uuid = $("#uuid").val();
                var roleName = $("#roleName").val();
                var roleLevel = $("#roleLevel").val();
                var roleId = $("#roleId").val();
                var operatingSystem = $("#operatingSystem").val();
                var deviceType = $("#deviceType").val();
                var gameLanguage = $("#gameLanguage").val();
                var accessToken = $("#accessToken").val();
                var loginTimestamp = $("#loginTimestamp").val();

                var message =  $(".q3 #content").val().replace(/\n/g, "<br/>");
                var tel = $('#select-area').val();
                tel = tel.substring(tel.lastIndexOf("+"));

                var params = "ip="+ip+"&gameCode="+gameCode+"&packageName="+packageName+"&serverCode="+serverCode
                        +"&userId="+userId+"&uniqueId="+uniqueId+"&mac="+mac+"&imei="+imei+"&androidId="+androidId
                        +"&adid="+adid+"&idfa="+idfa+"&uuid="+uuid+"&uuid="+uuid+"&roleName=" +roleName
                        +"&roleLevel="+roleLevel+"&roleId="+roleId+"&operatingSystem="+operatingSystem
                        +"&deviceType="+deviceType
                        +"&gameLanguage="+gameLanguage+"&accessToken="+accessToken
                        +"&loginTimestamp="+loginTimestamp+"&message="+message+"&tel="+tel;

                $.ajax({
                    url: "../player_askQuestions",
                    data: params,
                    cache: false
                }).done(function(e) {
                    console.log(e);

                    var json = JSON.parse(e);

                    if(1000 == json.code) {
                        var msg = "<li class='li-child-odd'>" + $(".q3 #content").val().replace(/\n/g, "<br/>") + "</li>";
                        setMyselefMessage("#convo .chat-thread", msg);
                        autoScrollTop();

                        $("#aqUniqueId").val(json.aqUniqueId);
                    }

                    alert(json.message);

                });

                /*document.getElementById("question_form").submit(function (e) {
                    alert("Submitted");
                    console.log(e);

                    var msg = "<li class='li-child-odd'>" + $(".q3 #content").val().replace(/\n/g, "<br/>") + "</li>";
                    setMyselefMessage("#convo .chat-thread", msg);
                    autoScrollTop();
                });*/
            });

        });
        //-->
    </script>
</head>
<body>
<div id="Tab">
    <div class="Menubox">
        <div class="main_menu">
            <ul>
                <li id="menu1" class="li-tit hover" onclick="setTab('menu',1,3)"><span></br></br>提问</span></li>
                <li id="menu2" class="li-tit" onclick="setTab('menu',2,3)" >客服回复</li>
                <li id="menu3" class="li-tit" onclick="setTab('menu',3,3)" >电话联系</li>
            </ul>
        </div>
    </div>
    <div class="Contentbox">

        <div id="con_menu_1" class="hover">
            <div class="service-tit">提交問題</div>
            <div class="questions-top">
                <form method="post" id="question_form" action="">
                    <ul>
                        <li>
                            <div class="q1">描述：</div>
                            <div class="q3">
                                <textarea id="content" name="content" class="textarea-in" autofocus="autofocus" placeholder=""></textarea>
                                <div class="related-list">
                                    <ul id="search_result">

                                    </ul>
                                </div>
                                <div class="photo-upload">
                                    <div class="photo">
                                        <div id="preview">

                                        </div>
                                        <img src="../images/pic1.png" alt="" style="display:none"> </div>
                                    <div class="upload">
                                        <input type="button" class="btn-add" value="上傳">
                                        <input type="file" class="file-up" size="28" name="uploadFile" id="uploadFile">
                                    </div>
                                    <span class="upload-tips">總體大小不超過1M</span>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="q1">聯絡電話：</div>
                            <div class="q3">
                                <div class="input-box">
                                    <div class="area">
                                        <select class="select-area" id="select-area">
                                            <option areacode="886" data-pattern="^0{0,1}[6,7,9](?:\d{7}|\d{8}|\d{10})$">台灣 +886</option>
                                            <option areacode="852" data-pattern="^0{0,1}[1,5,6,9](?:\d{7}|\d{8}|\d{12})$">香港 +852</option>
                                            <option areacode="853" data-pattern="^6\d{7}$">澳門 +853</option>
                                            <option areacode="86" data-pattern="^1\d{10}$">中國大陸 +86</option>
                                            <option areacode="1" data-pattern="^\d{10,12}$">美國 +1</option>
                                            <option areacode="81" data-pattern="^0{0,1}[7,8,9](?:\d{8}|\d{9})$">日本 +81</option>
                                            <option areacode="82" data-pattern="^0{0,1}[7,1](?:\d{8}|\d{9})$">韓國 +82</option>
                                            <option areacode="65" data-pattern="^[13689]\d{6,7}$">新加坡 +65</option>
                                            <option areacode="60" data-pattern="^1\d{8,9}$">馬來西亞 +60</option>
                                        </select>
                                    </div>
                                    <input type="text" id="contactWay" name="contactWay" class="input-text input-text1" value="">
                                    <!--<a class="btn-claer"></a> </div>-->
                            </div>
                        </li>
                        <!--<li>
                            <div class="q1">Email信箱：</div>
                            <div class="q3">
                                <div class="input-box">
                                    <input id="email" name="email" type="text" class="input-text input-text2" value="">
                                    <a class="btn-claer"></a> </div>
                            </div>
                        </li>-->
                        <!--<li>
                            <div class="q1"></div>
                            <div class="q3">
                                <div class="q-tips">*電話/Email信箱必需填一項</div>
                            </div>
                        </li>-->
                    </ul>
                    <#--<input type="hidden" id="aqId" name="aqId" value=${aqId!''}>-->
                    <input type="hidden" id="ip" name="ip" value=${ip!''}>
                    <input type="hidden" id="gameCode" name="gameCode" value=${gameCode!''}>
                    <input type="hidden" id="packageName" name="packageName" value=${packageName!''}>
                    <input type="hidden" id="serverCode" name="serverCode" value=${serverCode!''}>
                    <input type="hidden" id="userId" name="userId" value=${userId!''}>
                    <input type="hidden" id="uniqueId" name="uniqueId" value=${uniqueId!''}>
                    <input type="hidden" id="mac" name="mac" value=${mac!''}>
                    <input type="hidden" id="imei" name="imei" value=${imei!''}>
                    <input type="hidden" id="androidId" name="androidId" value=${androidId!''}>
                    <input type="hidden" id="adid" name="adid" value=${adid!''}>
                    <input type="hidden" id="idfa" name="idfa" value=${idfa!''}>
                    <input type="hidden" id="uuid" name="uuid" value=${uuid!''}>
                    <input type="hidden" id="roleName" name="roleName" value=${roleName!''}>
                    <input type="hidden" id="roleLevel" name="roleLevel" value=${roleLevel!''}>
                    <input type="hidden" id="roleId" name="roleId" value=${roleId!''}>
                    <input type="hidden" id="operatingSystem" name="operatingSystem" value=${operatingSystem!''}>
                    <input type="hidden" id="deviceType" name="deviceType" value=${deviceType!''}>
                    <input type="hidden" id="gameLanguage" name="gameLanguage" value=${gameLanguage!''}>
                    <input type="hidden" id="accessToken" name="accessToken" value=${accessToken!''}>
                    <input type="hidden" id="loginTimestamp" name="loginTimestamp" value=${loginTimestamp!''}>
                    <!--<input type="hidden" id="filePath" name="filePath" value="">-->
                </form>

            </div>
            <a id="sub" class="btn-confirm" title="確認">確認</a>
        </div>

        <div id="con_menu_2" style="display:none">
            <input type="hidden" id="aqUniqueId" name="aqUniqueId" value="${aqId!''}">
            <div id="override_div">
            </div>
            <#--<div id="convo" data-from="Sonu Joshi">
                <ul class="chat-thread" id="chat-thread">
                    <li class="li-child-even">Are we meeting today? </li>
                    <li class="li-child-even">yes, what time suits you?</li>
                    <li class="li-child-odd">I was thinking after lunch, I have a meeting in the morning</li>
                    <li class="li-child-odd">Are we meeting today?</li>
                    <li class="li-child-even">yes, what time suits you?</li>
                    <li class="li-child-even">I was thinking after lunch, I have a meeting in the morning</li>
                    <li class="li-child-odd">Are we meeting today?</li>
                    <li class="li-child-odd">yes, what time suits you?</li>
                    <li class="li-child-even">I was thinking after lunch, I have a meeting in the morning</li>
                    <li class="li-child-even">Are we meeting today?</li>
                    <li class="li-child-odd">yes, what time suits you?</li>
                    <li class="li-child-odd">I was thinking after lunch, I have a meeting in the morning<img src="images/pic1.png"  alt="上海鲜花港 - 郁金香" /></li>
                </ul>
            </div>-->
            <div class="q4">
                <textarea id="contentChat" name="contentChat" class="textarea-in" autofocus="autofocus" placeholder=""></textarea>
            </div>
            <div class="p_btn">
                <a id="sub_send" class="btn-confirm-send" title="追问">追问</a>
                <a id="sub_complete" class="btn-confirm-complete" title="结束">结束</a>
            </div>
        </div>

        <div id="con_menu_3" style="display:none">
            <div class="contact-box">
                <h3 class="service-h3">聯繫客服</h3>
                <ul>
                    <li>Email：kefu099@gmail.com</li>
                </ul>
        </div>

    </div>
</div>
</body>
</html>
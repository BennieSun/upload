<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html>
<head>
    <meta http-equiv=Content-Type content="text/html;charset=utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--<meta http-equiv="Content-Language" content="zh-CN" />-->
    <meta name="roots" content="" />
    <meta name="Keywords" content="" />
    <meta name="Description" content="" />
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/csMain.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/utils.js"></script>
    <script type="text/javascript" src="js/commConfig.js"></script>
    <script type="text/javascript" src="js/config.js"></script>
    <title></title>
    <script>
        /**
         * tab切换
         **/
        function setTab(name,cursel,n){
            for(i=1;i<=n;i++){
                if (cursel==1){
                    var aqId = $('#aqUniqueId').val();
                    var gameLanguage = $('#gameLanguage').val();
                    if(''!=aqId || parseInt(aqId)>0) {
                        alert(_languageSelect(gameLanguage, "aqUniqueIdIsExist"));
                        return;
                    }
                }
                if (cursel==2){
                    var aqId = $('#aqUniqueId').val();
                    var gameLanguage = $('#gameLanguage').val();
                    if(''==aqId || parseInt(aqId)<=0) {
                        alert(_languageSelect(gameLanguage, "aqUniqueIdIsNull"));
                        return;
                    }
                    //setTimeout(autoScrollTop(),10000);
                }

                var menu=document.getElementById(name+i);
                var con=document.getElementById("con_"+name+"_"+i);
                menu.className=i==cursel?"hover li-tit":"li-tit";
                con.style.display=i==cursel?"block":"none";
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
            $(".chat-thread").scrollTop($(".chat-thread")[0].scrollHeight+50);
        }


        var Sock = function () {
            var socket;
            if (!window.WebSocket) {
                window.WebSocket = window.MozWebSocket;
            }

            if (window.WebSocket) {
                var userId = $("#userId").val();
                var roleName = $("#roleName").val();
                socket = new WebSocket("ws://10.10.10.200:26789/websocket?{\"userId\":\""+userId+"\",\"roleName\":\""+roleName+"\",\"senderType\":\"player\"}");//
                //socket = new WebSocket("ws://imcs.starb168.com:26789/websocket?{\"userId\":\""+userId+"\",\"roleName\":\""+roleName+"\",\"senderType\":\"player\"}");//
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
                        //移除发送的消息
                        $(".q4 #contentChat").val("");
                    }else if (event.data.indexOf("{\"code\":1001")>=0){//回复的消息
                        var msgJson = JSON.parse(event.data);
                        var foreignName = msgJson.foreignName;
                        var msg = "<li class='li-child-even' data-attr="+foreignName+">"+msgJson.message+"</li>";
                        setMyselefMessage("#convo .chat-thread",msg);
                        autoScrollTop();
                    }else {
                        var data = JSON.parse(event.data);
                        alert(data.message);
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
                        /*if (''== gameLanguage){
                            gameLanguage = lang_en_US;
                        }*/

                        /*if(''==message){
                            alert(_languageSelect(gameLanguage, "paramsException"));
                            return;
                        }*/

                        if(''==message.replace(/\<br\/\>/g,"").replace(/\s/g,"")){
                            alert(_languageSelect(gameLanguage,"descriptionIsNull"));
                            $(".q4 #contentChat").val('');
                            return;
                        }

                        if($.getByteLength(message.replace('<br/>'," "))>300){
                            alert(_languageSelect(gameLanguage,"descriptionIsToLong"));
                            return;
                        }

                        message = message.replace(/(^\s*)|(\s*$)/g,'');

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
            /*if (''== gameLanguage){
                gameLanguage = lang_en_US;
            }*/
            var aqUniqueId = $("#aqUniqueId").val();
            var userId = $("#userId").val();
            var gameCode = $("#gameCode").val();
            $.ajax({
                url: "../csChat_message",
                data: "gameLanguage="+gameLanguage+"&aqId="+aqUniqueId+"&userId="+userId+"&gameCode="+gameCode,
                cache: false
            }).done(function( html ) {
                console.log(html);
                $( "#con_menu_2 #chat-thread" ).append(html );

                if(''!=aqUniqueId || parseInt(aqUniqueId)>0) {
                    setTab('menu',2,3);
                    autoScrollTop();
                }
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
                var telPrefix = $('#select-area').val();
                telPrefix = telPrefix.substring(telPrefix.lastIndexOf("+"));
                var tel = $('#contactWay').val();
                tel = telPrefix +" "+tel;

                if(tel.replace(/\s/g,"")==telPrefix){
                    tel = "";
                }

                if(''==message.replace(/\<br\/\>/g,"").replace(/\s/g,"")){
                    alert(_languageSelect(gameLanguage,"descriptionIsNull"));
                    $(".q3 #content").val('');
                    return;
                }

                if($.getByteLength(message.replace('<br/>'," "))>300){
                    alert(_languageSelect(gameLanguage,"descriptionIsToLong"));
                    return;
                }

                message = message.replace(/(^\s*)|(\s*$)/g,'');

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
                    //console.log(e);

                    var json = JSON.parse(e);

                    if(1000 == json.code) {
                        $("#aqUniqueId").val(json.aqUniqueId);
                        var foreignName = roleName;
                        var msg = "<li class='li-child-odd' data-attr="+foreignName+">" + $(".q3 #content").val().replace(/\n/g, "<br/>") + "</li>";
                        setMyselefMessage("#convo .chat-thread", msg);

                        setTab('menu',2,3);
                        autoScrollTop();
                    }

                    alert(json.message);

                });


            });

            $('#sub_complete').click(function () {
                var r=confirm(_languageSelect(gameLanguage, "confirmEnd"));
                if (r==true){
                    var aqUniqueId = $("#aqUniqueId").val();
                    $.ajax({
                        url: "../csChat_end",
                        data: "gameLanguage="+gameLanguage+"&aqId="+aqUniqueId+"&userId="+userId+"&gameCode="+gameCode,
                        cache: false
                    }).done(function( data ) {
                        var msgJson = JSON.parse(data);
                        if(1000 == msgJson.code){
                            $('#aqUniqueId').val("");
                            $('#chat-thread').find("li").remove();
                            setTab('menu',1,3);
                            return;
                        }
                        alert(msgJson.message);
                    });
                }

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
                                <#--<div class="photo-upload">
                                    <div class="photo">
                                        <div id="preview">

                                        </div>
                                        <img src="../images/pic1.png" alt="" style="display:none"> </div>
                                    <div class="upload">
                                        <input type="button" class="btn-add" value="上傳">
                                        <input type="file" class="file-up" size="28" name="uploadFile" id="uploadFile">
                                    </div>
                                    <span class="upload-tips">總體大小不超過1M</span>
                                </div>-->
                            </div>
                        </li>
                        <li>
                            <div class="q1">電話：</div>
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
                <div id="convo" data-from="Sonu Joshi">
                    <ul class="chat-thread" id="chat-thread">
                    </ul>
                </div>
            </div>
            <div class="q4">
                <textarea id="contentChat" name="contentChat" class="textarea-in_chat" autofocus="autofocus" placeholder=""></textarea>
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
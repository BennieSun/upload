<div class="chart_box">
    <table id="table" class="table_l" width="1280px">
        <thead>
        <tr class="table_title_r column_select">
            <td class="ALL" width="15px;">序号</td>
            <td class="ALL" width="15px;" style="display: none">aqId</td>
            <td class="ALL" width="25px;" style="display: none">游戏标识</td>
            <td class="ALL" width="25px;" style="display: none">包名</td>
            <td class="ALL" width="15px;">用户Id</td>
            <td class="ALL" width="15px;">游戏名称</td>
            <td class="ALL" width="15px;">消息内容</td>
            <td class="ALL" width="15px;">信息所属人</td>
            <td class="ALL" width="25px;" style="display: none">创建时间</td>
            <td class="ALL" width="25px;">结束时间</td>
            <td class="ALL" width="25px;">详情</td>
        </tr>

        <#list csAskQuestionsList as csAskQuestions>
        <tr id=${csAskQuestions.id} class="table_title_r column_select">
        <td name="id" id="id" class="ALL" width="80px;">${csAskQuestions_index+1}</td>
        <td name="aqId" id="aqId" class="ALL" width="80px;" style="display: none">${csAskQuestions.id!''}</td>
        <td name="gameCode" id="gameCode" class="ALL" width="80px;"
            style="display: none">${csAskQuestions.gameCode!''}</td>
        <td name="packageName" id="packageName" class="ALL" width="80px;"
            style="display: none">${csAskQuestions.packageName!''}</td>
        <td name="userId" id="userId" class="ALL" width="80px;">${csAskQuestions.userId?c!''}</td>
        <td name="gameName" id="gameName" class="ALL" width="80px;">${csAskQuestions.gameName!''}</td>
        <td name="message" id="message" class="ALL" width="80px;" data-message="${csAskQuestions.message!''}"
            onmouseover="javascript:message_touch(this);" onmouseout="javascript:message_out(this);">
            <#if  (csAskQuestions.message?length>15)>
            <span>${csAskQuestions.message[0..15]?replace("/<br>/g","","ri")?default("")}...<span>
            <#else>
            ${csAskQuestions.message!''}
            </#if>
        </td>
        <td name="senderType" id="senderType" class="ALL" width="80px;">${csAskQuestions.senderId!''}</td>
        <td name="createdTime" id="createdTime" class="ALL" width="80px;" style="display: none">
        ${(csAskQuestions.createdTime*1000)?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}
        </td>
        <td name="modifiedTime" id="modifiedTime" class="ALL" width="80px;">
        ${(csAskQuestions.endTime*1000)?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}
        </td>
        <td name="modifiedTime" id="modifiedTime" class="ALL" width="80px;">
            <span style="color: red;">已结束</span>
        </td>
        <#--<td name="btn_td" id="btn_td"  class="ALL" width="80px;">
            <button type="button" onclick="javascript:btnClick(this);" aqId_data="${csAskQuestions.id?c!''}" userId_data="${csAskQuestions.userId?c!''}" gameCode_data="${csAskQuestions.gameCode!''}">回复</button>
        </td>-->
        </tr>
        </#list>

        </thead>
        <tbody>

        </tbody>
    </table>
</div>

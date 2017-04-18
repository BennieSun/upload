<div class="Menubox">
    <table id="table" class="table_l" width="520px">
        <thead>
        <tr class="table_title_r">
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">新消息</td>
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';" colspan="4"><span id="new_message_count" style="color: red">0</span>条</td>
        </tr>
        <tr class="table_title_r">
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">项目名称</td>
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">信息</td>
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">项目名称</td>
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">信息</td>
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">功能操作</td>
        </tr>
        </thead>

        <tbody>
        <tr class="table_title_r">
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">游戏名</td>
            <td class="ALL" width="80px"
                style="text-align: center;font-size: 13px;font-family: '微软雅黑';">${csBsAQChatDetail["gameName"]!''}</td>
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">游戏标识</td>
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">
            ${csBsAQChatDetail["gameCode"]!''}
            </td>
            <td class="ALL" width="80px" style="text-align: center"></td>
        </tr>
        <tr class="table_title_r">
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">账号Id</td>
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">
            ${csBsAQChatDetail["userId"]?c!''}
            </td>
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">注册来源</td>
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">
            ${csBsAQChatDetail["registPlatform"]!''}
            </td>
            <td class="ALL" width="80px" style="text-align: center"></td>
        </tr>
        <tr class="table_title_r">
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">角色名称</td>
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">
            ${csBsAQChatDetail["roleName"]!''}
            </td>
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">角色Id</td>
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">
            ${csBsAQChatDetail["roleId"]!''}
            </td>
            <td class="ALL" width="80px" style="text-align: center"></td>
        </tr>
        <tr class="table_title_r">
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">當前服務器</td>
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';" colspan="3">
            ${csBsAQChatDetail["serverName"]!''}
            </td>
            <td class="ALL" width="80px" style="text-align: center"></td>
        </tr>
        <tr class="table_title_r">
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">總儲值額度</td>
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';" colspan="3">
                <span style="color: red">${csBsAQChatDetail["priceTotal"]!0}</span> 美金
            </td>
            <td class="ALL" width="80px" style="text-align: center">
                <button type="button" style="background-color: #7a7a7a;font-size: 13px;font-family: '微软雅黑'">储值详情
                </button>
            </td>
        </tr>
        <tr class="table_title_r">
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">设备</td>
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">${csBsAQChatDetail["deviceType"]!''}</td>
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">系统版本</td>
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">${csBsAQChatDetail["systemVersion"]!''}</td>
            <td class="ALL" width="80px" style="text-align: center"></td>
        </tr>
        <tr class="table_title_r">
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';">手机号码</td>
            <td class="ALL" width="80px" style="text-align: center;font-size: 13px;font-family: '微软雅黑';" colspan="3">
            ${csBsAQChatDetail["telephone"]!''}
            </td>
            <td class="ALL" width="80px" style="text-align: center"></button>
            </td>
        </tr>
        </tbody>

    </table>
</div>
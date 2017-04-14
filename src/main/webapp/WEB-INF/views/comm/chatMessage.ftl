<#if senderType == "player">
    <div id="convo" data-from="Sonu Joshi">
        <ul class="chat-thread" id="chat-thread">
            <#list csChatDetailList as csChatDetail>
                <#if csChatDetail.senderType==1>
                    <li class="li-child-odd" data-attr="${csChatDetail.senderName}">${csChatDetail.message}</li>
                </#if>
                <#if csChatDetail.senderType==2>
                    <li class="li-child-even" data-attr="${csChatDetail.senderName}">${csChatDetail.message}</li>
                </#if>
            </#list>
        </ul>
    </div>
</#if>

<#if senderType == "cs">
    <div id="convo" data-from="Sonu Joshi">
        <ul class="chat-thread" id="chat-thread">
            <#list csChatDetailList as csChatDetail>
                <#if csChatDetail.senderType==1>
                    <li class="li-child-even" data-attr="${csChatDetail.senderName}">${csChatDetail.message}</li>
                </#if>
                <#if csChatDetail.senderType==2>
                    <li class="li-child-odd" data-attr="${csChatDetail.senderName}">${csChatDetail.message}</li>
                </#if>
            </#list>
        </ul>
    </div>
</#if>
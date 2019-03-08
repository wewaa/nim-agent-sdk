package com.company;

import com.gemteks.nim.sdkinterface.FreePPAgentDelegate;

public class FreePPDelegateImpl implements FreePPAgentDelegate {

    // 用来接收SDK层传上来的消息
    @Override
    public void OnRecieveChatApiMsg(String Msg) {
        System.out.println("OnRecieveChatApiMsg callback "+Msg);
        Main.agentInterfaceImpl.sendChatCmd(Msg);
    }

    @Override
    public void onReceiveRuleApiMsg(String Msg){

    }

    @Override
    public void onReceiveRuleDynamicApiMsg(String Msg){

    }

    @Override
    public void onReceiveHttpResponse(String apiName,int respCode,String respJson){

    }
}

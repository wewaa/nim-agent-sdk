package com.company;

import com.gemteks.nim.sdkinterface.JoinmeAgentDelegate;

public class JoinMeDelegateImpl implements JoinmeAgentDelegate {

    // 用来接收SDK层传上来的消息
    @Override
    public void OnRecieveMsg(String Msg) {
        System.out.println("callback "+Msg);
        Main.agentInterfaceImpl.sendChatCmd(Msg);
    }
}

package com.company;

import com.gemteks.nim.sdkinterface.JoinmeAgentInterfaceImpl;

public class Main {

    public static JoinmeAgentInterfaceImpl agentInterfaceImpl = null;

    public static void main(String[] args) {
	// write your code here

        JoinMeDelegateImpl delegateImpl  = new JoinMeDelegateImpl();// 代理类
        agentInterfaceImpl = new JoinmeAgentInterfaceImpl(delegateImpl);

        agentInterfaceImpl.init("robottest","d5f6a3b7-5a91-4a95-9dca-9636f03d0ec2","BhNhzUEHTBGucAvNUeYlFzvBJZcaBSKttwtKkuJl", "c21f969b-5f03-433d-95e0-4f8f136e7682", "97b36bff-96ee-419d-9736-8b98ed2b603a","xZfLTnrwqy99yS/vzD1rDm94n3GeedOAMCDolS1jLjs=");
    }
}

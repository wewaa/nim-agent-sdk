package com.company;

import com.gemteks.nim.sdkinterface.FreePPAgentInterfaceImpl;

public class Main {

    public static FreePPAgentInterfaceImpl agentInterfaceImpl = null;

    public static void main(String[] args) {
	// write your code here

        FreePPDelegateImpl delegateImpl  = new FreePPDelegateImpl();// 代理类
        agentInterfaceImpl = new FreePPAgentInterfaceImpl(delegateImpl);

        agentInterfaceImpl.init("highway","180c1298-abe8-48ff-9d3f-e30a675edf5b","ApkiIXobKNxEYPKrFOslNoRUfnmPnTRoVHHbWdBu", "c21f969b-5f03-433d-95e0-4f8f136e7682",
                "9abae42e-28a9-418f-977d-46c73dbf4042","akf6WCkeA08YcDgXfDaBcmzzNBXwrGLnnv8xeKGl",8090);
    }
}

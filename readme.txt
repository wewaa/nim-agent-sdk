﻿
此工程是一个Java程序调用第三方Agent SDK jar包的demo程序

jar包位于工程目录lib下

一、jar包的简单使用流程如Main函数中所示：
1.需要引入包 import com.gemteks.nim.sdkinterface.FreePPAgentInterfaceImpl;
2.创建对象FreePPDelegateImpl的实例，此对象实例需要传入SDK接口实现层，以便SDK接口实现层回调给Java层；
3.创建对象FreePPAgentInterfaceImpl的实例，用此对象实例来调用SDK层接口；

二、SDK相关接口调用说明：
1.在调用SDK相关接口之前，需要先创建好Application,Agent,以拿到init接口所需要的相关信息；
2.第三方开发者在调用功能性接口之前，需要首先调用init接口告知SDK当前Agent相关信息；
3.SDK中封装的接口除了init接口，其他的接口返回值均是由对应的异步回调函数异步回调给第三方开发者；
4.对于第三方开发者而言，在调用了init接口之后，需要根据自己的需求进行setMenu和setQuickCmd操作；


三、SDK提供以下接口：
1.SDK使用的初始化接口
 /*
    agentName: agent的名字
    appId：developer web上申请的application得到的appid
    appKey：developer web上申请的application得到的appKey
    domainId：一般会在developer web上显示为默认值
    agentId：创建agent后得到agentId
    accessToken:创建agent后得到的accessToken
    restPort：第三方开发者开发的java程序即将部署的服务器所占用的端口号
 */
 def init(agentName:String,appId: String, appKey : String, domainId : String, agentId : String, accessToken : String,restPort:Int) : Boolean
 appName:
2.回复ChatApi发来的消息
  /*
  Request sample:
  Follow Event:
  {"events":
  [
  {"type":"Follow",
  "replyToken":"WH1gWCkdjCs96pcvHq2wfnd16hKw0OCgXOjOyVNb",
  "timestamp":1513574717848,
  "source":
  {"type":"ProfileRobot",
  "pid":"13a67959-7f80-44e3-8518-c9209f8497a7",
  "rid":"08019765-4755-42bf-8df8-bddcbddc3032"}}]}
  */
  def sendChatCmd(msg: String)
3.设置Agent的menu
  /*
  Request:
  {
    "locale":"zh_TW",
    "menu":[
      {
        "type":"Menu",
        "title":"群发助手",
        "actions":[
          {
            "type":"Macro",
            "label":"群发助手",
            "macro":{
              "type":"BroadCast",
              "value":{
                "data":"This is option BroadCast string"
              }
            }
          }
        ]
      }
    ]
  }
  */
  def setMenu(menuString:String)
4.设置Agent的快捷命令
  /*
  Request:
  {
    "locale": "zh_TW",
    "shortCut": [
      {
        "type":"Macro",
        "label":"直播",
        "macro":{
          "type":"LiveStream",
          "value":{
            "url":"This is option live stream string maybe empty"
          }
        }
      },
      {
        "type":"Macro",
        "label":"拍照",
        "macro":{
          "type":"PropCommand",
          "value":{
            "compId":1,
            "prop":"Snapshot",
            "op":{
              "type":"Button.Click",
              "value":{

              }
            }
          }
        }
      }
    ],
    "commands":[
      {
        "type": "Command",
        "label": "/snapshot",
        "description": "description"
      },
      {
        "type": "Command",
        "label": "/livestream",
        "description": "description"
      }
    ]
  }
  */
  def setQuickCmd(quickcmdString:String)
5./* get oauth token Basic Auth
  Request:
 {
   "domain_id": "c21f969b-5f03-433d-95e0-4f8f136e7682",
   "client_id":"5e2585c7-9c67-4749-9d6b-7aeeb671deab",// Application appId
   "agent_id": "de10082b-3250-48f1-97de-dd0222817ce0",
   "grant_type": "authorization_code",
   "redirect_uri": "http://xacluster-476176176.us-east-1.elb.amazonaws.com:8080/oauth/code",
    "code":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MjUzMzQyMzUsImlhdCI6MTUyNTMzMzYzNiwiZG1JZCI6ImMyMWY5NjliLTVmMDMtNDMzZC05NWUwLTRmOGYxMzZlNzY4MiIsImdyYW50SWQiOiJ2dURHb0taYW12dURUejRVSFc4SEtoSE56RlNPMVhIS0R3ZmphUko5dmptbFhhbEtGajZ0ZVptcmhXSEZUMGFMIn0.rqe9z4mATBQN-v6HcAmKB0mj4RnoCXq8j3MGztJVoYY"
 }
  Response:
 {
    "token_type": "Bearer",
    "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MTA2NTEyMTksImlhdCI6MTUxMDY0NzYyMCwKICAiZG1JZCI6ICJjMjFmOTY5Yi01ZjAzLTQzM2QtOTVlMC00ZjhmMTM2ZTc2ODIiLAogICJhcHBJZCI6ICIxNGJkOGE4ZC1kMDJkLTQ5N2EtOWRmNy03MzQ0NzljOGNmMTIiLAogICJ0b2tlbklkIjogImh3WkxVc1ZjS0U0TjVMVWYwUHFBcFhkejZaTnpmZmdubXpGb1dXYmZ0M0l0dVZ3YmZPS0FIbExGNW9lUVhJcFoiCn0.md7wOkUEsIwTLa4lZsmms3lbQOvcTY4P0vCIvgtVig0",
    "expires_in": 3599,
    "refresh_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MTA2NDc2MTksImRtSWQiOiJjMjFmOTY5Yi01ZjAzLTQzM2QtOTVlMC00ZjhmMTM2ZTc2ODIiLCJhcHBJZCI6IjE0YmQ4YThkLWQwMmQtNDk3YS05ZGY3LTczNDQ3OWM4Y2YxMiIsImF1dGhJZCI6IjhPajN4QnhvZmlwc1ZQcFlhSHJ2SDVpUUVIbGR3NlNTZ3pqWjdLMjVCZENyaGdoQWlRTW1GYUp6d3ppdUNRWEMifQ.9TDwPnZFoHbs2ReaCT6Wtwk8o4-ckb9P61zPl6I4nkA"
 }
  */
  def getOauthToken(getTokenStr:String)

6./* refresh token  Basic Auth
  Request:
{
  "domain_id": "c21f969b-5f03-433d-95e0-4f8f136e7682",
  "client_id":"5e2585c7-9c67-4749-9d6b-7aeeb671deab",
  "agent_id": "de10082b-3250-48f1-97de-dd0222817ce0",
  "grant_type": "refresh_token",
  "refresh_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MjUzMzc3NDksImRtSWQiOiJjMjFmOTY5Yi01ZjAzLTQzM2QtOTVlMC00ZjhmMTM2ZTc2ODIiLCJhcHBJZCI6IjVlMjU4NWM3LTljNjctNDc0OS05ZDZiLTdhZWViNjcxZGVhYiIsImF1dGhJZCI6InlXSTFFOG5MaGN0SkM3THVHRVNIWlVXTVZ1R1BKRkRsMEd2RVgzRmtjUUZoT2V5azZjMTBIaXI3Y0ZhRUZDMU8ifQ.0hB30G0HmLse6PZhn6Isv-3YdBKXM2GfP_MiPkxRQSU"
}
Response:
{
    "token_type": "Bearer",
    "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MTA2NTEyMTksImlhdCI6MTUxMDY0NzYyMCwKICAiZG1JZCI6ICJjMjFmOTY5Yi01ZjAzLTQzM2QtOTVlMC00ZjhmMTM2ZTc2ODIiLAogICJhcHBJZCI6ICIxNGJkOGE4ZC1kMDJkLTQ5N2EtOWRmNy03MzQ0NzljOGNmMTIiLAogICJ0b2tlbklkIjogImh3WkxVc1ZjS0U0TjVMVWYwUHFBcFhkejZaTnpmZmdubXpGb1dXYmZ0M0l0dVZ3YmZPS0FIbExGNW9lUVhJcFoiCn0.md7wOkUEsIwTLa4lZsmms3lbQOvcTY4P0vCIvgtVig0",
    "expires_in": 3599,
    "refresh_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MTA2NDc2MTksImRtSWQiOiJjMjFmOTY5Yi01ZjAzLTQzM2QtOTVlMC00ZjhmMTM2ZTc2ODIiLCJhcHBJZCI6IjE0YmQ4YThkLWQwMmQtNDk3YS05ZGY3LTczNDQ3OWM4Y2YxMiIsImF1dGhJZCI6IjhPajN4QnhvZmlwc1ZQcFlhSHJ2SDVpUUVIbGR3NlNTZ3pqWjdLMjVCZENyaGdoQWlRTW1GYUp6d3ppdUNRWEMifQ.9TDwPnZFoHbs2ReaCT6Wtwk8o4-ckb9P61zPl6I4nkA"
}

  * */
  def refreshToken(refreshTokenStr:String)
 7. /* RuleApi action执行完成的回复接口
   {
     actId:"",
     status:""
   }
    */
   def sendRuleCmd(ruleCmdStr:String)
 8.  /*
      Agent获取用户的profile信息
      Response body example
    {
        "name": "FreePP Test",
        "pid": "Uxxxxxxxxxxxxxx...",
        "avatar": "https://abc.com/...",
        "locale": "zh_TW"
    }
      * */
      def getProfile(oauthToken:String)


四、SDK提供以下回调函数：
  /*
1.  接收到ChatApi消息时，回调给第三方
  * */
  def OnRecieveChatApiMsg(ChatApiMsg:String)

2.  // 接收 Rule Api action消息，回调给第三方
  sample：
  {
    actionFields: {
    	message: 'hello world' // user message
    },
    user: {
    	jmGid: '', // freepp group id
    	jmPid: '', // freepp profile id
    },
    actId: ''
  }
  def onReceiveRuleApiMsg(RuleApiMsg:String)

3.  // 接收 RuleDynamic Api消息，回调给第三方
  sample：
  {
    user: {
    	jmGid: '',
    	jmPid: '',
    }
  }
  def onReceiveRuleDynamicApiMsg(RuleDynamicApiMsg:String)

  /*
4.  提供给第三方的接口返回数据：

  apiName包含:
  sendChatCmd
  sendRuleCmd
  setMenu
  setQuickCmd
  getOauthToken
  refreshToken
  getProfile

  respCode:
  0:表示成功
  其他http状态码：表示失败

  respJson：
  指返回的json串
  * */
  def onReceiveHttpResponse(apiName:String,respCode:Int,respJson:String)

五、Agent sdk接收消息路由
   Agent sdk接收消息的地址示例：http://serverIP:restPort/middlebot/3rd/v1/chatevent
   Header要求带上X-FreePP-Signature，值为SDKServer.hmac256(SDKServer.appKey,req.toJson.toString())

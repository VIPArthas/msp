package com.wh.util;

import com.wh.constants.Constants;
import com.wh.message.resp.TextMessage;
import com.wh.pojo.*;

/**
 * Created by danding on 2015/8/17.
 */
public class MessageTemplate {

    public static TextMessage getWelcomeInfo(String fromUser,String toUser){
        TextMessage textMessage = new TextMessage();
        textMessage.setFromUserName(fromUser);
        textMessage.setToUserName(toUser);
        textMessage.setMsgType(MessageUtil.RESQ_MESSAGE_TYPE_TEXT);
        textMessage.setContent("Biu~的一下你就来到小岗的的大本营啦！\n" +
                "[鼓掌][鼓掌][鼓掌][鼓掌][爱心][鼓掌][鼓掌][鼓掌][鼓掌]\n" +
                "这里除了工作一无所有！\n" +
                "只缺一个渴望工作君临幸的你！\n" +
                "小岗为你量身打造了“人岗匹配”系统，\n" +
                "从此你就是校园招聘会上的一个传奇！\n" +
                "[玫瑰][玫瑰][玫瑰][玫瑰][玫瑰][玫瑰][玫瑰][玫瑰][玫瑰]\n" +
                "你需要做的只是轻轻一点，\n" +
                "点击“简历”告诉小岗你要什么样的工作；\n" +
                "点击“职业测评”你会了解到自己有多大能耐；\n" +
                "就这么简单，\n" +
                "小岗就会把工作送到你的碗里来~ \n" +
                "[鼓掌][鼓掌][鼓掌][鼓掌][爱心][鼓掌][鼓掌][鼓掌][鼓掌]\n" +
                "还等什么？早到早得哦~[勾引]");
        return textMessage;
    }
    /**
     * 返回菜单对象
     * @return
     */
    public static Menu getMenu(){
        //个人菜单
        ViewButton myResume = new ViewButton();
        myResume.setName("我的简历");
        myResume.setType("view");
        myResume.setUrl(Constants.BASE_SERVER + "/resume/resumePage.do");

        ViewButton evaluation = new ViewButton();
        evaluation.setName("职业测评");
        evaluation.setType("view");
        evaluation.setUrl(Constants.BASE_SERVER + "/zycp/paperPage");

        ViewButton mainButton3_2 = new ViewButton();
        mainButton3_2.setName("人职匹配");
        mainButton3_2.setType("view");
        mainButton3_2.setUrl(Constants.BASE_SERVER + "/systemMajor/index.do");

        ViewButton button2_1 = new ViewButton();
        button2_1.setName("扫一扫");
        button2_1.setType("view");
        button2_1.setUrl(Constants.BASE_SERVER + "/resume/scanStationPage");

        ViewButton button2_2 = new ViewButton();
        button2_2.setName("岗位推荐");
        button2_2.setType("view");
        button2_2.setUrl(Constants.BASE_SERVER + "/station/recommendStation");

        ViewButton button2_3 = new ViewButton();
        button2_3.setName("投递的岗位");
        button2_3.setType("view");
        button2_3.setUrl(Constants.BASE_SERVER + "/station/postedResumes");

        ViewButton button2_4 = new ViewButton();
        button2_4.setName("收到的面试邀请");
        button2_4.setType("view");
        button2_4.setUrl(Constants.BASE_SERVER + "/meet/userMeetList");

        ViewButton button2_5 = new ViewButton();
        button2_5.setName("招聘企业");
        button2_5.setType("view");
        button2_5.setUrl(Constants.BASE_SERVER + "/user/lookCompanys");

        ComplexButton mainButton1 = new ComplexButton();
        mainButton1.setName("简历");
        mainButton1.setSub_button(new Button[]{myResume, evaluation});

        ComplexButton mainButton2 = new ComplexButton();
        mainButton2.setName("找工作");
        mainButton2.setSub_button(new Button[]{button2_1,button2_5,button2_3,button2_4,mainButton3_2});

        ViewButton mainButton3 = new ViewButton();
        mainButton3.setName("使用指南");
        mainButton3.setType("view");
        mainButton3.setUrl("http://mp.weixin.qq.com/s?__biz=MzA4NDM2Njk5Mg==&mid=402121703&idx=1&sn=55db0fd4fe7c62f12581afb41f720549#rd");

        Menu menu = new Menu();
        menu.setButton(new Button[]{mainButton1,mainButton2,mainButton3});

        return menu;
    }

    /**
     * 学生身份-个性化菜单
     * @return
     */
    public static Menu getAddconditonMenu_student(String platformId){
        Menu menu = new Menu();

        ViewButton myResume = new ViewButton();
        myResume.setName("☀我的简历");
        myResume.setType("view");
        myResume.setUrl(Constants.BASE_SERVER + "/resume/resumePage.do?platformId="+platformId);

        ViewButton evaluation = new ViewButton();
        evaluation.setName("职业测评");
        evaluation.setType("view");
        evaluation.setUrl(Constants.BASE_SERVER + "/zycp/startPaperPage?platformId="+platformId);

        ViewButton button2_1 = new ViewButton();
        button2_1.setName("扫一扫");
        button2_1.setType("view");
        button2_1.setUrl(Constants.BASE_SERVER + "/resume/scanStationPage?platformId="+platformId);

        ViewButton button2_2 = new ViewButton();
        button2_2.setName("岗位推荐");
        button2_2.setType("view");
        button2_2.setUrl(Constants.BASE_SERVER + "/station/recommendStation?platformId="+platformId);

        ViewButton button2_3 = new ViewButton();
        button2_3.setName("投递的岗位");
        button2_3.setType("view");
        button2_3.setUrl(Constants.BASE_SERVER + "/station/postedResumes?platformId="+platformId);

        ViewButton button2_4 = new ViewButton();
        button2_4.setName("收到的面试邀请");
        button2_4.setType("view");
        button2_4.setUrl(Constants.BASE_SERVER + "/meet/userMeetList?platformId="+platformId);

        ViewButton mainButton3_2 = new ViewButton();
        mainButton3_2.setName("人职匹配");
        mainButton3_2.setType("view");
        mainButton3_2.setUrl(Constants.BASE_SERVER + "/systemMajor/index.do?platformId="+platformId);

        ViewButton btn2_5 = new ViewButton();
        btn2_5.setName("招聘企业");
        btn2_5.setType("view");
        btn2_5.setUrl(Constants.BASE_SERVER + "/user/lookCompanys?platformId="+platformId);

        ComplexButton mainButton1 = new ComplexButton();
        mainButton1.setName("简历");
        mainButton1.setSub_button(new Button[]{myResume, evaluation});

        ComplexButton mainButton2 = new ComplexButton();
        mainButton2.setName("找工作");
        mainButton2.setSub_button(new Button[]{button2_1,btn2_5, button2_3, button2_4});

        ViewButton mainButton3_1 = new ViewButton();
        mainButton3_1.setName("使用指南");
        mainButton3_1.setType("view");
        mainButton3_1.setUrl("http://mp.weixin.qq.com/s?__biz=MzA4NDM2Njk5Mg==&mid=402121703&idx=1&sn=55db0fd4fe7c62f12581afb41f720549#rd");

        ViewButton mainButton3_3 = new ViewButton();
        mainButton3_3.setName("支付测试");
        mainButton3_3.setType("view");
        mainButton3_3.setUrl(Constants.BASE_SERVER + "/common/userRecharge/wx/recharge.htm?platformId="+platformId);
        
        ComplexButton mainButton3 = new ComplexButton();
        mainButton3.setName("测试中心");
        mainButton3.setSub_button(new Button[]{mainButton3_1, mainButton3_2, mainButton3_3});
        
//        MatchRule matchrule = new MatchRule();
//        matchrule.setGroup_id(WeiXinUtil.getGroupIdByName("学生")+"");

        menu.setButton(new Button[]{mainButton1, mainButton2, mainButton3});
//        menu.setMatchrule(matchrule);

        return menu;
    }

    /**
     * 企业身份-个性化菜单
     * @return
     */
    public static Menu getAddconditonMenu_company(String platformId){
        Menu menu = new Menu();

        ViewButton btn1_1 = new ViewButton();
        btn1_1.setName("收到的简历");
        btn1_1.setType("view");
        btn1_1.setUrl(Constants.BASE_SERVER + "/resume/resumeList.do?platformId="+platformId);

        ViewButton btn1_2 = new ViewButton();
        btn1_2.setName("发出的面试邀请");
        btn1_2.setType("view");
        btn1_2.setUrl(Constants.BASE_SERVER + "/meet/meetList?platformId="+platformId);

        ViewButton btn1_3 = new ViewButton();
        btn1_3.setName("推荐的简历");
        btn1_3.setType("view");
        btn1_3.setUrl(Constants.BASE_SERVER + "/resume/recommendResume?platformId="+platformId);

        ViewButton btn2_1 = new ViewButton();
        btn2_1.setName("添加岗位");
        btn2_1.setType("view");
        btn2_1.setUrl(Constants.BASE_SERVER + "/station/addStationPage.do?platformId="+platformId);

        ViewButton btn2_2 = new ViewButton();
        btn2_2.setName("发布的岗位");
        btn2_2.setType("view");
        btn2_2.setUrl(Constants.BASE_SERVER + "/station/publishStations.do?platformId="+platformId);

        ViewButton btn3_1 = new ViewButton();
        btn3_1.setName("我的");
        btn3_1.setType("view");
        btn3_1.setUrl(Constants.BASE_SERVER + "/user/selectCompany?platformId="+platformId);


        ComplexButton mainButton1 = new ComplexButton();
        mainButton1.setName("简历");
        mainButton1.setSub_button(new Button[]{btn1_1, btn1_2});

        ComplexButton mainButton2 = new ComplexButton();
        mainButton2.setName("岗位");
        mainButton2.setSub_button(new Button[]{btn2_1,btn2_2});

//        MatchRule matchrule = new MatchRule();
//        matchrule.setGroup_id(WeiXinUtil.getGroupIdByName("企业")+"");

        menu.setButton(new Button[]{mainButton1, mainButton2, btn3_1});
//        menu.setMatchrule(matchrule);

        return menu;
    }
}

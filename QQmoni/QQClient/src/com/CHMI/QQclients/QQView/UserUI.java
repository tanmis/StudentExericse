package com.CHMI.QQclients.QQView;

import com.CHMI.QQclients.service.UserClientServe;

import java.util.Scanner;

/**
 * @author 茶米酱
 * @version 1.0
 * 用户登录显示界面
 */
public class UserUI {
    private Boolean loop  = true;
    private int key;
    //用于登录服务器
    private  UserClientServe ucs = new UserClientServe();;

    public static void main(String[] args) {
        new UserUI().menu();
    }
    public void menu(){
        Scanner scanner = new Scanner(System.in);

        //进入第一层界面
        do {
            System.out.println("==========欢迎登录我们的网络==========\n");
            System.out.println("\t\t1.登录聊天系统\n");
            System.out.println("\t\t9.退出聊天系统\n");
            System.out.println("请输入你的选择\n");
            key = Integer.parseInt(Utility.readString(1));
            switch (key){
                case 1:
                    System.out.println("=========登录系统==========");
                    System.out.println("\n\t\t请输入账号id");
                    String userid = Utility.readString(50);
                    System.out.println("\n\t\t请输入密码");
                    String pwd = Utility.readString(50);
                    //传入比较方法进行比较
                    if (ucs.ChekUser(userid,pwd)){
                        System.out.println("==========欢迎登录"+userid+"==============");
                        do {
                            System.out.println("========欢迎来到二级目录("+userid+")==========");
                            System.out.println("\n\t\t1.显示在线用户列表");
                            System.out.println("\n\t\t2.群发消息");
                            System.out.println("\n\t\t3.私发消息");
                            System.out.println("\n\t\t4.发送文件");
                            System.out.println("\n\t\t9.退出系统");
                            System.out.println("\n\t\t请输入你的选择");
                            key = Integer.parseInt(Utility.readString(1));
                            switch (key){
                                case 1:
                                    System.out.println("显示用户在线列表");
                                    ucs.onlineFriendList();
                                    break;
                                case 2:
                                    System.out.println("群发消息");
                                    System.out.println("输入要发的消息");
                                    String s = Utility.readString(1000);
                                    ucs.sendmassmessages(s);
                                    break;
                                case 3:
                                    System.out.println("私发消息·请输入需要发给的人id");
                                    String onlysenduserid = Utility.readString(100);
                                    System.out.println("请输入私聊信息(限制一万字）");
                                    String onlysendword = Utility.readString(100000);
                                    ucs.Messagessentprivately(onlysenduserid,onlysendword);
                                    break;
                                case 4:
                                    System.out.println("发送文件");
                                    System.out.println("发送给谁");
                                    String filluserid = Utility.readString(50);
                                    System.out.println("文件路径");
                                    String filladdress = Utility.readString(50);
                                    System.out.println("对方电脑路径");
                                    String counterpartaddress = Utility.readString(50);
                                    ucs.Sendthefile(filluserid,filladdress,counterpartaddress);
                                    break;
                                case 9:
                                    loop = false;
                                    ucs.logout();
                                    break;
                            }
                        }while (loop);


                    }
                    else {
                        System.out.println("====登录失败=========");
                    }
                    break;
                case 9:
                    loop =false;
                    break;
                default:
                    System.out.println("输入错误重新选择");
                    break;

            }

        }while (loop);
    }
}

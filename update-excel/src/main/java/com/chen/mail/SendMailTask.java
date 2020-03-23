package com.chen.mail;

import com.chen.Main;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.chen.mail.OhMyEmail.SMTP_QQ;

public class SendMailTask {
    // 该邮箱修改为你需要测试的邮箱地址
    private static final String TO_EMAIL = "461568241@qq.com";

    public void initAndSend() throws SendMailException {
        before();
        testSendAttach();
    }
    public void before() {
        // 配置，一次即可
        OhMyEmail.config(SMTP_QQ(false), "2788281323@qq.com", "vbjabmklljkidcga");
        // 如果是企业邮箱则使用下面配置
//        OhMyEmail.config(SMTP_ENT_QQ(false), "xxx@qq.com", "*******");
    }

    public void testSendText() throws SendMailException {
        OhMyEmail.subject("这是一封测试TEXT邮件")
                .from("小姐姐的邮箱")
                .to(TO_EMAIL)
                .text("信件内容")
                .send();
    }

    public void testSendHtml() throws SendMailException {
        OhMyEmail.subject("这是一封测试HTML邮件")
                .from("小姐姐的邮箱")
                .to(TO_EMAIL)
                .html("<h1 font=red>信件内容</h1>")
                .send();
    }

    public void testSendAttach() throws SendMailException {
        String FORMAT =  new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        OhMyEmail.subject(Main.MIAL_TITLE +FORMAT)
                .from("你的宝宝")
                .to(TO_EMAIL)
                .html("<h1 font=red>详见附件</h1>")
                .attach(new File(Main.TO_FILE_PATH),Main.MIAL_TITLE +".xlsx")
                .send();
    }
}
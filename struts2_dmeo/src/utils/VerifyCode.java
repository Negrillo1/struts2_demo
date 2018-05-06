package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
/**
 * 
 * @author 林敬凯  2018/4/19
 *
 */
public class VerifyCode {

	private BufferedImage image;//图像
	private String str;//验证码
	private static char code[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789".toCharArray();
	
	public static final String SESSION_CODE_NAME="code";
	
	private VerifyCode() {
		init();//初始化属性
	}
	/*
	 * 取得RandomNumUtil实例
	 */
	public static VerifyCode Instance() {
		return new VerifyCode();
	}
	/*
	 * 取得验证码图片
	 */
	public BufferedImage getImage() {
		return this.image;
	}
	/*
	 * 取得验证码
	 */
	public String getString() {
		return this.str;
	}
	private void init() {
		// TODO Auto-generated method stub
		int width = 85;
		int height = 20;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//获得图形上下文
		Graphics g = image.getGraphics();
		//随机类
		Random random = new Random();
		//设置背景颜色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		//设置字体
		g.setFont(new Font("Time New Roman",Font.PLAIN,18));
		g.setColor(getRandColor(160, 200));
		//产生150条干扰线
		for (int i = 0; i < 150; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(12);
			int y1 = random.nextInt(12);
			g.drawLine(x, y, x + x1, y + y1);
		}
		//取到随机产生的认证码（4位数字）
		 String sRand = "";
		 for (int i = 0; i < 4; i++) {
			 String rand = String.valueOf(code[random.nextInt(code.length)]);
			 sRand += rand;
			 //将认证码显示到图像中
			 g.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110), 20 + random.nextInt(110)));
			 g.drawString(rand,13 * i + 6, 16);
		 }
		 //赋值验证码
		 this.str = sRand;
		 
		 g.dispose();
		 //赋值图像
		 this.image = image;
		 
	}
	/*
	 * 给定范围获得随机颜色
	 */
	private Color getRandColor(int fc, int bc) {  
        Random random = new Random();  
        if (fc > 255)  
            fc = 255;  
        if (bc > 255)  
            bc = 255;  
        int r = fc + random.nextInt(bc - fc);  
        int g = fc + random.nextInt(bc - fc);  
        int b = fc + random.nextInt(bc - fc);  
        return new Color(r, g, b);  
    } 
}

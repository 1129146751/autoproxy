package com.newtouch;

public class ProxyTest {
	public static void main(String[] args) throws Exception {
		Math math=new MathLmp();
		MathProxy objPoxy=new MathProxy(math);
		//isObjPoxy代理类
		Math isObjPoxy=(Math)objPoxy.getProxy();
		isObjPoxy.add(1, 3);
	}
}

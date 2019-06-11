package com.newtouch;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import javax.swing.plaf.basic.BasicComboPopup.InvocationKeyHandler;

import org.omg.CORBA.SystemException;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.InvokeHandler;
import org.omg.CORBA.portable.OutputStream;
import org.omg.CORBA.portable.ResponseHandler;

public class MathProxy {
	private Object obj;
	public MathProxy(Object obj){
		this.obj=obj;
	}
	
	public Object getProxy() throws Exception{
		Object proxy;
		//目标类的类加载器
		ClassLoader classLoader=obj.getClass().getClassLoader();
		//目标类实现的接口
		Class[] classInterface=obj.getClass().getInterfaces();
		//方法1：使用代理类的newProxyInstance方法创建代理对象
		/*proxy=Proxy.newProxyInstance(classLoader,classInterface,
				(proxy1, method, args)->{
					//获取方法的名字
					String methodName = method.getName();
					//记录日志
					System.out.println("LoggingProxy==> The method " + methodName+" begin with "+ Arrays.asList(args));
					Object result = method.invoke(obj, args);  // 目标对象执行目标方法. 相当于执行ArithmeticCalculatorImpl中的+ - * /
					//记录日志
					System.out.println("LoggingProxy==> The method " + methodName  +" ends with :" +result   );
					return result ;
			
		});*/
		//方法2：使用代理类的构造方法  创建代理对象
		Class clazz=Proxy.getProxyClass(classLoader, classInterface);//得到代理类的class类型的对象
		Constructor  constructor=clazz.getDeclaredConstructor(InvocationHandler.class);
		proxy= constructor.newInstance(new InvocationHandler(){

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				System.out.println(method.getName());
				Object result=method.invoke(obj, args);
				System.out.println(result);
				return result;
			}
			
		});
//		return proxy;
		/*Class clazz=Proxy.getProxyClass(classLoader, classInterface);
		Constructor constructor=clazz.getDeclaredConstructor(InvocationHandler.class);
		proxy=constructor.newInstance((proxy1, method,args)->{ //目标申明必须是一个函数式接口
			return null;
		} );*/
		
		return proxy;
	}
}

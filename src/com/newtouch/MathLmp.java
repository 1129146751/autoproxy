package com.newtouch;

/**
 * 目标类
 * @author Administrator
 *
 */
public class MathLmp implements Math {
	
    /**
     * 求和
     */
	@Override
	public Integer add(Integer i, Integer j) {
		Integer result=i+j;
		return result;
	}
	
	@Override
	public float div(Integer i, Integer j) {
		float result=i/j;
		return result;
	}

}

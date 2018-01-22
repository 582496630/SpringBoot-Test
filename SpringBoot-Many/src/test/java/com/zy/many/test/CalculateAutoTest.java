/*package com.zy.many.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;

 * 测试类中的标签及意义
@Test
该标签是测试方法的标示，可以直接运行，替代java中的main函数
@before
做每个方法执行前会先执行该标签的方法，通常过一些数据清理与准备的工作
@after
每个测试方法执行完成后执行该标签的方法，通常做一些数据清理与销毁的工作
@beforeclass
做每个测试类执行前最开始执行的方法，做一些整个类中的最前面的数据准备工作
@afterclass
做每个测试类执行后最后的方法，做一些整个类中的最后的数据销毁工作
@Test（exception==**.class）
方法执行的异常，可以在test标签后面进行指定（通常可以在，该case跑会抛异常，并且想让该case通过的场景使用）
@Test（timeout==xxx）
方法执行限定时间，限定时间内如果未完成，也算case执行失败
@Ignore
无效case，执行过程中该方法不被执行
5 test结果的，放在工程的target目录下，可在target目录下，surefire-reports目录里面，可以进行结果的查看
 * 
 
import org.junit.runners.MethodSorters;

import com.zy.many.test.junit.Calculate;

*//**
 * 测试类
 * 
 * @author zhouyou
 * @version
 *//*
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CalculateAutoTest {
	private Calculate cal;
	
	@Before//在运行每个测试方法之前先初始化cal
	public void setUp(){
		System.out.println("运行befor方法");
		cal = new Calculate();
	}
	@BeforeClass
	public static void setUp1(){
		System.out.println("beforeClass");
	}
	@AfterClass
	public static void setAfter(){
		System.out.println("AfterClass");
	}
	
	@Test
	public void testAdd() {
		assertEquals(8, new Calculate().add(4, 4));
		System.out.println("add测试");
	}

	@Test
	public void testSubstract() {
		assertEquals(1, new Calculate().substract(5, 4));
		System.out.println("substract测试");
	}

	@Test
	public void testMultiply() {
		assertEquals("乘法有问题", 20 , new Calculate().multiply(5, 4));
		System.out.println("multiply测试");
	}

	@Test
	public void testDivide() {
		assertEquals("除法有问题", 2 , cal.divide(8, 4));
		System.out.println("divide测试");
	}

}
*/
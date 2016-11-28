package gui.alvin.managerment.bean;

import java.awt.Graphics2D;

/**
 * 所有类的接口
 * 
 * @author 唐植超
 * 
 */
public interface IBean {

	/**
	 * 画图的方法
	 * 
	 * @param g2
	 */
	void draw(Graphics2D g2);

	/**
	 * 连线的方法
	 * 
	 * @param g2
	 */
	void conection(Graphics2D g2);

	/**
	 * 解析xml节点为语句关系
	 * 
	 * @param parent
	 */
	// void analyser(Java_Statement parent);
}

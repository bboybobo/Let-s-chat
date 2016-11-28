package talk.alvin.util;

import talk.alvin.bean.resoucebean.BaseResouceBean;

/**
 * 资源管理工具
 * 
 * @author 唐植超
 * 
 */
public enum Env {
	/** 配置文件 */
	configer(new BaseResouceBean("talk.txt")),
	/** 星座 */
	star(new BaseResouceBean("star.txt")),
	/** 血型 */
	blood(new BaseResouceBean("blood.txt")),
	/** 图标 */
	icons(new BaseResouceBean("icons.txt"));

	public BaseResouceBean instance;

	private Env(BaseResouceBean resource) {
		this.instance = resource;
	}

}

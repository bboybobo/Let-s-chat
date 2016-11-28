package talk.alvin.biz.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import talk.alvin.bean.SimpleUser;
import talk.alvin.bean.UserInfoBean;
import talk.alvin.biz.BaseBiz;
import talk.alvin.biz.IUserInfoBiz;
import talk.alvin.util.Resource;
import talk.alvin.util.StringUtil;

public class UserInfoBizImpl extends BaseBiz implements IUserInfoBiz {

	public UserInfoBean doLogin(String name, String pass) {
		// 根据name找到xml文件的路径 实例化出一个对象
		List<String> groupFirendList;
		Object tempObj;
		try {
			String path = Resource.instance
					.getUserDataPath(name.concat(".txt"));
			UserInfoBean user = (UserInfoBean) xmlIo.getObject(new File(path));
			pass = encodePassword(pass, name);
			if (user == null) {
				return null;
			}
			if (!user.getPassword().equals(pass)) {
				return null;
			}
			// 读取好友信息
			for (String key : user.getGroupList()) {
				groupFirendList = user.getFriendMap().get(key);
				if (groupFirendList != null) {
					for (String id : groupFirendList) {
						path = Resource.instance.getSimpleUserDataPath(id
								.concat(".txt"));
						tempObj = xmlIo.getObject(new File(path));
						user.getFirendInfoMap().put(id, (SimpleUser) tempObj);
					}
				}
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String doRegister(UserInfoBean user) {
		Integer id;
		String mess = "";
		String idFilePath;
		String userDataPath;
		String pass;
		File file;
		SimpleUser suser;
		// 添加3个分组
		user.getGroupList().add("My Family");
		user.getGroupList().add("My Firends");
		user.getGroupList().add("Stranger");
		// 从配置文件中拿到一个账号
		idFilePath = Resource.instance.getConfigPath("user.txt");
		file = new File(idFilePath);
		try {
			id = Integer.parseInt(fileIO.getContent(file)) + 1;
		} catch (Exception e) {
			e.printStackTrace();
			id = 10000;
		}
		user.setId(id + "");
		// 密码加密
		pass = encodePassword(user.getPassword(), user.getId());
		user.setPassword(pass);
		// 初始化好友关系
		for (String key : user.getGroupList()) {
			user.getFriendMap().put(key, new ArrayList<String>());
		}
		// 默认将自己加为好友
		user.getFriendMap().get(user.getGroupList().get(0)).add(user.getId());
		// 将用户信息保存到xml文件
		try {
			fileIO.wirteContent(file, user.getId());
			userDataPath = Resource.instance.getUserDataPath(user.getId()
					.concat(".txt"));
			file = new File(userDataPath);
			xmlIo.writeObject(file, user);

			// 备份user
			suser = new SimpleUser();
			suser.setFaceId(user.getFaceId());
			suser.setId(user.getId());
			suser.setName(user.getNickName());
			userDataPath = Resource.instance.getSimpleUserDataPath(user.getId()
					.concat(".txt"));
			file = new File(userDataPath);
			xmlIo.writeObject(file, suser);
			mess = "注册成功！ 账号为 " + user.getId();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			mess = "服务器故障,情重新注册!";
		}
		// 发送邮件

		return mess;
	}

	/**
	 * 统一加密
	 * 
	 * @param pass
	 * @param name
	 * @return
	 */
	private String encodePassword(String pass, String name) {
		name = StringUtil.instance.encoderByMd5(name);
		pass = StringUtil.instance.encoderByMd5(pass);
		pass = StringUtil.instance.encoderByMd5(name.concat(pass));
		return pass;
	}

	public List<SimpleUser> doSearch(String id, String account) {
		String userDataPath = Resource.REALPATH.concat("data").concat(
				File.separator).concat("simpleData").concat(File.separator);
		File userDataFile = new File(userDataPath);
		SimpleUser user;
		List<SimpleUser> resultList = new ArrayList<SimpleUser>();
		if (!id.equals("") && !account.equals("")) {
			for (String path : userDataFile.list()) {
				if (path.indexOf(id) != -1) {
					try {
						user = (SimpleUser) xmlIo.getObject(new File(
								userDataPath.concat(path)));
						if (user != null
								&& user.getName().indexOf(account) != -1) {
							resultList.add(user);
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		} else if (!id.equals("")) {
			for (String path : userDataFile.list()) {
				if (path.indexOf(id) != -1) {
					try {
						user = (SimpleUser) xmlIo.getObject(new File(
								userDataPath.concat(path)));
						if (user != null) {
							resultList.add(user);
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		} else if (!account.equals("")) {
			for (String path : userDataFile.list()) {
				try {
					user = (SimpleUser) xmlIo.getObject(new File(userDataPath
							.concat(path)));
					if (user != null && user.getName().indexOf(account) != -1) {
						resultList.add(user);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		} else {
			log.debug("no search condition");
		}
		return resultList;
	}

	public SimpleUser doAddFirend(String firendId, String myId, int grouIndex) {
		try {
			String path = "";
			UserInfoBean user;
			File file;
			// 当前用户添加好友
			path = Resource.instance.getUserDataPath(firendId).concat(".txt");
			file = new File(path);
			user = (UserInfoBean) xmlIo.getObject(file);
			if (user != null) {
				user.getFriendMap().get(user.getGroupList().get(grouIndex))
						.add(myId);
				xmlIo.writeObject(file, user);
			}

			path = Resource.instance.getUserDataPath(myId).concat(".txt");
			file = new File(path);
			user = (UserInfoBean) xmlIo.getObject(file);
			if (user != null) {
				user.getFriendMap().get(user.getGroupList().get(0)).add(
						firendId);
				xmlIo.writeObject(file, user);
			}
			path = Resource.instance.getSimpleUserDataPath(firendId).concat(
					".txt");
			file = new File(path);
			SimpleUser simpleUser = (SimpleUser) xmlIo.getObject(file);
			return simpleUser;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

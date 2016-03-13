package com.jd.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class LoginUtil {
	private static final String ServerUrl = "http://10.0.2.2:8080/ZTXJDServer/login";
	private static final String USER = "user";
	private static final String PWD = "password";
	/**唯一标识符--key*/
	private static final String UID = "uid";
	private static Context context;

	/**
	 * 尝试登陆服务器成功 返回true 否则返回false；
	 * 
	 * @param context
	 * @param user
	 * @param password
	 * @return
	 */
	public static boolean login(Context context, String user, String password) {
		LoginUtil.context = context;
		// 客户端
		DefaultHttpClient client = new DefaultHttpClient();
		// 访问方式
		HttpPost httpPost = new HttpPost(LoginUtil.ServerUrl);
		// 将用户名密码封装到list里面
		List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
		list.add(new BasicNameValuePair(USER, user));
		list.add(new BasicNameValuePair(PWD, password));
		/* 包含用户名密码的实体 */
		HttpEntity entity;
		try {
			entity = new UrlEncodedFormEntity(list, "utf-8");
			httpPost.setEntity(entity);
			// 执行post，并且得到响应
			HttpResponse httpResponse = client.execute(httpPost);
			int result = httpResponse.getStatusLine().getStatusCode();
			if (result == 200) {
				// 服务器返回的实体
				HttpEntity result_entity = httpResponse.getEntity();
				if (result_entity != null) {
					// 返回的json字符串
					String result_info = EntityUtils.toString(result_entity,
							"utf-8");
					// json字符串
					JSONObject jsonObject = new JSONObject(result_info);
					String uid = jsonObject.getString(UID);
					if (uid == null || uid.length() < 1) {
						// 登录失败
						return false;
					}
					//System.out.println("200" + ",uid:" + uid);
					// 登录成果保存UID
					saveUID(uid);
					return true;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 存储uid（user identification 用户识别; user identifier 用户标识符; universal
	 * identifier 通用标识符; unique identifier 惟一标识符）
	 * 
	 * @param uid
	 */
	private static void saveUID(String uid) {
		//共享      参数
		SharedPreferences preferences = context.getSharedPreferences(UID,
				context.MODE_PRIVATE);
		Editor edit = preferences.edit();
		edit.putString(UID, uid);
		edit.commit();
	}

}

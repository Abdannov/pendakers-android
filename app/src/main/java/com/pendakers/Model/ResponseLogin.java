package com.pendakers.Model;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin{

	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("message")
	private String message;

	@SerializedName("token_type")
	private String tokenType;

	@SerializedName("user")
	private User user;

	public String getAccessToken(){
		return accessToken;
	}

	public String getMessage(){
		return message;
	}

	public String getTokenType(){
		return tokenType;
	}

	public User getUser(){
		return user;
	}
}
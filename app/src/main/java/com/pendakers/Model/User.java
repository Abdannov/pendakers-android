package com.pendakers.Model;

import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("tanggal_gabung")
	private String tanggalGabung;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("email_verified_at")
	private Object emailVerifiedAt;

	@SerializedName("id")
	private int id;

	@SerializedName("email")
	private String email;

	@SerializedName("kabkota")
	private String kabkota;

	public String getKabkota() {
		return kabkota;
	}

	public void setKabkota(String kabkota) {
		this.kabkota = kabkota;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getTanggalGabung(){
		return tanggalGabung;
	}

	public String getName(){
		return name;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public Object getEmailVerifiedAt(){
		return emailVerifiedAt;
	}

	public int getId(){
		return id;
	}

	public String getEmail(){
		return email;
	}
}
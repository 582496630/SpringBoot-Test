package com.zy.many.utils;

import java.util.HashMap;

public class Ret {

	public enum Status {
		ERROR("E"), WARNING("W"), SUCCESS("S");
		private String data;

		private Status(String data) {
			this.data = data;
		}

		@Override
		public String toString() {
			return data;
		}
	}

	@SuppressWarnings("serial")
	public class Wrapper extends HashMap<String, Object> {

		public Wrapper(String key, Object data) {
			super.put(key, data);
		}

		@Override
		@Deprecated
		public Object put(String key, Object data) {
			return super.put(key, data);
		}

	}

	private String status = "S";
	private String message;
	private Object data;

	private Ret() {
	}

	private Ret(Object data) {
		this.data = data;
	}

	private Ret(String key, Object data) {
		this.data = new Wrapper(key, data);
	}

	private Ret(Status status, String message) {
		this.status = status.toString();
		this.message = message;
		this.data = "";
	}

	private Ret(String status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public Object getdata() {
		return data;
	}

	public static Ret warn(String message) {
		return new Ret(Status.WARNING, message);
	}

	public static Ret error(String message) {
		return new Ret(Status.ERROR, message);
	}

	public static Ret success() {
		return new Ret();
	}

	public static Ret success(Object data) {
		return new Ret(data);
	}

	public static Ret success(String key, Object data) {
		return new Ret(key, data);
	}

	public static Ret success(String status, String message, Object data) {
		return new Ret(status, message, data);
	}

}

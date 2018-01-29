package com.zy.many.xml;

import java.util.List;

public class GameProtocolXMLEntity {

	// private String protocol;
	// private String title;
	private String title_head;
	private String title_body;
	private List<String> listbody_head;
	private List<String> listbody;
	private List<String> listbody1;
	private List<String> listbody2;

	public String tobodylist(List<String> listbody) {
		String body = null;
		for (String string : listbody) {
			body = body + "," + string;
		}
		return body;
	}

	public String tobody2list(List<String> list2body) {
		String body2 = null;
		for (String string : list2body) {
			body2 = body2 + "," + string;
		}
		return body2;
	}

	public String tobody1list(List<String> list1body) {
		String body1 = null;
		for (String string : list1body) {
			body1 = body1 + "," + string;
		}
		return body1;
	}

	public String toListbody_head(List<String> listbody_head) {
		String body_head = null;
		for (String string : listbody_head) {
			body_head = body_head + "," + string;
		}
		return body_head;
	}

	public List<String> getListbody1() {
		return listbody1;
	}

	public void setListbody1(List<String> listbody1) {
		this.listbody1 = listbody1;
	}

	public List<String> getListbody2() {
		return listbody2;
	}

	public void setListbody2(List<String> listbody2) {
		this.listbody2 = listbody2;
	}

	public List<String> getListbody_head() {
		return listbody_head;
	}

	public void setListbody_head(List<String> listbody_head) {
		this.listbody_head = listbody_head;
	}

	public List<String> getListbody() {
		return listbody;
	}

	public void setListbody(List<String> listbody) {
		this.listbody = listbody;
	}

	/*
	 * public String getTitle() { return title; } public void setTitle(String
	 * title) { this.title = title; }
	 */
	public String getTitle_head() {
		return title_head;
	}

	public void setTitle_head(String title_head) {
		this.title_head = title_head;
	}

	public String getTitle_body() {
		return title_body;
	}

	public void setTitle_body(String title_body) {
		this.title_body = title_body;
	}

}

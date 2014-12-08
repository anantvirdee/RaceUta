package com.parse.starter;

public class  Note{
	private String name;
	private String comment;
	Note( String noteName, String noteComment) {
		name = noteName;
		comment = noteComment;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return this.getName()+this.getComment();
	}

}

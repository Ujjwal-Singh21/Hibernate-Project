package com.capg.Collections;
import java.util.List;

public class ListQuestion {
	
	private int id;
	private String qname;
	private List<String> answers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQname() {
		return qname;
	}

	public void setQname(String qname) {
		this.qname = qname;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "ListQuestion [id=" + id + ", qname=" + qname + ", answers=" + answers + "]";
	}

}

package data;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="result")
public class result {
	private String date;
	private String first;
	private String second;
	private String third;
	private starter st;
	private consola con;

	@XmlElement(name="starter", type=starter.class)
	public void setStarter(starter st) {
		this.st = st;
	}

	public starter getStarter() {
		return st;
	}

	@XmlElement(name="consola", type=consola.class)
	public void setConsola(consola con) {
		this.con = con;
	}

	public consola getConsola() {
		return con;
	}

	/*@XmlElement(name="date")
	public void setDate(String date) {
		this.date = date;
	}*/

	public String getDate() {
		return date;
	}

	/*@XmlElement(name="first")
	public void setFirst(String first) {
		this.first = first;
	}*/

	public String getFirst() {
		return first;
	}

	/*@XmlElement(name="second")
	public void setSec(String second) {
		this.second = second;
	}*/

	public String getSec() {
		return second;
	}

	/*@XmlElement(name="third")
	public void setThird(String third) {
		this.third = third;
	}*/

	public String getThird() {
		return third;
	}

}
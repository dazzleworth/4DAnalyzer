package data;

import javax.xml.bind.annotation.*;

//@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="live4d")
public class live4d {

fourd fd;

@XmlElement(name="fourd", type=fourd.class)
public void setFourd(fourd fd) {
	this.fd = fd;
}

public fourd getFourd() {
	return fd;
}

}
package data;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="fourd")
public class fourd {
	result res;

	@XmlElement(name="result", type=result.class)
	public void setResult(result res) {
		this.res = res;
	}

	public result getResult() {
		return res;
	}
}
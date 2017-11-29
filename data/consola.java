package data;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="consola")
public class consola {
	List<String> consolaItems = new ArrayList<String>();

	public List<String> getConsolaList() {
		return consolaItems;
	}

	@XmlElement(name="consolaItem")
	public void setConsolaList(List<String> consolaItems) {
		this.consolaItems = consolaItems;
	}
}

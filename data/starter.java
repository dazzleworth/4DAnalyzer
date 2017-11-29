package data;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="starter")
public class starter {
	List<String> starterItems = new ArrayList<String>();

	public List<String> getStarterList() {
		return starterItems;
	}

	@XmlElement(name="starterItem")
	public void setStarterList(List<String> starterItems) {
		this.starterItems = starterItems;
	}
}

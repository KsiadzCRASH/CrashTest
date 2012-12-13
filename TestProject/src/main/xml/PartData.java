package main.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "val2")
public class PartData {
    @XmlValue
	public String value;

	public PartData() {
	}
}
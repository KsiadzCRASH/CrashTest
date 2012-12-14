package main.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import main.xml.XmlReader.RootElem.Part;

@XmlRootElement(name = "root")
public class ElemData {
	@XmlElement(name = "val1")
	public String val1;
	@XmlElement(name = "val2")
	public List<PartData> val2List;
	@XmlElement(name = "val3")
	public String val3;

	public ElemData() {
	}
}
/**
 * 
 */
package pl.asseco.amms.model.apteka.xo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * @author Tomasz.Switek
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "main")
public class ExpFkXmlData 
{
	@XmlElement(name="elem")
	List<ExpFkXmlElemData> elemList;

	public List<ExpFkXmlElemData> getElemList() {
		return elemList;
	}
	public void setElemList(List<ExpFkXmlElemData> elemList) {
		this.elemList = elemList;
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "elem")
	public static class ExpFkXmlElemData
	{
		private String select;

		@XmlElement(name="from")
		private List<ExpFkXmlFromData> from;
		
		private String where;

		public String getSelect() {
			return select;
		}

		public void setSelect(String select) {
			this.select = select;
		}

		public List<ExpFkXmlFromData> getFrom() {
			return from;
		}

		public void setFrom(List<ExpFkXmlFromData> from) {
			this.from = from;
		}

		public String getWhere() {
			return where;
		}

		public void setWhere(String where) {
			this.where = where;
		}

	}
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "from")
	public static class ExpFkXmlFromData
	{
		@XmlValue
		private String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}

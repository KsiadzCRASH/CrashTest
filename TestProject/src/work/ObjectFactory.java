//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.12.16 at 05:25:00 PM CET 
//


package work;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the work package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Root_QNAME = new QName("", "root");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: work
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ExpFkXmlQueryXO }
     * 
     */
    public ExpFkXmlQueryXO createExpFkXmlQueryXO() {
        return new ExpFkXmlQueryXO();
    }

    /**
     * Create an instance of {@link ExpFkXmlQueryElemPartXO }
     * 
     */
    public ExpFkXmlQueryElemPartXO createExpFkXmlQueryElemPartXO() {
        return new ExpFkXmlQueryElemPartXO();
    }

    /**
     * Create an instance of {@link StrExpFkXO }
     * 
     */
    public StrExpFkXO createStrExpFkXO() {
        return new StrExpFkXO();
    }

    /**
     * Create an instance of {@link ExpFkXmlQueryElemXO }
     * 
     */
    public ExpFkXmlQueryElemXO createExpFkXmlQueryElemXO() {
        return new ExpFkXmlQueryElemXO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExpFkXmlQueryXO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "root")
    public JAXBElement<ExpFkXmlQueryXO> createRoot(ExpFkXmlQueryXO value) {
        return new JAXBElement<ExpFkXmlQueryXO>(_Root_QNAME, ExpFkXmlQueryXO.class, null, value);
    }

}

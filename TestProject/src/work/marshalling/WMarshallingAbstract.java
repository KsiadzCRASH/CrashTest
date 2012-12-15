package work.marshalling;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.util.ValidationEventCollector;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public abstract class WMarshallingAbstract<T extends ContentXMLAbstract> implements WMarshalling<T> {

    // wstrzyknąć w beanie springowym konkretny schemat i contextPath

    protected Logger logger = Logger.getLogger(getClass());

    protected Resource schema;

    protected String contextPath;

    private NamespacePrefixMapperImpl prefixMapper;

    /**
     * Flaga walidacji anotacji XML przed przetwarzaniem JAXB
     */
    private boolean validateAnnotations = false;

    public NamespacePrefixMapperImpl getPrefixMapper() {

        return prefixMapper;
    }

    public void setPrefixMapper(NamespacePrefixMapperImpl prefixMapper) {
        this.prefixMapper = prefixMapper;
    }

    public Resource getSchema() {
        return schema;
    }

    public void setSchema(Resource schema) {
        this.schema = schema;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String marshall(T komunikat) throws WMarshallingException {

        if (schema == null)
            throw new WMarshallingException("Podczasz tworzenia beana nie ustawiono schematu xsd");
        if (contextPath == null)
            throw new WMarshallingException("Podczasz tworzenia beana nie podano ścieżki dla modelu komunikatu.");
        if (komunikat == null)
            throw new WMarshallingException("Nie przekazano obiektu komunikatu");
        if (validateAnnotations) {
            validate(komunikat);
        }

        // wczytanie schematu XML
        Schema mySchema = null;
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            File schemaFile = null;
            URL url = null;

            try {
                url = schema.getURL();
            } catch (Exception ex) {
                try {
                    schemaFile = schema.getFile();
                } catch (IOException e) {
                }
            }
            if (url != null) {
                mySchema = schemaFactory.newSchema(url);
            } else if (schemaFile != null) {
                mySchema = schemaFactory.newSchema(schemaFile);
            } else {
                throw new WMarshallingException("Nie można było załadować pliku schematu xsd: " + schema.getFilename());
            }

        } catch (SAXException saxe) {
            throw new SchemaXMLException(saxe);
        }

        // rozpoczęcie procesu mapowania
        ValidationEventCollector validationEventCollector = new ValidationEventCollector();
        try {
            //Komunikat.class.getPackage().getName()
            ClassLoader cl;
            JAXBContext jaxContext;
            if (contextPath.contains("licence")) {
                cl = Class.forName(contextPath.concat(".ObjectFactory")).getClassLoader();
                jaxContext = JAXBContext.newInstance(contextPath, cl);
            } else {
                jaxContext = JAXBContext.newInstance(contextPath);
            }
//			JAXBContext jaxContext = JAXBContext.newInstance(contextPath);
            Marshaller marshaller = jaxContext.createMarshaller();

            // ustawienie schematu, ktorym bedzie się posługiwał walidator
            marshaller.setSchema(mySchema);
            marshaller.setEventHandler(validationEventCollector);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(true));
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            if (prefixMapper != null) {
                /* NIE RUSZAĆ TEGO! 
                 * (albo jeśli się chce ruszyć to sprawdzić WSZYSTKIE istniejące eksporty odwołujące się do bieżącej klasy czy się ich nie zepsuło! */
                marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", prefixMapper);
            }

            Writer writer = new StringWriter();

            JAXBElement<T> ob = getRootElement(komunikat);
            //JAXBElement<T> ob1 = getRoot(xmlContent);

            marshaller.marshal(ob, writer);

            sprawdzStatusWalidacji(validationEventCollector);

            return writer.toString();
        } catch (JAXBException e) {
            // sprawdzenie czy blad nie byl spowodowany walidacją
            sprawdzStatusWalidacji(validationEventCollector);

            throw new WMarshallingException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    @SuppressWarnings("unchecked")
    public T unmarshall(String content) throws WMarshallingException {

        if (schema == null)
            throw new WMarshallingException("Podczasz tworzenia beana nie ustawiono schematu xsd");
        if (contextPath == null)
            throw new WMarshallingException("Podczasz tworzenia beana nie podano ścieżki dla modelu komunikatu.");
        if (content == null)
            throw new WMarshallingException("Brak zawartości XML");
        content = content.trim(); //Trzeba dodać trim inaczej JAXB wykryje błąd iż jest coś za sekcja końcowa (SAX to ignoruje) 
        // wczytanie schematu XML
        Schema mySchema = null;
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            File schemaFile = null;
            URL url = null;

            try {
                url = schema.getURL();
            } catch (Exception ex) {
                try {
                    schemaFile = schema.getFile();
                } catch (IOException e) {
                }
            }
            if (url != null) {
                mySchema = schemaFactory.newSchema(url);
            } else if (schemaFile != null) {
                mySchema = schemaFactory.newSchema(schemaFile);
            } else {
                throw new WMarshallingException("Nie mozna było załadować pliku schematu xsd: " + schema.getFilename());
            }
        } catch (SAXException saxe) {
            throw new SchemaXMLException(saxe);
        }

        // rozpoczęcie procesu mapowania
        ValidationEventCollector validationEventCollector = new ValidationEventCollector();
        try {
            //Komunikat.class.getPackage().getName()
            ClassLoader cl;
            JAXBContext jaxContext;
            if (contextPath.contains("licence")) {
                cl = Class.forName(contextPath.concat(".ObjectFactory")).getClassLoader();
                jaxContext = JAXBContext.newInstance(contextPath, cl);
            } else {
                jaxContext = JAXBContext.newInstance(contextPath);
            }

//			JAXBContext jaxContext = JAXBContext.newInstance(contextPath);
            Unmarshaller unmarshaller = jaxContext.createUnmarshaller();

            // ustawienie schematu, ktorym bedzie się posługiwał walidator
            unmarshaller.setSchema(mySchema);
            unmarshaller.setEventHandler(validationEventCollector);

            Reader reader = new StringReader(checkForUtf8BOM(content));
//            Reader reader = checkForUtf8BOM(new InputStreamReader(inputStream, "UTF-8"));

            JAXBElement<?> jaxbElement = (JAXBElement<?>) unmarshaller.unmarshal(reader);

            T komunikat = (T) jaxbElement.getValue();
            sprawdzStatusWalidacji(validationEventCollector);

            return komunikat;
        } catch (JAXBException e) {
            // sprawdzenie czy blad nie byl spowodowany walidacją
            sprawdzStatusWalidacji(validationEventCollector);
            throw new WMarshallingException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new WMarshallingException(e);
        }
//        catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            throw new AmmsMarshallingException(e);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new AmmsMarshallingException(e);
//        }

    }

    public static String checkForUtf8BOM(String content) {
        try {
            byte[] utf8Byte = content.getBytes("UTF-8");
            byte[] byteContent = content.getBytes();
            boolean isCp1250 = byteContent.length != utf8Byte.length;

            // obsluga dla byteContent w cp1250
            if (isCp1250 && byteContent[0] == -17 && byteContent[1] == -69 && byteContent[2] == -65) {
                byte[] newContent = new byte[byteContent.length - 1];
                System.arraycopy(byteContent, 1, newContent, 0, newContent.length);
                content = new String(newContent);
            } else if (utf8Byte[0] == -17 && utf8Byte[1] == -69 && utf8Byte[2] == -65){
                byte[] newContent = new byte[utf8Byte.length - 3];
                System.arraycopy(utf8Byte, 3, newContent, 0, newContent.length);
                content = new String(newContent, "UTF-8");
            }

            return content;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    public static Reader checkForUtf8BOM(Reader reader) {
        PushbackReader pushbackReader = new PushbackReader(reader);
        try {
            byte[] bom = new byte[3];
            bom[0] = (byte) pushbackReader.read();
            bom[1] = (byte) pushbackReader.read();
            bom[2] = (byte) pushbackReader.read();
            // obsluga dla unix
            if (!(bom[0] == -17 && bom[1] == -69 && bom[2] == -65)) {
                pushbackReader.unread(3);
            }
            // obsluga dla windows
            if (!(bom[0] == -60 && bom[1] == -113 && bom[2] == -62)) {
                pushbackReader.unread(3);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return pushbackReader;
    }


    protected void sprawdzStatusWalidacji(ValidationEventCollector validationEventCollector) throws ValidationXMLException {

        if (validationEventCollector != null && validationEventCollector.hasEvents()) {
            String message = "Wystąpiły błędy podczas walidacji pliku XML:\n";
            int line = 0;
            int column = 0;
            for (ValidationEvent ve : validationEventCollector.getEvents()) {
                String msg = ve.getMessage();
                ValidationEventLocator vel = ve.getLocator();
                line = vel.getLineNumber();
                column = vel.getColumnNumber();

                message += "\nLinia: " + line + " Kolumna: " + column + ": " + msg;
            }

            throw new ValidationXMLException(message, line, column);
        }
    }

    /**
     * Dostarcza konkretnej instancji JAXBElement<T> gdzie T stanowi rootElement
     *
     * @param rootContent
     * @return
     */
    protected abstract JAXBElement<T> getRootElement(T rootContent);

    /**
     * Waliduje obiekt weryfikując anotacje pod kątem sprawdzenia wymagalności
     *
     * @param aXMLObject obiekt do walidacji
     */
    protected void validate(T aXMLObject) {
        validateElement(aXMLObject, aXMLObject.getClass(), new HashSet<String>());
    }

    /**
     * Waliduje obiekt (schodfzi rekurencyjnie)
     *
     * @param aObject   obiekt do weryfikacji
     * @param clazz     klas aobiektu (nadrzędna lub podrzędna - extends)
     * @param aZbiorPol zbiór (cache) prztwrozonych już pól obiektu, służy do weryfikacji
     *                  czy klasa nadrzędna nie nadpisała pola klasy podrzędnej (kwestia weryfikacji anotacji
     *                  z wymagalności)
     */
    protected void validateElement(Object aObject, Class clazz, Set<String> aZbiorPol) {
        if (aObject != null) {
            List<Field> pPola = ClassUtils.findFieldForAnnotation(clazz, XmlAttribute.class);
            for (Field pPole : pPola) {
                if (!aZbiorPol.contains(pPole.getName()) && pPole.getDeclaringClass() == clazz)//tylko zbieramy te pola które wyżej nie zostały przetworzone
                {
                    aZbiorPol.add(pPole.getName());
                    XmlAttribute pAtrybut = pPole.getAnnotation(XmlAttribute.class);
                    if (pAtrybut.required()) {
                        Method pMethod = ClassUtils.getReadMethod(pPole, clazz);
                        try {
                            if (pMethod != null && pMethod.invoke(aObject) == null) {
                                String pNazwa = pAtrybut.name();
                                pNazwa = pNazwa.equals("##default") ? pPole.getName() : pNazwa;
                                throw new IllegalStateException("Atrybut '" + pNazwa + "' jest wymagany dla obiektu " + clazz.getName());
                            }
                        } catch (IllegalAccessException e) {
                            logger.error("Błąd wywołania metody", e);
                        } catch (InvocationTargetException e) {
                            logger.error("Błąd wywołania metody", e);
                        }
                    }
                }
            }
            pPola = ClassUtils.findFieldForAnnotation(clazz, XmlElement.class);
            for (Field pPole : pPola) {
                if (!aZbiorPol.contains(pPole.getName()) && pPole.getDeclaringClass() == clazz)//tylko zbieramy te pola które wyżej nie zostały przetworzone
                {
                    aZbiorPol.add(pPole.getName());
                    XmlElement pElement = pPole.getAnnotation(XmlElement.class);
                    if (pElement.required() || pElement.nillable() == false) {
                        Method pMethod = ClassUtils.getReadMethod(pPole, clazz);
                        Object pValue = null;
                        try {
                            if (pMethod != null && (pValue = pMethod.invoke(aObject)) == null) {
                                if ("\u0000".equals(pElement.defaultValue()))//brak wartości domyślnej
                                {
                                    String pNazwa = pElement.name();
                                    pNazwa = pNazwa.equals("##default") ? pPole.getName() : pNazwa;
                                    throw new IllegalStateException("Element '" + pNazwa + "' jest wymagany dla obiektu " + clazz.getName());
                                }
                            } else {
                                validateElement(pValue, pValue.getClass(), new HashSet<String>());//nowy obiekt zatem pola od nowa
                            }
                        } catch (IllegalAccessException e) {
                            logger.error("Błąd wywołania metody", e);
                        } catch (InvocationTargetException e) {
                            logger.error("Błąd wywołania metody", e);
                        }
                    }
                }
            }
            if (clazz.getSuperclass() != null) {
                validateElement(aObject, clazz.getSuperclass(), aZbiorPol);
            }
        }
    }

}

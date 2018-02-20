
package server;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para throwable complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="throwable">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stackTrace" type="{http://server/}stackTraceElement" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "throwable", propOrder = {
    "stackTrace"
})
public class Throwable {

    @XmlElement(nillable = true)
    protected List<StackTraceElement> stackTrace;

    /**
     * Gets the value of the stackTrace property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stackTrace property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStackTrace().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StackTraceElement }
     * 
     * 
     */
    public List<StackTraceElement> getStackTrace() {
        if (stackTrace == null) {
            stackTrace = new ArrayList<StackTraceElement>();
        }
        return this.stackTrace;
    }

}

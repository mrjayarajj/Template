//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.0 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.10.21 at 12:38:33 PM IST 
//


package xml.third_party.jaxb.chessboard.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "position"
})
@XmlRootElement(name = "ROOK")
public class ROOK {

    @XmlElement(name = "POSITION", required = true)
    protected POSITION position;

    /**
     * Gets the value of the position property.
     * 
     * @return
     *     possible object is
     *     {@link POSITION }
     *     
     */
    public POSITION getPOSITION() {
        return position;
    }

    /**
     * Sets the value of the position property.
     * 
     * @param value
     *     allowed object is
     *     {@link POSITION }
     *     
     */
    public void setPOSITION(POSITION value) {
        this.position = value;
    }

}
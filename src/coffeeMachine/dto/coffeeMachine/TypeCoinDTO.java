//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.23 at 11:51:09 AM EEST 
//


package coffeeMachine.dto.coffeeMachine;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typeCoin.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="typeCoin">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FIVE"/>
 *     &lt;enumeration value="TEN"/>
 *     &lt;enumeration value="TWENTY"/>
 *     &lt;enumeration value="FIFTY"/>
 *     &lt;enumeration value="LEV"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "typeCoin")
@XmlEnum
public enum TypeCoinDTO {

    FIVE,
    TEN,
    TWENTY,
    FIFTY,
    LEV;

    public String value() {
        return name();
    }

    public static TypeCoinDTO fromValue(String v) {
        return valueOf(v);
    }

}
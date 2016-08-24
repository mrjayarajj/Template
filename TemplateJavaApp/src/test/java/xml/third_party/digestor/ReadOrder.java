package xml.third_party.digestor;

import java.io.File;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;

import xml.third_party.digestor.dto.order.Order;

public class ReadOrder {
	
   public static void main( String[] args ) {
      try {

         File input = new File( "resource-examples/order.xml" );
         File rules = new File( "resource-examples/order-rules.xml" );

         Digester digester = DigesterLoader.createDigester( rules.toURL() );

         Order order = (Order)digester.parse( input );
         //System.out.println( order );
         System.out.println( order.getBuyerCustomerParty().getParty().getPostalAddress().getCityName());
  
      } catch( Exception exc ) {
         exc.printStackTrace();
      }
   }
}
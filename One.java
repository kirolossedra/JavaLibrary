package src;


import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.lang.reflect.*;
public class One {
   public static void main(String[] args) {
       try {
           File file = new File("lib.xml");
           File file2 = new File("Animal.xml");
           DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
           DocumentBuilder db = dbf.newDocumentBuilder();
           Document doc = db.parse(file);
           Document doc2 = db.parse(file2);
           NodeList Node2 = doc2.getElementsByTagName("Animal");
           NodeList nodeList = doc.getElementsByTagName("employee");
           Element element = (Element) nodeList.item(0);
           Element element2 = (Element)Node2.item(0);
           Employee employee = mapXMLToObject(element, Employee.class);
           Animal animal = mapXMLToObject(element2,Animal.class);
           
           System.out.println(employee.getName());
           System.out.println(animal.getKind());
           
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   public static <T> T mapXMLToObject(Element element, Class<T> clazz) throws Exception {
       T obj = clazz.getDeclaredConstructor().newInstance();
       Field[] fields = clazz.getDeclaredFields();
       for (Field field : fields) {
           String fieldName = field.getName();
           NodeList nodeList = element.getElementsByTagName(fieldName);
           if (nodeList.getLength() > 0) {
               String value = nodeList.item(0).getTextContent();
               field.setAccessible(true);
               if (field.getType().equals(String.class)) {
                   field.set(obj, value);
               } else if (field.getType().equals(Integer.class)) {
                   field.set(obj, Integer.parseInt(value));
               } // Add more data types as needed
           }
       }
       return obj;
   }
}
class Employee {
   private String name;
   private String id;
   // Constructor, getters, setters
   // ...
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
}


class Animal{
	private String kind;
	private String sound;
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getSound() {
		return sound;
	}
	public void setSound(String sound) {
		this.sound = sound;
	}
	
	
	
}

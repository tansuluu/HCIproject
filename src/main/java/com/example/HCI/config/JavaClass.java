package com.example.HCI.config;
import java.io.StringReader;
import java.io.StringWriter;



import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class JavaClass {
    public static void main(String[] args) throws Exception{

        StringWriter writer = new StringWriter();
        Address address = new Address("1234 Main Street", "San Francisco", "CA");
        Serializer serializer = new Persister();

        serializer.write(address, writer);
        System.out.println("Wrote: " + writer.getBuffer());

        String input = "<address street='1234 Main Street' city='San Francisco' state='CA'/>";
        Serializer serializer1 = new Persister();
        System.out.println("Read back: " + serializer1.read(Address.class, new StringReader(input)));
    }
}

@Root
class Address {
    @Attribute(name="street")
    private final String street;

    @Attribute(name="city")
    private final String city;

    @Attribute(name="state")
    private final String state;

    public Address(@Attribute(name="street") String street, @Attribute(name="city") String city, @Attribute(name="state") String state) {
        super();
        this.street = street;
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Address [city=" + city + ", state=" + state + ", street=" + street + "]";
    }
}
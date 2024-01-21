package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderContact {

    @DataProvider
    public Iterator<Object[]> example() {
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactSuccess() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{
                Contact.builder()
                        .name("Tony")
                        .lastName("Stark")
                        .address("NY")
                        .phone("356594645687")
                        .email("stark@gmail.com")
                        .description("all fields")
                        .build()
        });
        list.add(new
                Object[]{
                Contact.builder()
                        .name("TonyReq")
                        .lastName("Stark")
                        .address("NY")
                        .phone("356594463245987")
                        .email("stark@gmail.com")
                        .build()
        });

        return list.iterator();
    }

}
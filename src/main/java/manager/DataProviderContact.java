package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderContact {

    @DataProvider
    public Iterator<Object[]> example(){
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactSuccess(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastName("Brown")
                .phone("12345671534")
                .email("abc534@nv.com")
                .address("New-York")
                .description("Boss")
                .build()
        });

        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastName("Brow")
                .phone("12367153435")
                .email("abc1534@nv.com")
                .address("New-York")
                .description("Boss")
                .build()
        });
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactWrongPhone(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastName("White")
                .phone("1234567")
                .email("abc123@nv.com")
                .address("London")
                .build()
        });
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastName("White")
                .phone("1234567345623572340968")
                .email("abc123@nv.com")
                .address("London")
                .build()
        });
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastName("White")
                .phone("123WW4/567")
                .email("abc123@nv.com")
                .address("London")
                .build()
        });

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.csv")));
        String line = reader.readLine();
        while (line != null){
            String[] all = line.split(",");
            list.add(new Object[]{Contact.builder()
                    .name(all[0])
                    .lastName(all[1])
                    .email(all[2])
                    .phone(all[3])
                    .address(all[4])
                    .description(all[5])
                    .build()
            });
            line = reader.readLine();
        }
        return list.iterator();
    }
}

package data;

import dto.UserDtoLombok;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderReg {

    @DataProvider
    public Iterator<Object[]> positiveDataReg(){
        List<Object[]> list= new ArrayList<>();
        list.add(new Object[]{
                UserDtoLombok.builder()
                        .name("John")
                        .lastName("Doe")
                        .email("testqa20@gmail.com")
                        .password("123456Aa$")
                        .build()
        });

        list.add(new Object[]{
                UserDtoLombok.builder()
                        .name("Ned")
                        .lastName("Flanders")
                        .email("testqa30@gmail.com")
                        .password("123456Aa$")
                        .build()
        });
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> negativeEmailDataReg(){
        List<Object[]> list= new ArrayList<>();
        list.add(new Object[]{
                UserDtoLombok.builder()
                        .email("testqa20@gmailcom")
                        .password("123456Aa$")
                        .build()
        });

        list.add(new Object[]{
                UserDtoLombok.builder()
                        .email("testqa20gmail.com")
                        .password("123456Aa$")
                        .build()
        });
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]>regCSV(){
        List<Object[]> list = new ArrayList<>();
        String path = "src/test/resources/userdatareg.csv";
        try(BufferedReader reader = new BufferedReader(new FileReader(new File(path)))) {
            String line = reader.readLine();
            while(line!=null){
                String[] split = line.split(",");
                list.add(new Object[]{
                        UserDtoLombok.builder()
                                .name(split[0])
                                .lastName(split[1])
                                .email(split[2])
                                .password(split[3])
                                .build()
                });
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]>negativeRegCSV(){
        List<Object[]> list = new ArrayList<>();
        String path = "src/test/resources/negdatareg.csv";
        try(BufferedReader reader = new BufferedReader(new FileReader(new File(path)))) {
            String line = reader.readLine();
            while(line!=null){
                String[] split = line.split(",");
                list.add(new Object[]{
                        UserDtoLombok.builder()
                                .name(split[0])
                                .lastName(split[1])
                                .email(split[2])
                                .password(split[3])
                                .build()
                });
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.iterator();
    }
}

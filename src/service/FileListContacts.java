package service;

import java.io.*;
import java.util.ArrayList;

public class FileListContacts {
    private static volatile FileListContacts instance;
    public static FileListContacts getInstance(){
        if(instance ==null)
            instance = new FileListContacts();
        return instance;
    }
    private final File file = new File("contact.csv");

    public ArrayList<Contact> readFile() throws IOException {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            bufferedReader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Contact> phoneBooks = new ArrayList<>();
        String line = "";
        while((line = bufferedReader.readLine()) != null){
            String[] tokens = line.split(",");
            Contact contact = new Contact(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
            phoneBooks.add(contact);
        }
        return phoneBooks;
    }
    public void writeFile(ArrayList<Contact> phoneBook) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("Số điện thoại,Nhóm,Họ tên,Giới tính,Địa chỉ,Ngày sinh,Email");
        for(Contact contact : phoneBook){
            bufferedWriter.write(contact+"\n");
        }
    }
}

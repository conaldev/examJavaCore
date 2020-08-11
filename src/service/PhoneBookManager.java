package service;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBookManager {
    Scanner scanner = new Scanner(System.in);
    private FileListContacts fileListContacts;
    private static volatile PhoneBookManager intance;
    private static ArrayList<Contact> listContacts = new ArrayList<>();
    private final File file = new File("contact.csv");
    public static PhoneBookManager getInstance() {
        if (listContacts.isEmpty()) {
            try {
                listContacts = FileListContacts.getInstance().readFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (intance == null)
            intance = new PhoneBookManager();
        return intance;
    }

    public void addContact() {
        String phoneNumber;
        while(true) {
            System.out.print("Enter a phone number: ");
            phoneNumber = scanner.nextLine();
            Pattern patternP = Pattern.compile("^\\d{10}$");
            Matcher matcherP = patternP.matcher(phoneNumber);
            if (matcherP.matches())
                break;
        }
        System.out.print("Enter a group: ");
        String group = scanner.nextLine();
        System.out.print("Enter a fullname: ");
        String fullName = scanner.nextLine();
        String gender;
        while(true) {
            System.out.print("Enter a gender: ");
            gender = scanner.nextLine();
            if(gender.equals("male") || gender.equals("female"))
                break;
        }
        System.out.print("Enter a address: ");
        String address = scanner.nextLine();
        System.out.print("Enter a date of birth: ");
        String dateOfBirth = scanner.nextLine();
        String email;
        while(true) {
            System.out.print("Enter a email: ");
            email = scanner.nextLine();
            Pattern patternE = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
            Matcher matcher = patternE.matcher(email);
            if(matcher.matches())
                break;
        }
        Contact contact = new Contact(phoneNumber, group, fullName, gender, address, dateOfBirth, email);
        listContacts.add(contact);
    }

    public void editContact() {
        System.out.println("Enter your phone number you want to edit: ");
        String phoneNumber = scanner.nextLine();
        boolean havePhoneNumber =false;
        while (!phoneNumber.equals("\n")) {
            for (Contact contact : listContacts) {
                if (contact.getPhoneNumber().equals(phoneNumber)) {
                    havePhoneNumber = true;
                    System.out.print("Enter a group: ");
                    String group = scanner.nextLine();
                    System.out.print("Enter a fullname: ");
                    String fullName = scanner.nextLine();
                    String gender;
                    while(true) {
                        System.out.print("Enter a gender: ");
                        gender = scanner.nextLine();
                        if(gender.equals("male") || gender.equals("female"))
                            break;
                    }
                    System.out.print("Enter a address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter a date of birth: ");
                    String dateOfBirth = scanner.nextLine();
                    String email;
                    while(true) {
                        System.out.print("Enter a email: ");
                        email = scanner.nextLine();
                        Pattern patternE = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
                        Matcher matcher = patternE.matcher(email);
                        if(matcher.matches())
                            break;
                    }
                    contact.setGender(gender);
                    contact.setAddress(address);
                    contact.setGroup(group);
                    contact.setFullName(fullName);
                    contact.setDateOfBirth(dateOfBirth);
                    contact.setEmail(email);
                    break;
                }
            }
            if (!havePhoneNumber) {
                System.out.println("Không tìm được danh bạ với số điện thoại trên. Yêu cầu nhập lại!");
                phoneNumber = scanner.nextLine();
            } else break;
        }
    }
    public void findContact() {
        System.out.println("Enter phone number:");
        String phoneNumber = scanner.nextLine();
        for(Contact contact : listContacts){
            if(contact.getPhoneNumber().equals(phoneNumber)){
                System.out.println(contact);
                break;
            }
        }
    }
    public void deleteContact() {
        System.out.print("Enter your phone number you want to delete: ");
        String phoneNumber = scanner.nextLine();
        boolean havePhoneNumber =false;
        while (!phoneNumber.equals("\n")) {
            for (Contact contact : listContacts) {
                if (contact.getPhoneNumber().equals(phoneNumber)){
                    System.out.println("Are you sure want to delete this contact ? Y (for yes)?");
                    String choose = scanner.next();
                    if(choose.equals("Y") || choose.equals("y")){
                        listContacts.remove(contact);
                    }
                    havePhoneNumber = true;
                    break;
                }
            }
            if (!havePhoneNumber) {
                System.out.println("Không tìm được danh bạ với số điện thoại trên. Yêu cầu nhập lại!");
                phoneNumber = scanner.nextLine();
            } else break;
        }
    }

    public void displayPhoneBook() {
        for (Contact contact : listContacts) {
            System.out.println(contact);
        }
    }
    public void writeFile() throws IOException {
        BufferedWriter bufferedWriter = null;
            bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("Số điện thoại,Nhóm,Họ tên,Giới tính,Địa chỉ,Ngày sinh,Email\n");
        for (Contact contact : listContacts) {
            bufferedWriter.write(contact+"\n");
        }
        bufferedWriter.close();
    }
    public void readFile() throws IOException {
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
        listContacts = phoneBooks;
    }
}

import service.PhoneBookManager;

import java.io.IOException;
import java.util.Scanner;

public class MenuMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneBookManager phoneBookManager = new PhoneBookManager();
        while (true) {
            System.out.println("-----Chương Trình Quản Lý Danh Bạ-----");
            System.out.println("Chọn chức năng theo số (để tiếp tục): ");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Đọc từ file");
            System.out.println("7. Ghi vào file");
            System.out.println("8. Thoát");
            System.out.println("Chọn chức năng: ");
            int choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    PhoneBookManager.getInstance().displayPhoneBook();
                    break;
                case 2:
                    PhoneBookManager.getInstance().addContact();
                    break;
                case 3:
                    PhoneBookManager.getInstance().editContact();
                    break;
                case 4:
                    PhoneBookManager.getInstance().deleteContact();
                    break;
                case 5:
                    PhoneBookManager.getInstance().findContact();
                    break;
                case 6:
                    try {
                        PhoneBookManager.getInstance().readFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    try {
                        PhoneBookManager.getInstance().writeFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.out.println("Vui lòng chọn giá trị hợp lệ !");
            }
        }
    }
}

//ten:Do Tran Nhat Tan lop: 10DH-CNTT5 mssv: 1050080198
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Account {
	private double soTien;
    private int soTk;
    private String tenTk;
    private String trangThai;
    private String thongBao;
    public Account(double soTien, int soTk, String tenTk, String trangThai, String thongBao) {
        this.soTien = soTien;
        this.soTk = soTk;
        this.tenTk = tenTk;
        this.trangThai = trangThai;
        this.thongBao = thongBao;
    }    
    public Account(){
 
    }
 
    public double getsoTien() {
        return soTien;
    }
 
    public void setsoTien(double soTien) {
        if (soTien >= 50) {
            this.soTien = soTien;
        } else {
            this.soTien = 50;
            this.thongBao = "Số tiền không hợp lệ.";
        }
    }
 
    public int getsoTk() {
        return soTk;
    }
 
    public void setsoTk(int soTk) {
        if (soTk > 0 && soTk != 999999) {
            this.soTk = soTk;
        } else {
            this.soTk = 999999;
            this.thongBao = "số tài khoản không hợp lệ.";
        }
    }
 
    public String gettenTk() {
        return tenTk;
    }
 
    public void settenTk(String tenTk) {
        if (!tenTk.isEmpty()) {
            this.tenTk = tenTk;
        } else {
            this.tenTk = "chưa xác định";
            this.thongBao = "tên tài khoản không hợp lệ.";
        }
    }
    public String gettrangThai() {
        return trangThai;
    }
    public void settrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            soTien += amount;
            System.out.println("Nạp tiền thành công.");
        } else {
            System.out.println("Số tiền không hợp lệ.");
        }
    }
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= soTien) {
            soTien -= amount;
            System.out.println("Rút tiền thành công.");
            return true;
        } else {
            System.out.println("Số tiền muốn rút không hợp lệ hoặc không đủ.");
            return false;
        }
    }

    public boolean transfer(Account destinationAccount, double amount) {
        if (amount > 0 && amount <= soTien) {
            soTien -= amount;
            destinationAccount.soTien += amount;
            System.out.println("Chuyển tiền thành công.");
            return true;
        } else {
            System.out.println("Số tiền muốn chuyển không hợp lệ hoặc không đủ.");
            return false;
        }
    }

    @Override
    public String toString() {
        NumberFormat currencyEN = NumberFormat.getCurrencyInstance();
        String str1 = currencyEN.format(soTien);
        return soTien + "-"+soTk + "-"+tenTk + "-"+trangThai;
    }
 
    void thongBao(){
        System.out.printf("%5d %-20s %5d %10.2f %10s %8.5f \n ",soTien,soTk,tenTk,trangThai);
    }
    public double napTien() {
        double nap;
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số tiền bạn cần nạp: ");
        nap = sc.nextDouble();
        
        if (nap >= 0) {
            soTien = nap + soTien;
            NumberFormat currencyEN = NumberFormat.getCurrencyInstance();
            String str1 = currencyEN.format(nap);
            System.out.println("bạn vừa nạp " + str1 + " vào tài khoản.");
        } else {
            System.out.println("Số tiền nạp vào không hợp lệ!");
        }
        return nap;
    }
    public double rutTien(){
        double phi=1100;
        double rut;
        System.out.print("Nhập số tiền bạn cần rút: ");
        rut=sc.nextDouble();
        if(rut<=soTien){
            soTien=(soTien - phi - rut);
            NumberFormat currencyEN = NumberFormat.getCurrencyInstance();
            String str1 = currencyEN.format(rut);
            System.out.println("Bạn vừa rút " + str1 + "Đ từ tài khoản.");
        } else {
            System.out.println("Số tiền muốn rút không hợp lệ!");
            return rutTien();
        }
        return rut;
    }
    public boolean chuyenKhoan(Account accountNhan, double soTienChuyen) {
        if (this.soTien >= soTienChuyen) {
            this.soTien -= soTienChuyen;
            accountNhan.soTien += soTienChuyen;
            System.out.println("chuyển khoản thành công");
            return true;
        } else {
            System.out.println("số dư tài khoản không đủ");
            return false; 
        }
    }
    public double daoHan() {
        double laiSuat = 0.035;
        soTien = soTien + soTien * laiSuat;
        NumberFormat currencyEN = NumberFormat.getCurrencyInstance();
        String str1 = currencyEN.format(soTien);
        System.out.println("Bạn vừa được " + str1 + " từ đáo hạn 1 tháng");
        return setsoTien;
    }
    void inTk() {
        NumberFormat currencyEN = NumberFormat.getCurrencyInstance();
        String str1 = currencyEN.format(setsoTien);
        System.out.printf("%-10.2f %-20s %-20s \n", soTien, tenTk, str1);
    }
}

public class AccountList {
    private List<Account> accounts;

    public AccountList() {
        accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public int getAccountCount() {
        return accounts.size();
    }

    public void printAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("Không có tài khoản nào trong danh sách.");
        } else {
            for (Account account : accounts) {
                System.out.println(account.toString());
            }
        }
    }

    public boolean deposit(int accountNumber, double amount) {
        for (Account account : accounts) {
            if (account.getsoTk() == accountNumber) {
                account.deposit(amount);
                return true;
            }
        }
        return false;
    }

    public boolean withdraw(int accountNumber, double amount) {
        for (Account account : accounts) {
            if (account.getsoTk() == accountNumber) {
                return account.withdraw(amount);
            }
        }
        return false;
    }

    public boolean transfer(int sourceAccountNumber, int destinationAccountNumber, double amount) {
        Account sourceAccount = null;
        Account destinationAccount = null;

        for (Account account : accounts) {
            if (account.getsoTk() == sourceAccountNumber) {
                sourceAccount = account;
            }
            if (account.getsoTk() == destinationAccountNumber) {
                destinationAccount = account;
            }
        }

        if (sourceAccount != null && destinationAccount != null) {
            return sourceAccount.transfer(destinationAccount, amount);
        }

        return false;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountList list = new AccountList();

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("----- MENU -----");
            System.out.println("1. Thêm tài khoản");
            System.out.println("2. Số tài khoản hiện có");
            System.out.println("3. In thông tin tất cả tài khoản");
            System.out.println("4. Nạp tiền vào tài khoản");
            System.out.println("5. Rút tiền");
            System.out.println("6. Chuyển tiền");
            System.out.println("7. Kết thúc");
            System.out.print("Vui lòng chọn chức năng: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    
                    System.out.print("Nhập số tài khoản: ");
                    int soTk = scanner.nextInt();
                    System.out.print("Nhập tên tài khoản: ");
                    String tenTk = scanner.next();
                    System.out.print("Nhập số tiền: ");
                    double soTien = scanner.nextDouble();
                    Account account = new Account(soTien, soTk, tenTk, "", "");
                    list.addAccount(account);
                    System.out.println("Tài khoản đã được thêm thành công.");
                    break;
                case 2:
                    
                    int count = list.getAccountCount();
                    System.out.println("Số tài khoản hiện có: " + count);
                    break;
                case 3:
                    
                    list.printAllAccounts();
                    break;
                case 4:
                    
                    System.out.print("Nhập số tài khoản cần nạp tiền: ");
                    int napTk = scanner.nextInt();
                    System.out.print("Nhập số tiền cần nạp: ");
                    double napTien = scanner.nextDouble();
                    boolean napSuccess = list.deposit(napTk, napTien);
                    if (napSuccess) {
                        System.out.println("Nạp tiền thành công.");
                    } else {
                        System.out.println("Nạp tiền thất bại. Kiểm tra lại số tài khoản.");
                    }
                    break;
                case 5:
                    
                    System.out.print("Nhập số tài khoản cần rút tiền: ");
                    int rutTk = scanner.nextInt();
                    System.out.print("Nhập số tiền cần rút: ");
                    double rutTien = scanner.nextDouble();
                    boolean rutSuccess = list.withdraw(rutTk, rutTien);
                    if (rutSuccess) {
                        System.out.println("Rút tiền thành công.");
                    } else {
                        System.out.println("Rút tiền thất bại. Kiểm tra lại số tài khoản và số dư.");
                    }
                    break;
                case 6:
                    
                    System.out.print("Nhập số tài khoản nguồn: ");
                    int sourceTk = scanner.nextInt();
                    System.out.print("Nhập số tài khoản đích: ");
                    int destinationTk = scanner.nextInt();
                    System.out.print("Nhập số tiền cần chuyển: ");
                    double transferAmount = scanner.nextDouble();
                    boolean transferSuccess = list.transfer(sourceTk, destinationTk, transferAmount);
                    if (transferSuccess) {
                        System.out.println("Chuyển tiền thành công.");
                    } else {
                        System.out.println("Chuyển tiền thất bại. Kiểm tra lại số tài khoản và số dư.");
                    }
                    break;
                case 7:
                
                    isRunning = false;
                    System.out.println("Chương trình đã kết thúc.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        }
    }
}


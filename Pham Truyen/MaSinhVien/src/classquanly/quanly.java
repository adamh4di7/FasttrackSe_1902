package classquanly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class quanly {

	Scanner sc = new Scanner(System.in);
	public void dangnhap() {
		System.out.println("Nhap ten dang nhap: ");
		String user= sc.nextLine();
		System.out.println("Nhap mat khau: ");
		String pass= sc.nextLine();
		try {
			if(user.equals("") || pass.equals("")) {
				System.out.println("Vui long khong de trong");
			} else {
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlydaotao?useUnicode=true&characterEncoding=utf-8","root","");
				PreparedStatement ps = con.prepareStatement("SELECT * From users where username=? And password=?");
				ps.setString(1, user);
				ps.setString(2, pass);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					System.out.println("Dang nhap thanh cong");

					if (rs.getString(3).equalsIgnoreCase("giangvien")) {
						System.out.println( "Chao giang vien: " + rs.getString(4));
						hienthidanhsachsinhvien();
					} else {
						System.out.println( "Chao can bo: " + rs.getString(4));
						while (true) {
							menu();
						}
					}
				} else {
					System.out.println("Dang nhap khong thanh cong");
				}
			}
		} catch (Exception e) {
			
		}
	}
	public void hienthidanhsachsinhvien() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlydaotao?useUnicode=true&characterEncoding=utf-8","root","");
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * From students");
			ResultSet rs = preparedStatement.executeQuery("SELECT * From students");
			while(rs.next()) {
				System.out.println(
						"Mã sinh viên: "+	rs.getString(1)+
						" Tên sinh viên: "+ rs.getString(2)+
						" ID Lớp: "+ rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void menu() {
		System.out.println("=====Menu quản lý sinh viên=====");
		System.out.println("1. Xem danh sách");
		System.out.println("2. Thêm mới sinh viên");
		System.out.println("3. Sửa thông tin sinh viên");
		System.out.println("4. Xóa sinh viên");
		System.out.println("5. Thoát");
		System.out.println("Thực thi: ");
		int chon = Integer.parseInt(sc.nextLine());
		switch (chon) {
		case 1:
			hienthidanhsachsinhvien();
			break;
		case 2:
			themmoi();
			break;
		case 3:
			xua();
			break;
		case 4:
			xoa();
			break;	
		case 5:
			sc.close();
			System.exit(0);
			break;	
		default:
			break;
		}
	}
	public void themmoi() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlydaotao?useUnicode=true&characterEncoding=utf-8","root","");
			PreparedStatement preparedStatement = connection.prepareStatement("insert into students values(?,?,?)");
			System.out.println("Nhập mã sinh viên: ");
			String masv= sc.nextLine();
			System.out.println("Tên sinh viên: ");
			String hoten= sc.nextLine();
			System.out.println("ID lớp: ");
			String idlop= sc.nextLine();
			preparedStatement.setString(1, masv);
			preparedStatement.setString(2, hoten);
			preparedStatement.setString(3, idlop);
			preparedStatement.executeUpdate();
			System.out.println("Thêm thành công");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void xua() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlydaotao?useUnicode=true&characterEncoding=utf-8","root","");
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * From students where MaSV=?");
			System.out.println("Nhập mã sv muốn cập nhật: ");
			String nhapmasv = sc.nextLine();
			preparedStatement.setString(1, nhapmasv);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				System.out.println("Mã SV: " + rs.getString(1));	
				System.out.println("Tên Sinh Viên: "+rs.getString(2));
				System.out.println("Mã Lớp: "+ rs.getString(3));
				System.out.println("Cập nhật lại mã sv: ");
				String masv = sc.nextLine();
				System.out.println("Cập nhật tên sinh viên: ");
				String hoten= sc.nextLine();
				System.out.println("Cập nhật ID lớp: ");
				String idlop= sc.nextLine();
				PreparedStatement pr = connection.prepareStatement("UPDATE students SET MaSV=?,HoTenSV=?,IDLop=? where MaSV=?");
				pr.setString(1, masv);
				pr.setString(2, hoten);
				pr.setString(3, idlop);
				pr.setString(4, nhapmasv);
				pr.executeUpdate();
				System.out.println("Cập nhật thành công");
			} else {
				System.out.println("Sai Mã");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void xoa() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlydaotao?useUnicode=true&characterEncoding=utf-8","root","");
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM students where MaSV=?");
			System.out.println("Nhập mã sv muốn xóa: ");
			String nhapmasv = sc.nextLine();
			preparedStatement.setString(1, nhapmasv);
			preparedStatement.executeUpdate();
			System.out.println("Đã xóa sv có mã: " + nhapmasv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package Integracion.Cliente;

import Negocio.Cliente.TCliente;
import Negocio.Cliente.TDistribuidor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.HashSet;

public class DAOCliente implements IDAOCliente {

	Connection con;
	
	public DAOCliente() {
		System.out.println("Intentando Conexión - DAOCliente");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scheam", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Conexión Realizada - DAOCliente");
	}
	
	public Integer create(TCliente cliente) {
		System.out.println("Intentando create - DAOCliente");
		try {
			Statement stmt = con.createStatement();
			PreparedStatement ps;
			String sql = "INSERT INTO clientes (nombre, email, activo) VALUES (?,?,?);";
			ps = con.prepareStatement(sql);
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getEmail());
			ps.setBoolean(3, true);
			

			ps.executeUpdate();
			stmt.close();
			con.close();
			System.out.println("Create Realizado - DAOCliente");
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
	
	public Integer create(TDistribuidor distribuidor) {
		System.out.println("Intentando create - DAOCliente");
		try {
			Statement stmt = con.createStatement();
			PreparedStatement ps;
			String sql = "INSERT INTO clientes (nombre, email, activo, direccion, CIF) VALUES (?,?,?,?,?);";
			ps = con.prepareStatement(sql);
			ps.setString(1, distribuidor.getNombre());
			ps.setString(2, distribuidor.getEmail());
			ps.setBoolean(3, true);
			ps.setString(4, distribuidor.getDireccion());
			ps.setString(5, distribuidor.getCIF());
			

			ps.executeUpdate();
			stmt.close();
			con.close();
			System.out.println("Create Realizado - DAOCliente");
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}

	public Integer delete(Integer idcliente) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Integer modify(TCliente cliente) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Set mostrarClientes() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public TCliente mostrarClienteID(Integer idcliente) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}
package Integracion.Cliente;

import Negocio.Cliente.TCliente;
import Negocio.Cliente.TDistribuidor;
import Negocio.Cliente.TParticular;
import Negocio.Empleado.TEmpleado;

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
			
			sql = "select id_cliente from clientes where nombre = ?";
			ps = (PreparedStatement) con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			cliente.setID(rs.getInt(1));

			rs.close();
			
			if(cliente instanceof TDistribuidor) {
				sql = "INSERT INTO distribuidores (ID, CIf, direccion) VALUES (?,?,?);";
				ps = con.prepareStatement(sql);
				ps.setInt(1, cliente.getID());
				ps.setString(2, ((TDistribuidor) cliente).getCIF());
				ps.setString(3, ((TDistribuidor) cliente).getDireccion());
				ps.executeUpdate();
			} else if (cliente instanceof TParticular){
				sql = "INSERT INTO particulares (ID, DNI, telefono) VALUES (?,?,?);";
				ps = con.prepareStatement(sql);
				ps.setInt(1, cliente.getID());
				ps.setString(2, ((TParticular) cliente).getDNI());
				ps.setInt(3, ((TParticular) cliente).getTelefono());
				ps.executeUpdate();
			}
			ps.close();
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
		System.out.println("Intentando Delete - DAOCliente");
		try {
			Statement stmt = con.createStatement();
			PreparedStatement ps;
			String sql = "UPDATE clientes set activo = false where id_cliente = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, idcliente);
			ps.executeUpdate();
			
			ps.close();
			stmt.close();
			con.close();
			System.out.println("Delete Realizado - DAOCliente");
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
	
	public Integer modify(TCliente cliente) {
		System.out.println("Intentando Modify - DAOCliente");
		try {
			Statement stmt = con.createStatement();
			PreparedStatement ps;
			
			String sql = "UPDATE clientes set nombre = ?, email = ?, activo = ? where id_cliente = ?";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getEmail());
			ps.setBoolean(3, cliente.getActivo());
			ps.setInt(4, cliente.getID());
			
			if(cliente instanceof TDistribuidor) {
				sql = "UPDATE distribuidores set direccion = ?, CIF = ? where id_cliente = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, ((TDistribuidor) cliente).getDireccion());
				ps.setString(2, ((TDistribuidor) cliente).getCIF());
				ps.setInt(3, cliente.getID());
				ps.executeUpdate();
				
			} else if(cliente instanceof TParticular){
				sql = "UPDATE particulares set DNI = ?, telefono = ? where id_empleado = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, ((TParticular) cliente).getDNI());
				ps.setInt(2, ((TParticular) cliente).getTelefono());
				ps.setInt(3, cliente.getID());
				ps.executeUpdate();
			}			
			ps.close();
			stmt.close();
			con.close();
			
			System.out.println("Modify Realizado - DAOCliente");
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}

	public Set<TCliente> mostrarClientes() {
		System.out.println("Intentando readAll - DAOCliente");
		Set<TCliente> result = new HashSet<TCliente>();
		TCliente aux;
		//TParticular particular;
		//TDistribuidor distribuidor;
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM clientes WHERE activo");
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				return result;
			} else {
				aux = new TCliente();
				if(aux instanceof TDistribuidor) {
					TDistribuidor distribuidor = (TDistribuidor) aux;
					distribuidor = new TDistribuidor();
					distribuidor.setID(rs.getInt("id_cliente"));
					distribuidor.setNombre(rs.getString("nombre"));
					distribuidor.setEmail("email");
					distribuidor.setCIF(rs.getString("CIF"));
					distribuidor.setDireccion(rs.getString("direccion"));;
					result.add(distribuidor);
				} else {
					TParticular particular = (TParticular) aux;
					particular = new TParticular();
					particular.setID(rs.getInt("id_cliente"));
					particular.setNombre(rs.getString("nombre"));
					particular.setEmail("email");
					particular.setDNI(rs.getString("DNI"));
					particular.setTelefono(rs.getInt("telefono"));;
					result.add(particular);
				}
				while (rs.next()) {
					aux = new TCliente();
					if(aux instanceof TDistribuidor) {
						TDistribuidor distribuidor = (TDistribuidor) aux;
						distribuidor = new TDistribuidor();
						distribuidor.setID(rs.getInt("id_cliente"));
						distribuidor.setNombre(rs.getString("nombre"));
						distribuidor.setEmail("email");
						distribuidor.setCIF(rs.getString("CIF"));
						distribuidor.setDireccion(rs.getString("direccion"));;
						result.add(distribuidor);
					} else {
						TParticular particular = (TParticular) aux;
						particular = new TParticular();
						particular.setID(rs.getInt("id_cliente"));
						particular.setNombre(rs.getString("nombre"));
						particular.setEmail("email");
						particular.setDNI(rs.getString("DNI"));
						particular.setTelefono(rs.getInt("telefono"));;
						result.add(particular);
					}
				}
				rs.close();
				ps.close();
				con.close();
			}
			
			System.out.println("Readall realizado - DAOCliente");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public TCliente mostrarClienteID(Integer idcliente) {
		System.out.println("Intentando readByID - DAOCliente");
		TCliente result = new TCliente();
		try {
			PreparedStatement ps = con.prepareStatement("select * from empleados where id_cliente = ?");
			ps.setInt(1, idcliente);

			ResultSet rs = ps.executeQuery();

			if (!rs.next())
				result.setID(-1);
			else {
				if(result instanceof TDistribuidor) {
					TDistribuidor distribuidor = (TDistribuidor) result;
					distribuidor.setID(rs.getInt("id_cliente"));
					distribuidor.setNombre(rs.getString("nombre"));
					distribuidor.setEmail(rs.getString("email"));
					distribuidor.setCIF(rs.getString("CIF"));
					distribuidor.setDireccion(rs.getString("direccion"));
					distribuidor.setActivo(rs.getBoolean("activo"));
				} else {
					TParticular particular = (TParticular) result;
					particular.setID(rs.getInt("id_cliente"));
					particular.setNombre(rs.getString("nombre"));
					particular.setEmail(rs.getString("email"));
					particular.setDNI(rs.getString("DNI"));
					particular.setTelefono(rs.getInt("telefono"));
					particular.setActivo(rs.getBoolean("activo"));
				}				
			}
			
			rs.close();
			ps.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("ReadybyDNI realizado - DAOCliente");
		return result;
	}

	
}
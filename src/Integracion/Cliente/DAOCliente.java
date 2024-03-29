package Integracion.Cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Negocio.Cliente.TCliente;
import Negocio.Cliente.TDistribuidor;
import Negocio.Cliente.TParticular;

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
			// Statement stmt = con.createStatement();
			PreparedStatement ps;
			String sql = "INSERT INTO clientes (nombre, email, activo) VALUES (?,?,?);";
			ps = con.prepareStatement(sql);
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getEmail());
			ps.setBoolean(3, true);

			ps.executeUpdate();

			ps.close();

			cliente.setID(readByEmail(cliente.getEmail()).getID());

			if (cliente instanceof TDistribuidor) {
				PreparedStatement ps1;
				sql = "INSERT INTO distribuidores (ID, CIF, direccion) VALUES (?,?,?);";
				ps1 = con.prepareStatement(sql);
				ps1.setInt(1, cliente.getID());
				ps1.setString(2, ((TDistribuidor) cliente).getCIF());
				ps1.setString(3, ((TDistribuidor) cliente).getDireccion());
				ps1.executeUpdate();
				ps1.close();
			} else if (cliente instanceof TParticular) {
				PreparedStatement ps2;
				sql = "INSERT INTO particulares (ID, DNI, telefono) VALUES (?,?,?);";
				ps2 = con.prepareStatement(sql);
				ps2.setInt(1, cliente.getID());
				ps2.setString(2, ((TParticular) cliente).getDNI());
				ps2.setInt(3, ((TParticular) cliente).getTelefono());
				ps2.executeUpdate();
				ps2.close();
			}
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		System.out.println("Create Realizado - DAOCliente");
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
			PreparedStatement ps;

			String sql = "UPDATE clientes set nombre = ?, email = ?, activo = ? where id_cliente = ?";
			ps = con.prepareStatement(sql);

			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getEmail());
			ps.setBoolean(3, cliente.getActivo());
			ps.setInt(4, cliente.getID());
			ps.executeUpdate();

			if (cliente instanceof TDistribuidor) {
				sql = "UPDATE distribuidores set direccion = ?, CIF = ? where ID = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, ((TDistribuidor) cliente).getDireccion());
				ps.setString(2, ((TDistribuidor) cliente).getCIF());
				ps.setInt(3, cliente.getID());
				ps.executeUpdate();

			} else if (cliente instanceof TParticular) {
				sql = "UPDATE particulares set DNI = ?, telefono = ? where ID = ?";
				PreparedStatement ps1 = con.prepareStatement(sql);
				ps1.setString(1, ((TParticular) cliente).getDNI());
				ps1.setInt(2, ((TParticular) cliente).getTelefono());
				ps1.setInt(3, cliente.getID());
				ps1.executeUpdate();
				ps1.close();
			}
			ps.close();
			con.close();

			System.out.println("Modify Realizado - DAOCliente");
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}

	public Set<TCliente> readAll() {
		System.out.println("Intentando readAll - DAOCliente");
		Set<TCliente> result = new HashSet<TCliente>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM clientes WHERE activo");
			PreparedStatement psdist = con.prepareStatement("SELECT * FROM distribuidores");
			PreparedStatement pspart = con.prepareStatement("SELECT * FROM particulares");
			ResultSet rs = ps.executeQuery();
			ResultSet rsdist = psdist.executeQuery();
			ResultSet rspart = pspart.executeQuery();
			List<Integer> iddist = new ArrayList<Integer>();
			List<TDistribuidor> dist = new ArrayList<TDistribuidor>();
			List<Integer> idpart = new ArrayList<Integer>();
			List<TParticular> part = new ArrayList<TParticular>();

			while (rsdist.next()) { // getting all ids in distribuidores table
				iddist.add(rsdist.getInt("ID"));
				TDistribuidor distaux = new TDistribuidor();
				distaux.setID(rsdist.getInt("ID"));
				distaux.setCIF(rsdist.getString("CIF"));
				distaux.setDireccion(rsdist.getString("direccion"));
				dist.add(distaux);
			}

			while (rspart.next()) { // getting all ids in particulares table
				idpart.add(rspart.getInt("ID"));
				TParticular partaux = new TParticular();
				partaux.setID(rspart.getInt("ID"));
				partaux.setDNI(rspart.getString("DNI"));
				partaux.setTelefono(rspart.getInt("telefono"));
				part.add(partaux);
			}

			if (!rs.next()) {
				return result;
			} else {
				if (iddist.contains(rs.getInt("id_cliente"))) {
					TDistribuidor auxdist = new TDistribuidor();
					auxdist.setID(rs.getInt("id_cliente"));
					auxdist.setNombre(rs.getString("nombre"));
					auxdist.setEmail(rs.getString("email"));
					auxdist.setCIF(dist.get(iddist.indexOf(auxdist.getID())).getCIF());
					auxdist.setDireccion(dist.get(iddist.indexOf(auxdist.getID())).getDireccion());
					result.add(auxdist);
				} else if (idpart.contains(rs.getInt("id_cliente"))) {
					TParticular auxpart = new TParticular();
					auxpart.setID(rs.getInt("id_cliente"));
					auxpart.setNombre(rs.getString("nombre"));
					auxpart.setEmail(rs.getString("email"));
					auxpart.setDNI(part.get(idpart.indexOf(auxpart.getID())).getDNI());
					auxpart.setTelefono(part.get(idpart.indexOf(auxpart.getID())).getTelefono());
					result.add(auxpart);
				}

				while (rs.next()) {
					if (iddist.contains(rs.getInt("id_cliente"))) {
						TDistribuidor auxdist = new TDistribuidor();
						auxdist.setID(rs.getInt("id_cliente"));
						auxdist.setNombre(rs.getString("nombre"));
						auxdist.setEmail(rs.getString("email"));
						auxdist.setCIF(dist.get(iddist.indexOf(auxdist.getID())).getCIF());
						auxdist.setDireccion(dist.get(iddist.indexOf(auxdist.getID())).getDireccion());
						result.add(auxdist);
					} else if (idpart.contains(rs.getInt("id_cliente"))) {
						TParticular auxpart = new TParticular();
						auxpart.setID(rs.getInt("id_cliente"));
						auxpart.setNombre(rs.getString("nombre"));
						auxpart.setEmail(rs.getString("email"));
						auxpart.setDNI(part.get(idpart.indexOf(auxpart.getID())).getDNI());
						auxpart.setTelefono(part.get(idpart.indexOf(auxpart.getID())).getTelefono());
						result.add(auxpart);
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

	public TCliente readByID(Integer idcliente) {
		System.out.println("Intentando readByID - DAOCliente");
		TCliente result = new TCliente();
		try {
			PreparedStatement ps = con.prepareStatement("select * from clientes where id_cliente = ?");
			ps.setInt(1, idcliente);

			ResultSet rs = ps.executeQuery();

			if (!rs.next())
				result.setID(-1);
			else {
				result.setID(rs.getInt("id_cliente"));
				result.setNombre(rs.getString("nombre"));
				result.setEmail(rs.getString("email"));
				result.setActivo(rs.getBoolean("activo"));
				try {
					ps = con.prepareStatement("select * from distribuidores where ID = ?");
					ps.setInt(1, idcliente);

					rs = ps.executeQuery();

					if (!rs.next()) {
						try {
							ps = con.prepareStatement("select * from particulares where ID = ?");
							ps.setInt(1, idcliente);

							rs = ps.executeQuery();
							TParticular part = new TParticular();
							if(rs.next()) {
								part.setID(result.getID());
								part.setNombre(result.getNombre());
								part.setActivo(result.getActivo());
								part.setEmail(result.getEmail());
								part.setTelefono(rs.getInt("telefono"));
								part.setDNI(rs.getString("DNI"));
							}
							result = part;
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} else {
						TDistribuidor dist = new TDistribuidor();

						dist.setID(result.getID());
						dist.setNombre(result.getNombre());
						dist.setActivo(result.getActivo());
						dist.setEmail(result.getEmail());
						dist.setCIF(rs.getString("CIF"));
						dist.setDireccion(rs.getString("direccion"));

						result = dist;
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("ReadybyID realizado - DAOCliente");
		return result;
	}

	public TCliente readByEmail(String email) {
		System.out.println("Intentando readByEmail - DAOEquipo");
		TCliente result = new TCliente();

		try {
			PreparedStatement ps = con.prepareStatement("select * from clientes where email = ?");
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
				result.setID(-1);
			} else {
				result.setID(rs.getInt("id_cliente"));
				result.setNombre(rs.getString("nombre"));
				result.setEmail(rs.getString("email"));
				result.setActivo(rs.getBoolean("activo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return result;
	}
}

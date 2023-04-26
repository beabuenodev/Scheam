package Negocio.Producto;

import java.util.Set;

import Integracion.Factorias.FactoriaDAOImp;

public class SAProducto implements ISAProducto {

	public Integer altaProducto(TProducto producto) {
		System.out.println("Intentando altaProducto - SAProducto");
		TProducto emp = FactoriaDAOImp.getInstance().getDaoProducto().readById(producto.getIdproyecto());

		if (emp.getIdproyecto() == -1) {
			System.out.println("altaProducto Realizado (creado) - SAProducto");
			return FactoriaDAOImp.getInstance().getDaoProducto().create(producto);
		} else {
			if (emp.getActivo()) {
				System.out.println("altaProducto No Realizado (existe y activo) - SAProducto");
				return -1;
			} else {
				producto.setActivo(true);
				producto.setIdproyecto(emp.getIdproyecto());
				System.out.println("altaProducto Realizado (reactivado) - SAProducto");
				FactoriaDAOImp.getInstance().getDaoProducto().modify(producto);
				return 2;
			}
		}
	}

	public Integer bajaProducto(Integer IDProducto) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Integer modificarProducto(TProducto producto) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Set listarProductos() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public TProducto mostrarProductoID(Integer IDProducto) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Integer cerrarProducto(Integer IDProducto) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}

package Presentacion.Controlador;

import java.util.Set;
import java.util.List;

import Negocio.Cliente.TCliente;
import Negocio.Empleado.TEmpleado;
import Negocio.Equipo.TEquipo;
import Negocio.Equipo.TVinculacion;
import Negocio.Factorias.FactoriaSA;
import Negocio.Factura.SAFactura;
import Negocio.Factura.TFactura;
import Negocio.Factura.TLineaFactura;
import Negocio.Producto.TProducto;
import Negocio.Tareas.TTarea;
import Presentacion.IGUI;
import Presentacion.Factorias.FactoriaVistas;

public class ControladorImp extends Controlador {
	
	//SAFACTURA
	
	private IGUI gui;
	
	private SAFactura safactura = FactoriaSA.getInstance().getSAFactura();

    @SuppressWarnings("unchecked")
	public void update(int event, Object objeto) {
    	int res;
    	
        switch (event){
        //MAIN WINDOW
        case Eventos.MainWindow:
        	System.out.println("Entrando a MainWindowOpen - Controlador");
        	gui = FactoriaVistas.getInstance().generateFrame(event, null);
        	gui.update(event, null);
        	break;
        	
        //EMPLEADO
        case Eventos.MainWindowEmpleado:
        	System.out.println("Entrando a MainWindowEmpleadoOpen - Controlador");
        	gui = FactoriaVistas.getInstance().generateFrame(event, null);
        	gui.update(event, null);
            break;
        case Eventos.VistaAltaEmpleado:
        	System.out.println("Entrando a VistaAltaEmpleado - Controlador");
        	gui = FactoriaVistas.getInstance().generateFrame(event, null);
        	gui.update(event, null);
            break;
        case Eventos.AltaEmpleado:
        	System.out.println("Entrando a AltaEmpleado - Controlador");
        	res = FactoriaSA.getInstance().getSAEmpleado().altaEmpleado((TEmpleado) objeto);
        	if (res == -1) gui.update(Eventos.AltaEmpleadoNoOK, null); 
        	else if (res == 2) gui.update(Eventos.AltaEmpleadoOKReactivar, null);
        	else gui.update(Eventos.AltaEmpleadoOK, null);
        	break;
        case Eventos.VistaBajaEmpleado:
        	System.out.println("Entrando a VistaBajaEmpleado - Controlador");
        	gui = FactoriaVistas.getInstance().generateFrame(event, null);
        	gui.update(event, null);
        	break;
        case Eventos.BajaEmpleado:
        	System.out.println("Entrando a BajaEmpleado - Controlador");
        	res = FactoriaSA.getInstance().getSAEmpleado().bajaEmpleado((Integer) objeto);
        	if (res == -1) gui.update(Eventos.BajaEmpleadoNoOK, null);
        	else if(res == -2) gui.update(Eventos.BajaEmpleadoNoOK2, null);
        	else if(res == -3) gui.update(Eventos.BajaEmpleadoNoOK3, null);
        	else gui.update(Eventos.BajaEmpleadoOK, objeto);
        	break;
        case Eventos.ListarEmpleado:
        	System.out.println("Entrando a ListarEmpleados - Controlador");
        	Set<TEmpleado> lista = FactoriaSA.getInstance().getSAEmpleado().listarEmpleados();
        	gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaListarEmpleado, null);
        	gui.update(event, lista);
        	gui.update(Eventos.VistaListarEmpleado, null);
        	break;
        case Eventos.VistaModificarEmpleado:
        	System.out.println("Entrando a VistaModificarEmpleados - Controlador");
        	gui = FactoriaVistas.getInstance().generateFrame(event, null);
        	gui.update(event, null);
        	break;
        case Eventos.ModificarEmpleado:
        	System.out.println("Entrando a ModificarEmpleados - Controlador");
        	res = FactoriaSA.getInstance().getSAEmpleado().modificarEmpleado((TEmpleado) objeto);
        	if (res == -1) gui.update(Eventos.ModificarEmpleadoNoOK, null);
        	else if (res == -2) gui.update(Eventos.ModificarEmpleadoDNINoOK, null);
        	else gui.update(Eventos.ModificarEmpleadoOK, null);
        	break;
        case Eventos.VistaFormMostrarEmpleadoID:
        	System.out.println("Entrando a VistaFormMostrarEmpleadoID - Controlador");
        	gui = FactoriaVistas.getInstance().generateFrame(event, null);
        	gui.update(event,null);
        	break;
        case Eventos.VistaMostrarEmpleadoID:
        	System.out.println("Entrando a VistaMostrarEmpleadoID - Controlador");
        	gui = FactoriaVistas.getInstance().generateFrame(event, null);
        	gui.update(event, null);
        	break;
        case Eventos.MostrarEmpleadoID:
        	System.out.println("Entrando a MostrarEmpleadoID - Controlador");
        	TEmpleado emp = FactoriaSA.getInstance().getSAEmpleado().mostrarEmpleadoID((int) objeto);
        	if(emp.getIdEmpleado() == -1) {
        		gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaMostrarEmpleadoID, null);
        		gui.update(Eventos.MostrarEmpleadoIDNoOK, null);
        	}
        	else {
            	gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaMostrarEmpleadoID, null);
	        	gui.update(event,  emp);
	        	gui.update(Eventos.VistaMostrarEmpleadoID, null);
        	}
        	break;
        case Eventos.VistaFormListarIntegrantesEquipoId:
        	System.out.println("Entrando a VistaFormListarInegrantesEquipoId - Controlador");
        	gui = FactoriaVistas.getInstance().generateFrame(event, null);
        	gui.update(event,null);
        	break;
        case Eventos.VistaListarIntegrantesEquipo:
        	System.out.println("Entrando a VistaListarIntegrantesEquipo - Controlador");
        	gui = FactoriaVistas.getInstance().generateFrame(event, null);
        	gui.update(event, null);
        	break;
        case Eventos.ListarIntegrantesEquipo:
        	System.out.println("Entrando a ListarIntegrantesEquipo - Controlador");
        	Set<TEmpleado> list = FactoriaSA.getInstance().getSAEmpleado().listarIntegrantesIdEquipo((Integer) objeto);
        	if (list == null) {
        		gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaListarIntegrantesEquipo, null);
        		gui.update(Eventos.ListarIntegrantesEquipoNoOk, null);
        	} else {
        		gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaListarIntegrantesEquipo, null);
        		gui.update(event, list);
        		gui.update(Eventos.VistaListarIntegrantesEquipo, null);
        	}
        	break;
        	
        //EQUIPO
        case Eventos.MainWindowEquipo:
        	System.out.println("Entrando a MainWindowEquipo - Controlador");
        	gui = FactoriaVistas.getInstance().generateFrame(event, null);
        	gui.update(event, null);
        	break;
        case Eventos.VistaAltaEquipo:
        	System.out.println("Entrando a VistaAltaEquipo - Controlador");
        	gui = FactoriaVistas.getInstance().generateFrame(event, null);
        	gui.update(event, null);
        	break;
        case Eventos.AltaEquipo:
        	System.out.println("Entrando a AltaEquipo - Controlador");
        	res = FactoriaSA.getInstance().getSAEquipo().altaEquipo((TEquipo) objeto);
        	if (res == -1) gui.update(Eventos.AltaEquipoNoOK, null); 
        	else if (res == 2) gui.update(Eventos.AltaEquipoOKReactivar, null);
        	else gui.update(Eventos.AltaEquipoOK, null);
        	break;
        	
        case Eventos.VistaBajaEquipo:     	
        	System.out.println("Entrando a VistaBajaEquipo - Controlador");
        	gui = FactoriaVistas.getInstance().generateFrame(event, null);
        	gui.update(event, null);        	    	
        	break;
        
        case Eventos.BajaEquipo:
        	System.out.println("Entrando a BajaEquipo - Controlador");
        	res = FactoriaSA.getInstance().getSAEquipo().bajaEquipo((Integer) objeto);
        	if (res == -1) gui.update(Eventos.BajaEquipoNoOK, null);
        	else if(res == -2) gui.update(Eventos.BajaEquipoNoOK2, null);
        	else if(res == -3) gui.update(Eventos.BajaEquipoNoOK3, null);
        	else if(res == -4) gui.update(Eventos.BajaEquipoNoOK4, null);
        	else gui.update(Eventos.BajaEquipoOK, objeto);
        	break;
        	
        case Eventos.VistaModificarEquipo:
        	System.out.println("Entrando a VistaModificarEquipo - Controlador");
        	gui = FactoriaVistas.getInstance().generateFrame(event, null);
        	gui.update(event, null);        	    	
        	break;
        
        case Eventos.ModificarEquipo:
        	System.out.println("Entrando a ModificarEquipo - Controlador");
        	res = FactoriaSA.getInstance().getSAEquipo().modificarEquipo((TEquipo) objeto);
        	if (res == -1) gui.update(Eventos.ModificarEquipoNoOK, null);
        	else if (res == -2) gui.update(Eventos.ModificarEquipoNombreNoOK, null);
        	else gui.update(Eventos.ModificarEquipoOK, null);
        	break;
        case Eventos.VistaFormMostrarEquipoID:
        	System.out.println("Entrando a VistaFormMostrarEquipoID - Controlador");
        	gui = FactoriaVistas.getInstance().generateFrame(event, null);
        	gui.update(event,null);
        	break;
        case Eventos.VistaMostrarEquipoID:
        	System.out.println("Entrando a VistaMostrarEquipoID - Controlador");
        	gui = FactoriaVistas.getInstance().generateFrame(event, null);
        	gui.update(event, null);
        	break;
        case Eventos.MostrarEquipoID:
        	System.out.println("Entrando a MostrarEmpleadoID - Controlador");
        	TEquipo equ = FactoriaSA.getInstance().getSAEquipo().mostrarEquipoID((int) objeto);
        	if(equ.getIdEquipo() == -1) {
        		gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaMostrarEquipoID, null);
        		gui.update(Eventos.MostrarEquipoIDNoOK, null);
        	}
        	else {
            	gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaMostrarEquipoID, null);
	        	gui.update(event,  equ);
	        	gui.update(Eventos.VistaMostrarEquipoID, null);
        	}
        	break;
        case Eventos.ListarEquipos:
	    	System.out.println("Entrando a ListarEquipos - Controlador");
	    	Set<TEquipo> listaEq = FactoriaSA.getInstance().getSAEquipo().listarEquipos();
	    	gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaListarEquipos, null);
	    	gui.update(event, listaEq);
	    	gui.update(Eventos.VistaListarEquipos, null);
	    	break;
        case Eventos.VistaAnyadirIntegrante:
        	System.out.println("Entrando a VistaAnyadirIntegrante - Controlador");
        	gui = FactoriaVistas.getInstance().generateFrame(event, null);
        	gui.update(event, null);
        	break;
        case Eventos.AnyadirIntegrante:	
        	System.out.println("Entrando a AnyadirIntegrante - Controlador");
        	res = FactoriaSA.getInstance().getSAEquipo().anyadirIntegrante((TVinculacion) objeto);
        	if(res==-1) gui.update(Eventos.AnyadirIntegranteNoOk3, null);
        	else if(res==-2) gui.update(Eventos.AnyadirIntegranteNoOk2, null);
        	else if(res==-3) gui.update(Eventos.AnyadirIntegranteNoOk4, null);
        	else  gui.update(Eventos.AnyadirIntegranteOk, null);
			break;
        case Eventos.VistaRetirarIntegrante:
        	System.out.println("Entrando a VistaRetirarIntegrante - Controlador");
        	gui = FactoriaVistas.getInstance().generateFrame(event, null);
        	gui.update(event, null);
        	break;
        case Eventos.RetirarIntegrante:
        	System.out.println("Entrando a RetirarIntegrante - Controlador");
        	res = FactoriaSA.getInstance().getSAEquipo().retirarIntegrante((TVinculacion) objeto);
        	if(res==-1) gui.update(Eventos.RetirarIntegranteNoOk, null);
        	else  gui.update(Eventos.RetirarIntegranteOk, null);
			break;
        case Eventos.VistaFormListarEquiposDeIntegranteId:
        	System.out.println("Entrando a VistaFormListarEquiposDeIntegranteId - Controlador");
        	gui = FactoriaVistas.getInstance().generateFrame(event, null);
        	gui.update(event,null);
        	break;
        case Eventos.VistaListarEquiposDeIntegranteId:
        	System.out.println("Entrando a VistaListarEquiposDeIntegranteId - Controlador");
        	gui = FactoriaVistas.getInstance().generateFrame(event, null);
        	gui.update(event, null);
        	break;
        case Eventos.ListarEquiposDeIntegranteId:
	    	System.out.println("Entrando a ListarEquipos - Controlador");
	    	Set<TEquipo> listaEqInt = FactoriaSA.getInstance().getSAEquipo().listarEquiposEmpleadoId((Integer) objeto);
	    	if (listaEqInt == null) {
        		gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaListarEquiposDeIntegranteId, null);
        		gui.update(Eventos.ListarEquiposDeIntegranteIdNoOk, null);
        	} else {
        		gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaListarEquiposDeIntegranteId, null);
        		gui.update(event, listaEqInt);
        		gui.update(Eventos.VistaListarEquiposDeIntegranteId, null);
        	}
	    	break;
	    	
        //PRODUCTO
	    case Eventos.MainWindowProducto:
	    	System.out.println("Entrando a MainWindowProductoOpen - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event, null);
	        break;
	    case Eventos.VistaAltaProducto:
	    	System.out.println("Entrando a VistaAltaProducto - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event, null);
	        break;
	    case Eventos.AltaProducto:
	    	System.out.println("Entrando a AltaProducto - Controlador");
	    	res = FactoriaSA.getInstance().getSAProducto().altaProducto((TProducto) objeto);
	    	if (res == -1) gui.update(Eventos.AltaProductoNoOK, null); 
	    	else if (res == 2) gui.update(Eventos.AltaProductoOKReactivar, null);
	    	else gui.update(Eventos.AltaProductoOK, null);
	    	break;
	    case Eventos.VistaBajaProducto:
	    	System.out.println("Entrando a VistaBajaProducto - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event, null);
	    	break;
	    case Eventos.BajaProducto:
	    	System.out.println("Entrando a BajaProducto - Controlador");
	    	res = FactoriaSA.getInstance().getSAProducto().bajaProducto((Integer) objeto);
	    	if (res == -1) gui.update(Eventos.BajaProductoNoOK, null);
	    	else if(res == -2) gui.update(Eventos.BajaProductoNoOK2, null);
	    	else if(res == -3)	gui.update(Eventos.BajaProductoNoOK3, null);
	    	else gui.update(Eventos.BajaProductoOK, objeto);
	    	break;
	    case Eventos.ListarProducto:
	    	System.out.println("Entrando a ListarProductos - Controlador");
	    	Set<TProducto> listaPr = FactoriaSA.getInstance().getSAProducto().listarProductos();
	    	gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaListarProducto, null);
	    	gui.update(event, listaPr);
	    	gui.update(Eventos.VistaListarProducto, null);
	    	break;
	    case Eventos.VistaModificarProducto:
	    	System.out.println("Entrando a VistaModificarProductos - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event, null);
	    	break;
	    case Eventos.ModificarProducto:
	    	System.out.println("Entrando a ModificarProductos - Controlador");
	    	res = FactoriaSA.getInstance().getSAProducto().modificarProducto((TProducto) objeto);
	    	if (res == -1) gui.update(Eventos.ModificarProductoNoOK, null);
	    	else if (res == -2) gui.update(Eventos.ModificarProductoNombreNoOK, null);
	    	else if (res == -3) gui.update(Eventos.ModificarProductoActivoNoOK, null);
	    	else gui.update(Eventos.ModificarProductoOK, null);
	    	break;
	    case Eventos.VistaFormMostrarProductoID:
	    	System.out.println("Entrando a VistaFormMostrarProductoID - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event,null);
	    	break;
	    case Eventos.VistaMostrarProductoID:
	    	System.out.println("Entrando a VistaMostrarProductoID - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event, null);
	    	break;
	    case Eventos.MostrarProductoID:
	    	System.out.println("Entrando a MostrarProductoID - Controlador");
	    	TProducto pro = FactoriaSA.getInstance().getSAProducto().mostrarProductoID((int) objeto);
	    	if(pro.getIdproyecto() == -1) {
	    		gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaMostrarProductoID, null);
	    		gui.update(Eventos.MostrarProductoIDNoOK, null);
	    	}
	    	else {
	        	gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaMostrarProductoID, null);
	        	gui.update(event,  pro);
	        	gui.update(Eventos.VistaMostrarProductoID, null);
	    	}
	    	break;
	    case Eventos.VistaCerrarProducto:
	    	System.out.println("Entrando a VistaCerrarProducto - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event, null);
	    	break;
	    case Eventos.CerrarProducto:
	    	System.out.println("Entrando a CerrarProducto - Controlador");
	    	res = FactoriaSA.getInstance().getSAProducto().cerrarProducto((Integer) objeto);
	    	if (res == -1) gui.update(Eventos.CerrarProductoNoOK, null);
	    	else if(res == -2) gui.update(Eventos.CerrarProductoNoOK2, null);
	    	else if(res == -3) gui.update(Eventos.CerrarProductoNoOK3, null);
	    	else gui.update(Eventos.CerrarProductoOK, objeto);
	    	break;
	    	
	    	
	    //CLIENTE
	    case Eventos.MainWindowCliente:
	    	System.out.println("Entrando a MainWindowClienteOpen - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event, null);
	        break;
	    case Eventos.VistaAltaCliente:
	    	System.out.println("Entrando a VistaAltaCliente - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event, null);
	        break;
	    case Eventos.AltaCliente:
	    	System.out.println("Entrando a AltaCliente - Controlador");
	    	res = FactoriaSA.getInstance().getSACliente().altaCliente((TCliente) objeto);
	    	if (res == -1) gui.update(Eventos.AltaClienteNoOK, null); 
	    	else if (res == 2) gui.update(Eventos.AltaClienteOKReactivar, null);
	    	else gui.update(Eventos.AltaClienteOK, null);
	    	break;
	    case Eventos.VistaBajaCliente:
	    	System.out.println("Entrando a VistaBajaCliente - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event, null);
	    	break;
	    case Eventos.BajaCliente:
	    	System.out.println("Entrando a BajaCliente - Controlador");
	    	res = FactoriaSA.getInstance().getSACliente().bajaCliente((Integer) objeto);
	    	if (res == -1) gui.update(Eventos.BajaClienteNoOK, null);
	    	else if(res == -2) gui.update(Eventos.BajaClienteNoOK2, null);
	    	else gui.update(Eventos.BajaClienteOK, objeto);
	    	break;
	    case Eventos.ListarCliente:
	    	System.out.println("Entrando a ListarCliente - Controlador");
	    	Set<TCliente> listaCl = FactoriaSA.getInstance().getSACliente().mostrarClientes();
	    	gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaListarCliente, null);
	    	gui.update(event, listaCl);
	    	gui.update(Eventos.VistaListarCliente, null);
	    	break;
	    case Eventos.VistaModificarCliente:
	    	System.out.println("Entrando a VistaModificarClientes - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event, null);
	    	break;
	    case Eventos.ModificarCliente:
	    	System.out.println("Entrando a ModificarClientes - Controlador");
	    	res = FactoriaSA.getInstance().getSACliente().modificarCliente((TCliente) objeto);
	    	if (res == -1) gui.update(Eventos.ModificarClienteNoOK, null);
	    	else if (res == -2) gui.update(Eventos.ModificarClienteEmailNoOK, null);
	    	else if (res == -3) gui.update(Eventos.ModificarClienteActivoNoOK, null);
	    	else gui.update(Eventos.ModificarClienteOK, null);
	    	break;
	    case Eventos.VistaFormMostrarClienteID:
	    	System.out.println("Entrando a VistaFormMostrarClienteID - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event,null);
	    	break;
	    case Eventos.VistaMostrarClienteID:
	    	System.out.println("Entrando a VistaMostrarClienteID - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event, null);
	    	break;
	    case Eventos.MostrarClienteID:
	    	System.out.println("Entrando a MostrarClienteID - Controlador");
	    	TCliente cli = FactoriaSA.getInstance().getSACliente().mostrarClienteID((int) objeto);
	    	if(cli.getID() == -1) {
	    		gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaMostrarClienteID, null);
	    		gui.update(Eventos.MostrarClienteIDNoOK, null);
	    	}
	    	else {
	        	gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaMostrarClienteID, null);
	        	gui.update(event,  cli);
	        	gui.update(Eventos.VistaMostrarClienteID, null);
	    	}
	    	break;
	    	
	    //TAREA
	    case Eventos.MainWindowTarea:
	    	System.out.println("Entrando a MainWindowTareaOpen - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event, null);
	        break;
	    case Eventos.VistaAltaTarea:
	    	System.out.println("Entrando a VistaAltaTarea - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event, null);
	        break;
	    case Eventos.AltaTarea:
	    	System.out.println("Entrando a AltaTarea - Controlador");
	    	res = FactoriaSA.getInstance().getSATarea().altaTarea((TTarea) objeto);
	    	if (res == -1) gui.update(Eventos.AltaTareaNoOK, null); 
	    	else if (res == 2) gui.update(Eventos.AltaTareaOKReactivar, null);
	    	else gui.update(Eventos.AltaTareaOK, null);
	    	break;
	    case Eventos.VistaBajaTarea:
	    	System.out.println("Entrando a VistaBajaTarea - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event, null);
	    	break;
	    case Eventos.BajaTarea:
	    	System.out.println("Entrando a BajaTarea - Controlador");
	    	res = FactoriaSA.getInstance().getSATarea().bajaTarea((Integer) objeto);
	    	if (res == -1) gui.update(Eventos.BajaTareaNoOK, null);
	    	else if(res == -2) gui.update(Eventos.BajaTareaNoOK2, null);
	    	else gui.update(Eventos.BajaTareaOK, objeto);
	    	break;
	    case Eventos.ListarTareas:
	    	System.out.println("Entrando a ListarTarea - Controlador");
	    	Set<TTarea> listaTar = FactoriaSA.getInstance().getSATarea().listarTareas();
	    	gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaListarTareas, null);
	    	gui.update(event, listaTar);
	    	gui.update(Eventos.VistaListarTareas, null);
	    	break;
	    case Eventos.VistaModificarTarea:
	    	System.out.println("Entrando a VistaModificarTareas - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event, null);
	    	break;
	    case Eventos.ModificarTarea:
	    	System.out.println("Entrando a ModificarTareas - Controlador");
	    	res = FactoriaSA.getInstance().getSATarea().modificarTarea((TTarea) objeto);
	    	if (res == -1) gui.update(Eventos.ModificarTareaNoOK, null);
	    	else if (res == -2) gui.update(Eventos.ModificarTareaNombreNoOK, null);
	    	else if (res == -3) gui.update(Eventos.ModificarTareaActivoNoOK, null);
	    	else gui.update(Eventos.ModificarTareaOK, null);
	    	break;
	    case Eventos.VistaFormMostrarTareasID:
	    	System.out.println("Entrando a VistaFormMostrarClienteID - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event,null);
	    	break;
	    case Eventos.VistaMostrarTareasID:
	    	System.out.println("Entrando a VistaMostrarTareasID - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event, null);
	    	break;
	    case Eventos.MostrarTareasID:
	    	System.out.println("Entrando a MostrarTareasID - Controlador");
	    	TTarea tar = FactoriaSA.getInstance().getSATarea().mostrarTareaID((int) objeto);
	    	if(tar.getIdTarea() == -1) {
	    		gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaMostrarTareasID, null);
	    		gui.update(Eventos.MostrarTareasIDNoOK, null);
	    	}
	    	else {
	        	gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaMostrarTareasID, null);
	        	gui.update(event,  tar);
	        	gui.update(Eventos.VistaMostrarTareasID, null);
	    	}
	    	break;
	    case Eventos.VistaCerrarTarea:
	    	System.out.println("Entrando a VistaCerrarTarea - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event, null);
	    	break;
	    case Eventos.CerrarTarea:
	    	System.out.println("Entrando a CerrarTarea - Controlador");
	    	res = FactoriaSA.getInstance().getSATarea().cerrarTarea((Integer) objeto);
	    	if (res == -1) gui.update(Eventos.CerrarTareaNoOK, null);
	    	else if(res == -2) gui.update(Eventos.CerrarTareaNoOK2, null);
	    	else gui.update(Eventos.CerrarTareaOK, objeto);
	    	break;
	    case Eventos.VistaFormListarTareasEquipoId:
        	System.out.println("Entrando a VistaFormListarTareasEquipoId - Controlador");
        	gui = FactoriaVistas.getInstance().generateFrame(event, null);
        	gui.update(event,null);
        	break;
	     case Eventos.VistaListarTareasEquipoId:
        	System.out.println("Entrando a VistaListarTareasEquipoId - Controlador");
        	gui = FactoriaVistas.getInstance().generateFrame(event, null);
        	gui.update(event, null);
        	break;
	     case Eventos.ListarTareasEquipoId:
	        	System.out.println("Entrando a ListarTareasEquipoId - Controlador");
	        	Set<TTarea> listat = FactoriaSA.getInstance().getSATarea().listarTareasEquipo((Integer) objeto);
	        	if (listat == null) {
	        		gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaListarTareasEquipoId, null);
	        		gui.update(Eventos.ListarTareasEquipoIdNoOK, null);
	        	} else {
	        		gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaListarTareasEquipoId, null);
	        		gui.update(event, listat);
	        		gui.update(Eventos.VistaListarTareasEquipoId, null);
	        	}
	        	break;
	     case Eventos.VistaFormListarTareasProductoId:
        	System.out.println("Entrando a VistaFormListarTareasProductoId - Controlador");
        	gui = FactoriaVistas.getInstance().generateFrame(event, null);
        	gui.update(event, null);
        	break;
	     case Eventos.VistaListarTareasProductoId:
	        	System.out.println("Entrando a VistaListarTareasProductoId - Controlador");
	        	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	        	gui.update(event, null);
	        break;
	     case Eventos.ListarTareasProductoId:
	        	System.out.println("Entrando a ListarTareasProductoId - Controlador");
	        	Set<TTarea> listap = FactoriaSA.getInstance().getSATarea().listarTareasProducto((Integer) objeto);
	        	if (listap == null) {
	        		gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaListarTareasProductoId, null);
	        		gui.update(Eventos.ListarTareasProductoIdNoOK, null);
	        	} else {
	        		gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaListarTareasProductoId, null);
	        		gui.update(event, listap);
	        		gui.update(Eventos.VistaListarTareasProductoId, null);
	        	}
	        	break;
	    //FACTURA
        	
	     case Eventos.MainWindowFactura:
	    	System.out.println("Entrando a MainWindowFactura - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event, null);
	    	break;
	     case Eventos.VistaCrearCarrito:
	    	System.out.println("Entrando a VistaCrearCarrito - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event, null);
	    	break;
	     case Eventos.CrearCarrito:
		    System.out.println("Entrando a CrearCarrito - Controlador"); 
	    	res = safactura.crearCarrito((Integer) objeto);
	    	if (res == -1) gui.update(Eventos.CrearCarritoNoOk, null);
	    	else if (res == -2) gui.update(Eventos.NecesitasCarrito, null);
	    	else gui.update(Eventos.CrearCarritoOk, null);
	    	break;
	     case Eventos.CerrarCarrito:
	    	System.out.println("Entrando a CerrarCarrito - Controlador"); 
	    	res = safactura.cerrarCarrito();
	    	if (res == -1) gui.update(Eventos.CerrarCarritoNoOK, null);
	    	else if (res == -2) gui.update(Eventos.NecesitasCarrito, null);
	    	else gui.update(Eventos.CerrarCarritoOK, null);
	    	break;
	     case Eventos.EliminarCarrito:
		    System.out.println("Entrando a EliminarCarrito - Controlador");
		    res = safactura.eliminarCarrito();
		    if (res == -1) gui.update(Eventos.NecesitasCarrito, null);
		    else gui.update(Eventos.EliminarCarritoOk, null);
		    break;
	     case Eventos.VistaAnyadirProductoCarrito:
	    	System.out.println("Entrando a VistaA�adirProductoCarrito - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event, null);
	    	break;
	     case Eventos.AnyadirProductoCarrito:
		    System.out.println("Entrando a A�adirProductoCarrito - Controlador");
		    List<Integer> pair = (List<Integer>) objeto;
		    res = safactura.anyadirProductoaCarrito(pair.get(0), pair.get(1));
		    if (res == -1) gui.update(Eventos.AnyadirProductoCarritoNoOk, null);
	    	else if (res == -2) gui.update(Eventos.NecesitasCarrito, null);
		    else gui.update(Eventos.AnyadirProductoCarritoOk, objeto);
		    break;
	     case Eventos.MostrarCarrito:
			System.out.println("Entrando a MostrarCarrito - Controlador");
	    	Set<TLineaFactura> listafact = safactura.mostrarCarrito();
	    	if (listafact == null) {
	    		gui.update(Eventos.NecesitasCarrito, null);
	    	} else {
		    	gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaMostrarCarrito, null);
		    	gui.update(event, listafact);
		    	gui.update(Eventos.VistaMostrarCarrito, null);
	    	}
	    	break;
	     case Eventos.VistaEliminarProductoCarrito:
	    	System.out.println("Entrando a VistaEliminarProductoCarrito - Controlador");
	    	gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	gui.update(event, null);
	    	break;
	     case Eventos.EliminarProductoCarrito:
		    System.out.println("Entrando a EliminarProductocarrito - Controlador");
		    List<Integer> pair2 = (List<Integer>) objeto;
		    res = safactura.eliminarProductodeCarrito(pair2.get(0), pair2.get(1));
		    if (res == -1) gui.update(Eventos.EliminarProductoCarritoNoOk, null);
	    	else if (res == -2) gui.update(Eventos.NecesitasCarrito, null);
		    else gui.update(Eventos.EliminarProductoCarritoOk, objeto);
		    break;
	     case Eventos.VistaFormMostrarFacturaID:
	    	 System.out.println("Entrando a VistaFormMostrarFacturaID - Controlador");
	    	 gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	 gui.update(event, null);
	    	 break;
	     case Eventos.MostrarFacturaID:
	    	System.out.println("Entrando a MostrarFacturaID Controlador");
        	TFactura fact = safactura.mostrarFacturaID((int) objeto);
        	if(fact.getIdFactura() == -1) {
        		gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaMostrarFacturaID, null);
        		gui.update(Eventos.MostrarFacturaIDNoOK, null);
        	}
        	else {
            	gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaMostrarFacturaID, null);
	        	gui.update(event,  fact);
	        	gui.update(Eventos.VistaMostrarFacturaID, null);
        	}
        	break;
	     case Eventos.VistaFormMostrarFacturasIDCliente:
	    	 System.out.println("Entrando a VistaFormMostrarFacturasIDCliente - Controlador");
	    	 gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	 gui.update(event, null);
	    	 break;
	     case Eventos.VistaMostrarFacturasIDCliente:
	    	 System.out.println("Entrando a VistaMostrarFacturasIDCliente - Controlador");
	    	 gui = FactoriaVistas.getInstance().generateFrame(event, null);
	    	 gui.update(event, null);
	    	 break;
	     case Eventos.MostrarFacturasIDCliente:
	    	 System.out.println("Entrando a MostrarFacturasIDCliente - Controlador");
        	Set<TFactura> list2 = safactura.listarFacturasIDCliente((int) objeto);
        	if (list2 == null) {
        		gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaMostrarFacturasIDCliente, null);
        		gui.update(Eventos.MostrarFacturasIDClienteNoOk, null);
        	} else {
        		gui = FactoriaVistas.getInstance().generateFrame(Eventos.VistaMostrarFacturasIDCliente, null);
        		gui.update(event, list2);
        		gui.update(Eventos.VistaMostrarFacturasIDCliente, null);
        	}
        	break;
	    }
    }
}
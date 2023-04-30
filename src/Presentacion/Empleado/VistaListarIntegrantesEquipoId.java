
package Presentacion.Empleado;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;

import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

public class VistaListarIntegrantesEquipoId extends JFrame implements IGUI {
	private JPanel contentPane;
	private JTextField idtextfield;
	
	boolean cerrar = true;
	
	
	public VistaListarIntegrantesEquipoId() {
		vListarIntegrantesEquipoId();
	}
	
	public void vListarIntegrantesEquipoId() {
		setMinimumSize(new Dimension(500, 360));
		setTitle("Mostrar Integrantes Equipo");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VistaListarIntegrantesEquipoId.class.getResource("/icons/empleados/listar_integrantes_equipo-removebg-preview.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel titlepanel = new JPanel();
		contentPane.add(titlepanel, BorderLayout.NORTH);
		
		JLabel logo = new JLabel("      Mostrar Integrantes Equipo");
		logo.setFont(new Font("Tahoma", Font.BOLD, 25));
		logo.setIcon(new ImageIcon(new ImageIcon(VistaListarIntegrantesEquipoId.class.getResource("/icons/empleados/listar_integrantes_equipo-removebg-preview.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		titlepanel.add(logo);
		
		JPanel infopanel = new JPanel();
		contentPane.add(infopanel, BorderLayout.CENTER);
		infopanel.setLayout(null);
		
		JLabel idlabel = new JLabel("ID:");
		idlabel.setBounds(111, 94, 37, 14);
		infopanel.add(idlabel);
		
		idtextfield = new JTextField();
		idtextfield.setBounds(158, 92, 215, 17);
		infopanel.add(idtextfield);
		idtextfield.setColumns(10);
		
		JButton okbutton = new JButton("Ok");
		okbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ok();
				dispose();
			}
		});
		okbutton.setBounds(143, 144, 90, 23);
		infopanel.add(okbutton);
		
		JButton cancelbutton = new JButton("Cancelar");
		cancelbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		cancelbutton.setBounds(250, 144, 90, 23);
		infopanel.add(cancelbutton);
	}

	private void ok() {
		Integer id_equipo = Integer.parseInt(idtextfield.getText());
		Controlador.getInstance().update(Eventos.ListarIntegrantesEquipo, id_equipo);
		if (cerrar) 
			dispose();
		 else 
			cerrar = true;
	}
	@Override
	public void update(int event, Object object) {
		switch(event) {
		case Eventos.VistaListarIntegrantesEquipo:
			setVisible(true);
			break;
		case Eventos.ListarIntegrantesEquipoOK:
			Controlador.getInstance().update(Eventos.MainWindowEmpleado, null);
			break;
		case Eventos.ListarIntegrantesEquipoNoOK:
			JOptionPane.showMessageDialog(null, "El id_equipo no existe o el equipo está vacio");			
			Controlador.getInstance().update(Eventos.VistaFormMostrarEmpleadoID, null);
			break;
		}
	}
}
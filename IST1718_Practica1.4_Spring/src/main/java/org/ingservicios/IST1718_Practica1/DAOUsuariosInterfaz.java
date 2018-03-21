package org.ingservicios.IST1718_Practica1;

import java.util.List;

public interface DAOUsuariosInterfaz {

	public List<DTOUsuarios> leeUsuarios();
	
	public boolean buscaUsuario(String nombre, String password);

	public void addUsuario(String nombre, String password, String email, String dni);
	
}

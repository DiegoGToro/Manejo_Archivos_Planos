package interfaces;

public interface ICRUD<T> {
	public void IngresarUsuario(Object T);
	public void ModificarUsuario(int idModificar);
	public void EliminarUsuario(int idEliminar);
	public void ListarUsuarios();
	public void ListarIDS();
	public void ConsultarUsuario(int idConsultar);
	public boolean ConsultarID(int idConsultar);
}

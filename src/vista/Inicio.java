package vista;

import java.util.Scanner;

import bean.Usuario;
import logica.lASecuenciales;
import logica.lAaleatorios;

public class Inicio {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		MenuPrincipal menu = new MenuPrincipal();
		byte opt = -1, opt2= -1;
		int ID=0;

		while (opt != 0) {
			menu.menuPrincipal();
			opt = teclado.nextByte();
			Usuario usuario = new Usuario();
			switch (opt) {
			case 1:
				do{
					menu.menuSecuencial();
					opt2 = teclado.nextByte();
					lASecuenciales LSecuencial = new lASecuenciales();
					switch (opt2) {
					case 1: PedirDatos DatosUsuario = new PedirDatos();
					usuario = DatosUsuario.DatosUsuarioSecuencial();
					LSecuencial.IngresarUsuario(usuario);
					break;
					case 2:	LSecuencial.ListarIDS();
					System.out.println("Ingrese ID a modificar: ");
					ID = DatoID();
					LSecuencial.ModificarUsuario(ID);
					break;
					case 3: LSecuencial.ListarUsuarios();
					System.out.println("\nIngrese ID a Eliminar: ");
					ID = DatoID();
					LSecuencial.EliminarUsuario(ID);
					break;
					case 4: LSecuencial.ListarUsuarios();
					break;
					case 5: LSecuencial.ListarIDS();
					System.out.println("Ingrese ID a consultar: ");
					ID = DatoID();
					LSecuencial.ConsultarUsuario(ID);
					break;
					case 6:	LSecuencial.SecuencialAAleatorio();
					break;
					case 7:	LSecuencial.SecuencialASerializado();
					break;
					case 0:	System.out.println("Regresando al Menu Principal...");
					break;
					default: System.out.println("Esa opcion no es valida, por favor ingresa otra opcion...");
					break;
					}
				}while(opt2 != 0);
				break;
			case 2:
				do {
					menu.menuAleatorio();
					opt2 = teclado.nextByte();
					lAaleatorios LAleatorio = new lAaleatorios();
					switch (opt2) {
					case 1: PedirDatos DatosUsuario = new PedirDatos();
					usuario = DatosUsuario.DatosUsuarioAleatorio();
					LAleatorio.IngresarUsuario(usuario);
					break;
					case 2: LAleatorio.ListarIDS();
					System.out.println("Ingrese ID a modificar: ");
					ID = DatoID();
					LAleatorio.ModificarUsuario(ID);
					break;
					case 3: LAleatorio.ListarUsuarios();
					System.out.println("Ingrese ID a Eliminar: ");
					ID = DatoID();
					LAleatorio.EliminarUsuario(ID);
					break;
					case 4: LAleatorio.ListarUsuarios();
					break;
					case 5: LAleatorio.ListarIDS();
					System.out.println("Ingrese ID a consultar: ");
					ID = DatoID();
					LAleatorio.ConsultarUsuario(ID);
					break;
					case 6: LAleatorio.AleatorioASecuencial();
					break;
					case 7: LAleatorio.AleatorioASerializado();
					break;
					case 0:	System.out.println("Regresando al Menu Principal...");
					break;
					default: System.out.println("Esa opcion no es valida, por favor ingresa otra opcion...");
					break;
					}
				}while(opt2 != 0);
				break;
			case 0: System.out.println("Gracias por visitarnos...");
			break;
			default: System.out.println("Esa opcion no es valida, por favor ingresa otra opcion...");
			break;
			}
		}
		teclado.close();
	}


	public static int DatoID() {
		Scanner teclado = new Scanner(System.in);
		int id = teclado.nextInt();

		return id;
	}
}

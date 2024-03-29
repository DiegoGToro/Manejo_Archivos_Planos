package vista;

import java.time.LocalDate;
import java.util.Scanner;

import bean.Usuario;
import logica.lASecuenciales;
import logica.lAaleatorios;

public class PedirDatos {
	public Usuario DatosUsuarioSecuencial() {
		lASecuenciales logicaS = new lASecuenciales();
		Usuario usuario = new Usuario();
		Scanner teclado = new Scanner(System.in);
		boolean valid = false;
		System.out.println("----- Ingresar Usuario -----");
		do {
			if (valid == true) {
				System.out.println("\nEl Usuario con el ID " + usuario.getId() + " ya se encuentra registrado."
						+ "\nPor favor ingresa otra Cedula.\n");
			}
			System.out.print("Digite la Cédula del Usuario: ");
			usuario.setId(teclado.nextInt());
			valid =logicaS.ConsultarID(usuario.getId());
		} while (valid == true);
		System.out.print("Digite el Nombre del Usuario: ");
		teclado.nextLine();
		usuario.setNombre(teclado.nextLine());
		System.out.print("Digite el Salario del Usuario: ");
		usuario.setSalario(teclado.nextLong());
		System.out.print("Digite el año de nacimiento del Usuario (AAAA-MM-DD): ");
		usuario.setFechaNacimiento(LocalDate.parse(teclado.next()));

		return usuario;
	}

	public Usuario DatosUsuarioAleatorio() {
		lAaleatorios logicaA = new lAaleatorios();
		Usuario usuario = new Usuario();
		Scanner teclado = new Scanner(System.in);
		boolean valid = false;
		System.out.println("----- Ingresar Usuario -----");
		do {
			if (valid == true) {
				System.out.println("\nEl Usuario con el ID " + usuario.getId() + " ya se encuentra registrado."
						+ "\nPor favor ingresa otra Cedula.\n");
			}
			System.out.print("Digite la Cédula del Usuario: ");
			usuario.setId(teclado.nextInt());
			valid =logicaA.ConsultarID(usuario.getId());
		} while (valid == true);
		System.out.print("Digite el Nombre del Usuario: ");
		teclado.nextLine();
		usuario.setNombre(teclado.nextLine());
		System.out.print("Digite el Salario del Usuario: ");
		usuario.setSalario(teclado.nextLong());
		System.out.print("Digite el año de nacimiento del Usuario (AAAA-MM-DD): ");
		usuario.setFechaNacimiento(LocalDate.parse(teclado.next()));

		return usuario;
	}
}

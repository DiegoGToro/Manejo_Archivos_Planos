package logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import bean.Usuario;
import interfaces.ICRUD;

public class lASecuenciales implements ICRUD<Usuario>{

	private String ubiArchivo = "./datos/ArchivosSecuenciales/usuariosSecuencial.dat";

	@Override
	public void IngresarUsuario(Object usuario) {
		try {
			FileWriter archivo = new FileWriter(ubiArchivo,true);
			BufferedWriter escribir = new BufferedWriter(archivo);
			PrintWriter linea = new PrintWriter(escribir);
			linea.append(((Usuario) usuario).getId() + "," + ((Usuario) usuario).getNombre() + "," + ((Usuario) usuario).getSalario()+ ","
					+ ((Usuario) usuario).getFechaNacimiento()+ "\n");
			linea.close();
			System.out.println("Guardado...");
		} catch (IOException e) {}
	}

	@Override
	public void ModificarUsuario(int idModificar) {
		Scanner teclado = new Scanner(System.in);
		Usuario usuario = new Usuario();
		ArrayList<String> registrosUsuarios = new ArrayList<String>();
		try {
			FileReader datosLeidos = new FileReader(ubiArchivo);
			BufferedReader bufferdatos = new BufferedReader(datosLeidos);
			String linea = "";
			while (linea != null) {
				linea = bufferdatos.readLine();
				if (linea != null) {
					registrosUsuarios.add(linea);
				}
			}
			datosLeidos.close();
			FileWriter archivo = new FileWriter(ubiArchivo);
			BufferedWriter escribir = new BufferedWriter(archivo);
			PrintWriter lineaUsuario = new PrintWriter(escribir);
			Iterator<String> apuntadorListaUsuarios = registrosUsuarios.iterator();
			while (apuntadorListaUsuarios.hasNext()) {
				String itemApuntador = apuntadorListaUsuarios.next();
				int ubicacion = itemApuntador.indexOf(",");
				int wcedula = Integer.parseInt(itemApuntador.substring(0, ubicacion));
				if (wcedula != idModificar) {
					lineaUsuario.append(itemApuntador + "\n");
				}else {
					System.out.println("ItemApuntador: " + itemApuntador);
					itemApuntador = wcedula+",";
					usuario.setId(wcedula);
					System.out.println("Nuevo Nombre: ");
					itemApuntador += teclado.nextLine() + ",";
					System.out.println("Nuevo Salario: ");
					itemApuntador += teclado.nextLong() + ",";
					System.out.println("Nueva Fecha de Nacimiento (AAAA-MM-DD): ");
					usuario.setFechaNacimiento(LocalDate.parse(teclado.next()));
					itemApuntador += usuario.getFechaNacimiento();
					lineaUsuario.append(itemApuntador + "\n");
				}
			}
			lineaUsuario.close();
			archivo.close();
		} catch (IOException e) {}
	}

	@Override
	public void EliminarUsuario(int idEliminar) {
		ListarUsuarios();
		ArrayList<String> registrosEmpleados = new ArrayList<String>();
		try {
			FileReader datosLeidos = new FileReader(ubiArchivo);
			BufferedReader bufferdatos = new BufferedReader(datosLeidos);
			String linea = "";
			while (linea != null) {
				linea = bufferdatos.readLine();
				if (linea != null) {
					registrosEmpleados.add(linea);
				}
			}
			datosLeidos.close();
			FileWriter archivo = new FileWriter(ubiArchivo);
			BufferedWriter escribir = new BufferedWriter(archivo);
			PrintWriter lineaUsuario = new PrintWriter(escribir);
			Iterator<String> apuntadorListaEmpleados = registrosEmpleados.iterator();
			while (apuntadorListaEmpleados.hasNext()) {
				String itemApuntador = apuntadorListaEmpleados.next();
				int ubicacion = itemApuntador.indexOf(",");
				int wcedula = Integer.parseInt(itemApuntador.substring(0, ubicacion));
				if (wcedula != idEliminar) {
					lineaUsuario.append(itemApuntador + "\n");
				}
			}
			lineaUsuario.close();
			archivo.close();
		} catch (IOException e) {}
	}

	@Override
	public void ListarUsuarios() {
		try
		{
			System.out.println("----- Listar Usuarios -----");

			FileReader datosLeidos = new FileReader(ubiArchivo);
			try {
				BufferedReader bufferdatos = new BufferedReader(datosLeidos);
				String linea = "";
				while (linea != null){
					linea = bufferdatos.readLine();
					if(linea != null){
						System.out.println(linea);
					}
				}
				bufferdatos.close();
			}catch(IOException e) {}
		}catch(IOException e) {}
	}

	@Override
	public void ListarIDS() {
		ArrayList<String> registrosUsuarios = new ArrayList<String>();
		try
		{
			System.out.println("----- Listando IDS disponibles -----");

			FileReader datosLeidos = new FileReader(ubiArchivo);
			BufferedReader bufferdatos = new BufferedReader(datosLeidos);
			String linea = "";
			while (linea != null){
				linea = bufferdatos.readLine();
				if(linea != null){
					registrosUsuarios.add(linea);
				}
			}
			datosLeidos.close();
			Iterator<String> apuntadorListaUsuarios = registrosUsuarios.iterator();
			while (apuntadorListaUsuarios.hasNext()) {
				String itemApuntador = apuntadorListaUsuarios.next();
				int ubicacion = itemApuntador.indexOf(",");
				int wcedula = Integer.parseInt(itemApuntador.substring(0, ubicacion));
				System.out.println(wcedula);
			}
		}catch(IOException e) {}
	}

	@Override
	public void ConsultarUsuario(int idListar) {
		System.out.println("------Listando Usuario con el ID: " + idListar + "-----\"");
		ArrayList<String> registrosUsuarios = new ArrayList<String>();
		try
		{
			FileReader datosLeidos = new FileReader(ubiArchivo);
			BufferedReader bufferdatos = new BufferedReader(datosLeidos);
			String linea = "";
			while (linea != null){
				linea = bufferdatos.readLine();
				if(linea != null){
					registrosUsuarios.add(linea);
				}
			}
			datosLeidos.close();
			Iterator<String> apuntadorListaUsuarios = registrosUsuarios.iterator();
			while (apuntadorListaUsuarios.hasNext()) {
				String itemApuntador = apuntadorListaUsuarios.next();
				int ubicacion = itemApuntador.indexOf(",");
				int wcedula = Integer.parseInt(itemApuntador.substring(0, ubicacion));
				if(wcedula == idListar) {
					System.out.println(itemApuntador);
				}
			}
		}catch(IOException e) {}
	}

	@Override
	public boolean ConsultarID(int idConsultar) {
		int id = -1;
		boolean verificar = false;
		ArrayList<String> registroUsuarios = new ArrayList<String>();
		String wcadena = "";
		try {
			FileReader datosLeidos = new FileReader(ubiArchivo);
			BufferedReader bufferdatos = new BufferedReader(datosLeidos);
			while(wcadena != null) {
				wcadena = bufferdatos.readLine();
				if (wcadena != null) {
					registroUsuarios.add(wcadena);
				}
			}
			datosLeidos.close();
		} catch (IOException e) {}

		Iterator<String> apuntadorListaUsuarios = registroUsuarios.iterator();
		while (apuntadorListaUsuarios.hasNext() && verificar != true) {
			String itemApuntador = apuntadorListaUsuarios.next();
			int ubicacion = itemApuntador.indexOf(",");
			id = Integer.parseInt(itemApuntador.substring(0, ubicacion));
			if(id == idConsultar) {
				verificar = true;
			}
		}
		return verificar;
	}

	public void SecuencialAAleatorio() {
		Scanner teclado = new Scanner(System.in);
		Usuario usuario = new Usuario();
		ArrayList<String> registrosUsuarios = new ArrayList<String>();
		try {
			FileReader datosLeidos = new FileReader(ubiArchivo);
			BufferedReader bufferdatos = new BufferedReader(datosLeidos);
			String linea = "";
			while (linea != null) {
				linea = bufferdatos.readLine();
				if (linea != null) {
					registrosUsuarios.add(linea);
				}
			}
			datosLeidos.close();
			Iterator<String> apuntadorListaUsuarios = registrosUsuarios.iterator();

			File archiv = new File("./datos/ArchivosSecuenciales/copiaSecuencialaAleatorio.txt"); //Elimino el archivo para no tener problemas con duplicado de datos
			archiv.delete();

			while (apuntadorListaUsuarios.hasNext()) { //Llevando de String a la clase de Usuario en sus diferentes tipos de datos
				String itemApuntador = apuntadorListaUsuarios.next();
				int ubicacion = itemApuntador.indexOf(",");
				int ubicacionAux = 0;

				System.out.println("ID: " + itemApuntador.substring(ubicacionAux,ubicacion));
				usuario.setId(Integer.parseInt(itemApuntador.substring(0, ubicacion))) ;
				ubicacionAux = ubicacion+1;
				ubicacion = itemApuntador.lastIndexOf(",");
				String itemApuntadorAux = itemApuntador.substring(ubicacionAux,ubicacion);

				ubicacion = itemApuntadorAux.indexOf(",");
				usuario.setNombre(itemApuntadorAux.substring(0,ubicacion));

				usuario.setSalario(Long.parseLong(itemApuntadorAux.substring(ubicacion+1, itemApuntadorAux.length())));

				ubicacion = itemApuntador.lastIndexOf(",");

				usuario.setFechaNacimiento(LocalDate.parse(itemApuntador.substring(ubicacion+1, itemApuntador.length())));
				//Una vez guardados los diferentes datos a su correspondiente variable se guardan de manera Aleatoria
				RandomAccessFile archivo = new RandomAccessFile("./datos/ArchivosSecuenciales/copiaSecuencialaAleatorio.txt","rw");

				archivo.seek(archivo.length());
				archivo.writeInt(usuario.getId());
				archivo.writeUTF(usuario.getNombre());
				archivo.writeLong(usuario.getSalario());
				DateTimeFormatter ymd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				archivo.writeUTF(usuario.getFechaNacimiento().format(ymd));

				archivo.close();
			}
		}catch (IOException e) {}

		System.out.println("El proceso de pasar los datos del Archivo Secuencial a uno Aleatorio se ha completado con Exito");
		System.out.println("\n Imprimiendo los datos desde el Archivo Aleatorio: ");

		String wcadena="";
		try
		{
			String archivoPersonas = "./datos/ArchivosSecuenciales/copiaSecuencialaAleatorio.txt";
			RandomAccessFile archivo = new RandomAccessFile(archivoPersonas,"r");
			archivo.seek(0);
			while(archivo.getFilePointer() < archivo.length()){
				wcadena = archivo.readInt() + ", ";
				wcadena += archivo.readUTF() + ", ";
				wcadena += archivo.readLong() + ", ";
				wcadena += archivo.readUTF();

				System.out.println(wcadena);
			}
			archivo.close();
		}catch(IOException e) {}
	}

	@SuppressWarnings("unchecked")
	public void SecuencialASerializado() {
		ArrayList<String> registrosUsuarios = new ArrayList<String>();
		ArrayList<Usuario> usuarioList = new ArrayList<Usuario>();
		ArrayList<Usuario> usuarioListAux = new ArrayList<Usuario>();

		try {
			FileReader datosLeidos = new FileReader(ubiArchivo);
			BufferedReader bufferdatos = new BufferedReader(datosLeidos);
			String linea = "";
			while (linea != null) {
				linea = bufferdatos.readLine();
				if (linea != null) {
					registrosUsuarios.add(linea);
				}
			}
			datosLeidos.close();

			File archiv = new File("./datos/ArchivosSecuenciales/copiaSecuencialaSerializado.txt"); //Elimino el archivo para no tener problemas con duplicado de datos
			archiv.delete();
		}catch (IOException e) {}

		Iterator<String> apuntadorListaUsuarios = registrosUsuarios.iterator();

		while (apuntadorListaUsuarios.hasNext()) { //Llevando de String a la clase de Usuario en sus diferentes tipos de datos
			String itemApuntador = apuntadorListaUsuarios.next();
			int ubicacion = itemApuntador.indexOf(",");
			int ubicacionAux = 0;
			Usuario usuario = new Usuario();

			System.out.println("ID: " + itemApuntador.substring(ubicacionAux,ubicacion));
			usuario.setId(Integer.parseInt(itemApuntador.substring(0, ubicacion))) ;
			ubicacionAux = ubicacion+1;
			ubicacion = itemApuntador.lastIndexOf(",");
			String itemApuntadorAux = itemApuntador.substring(ubicacionAux,ubicacion);

			ubicacion = itemApuntadorAux.indexOf(",");
			usuario.setNombre(itemApuntadorAux.substring(0,ubicacion));

			usuario.setSalario(Long.parseLong(itemApuntadorAux.substring(ubicacion+1, itemApuntadorAux.length())));

			ubicacion = itemApuntador.lastIndexOf(",");
			usuario.setFechaNacimiento(LocalDate.parse(itemApuntador.substring(ubicacion+1, itemApuntador.length())));

			usuarioList.add(usuario);
		}

		try { //Escribir los datos en archivo serializado
			FileOutputStream ruta_salida = new FileOutputStream("./datos/ArchivosSecuenciales/copiaSecuencialaSerializado.txt");
			ObjectOutputStream archivo_salida = new ObjectOutputStream(ruta_salida);

			archivo_salida.writeObject(usuarioList);
			archivo_salida.close();

			//Leer los datos de archivo serializado para posteriormente imprimirlos
			FileInputStream ruta_entrada = new FileInputStream("./datos/ArchivosSecuenciales/copiaSecuencialaSerializado.txt");
			ObjectInputStream archivo_entrada = new ObjectInputStream(ruta_entrada);

			usuarioListAux = (ArrayList<Usuario>) archivo_entrada.readObject();
			archivo_entrada.close();
		} catch (Exception e) {}

		System.out.println("El proceso de pasar los datos del Archivo Secuencial a uno Serializado se ha completado con Exito");
		System.out.println("\n Imprimiendo los datos desde el Archivo Serializado: ");

		for(Usuario usuarioAux : usuarioListAux) {
			System.out.println(usuarioAux.getId() + ", "
					+ usuarioAux.getNombre() + ", "
					+ usuarioAux.getSalario() + ", "
					+ usuarioAux.getFechaNacimiento());
		}
	}
}
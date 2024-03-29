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
import java.util.Scanner;

import bean.Usuario;
import interfaces.ICRUD;

public class lAaleatorios implements ICRUD<Usuario> {

	private String ubiArchivo = "./datos/ArchivosAleatorios/usuariosAleatorio.dat";

	@Override
	public void IngresarUsuario(Object usuario) {
		try {
			RandomAccessFile archivo = new RandomAccessFile(ubiArchivo, "rw");

			archivo.seek(archivo.length());
			archivo.writeInt(((Usuario) usuario).getId());
			archivo.writeUTF(((Usuario) usuario).getNombre());
			archivo.writeLong(((Usuario) usuario).getSalario());
			DateTimeFormatter ymd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			archivo.writeUTF(((Usuario) usuario).getFechaNacimiento().format(ymd));

			archivo.close();
		} catch (IOException a) {
			System.out.println(a);
		}
	}

	@Override
	public void ModificarUsuario(int idModificar) {
		Scanner teclado = new Scanner(System.in);
		ArrayList<Usuario> usuariosLista = new ArrayList<Usuario>();
		int idPersonaAux = 0;
		try {
			RandomAccessFile archivo = new RandomAccessFile(ubiArchivo, "rw");
			archivo.seek(0);
			while (archivo.getFilePointer() < archivo.length()) {
				idPersonaAux = archivo.readInt();
				Usuario datosUsuario = new Usuario();
				if (idPersonaAux != idModificar) {
					datosUsuario.setId(idPersonaAux);
					datosUsuario.setNombre(archivo.readUTF());
					datosUsuario.setSalario(archivo.readLong());
					datosUsuario.setFechaNacimiento(LocalDate.parse(archivo.readUTF()));
				} else {
					System.out.println("Nuevos Datos del Usuario");
					datosUsuario.setId(idPersonaAux);
					System.out.println("Nombre del Usuario: ");
					datosUsuario.setNombre(teclado.nextLine());
					System.out.println(datosUsuario.getNombre());
					System.out.println("Salario del Usuario: ");
					datosUsuario.setSalario(teclado.nextLong());
					System.out.println("Fecha de Nacimiento (AAAA-MM-DD): ");
					teclado.nextLine();
					datosUsuario.setFechaNacimiento(LocalDate.parse(teclado.nextLine()));

					archivo.readUTF();
					archivo.readLong();
					archivo.readUTF();
				}
				usuariosLista.add(datosUsuario);
			}
			archivo.close();

			File archivo1 = new File(ubiArchivo);
			archivo1.delete();
		} catch (IOException e) {}

		for (int i = 0; i < usuariosLista.size(); i++) {
			Usuario datosUsuarioNuevo = new Usuario();
			datosUsuarioNuevo.setId(usuariosLista.get(i).getId());
			datosUsuarioNuevo.setNombre(usuariosLista.get(i).getNombre());
			datosUsuarioNuevo.setSalario(usuariosLista.get(i).getSalario());
			datosUsuarioNuevo.setFechaNacimiento(usuariosLista.get(i).getFechaNacimiento());

			IngresarUsuario(datosUsuarioNuevo);
		}
	}

	@Override
	public void EliminarUsuario(int idEliminar) {
		ArrayList<Usuario> usuariosLista = new ArrayList<Usuario>();
		int idPersonaAux = 0;
		try {
			RandomAccessFile archivo = new RandomAccessFile(ubiArchivo, "rw");
			archivo.seek(0);
			while (archivo.getFilePointer() < archivo.length()) {
				idPersonaAux = archivo.readInt();
				if (idPersonaAux != idEliminar) {
					Usuario datosUsuario = new Usuario();
					datosUsuario.setId(idPersonaAux);
					datosUsuario.setNombre(archivo.readUTF());
					datosUsuario.setSalario(archivo.readLong());
					datosUsuario.setFechaNacimiento(LocalDate.parse(archivo.readUTF()));
					usuariosLista.add(datosUsuario);
				} else {
					archivo.readUTF();
					archivo.readLong();
					archivo.readUTF();
				}
			}
			archivo.close();
			File archivo1 = new File(ubiArchivo);
			archivo1.delete();
			archivo.close();
			for (int i = 0; i < usuariosLista.size(); i++) {
				Usuario datosUsuarioNuevo = new Usuario();
				datosUsuarioNuevo.setId(usuariosLista.get(i).getId());
				datosUsuarioNuevo.setNombre(usuariosLista.get(i).getNombre());
				datosUsuarioNuevo.setSalario(usuariosLista.get(i).getSalario());
				datosUsuarioNuevo.setFechaNacimiento(usuariosLista.get(i).getFechaNacimiento());

				IngresarUsuario(datosUsuarioNuevo);
			}
		} catch (IOException e) {
		}
	}

	@Override
	public void ListarUsuarios() {
		String wcadena = "";
		try {
			RandomAccessFile archivo = new RandomAccessFile(ubiArchivo, "r");
			archivo.seek(0);
			while (archivo.getFilePointer() < archivo.length()) {
				wcadena = archivo.readInt() + ", ";
				wcadena += archivo.readUTF() + ", ";
				wcadena += archivo.readLong() + ", ";
				wcadena += archivo.readUTF();

				System.out.println(wcadena);
			}
			archivo.close();
		} catch (IOException e) {
		}
	}

	@Override
	public void ListarIDS() {
		int id = -1;
		try {
			RandomAccessFile archivo = new RandomAccessFile(ubiArchivo, "r");
			archivo.seek(0);
			while (archivo.getFilePointer() < archivo.length()) {
				id = archivo.readInt();
				archivo.readUTF();
				archivo.readLong();
				archivo.readUTF();

				System.out.println(id);
			}
			archivo.close();
		} catch (IOException e) {
		}
	}

	@Override
	public void ConsultarUsuario(int idConsultar) {
		int id = -1;
		String wcadena = "";
		try {
			RandomAccessFile archivo = new RandomAccessFile(ubiArchivo, "r");
			archivo.seek(0);
			while (archivo.getFilePointer() < archivo.length() && id != idConsultar) {
				id = archivo.readInt();
				if (id == idConsultar) {
					wcadena = Integer.toString(id) + ", ";
					wcadena += archivo.readUTF() + ", ";
					wcadena += archivo.readLong() + ", ";
					wcadena += archivo.readUTF();
				} else {
					archivo.readUTF();
					archivo.readLong();
					archivo.readUTF();
				}
				System.out.println(wcadena);
			}
			archivo.close();
		} catch (IOException e) {
		}
	}

	@Override
	public boolean ConsultarID(int idConsultar) {
		int id = -1;
		boolean verificar = false;
		try {
			RandomAccessFile archivo = new RandomAccessFile(ubiArchivo, "r");
			archivo.seek(0);
			while (archivo.getFilePointer() < archivo.length() && id != idConsultar) {
				id = archivo.readInt();
				if (id == idConsultar) {
					verificar = true;
				}
				archivo.readUTF();
				archivo.readLong();
				archivo.readUTF();
			}
			archivo.close();
		} catch (IOException e) {}
		return verificar;
	}

	public void AleatorioASecuencial() {
		ArrayList<Usuario> usuariosLista = new ArrayList<Usuario>();
		try {
			RandomAccessFile archivo = new RandomAccessFile(ubiArchivo, "r");
			archivo.seek(0);
			while (archivo.getFilePointer() < archivo.length()) {
				Usuario datosUsuario = new Usuario();

				datosUsuario.setId(archivo.readInt());
				datosUsuario.setNombre(archivo.readUTF());
				datosUsuario.setSalario(archivo.readLong());
				datosUsuario.setFechaNacimiento(LocalDate.parse(archivo.readUTF()));
				usuariosLista.add(datosUsuario);
			}
			archivo.close();

			File archiv = new File("./datos/ArchivosAleatorios/copiaAleatorioaSecuencial.txt");
			archiv.delete();
			for (int i = 0; i < usuariosLista.size(); i++) {
				Usuario datosUsuarioNuevo = new Usuario();
				datosUsuarioNuevo.setId(usuariosLista.get(i).getId());
				datosUsuarioNuevo.setNombre(usuariosLista.get(i).getNombre());
				datosUsuarioNuevo.setSalario(usuariosLista.get(i).getSalario());
				datosUsuarioNuevo.setFechaNacimiento(usuariosLista.get(i).getFechaNacimiento());
				FileWriter archivo1 = new FileWriter("./datos/ArchivosAleatorios/copiaAleatorioaSecuencial.txt", true);
				BufferedWriter escribir = new BufferedWriter(archivo1);
				PrintWriter linea = new PrintWriter(escribir);
				linea.append(datosUsuarioNuevo.getId() + "," + datosUsuarioNuevo.getNombre() + ","
						+ datosUsuarioNuevo.getSalario() + "," + datosUsuarioNuevo.getFechaNacimiento() + "\n");
				linea.close();
			}
		} catch (IOException e) {
		}

		System.out.println("El proceso de pasar los datos del Archivo Aleatorio a uno Secuencial se ha completado con Exito");
		System.out.println("\n Imprimiendo los datos desde el Archivo Secuencial: ");
		try {
			FileReader datosLeidos = new FileReader("./datos/ArchivosAleatorios/copiaAleatorioaSecuencial.txt");
			try {
				BufferedReader bufferdatos = new BufferedReader(datosLeidos);
				String linea = "";
				while (linea != null) {
					linea = bufferdatos.readLine();
					if (linea != null) {
						System.out.println(linea);
					}
				}
				bufferdatos.close();
			} catch (IOException e) {
			}
		} catch (IOException e) {
		}
	}

	@SuppressWarnings("unchecked")
	public void AleatorioASerializado() {
		ArrayList<Usuario> usuariosLista = new ArrayList<>();
		ArrayList<Usuario> usuarioListAux = new ArrayList<Usuario>();
		try {
			RandomAccessFile archivo = new RandomAccessFile(ubiArchivo, "r");
			archivo.seek(0);
			while (archivo.getFilePointer() < archivo.length()) {
				Usuario datosUsuario = new Usuario();

				datosUsuario.setId(archivo.readInt());
				datosUsuario.setNombre(archivo.readUTF());
				datosUsuario.setSalario(archivo.readLong());
				datosUsuario.setFechaNacimiento(LocalDate.parse(archivo.readUTF()));
				usuariosLista.add(datosUsuario);
			}
			archivo.close();

			//Eliminar Copia para evitar errores
			File archiv = new File("./datos/ArchivosAleatorios/copiaAleatorioaSerializado.txt");
			archiv.delete();

			//Guardar Lista de Datos de manera Serializada
			FileOutputStream ruta_salida = new FileOutputStream("./datos/ArchivosAleatorios/copiaAleatorioaSerializado.txt");
			ObjectOutputStream archivo_salida = new ObjectOutputStream(ruta_salida);

			archivo_salida.writeObject(usuariosLista);
			archivo_salida.close();
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}catch (Exception e) { System.out.println(e);}

		System.out.println("El proceso de pasar los datos del Archivo Aleatorio a uno Serializado se ha completado con Exito");
		System.out.println("\n Imprimiendo los datos desde el Archivo Serializado: ");
		try {
			FileInputStream ruta_entrada = new FileInputStream("./datos/ArchivosAleatorios/copiaAleatorioaSerializado.txt");
			ObjectInputStream archivo_entrada = new ObjectInputStream(ruta_entrada);

			usuarioListAux = (ArrayList<Usuario>) archivo_entrada.readObject();
			archivo_entrada.close();
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}catch (Exception e) { System.out.println(e);}
		for(Usuario usuario : usuarioListAux) {
			System.out.println(usuario.getId() + ", "
					+ usuario.getNombre() + ", "
					+ usuario.getSalario() + ", "
					+ usuario.getFechaNacimiento());
		}
	}
}
package vista;

public class MenuPrincipal {
	public void menuPrincipal() {
		System.out.println("-----------------------------------------");
		System.out.println("|             Menu Principal            |");
		System.out.println("|           INGRESAR USUARIOS           |");
		System.out.println("|---------------------------------------|");
		System.out.println("|   1. Archivos Planos Secuenciales     |");
		System.out.println("|   2. Archivos Planos Aleatorios       |");
		System.out.println("|   0. Salir                            |");
		System.out.println("-----------------------------------------");
	}

	public void menuSecuencial() {
		System.out.println("-----------------------------------------------");
		System.out.println("|       Archivos Planos Secuenciales          |");
		System.out.println("|---------------------------------------------|");
		System.out.println("|   1. Ingresar Usuario                       |");
		System.out.println("|   2. Modificar Usuario                      |");
		System.out.println("|   3. Eliminar Usuario                       |");
		System.out.println("|   4. Listar Usuario                         |");
		System.out.println("|   5. Consultar Usuario                      |");
		System.out.println("|   6. De Archivos Secuenciales a Aleatorios  |");
		System.out.println("|   7. De Archivos Secuenciales a Serializados|");
		System.out.println("|   0. Regresar a Menu Principal              |");
		System.out.println("----------------------------------------------");

	}

	public void menuAleatorio() {
		System.out.println("/n---------------------------------------------");
		System.out.println("|       Archivos Planos Aleatorios          |");
		System.out.println("|-------------------------------------------|");
		System.out.println("|   1. Ingresar Usuario                     |");
		System.out.println("|   2. Modificar Usuario                    |");
		System.out.println("|   3. Eliminar Usuario                     |");
		System.out.println("|   4. Listar Usuario                       |");
		System.out.println("|   5. Consultar Usuario                    |");
		System.out.println("|   6. De Archivos Aleatorios a Secuenciales|");
		System.out.println("|   7. De Archivos Aleatorios a Serializados|");
		System.out.println("|   0. Regresar a Menu Principal            |");
		System.out.println("---------------------------------------------");
	}
}

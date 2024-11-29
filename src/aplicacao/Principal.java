package aplicacao;

import java.util.Date;

import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

public class Principal {

	public static void main(String[] args) {
		
		Departamento dep = new Departamento(1, "Eletronicos");
		System.out.println(dep);
		
		Vendedor ven = new Vendedor(
				111, "Maria Aparecida", "maria@hotmail.com", new Date(), 4000.0, dep);
		System.out.println(ven);
	}
}

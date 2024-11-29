package aplicacao;

import java.util.List;

import DaoJdbc.DaoVendedor;
import DaoJdbc.FabricaDao;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

public class Principal {

	public static void main(String[] args) {
		
		DaoVendedor dv = FabricaDao.criaVendedorImpl();
		
		System.out.println("***** TESTE 1 VENDEDOR FINDBYID *****");
		Vendedor vdd = dv.findById(4);
		System.out.println(vdd);
		
		
		System.out.println("\n***** TESTE 2 VENDEDOR FINDBYDEPARTAMENTO *****");
		Departamento dep = new Departamento(2, null);
		List<Vendedor> lista = dv.findByDepartamento(dep);
		for (Vendedor a: lista)
			System.out.println(a);
		
		
		System.out.println("\n***** TESTE 3 VENDEDOR FINDALL *****");
		lista = dv.findAll();
		for (Vendedor a: lista)
			System.out.println(a);
	}
}

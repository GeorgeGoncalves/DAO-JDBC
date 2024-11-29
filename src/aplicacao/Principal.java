package aplicacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DaoJdbc.DaoDepartamento;
import DaoJdbc.DaoVendedor;
import DaoJdbc.FabricaDao;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

public class Principal {

	public static void main(String[] args) {
		
		DaoVendedor dv = FabricaDao.criaVendedorImpl();
		
		/*System.out.println("***** TESTE 1 VENDEDOR FINDBYID *****");
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
		
		
		System.out.println("\n***** TESTE 4 VENDEDOR INSERT *****");
		Vendedor nv = new Vendedor(null, "Lucas Silva", "lucas@hotmail.com",
				new Date(), 5199.0, dep);
		//dv.insert(nv);
		System.out.println("Inserido! Novo ID: " + nv.getId());
				
		
		System.out.println("\n***** TESTE 5 VENDEDOR UPDATE*****");
		vdd = dv.findById(15);
		vdd.setNome("Marcos Paulo");
		dv.update(vdd);
		System.out.println("Update completado!");
		
		
		System.out.println("\n***** TESTE 6 VENDEDOR DELETEID*****");
		dv.deleteById(5);
		System.out.println("Delete completado.");*/
		
		
		DaoDepartamento dd = FabricaDao.criaDepartamentoImpl();
		
		
		System.out.println("\n***** TESTE 1 DEPARTAMENTO INSERT ******");
		Departamento dpt = new Departamento(null, "Manutencao");
		dd.insert(dpt);
		System.out.println("Novo departamento inserido: " + dpt.getId());
				
		
		System.out.println("\n***** TESTE 2 DEPARTAMENTO FINDBYID ******");
		dpt = dd.findById(11);
		System.out.println(dpt);

		
		System.out.println("\n***** TESTE 3 DEPARTAMENTO UPDATE ******");
		dpt = dd.findById(3);
		dpt.setNome("Moda");
		dd.update(dpt);
		System.out.println("Update realizado com sucesso!");
		
		
		System.out.println("\n***** TESTE 4 DEPARTAMENTO FINDALL ******");
		List<Departamento> lista = new ArrayList<>();
		lista = dd.findAll();
		for (Departamento a: lista)
			System.out.println(a);
		
		
		System.out.println("\n***** TESTE DEPARTAMENTO 5 DELETEBYID *****");
		dd.delete(19);
		System.out.println("ID apagado com sucesso!");
	}
}

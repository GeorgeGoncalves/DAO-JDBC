package aplicacao;

import DaoJdbc.DaoVendedor;
import DaoJdbc.FabricaDao;
import modelo.entidades.Vendedor;

public class Principal {

	public static void main(String[] args) {
		
		DaoVendedor dv = FabricaDao.criaVendedorImpl();
		
		System.out.println("***** TESTE 1 VENDEDOR *****");
		Vendedor vdd = dv.findById(4);
		System.out.println(vdd);
	}
}

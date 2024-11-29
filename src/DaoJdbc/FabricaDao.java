package DaoJdbc;

import DaoJdbcImpl.DepartamentoImpl;
import DaoJdbcImpl.VendedorImpl;
import bancoDados.BancoDados;

public class FabricaDao {

	public static DaoVendedor criaVendedorImpl() {
		return new VendedorImpl(BancoDados.conectar());		
	}
	
	public static DaoDepartamento criaDepartamentoImpl() {
		return new DepartamentoImpl();
	}
}

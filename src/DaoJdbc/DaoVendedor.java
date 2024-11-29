package DaoJdbc;

import java.util.List;

import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

public interface DaoVendedor {

	void insert(Vendedor obj);
	void update(Vendedor obj);
	void deleteById(Integer id);
	Vendedor findById(Integer id);
	List<Vendedor> findAll();	
	List<Vendedor> findByDepartamento(Departamento departamento);
}

package DaoJdbc;

import java.util.List;

import modelo.entidades.Departamento;

public interface DaoDepartamento {

	void insert(Departamento obj);
	void update(Departamento obj);
	void delete(Integer id);
	Departamento findById(Integer id);
	List<Departamento> findAll();	
}

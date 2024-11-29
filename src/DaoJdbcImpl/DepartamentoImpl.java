package DaoJdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import DaoJdbc.DaoDepartamento;
import bancoDados.BDException;
import bancoDados.BancoDados;
import modelo.entidades.Departamento;

public class DepartamentoImpl implements DaoDepartamento {

	private Connection conexao;
	
	public DepartamentoImpl(Connection conexao) {
		this.conexao = conexao;
	}
	
	@Override
	public void insert(Departamento obj) {
		
		PreparedStatement ps = null;
		
		try {
			
			ps = conexao.prepareStatement("INSERT INTO Departamento "
					+ "(Nome) "
					+ "VALUES "
					+ "(?)",
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, obj.getNome());
			
			int linhas = ps.executeUpdate();
			
			if (linhas > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				BancoDados.fecharRS(rs);
			}
		} catch (SQLException e) {
			throw new BDException(e.getMessage());
		} finally {
			BancoDados.fecharStatement(ps);
		}
	}

	@Override
	public void update(Departamento obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Departamento findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Departamento> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

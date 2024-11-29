package DaoJdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		
		PreparedStatement ps = null;
		
		try {
			
			ps = conexao.prepareStatement("UPDATE departamento "
					+ "SET Nome = ? "
					+ "WHERE Id = ?");
			
			ps.setString(1, obj.getNome());
			ps.setInt(2, obj.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new BDException(e.getMessage());
		} finally {
			BancoDados.fecharStatement(ps);
		}
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Departamento findById(Integer id) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = conexao.prepareStatement("select departamento. *"
					+ "from departamento where id = ?");
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {				
				Departamento dep = instanciarDepartamento(rs);
				return dep;
			}
			return null;
		} catch (SQLException e) {
			throw new BDException(e.getMessage());
		} finally {
			BancoDados.fecharStatement(ps);
		}		
	}

	private Departamento instanciarDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("id"));
		dep.setNome(rs.getString("nome"));
		return dep;
	}

	@Override
	public List<Departamento> findAll() {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = conexao.prepareStatement("select * from departamento");
			
			rs = ps.executeQuery();
			
			List<Departamento> lista = new ArrayList<>();
			
			while (rs.next()) 
				lista.add(instanciarDepartamento(rs));
			
			return lista;
		} catch (SQLException e) {
			throw new BDException(e.getMessage());
		} finally {
			BancoDados.fecharStatement(ps);
			BancoDados.fecharRS(rs);
		}
		
	}

}

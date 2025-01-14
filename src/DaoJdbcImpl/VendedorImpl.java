package DaoJdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DaoJdbc.DaoVendedor;
import bancoDados.BDException;
import bancoDados.BancoDados;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

public class VendedorImpl implements DaoVendedor {

	private Connection conexao;
	
	public VendedorImpl(Connection conexao) {
		this.conexao = conexao;
	}
	
	@Override
	public void insert(Vendedor obj) {
		
		PreparedStatement ps = null;
		
		try {
			
			ps = conexao.prepareStatement("INSERT INTO vendedor " 
					+ "(Nome, Email, dataNasc, salario, DepartamentoId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, obj.getNome());
			ps.setString(2, obj.getEmail());
			ps.setDate(3, new java.sql.Date(obj.getDataNasc().getTime()));
			ps.setDouble(4, obj.getSalario());
			ps.setInt(5, obj.getDepartamento().getId());
			
			int linhas = ps.executeUpdate();
			
			if (linhas > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
			
				BancoDados.fecharRS(rs);
			} else {
				throw new BDException(
						"Erro não esperado! Linhas não afetadas!");
			}
			
		} catch (SQLException e) {
			throw new BDException(e.getMessage());
		} finally {
			BancoDados.fecharStatement(ps);
		}
	}

	@Override
	public void update(Vendedor obj) {
		
		PreparedStatement ps = null;
		
		try {
			
			ps = conexao.prepareStatement("UPDATE vendedor "  
					+ "SET Nome = ?, Email = ?, DataNasc = ?, "
					+ "salario = ? "
					+ "where id = ?");
			
			ps.setString(1, obj.getNome());
			ps.setString(2, obj.getEmail());
			ps.setDate(3, new java.sql.Date(obj.getDataNasc().getTime()));
			ps.setDouble(4, obj.getSalario());
			ps.setInt(5, obj.getDepartamento().getId());
			ps.setInt(5, obj.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new BDException(e.getMessage());
		}finally {
			BancoDados.fecharStatement(ps);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		
		PreparedStatement ps = null;
		
		try  {
			
			ps = conexao.prepareStatement("DELETE FROM vendedor "
					+ "WHERE Id = ?" );
			
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new BDException(e.getMessage());
		} finally {
			BancoDados.fecharStatement(ps);
		}
		
	}

	@Override
	public Vendedor findById(Integer id) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = conexao.prepareStatement(
					"SELECT vendedor.*,departamento.Nome as DepNome "
					+ "FROM vendedor INNER JOIN departamento "
					+ "ON vendedor.DepartamentoId = departamento.Id "
					+ "WHERE vendedor.Id = ? ");
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				
				Departamento dep = instanciarDepartamento(rs);
				
				Vendedor ven = instanciarVendedor(rs, dep);
				
				return ven;						
			}
			return null;
		} catch (SQLException e) {
			throw new BDException(e.getMessage());
		}finally {
			BancoDados.fecharStatement(ps);;
			BancoDados.fecharRS(rs);
		}
	}

	private Vendedor instanciarVendedor(ResultSet rs, Departamento dep)
			throws SQLException {
		Vendedor ven = new Vendedor();
		ven.setId(rs.getInt("id"));
		ven.setNome(rs.getString("Nome"));
		ven.setEmail(rs.getString("email"));
		ven.setDataNasc(rs.getDate("dataNasc"));
		ven.setSalario(rs.getDouble("salario"));
		ven.setDepartamento(dep);
		return ven;
	}

	private Departamento instanciarDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new  Departamento();
		dep.setId(rs.getInt("departamentoId"));
		dep.setNome(rs.getString("depNome"));
		return dep;
	}

	@Override
	public List<Vendedor> findAll() {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
		ps = conexao.prepareStatement(
					 "SELECT vendedor.*,departamento.Nome as DepNome " 
					 + "FROM vendedor INNER JOIN departamento " 
					 + "ON vendedor.DepartamentoId = departamento.Id "
					 + "ORDER BY Nome");
		
		rs = ps.executeQuery();
		
		List<Vendedor> lista = new ArrayList<>();
		Map<Integer, Departamento> map = new HashMap<>();
		
		while (rs.next()) {
			Departamento dep = instanciarDepartamento(rs);
			
			if (dep == null) {
				
				dep = instanciarDepartamento(rs);
				map.put(rs.getInt("DepartamentoId"), dep);
			}
			
			Vendedor vdd = instanciarVendedor(rs, dep); 
			lista.add(vdd);
		}
		
		return lista;		
		} catch (SQLException e) {
			throw new BDException(e.getMessage());
		} finally {
			BancoDados.fecharStatement(ps);
			BancoDados.fecharRS(rs);
		}		
	}

	@Override
	public List<Vendedor> findByDepartamento(Departamento departamento) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
		
			ps = conexao.prepareStatement( 
					 "SELECT vendedor.*,departamento.Nome as DepNome " 
					 + "FROM vendedor INNER JOIN departamento " 
					 + "ON vendedor.DepartamentoId = departamento.Id "
					 + "WHERE DepartamentoId = ? "
					 + "ORDER BY Nome");
			
			ps.setInt(1, departamento.getId());
			rs = ps.executeQuery();
			
			List<Vendedor> lista = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();
			
			while (rs.next()) {
				
				Departamento dep = map.get(rs.getInt("departamentoId"));
				
				if (dep == null) {
					
					dep = instanciarDepartamento(rs);
					map.put(rs.getInt("departamentoId"), dep);
				}
				
				Vendedor vdd = instanciarVendedor(rs, dep);
				lista.add(vdd);
			}
			return lista;			
		} catch (SQLException e) {
			throw new BDException(e.getMessage());
		} finally {
			BancoDados.fecharStatement(ps);
			BancoDados.fecharRS(rs);
		}
	}
}

package bancoDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDados {
	
	private static Connection conexao = null;

	public static Connection conectar() {

		if (conexao == null) {

			try {

				conexao = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/curso",
						"root", "root");

			} catch (SQLException e) {
				throw new BDException(e.getMessage());
			}
		}
		return conexao;
	}

	public static Connection fecharConexao() {
		
		if (conexao != null) {
			
			try {
				
				conexao.close();
				
			} catch (SQLException e) {
				throw new BDException(e.getMessage());
			}
		}
		return conexao;
	}
	
	public static Statement fecharStatement(Statement st) {
		
		if (st != null) {
			try {
				
				st.close();
				
			} catch (SQLException e) {
				throw new BDException(e.getMessage());
			}
		}
		return st;
	}
	
	public static ResultSet fecharRS(ResultSet rs) {
		
		if (rs != null) {
			try {
				
				rs.close();
				
			} catch (SQLException e) {
				throw new BDException(e.getMessage());
			}
		}
		return rs;
	}
}

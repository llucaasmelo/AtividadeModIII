import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    conectaDAO conexao = new conectaDAO();
    
    public void cadastrarProduto(ProdutosDTO produto) {
        try {
            conn = conexao.connectDB();  // Conecte-se ao banco de dados
            String sql = "INSERT INTO produto(nome, valor, status) VALUES (?, ?, ?)";
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + ex);
                }
            }
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos() {
        // Implemente a lógica para listar produtos
   
        return listagem;
    }
}


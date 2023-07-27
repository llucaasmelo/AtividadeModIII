import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

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
        
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("SELECT * FROM produto");
            resultset = prep.executeQuery();
            
            while (resultset.next()) {
                ProdutosDTO product = new ProdutosDTO();
                
                product.setId(resultset.getInt("id"));
                product.setNome(resultset.getString("nome"));
                product.setValor(resultset.getInt("valor"));
                product.setStatus(resultset.getString("status"));
                listagem.add(product);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar produto: " + ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + ex);
                }
            }
        }

        return listagem;
    }
    

}


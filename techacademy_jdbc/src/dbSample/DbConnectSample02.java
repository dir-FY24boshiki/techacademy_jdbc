package dbSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnectSample02 {

    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            // 1. ドライバーのクラスをJava上で読み込む
            Class.forName("com.mysql.cj.jdbc.Driver");

        // 2. DBと接続する
        con = DriverManager.getConnection(
                "jdbc:mysql://localhost/world?useSSL=false&allowPublicKeyRetrieval=true",
                "root",
                "aW7dSaFE"
                );

        // 3. DBとやりとりする窓口（Statementオブジェクト）の作成
        stmt = con.createStatement();

        // 4, 5. Select文の実行と結果を格納／代入
        String sql = "select * from country limit 50";
        rs = stmt.executeQuery(sql);

        // 6. 結果を表示する
        sql = "update county set Population = 10500 where code = 'ABW'";
        int count = stmt.executeUpdate(sql);
        System.out.println(count);

        } catch (ClassNotFoundException e) {
            System.err.println("JDBCドライバーのロードに失敗しました。");
            e.printStackTrace();;
        } catch (SQLException e) {
            System.err.println("データベースに異常が発生しました。");
            e.printStackTrace();
        }finally {
            //7. 接続を閉じる
            if(rs != null) {
                try {
                    rs.close();
                }catch (SQLException e) {
                    System.err.println("ResultSetを閉じるときにエラーが発生しました。");
                    e.printStackTrace();
                }
            }
            if(stmt != null) {
                try {
                    stmt.close();
                }catch (SQLException e) {
                    System.err.println("Statementを閉じるときにエラーが発生しました。");
                    e.printStackTrace();
                }
            }
            if(con != null) {
                try {
                    con.close();
                }catch(SQLException e) {
                    System.err.println("データベース切断時にエラーが発生しました。");
                    e.printStackTrace();
                }
            }
        }
        


        
    }
    
}
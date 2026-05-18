package persistence;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import persistence.ConnectionPROJECT;
public class Metadata {

      public static void showInfoBD(DatabaseMetaData dbmd) throws SQLException {
        String name = dbmd.getDatabaseProductName();
        String driver = dbmd.getDriverName();
        String url = dbmd.getURL();
        String user = dbmd.getUserName();

        System.out.println("Informació de la BD:");
        System.out.println("Nom: " + name);
        System.out.println("Driver: " + driver);
        System.out.println("URL: " + url);
        System.out.println("Usuari: " + user);
    }

    public static void showTablesInfo(DatabaseMetaData dbmd) throws SQLException {
        String[] types = {"TABLE"};
        try (ResultSet rs = dbmd.getTables(dbmd.getCatalogTerm(), dbmd.getSchemaTerm(), null, types);) {

            while (rs.next()) {
                String catalog = rs.getString(1);
                String schema = rs.getString(2);
                String table = rs.getString(3);
                String type = rs.getString(4);
                System.out.println(type + " - Cataleg: " + catalog +
                        ", Esquema: " + schema + ", Nom: " + table);
            }
        }
    }

    public static void showColumns(DatabaseMetaData dbmd, String taula) throws SQLException {
        System.out.println("Columnes de la taula: " + taula);
        try (ResultSet columnes = dbmd.getColumns(null, null, taula, null);) {
            while (columnes.next()) {
                String name = columnes.getString("COLUMN_NAME"); // 4
                String type = columnes.getString("TYPE_NAME"); // 6
                String size = columnes.getString("COLUMN_SIZE"); // 7
                String isNullable = columnes.getString("IS_NULLABLE"); // 18
                System.out.println("Columna: " + name + ", Tipus: " + type +
                        ", Mida: " + size + ", Pot ser nul·la? " + isNullable);
            }
        }
    }

    public static void main(String[] args) {
        DatabaseMetaData dbmd = null;
        try {
            Connection connection = ConnectionPROJECT.getConnection();
            dbmd = connection.getMetaData();
            showInfoBD(dbmd);
            System.out.println("----");

            showTablesInfo(dbmd);
            System.out.println("----");
            showColumns(dbmd, "BOOKS");
            System.out.println("----");
            showColumns(dbmd,"PERSONS");
            System.out.println("----");
            showColumns(dbmd,"LOANS");
            System.out.println("----");




        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}



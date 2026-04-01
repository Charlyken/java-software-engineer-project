package com.esigelec.dao.dominante;

import com.esigelec.dao.DataBaseConnection;
import com.esigelec.model.Dominante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DominanteDAOImpl implements  DominanteDAO {

    private static final String INSERT = "INSERT INTO DOMINANTES"
                                            +"(nom_dominante, description) VALUES (?, ?)";

    private static final String SELECT = "SELECT * FROM DOMINANTES ORDER BY id_dominante";
    private static final String FIND_BY_ID = "SELECT * FROM DOMINANTES WHERE id_dominante = ?";
    private static final String DELETE_BY_ID = "DELETE FROM DOMINANTES WHERE id_dominante = ?";
    private static final String UPDATE = "UPDATE DOMINANTES " +
                                         "SET nom_dominante = ?, description = ? " +
                                         "WHERE id_dominante = ?";
    /**
     * Creation d'une dominante
     * @param dominante
     */
    @Override
    public void createDominante (Dominante dominante) {
        if (dominante == null) {
            return;
        }

        try(Connection con = DataBaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT)){
            ps.setString(1, dominante.getNomDominante());
            ps.setString(2, dominante.getDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Dominante>  getAllDominantes() {
        List<Dominante> dominantes = new ArrayList<>();

        try(Connection con = DataBaseConnection.getConnection();
           PreparedStatement ps = con.prepareStatement(SELECT);
           ResultSet rs = ps.executeQuery()){

            while (rs.next()) {
                Dominante d = new Dominante();
                d.setId(rs.getLong("id_dominante"));
                d.setNomDominante(String.valueOf(rs.getString("nom_dominante")));
                d.setDescription(String.valueOf(rs.getString("description")));

                dominantes.add(d);
            }

        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        return dominantes;
    }

    @Override
    public void updateDominante(Dominante dominante) {
        try(Connection con = DataBaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(UPDATE)) {
            ps.setLong(3, dominante.getId());
            ps.setString(1, dominante.getNomDominante());
            ps.setString(2, dominante.getDescription());
            ps.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteDominante(Long id) {
        try(Connection con = DataBaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(DELETE_BY_ID)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public  Dominante getDominanteById(Long id) {
        Dominante d = null;
        try(Connection con = DataBaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(FIND_BY_ID)){
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                d = new Dominante();
               d.setNomDominante(String.valueOf(rs.getString("nom_dominante")));
               d.setDescription(String.valueOf(rs.getString("description")));
            }
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }

        return d;
    }
}

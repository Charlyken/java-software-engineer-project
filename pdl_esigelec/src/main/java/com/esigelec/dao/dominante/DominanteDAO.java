package com.esigelec.dao.dominante;

import com.esigelec.model.Dominante;

import java.util.List;

public interface DominanteDAO {

    void createDominante(Dominante dominante);
    List<Dominante> getAllDominantes();
    Dominante getDominanteById(Long id);
    void updateDominante(Dominante dominante);
    void deleteDominante(Long id);
}

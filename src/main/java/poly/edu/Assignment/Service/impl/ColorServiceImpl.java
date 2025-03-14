package poly.edu.Assignment.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.edu.Assignment.Service.ColorService;
import poly.edu.Assignment.dao.ColorDAO;
import poly.edu.Assignment.model.Color;

@Service
public class ColorServiceImpl implements ColorService {
    @Autowired
    ColorDAO dao;
    @Override
    public List<Color> findAll() {
        return dao.findAll();
    }

    // @Override
    // public Color findByID(int id) {
    //    return dao.findAllById(id);
    // }

}

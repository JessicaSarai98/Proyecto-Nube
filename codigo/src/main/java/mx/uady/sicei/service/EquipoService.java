package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uady.sicei.exception.NotFoundException;
import mx.uady.sicei.model.Equipo;
import mx.uady.sicei.model.request.EquipoRequest;
import mx.uady.sicei.repository.EquipoRepository;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public List<Equipo> getEquipos() {

        List<Equipo> equipos = new LinkedList<>();
        equipoRepository.findAll().iterator().forEachRemaining(equipos::add);

        return equipos;
    }

    public Equipo crearEquipo(EquipoRequest request) {
        Equipo equipo = new Equipo();
        equipo.setModelo(request.getModelo());
        equipo = equipoRepository.save(equipo);
        return equipo;
    }

    public Equipo editarEquipo(Integer id, EquipoRequest request){
        Equipo equipo =  equipoRepository.findById(id).orElseThrow(() -> new NotFoundException());
        equipo.setModelo(request.getModelo());
        equipoRepository.save(equipo);
        return equipo;
    }
    
}

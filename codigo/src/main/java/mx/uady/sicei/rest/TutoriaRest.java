package mx.uady.sicei.rest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.uady.sicei.model.Tutoria;
import mx.uady.sicei.service.TutoriaService;
import mx.uady.sicei.model.request.TutoriaRequest;

@RestController 
@RequestMapping("/api")
public class TutoriaRest{
    @Autowired
    private TutoriaService tutoriaService;

    @GetMapping("/tutorias")
    public ResponseEntity<List<Tutoria>> obtenerTutorias(){
        List<Tutoria> tutorias = tutoriaService.getTutorias();
        return ResponseEntity.ok(tutorias);
    }

    @PostMapping("/tutorias")
    public ResponseEntity<Tutoria> postTutoria(@RequestBody @Valid TutoriaRequest request) throws URISyntaxException{
        Tutoria tutoria = tutoriaService.crearTutoria(request);

        return ResponseEntity.created(new URI("/tutorias/"+tutoria.getId())).body(tutoria);
    }

    @PutMapping("/tutorias/{idalumno}/{idprofesor}")
    public ResponseEntity<Tutoria> putTutorias(@PathVariable Integer idalumno, @PathVariable Integer idprofesor, @RequestBody TutoriaRequest request)
        throws URISyntaxException{
            Tutoria tutoria = tutoriaService.editarTutoria(idalumno,idprofesor,request);
            return ResponseEntity.ok().body(tutoria);
    }

    @GetMapping("/tutorias/{idalumno}/{idprofesor}")
    public ResponseEntity<Tutoria> getTutorial(@PathVariable Integer idalumno, @PathVariable Integer idprofesor){
        return ResponseEntity.ok().body(tutoriaService.getTutoria(idalumno,idprofesor));
    }

    @DeleteMapping("/tutorias/{idalumno}/{idprofesor}")
    public ResponseEntity<Void> eliminarTutoria(@PathVariable Integer idalumno, @PathVariable Integer idprofesor){
        tutoriaService.borrarTutoria(idalumno,idprofesor);
        return ResponseEntity.noContent().build();
    }
}
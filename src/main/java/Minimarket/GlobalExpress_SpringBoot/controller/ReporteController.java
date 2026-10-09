package Minimarket.GlobalExpress_SpringBoot.controller;

import Minimarket.GlobalExpress_SpringBoot.dto.ReporteRequest;
import Minimarket.GlobalExpress_SpringBoot.model.Reporte;
import Minimarket.GlobalExpress_SpringBoot.service.ReporteService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public List<Reporte> listar() {
        return reporteService.listar();
    }

    @GetMapping("/{id}")
    public Reporte obtener(@PathVariable Integer id) {
        return reporteService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reporte generar(@RequestBody ReporteRequest solicitud) {
        return reporteService.generar(solicitud);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        reporteService.eliminar(id);
    }
}

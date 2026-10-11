package Minimarket.GlobalExpress_SpringBoot.controller;

import Minimarket.GlobalExpress_SpringBoot.dto.ReporteRequest;
import Minimarket.GlobalExpress_SpringBoot.dto.VentaReporteDto;
import Minimarket.GlobalExpress_SpringBoot.model.Reporte;
import Minimarket.GlobalExpress_SpringBoot.service.ReporteService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
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

    /** GET /api/reportes/ventas?desde=2026-07-01&hasta=2026-09-30 */
    @GetMapping("/ventas")
    public List<VentaReporteDto> ventasDelPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        return reporteService.ventasDelPeriodo(desde, hasta);
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

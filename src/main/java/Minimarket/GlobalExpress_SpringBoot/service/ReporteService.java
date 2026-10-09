package Minimarket.GlobalExpress_SpringBoot.service;

import Minimarket.GlobalExpress_SpringBoot.dto.ReporteRequest;
import Minimarket.GlobalExpress_SpringBoot.exception.RecursoNoEncontradoException;
import Minimarket.GlobalExpress_SpringBoot.model.Reporte;
import Minimarket.GlobalExpress_SpringBoot.model.Usuario;
import Minimarket.GlobalExpress_SpringBoot.model.Venta;
import Minimarket.GlobalExpress_SpringBoot.repository.ReporteRepository;
import Minimarket.GlobalExpress_SpringBoot.repository.UsuarioRepository;
import Minimarket.GlobalExpress_SpringBoot.repository.VentaRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReporteService {

    private final ReporteRepository reporteRepository;
    private final UsuarioRepository usuarioRepository;
    private final VentaRepository ventaRepository;

    public ReporteService(ReporteRepository reporteRepository,
                          UsuarioRepository usuarioRepository,
                          VentaRepository ventaRepository) {
        this.reporteRepository = reporteRepository;
        this.usuarioRepository = usuarioRepository;
        this.ventaRepository = ventaRepository;
    }

    @Transactional(readOnly = true)
    public List<Reporte> listar() {
        return reporteRepository.findAllByOrderByIdReporteDesc();
    }

    @Transactional(readOnly = true)
    public Reporte obtener(Integer id) {
        return reporteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Reporte", id));
    }

    @Transactional
    public Reporte generar(ReporteRequest req) {
        if (req == null || req.idUsuario() == null || req.idVenta() == null
                || req.periodoInicio() == null || req.periodoFin() == null) {
            throw new IllegalArgumentException("Usuario, venta y período (inicio y fin) son obligatorios.");
        }
        if (req.periodoInicio().isAfter(req.periodoFin())) {
            throw new IllegalArgumentException("El inicio del período no puede ser posterior al fin.");
        }
        Usuario usuario = usuarioRepository.findById(req.idUsuario())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario", req.idUsuario()));
        Venta venta = ventaRepository.findById(req.idVenta())
                .orElseThrow(() -> new RecursoNoEncontradoException("Venta", req.idVenta()));

        Reporte reporte = new Reporte();
        reporte.setFechaGeneracion(LocalDateTime.now());
        reporte.setPeriodoInicio(req.periodoInicio());
        reporte.setPeriodoFin(req.periodoFin());
        reporte.setUsuario(usuario);
        reporte.setVenta(venta);
        return reporteRepository.save(reporte);
    }

    @Transactional
    public void eliminar(Integer id) {
        reporteRepository.delete(obtener(id));
    }
}

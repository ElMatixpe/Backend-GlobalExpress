package Minimarket.GlobalExpress_SpringBoot.controller;

import Minimarket.GlobalExpress_SpringBoot.dto.VentaRequest;
import Minimarket.GlobalExpress_SpringBoot.model.DetalleVenta;
import Minimarket.GlobalExpress_SpringBoot.model.Venta;
import Minimarket.GlobalExpress_SpringBoot.security.UsuarioSesion;
import Minimarket.GlobalExpress_SpringBoot.service.VentaService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public List<Venta> listar(@RequestParam(required = false) String estado) {
        return ventaService.listar(estado);
    }

    @GetMapping("/{id}")
    public Venta obtener(@PathVariable Integer id) {
        return ventaService.obtener(id);
    }

    @GetMapping("/{id}/detalles")
    public List<DetalleVenta> detalles(@PathVariable Integer id) {
        return ventaService.detalles(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Venta registrar(@RequestBody VentaRequest solicitud, @AuthenticationPrincipal UsuarioSesion sesion) {
        VentaRequest conVendedor = new VentaRequest(
                sesion.idUsuario(), solicitud.cliente(), solicitud.metodoPago(),
                solicitud.estado(), solicitud.detalles());
        return ventaService.registrar(conVendedor);
    }
}

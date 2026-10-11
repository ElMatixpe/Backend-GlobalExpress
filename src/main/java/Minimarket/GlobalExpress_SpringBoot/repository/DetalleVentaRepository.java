package Minimarket.GlobalExpress_SpringBoot.repository;

import Minimarket.GlobalExpress_SpringBoot.model.DetalleVenta;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {

    List<DetalleVenta> findByVentaIdVenta(Integer idVenta);

    // Detalles de todas las ventas de un período (fecha de la venta >= inicio y < fin)
    List<DetalleVenta> findByVentaFechaGreaterThanEqualAndVentaFechaLessThan(
            LocalDateTime inicio, LocalDateTime fin);
}
package Minimarket.GlobalExpress_SpringBoot.repository;

import Minimarket.GlobalExpress_SpringBoot.model.Venta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Integer> {

    // Historial: las más recientes primero
    List<Venta> findAllByOrderByIdVentaDesc();

    // Historial filtrado por estado ("Pagado" o "Cancelado")
    List<Venta> findByEstadoOrderByIdVentaDesc(String estado);
}

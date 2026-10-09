package Minimarket.GlobalExpress_SpringBoot.repository;

import Minimarket.GlobalExpress_SpringBoot.model.DetalleVenta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {

    List<DetalleVenta> findByVentaIdVenta(Integer idVenta);
}

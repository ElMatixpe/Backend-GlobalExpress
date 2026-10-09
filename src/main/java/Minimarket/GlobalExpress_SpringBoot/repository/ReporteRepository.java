package Minimarket.GlobalExpress_SpringBoot.repository;

import Minimarket.GlobalExpress_SpringBoot.model.Reporte;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReporteRepository extends JpaRepository<Reporte, Integer> {

    List<Reporte> findAllByOrderByIdReporteDesc();
}

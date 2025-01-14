package uz.ilmnajot.voltu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.voltu.entity.Route;

@Repository
public interface RouteRepository  extends JpaRepository<Route, Long> {
}
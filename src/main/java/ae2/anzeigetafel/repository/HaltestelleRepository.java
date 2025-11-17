// HaltestelleRepository.java
package ae2.anzeigetafel.repository;

import ae2.anzeigetafel.entity.Haltestelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface HaltestelleRepository extends JpaRepository<Haltestelle, Integer> {

    // Findet eine Haltestelle anhand ihres Namens.
    // Optional bedeutet: Kann vorhanden sein oder nicht.
    Optional<Haltestelle> findByName(String name);
}

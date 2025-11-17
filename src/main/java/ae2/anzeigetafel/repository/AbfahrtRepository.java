// AbfahrtRepository.java
package ae2.anzeigetafel.repository;

import ae2.anzeigetafel.entity.Abfahrt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AbfahrtRepository extends JpaRepository<Abfahrt, Integer> {
    // Dieses Repository ermöglicht das Arbeiten mit der Tabelle "abfahrt"
    // Es erbt Standard-Methoden wie findAll(), findById(), save() usw.

    @Query("SELECT a FROM Abfahrt a " +
            "WHERE a.haltestelle.id = :haltestelleId " +                      // nur Abfahrten der gewählten Haltestelle
            "AND (a.tatsaechlicheAbfahrt >= :now OR a.geplantAbfahrt >= :now) " + // nur zukünftige Abfahrten
            "ORDER BY COALESCE(a.tatsaechlicheAbfahrt, a.geplantAbfahrt) ASC")    // sortiert nach der echten Abfahrtszeit
    List<Abfahrt> findUpcomingByHaltestelleId(
            @Param("haltestelleId") Integer haltestelleId,   // ID der Haltestelle
            @Param("now") LocalDateTime now                   // aktueller Zeitpunkt
    );
}

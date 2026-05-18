package fr.insa.archi.labotrack.sample.repository;

import fr.insa.archi.labotrack.sample.domain.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Long> {
}

package br.rosaluz.banking.system.pix.repository;

import br.rosaluz.banking.system.pix.model.Pix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PixRepository extends JpaRepository<Pix, Long> {
}

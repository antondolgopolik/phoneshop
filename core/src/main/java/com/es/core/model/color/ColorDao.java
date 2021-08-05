package com.es.core.model.color;

import java.util.Optional;
import java.util.Set;

public interface ColorDao {

    Optional<Color> get(Long id);

    Set<Color> getByPhoneId(Long id);
}

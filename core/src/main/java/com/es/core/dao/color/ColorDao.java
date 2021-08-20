package com.es.core.dao.color;

import com.es.core.model.phone.Color;

import java.util.Optional;
import java.util.Set;

public interface ColorDao {

    Optional<Color> get(Long id);

    Set<Color> getByPhoneId(Long id);
}

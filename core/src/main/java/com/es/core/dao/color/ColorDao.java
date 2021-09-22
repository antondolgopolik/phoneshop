package com.es.core.dao.color;

import com.es.core.model.phone.Color;

import java.util.Optional;
import java.util.Set;

public interface ColorDao {

    Set<Color> get(Long phoneId);
}
